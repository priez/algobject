package univ.components.edges;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.QuadCurve2D;

import univ.components.nodes.IShapeNode;

public class DirectEdge extends AbstractCurveEdge {

	public DirectEdge(IShapeNode f, IShapeNode l, String val) {
		super(f, l, val, EdgeStyle.SOLID);
	}
	
	@Override
	protected void drawString(Graphics2D g2) {
		int x1 = f.getCenter().x,
			y1 = f.getCenter().y,
			x2 = l.getCenter().x,
			y2 = l.getCenter().y;
		
		g2.drawString(getValue(), 
			(x1 + x2) / 2 - g2.getFontMetrics().stringWidth(getValue()) / 2, 
			(y1 + y2) / 2 + 20);
	}

	@Override
	protected void drawCurve(Graphics2D g2) {
		Stroke stroke = g2.getStroke();
		g2.setColor(getColor());
		
		QuadCurve2D qc = new QuadCurve2D.Double(
			f.getCenter().getX(), f.getCenter().getY(),
			f.getCenter().getX(), f.getCenter().getY(),
			l.getCenter().getX(), l.getCenter().getY());
		
		g2.setStroke(new BasicStroke(
			1.1f, BasicStroke.CAP_ROUND, 
			BasicStroke.JOIN_BEVEL, 1, 
			es.getDashPatter(), 1));
		
		g2.draw(qc);
		g2.setStroke(stroke);
	}

}
