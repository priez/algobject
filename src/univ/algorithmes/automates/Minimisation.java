package univ.algorithmes.automates;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import univ.structures.automates.Automate;
import univ.structures.automates.IAutomate;
import univ.structures.automates.IEtatDFA;
import univ.structures.automates.IAutomate.GenEtat;
import univ.structures.automates.Transition;
import univ.structures.collections.SetTools;
import univ.structures.ensembles.IUnionFind;
import univ.structures.ensembles.UnionFind;

/**
 * Minimisation de DFA accessible
 * 
 */
public class Minimisation {
	
	public static<A> IAutomate<A, IEtatDFA<A>> minimiser(IAutomate<A, IEtatDFA<A>> dfa) {
		IAutomate<A, IEtatDFA<A>> min;
		IEtatDFA<A> p;
		Map<Set<IEtatDFA<A>>, IEtatDFA<A>> map;
		// partition d'états équivalent au sens de Myhill-Nérode
		IUnionFind<IEtatDFA<A>> partition = EquivMyhillNerode(dfa);
		System.out.println("part ::" + partition);
		// création d'un automate
		min = new Automate<A, IEtatDFA<A>>(GenEtat.DFA, dfa.getAlphabet());
		// création des états + association partition -> nouveaux états
		map = new HashMap<Set<IEtatDFA<A>>, IEtatDFA<A>>();
		for (Set<IEtatDFA<A>> set : partition.partition()) {
			p = min.createEtat();
			if (set.contains(dfa.getInitial()))
				p.setInitial();
			if (set.iterator().next().isFinal())
				p.setFinal();
			map.put(set, p);
		}
		// création des transitions
		for (Set<IEtatDFA<A>> set : partition.partition()) {
			for (A a : dfa.getAlphabet()) {
				min.addTransition(new Transition<A>(
					map.get(set), 
					map.get(partition.trouverEnsemble(
						set.iterator().next().delta(a))),
					a));
		}	}
		return min;
	}
	
	private static<A> IUnionFind<IEtatDFA<A>> EquivMyhillNerode(IAutomate<A, IEtatDFA<A>> dfa) {
		Map<List<Set<IEtatDFA<A>>>, IEtatDFA<A>> map;
		List<Set<IEtatDFA<A>>> li;
		SortedMap<IEtatDFA<A>, List<IEtatDFA<A>>> parse = parseDFA(dfa);
		IUnionFind<IEtatDFA<A>> uf = initialisation(dfa), 
								uftmp = null;
		
		while (!uf.equals(uftmp)) {
			uftmp = uf;
			uf = singletons(dfa);
			for (Set<IEtatDFA<A>> set : uftmp.partition()) {
				map = new HashMap<List<Set<IEtatDFA<A>>>, IEtatDFA<A>>();
				if (set.size() > 1) {
					for (IEtatDFA<A> p : set) {
						li = oldPart(parse.get(p), uftmp);
						if (map.containsKey(li))
							uf.union(p, map.get(li));
						else map.put(li, p);
		}	}	}	}
		return uf;
	}
	
	private static<A> List<Set<IEtatDFA<A>>> oldPart(List<IEtatDFA<A>> parse, IUnionFind<IEtatDFA<A>> uf) {
		List<Set<IEtatDFA<A>>> li = new LinkedList<Set<IEtatDFA<A>>>();
		for (IEtatDFA<A> e : parse)
			li.add(uf.trouverEnsemble(e));
		return li;
	}
	
	private static<A> SortedMap<IEtatDFA<A>, List<IEtatDFA<A>>> parseDFA(IAutomate<A, IEtatDFA<A>> dfa) {
		SortedMap<IEtatDFA<A>, List<IEtatDFA<A>>> map = new TreeMap<IEtatDFA<A>, List<IEtatDFA<A>>>();
		for (IEtatDFA<A> e : dfa.getEtats()) {
			List<IEtatDFA<A>> liste = new LinkedList<IEtatDFA<A>>();
			for (A a : dfa.getAlphabet()) 
				liste.add(e.delta(a));
			map.put(e, liste);
		}
		return map;
	}
	
	private static<A> IUnionFind<IEtatDFA<A>> singletons(IAutomate<A, IEtatDFA<A>> dfa) {
		IUnionFind<IEtatDFA<A>> uf = new UnionFind<IEtatDFA<A>>();
		for (IEtatDFA<A> e : dfa.getEtats())
			uf.creerEnsemble(e);
		return uf;
	}

	private static<A> IUnionFind<IEtatDFA<A>> initialisation(IAutomate<A, IEtatDFA<A>> dfa) {
		IUnionFind<IEtatDFA<A>> uf = new UnionFind<IEtatDFA<A>>();
		
		IEtatDFA<A> ter = null, nonter = null;
		for (IEtatDFA<A> e : dfa.getEtats()) {
			uf.creerEnsemble(e);
			if (e.isFinal())
				if (ter == null) ter = e;
				else uf.union(ter, e);
			else
				if (nonter == null) nonter = e;
				else uf.union(nonter, e);
		}
		return uf;
	}
	
	public static void main(String[] args) {
		IAutomate<Integer, IEtatDFA<Integer>> m;
		m= new Automate<Integer, IEtatDFA<Integer>>(
			GenEtat.DFA, 
			SetTools.setFactory(0,1));
		IEtatDFA<Integer> a,b,c,d,e,f,g,h,i;//,j;
		
		a = m.createEtat(); a.setInitial();
		b = m.createEtat();
		c = m.createEtat(); c.setFinal();
		d = m.createEtat();
		e = m.createEtat();
		f = m.createEtat(); f.setFinal();
		g = m.createEtat();
		h = m.createEtat();
		i = m.createEtat(); i.setFinal();
		//j= m.createEtat();
		
		m.addTransition(new Transition<Integer>(a,b,0));
		m.addTransition(new Transition<Integer>(a,e,1));
		
		m.addTransition(new Transition<Integer>(b,c,0));
		m.addTransition(new Transition<Integer>(b,f,1));
		
		m.addTransition(new Transition<Integer>(c,d,0));
		m.addTransition(new Transition<Integer>(c,h,1));
		
		m.addTransition(new Transition<Integer>(d,e,0));
		m.addTransition(new Transition<Integer>(d,h,1));
		
		m.addTransition(new Transition<Integer>(e,f,0));
		m.addTransition(new Transition<Integer>(e,i,1));
		
		m.addTransition(new Transition<Integer>(f,g,0));
		m.addTransition(new Transition<Integer>(f,b,1));
		
		m.addTransition(new Transition<Integer>(g,h,0));
		m.addTransition(new Transition<Integer>(g,b,1));
		
		m.addTransition(new Transition<Integer>(h,i,0));
		m.addTransition(new Transition<Integer>(h,c,1));
		
		m.addTransition(new Transition<Integer>(i,a,0));
		m.addTransition(new Transition<Integer>(i,e,1));
		
		//m.addTransition(new Transition<Integer>(j,g,0));
		//m.addTransition(new Transition<Integer>(j,e,1));
		
		System.out.println(m);
		
		System.out.println(minimiser(m));
	}
}
