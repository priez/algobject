package univ.structures.tas;

/**
 * <h2>Tas binomiaux</h2>
 * 
 * Structures de données sous le nom de <i>tas fusionnables</i> supportant les opérations 
 * suivantes :
 * 
 * <ul>
 *	<li> <code>CREER_TAS()</code> crée et retourne un nouveau tas sans élément.
 *	<li> <code>INSERER(T, s)</code> insère dans le tas T un noeud x, dont le champ <i>clé</i>
 *		a déjà été rempli.
 *	<li> <code>MINIMUM(T)</code> retourne un pointeur sur le noeud dont la clé est minimale
 *		et retourne un pointeur sur ce noeud.
 *	<li> <code>UNION(T1, T2)</code> crée et retourne un nouveau tas qui contient tous les 
 *		noeuds des tas T1 et T2. Les tas T1 et T2 sont "détruits" par cette opération.
 *	<li> <code>DIMINUER_CLE(T, x, k)</code> affecte au noeud x du tas T la nouvelle valeur
 *		de clé k, qui est censée être inférieure ou égale à la valeur courante de la clé.
 *	<li>  <code>SUPPRIMER(T, x)</code> supprime le noeud x du tas T 
 * </ul>
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
 * @author EliX
 *
 */
public interface ITasBinomial<S> {
	
	/**
	 * Insère un noeud au tas.
	 * @param s le noeud à ajouter.
	 */
	void inserer(S s);
	
	/**
	 * Supprime un noeud du tas.
	 * @param s le noeud à supprimer.
	 */
	void supprimer(S s);
	
	/**
	 * Retourne le noeud ayant la clé de poids minimum.
	 * @return un noeud.
	 */
	S minimum();

}
