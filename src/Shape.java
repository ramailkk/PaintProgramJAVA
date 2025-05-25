import java.awt.Graphics;
import java.io.Serializable;

abstract class Shape implements Serializable
{
    abstract public void draw(Graphics g);
}