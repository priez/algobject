package univ.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import univ.components.edges.ICurveEdge;
import univ.components.nodes.IShapeNode;
import univ.components.test.TestViewer;
import univ.structures.graphes.Graphe;
import univ.structures.graphes.IArc;
import univ.structures.graphes.IGraphe;
import univ.structures.graphes.IPaire;
import univ.structures.graphes.ISommet;

public class GrapheGridPanel<E extends IPaire<E>> extends GraphePanel<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5746557662872162872L;
	
	public Color grid_color;
	
	public int col,row;

	GrapheGridPanel(Color bg, IGraphe<E> model) {
		super(bg, model);
		
		col = 80;
		row = 80;
	}
	
	@Override
	public void addNode(IShapeNode shape) {
		if (shape == null) 
			throw new NullPointerException();
		if (setnodes.contains(shape)) 
			throw new IllegalArgumentException("Ce noeud existe déjà");
		
		ISommet<E> s = model.createSommet();
		center(shape);
		setnodes.add(shape);
		mapSomShap.put(s, shape);
		
		repaint();
	}
	
	private void center(IShapeNode n) {
		n.setPosition(new Point(
				col * (n.getCenter().x / col) + col/2 - (n.getCenter().x - n.getPosition().x),
				row * (n.getCenter().y / row) + row/4 + (n.getCenter().y - n.getPosition().y)/2
		));
	}
	
	private String makeCommand(IShapeNode n, Map<Integer, String> mapTag) {
		StringBuffer com = new StringBuffer("\\AOn");
		if (n.getId() / 100 == 0) com.append("o");
		if (n.getId() / 10 == 0)  com.append("o");
		com.append(new String(""+ n.getId())
			.replace("0", "o")
			.replace("1", "a")
			.replace("2", "b")
			.replace("3", "c")
			.replace("4", "d")
			.replace("5", "e")
			.replace("6", "f")
			.replace("7", "g")
			.replace("8", "h")
			.replace("9", "i"));
		mapTag.put(n.getId(), new String("\\newcommand{" + com.toString() + "}{" + n.latex() + "}\n"));
		return com.toString();
	}
	
	public String latex() {
		IShapeNode[][] matrix = new IShapeNode
				[this.getSize().height / row +1]
				[this.getSize().width / col +1];
		Map<IShapeNode, String> map = new HashMap<IShapeNode, String>();
		SortedMap<Integer, String> mapTag = new TreeMap<Integer, String>();
		StringBuffer sb = new StringBuffer("{");
		int h = 0, maxID = 0;
		String nl;
		for (IShapeNode n : setnodes) {
			h = Math.max(h, n.getCenter().y / row);
			maxID = Math.max(maxID, n.getId());
			matrix[n.getCenter().y / row][n.getCenter().x / col] = n;
			nl = makeCommand(n, mapTag);
			map.put(n, nl);
		}
		for (String n : mapTag.values()) {
			sb.append(n);
		}
		sb.append("\\begin{tikzpicture}[auto]\n");
		sb.append("\\matrix[column sep=.1cm, row sep=.1cm]{\n");
		String space = "                ";
		StringBuffer sb2;
		for (int i = 0; i <= h; i++) {
			sb2 = new StringBuffer();
			boolean b;
			for (int j = 0; j < matrix[i].length; j++) {
				b = true;
				if (matrix[i][j] != null) {
					sb.append(sb2 + " " + map.get(matrix[i][j]) + " ");
					//sb.append(sb2 + " " + matrix[i][j].latex() + " ");
					sb2 = new StringBuffer();
					b = false;
				}
				if (j != matrix[i].length - 1) {
					if (b) sb2.append(space);
					sb2.append("&");
				}
			}
			sb.append("\\\\ \n");
		}
		sb.append("};\n");
		for (ICurveEdge c : setedges) {
			sb.append(c.latex() + "\n");
		}
		sb.append("\\end{tikzpicture}}\n");
		return sb.toString();
	}

	@Override
	protected void createControllers() {
		super.createControllers();
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent m) {
				if (m.getClickCount() == 2) {
					latex();
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent m) {
				Set<IShapeNode> set = new HashSet<IShapeNode>(selectedNodes);
				if (release != null)
					set.add(release);
				for (IShapeNode n : set) 
					center(n);
			}
		});
		
		//this.add
	}
	
	@Override
	public void paint(Graphics g) {
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

		super.paint(g);
		
		g2.setColor(grid_color);
		
		for (int y = col; y < this.getSize().width; y += col)
			g2.drawLine(y, 0, y, this.getSize().height);
		
		for (int x = row; x < this.getSize().height; x += row)
			g2.drawLine(0, x, this.getSize().width, x);
	}
	
	public static void main(String[] args) {
		GrapheGridPanel<IArc> gp = new GrapheGridPanel<IArc>(Color.white, new Graphe<IArc>(IArc.class));
		
		TestViewer.showOnFrame(gp);
	}

}
