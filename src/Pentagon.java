import java.awt.*;
import java.io.Serializable;

public class Pentagon extends Shape implements Serializable {
    private int size;
    private Point Origin;
    private Color fill_color;
    private Color stroke_color;
    private int[] x = new int[5];
    private int[] y = new int[5];

    private int[] stroke_x = new int[5];
    private int[] stroke_y = new int[5];
    private int stroke;

    Pentagon(Point origin, int size, Color fill_color, Color stroke_color, int stroke){
        setOrigin(origin);
        setSize(size);
        setFill_color(fill_color);
        setStroke_color(stroke_color);
        setStroke(stroke);

        for (int i = 0; i < 5 ; i++) {
            x[i] = getOrigin().x + (int) (getSize()* Math.cos(Math.toRadians(90 + 72 * (i+1))));
            y[i] = getOrigin().y + (int) (getSize() * Math.sin(Math.toRadians(90 + 72 * (i+1))));

            stroke_x[i] = (getOrigin().x) + (int) ((getSize()-stroke)* Math.cos(Math.toRadians(90 + 72 * (i+1))));
            stroke_y[i] = (getOrigin().y) + (int) ((getSize()-stroke) * Math.sin(Math.toRadians(90 + 72 * (i+1))));
        }
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setOrigin(Point origin) {
        Origin = origin;
    }

    public void setFill_color(Color fill_color) {
        this.fill_color = fill_color;
    }

    public void setStroke_color(Color stroke_color) {
        this.stroke_color = stroke_color;
    }

    public int getSize() {
        return size;
    }

    public Point getOrigin() {
        return Origin;
    }

    public Color getFill_color() {
        return fill_color;
    }

    public Color getStroke_color() {
        return stroke_color;
    }

    public int[] getX() {
        return x;
    }

    public int[] getY() {
        return y;
    }

    public int getStroke() {
        return stroke;
    }

    public void setStroke(int stroke) {
        this.stroke = stroke;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getStroke_color());
        g.fillPolygon(getX(),getY(),5);
        g.setColor(getFill_color());
        g.fillPolygon(stroke_x,stroke_y,5);
    }
}
