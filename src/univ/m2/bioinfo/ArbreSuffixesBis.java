package univ.m2.bioinfo;

import java.util.HashMap;
import java.util.Map;

import univ.structures.automates.Automate;
import univ.structures.automates.IEtatDFA;
import univ.structures.automates.Transition;

public class ArbreSuffixesBis extends Automate<Character, IEtatDFA<Character>> {
	
	char[] y;
	int n;
	
	Map<IEtatDFA<Character>, IEtatDFA<Character>> ls;
	Map<IEtatDFA<Character>, Integer> sortie;
	
	IEtatDFA<Character> fourche;

	public ArbreSuffixesBis(String y) {
		super(GenEtat.DFA);
		this.setInitial(this.createEtat());
		
		this.y = y.toCharArray();
		n = y.length();
		sortie = new HashMap<IEtatDFA<Character>, Integer>();
		ls = new HashMap<IEtatDFA<Character>, IEtatDFA<Character>>();
		
		algo();
	}

	private void algo() {
		int k;
		IEtatDFA<Character> p, q;
		ls.put(this.getInitial(), this.getInitial());
		
		this.fourche = this.getInitial();
		k = 0;
		for (int i = 0; i < n; i++) {
			k = Math.max(k, i);
			k = descLenteBis(ls.get(fourche), k);
			p = fourche;
			for (int j = k; j < n; j++) {
				q = this.createEtat();
				this.addSymbole(y[j]);
				this.addTransition(new Transition<Character>(p, q, y[j]));
				p = q;
			}
			sortie.put(p, i);
		}
		sortie.put(this.getInitial(), n);
	}

	private int descLenteBis(IEtatDFA<Character> p, int k) {
		IEtatDFA<Character> q, e, f;
		while (k < n && p.delta(y[k]) != null) {
			q = p.delta(y[k]);
			e = p; f = q;
			while (!e.equals(this.getInitial()) && ls.get(f) == null) {
				ls.put(f, ls.get(e).delta(y[k]));
				e = ls.get(e); f = ls.get(f);	
			}
			if (ls.get(f) == null) ls.put(f, this.getInitial());
			p = q; 
			k++;
		}
		if (p != null) fourche = p;
		return k;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(super.toString());
		sb.append(System.getProperty("line.separator") + ls.toString()); 
		
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(new ArbreSuffixesBis("ababbb"));
	}

}
