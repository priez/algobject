package univ.structures.automates;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import univ.structures.collections.DefaultMap;
import univ.structures.collections.DefaultSortedMap;
import univ.structures.graphes.Graphe;
import univ.structures.graphes.ISommet;

public class Automate<A, E extends IEtat<A>> extends Graphe<ITransition<A>> implements IAutomate<A, E> {

	private SortedMap<E, Map<A, SortedSet<E>>> mapmapTrans;
	
	private Set<A> alphabet;
	
	private GenEtat generateur;
	
	private int ind;
	
	private E initial;
	
	private SortedSet<E> etatsFinaux;
	
	public Automate(GenEtat g, Set<A> alpha) {
		this(g);
		
		alphabet.addAll(alpha);
	}
	
	public Automate(GenEtat g) {
		super(ITransition.class);

		if (g == null) throw new IllegalArgumentException("Le générateur ne doit pas être nul.");
		
		generateur = g;
		ind = 0;
		initial = null;
		etatsFinaux = new TreeSet<E>();
		alphabet = new HashSet<A>();
		mapmapTrans = new DefaultSortedMap<E, Map<A,SortedSet<E>>>(
				new TreeMap<E, Map<A, SortedSet<E>>>(), 
				new DefaultMap<A, SortedSet<E>>(
					new HashMap<A, SortedSet<E>>(), 
					new TreeSet<E>())) ;
	}
	
	protected Set<E> getTransition(E s, A v) {
		return mapmapTrans.get(s).get(v);
	}

	@Override
	public void addSymbole(A a) {
		if (a == null) throw new IllegalArgumentException();
		alphabet.add(a);
		firePropertyChange(SYMB_AJOUTE, null, a);
	}

	@SuppressWarnings("deprecation")
	@Override
	public E createEtat() {
		E p = generateur.create(this, ind++);
		super.addSommet(p);
		return p;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addTransition(ITransition<A> trans) {
		if (generateur.deterministe() 
				&& mapmapTrans.get(trans.getSortant())
					.get(trans.getValeur()).size() > 0) 
			throw new IllegalArgumentException("Un DFA ne peut avoir plusieurs transition d'un état par un même symbole.");
		super.addPaire(trans);
		if (!mapmapTrans.containsKey(trans.getSortant())) {
			mapmapTrans.put(
				(E) trans.getSortant(), 
				new DefaultMap<A, SortedSet<E>>(
					new HashMap<A, SortedSet<E>>(), 
					new TreeSet<E>()));
		}
		Map<A, SortedSet<E>> map = mapmapTrans.get(trans.getSortant());
		if (!map.containsKey(trans.getValeur()))
			map.put(trans.getValeur(), new TreeSet<E>());
		
		map.get(trans.getValeur()).add((E) trans.getEntrant());
	}
	
	@Override
	public void setInitial(E e) {
		if (e == null) throw new IllegalArgumentException();
		if (!getEtats().contains(e)) throw new IllegalArgumentException("l'état n'appartient pas à l'automate");
		E old = initial;
		initial = e;
		firePropertyChange(INIT_AJOUTE, old, initial);
	}

	@Override
	public void setFinal(E e) {
		setFinal(e, true);
	}

	@Override
	public void setFinal(E e, boolean b) {
		if (e == null) throw new IllegalArgumentException();
		if (!getEtats().contains(e)) throw new IllegalArgumentException("l'état n'appartient pas à l'automate");
		
		if (b) {
			etatsFinaux.add(e);
			firePropertyChange(TERM_AJOUTE, null, e);
		} else {
			etatsFinaux.remove(e);
			firePropertyChange(TERM_ENLEVE, e, null);
		}
	}

	@Override
	public void removeEtat(E e) {
		if (e == null) throw new IllegalArgumentException();
		if (e.equals(initial)) initial = null;
		etatsFinaux.remove(e);
		super.removeSommet(e);
	}

	@Override
	public void removeTransition(ITransition<A> trans) {
		super.removePaire(trans);
		mapmapTrans.get(trans.getSortant()).get(trans.getValeur()).remove(trans.getEntrant());
	}

	@Override
	public Set<A> getAlphabet() {
		return Collections.unmodifiableSet(alphabet);
	}

	@Override
	public E getInitial() {
		return initial;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<E> getEtats() {
		return (Set<E>) super.getSommets();
	}

	@Override
	public Set<E> getEtatsFinaux() {
		return Collections.unmodifiableSet(etatsFinaux);
	}

	@Override
	public void addPaire(ITransition<A> p) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void removeSommet(ISommet<ITransition<A>> s) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void removePaire(ITransition<A> p) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ISommet<ITransition<A>> createSommet() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isValue() {
		return true;
	}

	@Override
	public boolean isOriente() {
		return true;
	}

	@Override
	public int hashCode() {
		return super.hashCode() 
			+ (initial == null ? -1 : initial.hashCode())
			+ etatsFinaux.hashCode();
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		String ln = System.getProperty("line.separator");
		sb.append("l'automate : " + ln);
		sb.append("\t" + "alphabet : " + alphabet.toString() + ln);
		sb.append("\t" + "Q : " + getEtats().toString() + ln);
		sb.append("\t" + "i : " + (getInitial() == null ? "null" : getInitial()) + ln);
		sb.append("\t" + "F : " + getEtatsFinaux().toString() + ln);
		sb.append("\t" + "delta : " + getPaires().toString());
		return sb.toString();
	}
	
	
}
