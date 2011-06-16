package univ.structures.automates;

import java.util.Set;

public class EtatNFA<A> extends AbstractEtat<A> implements IEtatNFA<A> {

	protected EtatNFA(IAutomate<A, IEtat<A>> auto, int indice) {
		super(auto, indice);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<IEtatNFA<A>> delta(A a) {
		return (Set<IEtatNFA<A>>) ((Object) ((Automate<A, IEtat<A>>) getModel()).getTransition(this, a));
	}

}
