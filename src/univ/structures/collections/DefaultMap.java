package univ.structures.collections;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author EliX
 * 
 * Map avec valeur par défaut.
 * 
 * Si la clé K n'existe pas alors la valeur
 * par défaut est retournée sinon même action
 * qu'un map classique.
 */
public class DefaultMap<K, V> implements Map<K, V> {
	
	private Map<K, V> map;
	
	private V v;
	
	public DefaultMap(Map<K, V> gestionnaire, V valeurDef) {
		map = gestionnaire;
		v = valeurDef;
	}
	
	protected Map<K, V> getGestionnaire() {
		return map;
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public boolean containsKey(Object arg) {
		return map.containsKey(arg);
	}

	@Override
	public boolean containsValue(Object arg) {
		return map.containsValue(arg);
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return map.entrySet();
	}

	@Override
	public V get(Object arg) {
		if (map.containsKey(arg))
			return map.get(arg);
		else return v;
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public Set<K> keySet() {
		return map.keySet();
	}

	@Override
	public V put(K arg0, V arg1) {
		return map.put(arg0, arg1);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> arg0) {
		map.putAll(arg0);
	}

	@Override
	public V remove(Object arg0) {
		return map.remove(arg0);
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public Collection<V> values() {
		Set<V> coll = new HashSet<V>(map.values());
		coll.add(v);
		return coll;
	}

}
