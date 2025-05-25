import java.awt.*;

public class Hexagon extends Shape {
    private Point Origin;
    private int size;
    private Color fill_color;
    private Color stroke_color;
    private int[] x = new int[6];
    private int[] y = new int[6];
    private int[] fill_x = new int[6];
    private int[] fill_y = new int[6];
    private int stroke;

    Hexagon(Point origin, int size, Color fill_color, Color stroke_color, int stroke){
        setOrigin(origin);
        setSize(size);
        setFill_color(fill_color);
        setStroke_color(stroke_color);
        setStroke(stroke);
        for (int i =0 ; i<6; i++) {
            x[i] = getOrigin().x + (int) (-getSize() * Math.cos(Math.toRadians(90 + 60 * (i+1))));
            y[i] = getOrigin().y + (int) (-getSize() * Math.sin(Math.toRadians(90 + 60 * (i+1))));

            fill_x[i] = getOrigin().x + (int) (-(getSize()-stroke) * Math.cos(Math.toRadians(90 + 60 * (i+1))));
            fill_y[i] = getOrigin().y + (int) (-(getSize()-stroke) * Math.sin(Math.toRadians(90 + 60 * (i+1))));
        }
    }

    public void setOrigin(Point origin) {
        Origin = origin;
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
        return Origin;
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
        g.fillPolygon(getX(),getY(),6);
        g.setColor(getFill_color());
        g.fillPolygon(fill_x,fill_y,6);
    }
}
