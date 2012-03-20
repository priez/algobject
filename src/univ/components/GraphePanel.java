package univ.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;

import univ.components.edges.DirectEdge;
import univ.components.edges.ICurveEdge;
import univ.components.edges.ICurveEdge.EdgeStyle;
import univ.components.nodes.CircleNode;
import univ.components.nodes.EllipseNode;
import univ.components.nodes.IShapeNode;
import univ.components.nodes.RectangleNode;
import univ.components.test.TestViewer;

public class GraphePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public static Color SELECTED_COL = Color.green;
	
	private Set<IShapeNode> setnodes;
	
	private Set<ICurveEdge> setedges;
	
	GraphePanel (Color bg) {
		super();
		super.setBackground(bg);
		super.setPreferredSize(new Dimension(600, 400));
		//super.setLayout(null);
		
		createModel();
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

	private void createModel() {
		setnodes = new HashSet<IShapeNode>();
		setedges = new HashSet<ICurveEdge>();
	}

	private IShapeNode movable;
	private Map<IShapeNode, Point> squelette;
	private Point origine; 
	private Rectangle select;
	private Set<IShapeNode> selectedNodes;
	private Map<IShapeNode, Color> selectedNodesCol;
	
	private void createControllers() {
		movable = null;
		select = null;
		squelette = new HashMap<IShapeNode, Point>();
		selectedNodes = new HashSet<IShapeNode>();
		selectedNodesCol = new HashMap<IShapeNode, Color>();
		
		/* sélection de noeud */
		this.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// si aucun noeud n'est sélectionné : 
				// // soit on sélectionne un noeud
				// // soit on clique dans le vide
				if (selectedNodes.isEmpty()) {
					
					for (IShapeNode n : setnodes)
						if (n.isOn(e.getPoint())) {
							selectNode(n);
							movable = n;
							GraphePanel.this.repaint();
						}
				// sinon un ou plusieurs noeud sont sélectionnés
			 	} else {
					// 1 cas on clique dans le vide : on déselectionne tout.
			 		// 2 cas on clique sur un noeud particulier : on déselectionne tout et sélection celui-ci
			 		for (IShapeNode n : setnodes) {
			 			if (n.isOn(e.getPoint())) {
			 				selectNode(n);
			 			} else if (selectedNodes.contains(n))
			 				deselectNode(n);
			 		}
			 		GraphePanel.this.repaint();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// si on clique dans le vide : rectangle ou déplacement
				if (movable == null) {
					// si on est sur un noeud non sélectionné
					for (IShapeNode n : setnodes) 
						if (n.isOn(e.getPoint()))
							movable = n;
					// sinon on fait un rectangle
					if (movable == null) {
						select = new Rectangle(e.getPoint());
						origine = e.getPoint();
				}	}
			}

			@Override
			public void mouseReleased(MouseEvent m) {
				movable = null;
				select = null;
				origine = null;
				GraphePanel.this.repaint();
			}
		});
		
		this.addMouseMotionListener(new MouseMotionAdapter() {
			int w,h;
			/* création d'un squelette par rapport à un objet déplacable */
			private void createSquelette() {
				Point p;
				int x,y;
				for (IShapeNode n : selectedNodes) {
					x = movable.getPosition().x - n.getPosition().x;
					y = movable.getPosition().y - n.getPosition().y;
					p = new Point(x,y);
					squelette.put(n, p);
			}	}
			
			/* déstruction du sequelette */
			private void deplaceSquelette() {
				int x,y;
				for (IShapeNode n : selectedNodes) {
					x = movable.getPosition().x - squelette.get(n).x;
					y = movable.getPosition().y - squelette.get(n).y;
					n.setPosition(new Point(x,y));
				}
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				/* déplacement d'un ou plusieurs noeuds */
				if (movable != null) {
					// calcul du centre de l'objet (principal) à déplacer
					int x = movable.getPosition().x - movable.getCenter().x,
						y = movable.getPosition().y - movable.getCenter().y;
					// déplacement de l'objet principal
					movable.setPosition(new Point(e.getPoint().x + x, e.getPoint().y + y));
					// déplacement des autres objets.
					if (selectedNodes.size() > 1) {
						// si le "squelette" de déplacement des objets n'est pas crée, on crée
						if (squelette.isEmpty())
							createSquelette();
						// puis on déplace le tout
						deplaceSquelette();
					}
					GraphePanel.this.repaint();
				/* sinon si on clique dans le vide pour sélectionner dans un rectangle */
				} else if (select != null) {
					// dimension du rectangle
					w = Math.abs(origine.x - e.getPoint().x);
					h = Math.abs(origine.y - e.getPoint().y);
					// test des noeuds inclu ou exclu du rectangle
					for (IShapeNode n : setnodes) {
						if (n.isIn(select)) {
							if (!selectedNodes.contains(n)) 
								selectNode(n);
						} else { 
							if (selectedNodes.contains(n))
								deselectNode(n);
					}	}	
					// positionnement du rectangle
					if (origine.x > e.getPoint().x)
						select.x = e.getPoint().x;
					select.width = w;
					if (origine.y > e.getPoint().y)
						select.y = e.getPoint().y;
					select.height = h;
					
					GraphePanel.this.repaint();
				} 
			}
		});
	}
	
	public void selectNode (IShapeNode n) {
		selectedNodes.add(n);
		selectedNodesCol.put(n, n.getFill());
		n.setFill(SELECTED_COL);
	}
	
	public void deselectNode (IShapeNode n) {
		n.setFill(selectedNodesCol.get(n));
		selectedNodes.remove(n);
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
		
		if (select != null) {
			g2.setColor(SELECTED_COL);
			g2.draw(select);
		}
	}
	
	
	public static void main(String[] args) {
		GraphePanel gp = new GraphePanel(Color.white);
		
		TestViewer.showOnFrame(gp);
	}

}
