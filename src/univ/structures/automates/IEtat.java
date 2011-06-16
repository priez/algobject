package univ.structures.automates;

import java.util.Set;

import univ.structures.graphes.ISommet;

/**
 * <h2>Interface d'état d'un automate</h2>
 * 
 * <p>Un état va permettre de contrôler les déplacements dans l'automate.
 * 
 * <p>Les déplacements vont s'effectuer par l'intermédiaire de la fonction
 * de transition &delta; de l'automate.
 * 
 * <p>Cette interface va donner un tronc commun aux états des DFA ou des NFA.
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
 * 
 * @note les implantations d'états doivent étendre AbstractEtat.
 */
public interface IEtat<A> extends ISommet<ITransition<A>> {
	
	/**
	 * Indique si l'état est initial.
	 * @return <code>true</code> <b>si</b> l'état est initial, <code>false</code> <b>sinon</b>
	 */
	boolean isInitial();
	
	/**
	 * Indique si l'état est final.
	 * @return <code>true</code> <b>si</b> l'état est final, <code>false</code> <b>sinon</b>
	 */
	boolean isFinal();
	
	/**
	 * @return l'ensemble des transitions partant de l'état courant. 
	 */
	Set<ITransition<A>> getTransition();
	
	/**
	 * Rend l'état initial
	 * @attention cela modifie la structure de l'automate!!!
	 */
	void setInitial();
	
	/**
	 * Rend l'état final
	 * @attention cela modifie la structure de l'automate !!!
	 */
	void setFinal();
	
	/**
	 * Configure la finalité de l'état
	 * @attention cela modifie la structure de l'automate !!!
	 * @param b la finalité
	 * @post isFinal() <b>si</b> <code>b == true</code>,
	 * 		!isFinal() <b>sinon</b>
	 */
	void setFinal(boolean b);
	
	
}
