package univ.structures.graphes;

import java.util.HashSet;
import java.util.Set;

public class Arc extends AbstractPaire<IArc> implements IArc {
	
	private ISommet<IArc> a,b;
	
	public Arc(ISommet<IArc> a, ISommet<IArc> b) {
		super(a, b);
		
		this.a = a;
		this.b = b;
	}


	@Override
	public ISommet<IArc> getSommetA() {
		return a;
	}

	@Override
	public ISommet<IArc> getSommetB() {
		return b;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj.getClass() == getClass()) {
			Arc arc = (Arc) obj;
			return (a.equals(arc.a) && b.equals(arc.b))
				|| (a.equals(arc.b) && b.equals(arc.a));
		}
		return false;
	}

	@Override
	public int hashCode() {
		return a.hashCode() + b.hashCode();
	}

	@Override
	public String toString() {
		return "{" + a.toString() + ", " +  b.toString() + "}";
	}


	@Override
	public Set<ISommet<IArc>> getElements() {
		Set<ISommet<IArc>> set = new HashSet<ISommet<IArc>>();
		set.add(a);
		set.add(b);
		return set;
	}

	@Override
	public boolean contains(ISommet<IArc> e) {
		return a.equals(e) || b.equals(e);
	}

}
