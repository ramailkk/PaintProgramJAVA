import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class FolderPanel extends JPanel implements WindowListener, MouseMotionListener, MouseListener, ActionListener {

    private final int DEFAULT_WIDTH  = 800;
    private final int DEFAULT_HEIGHT = 630;

    JFrame frame;

    private int x;
    private int y;
    private int width;
    private int height;
    private File folder;
    private File[] files;
    private String[][] file_name;
    private Table table;
    private File selected_file;
    private Button selected_button;
    private ArrayList<ArrayList<Shape>> shape_lists = new ArrayList<>();
    private LayerToolbar layerToolbar;
    private MenuToolbar menuToolbar;

    public FolderPanel(MenuToolbar menuToolbar,JFrame frame, LayerToolbar layerToolbar){
        this.menuToolbar = menuToolbar;
        this.layerToolbar = layerToolbar;
        this.frame = frame;
        setBackground( new Color(80,80,80));
        setPreferredSize( new Dimension( DEFAULT_WIDTH, DEFAULT_HEIGHT ) );
        this.addMouseListener( this);
        this.addMouseMotionListener(this);
        setFocusable(true);

        folder = new File("src/text");
        files = folder.listFiles();
        file_name = new String[files.length+1][3];
        file_name[0][0] = "Name";
        file_name[0][1] = "Date Modified";
        file_name[0][2] = "Size (in kb)";;
        for (int i = 1;  i<file_name.length;i++) {
            if (files[i-1].getName().endsWith(".txt")){
                file_name[i][0] = files[i-1].getName();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                file_name[i][1] = sdf.format(files[i-1].lastModified());
                file_name[i][2] = String.valueOf((double)files[i-1].length()/1024);
        }
        }

        table = new Table(x,y,file_name.length,file_name[0].length,new Color(80,80,80),Color.BLACK,Color.WHITE,
                Color.DARK_GRAY,Color.BLACK,Color.WHITE,Color.BLACK,Color.LIGHT_GRAY,Color.WHITE,file_name);
    }

    private void double_click(Button button){
        if (button == selected_button){
            if (selected_file != null){
                try {
                    file_reader();
                    menuToolbar.setShapes(getShape_lists());
                    layerToolbar.setShapeLists(getShape_lists());
                    layerToolbar.new_file();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            frame.dispose();
        }
        else {
            setSelected_button(button);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        table.paint(g);

        }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Toolkit.getDefaultToolkit().sync();
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (int i =1; i<table.getButtons().length;i++){
            if (table.getButtons()[i].IsClicked(e.getX(), e.getY())){
                double_click(table.getButtons()[i]);
                for (int j = 0; j <files.length ; j++) {
                    if (files[j].getName().equals(file_name[i][0])){
                        setSelected_file(files[j]);
                    }
                }
                repaint();
                int index = i*3;
                table.getCells()[index].setCell_color(new Color(160,160,160));
                table.getCells()[index+1].setCell_color(new Color(160,160,160));
                table.getCells()[index+2].setCell_color(new Color(160,160,160));
                for (int j =3; j<table.getCells().length;j++){
                    if (j>index + 2 || j<index) {
                        table.getCells()[j].setCell_color(new Color(80,80,80));
                }
                }
            }
        }
        if (table.getClosing().IsClicked(e.getX(),e.getY())){

            frame.dispose();
        }
    }
    public void file_reader() throws IOException, ClassNotFoundException {
        if (selected_file!=null) {
            FileInputStream file2 = new FileInputStream
                    ("src/text/" + getSelected_file().getName());
            ObjectInputStream in = new ObjectInputStream
                    (file2);
            int count = 0;
            while (true) {
                try {
                    Object object =  in.readObject();
                    if (object != null){
                        if (object.equals("add")){
                            count++;
                            shape_lists.add(new ArrayList<>());
                        }
                        else
                            shape_lists.get(count-1).add((Shape) object);
                    }
                    else
                        shape_lists.get(count-1).add(null);
                }catch (ClassCastException e)  {
                    System.out.println("Idk");

                }
                catch (EOFException e) {break;}

                }
            }
    }

    public ArrayList<ArrayList<Shape>> getShape_lists() {
        return shape_lists;
    }

    public void setShape_lists(ArrayList<ArrayList<Shape>> shape_lists) {
        this.shape_lists = shape_lists;
    }

    public File getSelected_file() {
        return selected_file;
    }

    public void setSelected_file(File selected_file) {
        this.selected_file = selected_file;
    }

    public Button getSelected_button() {
        return selected_button;
    }

    public void setSelected_button(Button selected_button) {
        this.selected_button = selected_button;
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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
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
}
