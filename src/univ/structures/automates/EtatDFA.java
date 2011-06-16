package univ.structures.automates;

import java.util.Set;

public class EtatDFA<A> extends AbstractEtat<A> implements IEtatDFA<A> {

	protected EtatDFA(IAutomate<A, IEtat<A>> auto, int indice) {
		super(auto, indice);
	}

	@Override
	public IEtatDFA<A> delta(A a) {
		Set<IEtat<A>> set = ((Automate<A, IEtat<A>>) getModel()).getTransition(this, a);
		if (set.isEmpty()) return null;
		else return (IEtatDFA<A>) set.iterator().next();
	}

}
