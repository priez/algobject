package univ.structures.graphes;

import univ.structures.graphes.IPaire;

/**
 * <h2>Interface d'arc d'un graphe</h2>
 * 
 * Un arc est un couple (u,v) ayant une notion d'ordre.
 * 
 * On dit l'arc <i>part</i> du sommet u et <i>arrive</i> dans
 * le sommet v.
 * 
 * <hr>
 * 
 * Définitions issues de l'<u><i>Introduction à l'algorithmique</i></u>
 * de Thomas H. <span style="font-variant: small-caps;">Cormen</span>, 
 * Charles E. <span style="font-variant: small-caps;">Leiserson</span>, 
 * Ronald L. <span style="font-variant: small-caps;">Rivest</span> et
 * Clifford <span style="font-variant: small-caps;">Stein</span>
 * 
 * <hr>
 * 
 * @author EliX
 *
 * @param <E>
 */
public interface IArc extends IPaire<IArc> {
	
	/**
	 * @return le sommet u de l'arc courant {u,v}.
	 */
	ISommet<IArc> getSommetA();
	
	/**
	 * @return le sommet v de l'arc courant {u,v}.
	 */
	ISommet<IArc> getSommetB();

}
