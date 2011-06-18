package univ.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

import univ.components.edges.ICurveEdge;
import univ.components.edges.QuadraticEdge;
import univ.components.nodes.CircleNode;
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
		
		
		/***************************************/
		
		IShapeNode 	cn = new CircleNode("test1"),
					rn = new RectangleNode("test2");
		cn.setPosition(new Point(100, 100));
		cn.setDouble(true);
		cn.setDraw(true);
		rn.setPosition(new Point(400, 200));
		//setnodes.add(cn);
		//setnodes.add(rn);

		//setedges.add(new QuadraticEdge(cn, rn, "toast"));
	}

	private void createControllers() {
		this.addMouseListener(new MouseAdapter() {
			
			private long time = 0;

			@Override
			public void mouseClicked(MouseEvent m) {
				// TODO Auto-generated method stub
				System.out.println("click " + (m.getWhen() - time));
			}

			@Override
			public void mousePressed(MouseEvent m) {
				// TODO Auto-generated method stub
				time = m.getWhen();
				System.out.println("press");
			}

			@Override
			public void mouseReleased(MouseEvent m) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		/*
		for (ICurveEdge e : setedges)
			e.paint(g);
		
		for (IShapeNode n : setnodes)
			n.paint(g);
		*/
	}
	
	
	public static void main(String[] args) {
		GraphePanel gp = new GraphePanel(Color.lightGray);
		
		TestViewer.showOnFrame(gp);
	}

}
