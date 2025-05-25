import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.PrimitiveIterator;

public class StrokeToolbar extends Toolbar {
    private Button plus_button;
    private Button minus_button;
    private int stroke_width;
    private Tooltip tooltip;
    private ArrayList<Button> buttons = new ArrayList<>()   ;
    public StrokeToolbar(int x, int y){
        ImageIcon plus_button_dep = new ImageIcon("Src/Shapes/Depressed/plus_depressed.png");
        ImageIcon plus_button_pre = new ImageIcon("Src/Shapes/Pressed/plus_pressed.png");
        ImageIcon minus_button_dep = new ImageIcon("Src/Shapes/Depressed/minus_depressed.png");
        ImageIcon minus_button_pre = new ImageIcon("Src/Shapes/Pressed/minus_pressed.png");
        plus_button = new Button(x,y,48,48,plus_button_dep.getImage(),plus_button_pre.getImage());
        minus_button = new Button(x+48,y,48,48,minus_button_dep.getImage(),minus_button_pre.getImage());
        stroke_width = 1;
        buttons.add(plus_button);
        buttons.add(minus_button);
    }

    public void draw(Graphics g, ImageObserver that){
        Font font1 = g.getFont();
        Font font = new Font("Monospaced", Font.BOLD,15);
        g.setFont(font);
        g.setColor(Color.black);
        g.drawString("Stroke Width", getMinus_button().x + 50, getPlus_button().y + 24);
        g.drawString(String.valueOf(stroke_width)+ "px", getMinus_button().x+ 85, getPlus_button().y + 48);
        g.drawImage(plus_button.GetImage(), plus_button.x, plus_button.y, that);
        g.drawImage(minus_button.GetImage(),minus_button.x, minus_button.y, that);
        g.setFont(font1);
        tooltip.draw_tooltip(g);
    }
    public void IsClicked(int x, int y){
        if (getPlus_button().IsClicked(x,y)){
            setStroke_width(getStroke_width()+1);
        }
        else if ((getMinus_button().IsClicked(x,y))){
            setStroke_width(getStroke_width() - 1);
        }
        getPlus_button().IsClicked(x,y);
        getMinus_button().IsClicked(x,y);
    }
    public void confirm_tooltip(int x, int y){
        String type = "";
        for (Button value : buttons) {
            boolean breaker = tooltip.confirm_tooltip(x, y, value);
            Button button = tooltip.getSelected_button();
            if (button == plus_button)
                type = "Increase Stroke Width";
            else if (button == minus_button)
                type = "Decrease Stroke Width";
            tooltip.setMessage(type);
            if (breaker){
                return;
            }
        }
    }



    public Tooltip getTooltip() {
        return tooltip;
    }

    public void setTooltip(Tooltip tooltip) {
        this.tooltip = tooltip;
    }

    public Button getPlus_button() {
        return plus_button;
    }

    public void setPlus_button(Button plus_button) {
        this.plus_button = plus_button;
    }

    public Button getMinus_button() {
        return minus_button;
    }

    public void setMinus_button(Button minus_button) {
        this.minus_button = minus_button;
    }

    public int getStroke_width() {
        return stroke_width;
    }

    public void setStroke_width(int stroke_width) {
        if (stroke_width>=0 && stroke_width <=16)
            this.stroke_width = stroke_width;
    }
}

