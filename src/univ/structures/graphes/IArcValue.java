package univ.structures.graphes;


/**
 * <h2>Interface d'un arc valué</h2>
 * @author EliX
 *
 * @param <E>
 */
public interface IArcValue<E> extends IPaire<IArcValue<E>>, IValuation<E> {
	
	/**
	 * @return le sommet u de l'arc courant {u,v}.
	 */
	ISommet<IArcValue<E>> getSommetA();
	
	/**
	 * @return le sommet v de l'arc courant {u,v}.
	 */
	ISommet<IArcValue<E>> getSommetB();
}
