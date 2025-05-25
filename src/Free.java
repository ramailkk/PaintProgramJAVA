import java.awt.*;
import java.io.Serializable;

public class Free extends Shape implements Serializable {
    private Point origin;
    private Color stroke_color;
    private Point end;
    private int stroke;
    public Free(Point origin, Point end,Color stroke_color,int stroke){
        setOrigin(origin);
        setStroke_color(stroke_color);
        setStroke(stroke);
        setEnd(end);
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

    public void setOrigin(Point origin) {
        this.origin = origin;
    }

    public Color getStroke_color() {
        return stroke_color;
    }

    public void setStroke_color(Color stroke_color) {
        this.stroke_color = stroke_color;
    }

    public int getStroke() {
        return stroke;
    }

    public void setStroke(int stroke) {
        this.stroke = stroke;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = ((Graphics2D) g);
        g2.setColor(stroke_color);
        g2.setStroke(new BasicStroke(stroke));
        g2.drawLine(origin.x,origin.y,end.x,end.y);
        g2.setStroke(new BasicStroke(0));
    }
}
