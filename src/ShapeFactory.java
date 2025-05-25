import java.awt.*;

public class ShapeFactory {
    public Shape create_shape(String type, int x1, int y1, int x2, int y2, Color fill_color, Color stroke_color, int stroke){
        Shape shape = null;
        int distance = (int)(Math.sqrt((Math.pow((x1-x2),2)+Math.pow((y1-y2),2))));
        switch (type) {
            case "circle" ->
                    shape = new Circle(new Point((x1 + x2) / 2, (y1 + y2) / 2), distance, fill_color, stroke_color, stroke);
            case "rectangle" ->
                    shape = new Rectangle(new Point(Math.min(x1, x2), Math.min(y1, y2)), Math.abs(x1 - x2), Math.abs(y1 - y2), stroke_color, fill_color, stroke);
            case "triangle" ->
                    shape = new Triangle(new Point(x1, y1), new Point(x2, y2), fill_color, stroke_color, stroke);
            case "righttriangle" ->
                    shape = new RightTriangle(new Point(x1, y1), new Point(x2, y2), fill_color, stroke_color, stroke);
            case "pentagon" ->
                    shape = new Pentagon(new Point((x1 + x2) / 2, (y1 + y2) / 2), distance / 2, fill_color, stroke_color, stroke);
            case "hexagon" ->
                    shape = new Hexagon(new Point((x1 + x2) / 2, (y1 + y2) / 2), distance / 2, fill_color, stroke_color, stroke);
            case "pentagram" ->
                    shape = new Pentagram(new Point((x1 + x2) / 2, (y1 + y2) / 2), distance / 2, fill_color, stroke_color, stroke);
        }
        return shape;
    }
}
