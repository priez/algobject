package univ.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

import univ.components.edges.DirectEdge;
import univ.components.edges.ICurveEdge;
import univ.components.edges.ICurveEdge.EdgeStyle;
import univ.components.edges.QuadraticEdge;
import univ.components.nodes.CircleNode;
import univ.components.nodes.EllipseNode;
import univ.components.nodes.IShapeNode;
import univ.components.nodes.RectangleNode;
import univ.components.test.TestViewer;

public class GraphePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Set<IShapeNode> setnodes;
	
	private Set<ICurveEdge> setedges;
	
	GraphePanel (Color bg) {
		super();
		super.setBackground(bg);
		super.setPreferredSize(new Dimension(600, 400));
		//super.setLayout(null);
		
		setnodes = new HashSet<IShapeNode>();
		setedges = new HashSet<ICurveEdge>();
		
		createControllers();
		
		/*****/
		IShapeNode n1 = new CircleNode("00");
		n1.setPosition(new Point(100, 100));
		n1.setDraw(true);
		n1.setFill(Color.DARK_GRAY);
		n1.setFontColor(Color.white);
		
		IShapeNode n2 = new EllipseNode("alpha");
		n2.setDraw(true);
		n2.setFill(Color.orange);
		n2.setPosition(new Point(100, 200));
		
		IShapeNode n3 = new RectangleNode("2");
		n3.setDouble(true);
		n3.setDraw(true);
		n3.setFill(Color.red);
		n3.setFontColor(Color.white);
		n3.setPosition(new Point(300,300));
		
		setnodes.add(n1);
		setnodes.add(n2);
		setnodes.add(n3);
		
		setedges.add(new DirectEdge(n1, n2, "AlgObject"));
		ICurveEdge 	e1 = new DirectEdge(n2, n3, ""),
					e2 = new DirectEdge(n1, n3, "");
		e1.setEdgeStyle(EdgeStyle.DASHDOTTED);
		e2.setEdgeStyle(EdgeStyle.DOTTED);
		setedges.add(e1);
		setedges.add(e2);
	}

	private IShapeNode movable;
	private void createControllers() {
		movable = null;
		// Listener qui gère les noeuds à déplacer
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent m) {
				for (IShapeNode n : setnodes) {
					if (n.isOn(m.getPoint())) { 
						movable = n;
						break;
				}	}
			}

			@Override
			public void mouseReleased(MouseEvent m) {
				movable = null;
			}
		});
		
		this.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (movable != null) {
					int x = movable.getPosition().x - movable.getCenter().x,
						y = movable.getPosition().y - movable.getCenter().y;
					movable.setPosition(new Point(e.getPoint().x + x, e.getPoint().y + y));
					GraphePanel.this.repaint();
				}
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(
			RenderingHints.KEY_ANTIALIASING, 
			RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(
			RenderingHints.KEY_COLOR_RENDERING, 
			RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2.setRenderingHint(
			RenderingHints.KEY_RENDERING, 
			RenderingHints.VALUE_RENDER_QUALITY);
		
		for (ICurveEdge e : setedges)
			e.draw(g);
		
		for (IShapeNode n : setnodes)
			n.draw(g);
	}
	
	
	public static void main(String[] args) {
		GraphePanel gp = new GraphePanel(Color.white);
		
		TestViewer.showOnFrame(gp);
	}

}
