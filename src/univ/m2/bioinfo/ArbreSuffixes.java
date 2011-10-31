package univ.m2.bioinfo;

import java.util.HashMap;
import java.util.Map;

import univ.structures.automates.Automate;
import univ.structures.automates.IEtatDFA;
import univ.structures.automates.Transition;

public class ArbreSuffixes extends Automate<Character, IEtatDFA<Character>> {
	
	char[] y;
	Map<IEtatDFA<Character>, Integer> sortie;
	int n;
	IEtatDFA<Character> fourche;

	public ArbreSuffixes(String y) {
		super(GenEtat.DFA);
		this.setInitial(this.createEtat());
		
		this.y = y.toCharArray();
		this.n = y.length();
		sortie = new HashMap<IEtatDFA<Character>, Integer>();
		
		algo();
	}
	
	private void algo() {
		int k;
		IEtatDFA<Character> p, q;
		this.fourche = null;
		for (int i = 0; i < n; i++) {
			k = descLente(this.getInitial(), i);
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
	
	private int descLente(IEtatDFA<Character> p, int k) {
		while (k < n && p.delta(y[k]) != null) {
			p = p.delta(y[k]);
			k++;
		}
		if (p != null) this.fourche = p;
		return k;
	}
	
	public static void main(String[] args) {
		System.out.println(new ArbreSuffixes("ababbb"));
	}

}
