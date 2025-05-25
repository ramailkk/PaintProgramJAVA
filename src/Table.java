import java.awt.*;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Table {
	private int rows;
	private int columns;
	private int x;
	private int y;


	private Cell[] cells;
	private TitleBar titleBar;
	private Button[] buttons;
	private Button closing;

	public Table()
	{

	}

	public Table(int x, int y, int rows, int columns,Color cell_color,Color stroke_color,
				 Color text_color, Color first_cell, Color first_stroke,
				 Color first_text, Color title_cell, Color title_stroke,Color title_text,
				 String[][] info) {
		titleBar = new TitleBar(x, y, 800, "Double Click to Open (Bigger Files take time to open so Please Wait)", title_cell, title_stroke, title_text);
		this.x = x;
		this.y = y + 30;
		this.rows = rows;
		this.columns = columns;
		this.buttons = new Button[info.length];
		cells = new Cell[rows * columns];
		if (rows < 16){
			int h = 0;
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					if (i == 0)
						cells[i * columns + j] = new Cell(this.x + 800 / columns * j, this.y + h, 800 / columns, 50, first_cell, first_stroke, 3, info[i][j], first_text);
					else
						cells[i * columns + j] = new Cell(this.x + 800 / columns * j, this.y + h, 800 / columns, 50, cell_color, stroke_color, 3, info[i][j], text_color);
				}
				buttons[i] = new Button(this.x + 800 / columns * 0, this.y + h, 800, 50, null, null);
				closing = new Button(titleBar.getClosingButton().topleft.x, titleBar.getClosingButton().topleft.y, titleBar.getClosingButton().width, titleBar.getClosingButton().height, null, null);
				h += 50;
			}
	}
		else {
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					if (i == 0)
						cells[i * columns + j] = new Cell(this.x + 800 / columns * j, this.y + i * 600/rows, 800 / columns, 600/rows, first_cell, first_stroke, 3, info[i][j], first_text);
					else
						cells[i * columns + j] = new Cell(this.x + 800 / columns * j, this.y + i * 600/rows, 800 / columns, 600/rows, cell_color, stroke_color, 3, info[i][j], text_color);
				}
				buttons[i] = new Button(this.x + 800 / columns * 0, this.y +  i * 600/rows, 800, 600/rows, null, null);
				closing = new Button(titleBar.getClosingButton().topleft.x, titleBar.getClosingButton().topleft.y, titleBar.getClosingButton().width, titleBar.getClosingButton().height, null, null);
			}
		}


		}

	public Cell[] getCells() {
		return cells;
	}

	public void setCells(Cell[] cells) {
		this.cells = cells;
	}

	public void paint(Graphics g)
	{
		titleBar.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		for(int i = 0; i < rows * columns; i++)
		{
			cells[i].paint(g);
		}
		int w = 0;
		for (int i = 0; i <= columns; i++) {

			g2.setStroke(new BasicStroke(3));
			g2.setColor(Color.black);
			g2.drawLine(w,0,w,800);
			g2.setStroke(new BasicStroke(0));
			w += 800/columns;
		}
	}

	public Button[] getButtons() {
		return buttons;
	}

	public void setButtons(Button[] buttons) {
		this.buttons = buttons;
	}

	public Button getClosing() {
		return closing;
	}

	public void setClosing(Button closing) {
		this.closing = closing;
	}
}
