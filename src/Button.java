import javax.swing.*;
import java.awt.Image;

public class Button 
{
	public int x;
	public int y;
	private int width;
	private int height;
	private Image image_depressed;
	private Image image_pressed;
	private Image current_image;
	private boolean pressed;
	private boolean tooltip;
	
	public Button(int x, int y, int width, int height, Image i_depressed, Image i_pressed)
	{
		tooltip = false;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		image_depressed = i_depressed;
		image_pressed = i_pressed;
		current_image = i_depressed;
	}	
		
	public Image GetImage() 
	{
		return current_image;
	}

	public void setCurrent_image(Image current_image) {
		this.current_image = current_image;
	}

	public Image getImage_depressed() {
		return image_depressed;
	}

	public void setImage_depressed(Image image_depressed) {
		this.image_depressed = image_depressed;
	}

	public Image getImage_pressed(){
		return image_pressed;
	}


	public boolean IsPressed()
	{
		return pressed;
	}

	public boolean Press(int key, int condition){
		{
			if (key == condition) {
				pressed = true;
				current_image = image_pressed;
				return true;
			} else {
				current_image = image_depressed;
				return false;
			}
		}
	}

	public void depress(){
		if (pressed)
			current_image = image_depressed;
	}

	
	public void SetPressed(boolean pressed)
	{
		this.pressed = pressed;
	}
	public boolean IsClicked(int x, int y) {
		if (x > this.x && x < this.x + width && y > this.y && y < this.y + height) {
			pressed = true;
			current_image = image_pressed;
			return true;
		} else {
			current_image = image_depressed;
			return false;
	}
	}
	public void isReleased(){
		pressed = false;
		current_image = image_depressed;
	}

	public void setImage_pressed(Image image_pressed) {
		this.image_pressed = image_pressed;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
