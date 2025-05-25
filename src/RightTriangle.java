import java.awt.*;
import java.io.Serializable;

public class RightTriangle extends Shape implements Serializable {
    private Point Origin;
    private Point End;
    private Color fill_color;
    private Color stroke_color;
    private int[] x;
    private int[] y;
    private int stroke;

    RightTriangle(Point origin, Point end, Color fill_color, Color stroke_color, int stroke){
        setOrigin(origin);
        setEnd(end);
        setFill_color(fill_color);
        setStroke_color(stroke_color);
        setStroke(stroke);

        x = new int[]{getOrigin().x, getOrigin().x, getEnd().x};
        y = new int[]{getOrigin().y, getEnd().y, getEnd().y};
    }

    public void setOrigin(Point origin) {
        Origin = origin;
    }

    public void setEnd(Point end) {
        End = end;
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

    public Point getEnd() {
        return End;
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
        Graphics2D g2 = ((Graphics2D)g);
        g2.setColor(getFill_color());
        g2.fillPolygon(getX(), getY(),3);
        g2.setStroke(new BasicStroke(stroke));
        g2.setColor(getStroke_color());
        g2.drawPolygon(getX(),getY(),3);
        g2.setStroke(new BasicStroke(0));
    }
}
