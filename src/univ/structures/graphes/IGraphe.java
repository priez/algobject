package univ.structures.graphes;


import java.util.Set;

import univ.listener.PCL;

/** 
 * <h2>Interface de graphe</h2>
 * 
 * Un <i>graphe G</i> est un couple <i>(S,A)</i>, où
 * <ul>
 * 	<li> <i>S</i> est l'ensemble des <i>sommets</i> de <i>G</i>
 *  <li> <i>A</i> est l'ensemble des <i>paires (arcs</i> ou <i>arêtes) </i> de <i>G</i>
 * </ul>
 * 
 * On parle de <i>graphe non orienté</i> lorsque <i>A</i> est un ensemble
 * <i>d'arcs</i>, chaque arc est une paire de sommets formant un ensemble
 * non ordonné. (<b>ex: {a,b}</b>)<br><br>
 * 
 * On parle de <i>graphe orienté</i> lorsque <i>A</i> est un ensemble
 * <i>d'arêtes</i>, chaque arête est une paire de sommets formant un
 * couple (ensemble avec notion d'ordre). (<b>ex : (a,b)</b>)
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
 * Le graphe s'occupe de gérer la cohérence entre les noeuds et 
 * l'ensemble des paires.
 * 
 * @author EliX
 *
 *
 * @param <N> le type des sommets (associé aux arcs ou arêtes).
 * @param <E> le type des paires : arcs ou arêtes.
 * 
 * @exemple
 * Soit <i>G</i> un graphe orienté et valué <i>(S,A)</i> (valué par des entiers).
 * <ul>
 * 	<li>S : [-0-, -1-, -2-, -3-] l'ensemble des sommets du graphe.
 * 	<li>A : [(-0-, -0-, 1), (-0-, -1-, -1), (-2-, -3-, -4), (-3-, -2-, 4), (-3-, -1-, 0)] 
 * 		l'ensemble des arêtes valué du graphe.
 * </ul>
 * 
 * Soit <i>G'</i> un graphe non-orienté, non-valué <i>(S',A')</i>.
 * <ul>
 * 	<li>S' : [-0-, -1-, -3-, -9-] l'ensemble des sommets du graphe.
 * 	<li>A' : [(-0-,-1-), (-0-, -3-), (-3-, -9-)] l'ensemble des arcs du graphe.
 * </ul>
 */
public interface IGraphe<E extends IPaire<E>> extends PCL {
	
	public static final String 	SOMMET_AJOUTE = "1",
								SOMMET_ENLEVE = "2",
								PAIRE_AJOUTE = "4",
								PAIRE_ENLEVE = "8";
	
	/**
	 * @return l'ensemble des noeuds.
	 */
	Set<ISommet<E>> getSommets();
	
	/**
	 * @return l'ensemble des paires.
	 */
	Set<E> getPaires();
	
	/**
	 * Créer un sommet au graphe.
	 * @post getSommet().contains(result)
	 */
	ISommet<E> createSommet();

	@Deprecated
	void addSommet(ISommet<E> s);
	
	
	
	/**
	 * Ajoute une paire au graphe.
	 * @param p la paire à ajoutée.
	 * @pre<ul>
	 * 	<li>p != null 
	 * 	<li>forall s in p.getElements() : getSommets().contains(s)
	 * </ul>
	 * @post getPaires().contains(p) 
	 */
	void addPaire(E p);

	/**
	 * Retire du graphe un sommet
	 * @param s le sommet qu'on retire
	 * @pre s != null
	 * @post !getSommets().contains(s)
	 */
	void removeSommet(ISommet<E> s);
	
	/**
	 * Retire du graphe une paire
	 * @param p la paire qu'on retire
	 * @pre p != null
	 * @post !getPaires().contains(p)
	 */
	void removePaire(E p);
	
	/**
	 * Indique si le graphe est valué.
	 * @return <code>true</code> <b>si</b> l'ensemble
	 * 		des paires étendent IValuation;
	 * 		<code>false</code> sinon. 
	 */
	boolean isValue();
	
	/**
	 * Indique si le graphe est orienté.
	 * @return <code>true</code> <b>si</b> l'ensemble
	 * 		des paires sont des arêtes (étendent IOrientation);
	 * 		<code>false</code> sinon.
	 */
	boolean isOriente();
}
