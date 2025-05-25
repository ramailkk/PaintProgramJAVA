
import javax.swing.*;
import java.awt.*;
import java.awt.font.ImageGraphicAttribute;

public class Cell 
{

	protected Point topleft ;
	protected int width;
	protected int height;
	protected int stroke;
	protected Color cell_color;
	protected Color stroke_color;
	protected String text;
	protected Color text_color;
	
	protected int fontSize = 12;
	protected int fontStyle = Font.PLAIN;
	
	public Cell(int x, int y, int width, int height, Color cell_color, Color stroke_color, int stroke, String text,Color text_color)
	{
		topleft = new Point(x, y);
		this.width = width;
		this.height = height;
		this.cell_color = cell_color;
		this.stroke_color = stroke_color;
		this.stroke = stroke;
		this.text = text;
		this.text_color = text_color;
	}
	
	public Cell(Point p, int width, int height, Color cell_color, Color stroke_color, int stroke, String text, Color text_color)
	{
		this(p.x, p.y, width, height, cell_color, stroke_color, stroke, text, text_color);
	}
	
	public void paint(Graphics g)
	{
		g.setColor(stroke_color);
		g.fillRect(topleft.x - stroke , topleft.y - stroke, width + stroke*2, height + stroke*2);
		g.setColor(cell_color);
		g.fillRect(topleft.x , topleft.y, width, height);
		g.setColor(text_color);
		paintNormal(g);
		g.drawString(text, topleft.x + 5, topleft.y + (height + fontSize)/3);
	}
	
	public void paintNormal(Graphics g)
	{
		fontSize = (int)(Math.min(width,height)/2);
		fontStyle = Font.PLAIN;
		Font font= new Font("Times", fontStyle, fontSize);
		g.setFont(font);  		
	}
	
	protected void paintHighlighted(Graphics g)
	{
		fontSize = 16;
		fontStyle = Font.BOLD;
		Font font= new Font("Courier", fontStyle, fontSize);
		g.setFont(font);  
	}

	public Color getCell_color() {
		return cell_color;
	}

	public void setCell_color(Color cell_color) {
		this.cell_color = cell_color;
	}
}
