package univ.structures.collections;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class SetTools {

	public static<Q> Set<Q> intersection(Set<Q> s1, Set<Q> s2) {
		Set<Q> set = new HashSet<Q>(s1);
		set.removeAll(s2);
		return set;
	}
	
	public static<Q> SortedSet<Q> intersection(SortedSet<Q> s1, SortedSet<Q> s2) {
		SortedSet<Q> set = new TreeSet<Q>(s1);
		set.removeAll(s2);
		return set;
	}

	public static<Q> Set<Q> union(Set<Q> s1, Set<Q> s2) {
		Set<Q> set = new HashSet<Q>(s1);
		set.addAll(s2);
		return set;
	}

	public static<Q> SortedSet<Q> union(SortedSet<Q> s1, SortedSet<Q> s2) {
		SortedSet<Q> set = new TreeSet<Q>(s1);
		set.addAll(s2);
		return set;
	}
	
	/**
	 * Créer un ensemble à partir des éléments passé en paramètre
	 * @param <Q> le type des éléments
	 * @param elems la liste des éléments
	 * @return l'ensemble associé aux éléments passés en paramètre.
	 */
	public static<Q> Set<Q> setFactory(Q...elems) {
		Set<Q> set = new HashSet<Q>();
		for (Q e : elems)
			set.add(e);
		return set;
	}
}
