package univ.components.nodes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class DiamondNode extends AbstractShapeNode implements IShapeNode {
	
	

	public DiamondNode(String alias, String name, boolean draw, boolean doubl,
			Color fill) {
		super(alias, name, draw, doubl, fill);
	}

	public DiamondNode(String alias, String name, boolean draw, boolean doubl) {
		super(alias, name, draw, doubl);
	}

	public DiamondNode(String alias, String name, boolean draw, Color fill) {
		super(alias, name, draw, fill);
	}

	public DiamondNode(String alias, String name, boolean draw) {
		super(alias, name, draw);
	}

	public DiamondNode(String alias, String name, Color col, int minwidth,
			int minheight, double aspect, int innerxsep, int innerysep,
			int outerxsep, int outerysep, boolean draw, boolean doubl,
			Color fill) {
		super(alias, name, col, minwidth, minheight, aspect, innerxsep, innerysep,
				outerxsep, outerysep, draw, doubl, fill);
	}

	public DiamondNode(String alias, String name, Color fill) {
		super(alias, name, fill);
	}

	public DiamondNode(String alias, String name, int minsize, double aspect) {
		super(alias, name, minsize, aspect);
	}

	public DiamondNode(String alias, String name, int minwidth, int minheight,
			double aspect, boolean draw, boolean doubl, Color fill) {
		super(alias, name, minwidth, minheight, aspect, draw, doubl, fill);
	}

	public DiamondNode(String alias, String name, int minwidth, int minheight,
			double aspect) {
		super(alias, name, minwidth, minheight, aspect);
	}

	public DiamondNode(String alias, String name, int minwidth, int minheight) {
		super(alias, name, minwidth, minheight);
	}

	public DiamondNode(String alias, String name, int minsize) {
		super(alias, name, minsize);
	}

	public DiamondNode(String alias, String name) {
		super(alias, name);
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
		g.drawString(getAlias(), 
			x + getInnerXSep() + CONS / 2, 
			y + getInnerYSep() + 3 * g.getFontMetrics().getHeight() / 4);
	}

}
