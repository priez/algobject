package univ.structures.graphes;

import java.util.Set;
import java.util.TreeSet;

public class Arete extends AbstractPaire<IArete> implements IArete {
	
	private ISommet<IArete> e,s;
	
	public Arete(ISommet<IArete> sort, ISommet<IArete> entr) {
		super(sort, entr);
		
		e = entr;
		s = sort;
	}
	
	@Override
	public Set<ISommet<IArete>> getElements() {
		Set<ISommet<IArete>> res = new TreeSet<ISommet<IArete>>();
		res.add(e);
		res.add(s);
		return res;
	}

	@Override
	public boolean contains(ISommet<IArete> s) {
		return e.equals(s) || s.equals(s);
	}

	@Override
	public ISommet<IArete> getSortant() {
		return s;
	}

	@Override
	public ISommet<IArete> getEntrant() {
		return e;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj.getClass() == getClass()) {
			Arete ar = (Arete) obj;
			return e.equals(ar.e) && s.equals(ar.s);
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return e.hashCode() - s.hashCode();
	}

	@Override
	public String toString() {
		return "(" + s.toString() + ", " + e.toString() + ")";
	}
	
	

}
