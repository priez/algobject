package univ.components.nodes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import univ.components.IDrawable;

/**
 * <h2>Interface de noeud dessinable</h2>
 * 
 * L'objectif de cette interface est de définir un noeud à la 
 * manière de <i>TikZ</i>.<br><br>
 * 
 * <b>node[</b><i>options</i><b>](</b><i>name</i><b>) at 
 * 	(</b><i>coordonnate</i><b>){</b><i>text</i><b>}</b><br><br>
 * 
 * La liste des options :
 * <ul>
 * 	<li> <i>draw</i> -> donne un cadre au noeud.
 * 	<li> <i>fill</i> -> colorise le noeud, s'accompagne d'une couleur (<i>fill=red!20</i>).
 * 	<li> <i>double</i> -> double le cadre du noeud, XXX toujours accompagné de <i>draw</i>???
 * 	<li> <b>options de forme :</b> (XXX shape??)
 * 	<ul>
 * 		<li> <i>rectangle</i> -> la forme par défaut d'un noeud.
 * 		<li> <i>circle</i> -> le noeud prend la forme d'un cercle.
 * 		<li> <i>ellipse</i> -> le noeud prend la forme d'une ellipse.
 * 		<li> <i>dart</i> -> le noeud prend la forme d'une flèche. p177
 * 		<li> <i>diamond</i> -> le noeud prend la forme d'un losange.
 * 		<li> <i>trapezium</i> -> le noeud prend la forme d'un trapèze. p178
 * 		<li> <i>isosceles triangle</i> -> le noeud prend la forme d'un triangle isocèle. p178
 * 		<li> <i>coordonate</i> -> TODO ??? p175
 * 	</ul>
 * 	<li> <b>options de séparation : </b>
 * 	<ul>
 * 		<li> <i>inner sep</i> -> permet de donner une marge interne (<i>inner sep=5pt</i>)
 * 			(par défaut : 0.3333em).
 * 		<li> <i>outer sep</i> -> permet de donner une marge externe (déplace les ancres).
 * 		<li> <i>inner xsep</i> -> permet de spécifier la séparation interne horizontalement.
 * 		<li> <i>inner ysep</i> -> permet de spécifier la séparation interne verticalement.
 * 		<li> <i>outer xsep</i> -> permet de spécifier la séparation externe horizontalement.
 * 		<li> <i>outer ysep</i> -> permet de spécifier la séparation externe verticalement.
 * 	</ul>
 * 	<li> <b>option de dimensionnement :</b>
 *  <ul>
 * 		<li> <i>minimum height</i> -> permet de spécifier une hauteur minimum (<i>minimum height=1cm</i>).
 * 		<li> <i>minimum width</i> -> permet de spécifier une largeur minimum.
 * 		<li> <i>minimum size</i> -> spécifie la hauteur et la largeur minimum.
 * 		<li> <i>shape aspect</i> -> spécifie un ratio entre la largeur et la hauteur de la forme. 
 * 	</ul>
 * 	<li> <i>shape border uses incircle</i> -> TODO ???
 * 	<li> <i>shape border rotate</i> -> rotation par rapport à des ancres
 * </ul>
 * </ul>
 * Les options pour le texte d'affichage :
 * <ul>
 * 	<li> <i>TODO split...</i> -> <i>\node[circle, split, draw] {texte haut \nodepart{lower} texte bas}</i>p178
 * 	<li> <i>text</i> -> colorise le texte (dans la liste d'options : <i>text=red</i>).
 * 	<li> <i>font</i> -> paramétrise le texte (<i>font=\itshape</i>)
 * 	<li> <i></i> -> ...
 * </ul>
 * 
 * 
 * <hr>
 * 
 * Définitions issues de <u><i>The TikZ and PGF Packages Manual for
 * version 2.10</i></u>
 * de Till <span style="font-variant: small-caps;">Tantau</span>, 
 * 
 * <i>Institut für Theoretische Informatik, Universität zu Lübeck</i>
 * <hr>
 * 
 * @author EliX
 *
 */
public interface IShapeNode extends IDrawable {

