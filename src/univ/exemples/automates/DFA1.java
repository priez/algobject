package univ.exemples.automates;

import univ.structures.automates.Automate;
import univ.structures.automates.IAutomate;
import univ.structures.automates.IEtatDFA;
import univ.structures.automates.IAutomate.GenEtat;
import univ.structures.automates.Transition;
import univ.structures.collections.SetTools;

/**
 * Soit <i>M</i> l'automate reconnaissant le langage <i>a + b</i>.
 * <ul>
 * 	<li> &Sigma; : {a, b}
 *	<li> Q : {-0-, -1-, -2-}
 *	<li> i : -0-
 *	<li> F : {-1-, -2-}
 *	<li> &delta; : {(-0-, a, -1-), (-0-, b, -1-)}
 * </u>
 */
public class DFA1 {
	
	public static void main(String[] args) {
		IAutomate<Character, IEtatDFA<Character>> M;
		// initialisation de l'automate M avec comme paramètres :
		// -- le générateur DFA
		// -- l'alphabet {a, b}
		M = new Automate<Character, IEtatDFA<Character>>(
				GenEtat.DFA,
				SetTools.setFactory('a', 'b'));
		
		// création des deux états
		IEtatDFA<Character>	e0 = M.createEtat(),
							e1 = M.createEtat(),
							e2 = M.createEtat();	// état inutil...
		
		// paramétrage de l'état initial
		M.setInitial(e0);
		// paramétrage des états finaux
		M.setFinal(e1);
		M.setFinal(e2);
		
		// ajout des transitions (-0-, a, -1-) et (-0-, b, -1-)
		M.addTransition(new Transition<Character>(e0, e1, 'a'));
		M.addTransition(new Transition<Character>(e0, e1, 'b'));
		
		// affichage de l'automate
		System.out.println(M);
		
		IEtatDFA<Character> init, q;
		
		// récupération de l'état initial
		init = M.getInitial();
		
		// récupération l'état résultat de la transition &delta; (i, a)
		q = init.delta('a');
		
		// affichage de l'état + test s'il est final
		System.out.println(q + " : " + q.isFinal());
		
		// tentative d'ajout une nouvelle transition non viable dans un DFA
		// -- (-0-, a, -2-)
		try { M.addTransition(new Transition<Character>(e0, e2, 'a'));
		} catch (Exception e) {	System.out.println(e.getMessage());	}
	}

}
