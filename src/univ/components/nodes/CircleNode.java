package univ.components.nodes;

import java.awt.Graphics;
import java.awt.Point;

public class CircleNode extends AbstractShapeNode {

	public CircleNode(String name, boolean draw, boolean doubl,
			ColorSetting fill) {
		super(name, draw, doubl, fill);
	}

	public CircleNode(String name, boolean draw, boolean doubl) {
		super(name, draw, doubl);
	}

	public CircleNode(String name, boolean draw, ColorSetting fill) {
		super(name, draw, fill);
	}

	public CircleNode(String name, boolean draw) {
		super(name, draw);
	}

	public CircleNode(String name, ColorSetting col, int minwidth,
			int minheight, double aspect, int innerxsep, int innerysep,
			int outerxsep, int outerysep, boolean draw, boolean doubl,
			ColorSetting fill) {
		super(name, col, minwidth, minheight, aspect, innerxsep, innerysep,
				outerxsep, outerysep, draw, doubl, fill);
	}

	public CircleNode(String name, ColorSetting fill) {
		super(name, fill);
	}

	public CircleNode(String name, int minsize, double aspect) {
		super(name, minsize, aspect);
	}

	public CircleNode(String name, int minwidth, int minheight,
			double aspect, boolean draw, boolean doubl, ColorSetting fill) {
		super(name, minwidth, minheight, aspect, draw, doubl, fill);
	}

	public CircleNode(String name, int minwidth, int minheight,
			double aspect) {
		super(name, minwidth, minheight, aspect);
	}

	public CircleNode(String name, int minwidth, int minheight) {
		super(name, minwidth, minheight);
	}

	public CircleNode(String name, int minsize) {
		super(name, minsize);
	}

	public CircleNode(String name) {
		super(name);
	}

	@Override
	protected void fillShape(Graphics g, int x, int y, int h, int w) {
		g.fillOval(x, y, Math.max(w, h), Math.max(w, h));
	}

	@Override
	protected void drawShape(Graphics g, int x, int y, int h, int w) {
		g.drawOval(x, y, Math.max(w, h), Math.max(w, h));
	}

	@Override
	protected void drawString(Graphics g, int x, int y, int h, int w) {
		g.drawString(getName(), 
			x + getInnerXSep(), 
			y + Math.max(h, w) / 2 + getInnerYSep());
	}

	@Override
	public Point getCenter() {
		return new Point(
			getPosition().x + getInnerXSep() + Math.max(stringw, minwidth) / 2,
			getPosition().y + getInnerYSep() + Math.max(stringh, minheight));
	}

	@Override
	String option() {
		// FIXME
		return "circle";
	}

}
