package univ.structures.collections;

import java.util.Comparator;
import java.util.SortedMap;

public class DefaultSortedMap<K, V> extends DefaultMap<K, V> implements SortedMap<K,V> {

	public DefaultSortedMap(SortedMap<K, V> gestionnaire, V valeurDef) {
		super(gestionnaire, valeurDef);
	}

	@Override
	public Comparator<? super K> comparator() {
		return ((SortedMap<K,V>) super.getGestionnaire()).comparator();
	}

	@Override
	public K firstKey() {
		return ((SortedMap<K,V>) super.getGestionnaire()).firstKey();
	}

	@Override
	public SortedMap<K, V> headMap(K toKey) {
		return ((SortedMap<K,V>) super.getGestionnaire()).headMap(toKey);
	}

	@Override
	public K lastKey() {
		return ((SortedMap<K,V>) super.getGestionnaire()).lastKey();
	}

	@Override
	public SortedMap<K, V> subMap(K fromKey, K toKey) {
		return ((SortedMap<K,V>) super.getGestionnaire()).subMap(fromKey, toKey);
	}

	@Override
	public SortedMap<K, V> tailMap(K fromKey) {
		return ((SortedMap<K,V>) super.getGestionnaire()).tailMap(fromKey);
	}

}
