import java.awt.*;
import java.io.Serializable;

public class Pentagram extends Shape implements Serializable {
    private Point origin;
    private int size;
    private Color fill_color;
    private Color stroke_color;
    private int[] x = new int[10];
    private int[] y = new int[10];

    private int[] fill_x = new int[10];
    private int[] fill_y = new int[10];
    private int stroke;

    Pentagram(Point origin, int size, Color fill_color,Color stroke_color, int stroke){
        setOrigin(origin);
        setSize(size);
        setFill_color(fill_color);
        setStroke_color(stroke_color);
        setStroke(stroke);
        int count = 0;
        for (int i = 0; i < 5; i++) {
            x[count] = getOrigin().x + (int) (size * Math.cos(Math.toRadians(90 + 72 * (i + 1))));
            y[count] = getOrigin().y + (int) (size * Math.sin(Math.toRadians(90 + 72 * (i + 1))));
            count++;
            x[count] = getOrigin().x + (int) (size / 2 * Math.cos(Math.toRadians(126 + 72 * (i + 1))));
            y[count] = getOrigin().y + (int) (size / 2 * Math.sin(Math.toRadians(126 + 72 * (i + 1))));
            count++;
        }
        count = 0;
        for (int i = 0; i < 5; i++) {
            fill_x[count] = getOrigin().x + (int) ((size-stroke) * Math.cos(Math.toRadians(90 + 72 * (i + 1))));
            fill_y[count] = getOrigin().y + (int) ((size-stroke) * Math.sin(Math.toRadians(90 + 72 * (i + 1))));
            count++;
            fill_x[count] = getOrigin().x + (int) ((size-stroke) / 2 * Math.cos(Math.toRadians(126 + 72 * (i + 1))));
            fill_y[count] = getOrigin().y + (int) ((size-stroke) / 2 * Math.sin(Math.toRadians(126 + 72 * (i + 1))));
            count++;
        }
    }

    public void setOrigin(Point origin) {
        this.origin = origin;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setFill_color(Color fill_color) {
        this.fill_color = fill_color;
    }

    public void setStroke_color(Color stroke_color) {
        this.stroke_color = stroke_color;
    }

    public Point getOrigin() {
        return origin;
    }

    public int getSize() {
        return size;
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

    public int[] getFill_x() {
        return fill_x;
    }

    public int[] getFill_y() {
        return fill_y;
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
        g.fillPolygon(getX(),getY(),10);
        g.setColor(getFill_color());
        g.fillPolygon(fill_x,fill_y,10);
    }
}
