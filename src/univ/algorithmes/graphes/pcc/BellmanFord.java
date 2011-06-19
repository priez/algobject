package univ.algorithmes.graphes.pcc;

import univ.structures.graphes.IAreteValue;
import univ.structures.graphes.ISommet;

/**
 * <h2>Algorithme  de Bellman-Ford</h2>
 * 
 * Cet algorithme résout le problème des plus courts chemins à origine unique.
 * A l'opposé, d'autre méthode comme Dijkstra, l'algorithme de Bellman-Ford résout
 * le problème dans le cadre d'un graphe orienté valué sans contrainte sur les valuations.
 * 
 * <pre>
 * <u>pour</u> i &larr; 1 <u>à</u> |S| <u>faire</u>
 *     <u>pour chaque</u> (u,v) &isin; A <u>faire</u>
 *         relacher(u,v)
 *     
 * </pre>
 * 
 * @author EliX
 *
 */
public abstract class BellmanFord<E> extends AbstractPCC<E> {
	
	/**
	 * La variable qui indique si le graphe ne contient aucun circuit.
	 * Après l'exécution de l'algorithme <code>retour == true</code> si
	 * et seulement si le graphe ne graphe ne contient pas de circuit
	 */
	protected boolean retour;
	
	@Override
	public void algorithme(ISommet<IAreteValue<E>> s) {
		sourceUniqueInitialisation(s);
		
		for (int i = 0; i < G.getSommets().size(); i++)
			for (IAreteValue<E> a : G.getPaires())
				relacher(a);
		
		for (IAreteValue<E> a : G.getPaires()) {
			if (d.get(a.getEntrant()) > d.get(a.getSortant()) + w(a)) { 
				retour = false;
				return;
		}	}
	
		retour = true;
	}
	
}