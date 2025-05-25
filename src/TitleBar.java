import java.awt.*;
import java.io.FileNotFoundException;

public class TitleBar extends Cell
{
	private Cell closingButton;

	public TitleBar(int x, int y, int width,Color title_cell, Color title_stroke, Color title_text) throws FileNotFoundException {

		super(x, y, width, 30, title_cell, title_stroke, 3,"Data Field",title_text);
		closingButton =  new Cell(x + width - 24 , y + 3, 20, 20, Color.RED, Color.GRAY, 2, "X",Color.BLACK);
	}
	public TitleBar(int x ,int y , int width,String name, Color title_cell, Color title_stroke, Color title_text){
		super(x,y,width,30,title_cell,title_stroke,3,name,title_text);
		closingButton =  new Cell(x + width - 24 , y + 3, 20, 20, Color.RED, Color.GRAY, 2, "X",Color.BLACK);
	}

	public Cell getClosingButton() {
		return closingButton;
	}

	public void setClosingButton(Cell closingButton) {
		this.closingButton = closingButton;
	}

	public void paint(Graphics g)
	{
		g.setColor(stroke_color);
		g.fillRect(topleft.x - stroke , topleft.y - stroke, width + stroke*2, height + stroke*2);
		g.setColor(cell_color);
		g.fillRect(topleft.x , topleft.y, width, height);
		g.setColor(text_color);
		paintHighlighted(g);
		fontSize = 16;
		fontStyle = Font.BOLD;
		Font font= new Font("Courier", fontStyle, fontSize);
		g.setFont(font);
		g.drawString(text, topleft.x + 10, topleft.y + (height + fontSize)/3);
		closingButton.paint(g);
	}
}
