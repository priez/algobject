package univ.exemples.graphes;

import univ.structures.graphes.AreteValue;
import univ.structures.graphes.Graphe;
import univ.structures.graphes.IAreteValue;
import univ.structures.graphes.IGraphe;
import univ.structures.graphes.ISommet;

/**
 * Soit <i>G</i> un graphe orienté et valué <i>(S,A)</i> (valué par des entiers).
 * <ul>
 * 	<li>S : [-0-, -1-, -2-, -3-] l'ensemble des sommets du graphe.
 * 	<li>A : [(-0-, -0-, 1), (-0-, -1-, -1), (-2-, -3-, -4), (-3-, -2-, 4), (-3-, -1-, 0)] 
 * 		l'ensemble des arêtes valué du graphe.
 * </ul>
 */
public class GrapheOriVal1 {

	public static void main(String[] args) {
		// Déclaration et initialisation du graphe orienté et valué par des entiers.
		IGraphe<IAreteValue<Integer>> G = new Graphe<IAreteValue<Integer>>(IAreteValue.class);
		
		// création des sommets
		ISommet<IAreteValue<Integer>>
			s0 = G.createSommet(),
			s1 = G.createSommet(),
			s2 = G.createSommet(),
			s3 = G.createSommet();
		
		// création des arêtes valués
		G.addPaire(new AreteValue<Integer>(s0, s0, 1));
		G.addPaire(new AreteValue<Integer>(s0, s1, -1));
		G.addPaire(new AreteValue<Integer>(s2, s3, -4));
		G.addPaire(new AreteValue<Integer>(s3, s2, 4));
		G.addPaire(new AreteValue<Integer>(s3, s1, 0));
		
		System.out.println("Affichage du graphe :");
		System.out.println(G);
		
		System.out.println();
		System.out.println("Affichage des sommets adjacents à " + s0 + " :");
		System.out.println(s0.getAdjacents());
		
		System.out.println();
		System.out.println("Affichage des arêtes adjacents à " + s3 + " :");
		System.out.println(s3.getSortants());
		
		System.out.println();
		System.out.println("Affichage des arêtes incidents à " + s3 + " :");
		System.out.println(s3.getPairesIncidentes());
		
		System.out.println();
		System.out.println("Récupération de la valeur de la première arête adjacente à " + s3 + " :");
		System.out.println(s3.degreSortant() > 0 ? s3.getSortants().iterator().next().getValeur() : "null");
	}

}
