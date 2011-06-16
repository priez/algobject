package univ.structures.graphes;


import java.util.Set;

/**
 * <h2>Interface d'un sommets</h2>
 * 
 * Le sommet <code>v</code> est <i>adjacent</i> au sommet
 * <code>u</code> si <code>(u,v)</code> est une arête du graphe.
 * Dans le cadre d'un graphe non-orienté, la relation d'adjacence
 * est symétrique.<br><br>
 * 
 * On dit que l'arc/arête <code>(u,v)</code> est <i>incidente</i> 
 * pour les sommets <code>u</code> et <code>v</code>.
 * Dans le cadre d'un graphe orienté, on parle d'arc <code>(u,v)</code>
 * <i>incident extérieur</i> à <code>u</code> (ou <i>sortant</i> de 
 * <code>u</code>) et <i>intérieur</i> à <code>v</code> (ou <i>entrant</i>
 * de <code>v</code>).<br><br>
 * 
 * Le <i>degré</i> d'un sommet dans un graphe non-orienté est 
 * le nombre d'arête qui lui sont <i>incident</i>.<br>
 * 
 * Le <i>degré</i> d'un sommet dans un graphe orienté est la
 * somme du <i>degré sortant</i> et du <i>degré entrant</i>.
 * Le <i>degré sortant</i> d'un sommet est le nombre arcs qui
 * en partent et le <i>degré entrant</i> est le nombre arcs
 * qui y arrivent.
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
 * @param <E> type des éléments stockés dans les sommets.
 * 
 * @inv 
 * <ul> <li>Soit <code>s'</code> un sommet quelconque et <code>s</code>
 * 			le sommet courant.<br>
 * 			s.equals(s') <b>si</b> leur graphe associé est identique 
 * 			<b>et</b> s' != null <b>et</b> s.getId() == s'.getId()
 * 		<li>Dans le cas d'un graphe non-orienté :
 * 		<ul>
 * 		<li><code>degre() == degreEntrant() == degreSortant()</code>.
 * 		<li><code>getAdjacents().equals(SetTools.union(getEntrants(),getSortants()))</code>
 * 		</ul>
 * 		<li>Dans le cas d'un graphe orienté :
 * 		<ul>
 * 		<li><code>degre() == degreEntrant() + degreSortant()</code>.
 * 		<li><code>getAdjacents().equals(getSortants())</code>
 * 		<li>
 * 		</ul>
 * </ul>
 * 
 * @exemple
 * Soit <code>s</code> le sommet courant pour un graphe orienté.<br>
 * 	<ul>
 * 	<li><code>s -> a; s -> b; s -> c</code> les arêtes sortantes de <code>s</code>.
 * 	<li><code>d -> s; e -> s; f -> s; g -> s</code> les arêtes entrantes en <code>s</code>.
 *	</ul><br>
 * Les différentes méthodes donnent les résultats suivants :
 * 	<ul>
 * 	<li><code>degre() == 7</code>.
 * 	<li><code>degreEntrant() == 4</code>.
 * 	<li><code>degreSortant() == 3</code>.
 * 	<li><code>isIsole() == false</code>.
 * 	<li><code>isAdjacent(a) == true</code>.
 *  <li><code>isAdjacent(d) == false</code>.
 *  <li><code>getEntrants().equals([d, e, f, g])</code>
 *  <li><code>getSortants().equals([a, b, c])</code>
 *  <li><code>getPairesIncidentes().equals([s -> a; s -> b; s -> c; d -> s; e -> s; f -> s; g -> s])</code>
 * 	</ul> 
 * 
 */
public interface ISommet<E extends IPaire<E>> extends Comparable<ISommet<E>> {
	
	/**
	 * @return l'élément associé au noeud.
	 */
	int getId();
	
	/**
	 * @return le degré du sommet.
	 */
	int degre();
	
	/**
	 * @return le degré entrant du sommet. 
	 */
	int degreEntrant();
	
	/**
	 * @return le degré sortant du sommet.
	 */
	int degreSortant();
	
	/**
	 * Indique si le sommet est isolé
	 * @equiv degree() == 0
	 * @return <code>true</true> <b>si</b> degree() == 0, <code>false</code> <b>sinon</b>.
	 */
	boolean isIsole();
	
	/**
	 * Indique si le sommet <code>s</code> est adjacent
	 * au sommet courant.
	 * @param s le sommet qu'on teste.
	 * @pre s != null
	 * @return <code>true</code> si <code>getAdjacents().contains(s)</code>;
	 * 		<code>false</code> sinon.
	 */

	boolean isAdjacent(ISommet<E> s);
	
	/**
	 * @return l'ensemble des sommets adjacents au sommet courant. 
	 */
	Set<ISommet<E>> getAdjacents();
	
	/**
	 * @return l'ensemble des arêtes entrantes.
	 */
	Set<E> getEntrants();
	
	/**
	 * @return l'ensemble des arêtes sortantes.
	 */
	Set<E> getSortants();
	
	/**
	 * @return l'ensemble des arcs/arêtes incidentes.
	 */
	Set<E> getPairesIncidentes();

}
