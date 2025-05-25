import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.EventListener;

public class ShapeToolbar extends Toolbar {
    private Button circle_button;
    private Button rectangle_button;
    private Button triangle_button;
    private Button righttriangle_button;
    private Button pentagon_button;
    private Button hexagon_button;
    private Button pentagram_button;
    private Button free_button;
    private ArrayList<Button> buttons = new ArrayList<>();

    private String type;
    private Tooltip tooltip;

    public ShapeToolbar(int x, int y){
        ImageIcon circle_button_dep = new ImageIcon("src/Shapes/Depressed/circle_depressed.png");
        ImageIcon circle_button_pre = new ImageIcon("src/Shapes/Pressed/circle_pressed.png");
        ImageIcon rectangle_button_dep = new ImageIcon("src/Shapes/Depressed/rectangle_depressed.png");
        ImageIcon rectangle_button_pre = new ImageIcon("src/Shapes/Pressed/rectangle_pressed.png");
        ImageIcon triangle_button_dep = new ImageIcon("src/Shapes/Depressed/triangle_depressed.png");
        ImageIcon triangle_button_pre = new ImageIcon("src/Shapes/Pressed/triangle_pressed.png");
        ImageIcon pentagon_button_dep = new ImageIcon("src/Shapes/Depressed/pentagon_depressed.png");
        ImageIcon pentagon_button_pre = new ImageIcon("src/Shapes/Pressed/pentagon_pressed.png");
        ImageIcon hexagon_button_dep = new ImageIcon("src/Shapes/Depressed/hexagon_depressed.png");
        ImageIcon hexagon_button_pre = new ImageIcon("src/Shapes/Pressed/hexagon_pressed.png");
        ImageIcon pentagram_button_dep = new ImageIcon("src/Shapes/Depressed/pentagram_depressed.png");
        ImageIcon pentagram_button_pre = new ImageIcon("src/Shapes/Pressed/pentagram_pressed.png");
        ImageIcon righttriangle_button_dep = new ImageIcon("src/Shapes/Depressed/righttriangle_depressed.png");
        ImageIcon righttriangle_button_pre = new ImageIcon("src/Shapes/Pressed/righttriangle_pressed.png");
        ImageIcon free_button_dep = new ImageIcon("src/Shapes/Depressed/free_dep.png");
        ImageIcon free_button_pre = new ImageIcon("src/Shapes/Pressed/free_pre.png");

        circle_button = new Button(x,y,64,64,circle_button_dep.getImage(),circle_button_pre.getImage());
        rectangle_button = new Button(circle_button.x + 64, y, 64,64,rectangle_button_dep.getImage(),rectangle_button_pre.getImage());
        triangle_button = new Button(rectangle_button.x + 64, y,64,64,triangle_button_dep.getImage(),triangle_button_pre.getImage());
        righttriangle_button = new Button(triangle_button.x + 64,y,64, 64,righttriangle_button_dep.getImage(),righttriangle_button_pre.getImage());
        pentagon_button = new Button(x,y+64,64,64,pentagon_button_dep.getImage(),pentagon_button_pre.getImage());
        hexagon_button = new Button(pentagon_button.x + 64,y+64,64,64,hexagon_button_dep.getImage(),hexagon_button_pre.getImage());
        pentagram_button = new Button(hexagon_button.x + 64,y+64,64,64,pentagram_button_dep.getImage(),pentagram_button_pre.getImage());
        free_button = new Button(pentagram_button.x+64,y+64,64,64,free_button_dep.getImage(),free_button_pre.getImage());

        buttons.add(circle_button);
        buttons.add(rectangle_button);
        buttons.add(triangle_button);
        buttons.add(righttriangle_button);
        buttons.add(pentagon_button);
        buttons.add(hexagon_button);
        buttons.add(pentagram_button);
        buttons.add(free_button);
    }

