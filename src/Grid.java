import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.FileNotFoundException;

public class Grid {
    private Button grid_button;
    private ImageIcon grid_dep_off;
    private ImageIcon grid_pre_off;
    private ImageIcon grid_dep_2;
    private ImageIcon grid_pre_2;
    private ImageIcon grid_dep_4;
    private ImageIcon grid_pre_4;
    private ImageIcon grid_dep_8;
    private ImageIcon grid_pre_8;
    private ImageIcon grid_dep_16;
    private ImageIcon grid_pre_16;
    private ImageIcon grid_dep_32;
    private ImageIcon grid_pre_32;
    private ImageIcon grid_dep_64;
    private ImageIcon grid_pre_64;
    private int current;
    private int width;
    private int height;
    private boolean grid_active;
    public static Grid instance;
    private Tooltip tooltip;


    private Grid(int x, int y, int width, int height) {
        this.current = 1;
        this.width = width;
        this.height = height;
        grid_dep_off = new ImageIcon("src/Shapes/Depressed/grid_dep_off.png");
        grid_pre_off = new ImageIcon("src/Shapes/Pressed/grid_pre_off.png");
        grid_dep_2 = new ImageIcon("src/Shapes/Depressed/grid_dep_2.png");
        grid_pre_2 = new ImageIcon("src/Shapes/Pressed/grid_pre_2.png");
        grid_dep_4 = new ImageIcon("src/Shapes/Depressed/grid_dep_4.png");
        grid_pre_4 = new ImageIcon("src/Shapes/Pressed/grid_pre_4.png");
        grid_dep_8 = new ImageIcon("src/Shapes/Depressed/grid_dep_8.png");
        grid_pre_8 = new ImageIcon("src/Shapes/Pressed/grid_pre_8.png");
        grid_dep_16 = new ImageIcon("src/Shapes/Depressed/grid_dep_16.png");
        grid_pre_16 = new ImageIcon("src/Shapes/Pressed/grid_pre_16.png");
        grid_dep_32 = new ImageIcon("src/Shapes/Depressed/grid_dep_32.png");
        grid_pre_32 = new ImageIcon("src/Shapes/Pressed/grid_pre_32.png");
        grid_dep_64 = new ImageIcon("src/Shapes/Depressed/grid_dep_64.png");
        grid_pre_64 = new ImageIcon("src/Shapes/Pressed/grid_pre_64.png");

        grid_button = new Button(x, y, 128, 128, grid_dep_off.getImage(), grid_pre_off.getImage());
    }

    public void draw(Graphics g, ImageObserver that) {
        g.drawImage(grid_button.GetImage(), grid_button.x, grid_button.y, that);
    }

    public void IsClicked(int x, int y) throws FileNotFoundException {
        if (grid_button.IsClicked(x, y)) {
           current = current * 2;
           if (current == 2){
               grid_button.setImage_depressed(grid_dep_2.getImage());
               grid_button.setImage_pressed(grid_pre_2.getImage());
               grid_active = true;
           }
           else if (current == 4){
               grid_button.setImage_depressed(grid_dep_4.getImage());
               grid_button.setImage_pressed(grid_pre_4.getImage());
               grid_active = true;
           }
           else if (current == 8) {
               grid_button.setImage_depressed(grid_dep_8.getImage());
               grid_button.setImage_pressed(grid_pre_8.getImage());
               grid_active = true;
        }
           else if (current == 16){
               grid_button.setImage_depressed(grid_dep_16.getImage());
               grid_button.setImage_pressed(grid_pre_16.getImage());
               grid_active = true;
           }
           else if (current == 32) {
               grid_button.setImage_depressed(grid_dep_32.getImage());
               grid_button.setImage_pressed(grid_pre_32.getImage());
               grid_active = true;
        }
           else if (current == 64) {
               grid_button.setImage_depressed(grid_dep_64.getImage());
               grid_button.setImage_pressed(grid_pre_64.getImage());
               grid_active = true;
        }
           else {
               current = 1;
               grid_button.setImage_depressed(grid_dep_off.getImage());
               grid_button.setImage_pressed(grid_pre_off.getImage());
               grid_active = false;
           }
           grid_button.IsClicked(x,y);
        }
    }
    public static Grid getInstance(int x, int y, int width, int height) {
        if (instance == null){
            instance = new Grid(x,y,width,height);
        }
        return instance;
    }
    public void draw_grid(Graphics g) {
        if (grid_active) {
            int w;
            int h;
            if (current != 1) {
                w = 0 ;
                h = 125;
                for (int i = 0; i <=  (width/current); i++) {
                    g.drawLine(w, 125, w, 1000);
                    w += current;
                }
                for (int i = 0; i <= height/current ; i++) {
                    g.drawLine(0, h, 870, h);
                    h += current;
                }
            }
        }
    }

    public void confirm_tooltip(int x, int y){
        String type = "";
            tooltip.setSelected_button(grid_button);
                type = "Grid Button";
                tooltip.setMessage(type);
    }

    public Button getGrid_button() {
        return grid_button;
    }

    public void setGrid_button(Button grid_button) {
        this.grid_button = grid_button;
    }

    public Tooltip getTooltip() {
        return tooltip;
    }

    public void setTooltip(Tooltip tooltip) {
        this.tooltip = tooltip;
    }
}
