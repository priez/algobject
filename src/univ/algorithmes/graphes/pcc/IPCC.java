package univ.algorithmes.graphes.pcc;

import univ.structures.graphes.IAreteValue;
import univ.structures.graphes.ISommet;

/**
 * <h2>Interface d'algorithme de recherche de plus courts chemins à origine unique</h2>
 * 
 * Cette interface a pour objectif de définir une base commune à toutes les implantations 
 * d'algorithmes de recherches des plus court chemin : Dijkstra, Bellman-Ford...<br><br>
 * 
 * Un <i>problème de plus courts chemin</i> va intégrer une fonction de pondération 
 * <i>w : A &rarr; <b>R</b></i> qui fait correspondre un poids à chaque arc (fonction 
 * de sa pondération).
 * 
 * <hr>
 * 
 * Définitions issues de l'<u><i>Introduction à l'algorithmique</i></u>
 * de Thomas H. <span style="font-variant: small-caps;">Cormen</span>, 
 * Charles E. <span style="font-variant: small-caps;">Leiserson</span>, 
 * Ronald L. <span style="font-variant: small-caps;">Rivest</span> et
 * Clifford <span style="font-variant: small-caps;">Stein</span>
 * 
 * <hr>
 * @author EliX
 *
 * @param <E> le type des valuations du graphe.
 */
public interface IPCC<E> {
	
	/**
	 * Fonction de pondération.<br>
	 * 
	 * <i>w : A &rarr; <b>R</b></i>
	 * 
	 * @param a un arc ou arête
	 * @return la pondération
	 */
	double w(IAreteValue<E> a);
	
	/**
	 * Initialisation des estimations et des prédécesseurs.
	 * <pre>
	 * <u>pour chaque</u> v &isin; S <u>faire</u>
	 *     d[v] &larr; &infin;
	 *     &pi; &larr; NIL
	 * d[s] &larr; 0
	 * </pre>
	 * @param s le sommet origine
	 */
	void sourceUniqueInitialisation(ISommet<IAreteValue<E>> s);
	
	/**
	 * Fonction de relâchement.
	 * <pre>
	 * <u>si</u> d[v] > d[u] + w(u,v) <u>alors</u>
	 *     d[v] &larr; d[u] + w(u,v)
	 *     &pi;[v] &larr; u
	 * </pre>
	 * @param a l'arête valué
	 */
	void relacher(IAreteValue<E> a);

	/**
	 * Algorithme de recherche d'un plus court chemin.
	 * @param s l'origine unique
	 */
	void algorithme(ISommet<IAreteValue<E>> s);

}
