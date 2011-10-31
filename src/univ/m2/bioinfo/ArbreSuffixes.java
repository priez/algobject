package univ.m2.bioinfo;

import java.util.SortedMap;
import java.util.TreeMap;

import univ.structures.automates.Automate;
import univ.structures.automates.IEtatDFA;
import univ.structures.automates.Transition;

/**
 * <h2>Algorithme de construction d'arbre de suffixes</h2>
 * 
 * L'algorithme suivant construit un automate (trie) déterministe
 * reconnaissant l'ensemble des suffixes d'un mot <code>y</code>.
 * 
 * Cette classe implante les algorithmes p 166 du livre cité ci-après.
 * 
 * <hr>
 * 
 * <u><i>Algorithmique du texte</i></u>
 * de Maxime <span style="font-variant: small-caps;">Crochemore</span>, 
 * Christophe <span style="font-variant: small-caps;">Hancart</span> et 
 * Thierry <span style="font-variant: small-caps;">Lecroq</span>
 * 
 * <hr>
 * @author EliX
 *
 */
public class ArbreSuffixes extends Automate<Character, IEtatDFA<Character>> {
	
	String y;
	int n;
	
	private SortedMap<IEtatDFA<Character>, Integer> sortie;
	private IEtatDFA<Character> fourche;

	public ArbreSuffixes(String y, int n) {
		super(GenEtat.DFA);
		this.createEtat().setInitial();
		
		this.y = y;
		this.n = n;
		sortie = new TreeMap<IEtatDFA<Character>, Integer>();
		
		algo();
	}
	
	private void algo() {
		int k;
		IEtatDFA<Character> p, q;
		char yj;
		
		this.fourche = null;
		for (int i = 0; i < n; i++) {
			k = descLente(this.getInitial(), i);
			p = fourche;
			for (int j = k; j < n; j++) {
				q = this.createEtat();
				yj = y.charAt(j);
				this.addSymbole(yj);
				this.addTransition(new Transition<Character>(p, q, yj));
				p = q;
			}
			sortie.put(p, i);
			p.setFinal();
		}
		sortie.put(this.getInitial(), n);
		this.getInitial().setFinal();
	}
	
	private int descLente(IEtatDFA<Character> p, int k) {
		while (k < n && p.delta(y.charAt(k)) != null) {
			p = p.delta(y.charAt(k));
			k++;
		}
		if (p != null) this.fourche = p;
		return k;
	}
	
	public static void main(String[] args) {
		System.out.println(new ArbreSuffixes("ababbb", 6));
	}

}
