package univ.structures.ensembles;

import java.util.*;

/**
 * Structure de données Union-Find
 * cf : Cormen -> représentation d'ensemble disjoints 
 **/
public interface IUnionFind<E> {
	
	/**
	 * Créer le singleton \{e\} dans la structure
	 */
	public void creerEnsemble(E e);
	
	/**
	 * Effectue l'union des ensembles asssociés à x et y
	 */
	public void union(E x, E y);
	
	/**
	 * Trouve l'ensemble associé à l'élément <code>e</code>
	 */
	public Set<E> trouverEnsemble(E e);
	
	/**
	 * Retourne la partition des ensembles disjoints
	 * @return
	 */
	public Set<Set<E>> partition();
}