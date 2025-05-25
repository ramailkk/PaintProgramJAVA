import java.awt.*;
import java.io.Serial;
import java.io.Serializable;

public class Triangle extends Shape implements Serializable {
    private Point origin;
    private Point end;
    private Color fill_color;
    private Color stroke_color;
    private int[] x;
    private int[] y;
    private int stroke;

    Triangle(Point origin, Point end, Color fill_color, Color stroke_color, int stroke){
        setOrigin(origin);
        setEnd(end);
        setStroke_color(stroke_color);
        setFill_color(fill_color);
        setStroke(stroke);

        x = new int[]{origin.x, 2*origin.x- end.x, end.x};
        y = new int[]{origin.y,end.y,end.y};
    }

    public int getStroke() {
        return stroke;
    }

    public void setStroke(int stroke) {
        this.stroke = stroke;
    }

    public void setOrigin(Point origin) {
        this.origin = origin;
    }


    public void setFill_color(Color fill_color) {
        this.fill_color = fill_color;
    }

    public void setStroke_color(Color stroke_color) {
        this.stroke_color = stroke_color;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public Point getOrigin() {
        return origin;
    }


    public Color getFill_color() {
        return fill_color;
    }

    public Color getStroke_color() {
        return stroke_color;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = ((Graphics2D)g);
        g2.setColor(getFill_color());
        g2.fillPolygon(x,y , 3);
        g2.setStroke(new BasicStroke(stroke));
        g2.setColor(stroke_color);
        g2.drawPolygon(x,y,3);
        g2.setStroke(new BasicStroke(0));
    }
}
