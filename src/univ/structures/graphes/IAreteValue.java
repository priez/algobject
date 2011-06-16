package univ.structures.graphes;


/**
 * <h2>Interface d'arête valué</h2>
 * 
 * @author EliX
 *
 * @param <E>
 */
public interface IAreteValue<E> extends 
	IPaire<IAreteValue<E>>, 
	IOrientation<IAreteValue<E>>, 
	IValuation<E> {

}
