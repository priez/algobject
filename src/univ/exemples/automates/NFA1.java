package univ.exemples.automates;

import univ.structures.automates.Automate;
import univ.structures.automates.IAutomate;
import univ.structures.automates.IAutomate.GenEtat;
import univ.structures.automates.IEtatNFA;
import univ.structures.automates.Transition;

/**
 * Soit <i>M</i> l'automate reconnaissant le langage <i>a + aa + b</i>.
 * 
 * <ul>
 * 	<li> &Sigma; : {a, b}
 * 	<li> Q : {-0-, -1-, -2-}
 *	<li> i : -0-
 * 	<li> F : {-1-, -2-}
 * 	<li> &delta; : {(-0-, a, -1-), (-0-, b, -1-), (-0-, a, -2-), (-1-, a, -2-)}
 * </ul>
 */
public class NFA1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IAutomate<Character, IEtatNFA<Character>> M;
		// initialisation du NFA M
		M = new Automate<Character, IEtatNFA<Character>>(
			GenEtat.NFA);
		
		// construction de l'alphabet 
		M.addSymbole('a');
		M.addSymbole('b');
		
		// création des états
		IEtatNFA<Character> e0 = M.createEtat(),
							e1 = M.createEtat(),
							e2 = M.createEtat();
		
		// paramétrage de l'état initial
		e0.setInitial();
		
		// paramétrage des états finaux
		e1.setFinal();
		e2.setFinal();
		
		// ajout des transitions
		M.addTransition(new Transition<Character>(e0, e1, 'a'));
		M.addTransition(new Transition<Character>(e0, e1, 'b'));
		M.addTransition(new Transition<Character>(e0, e2, 'a'));
		M.addTransition(new Transition<Character>(e1, e2, 'a'));
		
		// affichage du graphe
		System.out.println(M);
		
		// affichage de l'ensemble des états de la transition &delta(i,a)
		System.out.println(e0.delta('a'));
	}

}
