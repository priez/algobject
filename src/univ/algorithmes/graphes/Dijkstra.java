package univ.algorithmes.graphes;

/**
 * <h2>L'algorithme de Dijkstra</h2>
 * 
 * <h3>Problème de la recherche d'un plus court chemin à partir d'un sommet <code>q</code></h3>
 * 
 * <h3>Graphe orienté pondéré G : (S, A) à poids strictement positifs ou nuls</h3>
 * 
 * <pre><code>
 * <h3>DIJKSTRA(G,w,s)</h3>
 * <ol>
 * 	<li> SOURCE-UNIQUE-INITIALISATION(G, s)
 * 	<li> E &larr; &empty;
 * 	<li> F &larr; S[G]
 *  <li> <u>tant que</u> F &ne; &empty; <u>faire</u>
 *  <ol><li> u &larr; EXTRAIRE-MIN(F)
 *  	<li> E &larr; E &cup; {u}
 *  	<li> <u>pour chaque</u> sommet v &isin; Adj[u] <u>faire</u>
 *  	<ol><li> RELACHER(u, v, w)</ol>
 *  	<li> <u>fin pour chaque</u></ol>
 *  <li> <u>fin tant que</u>
 * </ol>
 * </code></pre>
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
 * 
 * @author EliX
 *
 */
public final class Dijkstra {

}
