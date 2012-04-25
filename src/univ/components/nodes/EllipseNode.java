package univ.components.nodes;

import java.awt.Graphics;
import java.awt.Point;

public class EllipseNode extends AbstractShapeNode {

	public EllipseNode(String name, boolean draw, boolean doubl,
			ColorSetting fill) {
		super(name, draw, doubl, fill);
	}

	public EllipseNode(String name, boolean draw, boolean doubl) {
		super(name, draw, doubl);
	}

	public EllipseNode(String name, boolean draw, ColorSetting fill) {
		super(name, draw, fill);
	}

	public EllipseNode(String name, boolean draw) {
		super(name, draw);
	}

	public EllipseNode(String name, ColorSetting col, int minwidth,
			int minheight, double aspect, int innerxsep, int innerysep,
			int outerxsep, int outerysep, boolean draw, boolean doubl,
			ColorSetting fill) {
		super(name, col, minwidth, minheight, aspect, innerxsep, innerysep,
				outerxsep, outerysep, draw, doubl, fill);
	}

	public EllipseNode(String name, ColorSetting fill) {
		super(name, fill);
	}

	public EllipseNode(String name, int minsize, double aspect) {
		super(name, minsize, aspect);
	}

	public EllipseNode(String name, int minwidth, int minheight,
			double aspect, boolean draw, boolean doubl, ColorSetting fill) {
		super(name, minwidth, minheight, aspect, draw, doubl, fill);
	}

	public EllipseNode(String name, int minwidth, int minheight,
			double aspect) {
		super(name, minwidth, minheight, aspect);
	}

	public EllipseNode(String name, int minwidth, int minheight) {
		super(name, minwidth, minheight);
	}

	public EllipseNode(String name, int minsize) {
		super(name, minsize);
	}

	public EllipseNode(String name) {
		super(name);
	}

	@Override
	protected void fillShape(Graphics g, int x, int y, int h, int w) {
		g.fillOval(x, y, w, h);
	}

	@Override
	protected void drawShape(Graphics g, int x, int y, int h, int w) {
		g.drawOval(x, y, w, h);
	}

	@Override
	protected void drawString(Graphics g, int x, int y, int h, int w) {
		g.drawString(getName(), 
			x + getInnerXSep(), 
			y + getInnerYSep() + 3 * g.getFontMetrics().getHeight() / 4);
	}

	@Override
	public Point getCenter() {
		return new Point(
			getPosition().x + getInnerXSep() + Math.max(stringw, minwidth) / 2,
			getPosition().y + getInnerYSep() + Math.max(stringh, minheight) / 2);
	}

	@Override
	String option() {
		// FIXME
		return "rectangle";
	}

}
