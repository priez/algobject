package univ.m2.bioinfo;

import java.util.SortedMap;
import java.util.TreeMap;

import univ.structures.automates.Automate;
import univ.structures.automates.IEtatDFA;

public class ArbreCSuffixes extends Automate<String, IEtatDFA<String>> {
	
	String y;
	int n;
	
	private SortedMap<IEtatDFA<String>, IEtatDFA<String>> ls;
	private IEtatDFA<String> fourche;

	public ArbreCSuffixes(String y, int n) {
		super(GenEtat.DFA);
		this.createEtat().setInitial();
		
		this.y = y;
		this.n = n;
		
		ls = new TreeMap<IEtatDFA<String>, IEtatDFA<String>>();
		
		algo();
	}

	private void algo() {
		int k;
		//IEtatDFA<String> t;
		
		ls.put(this.getInitial(), this.getInitial());
		fourche = this.getInitial();
		k = 0;
		for (int i = 0; i < n; i++) {
			k = Math.max(k, i);
			if (ls.get(fourche) == null) {
				// notion d'arbre ==> il n'y a qu'un état entrant au plus.
				//ITransition<String> trans = fourche.getEntrants().iterator().next();
				//t = (IEtatDFA<String>) trans.getSortant();
				// TODO
			}
		}
	}

}
