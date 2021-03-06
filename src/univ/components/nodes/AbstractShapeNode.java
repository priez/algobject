package univ.components.nodes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;

public abstract class AbstractShapeNode implements IShapeNode {

	private String name;
	protected int stringw, stringh, minwidth, minheight;
	private boolean doubl, draw;
	private ColorSetting fill, font;
	private int innerxsep, innerysep, outerxsep, outerysep;
	private double aspect;
	
	private static int ID = 0;
	private int id;
	
	private Point p;
	
	protected AbstractShapeNode(String name) {
		this(name, DRAW_DEF);
	}
	
	protected AbstractShapeNode(String name, boolean draw) {
		this(name, draw, DOUBLE_DEF);
	}
	
	protected AbstractShapeNode(String name, boolean draw, boolean doubl) {
		this(name, draw, doubl, FILL_DEF_COL);
	}
	
	protected AbstractShapeNode(String name, ColorSetting fill) {
		this(name, DRAW_DEF, fill);
	}
	
	protected AbstractShapeNode(String name, boolean draw, ColorSetting fill) {
		this(name, draw, DOUBLE_DEF, fill);
	}
	
	protected AbstractShapeNode(String name, boolean draw, boolean doubl, ColorSetting fill) {
		this(name, 
			DimSetting.MIN_WIDTH.defaultValue(), 
			DimSetting.MIN_HEIGHT.defaultValue(), 
			ASPECT_DEF, draw, 
			DOUBLE_DEF, 
			fill);
	}
	
	protected AbstractShapeNode(String name, int minsize) {
		this(name, minsize, minsize);
	}
	
	protected AbstractShapeNode(String name, int minwidth, int minheight) {
		this(name, minwidth, minheight, ASPECT_DEF);
	}
	
	protected AbstractShapeNode(String name, int minsize, double aspect) {
		this(name, minsize, minsize, aspect);
	}
	
	protected AbstractShapeNode(String name, int minwidth, int minheight, double aspect) {
		this(name, minwidth, minheight, aspect, DRAW_DEF, DOUBLE_DEF, FILL_DEF_COL);
	}
	
	protected AbstractShapeNode(
			String name, 
			int minwidth, int minheight, 
			double aspect, 
			boolean draw, boolean doubl, 
			ColorSetting fill) {
		this(name, 
			FONT_DEF_COL,
			minwidth, minheight, aspect, 
			SepSetting.INNER_XSEP.defaultValue(),
			SepSetting.INNER_YSEP.defaultValue(),
			SepSetting.OUTER_XSEP.defaultValue(),
			SepSetting.OUTER_YSEP.defaultValue(),
			draw, doubl, fill);
	}
	
