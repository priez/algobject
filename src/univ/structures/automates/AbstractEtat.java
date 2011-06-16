package univ.structures.automates;

import java.util.Set;

import univ.structures.graphes.Sommet;

public abstract class AbstractEtat<A> extends Sommet<ITransition<A>> implements IEtat<A> {
	
	private IAutomate<A, IEtat<A>> model;
	
	protected AbstractEtat(IAutomate<A, IEtat<A>> auto, int indice) {
		super(indice, auto);
		model = auto;
	}
	
	@Override
	protected IAutomate<A, IEtat<A>> getModel() {
		return model;
	}
	
	@Override
	public boolean isInitial() {
		return this.equals(getModel().getInitial());
	}

	@Override
	public boolean isFinal() {
		return getModel().getEtatsFinaux().contains(this);
	}

	@Override
	public Set<ITransition<A>> getTransition() {
		return super.getSortants();
	}
	
	@Override
	public void setInitial() {
		getModel().setInitial(this);
	}
	
	@Override
	public void setFinal() {
		setFinal(true);
	}
	
	@Override
	public void setFinal(boolean b) {
		getModel().setFinal(this, b);
	}

}
