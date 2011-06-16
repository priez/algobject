package univ.components.edges;

import java.awt.Color;
import java.awt.Graphics;

import univ.components.nodes.IShapeNode;

public class DirectEdge implements ICurveEdge {
	
	private String v;
	
	private IShapeNode f,l;
	
	private Color col;
	
	public DirectEdge(IShapeNode f, IShapeNode l, String val) {
		this.v = val;
		this.f = f;
		this.l = l;
		this.col = Color.black;
	}

	@Override
	public void draw(Graphics g) {
		Color def = g.getColor();
		g.setColor(col);
		
		int x1 = f.getCenter().x,
			y1 = f.getCenter().y,
			x2 = l.getCenter().x,
			y2 = l.getCenter().y;
		
		g.drawLine(x1, y1, x2, y2);
		
		g.drawString(v, 
			(x1 + x2) / 2 - g.getFontMetrics().stringWidth(v) / 2, 
			(y1 + y2) / 2 + 20);
		
		g.setColor(def);
	}

	@Override
	public void setColor(Color c) {
		if (c == null) throw new IllegalArgumentException();
		
		col = c;
	}

	@Override
	public Color getColor() {
		return col;
	}

}
