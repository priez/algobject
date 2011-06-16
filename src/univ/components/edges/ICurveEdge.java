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
	
	enum EdgeStyle {
		SOLID,
		DOTTED,
		DENSELY_DOTTED,
		LOOSELY_DOTTED,
		DASHED,
		DENSELY_DASHED,
		LOOSELY_DASHED,
		DASHDOTTED,
		DENSELY_DASHDOTTED,
		LOOSELY_DASHDOTTED,
		DASHDOTDOTTED,
		DENSELY_DASHDOTDOTTED,
		LOOSELY_DASHDOTDOTTED;
		
		/**
		 * @return le texte de la sortie java associée au style
		 */
		public String getLatex() {
			return EdgeStyle.this.name().toLowerCase().replace("_", " ");
		}
	}
	
	void setColor(Color c);
	
	Color getColor();
}
