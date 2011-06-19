package univ.algorithmes.graphes.pcc;

import java.util.SortedSet;
import java.util.TreeSet;

import univ.structures.graphes.IAreteValue;
import univ.structures.graphes.ISommet;

/**
 * <h2>Algorithme de Dijkstra</h2>
 * 
 * L'algorithme de Dijkstra résout le problème de la recherche d'un plus court 
 * chemin à origine unique pour un graphe orienté pondéré où tous les arêtes 
 * ont une pondération positive ou nulle.
 * 
 * <pre>
 * source_unique_initialisation(s)
 * E &larr; &empty;
 * F &larr; S
 * <u>tant que</u> F &ne; &empty; <u>faire</u>
 *     u &larr; extraire_min(F)
 *     E &larr; E &cup; {u}
 *     <u>pour chaque</u> v &isin; Adj(u) <u>faire</u>
 *        relacher(u,v)
 * </pre>
 * 
 * Il faut en plus implanter une méthode d'extraction du minimum.
 * @author EliX
 *
 * @param <E>
 */
public abstract class Dijkstra<E> extends AbstractPCC<E> {
	
	@Override
	public void algorithme(ISommet<IAreteValue<E>> s) {
		SortedSet<ISommet<IAreteValue<E>>> E, F;
		ISommet<IAreteValue<E>> u;
		
		sourceUniqueInitialisation(s);
		E = new TreeSet<ISommet<IAreteValue<E>>>();
		F = new TreeSet<ISommet<IAreteValue<E>>>(G.getSommets());
		
		while (!F.isEmpty()) {
			u = extraireMin(F);
			E.add(u);
			for (IAreteValue<E> adj : u.getSortants())
				relacher(adj);
		}
	}
	
	abstract ISommet<IAreteValue<E>> extraireMin(SortedSet<ISommet<IAreteValue<E>>> f);

}
