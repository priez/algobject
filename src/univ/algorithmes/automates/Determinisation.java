package univ.algorithmes.automates;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;

import univ.structures.automates.Automate;
import univ.structures.automates.IAutomate;
import univ.structures.automates.IAutomate.GenEtat;
import univ.structures.automates.IEtatDFA;
import univ.structures.automates.IEtatNFA;
import univ.structures.automates.Transition;
import univ.structures.collections.SetTools;

public class Determinisation {
	
	/**
	 * Déterminisation de NFA
	 * @param <A> le type des valeurs des transitions
	 * @param nfa l'automate à déterminiser
	 * @return un DFA reconnaissant le même langage que le NFA passé en paramètre 
	 */
	public static<A> IAutomate<A, IEtatDFA<A>> determinise(IAutomate<A, IEtatNFA<A>> nfa) {
		// le résultat
		IAutomate<A, IEtatDFA<A>> dfa;
		// des états du DFA
		IEtatDFA<A> p, q;
		// une table associative liant le dfa et le nfa
		Map<SortedSet<IEtatNFA<A>>, IEtatDFA<A>> map;
		map = new HashMap<SortedSet<IEtatNFA<A>>, IEtatDFA<A>>();
		// les "états" du DFA non encore étudiés 
		Stack<SortedSet<IEtatNFA<A>>> nontraite;
		nontraite = new Stack<SortedSet<IEtatNFA<A>>>();
		// états du NFA associés à un état du DFA
		SortedSet<IEtatNFA<A>> etats, etatsEntrants;
		
		dfa = new Automate<A, IEtatDFA<A>>(GenEtat.DFA, nfa.getAlphabet());
		if (nfa.getInitial() != null) {
			// initialisation
			etats = new TreeSet<IEtatNFA<A>>();
			etats.add(nfa.getInitial());
			p = creation(etats, dfa, map, nontraite);
			p.setInitial();
			
			// partie principale
			while (!nontraite.isEmpty()) {
				etats = nontraite.pop();
				p = map.get(etats);
				for (A a : nfa.getAlphabet()) {
					etatsEntrants = sortants(etats, a);
					if (!map.containsKey(etatsEntrants))
						creation(etatsEntrants, dfa, map, nontraite);
					q = map.get(etatsEntrants);
					dfa.addTransition(new Transition<A>(p, q, a));
		}	}	}
		return dfa;
	}
	
	/**
	 * Récupération des états d'arrivés par l'ensemble des états de set par la 
	 * transition a.
	 */
	private static<A> SortedSet<IEtatNFA<A>> sortants(SortedSet<IEtatNFA<A>> set, A a) {
		SortedSet<IEtatNFA<A>> entrants = new TreeSet<IEtatNFA<A>>();
		for (IEtatNFA<A> e : set) 
			entrants.addAll(e.delta(a));
		return entrants;
	}
	
	/**
	 * Créer l'état du dfa associé à l'ensemble d'états du nfa.
	 */
	private static<A> IEtatDFA<A> creation(
		SortedSet<IEtatNFA<A>> set, 
		IAutomate<A, IEtatDFA<A>> dfa, 
		Map<SortedSet<IEtatNFA<A>>, IEtatDFA<A>> map,
		Stack<SortedSet<IEtatNFA<A>>> nontraite
	) {
		IEtatDFA<A> p = dfa.createEtat();
		map.put(set, p);
		nontraite.push(set);
		for (IEtatNFA<A> e : set) {
			if (e.isFinal()) {
				p.setFinal();
				break;
		}	}
		return p;
	}
	
	public static void main(String[] args) {
		/**
		 * Q = {e1, e2, e3, e4}
		 * i = e1
		 * F = {e2, e4}
		 * d :
		 * (e1, 0, e2) (e1, 1, e1) (e1, 1, e2)
		 * (e2, 0, e1) (e2, 0, e2) (e2, 0, e3)
		 * (e2, 1, e2) (e2, 1, e3)
		 * (e3, 0, e4) (e3, 1, e3) 
		 */
		IAutomate<Integer, IEtatNFA<Integer>> nfa = new Automate<Integer, IEtatNFA<Integer>>(GenEtat.NFA, SetTools.setFactory(0, 1));
		IEtatNFA<Integer> e1, e2, e3, e4;
		e1 = nfa.createEtat();
		e2 = nfa.createEtat();
		e3 = nfa.createEtat();
		e4 = nfa.createEtat();
		
		e1.setInitial();
		
		e2.setFinal();
		e4.setFinal();
		
		nfa.addTransition(new Transition<Integer>(e1, e2, 0));
		nfa.addTransition(new Transition<Integer>(e1, e1, 1));
		nfa.addTransition(new Transition<Integer>(e1, e2, 1));
		
		nfa.addTransition(new Transition<Integer>(e2, e1, 0));
		nfa.addTransition(new Transition<Integer>(e2, e2, 0));
		nfa.addTransition(new Transition<Integer>(e2, e3, 0));
		nfa.addTransition(new Transition<Integer>(e2, e1, 1));
		nfa.addTransition(new Transition<Integer>(e2, e3, 1));
		
		nfa.addTransition(new Transition<Integer>(e3, e4, 0));
		nfa.addTransition(new Transition<Integer>(e3, e3, 1));
		
		System.out.println(nfa);
		
		System.out.println(determinise(nfa));
	}
}
