package univ.exemples.graphes;

import univ.structures.graphes.Arc;
import univ.structures.graphes.Graphe;
import univ.structures.graphes.IArc;
import univ.structures.graphes.IAreteValue;
import univ.structures.graphes.IGraphe;
import univ.structures.graphes.ISommet;

/**
 * Soit <i>G'</i> un graphe non-orienté, non-valué <i>(S',A')</i>.
 * <ul>
 * 	<li>S' : [-0-, -1-, -3-, -9-] l'ensemble des sommets du graphe.
 * 	<li>A' : [(-0-,-1-), (-0-, -3-), (-3-, -9-)] l'ensemble des arcs du graphe.
 * </ul>
 */
public class GrapheNOriNVal1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Déclaration et initialisation du graphe orienté et valué par des entiers.
		IGraphe<IArc> G = new Graphe<IArc>(IAreteValue.class);
		
		// création des sommets
		ISommet<IArc>
			s0 = G.createSommet(),
			s1 = G.createSommet(),
			s2 = G.createSommet(),
			s3 = G.createSommet();
		
		// création des arêtes valués
		G.addPaire(new Arc(s0, s0));
		G.addPaire(new Arc(s0, s1));
		G.addPaire(new Arc(s2, s3));
		G.addPaire(new Arc(s3, s2));
		G.addPaire(new Arc(s3, s1));
		
		System.out.println("Affichage du graphe :");
		System.out.println(G);
		
		System.out.println();
		System.out.println("Affichage des sommets adjacents à " + s0 + " :");
		System.out.println(s0.getAdjacents());
		
		System.out.println();
		System.out.println("Affichage des arcs adjacents à " + s3 + " :");
		System.out.println(s3.getSortants());
		
		System.out.println();
		System.out.println("Affichage des arcs incidents à " + s3 + " :");
		System.out.println(s3.getPairesIncidentes());
		
		System.out.println();
		System.out.println("Le graphe contient l'arc {-1-, -3-} : " + G.getPaires().contains(new Arc(s1, s3)));

	}

}
