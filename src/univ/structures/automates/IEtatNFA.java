package univ.structures.automates;

import java.util.Set;

/**
 * <h2>Interface d'un état d'un automate fini non déterministe</h2>
 * 
 * Cette interface va définir la fonction de transition d'un NFA :
 * 
 * &delta; : Q x &Sigma; &rarr; P(Q)
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
public interface IEtatNFA<A> extends IEtat<A> {
	
	/**
	 * Fonction de transition de l'état courant par le symbole a
	 * @param a le symbole
	 * @return l'ensemble des états résultat de &delta;(q, a) avec
	 * 	q l'état courant.
	 */
	Set<IEtatNFA<A>> delta(A a);

}
