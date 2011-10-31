package univ.structures.automates;

import java.util.Set;

import univ.structures.graphes.IGraphe;
import univ.structures.graphes.ISommet;

/**
 * <h2>Interface d'automate fini</h2>
 * 
 * <p>Les représentations d'automates que l'on choisit
 *		vont être une extension des graphes valués. L'ensemble des valeurs
 * 		associées au graphe vont représenter l'alphabet de l'automate.
 * 
 * <p>Un automate fini va reconnaître un langage régulier
 * 		(cf <i>théorème de Kleene</i>). Nous dirons qu'un automate
 * 		est un quintuplet :
 * <ul>
 * 	<li> &Sigma; <i>l'alphabet</i>
 *  <li> Q l'<i>ensemble d'états</i> (les sommets du graphes)
 *  <li> i un <i>états initial</i> (un sommet particulier, utilisé comme "point 
 *  	de départ" des "mots" reconnus par le langage).
 *  <li> F &sub; Q un <i>ensemble d'états terminaux</i> (des sommets particuliers,
 *  	utilisés comme "marqueurs" de mots reconnus par le langage).
 *  <li> &delta; une <i>fonction de transition</i> d'un état par un symbôle (défini 
 *  	suivant le type de l'automate :	DFA, NFA, ...)   
 * </ul>
 *  
 * <p>On étendra la fonction de transition <u>&delta;</u> aux "mots" de &Sigma;*
 * 		(&Sigma;* = {&epsilon;} &cup; &Sigma; &cup; &Sigma;² &cup; ... et &epsilon;
 * 		le <i>mot vide</i>).<br>
 * 
 * 		Soient <i>w &isin; &Sigma;*</i> un mots et <i>q &isin; Q</i> un état :
 * 		<ul>
 * 			<li><i><u>&delta;</u>(q, w) = q</i> (ou {q} selon la définition de &delta;) <b>si</b> 
 * 				<i>w = &epsilon;</i>,
 * 			<li><i><u>&delta;</u>(q, w) = <u>&delta;</u>(&delta; (q, a), u)</i>
 * 				<b>sinon</b> (avec <i>w = a &middot; u, a &isin; &Sigma;, u &isin; &Sigma;*</i>).
 * 		</ul>
 * 
 * <p>Certaines définitions d'automate fini autorisent plusieurs états
 *  	initiaux, nous donnerons une définition n'en possédant qu'un, l'opération
 *  	de <i>standardisation</i> permettant toujours de se ramener à un automate
 *  	ayant un unique état initial.
 *  
 * <p>Cette interface va définir une interface commune pour les DFA et les NFA. Les
 * NFA sont les automates finis non-déterministes. Les DFA sont les automates finis
 * déterministes. La différence entre NFA et DFA va être sur la fonction de transition.
 * 
 * <ul>	<li>DFA : &delta; : Q x &Sigma; &rarr; Q
 * 		<li>NFA : &delta; : Q x &Sigma; &rarr; P(Q) (P(Q) l'ensemble des parties de Q)
 * </ul>
 * 
 * <hr>
 * 
 * Définitions issues de <u><i>Introduction to Automata Theory, 
 * Languages, and Computation</i></u>
 * de John E.<span style="font-variant: small-caps;">Hopcroft</span>, 
 * Rajeev. <span style="font-variant: small-caps;">Motwani</span> et 
 * Jeffrey D. <span style="font-variant: small-caps;">Ullman</span>
 * 
 * <hr>
 * @author EliX
 *
 * @param <A> le type des symbôles de &Sigma; de l'alphabet de l'automate.
 */
