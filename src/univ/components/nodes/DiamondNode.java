package univ.components.nodes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class DiamondNode extends AbstractShapeNode {

	private static final long serialVersionUID = -5193373333094698596L;

	public DiamondNode(String name, boolean draw, boolean doubl,
			Color fill) {
		super(name, draw, doubl, fill);
	}

	public DiamondNode(String name, boolean draw, boolean doubl) {
		super(name, draw, doubl);
	}

	public DiamondNode(String name, boolean draw, Color fill) {
		super(name, draw, fill);
	}

	public DiamondNode(String name, boolean draw) {
		super(name, draw);
	}

	public DiamondNode(String name, Color col, int minwidth,
			int minheight, double aspect, int innerxsep, int innerysep,
			int outerxsep, int outerysep, boolean draw, boolean doubl,
			Color fill) {
		super(name, col, minwidth, minheight, aspect, innerxsep, innerysep,
				outerxsep, outerysep, draw, doubl, fill);
	}

	public DiamondNode(String name, Color fill) {
		super(name, fill);
	}

	public DiamondNode(String name, int minsize, double aspect) {
		super(name, minsize, aspect);
	}

	public DiamondNode(String name, int minwidth, int minheight,
			double aspect, boolean draw, boolean doubl, Color fill) {
		super(name, minwidth, minheight, aspect, draw, doubl, fill);
	}

	public DiamondNode(String name, int minwidth, int minheight,
			double aspect) {
		super(name, minwidth, minheight, aspect);
	}

	public DiamondNode(String name, int minwidth, int minheight) {
		super(name, minwidth, minheight);
	}

	public DiamondNode(String name, int minsize) {
		super(name, minsize);
	}

	public DiamondNode(String name) {
		super(name);
	}
	
	private static int CONS = 20;

	@Override
	protected void fillShape(Graphics g, int x, int y, int h, int w) {
		int[] polx = {
			x, 
			x + w / 2 + CONS / 2, 
			x + w + CONS, 
			x + w / 2 + CONS / 2};
		int[] poly = {
			y + h / 2, 
			y, 
			y + h / 2, 
			y + h};
		Polygon pol = new Polygon(polx, poly, 4);
		g.fillPolygon(pol);
	}

	@Override
	protected void drawShape(Graphics g, int x, int y, int h, int w) {
		int[] polx = {
			x, 
			x + w / 2 + CONS / 2, 
			x + w + CONS, 
			x + w / 2 + CONS / 2};
		int[] poly = {
			y + h / 2, 
			y, 
			y + h / 2, 
			y + h};
		Polygon pol = new Polygon(polx, poly, 4);
		g.drawPolygon(pol);
	}

	@Override
	protected void drawString(Graphics g, int x, int y, int h, int w) {
		g.drawString(getName(), 
			x + getInnerXSep() + CONS / 2, 
			y + getInnerYSep() + 3 * g.getFontMetrics().getHeight() / 4);
	}

	@Override
	public Point getCenter() {
		return new Point(
			getPosition().x + getInnerXSep() + Math.max(stringw, minwidth) / 2 + 10,
			getPosition().y + getInnerYSep() + Math.max(stringh, minheight) / 2);
	}

}
