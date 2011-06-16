package univ.structures.graphes;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import univ.structures.collections.SetTools;

public class Sommet<E extends IPaire<E>> implements ISommet<E> {
	
	private int id;
	
	@SuppressWarnings("rawtypes")
	private IGraphe model;
	
	protected Sommet(int id, @SuppressWarnings("rawtypes") IGraphe graphe) {
		this.id = id;
		this.model = graphe;
	}
	
	@SuppressWarnings("rawtypes")
	protected IGraphe getModel() {
		return model;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public int degre() {
		return getPairesIncidentes().size();
	}

	@Override
	public int degreEntrant() {
		return getEntrants().size();
	}

	@Override
	public int degreSortant() {
		return getSortants().size();
	}

	@Override
	public boolean isIsole() {
		return degre() == 0;
	}

	@Override
	public boolean isAdjacent(ISommet<E> s) {
		if (s == null) throw new IllegalArgumentException();
		return getAdjacents().contains(s);
	}

	@Override
	public Set<ISommet<E>> getAdjacents() {
		Set<ISommet<E>> res = new TreeSet<ISommet<E>>();
		for (E p : getSortants()) {
			Iterator<ISommet<E>> it = p.getElements().iterator();
			ISommet<E> s = it.next();
			if (s.equals(this) && it.hasNext())
				res.add(it.next());
			else res.add(s);
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<E> getEntrants() {
		return Collections.unmodifiableSet(((Graphe<E>) model).getEntrants(this));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<E> getSortants() {
		return Collections.unmodifiableSet(((Graphe<E>) model).getSortants(this));
	}

	@Override
	public Set<E> getPairesIncidentes() {
		return Collections.unmodifiableSet(
				SetTools.union(getSortants(), getEntrants()));
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj.getClass() == getClass()) {
			@SuppressWarnings("unchecked")
			Sommet<E> s = (Sommet<E>) obj;
			return s.getId() == getId() && model == s.model;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	public String toString() {
		return "-" + getId() + "-";
	}

	@Override
	public int compareTo(ISommet<E> o) {
		if (o.getId() == getId())
			return 0;
		else return getId() > o.getId() ? 1 : -1;
	}

}
