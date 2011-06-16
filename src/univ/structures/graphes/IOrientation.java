package univ.structures.graphes;


/**
 * <h2>Interface d'orientation</h2>
 * 
 * Permet l'implantation d'arêtes.
 * Pour la paire <code>[a,b]</code>, l'arête <code>(a,b)</code>
 * impose l'ordre <code>a</code> puis <code>b</code>.
 * 
 * @author EliX
 *
 */
public interface IOrientation<E extends IPaire<E>> {
	
	/**
	 * Soit l'arête courante (a,b)
	 * @return l'élément <code>a</code> de l'arête
	 */
	ISommet<E> getSortant();
	
	/**
	 * Soit l'arête courante (a,b)
	 * @return l'élément <code>b</code> de l'arête
	 */
	ISommet<E> getEntrant();

}
