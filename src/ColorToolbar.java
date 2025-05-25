import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ColorToolbar extends Toolbar {
    private Color stroke_color = Color.WHITE;
    private Color fill_color = Color.WHITE;
    private final Color[] colors = {Color.BLACK, Color.DARK_GRAY, new Color(136, 0, 21), Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, new Color(0, 162, 232), Color.BLUE, new Color(163, 73, 164),
            Color.WHITE, Color.LIGHT_GRAY, new Color(185, 122, 87), Color.PINK, new Color(255, 201, 14), new Color(239, 228, 176), new Color(181, 230, 29), new Color(153, 217, 234), new Color(112, 146, 190), new Color(200, 191, 231)};
    private Button Fill_button;
    private Button Stroke_button;
    private Button rgb_button;
    private Button[] color_buttons = new Button[30];
    private Color[] user_colors = new Color[10];
    private String color_state;
    private Button current;
    private ArrayList<Button> buttons = new ArrayList<>();
    private Tooltip tooltip;

    public Button getCurrent() {
        return current;
    }

    public void setCurrent(Button current) {
        this.current = current;
    }

    public void setcurrent_color(Color color) {
        if (getCurrent() == getStroke_button())
            stroke_color = color;
        else if (getCurrent() == getFill_button()) {
            fill_color = color;
        }
    }

    public ColorToolbar(int x, int y) {
        color_state = "stroke";
        Arrays.fill(user_colors, Color.WHITE);
        ImageIcon dep = new ImageIcon("src/Shapes/Depressed/color_depressed.png");
        ImageIcon pre = new ImageIcon("src/Shapes/Pressed/color_pressed.png");
        Stroke_button = new Button(x, y, 24, 24, dep.getImage(), pre.getImage());
        ImageIcon rgb = new ImageIcon("src/Shapes/rgb.png");
        Fill_button = new Button(x + 24, y, 24, 24, dep.getImage(), pre.getImage());
        x = x + 48;
        int x1 = x;
        for (int i = 0; i < color_buttons.length; i++) {
            color_buttons[i] = new Button(x, y, 24, 24, dep.getImage(), pre.getImage());
            x = x + 24;
            if ((i + 1) % 10 == 0) {
                if ((i + 1) == color_buttons.length) {
                    rgb_button = new Button(x + 8, y - 30, 32, 32, rgb.getImage(), rgb.getImage());
                }
                x = x1;
                y = y + 24;
            }
        }
        setCurrent(getStroke_button());
        getStroke_button().setCurrent_image(getStroke_button().getImage_pressed());

        buttons.add(Stroke_button);
        buttons.add(Fill_button);
        buttons.add(rgb_button);
    }

    public void draw(Graphics g, ImageObserver that) {
        g.drawImage(Stroke_button.GetImage(), Stroke_button.x, Stroke_button.y, that);
        g.drawString("C1", Stroke_button.x, Stroke_button.y + 36);
        g.drawString("C2", Fill_button.x, Fill_button.y + 36);
        g.setColor(stroke_color);
        g.fillRect(Stroke_button.x, Stroke_button.y, 22, 22);
        g.drawImage(Fill_button.GetImage(), Fill_button.x, Fill_button.y, that);
        g.setColor(fill_color);
        g.fillRect(Fill_button.x, Fill_button.y, 22, 22);
        for (int i = 0; i < color_buttons.length - 10; i++) {
            g.drawImage(color_buttons[i].GetImage(), color_buttons[i].x, color_buttons[i].y, that);
            g.setColor(colors[i]);
            g.fillRect(color_buttons[i].x, color_buttons[i].y, 22, 22);
        }
        for (int i = 20; i < color_buttons.length; i++) {
            g.drawImage(color_buttons[i].GetImage(), color_buttons[i].x, color_buttons[i].y, that);
            g.setColor(user_colors[i - 20]);
            g.fillRect(color_buttons[i].x, color_buttons[i].y, 22, 22);
        }
        g.drawImage(rgb_button.GetImage(), rgb_button.x, rgb_button.y, that);
        tooltip.draw_tooltip(g);
    }

    public void IsClicked(int x, int y) {
        if ((x >= getStroke_button().x && x < getStroke_button().x + 24 && y >= getStroke_button().y && y < getStroke_button().y + 24)
                || (x >= getFill_button().x && x < getFill_button().x + 24 && y >= getFill_button().y && y < getFill_button().y + 24)) {
            getStroke_button().IsClicked(x, y);
            getFill_button().IsClicked(x, y);

            if (getStroke_button().IsClicked(x, y)) {
                color_state = "stroke";
                setCurrent(getStroke_button());
            } else if (getFill_button().IsClicked(x, y)) {
                setCurrent(getFill_button());
                color_state = "fill";
            }
        }
        reset(x, y);
        if (getRgb_button().IsClicked(x, y)) {
            JFrame frame = new JFrame("ColorGradient Editor v1.1");
            frame.setDefaultCloseOperation(3);
            GradientPanel panel = GradientPanel.getInstance(this);
            panel.setFrame(frame);
            if (getCurrent() == getStroke_button()) {
                panel.setColor(getStroke_color());
            } else
                panel.setColor(getFill_color());
            panel.setCustom_colors(getUser_colors());
            frame.add(panel);
            frame.pack();
            frame.addWindowListener(panel);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        if (find_normal_button(x, y) != null) {
            if (color_state.equals("stroke")) {
                setStroke_color(find_normal_button(x, y));
            } else if (color_state.equals("fill")) {
                setFill_color(find_normal_button(x, y));
            }
        } else if (find_user_color(x, y) != null) {
            if (color_state.equals("stroke")) {
                setStroke_color(find_user_color(x, y));
            } else if (color_state.equals("fill")) {
                setFill_color(find_user_color(x, y));
            }
        }
    }

    public void confirm_tooltip(int x, int y) {
        String type = "";
        for (int i = 0; i < buttons.size(); i++) {
            boolean bruh = tooltip.confirm_tooltip(x, y, buttons.get(i));
            if (tooltip.getSelected_button() == Stroke_button) {
                type = "StrokeColor";
            } else if (tooltip.getSelected_button() == Fill_button) {
                type = "FillColor";
            } else if (tooltip.getSelected_button() == rgb_button) {
                type = "Edit Colors";
            }
            tooltip.setMessage(type);
                if (bruh)
                    return;

        }
        for (int i = 0; i <color_buttons.length-10 ; i++) {
            boolean bruh = tooltip.confirm_tooltip(x,y,color_buttons[i]);
            type = "r: "+colors[i].getRed() + " g: " + colors[i].getGreen() + " b: " + colors[i].getBlue();
            tooltip.setMessage(type);
            if (bruh)
                return;
        }
        for (int i = color_buttons.length-10; i <color_buttons.length ; i++) {
            boolean bruh = tooltip.confirm_tooltip(x,y,color_buttons[i]);
            type = "r: "+user_colors[i-20].getRed() + " g: " + user_colors[i-20].getGreen() + " b: " + user_colors[i-20].getBlue();
            tooltip.setMessage(type);
            if (bruh)
                return;
        }
    }
    public Button getStroke_button() {
        return Stroke_button;
    }

    public void setStroke_button(Button stroke_button) {
        Stroke_button = stroke_button;
    }

    public Color getStroke_color() {
        return stroke_color;
    }

    public void setStroke_color(Color stroke_color) {
        this.stroke_color = stroke_color;
    }

    public Color getFill_color() {
        return fill_color;
    }

    public Color[] getColors() {
        return colors;
    }

    public Button[] getColor_buttons() {
        return color_buttons;
    }
    public Button getRgb_button() {
        return rgb_button;
    }

    public void setColor_buttons(Button[] color_buttons) {
        this.color_buttons = color_buttons;
    }


    public Color[] getUser_colors() {
        return user_colors;
    }

    public void setUser_colors(Color[] user_colors) {
        this.user_colors = user_colors;
    }

    public void setFill_color(Color fill_color) {
        this.fill_color = fill_color;
    }

    public Button getFill_button() {
        return Fill_button;
    }

    public void setFill_button(Button fill_button) {

        Fill_button = fill_button;
    }

    public Color find_normal_button(int x, int y) {
        for (int i = 0; i < color_buttons.length - 10; i++) {
            if (color_buttons[i].IsClicked(x, y)) {
                return colors[i];
            }
        }
        return null;
    }

    public Color find_user_color(int x, int y) {
        for (int i = 20; i < color_buttons.length; i++) {
            if (color_buttons[i].IsClicked(x,y)) {
                return user_colors[i-20];
            }
        }
        return null;
    }

    public void reset(int x, int y) {
        for (Button color_button : color_buttons) {
            color_button.IsClicked(x, y);
        }
    }

    public Tooltip getTooltip() {
        return tooltip;
    }

    public void setTooltip(Tooltip tooltip) {
        this.tooltip = tooltip;
    }
}
