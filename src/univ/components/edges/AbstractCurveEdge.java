package univ.components.edges;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import univ.components.nodes.IShapeNode;

public abstract class AbstractCurveEdge implements ICurveEdge {

	private Color col;
	
	private String v;
	
	protected IShapeNode f,l;
	
	protected EdgeStyle es;
	
	AbstractCurveEdge(IShapeNode f, IShapeNode l, String val, EdgeStyle es, Color col) {
		if (f == null || l == null || val == null || es == null)
			throw new IllegalArgumentException();
		
		this.v = val;
		this.f = f;
		this.l = l;
		this.es = es;
		this.col = col;
	}
	
	AbstractCurveEdge(IShapeNode f, IShapeNode l, String val, EdgeStyle es) {
		if (f == null || l == null || val == null || es == null)
			throw new IllegalArgumentException();
		
		this.v = val;
		this.f = f;
		this.l = l;
		this.es = es;
		this.col = Color.black;
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Color def = g.getColor();
		
		drawCurve(g2);
		drawString(g2);
		
		g.setColor(def);
	}
	
	protected abstract void drawString(Graphics2D g2);

	protected abstract void drawCurve(Graphics2D g2);
	

	@Override
	public void setColor(Color c) {
		if (c == null) throw new IllegalArgumentException();
		col = c;
	}

	@Override
	public Color getColor() {
		return col;
	}

	@Override
	public void setValue(String v) {
		if (v == null) throw new IllegalArgumentException();
		this.v = v;
	}

	@Override
	public String getValue() {
		return v;
	}

	@Override
	public void setEdgeStyle(EdgeStyle es) {
		if (es == null) throw new IllegalArgumentException();
		this.es = es;
	}
	
	@Override
	public String latex() {
		return "\\path[" +
			this.es + 
			"] (" + f.getId() + ") edge node {"+ (v != null ? (v.isEmpty() ? "" : v) : "") + "} (" + l.getId() + ");" ;
	}
}
