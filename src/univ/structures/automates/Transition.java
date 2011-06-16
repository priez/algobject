package univ.structures.automates;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import univ.structures.graphes.ISommet;

public class Transition<A> implements ITransition<A> {
	
	private IEtat<A> s,e;
	
	private A v;
	
	public Transition(IEtat<A> sortant, IEtat<A> entrant, A value) {
		if (sortant == null || entrant == null || value == null) throw new IllegalArgumentException();
		if (!((AbstractEtat<A>) sortant).getModel().equals(((AbstractEtat<A>) entrant).getModel()))
			throw new IllegalArgumentException("les états ne sont pas attachés au même automate");
		if (!((AbstractEtat<A>) sortant).getModel().getAlphabet().contains(value))
			throw new IllegalArgumentException("la valeur n'appartient pas à l'alphabet de l'automate");
		
		s = sortant;
		e = entrant;
		v = value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<ISommet<ITransition<A>>> getElements() {
		Set<ISommet<ITransition<A>>> set = new HashSet<ISommet<ITransition<A>>>();
		Collections.addAll(set, s, e);
		return set;
	}

	@Override
	public boolean contains(ISommet<ITransition<A>> p) {
		return e.equals(p) || s.equals(p);
	}

	@Override
	public ISommet<ITransition<A>> getSortant() {
		return s;
	}

	@Override
	public ISommet<ITransition<A>> getEntrant() {
		return e;
	}

	@Override
	public A getValeur() {
		return v;
	}

	@Override
	public boolean contains(IEtat<ITransition<A>> p) {
		return e.equals(p) || s.equals(p);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj.getClass() == getClass()) {
			@SuppressWarnings("unchecked")
			Transition<A> t = (Transition<A>) obj;
			return t.e.equals(e) 
				&& t.s.equals(s)
				&& t.v.equals(v);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return e.hashCode() + s.hashCode() - v.hashCode();
	}

	@Override
	public String toString() {
		return "(" + s.toString() + ", " + v.toString() + ", " + e.toString() + ")";
	}

}
