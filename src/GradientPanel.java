import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class GradientPanel extends JPanel implements MouseListener, MouseMotionListener, WindowListener, ActionListener {
    private int x;
    private int y;
    private final int DEFAULT_WIDTH  = 550;
    private final int DEFAULT_HEIGHT = 500;
    private Graphics g;
    private Color color = Color.white;
    private String red = String.valueOf(color.getRed());
    private String green = String.valueOf(color.getGreen());
    private String blue = String.valueOf(color.getBlue());
    private int custom_index = 0;
    double value = 1.00;
    JFrame frame;
    private int x1 = 250;
    private int y1 = 50;
    Button closing_button;
    Cell closingButton;

    private final Color[] basic_colors = {new Color(200,191,231),new Color(255,255,128),new Color(128,255,128),new Color(0,255,128),new Color(128,255,255),new Color(0,128,255),new Color(255,128,192),new Color(255,128,255),
    new Color(255,0,0),new Color(255,255,0),new Color(128,255,0),new Color(0,255,64),new Color(0,255,255),new Color(0,128,192),new Color(128,128,192),new Color(255,0,255),
    new Color(128,64,64),new Color(255,128,64),new Color(0,255,0),new Color(0,128,128),new Color(0,64,128),new Color(128,128,255),new Color(128,0,64),new Color(255,0,128),
    new Color(128,0,0),new Color(255,128,0),new Color(0,128,0),new Color(0,128,64), new Color(0,0,255),new Color(0,0,160),new Color(128,0,128),new Color(128,0,255),
    new Color(64,0,0),new Color(128,64,0),new Color(0,64,0),new Color(0,64,64),new Color(0,0,128),new Color(0,0,64),new Color(64,0,64),new Color(64,0,128),
    new Color(0,0,0),new Color(128,128,0),new Color(128,128,64),new Color(128,128,128),new Color(64,128,128),new Color(192,192,192),new Color(64,0,64),new Color(255,255,255)};
    private Button[] basic_buttons = new Button[48];

    private Button[] custom_buttons = new Button[10];
    public Color[] getCustom_colors() {
        return custom_colors;
    }

    public void setCustom_colors(Color[] custom_colors) {
        this.custom_colors = custom_colors;
    }

    private Color[] custom_colors = new Color[10];

    private Button add_to_custom_button;
    private ColorToolbar colorToolbar;

    private static GradientPanel instance;

    private GradientPanel(ColorToolbar colorToolbar){
        this.colorToolbar = colorToolbar;
            setBackground( new Color(225,225,225));
            setPreferredSize( new Dimension( DEFAULT_WIDTH, DEFAULT_HEIGHT ) );
            this.addMouseListener( this);
            this.addMouseMotionListener(this);
            setFocusable(true);
            Arrays.fill(custom_colors,Color.WHITE);
            ImageIcon dep = new ImageIcon("src/Shapes/Depressed/color_depressed.png");
            ImageIcon pre = new ImageIcon("src/Shapes/Pressed/color_pressed.png");
            ImageIcon add_dep = new ImageIcon("src/Shapes/Depressed/add_custom_color_depressed.png");
            ImageIcon add_pre = new ImageIcon("src/Shapes/Pressed/add_custom_color_pressed.png");

            add_to_custom_button = new Button(220,400,306,43,add_dep.getImage(),add_pre.getImage());
            int x =5;
            int y =100;
        for(int i = 0;i<basic_buttons.length;i++){
            basic_buttons[i] = new Button(x,y,24,24,dep.getImage(),pre.getImage());
            x = x + 28;
            if (((i+1) % 8) == 0){
                x = 5;
                y = y + 28;
            }
        }

        y = 285;
        for (int i =0; i<custom_buttons.length;i++){
            custom_buttons[i] = new Button(x,y,24,24,dep.getImage(),pre.getImage());
            x = x + 28;
            if (((i+1)%8) == 0){
                x = 5;
                y = y+28;
            }
        }
        closingButton =  new Cell(520 , 10, 20, 20, Color.RED, Color.GRAY, 2, "X",Color.BLACK);
        closing_button = new Button(closingButton.topleft.x, closingButton.topleft.y, closingButton.width, closingButton.height,null, null);
    }

    public static GradientPanel getInstance(ColorToolbar colorToolbar){
        if (instance == null){
             instance = new GradientPanel(colorToolbar);
        }
        return instance;
    }

    private void draw(){
        g = getGraphics();
        for (int i = 0; i<basic_buttons.length;i++){
            g.drawImage(basic_buttons[i].GetImage(), basic_buttons[i].x,basic_buttons[i].y,this);
            g.setColor(basic_colors[i]);
            g.fillRect(basic_buttons[i].x, basic_buttons[i].y, 22, 22);
        }
        for (int i =0; i<custom_buttons.length;i++){
            g.drawImage(custom_buttons[i].GetImage(), custom_buttons[i].x, custom_buttons[i].y,this);
            g.setColor(custom_colors[i]);
            g.fillRect(custom_buttons[i].x,custom_buttons[i].y,22,22);
        }
        g.drawImage(add_to_custom_button.GetImage(), add_to_custom_button.x, add_to_custom_button.y, this);}

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        this.red = String.valueOf(color.getRed());
        this.green = String.valueOf(color.getGreen());
        this.blue = String.valueOf(color.getBlue());
    }
    private void setUpDrawingGraphics() {
        g = getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,550,40);
        g.setColor(Color.WHITE);
        Font font = new Font("Times New Roman",Font.BOLD,30);
        g.setFont(font);
        g.drawString("Edit Colors",20,30);
        closingButton.paint(g);
        g.setColor(Color.BLACK);
        g.setFont(font);
        g.drawString("Basic Colors", 20,70);
        for (int sat = 0; sat<=240;sat++){
            for (int hue = 239; hue>=0; hue--){
                double value = 0.89;
                double red = 0;
                double green = 0;
                double blue = 0;
                double H;
                double M = 255 * value;
                double m = M*(1-((double)(sat)/240.0));
                H = (double)(hue*360)/240;
                double z = (M-m)*(1-Math.abs((H/60 % 2)-1));
                if (H<60){
                    red = M;
                    green = z+m;
                    blue = m;
                }
                else if (H <120){
                    red = z + m;
                    green = M;
                    blue = m;
                }
                else if (H<180){
                    red = m;
                    green = M;
                    blue = z + m;
                }
                else if (H< 240){
                    red = m;
                    green = z + m;
                    blue = M;
                }
                else if (H< 300){
                    red = z + m;
                    green = m;
                    blue = M;
                }
                else if (H<360) {
                    red = M;
                    green = m;
                    blue = z+m;
                }
                g.setColor(new Color((int) red,(int) green,(int) blue));
                g.fillRect(hue + 250, sat + 50,1,1);

            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawRect(270,305,50,20);
        g.drawString(red,290,320);
        g.drawRect(270,335,50,20);
        g.drawString(green,285,350);
        g.drawRect(270,365,50,20);
        g.drawString(blue,290,380);
        g.setColor(color);
        g.fillRect(330,300,100,100);
        draw();
        g.setColor(Color.BLACK);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        if (grid_checker(x,y)){
            color = color_finder((double)(x-250)*360/240.0,(double) (y-50)/240.0,value);
        }
        IsClicked_Color(e.getX(),e.getY());
        red = String.valueOf(color.getRed());
        green = String.valueOf(color.getGreen());
        blue = String.valueOf(color.getBlue());

        IsClicked_Custom(e.getX(),e.getY());

        if(closing_button.IsClicked(x,y)){
            frame.dispose();
            colorToolbar.setcurrent_color(color);
        }
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        if (grid_checker(x,y)){
            color = color_finder((double)(x-250)*360/240.0,(double) (y-50)/240.0,value);
            red = String.valueOf(color.getRed());
            green = String.valueOf(color.getGreen());
            blue = String.valueOf(color.getBlue());
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {;
       setUpDrawingGraphics();
        g.setColor(Color.BLACK);
        Font font = new Font("Times New Roman",Font.PLAIN,15);
        g.setFont(font);
        g.drawString("Red",230,320);
        g.drawRect(270,305,50,20);
        g.drawString("Green",222,350);
        g.drawRect(270,335,50,20);
        g.drawString("Blue",230,380);
        g.drawRect(270,365,50,20);
        draw();
    }

    public boolean grid_checker(int x, int y){
        if (x <= 239+250 && x >= 250 && y <= 290 && y >= 50){
            repaint(280,300,200,100);
            return true;
        }

        return false;
    }

    public Color color_finder(double hue, double sat,double value){
        double red = 0;
        double green = 0;
        double blue = 0;
        double H;
        double M = 255 * value;
        double m = M*(1-sat);
        H = (double)(hue);
        double z = (M-m)*(1-Math.abs((H/60 % 2)-1));
        if (H<60){
            red = M;
            green = z+m;
            blue = m;
        }
        else if (H <120){
            red = z + m;
            green = M;
            blue = m;
        }
        else if (H<180){
            red = m;
            green = M;
            blue = z + m;
        }
        else if (H< 240){
            red = m;
            green = z + m;
            blue = M;
        }
        else if (H< 300){
            red = z + m;
            green = m;
            blue = M;
        }
        else if (H<360) {
            red = M;
            green = m;
            blue = z+m;
        }
        return new Color((int) red, (int) green, (int) blue);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Toolkit.getDefaultToolkit().sync();
        repaint(5,100,230,250);
        repaint(220,400,306,43);

    }

    public Color basic_color_picker(int x, int y){
        for (int i = 0; i<basic_buttons.length;i++){
            if (basic_buttons[i].IsClicked(x,y)) {
                return basic_colors[i];
        }
        }return null;
    }
    public Color custom_color_picker(int x, int y){
        for (int i = 0; i<custom_buttons.length;i++){
            if (custom_buttons[i].IsClicked(x,y)) {
                custom_index = i;
                return custom_colors[i];
            }
        }return null;
    }

    public void IsClicked_Color(int x, int y) {
        if (basic_color_picker(x,y)!= null) {
            repaint(270,300,200,100);
            color = basic_color_picker(x,y);
    }
        else if (custom_color_picker(x,y)!= null){
            repaint(270,300,200,100);
            color = custom_color_picker(x,y);
        }
    }
    public void IsClicked_Custom(int x, int y){
        if (add_to_custom_button.IsClicked(x,y)) {
            repaint(270,300,200,100);
            if (custom_index > custom_colors.length-1){
                custom_index = 0;
            }
            custom_colors[custom_index] = color;
            custom_index++;
     }
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        colorToolbar.setcurrent_color(color);
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
