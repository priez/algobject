package univ.structures.graphes;

@SuppressWarnings("rawtypes")
public abstract class AbstractPaire<E extends IPaire<E>> implements IPaire<E> {

	protected AbstractPaire(ISommet a, ISommet b) {
		if (a == null || b == null) 
			throw new IllegalArgumentException("les arg ne doivent pas être nuls");
		
		if (((Sommet) a).getModel() != ((Sommet) b).getModel())
			throw new IllegalArgumentException(
				"ces sommets n'ont pas le même graphe de référence.");
	}

}
