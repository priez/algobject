package univ.components.edges;

import java.awt.Color;

import univ.components.IDrawable;

/**
 * <h2>Interface de arcs ou arête</h2>
 * 
 * L'objectif de cette interface est de définir un arc ou
 * une arête à la manière de <i>TikZ</i>.<br><br>
 * 
 * exemple p197
 * <pre>
 * \begin{tikzpicture}
 *    \path  (0, 0) node             	(x) {Hello World!}
 *           (3, 1) node [circle,draw]	(y) {$\int_1^2 x \mathrm d x$}
 * 
 *    \draw[->, blue]   (x) -- (y);
 *    \draw[<-, red]    (x) -| (y);
 *    \draw[->, orange] (x) .. controls+(up:1cm) and +(left:1cm) .. node [above,sloped] {label} (y);
 * \end{tikzpicture}
 * </pre>
 * 
 * Différent style : p158
 * 
 * <ul>
 * 	<li><u>solid</u> : <pre>___________________</pre>
 * 	<li><u>dotted</u> : <pre>. . . . . . . . .</pre>
 * 	<li><u>loosely dotted</u> : <pre>.  .  .  .  .  .</pre>
 * 	<li><u>densely dotted</u> : <pre>...........</pre>
 * 	<li><u>dashed</u> : <pre>- - - - - - -</pre>
 * 	<li><u>densely dashed</u> : <pre>---------</pre>
 * 	<li><u>loosely dashed</u> : <pre>-  -  -  -  -  -  -</pre>
 * 	<li><u>dashdotted</u> : <pre>- . - . -</pre>
 * 	<li><u>densely dashdotted</u> : <pre>-.-.-.-.-.-.-</pre>
 * 	<li><u>loosely dashdotted</u> : <pre>-  .  -  .  -  .  -</pre>
 * 	<li><u>dashdotdotted</u> : <pre>- .. - .. - .. - .. -</pre>
 * 	<li><u>loosely/densely</u> ...
 * </ul>
 * 
 * <hr>
 * 
 * Définitions issues de <u><i>The TikZ and PGF Packages Manual for
 * version 2.10</i></u>
 * de Till <span style="font-variant: small-caps;">Tantau</span>, 
 * 
 * <i>Institut für Theoretische Informatik, Universität zu Lübeck</i>
 * <hr>
 * @author EliX
 *
 */
public interface ICurveEdge extends IDrawable {
	
	public enum EdgeStyle {
		SOLID {	@Override public float[] getDashPatter() {
			return new float[] {1};
		}	},
		DOTTED { @Override public float[] getDashPatter() {
			return new float[] {1, 5};
		}	},
		DENSELY_DOTTED { @Override public float[] getDashPatter() {
			return new float[] {5, 2};
		}	},
		LOOSELY_DOTTED { @Override public float[] getDashPatter() {
			return new float[] {5, 10};
		}	},
		DASHED { @Override public float[] getDashPatter() {
			return new float[] {10, 5};
		}	},
		DENSELY_DASHED { @Override public float[] getDashPatter() {
			return new float[] {10, 2};
		}	},
		LOOSELY_DASHED { @Override public float[] getDashPatter() {
			return new float[] {10, 10};
		}	},
		DASHDOTTED { @Override public float[] getDashPatter() {
			return new float[] {10, 5, 5, 5};
		}	},
		DENSELY_DASHDOTTED {
			@Override
			public float[] getDashPatter() {
				// TODO Auto-generated method stub
				return null;
			}
		},
		LOOSELY_DASHDOTTED {
			@Override
			public float[] getDashPatter() {
				// TODO Auto-generated method stub
				return null;
			}
		},
		DASHDOTDOTTED {
			@Override
			public float[] getDashPatter() {
				// TODO Auto-generated method stub
				return null;
			}
		},
		DENSELY_DASHDOTDOTTED {
			@Override
			public float[] getDashPatter() {
				// TODO Auto-generated method stub
				return null;
			}
		},
		LOOSELY_DASHDOTDOTTED {
			@Override
			public float[] getDashPatter() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		
		/**
		 * @return le texte de la sortie java associée au style
		 */
		public String getLatex() {
			return EdgeStyle.this.name().toLowerCase().replace("_", " ");
		}
		
		public abstract float[] getDashPatter();
	}

	void setValue(String v);
	
	String getValue();
	
	void setColor(Color c);
	
	Color getColor();
	
	void setEdgeStyle(EdgeStyle es);
}
