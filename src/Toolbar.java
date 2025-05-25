import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.FileNotFoundException;

public abstract class Toolbar {
    abstract void draw(Graphics g, ImageObserver that);
    abstract void IsClicked(int x, int y) throws FileNotFoundException;
}
