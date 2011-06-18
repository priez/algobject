package univ.algorithmes.graphes;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import univ.structures.graphes.IPaire;
import univ.structures.graphes.ISommet;

/**
 * <h2>Algorithme de parcours en largeur</h2>
 * <h3>Breadth First Search</h3>
 * 
 * <h3>Algorithme :</h3>
 * <pre>
 * <b>BFS(graphe G, sommet s) :</b>
 * f = file_vide ()
 * marquer (s)
 * enfiler (f, s)
 * <u>tant que</u> <u>non</u> est_file_vide (f) <u>faire</u>
 *    x = défiler (f)
 *    // traitement sur x
 *    afficher (x)
 *    <u>pour chaque</u> fils <u>de</u> x <u>faire</u>
 *        <u>si</u> <u>non</u> est_marqué (fils) <u>alors</u>
 *           marquer (fils)
 *           enfiler (f, fils)
 *        <u>fin_si</u>
 *    <u>fin_pour_chaque</u>
 * <u>fin_tant_que</u>
 * </pre>
 * 
 * @author EliX
 *
 */
public abstract class BFS<E extends IPaire<E>> implements SearchGraphe<E> {
	
	/**
	 * Implantation de l'algorithme de parcours en profondeur
	 * d'un graphe.
	 * @param root le sommet choisit en racine
	 */
	@Override
	public void algorithme(ISommet<E> root) {
		Stack<ISommet<E>> f;
		ISommet<E> x;
		Set<ISommet<E>> marque = new HashSet<ISommet<E>>();
		
		
		f = new Stack<ISommet<E>>();
		marque.add(root);
		f.push(root);
		while (!f.empty()) {
			x = f.pop();
			traitement(x);
			
			for (ISommet<E> fils : x.getAdjacents()) {
				if (!marque.contains(fils)) {
					marque.add(fils);
					f.push(fils);
		}	}	}
	}
}