	/**
	 * <h3>Type énuméré des valeurs de dimension minimal</h3>
	 * 
	 * @author EliX
	 *
	 */
	enum DimSetting {
		MIN_HEIGHT {
			@Override
			String defaultValueTex() {
				return TEX_DIM;
			}

			@Override
			int defaultValue() {
				return DIM;
			}
		},
		MIN_WIDTH {
			@Override
			String defaultValueTex() {
				return TEX_DIM;
			}

			@Override
			int defaultValue() {
				return DIM;
			}
		},
		MIN_SIZE {
			@Override
			String defaultValueTex() {
				return TEX_DIM;
			}

			@Override
			int defaultValue() {
				return DIM;
			}
		};
		
		protected static String TEX_DIM = "0pt";
		
		protected static int DIM = 0;
		
		/**
		 * Valeur par défaut en latex.
		 */
		abstract String defaultValueTex();
		
		/**
		 * Valeur par défaut pour le composant.
		 */
		abstract int defaultValue();
	}
	
	/**
	 * <h3>Type énuméré des valeurs de séparation</h3>
	 * 
	 * @author EliX
	 *
	 */
	enum SepSetting {
		INNER_SEP {
			@Override
			int defaultValue() {
				return INSEPDEF;
			}

			@Override
			String defaultValueTex() {
				return TEX_INSEPDEF;
			}
		},
		OUTER_SEP {
			@Override
			int defaultValue() {
				return OUSEPDEF;
			}

			@Override
			String defaultValueTex() {
				return TEX_OUSEPDEF;
			}
		},
		INNER_XSEP {
			@Override
			int defaultValue() {
				return INSEPDEF;
			}

			@Override
			String defaultValueTex() {
				return TEX_INSEPDEF;
			}
		},
		INNER_YSEP {
			@Override
			int defaultValue() {
				return INSEPDEF;
			}

			@Override
			String defaultValueTex() {
				return TEX_INSEPDEF;
			}
		},
		OUTER_XSEP {
			@Override
			int defaultValue() {
				return OUSEPDEF;
			}

			@Override
			String defaultValueTex() {
				return TEX_OUSEPDEF;
			}
		},
		OUTER_YSEP {
			@Override
			public
			int defaultValue() {
				return OUSEPDEF;
			}

			@Override
			public
			String defaultValueTex() {
				return TEX_OUSEPDEF;
			}
		};
		
		protected static String TEX_INSEPDEF = ".3333em",
								TEX_OUSEPDEF = ".5\\pgflinewidth";
		
		// FIXME double int??? les dimension sur les objets dessinable sont quoi???
		protected static int	INSEPDEF = 5,
	 							OUSEPDEF = 10;
		
		/**
		 * La valeur par défaut pour le composant
		 * @return un réel (ex : 0.333)
		 */
		abstract int defaultValue();
		
		/**
		 * La valeur par défaut en latex
		 * @return une chaine de caractère (cf doc)
		 */
		abstract String defaultValueTex();
	}
	
	public static Color FILL_DEF_COL = Color.white,
						FONT_DEF_COL = Color.black;
	
	public static boolean 	DRAW_DEF = false,
							DOUBLE_DEF = false;
	
	public static double ASPECT_DEF = 1.0;
	
	String getName();
	
	Color getFontColor();
	
	int getOuterXSep();
	int getOuterYSep();
	
	int getInnerXSep();
	int getInnerYSep();
	
	int getMinHeight();
	int getMinWidth();
	
	double getShapeAspect();
	
	void setFontColor(Color c);
	
	void setOuterXSep(int s);
	void setOuterYSep(int s);
	void setOuterSep(int s);
	
	void setInnerXSep(int s);
	void setInnerYSep(int s);
	void setInnerSep(int s);
	
	void setMinHeight(int s);
	void setMinWidth(int s);
	void setMinDim(int s);
	void setMinDim(Dimension dim);
	
	void setShapeAspect(double rat);
	
	boolean isDouble();
	boolean isDraw();
	
	Color getFill();
	
	void setDouble(boolean b);
	void setDraw(boolean b);
	
	void setFill(Color c);
	
	void setPosition(Point p);
	
	Point getPosition();
	
	Point getCenter();
	
	Dimension getDimension();
}
