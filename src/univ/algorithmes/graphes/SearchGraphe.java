package univ.algorithmes.graphes;

import univ.structures.graphes.IPaire;
import univ.structures.graphes.ISommet;

/**
 * <h2>Interface d'algorithme de recherche dans un graphe</h2>
 * 
 * @author EliX
 *
 */
public interface SearchGraphe<E extends IPaire<E>> {

	/**
	 * Méthode de traitement d'un sommet.
	 * @exemple Afficher le sommet
	 * @param n le sommet à traiter
	 */
	void traitement(ISommet<E> n);
	
	/**
	 * Méthode de l'algorithme de parcours d'un graphe.
	 * @ex parcours en largeur, profondeur...
	 * 
	 * @param root le sommet choisit en racine
	 */
	public void algorithme(ISommet<E> root);
}
