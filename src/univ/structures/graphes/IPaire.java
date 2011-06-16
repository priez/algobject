package univ.structures.graphes;


import java.util.Set;

/**
 * 
 * <h2>Interface d'une paire</h2>
 * 
 * Cette interface de donner une hiérarchie 
 * d'héritage aux <i>arcs</i> et <i>arêtes</i>.
 * 
 * @inv 
 * 	<ul>
 * 		<li> getElement().size() == 2
 * 		<li> <b>for</b> a in getElement() <b>:</b> a != null
 * </ul>
 * 
 * @author EliX
 *
 * @param <E> type des noeuds.
 */
public interface IPaire<E extends IPaire<E>> {
	
	/**
	 * @return la paire dans un tableau
	 */
	Set<ISommet<E>> getElements();
	
	/**
	 * Indique si l'élément est contenu
	 * dans la paire.
	 * 
	 * @param e un noeud
	 * @pre e != null
	 * @return <code>true</code> si <code>e</code> est un élément 
	 * 		de la paire, <code>false</code> sinon. 
	 */
	boolean contains(ISommet<E> e);
	
}
