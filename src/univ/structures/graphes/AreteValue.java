package univ.structures.graphes;

import java.util.Set;

public class AreteValue<E> implements IAreteValue<E> {
	
	private IArete arete;
	
	private E value;
	
	@SuppressWarnings("unchecked")
	public AreteValue(ISommet<IAreteValue<E>> sort, ISommet<IAreteValue<E>> entr, E v) {
		ISommet<IArete> pseudoA = (ISommet<IArete>) ((Object) sort);
		ISommet<IArete> pseudoB = (ISommet<IArete>) ((Object) entr);
		arete = new Arete(pseudoA, pseudoB);
		value = v;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Set<ISommet<IAreteValue<E>>> getElements() {
		Set set = arete.getElements();
		return set;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean contains(ISommet<IAreteValue<E>> e) {
		ISommet<IArete> pseudo = (ISommet<IArete>) ((Object) e);
		return arete.contains(pseudo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ISommet<IAreteValue<E>> getSortant() {
		return (ISommet<IAreteValue<E>>) ((Object) arete.getSortant());
	}

	@SuppressWarnings("unchecked")
	@Override
	public ISommet<IAreteValue<E>> getEntrant() {
		return (ISommet<IAreteValue<E>>) ((Object) arete.getEntrant());
	}

	@Override
	public E getValeur() {
		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj.getClass() == getClass()) {
			@SuppressWarnings("unchecked")
			AreteValue<E> a = (AreteValue<E>) obj;
			return a.arete.equals(arete) && 
				(a.value == null ? value == null : a.value.equals(value));
		}
		return false;
	}

	@Override
	public int hashCode() {
		return arete.hashCode() - (value == null ? 1 : value.hashCode());
	}

	@Override
	public String toString() {
		return "(" + arete.getSortant().toString() + ", " + arete.getEntrant() 
			+ ", " + (value == null ? "null" : value.toString()) + ")";
	}
	
	

}
