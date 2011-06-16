package univ.structures.graphes;

import java.util.Set;

public class ArcValue<E> implements IArcValue<E> {
	
	private IArc arc;
	private E value;
	
	@SuppressWarnings("unchecked")
	public ArcValue(ISommet<IArcValue<E>> a, ISommet<IArcValue<E>> b, E v) {
		ISommet<IArc> pseudoA = (ISommet<IArc>) ((Object) a);
		ISommet<IArc> pseudoB = (ISommet<IArc>) ((Object) b);
		arc = new Arc(pseudoA, pseudoB);
		value = v;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Set<ISommet<IArcValue<E>>> getElements() {
		Set set = arc.getElements();
		return set;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean contains(ISommet<IArcValue<E>> e) {
		ISommet<IArc> pseudo = (ISommet<IArc>) ((Object) e);
		return arc.contains(pseudo);
	}

	@Override
	public E getValeur() {
		return value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ISommet<IArcValue<E>> getSommetA() {
		return (ISommet<IArcValue<E>>) ((Object) arc.getSommetA());
	}

	@SuppressWarnings("unchecked")
	@Override
	public ISommet<IArcValue<E>> getSommetB() {
		return (ISommet<IArcValue<E>>) ((Object) arc.getSommetB());
	}

	
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj.getClass() == getClass()) {
			@SuppressWarnings("unchecked")
			ArcValue<E> a = (ArcValue<E>) obj;
			return a.arc.equals(arc) &&
				(a.value == null ? value == null : a.value.equals(value));
		}
		return false;
	}

	@Override
	public int hashCode() {
		return arc.hashCode() + (value != null ? value.hashCode() : -1);
	}

	@Override
	public String toString() {
		return "{" + getSommetA().toString() + ", " + getSommetB().toString() 
			+ ", " + (value != null ? value.toString() : "null") + "}";
	}
	
	

}
