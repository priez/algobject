package univ.structures.automates;

/**
 * <h2>Interface d'un état d'un automate fini déterministe</h2>
 * 
 * Cette interface va définir la fonction de transition d'un DFA :
 * 
 * &delta; : Q x &Sigma; &rarr; Q
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
 * 
 * @author EliX
 *
 * @param <A>
 */
public interface IEtatDFA<A> extends IEtat<A> {
	
	/**
	 * Fonction de transition de l'état courant par le symbole a
	 * @param a le symbole
	 * @return p l'état résultat de &delta;(q, a) avec
	 * 	q l'état courant <b>si</b> la transition existe, <code>null</code> <b>sinon</b>.
	 */
	IEtatDFA<A> delta(A a);

}
