package univ.structures.ensembles;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class UnionFind<E> implements IUnionFind<E> {
	
	Map<E, E> parent;
	Map<E, Set<E>> sets;
	Set<E> elements;
	
	public UnionFind() {
		parent = new HashMap<E, E>();
		sets = new HashMap<E, Set<E>>();
		elements = new HashSet<E>();
	}
	
	public UnionFind(Set<E> set) {
		this();
		if (set == null) throw new IllegalArgumentException();
		
		for (E e : set) {
			creerEnsemble(e);
		}
	}
	
	public UnionFind(E[] tab) {
		this();
		if (tab == null) throw new IllegalArgumentException();
		
		for (E e : tab) {
			creerEnsemble(e);
		}
	}

	@Override
	public void creerEnsemble(E e) {
		parent.put(e, e);
		elements.add(e);
		Set<E> set = new HashSet<E>();
		set.add(e);
		sets.put(e, set);
	}

	@Override
	public void union(E x, E y) {
		if (!elements.contains(x))
			throw new IllegalArgumentException(x.toString());
		if (!elements.contains(y))
			throw new IllegalArgumentException(y.toString());
		if (!x.equals(y)) {
			E racX = racine(x);
			sets.get(racX).addAll(trouverEnsemble(y));
			E racY = racine(y);
			parent.put(racY, racX);
			sets.remove(racY);
		}
	}
	
	/**
	 * Recherche la racine
	 */
	private E racine(E e) {
		E pere = parent.get(e);
		if (!e.equals(pere)) 
			return racine (pere);
		return e;
	}

	@Override
	public Set<E> trouverEnsemble(E e) {
		if (e == null) throw new IllegalArgumentException();
		return sets.get(racine(e));
	}
	
	@Override
	public Set<Set<E>> partition() {
		return new HashSet<Set<E>>(sets.values());
	}

	@Override
	public String toString() {
		return partition().toString();
	}

	@Override
	public boolean equals(Object obj) {
		if ((obj != null) && (obj.getClass() == getClass())) {
			@SuppressWarnings("unchecked")
			UnionFind<E> uf = (UnionFind<E>) obj;
			return partition().equals(uf.partition());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return partition().hashCode();
	}
	
	

}
