package univ.components;

import java.awt.Graphics;

/**
 * <h2>Interface d'élément sachant se dessiner</h2>
 * @author EliX
 *
 */
public interface IDrawable {
	
	/**
	 * Méthode permettant de dessiner l'objet.
	 * @param g 
	 */
	public void draw(Graphics g);

}
