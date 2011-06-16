package univ.components.nodes;

import java.awt.Color;
import java.awt.Graphics;

public class CircleNode extends AbstractShapeNode implements IShapeNode {

	

	public CircleNode(String alias, String name, boolean draw, boolean doubl,
			Color fill) {
		super(alias, name, draw, doubl, fill);
	}

	public CircleNode(String alias, String name, boolean draw, boolean doubl) {
		super(alias, name, draw, doubl);
	}

	public CircleNode(String alias, String name, boolean draw, Color fill) {
		super(alias, name, draw, fill);
	}

	public CircleNode(String alias, String name, boolean draw) {
		super(alias, name, draw);
	}

	public CircleNode(String alias, String name, Color col, int minwidth,
			int minheight, double aspect, int innerxsep, int innerysep,
			int outerxsep, int outerysep, boolean draw, boolean doubl,
			Color fill) {
		super(alias, name, col, minwidth, minheight, aspect, innerxsep, innerysep,
				outerxsep, outerysep, draw, doubl, fill);
	}

	public CircleNode(String alias, String name, Color fill) {
		super(alias, name, fill);
	}

	public CircleNode(String alias, String name, int minsize, double aspect) {
		super(alias, name, minsize, aspect);
	}

	public CircleNode(String alias, String name, int minwidth, int minheight,
			double aspect, boolean draw, boolean doubl, Color fill) {
		super(alias, name, minwidth, minheight, aspect, draw, doubl, fill);
	}

	public CircleNode(String alias, String name, int minwidth, int minheight,
			double aspect) {
		super(alias, name, minwidth, minheight, aspect);
	}

	public CircleNode(String alias, String name, int minwidth, int minheight) {
		super(alias, name, minwidth, minheight);
	}

	public CircleNode(String alias, String name, int minsize) {
		super(alias, name, minsize);
	}

	public CircleNode(String alias, String name) {
		super(alias, name);
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
		g.drawString(getAlias(), 
			x + getInnerXSep(), 
			y + Math.max(h, w) / 2 + getInnerYSep());
	}

}
