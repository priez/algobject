package univ.components.edges;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.QuadCurve2D;

import univ.components.nodes.IShapeNode;

public class QuadraticEdge extends AbstractCurveEdge {

	private static final double PHI = 1 / Math.sqrt(2);

	public QuadraticEdge(IShapeNode f, IShapeNode l, String val) {
		super(f, l, val, EdgeStyle.SOLID);
	}

	@Override
	protected void drawString(Graphics2D g2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void drawCurve(Graphics2D g2) {
		Stroke stroke = g2.getStroke();
		g2.setColor(getColor());
		// distance fl
		double dist = Math.hypot(
			f.getCenter().getX() - l.getCenter().getX(), 
			f.getCenter().getY() - l.getCenter().getY());
		// angle entre fl et l'abscisse
		double alpha = Math.acos(
			((l.getCenter().getX() - f.getCenter().getX()) / dist)
			* (l.getCenter().getY() > f.getCenter().getY() ? 1 : -1));
		// point de controle
		double 	beta = alpha + dist * PHI;
		double x,y;//, x1,y1;
		x = f.getCenter().getX() + dist * Math.cos(beta);
		//x1 = (f.getCenter().getX() + l.getCenter().getX()) / 2;
		//x = (x + x1) / 2;
		
		y = f.getCenter().getY() + dist * Math.sin(beta);
		//y1 = (f.getCenter().getY() + l.getCenter().getY()) / 2;
		//y = (y + y1) / 2;
		System.out.println(beta + "::"+ x + "::" + y);
		// courbe
		QuadCurve2D qc = new QuadCurve2D.Double(
			f.getCenter().getX(), f.getCenter().getY(),
			x, y,
			l.getCenter().getX(), l.getCenter().getY());
		
		g2.setStroke(new BasicStroke(
			1.1f, BasicStroke.CAP_ROUND, 
			BasicStroke.JOIN_BEVEL, 1, 
			es.getDashPatter(), 1));
		
		g2.draw(qc);
		
		g2.setStroke(new BasicStroke(4f));
		g2.drawLine((int)x, (int)y, (int)x, (int)y);
		//g2.drawLine(f.getCenter().x, f.getCenter().y, x2, y2)
		g2.setStroke(stroke);
	}
	
	

}
