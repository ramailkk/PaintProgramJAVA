import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

class Circle extends Shape implements Serializable
{
    private int	size;
    private Point Origin;
    private Color fill_color;
    private Color stroke_color;
    private int stroke;

    Circle(Point Origin,int iSize,Color fill_color,Color stroke_color,int stroke)
    {
        setOrigin(Origin);
        setSize(iSize);
        setFill_color(fill_color);
        setStroke_color(stroke_color);
        setStroke(stroke);
    }

    public int getStroke() {
        return stroke;
    }

    public void setStroke(int stroke) {
        this.stroke = stroke;
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

    void setSize(int iSize) {
        size = Math.max(iSize, 1);
    }

    public void setOrigin(Point origin) {
        Origin = origin;
    }

    public Point getOrigin() {
        return Origin;
    }

    int getSize()
    {
        return size;
    }


    public void draw(Graphics g)
    {
        g.setColor(getStroke_color());
        g.fillOval(getOrigin().x - getSize()/2 - stroke , getOrigin().y - getSize()/2 - stroke,  getSize() + stroke*2, getSize() + stroke*2);
        g.setColor(getFill_color());
        g.fillOval(getOrigin().x - getSize()/2 ,getOrigin().y - getSize()/2,getSize(),getSize());
    }
}