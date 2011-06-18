package univ.algorithmes.graphes;

import java.util.HashSet;
import java.util.Set;

import univ.structures.graphes.IPaire;
import univ.structures.graphes.ISommet;

/**
 * <h2>Algorithme de parcours en profondeur</h2>
 * <h3>Depth First Search</h3>
 * 
 * 
 * Nécessite une initialisation de l'ensemble des sommets marqués. (<code>init()</code>)
 * 
 * <pre>
 * <b>DFS(graphe G, sommet s) :</b>
 * marquer (s)
 * // traitement de s
 * afficher (s)
 * <u>pour chaque</u> fils <u>de</u> s <u>faire</u>
 *     <u>si</u> <u>non</u> est_marqué (fils) <u>alors</u>
 *         DFS (G, fils)
 *     <u>fin_si</u>
 * <u>fin_pour_chaque</u>
 * </pre>
 * 
 * @author EliX
 *
 * @param <E>
 */
public abstract class DFS<E extends IPaire<E>> implements SearchGraphe<E> {

	
	private Set<ISommet<E>> marque;
	
	public void init() {
		marque = new HashSet<ISommet<E>>();
	}
	
	@Override
	public void algorithme(ISommet<E> s) {
		marque.add(s);
		traitement(s);
		
		for (ISommet<E> fils : s.getAdjacents())
			if (!marque.contains(fils))
				algorithme(fils);
	}

}
