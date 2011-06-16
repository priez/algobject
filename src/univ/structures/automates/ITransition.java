package univ.structures.automates;

import univ.structures.graphes.IOrientation;
import univ.structures.graphes.IPaire;
import univ.structures.graphes.IValuation;

public interface ITransition<A> extends 
	IPaire<ITransition<A>>, 
	IOrientation<ITransition<A>>, 
	IValuation<A> {

	
	boolean contains(IEtat<ITransition<A>> e);

}
