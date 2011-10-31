package univ.m2.bioinfo;

import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

import univ.structures.automates.Automate;
import univ.structures.automates.IEtatDFA;
import univ.structures.automates.Transition;

/**
 * <h2>Algorithme de construction d'arbre des suffixes avec liens suffixes</h2>
 * 
 * Comparé à la classe <code>ArbreSuffixes</code>, on ajoute la notion de <i>liens
 * suffixes</i>.<br/>
 * 
 * On notera <i>ls</i> la fonction de &Sigma; x &Sigma;* &rarr; &Sigma;* tel que 
 * <i>ls(as) = s</i>.
 * <br/><br/>
 * p168
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
public class ArbreSuffixesBis extends Automate<Character, IEtatDFA<Character>> {
	
	String y;
	int n;
	
	private SortedMap<IEtatDFA<Character>, IEtatDFA<Character>> ls;
	private SortedMap<IEtatDFA<Character>, Integer> sortie;
	
	private IEtatDFA<Character> fourche;

	public ArbreSuffixesBis(String y, int n) {
		super(GenEtat.DFA);
		this.createEtat().setInitial();
		
		this.y = y;
		this.n = n;
		sortie = new TreeMap<IEtatDFA<Character>, Integer>();
		ls = new TreeMap<IEtatDFA<Character>, IEtatDFA<Character>>();
		
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
				char yj = y.charAt(j);
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

	private int descLenteBis(IEtatDFA<Character> p, int k) {
		IEtatDFA<Character> q, e, f;
		char yk;
		while (k < n && p.delta(y.charAt(k)) != null) {
			yk = y.charAt(k);
			q = p.delta(yk);
			e = p; f = q;
			while (!e.equals(this.getInitial()) && ls.get(f) == null) {
				ls.put(f, ls.get(e).delta(yk));
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
		String ln = System.getProperty("line.separator");
		IEtatDFA<Character> p;
		
		sb.append("\tliens suffixes : " + ln);
		Iterator<IEtatDFA<Character>> it = ls.keySet().iterator();
		while (it.hasNext()) {
			p = it.next();
			sb.append("\t\t" + p + " => " + ls.get(p) + ln);
		} 
		
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(new ArbreSuffixesBis("ababbb", 6));
	}

}
