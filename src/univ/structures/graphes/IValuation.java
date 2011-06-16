package univ.structures.graphes;


/**
 * 
 * @author EliX
 *
 * Interface de valuation d'éléments
 *
 * @param <E> type de la valeur
 */
public interface IValuation<E> {
	
	/**
	 * Retourne la valeur associé à l'élément.
	 */
	E getValeur();

}