    public void draw(Graphics g, ImageObserver that){
        g.drawImage(circle_button.GetImage(),circle_button.x,circle_button.y,that);
        g.drawImage(rectangle_button.GetImage(), rectangle_button.x, rectangle_button.y, that);
        g.drawImage(triangle_button.GetImage(), triangle_button.x, triangle_button.y, that);
        g.drawImage(righttriangle_button.GetImage(), righttriangle_button.x, righttriangle_button.y, that);
        g.drawImage(pentagon_button.GetImage(),pentagon_button.x, pentagon_button.y, that);
        g.drawImage(hexagon_button.GetImage(),hexagon_button.x,hexagon_button.y,that);
        g.drawImage(pentagram_button.GetImage(), pentagram_button.x, pentagram_button.y, that);
        g.drawImage(free_button.GetImage(), free_button.x, free_button.y, that);
        tooltip.draw_tooltip(g);
    }

    public void IsClicked(int x, int y){
        if (x>=600 && x<=850 && y>=0 && y<=150) {
            reset(x, y);
            if (getCircle_button().IsClicked(x, y)) {
                setType("circle");
            } else if (getRectangle_button().IsClicked(x, y)) {
                setType("rectangle");
            } else if (getTriangle_button().IsClicked(x, y)) {
                setType("triangle");
            } else if (getRighttriangle_button().IsClicked(x, y)) {
                setType("righttriangle");
            } else if (getPentagon_button().IsClicked(x, y)) {
                setType("pentagon");
            } else if (getHexagon_button().IsClicked(x, y)) {
                setType("hexagon");
            } else if (getPentagram_button().IsClicked(x, y)) {
                setType("pentagram");
            }
            else if (getFree_button().IsClicked(x,y)){
                setType("free");
            }
        }
    }

    public void confirm_tooltip(int x, int y){
        String type = "";
        for (Button value : buttons) {
            boolean breaker = tooltip.confirm_tooltip(x, y, value);
            Button button = tooltip.getSelected_button();
            if (button == circle_button)
                type = "Draw Circle";
            else if (button == rectangle_button)
                type = "Draw Rectangle";
            else if (button == triangle_button)
                type = "Draw Triangle";
            else if (button == righttriangle_button)
                type = "Draw Right triangle";
            else if (button == pentagon_button)
                type = "Draw Pentagon";
            else if (button == pentagram_button)
                type = "Draw Pentagram";
            else if (button == hexagon_button)
                type = "Draw Hexagon";
            else if (button == free_button)
                type = "Free Drawing";
            tooltip.setMessage(type);
            if (breaker)
                return;
        }
        }

    public void reset(int x, int y){
        getCircle_button().IsClicked(x, y);
        getRectangle_button().IsClicked(x, y);
        getTriangle_button().IsClicked(x, y);
        getRighttriangle_button().IsClicked(x, y);
        getPentagon_button().IsClicked(x, y);
        getHexagon_button().IsClicked(x, y);
        getPentagram_button().IsClicked(x, y);
        getFree_button().IsClicked(x,y);
    }
    public Button getCircle_button() {
        return circle_button;
    }

    public void setCircle_button(Button circle_button) {
        this.circle_button = circle_button;
    }

    public Button getRectangle_button() {
        return rectangle_button;
    }

    public void setRectangle_button(Button rectangle_button) {
        this.rectangle_button = rectangle_button;
    }

    public Button getFree_button() {
        return free_button;
    }

    public void setFree_button(Button free_button) {
        this.free_button = free_button;
    }

    public Button getTriangle_button() {
        return triangle_button;
    }

    public void setTriangle_button(Button triangle_button) {
        this.triangle_button = triangle_button;
    }

    public Button getRighttriangle_button() {
        return righttriangle_button;
    }

    public void setRighttriangle_button(Button righttriangle_button) {
        this.righttriangle_button = righttriangle_button;
    }

    public Button getPentagon_button() {
        return pentagon_button;
    }

    public void setPentagon_button(Button pentagon_button) {
        this.pentagon_button = pentagon_button;
    }

    public Button getHexagon_button() {
        return hexagon_button;
    }

    public void setHexagon_button(Button hexagon_button) {
        this.hexagon_button = hexagon_button;
    }

    public Button getPentagram_button() {
        return pentagram_button;
    }

    public void setPentagram_button(Button pentagram_button) {
        this.pentagram_button = pentagram_button;
    }

    public String getType() {
        return type;
    }

    public Tooltip getTooltip() {
        return tooltip;
    }

    public void setTooltip(Tooltip tooltip) {
        this.tooltip = tooltip;
    }

    public void setType(String type) {
        this.type = type;
    }
}

