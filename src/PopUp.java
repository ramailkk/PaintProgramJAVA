import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;

public class PopUp extends JPanel implements KeyListener, MouseListener {

    private Button ok_button;
    private Button cancel_button;
    private final int DEFAULT_WIDTH  = 300;
    private final int DEFAULT_HEIGHT = 200;
    private Graphics g;
    private String name;
    private MenuToolbar menuToolbar;
    private JFrame frame;
    private boolean pressed;

    public PopUp(MenuToolbar menuToolbar, JFrame frame){
        this.frame = frame;
        this.menuToolbar = menuToolbar;
        ImageIcon ok_button_dep = new ImageIcon("src/Shapes/Depressed/ok_depressed.png");
        ImageIcon ok_button_pre = new ImageIcon("src/Shapes/Pressed/ok_pressed.png");
        ImageIcon cancel_button_dep = new ImageIcon("src/Shapes/Depressed/cancel_depressed.png");
        ImageIcon cancel_button_pre = new ImageIcon("src/Shapes/Pressed/cancel_pressed.png");
        ok_button = new Button(25,150 ,48,48,ok_button_dep.getImage(),ok_button_pre.getImage());
        cancel_button = new Button( 175,150,48,48,cancel_button_dep.getImage(),cancel_button_pre.getImage());
        g = getGraphics();
        name = "";
        setBackground( new Color(60,60,60));
        setPreferredSize( new Dimension( DEFAULT_WIDTH, DEFAULT_HEIGHT ) );
        this.addKeyListener(new TAdapter());
        this.addMouseListener(this);
        setFocusable(true);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        draw(g);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);;
        draw(g);;
        Font font = new Font("Monospaced",Font.BOLD , 15 );
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("Enter file name with only ", 20,15 );
        g.drawString("alpha-numeric characters", 20,35 );
        font = new Font("Monospaced",Font.PLAIN , 15 );
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("(if name empty then file will be ", 10,55);
        g.drawString("-saved with time_stamp)", 10,70);
        font = new Font("Monospaced",Font.ITALIC , 13);
        g.setFont(font);
        g.drawString("You can also use backspace :)", 20, 85);
        naming(g);
    }

    private void draw(Graphics g){
        g.drawImage(ok_button.GetImage(), ok_button.x,ok_button.y , this);
        g.drawImage(cancel_button.GetImage(),cancel_button.x,cancel_button.y,this);
    }
    private void naming(Graphics g){
        g.setColor(Color.BLACK);
        Font font = new Font("Monospaced",Font.BOLD , 25 );
        g.setFont(font);
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(5.0f));
        g2.drawRect(20,100,260,35);
        g2.setColor(Color.WHITE);
        g2.fillRect(20,100,260,35);
        g2.setStroke(new BasicStroke());
        g.setColor(Color.BLACK);
        g.drawString(name,20,120);
    }
    private void IsClicked(int x, int y) throws IOException {
        if (ok_button.IsClicked(x,y)){
            if (name == null || name.equals("")) {
                name = String.valueOf(LocalDateTime.now()).substring(1, (String.valueOf(LocalDateTime.now()).length()) - 10);
                name = name.replaceAll(":","-");
            }
            menuToolbar.file_writer("src/text/" + name + ".txt");
            frame.dispose();
        }
        else if (cancel_button.IsClicked(x,y)){
            frame.dispose();
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        try {
            IsClicked(e.getX(),e.getY());
        } catch (FileNotFoundException ex) {
            throw new RuntimeException();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
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

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            if (pressed){
                repaint();
                int k = e.getKeyCode();
                char key = e.getKeyChar();
                if (k == 8) {
                    if (name.length()>0){
                        name = name.substring(0,name.length()-1);
                    }
                }

                else {
                    if (((key >= 'a' && key <='z') || (key >= 'A' && key <='Z') || (key >= '0' && key <= '9')) && name.length()<17){
                        name = name + key;
                    }
                }
                pressed = false;
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            pressed = true;
        }
    }

}
