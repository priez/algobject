package univ.algorithmes.graphes.pcc;

import java.util.SortedMap;
import java.util.TreeMap;

import univ.structures.graphes.IAreteValue;
import univ.structures.graphes.IGraphe;
import univ.structures.graphes.ISommet;

/**
 * <h2>Implantation partiel des algorithme de recherche de plus court chemin à origine unique</h2>
 * 
 * @author EliX
 *
 */
public abstract class AbstractPCC<E> implements IPCC<E> {
	
	protected SortedMap<ISommet<IAreteValue<E>>, Double> d;
	protected SortedMap<ISommet<IAreteValue<E>>, ISommet<IAreteValue<E>>> pi;
	
	protected IGraphe<IAreteValue<E>> G;

	@Override
	public void sourceUniqueInitialisation(ISommet<IAreteValue<E>> s) {
		d = new TreeMap<ISommet<IAreteValue<E>>, Double>();
		pi = new TreeMap<ISommet<IAreteValue<E>>, ISommet<IAreteValue<E>>>();
		
		for (ISommet<IAreteValue<E>> v : G.getSommets()) {
			d.put(v, Double.POSITIVE_INFINITY);
			pi.put(v, null);
		}
		d.put(s, 0.0);
	}

	@Override
	public void relacher(IAreteValue<E> a) {
		// a = (u,v)
		if (d.get(a.getEntrant()) > d.get(a.getSortant()) + w(a)) {
			d.put(a.getEntrant(), d.get(a.getSortant()) + w(a));
			pi.put(a.getEntrant(), a.getSortant());
		}
	}
	
	

}