	protected AbstractShapeNode(
			String name, ColorSetting col,
			int minwidth, int minheight, 
			double aspect,
			int innerxsep, int innerysep,
			int outerxsep, int outerysep,
			boolean draw, boolean doubl, 
			ColorSetting fill) {
		super();
		if (name == null)
			throw new IllegalArgumentException("L'alias et le nom ne doivent pas être nuls");
		if (minwidth < 0 || minheight < 0)
			throw new IllegalArgumentException("Les tailles ne doivent pas être négatives");
		
		if (innerxsep < 0 || innerysep < 0 || outerxsep < 0 || outerysep < 0) 
			throw new IllegalArgumentException("Les séparateurs ne doivent pas être négatif");
		
		if (aspect <= 0) throw new IllegalArgumentException("Le ratio doit être strictement supérieur à 0");
		
		if (fill == null || col == null) throw new IllegalArgumentException("Les couleurs ne doivent pas être nulles");
		
		this.id = ID++;
		
		this.name = name;
		
		this.font = col;
		
		this.minwidth = minwidth;
		this.minheight = minheight;
		this.aspect = aspect;
		
		this.innerxsep = innerxsep;
		this.innerysep = innerysep;
		this.outerxsep = outerxsep;
		this.outerysep = outerysep;
		
		this.draw = draw;
		this.doubl = doubl;
		
		this.fill = fill;
		
		this.p = new Point(0, 0);
		
		stringh = 0;
		stringw = 0;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getOuterXSep() {
		return outerxsep;
	}

	@Override
	public int getOuterYSep() {
		return outerysep;
	}

	@Override
	public int getInnerXSep() {
		return innerxsep;
	}

	@Override
	public int getInnerYSep() {
		return innerysep;
	}

	@Override
	public int getMinHeight() {
		return minheight;
	}

	@Override
	public int getMinWidth() {
		return minwidth;
	}

	@Override
	public double getShapeAspect() {
		return aspect;
	}
	
	@Override
	public void setOuterXSep(int s) {
		if (s < 0) throw new IllegalArgumentException();
		this.outerxsep = s;
	}

	@Override
	public void setOuterYSep(int s) {
		if (s < 0) throw new IllegalArgumentException();
		this.outerysep = s;
	}

	@Override
	public void setInnerXSep(int s) {
		if (s < 0) throw new IllegalArgumentException();
		this.innerxsep = s;
	}

	@Override
	public void setInnerYSep(int s) {
		if (s < 0) throw new IllegalArgumentException();
		this.innerysep = s;
	}

	@Override
	public void setMinHeight(int s) {
		if (s < 0) throw new IllegalArgumentException();
		this.minheight = s;
	}

	@Override
	public void setMinWidth(int s) {
		if (s < 0) throw new IllegalArgumentException();
		this.minwidth = s;
	}

	@Override
	public void setShapeAspect(double rat) {
		if (rat < 0) throw new IllegalArgumentException();
		this.aspect = rat;
	}

	@Override
	public void setOuterSep(int s) {
		setOuterXSep(s);
		setOuterYSep(s);
	}

	@Override
	public void setInnerSep(int s) {
		setInnerXSep(s);
		setInnerYSep(s);
	}

	@Override
	public void setMinDim(int s) {
		setMinHeight(s);
		setMinWidth(s);
	}

	@Override
	public void setMinDim(Dimension dim) {
		if (dim == null) throw new IllegalArgumentException();
		setMinHeight(dim.height);
		setMinWidth(dim.width);
	}

	@Override
	public boolean isDouble() {
		return doubl;
	}

	@Override
	public boolean isDraw() {
		return draw;
	}

	@Override
	public ColorSetting getFill() {
		return fill;
	}

	@Override
	public void setDouble(boolean b) {
		doubl = b;
	}

	@Override
	public void setDraw(boolean b) {
		draw = b;
	}

	@Override
	public void setFill(ColorSetting c) {
		if (c == null) throw new IllegalArgumentException();
		fill = c;
	}

	@Override
	public void setPosition(Point p) {
		this.p = p;
	}

	@Override
	public Point getPosition() {
		return p;
	}
	
	@Override
	public Color getFontColor() {
		return font.col();
	}

	@Override
	public void setFontColor(ColorSetting c) {
		if (c == null) throw new IllegalArgumentException();
		font = c;
	}

	protected abstract void fillShape(Graphics g, int x, int y, int h, int w);
	protected abstract void drawShape(Graphics g, int x, int y, int h, int w);
	protected abstract void drawString(Graphics g, int x, int y, int h, int w);

	@Override
	public void draw(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(
			RenderingHints.KEY_ANTIALIASING, 
			RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(
			RenderingHints.KEY_RENDERING, 
			RenderingHints.VALUE_RENDER_QUALITY);
		stringw = g.getFontMetrics().stringWidth(getName());
		stringh = g.getFontMetrics().getHeight();
		int x = getPosition().x,
			y = getPosition().y,
			h = Math.max(
				getMinHeight(), 
				stringh + 2 * getInnerYSep()),
			w = Math.max(
				getMinWidth(), 
				stringw + 2 * getInnerXSep());
		Color s = g.getColor();
		g2.setColor(Color.white);
		if (isDouble())
			fillShape(g2,
				x - getOuterXSep(), 
				y - getOuterYSep(), 
				h + 2 * getOuterYSep(), 
				w + 2 * getOuterXSep());
		g2.setColor(getFill().col());
		fillShape(g2, x, y, h, w);
		g2.setColor(getFontColor());
		drawString(g2, x, y, h, w);
		g2.setColor(Color.black);
		if (isDraw()) {
			drawShape(g2, x, y, h, w);
			if (isDouble())
				drawShape(g2, x - getOuterXSep(), y - getOuterYSep(), 
						h + 2 * getOuterYSep(), w + 2 * getOuterXSep());
		}
		// ...
		g2.setColor(s);
	}
	
	@Override
	public abstract Point getCenter();
	
	@Override
	public Dimension getDimension() {
		return new Dimension(
			2 * innerxsep + 2 * outerxsep + Math.max(stringw, minwidth), 
			2 * innerysep + 2 * outerysep + Math.max(stringh, minheight));
	}
	
	@Override
	public boolean isOn(Point point) {
		int dx = point.x - getPosition().x,
			dy = point.y - getPosition().y;
		return (dx >= 0 && dy >= 0 && dx <= getDimension().width && dy <= getDimension().height);
	}
	
	@Override
	public boolean isIn(Rectangle rec) {
		int dx = getPosition().x - rec.x,
			dy = getPosition().y - rec.y;
		if (dx > 0)
			dx = rec.width - dx;
		if (dy > 0)
			dy = rec.height - dy;
		return dx > 0 && dy > 0;
	}
	
	@Override
	public String latex() {
		return "\\node (" + id + ") [" + option() + 
				", fill=" + this.getFill() + 
				", text=" + this.font + 
				(draw ? ", draw" : "" ) + "] {" + (name != null ? (name.isEmpty() ? "" : "$" + name + "$") : "") + "};";
	}

	abstract String option();
	
	public int getId() {
		return id;
	}
	
}