public interface IAutomate<A, E extends IEtat<A>> 
	extends IGraphe<ITransition<A>>, Cloneable {
	
	public static final String 	ETAT_AJOUTE = "1",
								ETAT_ENLEVE = "2",
								TRANS_AJOUTE = "4",
								TRANS_ENLEVE = "8",
								INIT_AJOUTE = "16",
								INIT_ENLEVE = "32",
								TERM_AJOUTE = "64",
								TERM_ENLEVE = "128",
								SYMB_AJOUTE = "256";
	
	/**
	 * <h2>Générateur d'états</h2>
	 * @author EliX
	 *
	 */
	public enum GenEtat {
		DFA {
			@SuppressWarnings("unchecked")
			@Override
			<A, E extends IEtat<A>> E create(IAutomate<A, E> auto, int i) {
				return (E) new EtatDFA<A>((IAutomate<A, IEtat<A>>) auto, i);
			}

			@Override
			boolean deterministe() { return true; }
		},
		NFA {
			@SuppressWarnings("unchecked")
			@Override
			<A, E extends IEtat<A>> E create(IAutomate<A, E> auto, int i) {
				return (E) new EtatNFA<A>((IAutomate<A, IEtat<A>>) auto, i);
			}

			@Override
			boolean deterministe() { return false; }
		};
		
		abstract <A,E extends IEtat<A>> E create(IAutomate<A, E> auto, int i); 
		abstract boolean deterministe();
	}
	
	/**
	 * Ajoute un symbole à l'alphabet de l'automate.
	 * @param a le symbole à ajouter.
	 * @pre a != null
	 */
	void addSymbole(A a);
	
	/**
	 * Créer un nouvel état à l'automate.
	 * @return l'état nouvellement créé.
	 */
	E createEtat();
	
	/**
	 * Ajoute une transition à l'automate.
	 * @param trans
	 * @pre 
	 * <ul>	<li>trans != null
	 * 		<li>les états de la transition sont des états 
	 * 		de l'automate courant.
	 * </ul>
	 */
	void addTransition(ITransition<A> trans);
	
	/**
	 * Rend l'état initial.
	 * @param e l'état à rendre initial
	 * @pre  
	 * <ul>	<li>e != null
	 * 		<li>e est un état de l'automate courant
	 * </ul>
	 * @post <b>si</b> old getInitial() != null <b>alors</b> cet état n'est plus initial
	 * <b>et</b> <code>getInitial().equals(e)</code>.
	 */
	void setInitial(E e);
	
	/**
	 * Rend l'état final.
	 * @param e l'état à rendre final
	 * @pre
	 * <ul>	<li>e != null
	 * 		<li>e est un état de l'automate courant
	 * </ul>
	 * @post <code>getFinals().contains(e)</code>
	 */
	void setFinal(E e);
	
	/**
	 * Rend l'état final ou non final.
	 * @param e l'état considéré.
	 * @param b <code>true</code> pour rendre final l'état, <code>false</code> sinon
	 * @pre 
	 * <ul>	<li>e != null
	 * 		<li>e est un état de l'automate courant
	 * </ul>
	 * @post <b>si</b> <code>b</code> <b>alors</b> 
	 * 		<code>getFinals().contains(e)</code>
	 * 		<b>sinon</b> <code>!getFinals().contains(e)</code> 
	 */
	void setFinal(E e, boolean b);
	
	/**
	 * Retire un état de l'automate.
	 * @param e l'état à retirer.
	 * @pre e != null
	 */
	void removeEtat(E e);
	
	/**
	 * Retire une transition de l'automate.
	 * @param trans la transition à retirer.
	 * @pre trans != null
	 */
	void removeTransition(ITransition<A> trans);
	
	/**
	 * @return l'alphabet associé à l'automate. 
	 */
	Set<A> getAlphabet();
	
	/**
	 * @return l'état initial associé à l'automate.
	 * @post result == null <b>si</b> l'automate n'est pas initialisé.
	 */
	E getInitial();
	
	/**
	 * @return l'ensemble des états de l'automate.
	 */
	Set<E> getEtats();
	
	/**
	 * @return l'ensemble des états finaux de l'automate.
	 */
	Set<E> getEtatsFinaux();
	
	@Override
	ISommet<ITransition<A>> createSommet();
	
	/**
	 * @link UnsupportedOperationException
	 */
	@Override
	void addPaire(ITransition<A> p);

	/**
	 * @link UnsupportedOperationException
	 */
	@Override
	void removeSommet(ISommet<ITransition<A>> s);
	
	/**
	 * @link UnsupportedOperationException
	 */
	@Override
	void removePaire(ITransition<A> p);

}
