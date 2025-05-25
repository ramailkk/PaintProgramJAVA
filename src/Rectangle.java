import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

class Rectangle extends Shape implements Serializable
{
	private Point point;
	private Color fill_color;
	private Color stroke_color;
	private int height;
	private int width;
	private int stroke;
	Rectangle(Point point,int width, int height,Color fill_color,Color stroke_color,int stroke)
	{
		setPoint(point);
		setWidth(width);
		setHeight(height);
		setStroke_color(stroke_color);
		setFill_color(fill_color);
		setStroke(stroke);
	}

	public int getStroke() {
		return stroke;
	}

	public void setStroke(int stroke) {
		this.stroke = stroke;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public Point getPoint() {
		return point;
	}

	public Color getFill_color() {
		return fill_color;
	}

	public void setFill_color(Color fill_color) {
		this.fill_color = fill_color;
	}

	public Color getStroke_color() {
		return stroke_color;
	}

	public void setStroke_color(Color stroke_color) {
		this.stroke_color = stroke_color;
	}

	public void draw(Graphics g)
	{
		g.setColor(getFill_color());
		g.fillRect(getPoint().x- stroke , getPoint().y - stroke, width + stroke*2, height + stroke*2);
		g.setColor(getStroke_color());
		g.fillRect(getPoint().x,getPoint().y,getWidth(),getHeight());
	}
}