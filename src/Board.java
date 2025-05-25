import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.MouseInputListener;

public class Board extends JPanel implements ActionListener , MouseInputListener, KeyListener {
    private final int B_WIDTH = 1000;
    private final int B_HEIGHT = 800;
    private final int DELAY = 25;
    private Timer timer;
    private Toolbar menuToolbar;
    private Toolbar shapeToolbar;
    private Toolbar layerToolbar;
    private Toolbar colorToolbar;
    private Toolbar strokeToolbar;
    private StrokeToolbar strokeToolbar1;
    private MenuToolbar menuToolbar1;
    private ShapeToolbar shapeToolbar1;
    private ColorToolbar colorToolbar1;
    private LayerToolbar layerToolbar1;
    private Grid grid;
    private Integer x1,y1,x2,y2;
    Integer free_x1,free_y1,free_x,free_y;
    private boolean drawing;
    private boolean dragging;
    private ShapeFactory shapeFactory;
    private Shape shape;
    private ArrayList<Shape> current_layer;
    private Tooltip tooltip;
    private ArrayList<Shape> frees = new ArrayList<>();
    private Shape free;

    public Board() {
        initBoard();
        current_layer = layerToolbar1.getCurrent_layer_list();
    }


    private void InitializeAssets() {
        menuToolbar = new MenuToolbar(0,0);
        shapeToolbar = new ShapeToolbar(600, 0);
        colorToolbar = new ColorToolbar(270, 20);
        layerToolbar = new LayerToolbar(871, 150);
        strokeToolbar = new StrokeToolbar(0,64);
        strokeToolbar1 = ((StrokeToolbar)strokeToolbar);
        shapeToolbar1 = ((ShapeToolbar)shapeToolbar);
        colorToolbar1 = ((ColorToolbar)colorToolbar);
        shapeFactory = new ShapeFactory();
        menuToolbar1 = ((MenuToolbar)menuToolbar);
        layerToolbar1 = ((LayerToolbar)layerToolbar);
        menuToolbar1.setLayerToolbar(layerToolbar1);
        menuToolbar1.setShapes(((LayerToolbar) layerToolbar).getShapeLists());
        menuToolbar1.setUndo_redo(layerToolbar1.getCurrent_layer_list());
        grid = Grid.getInstance(870, 0, 870, B_HEIGHT - 125);
        tooltip = Tooltip.getInstance();
        shapeToolbar1.setTooltip(tooltip);
        menuToolbar1.setTooltip(tooltip);
        menuToolbar1.setFree(frees);
        colorToolbar1.setTooltip(tooltip);
        layerToolbar1.setTooltip(tooltip);
        strokeToolbar1.setTooltip(tooltip);
        grid.setTooltip(tooltip);
    }

    private void initBoard() {
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(new TAdapter());
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setFocusable(true);
        InitializeAssets();
        timer = new Timer(DELAY, this);
        timer.start();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        layerToolbar1.display(g);
        if (shape != null){
            shape.draw(g);
        }
        if (frees!=null){
            for (int i = 0; i <frees.size() ; i++) {
                frees.get(i).draw(g);
            }
        }
        g.setColor(Color.BLACK);
        grid.draw_grid(g);
        drawButton(g);
        tooltip.draw_tooltip(g);
        g.setFont(new Font("Times New Roman",Font.BOLD,14));

    }
    public void paint(Graphics g){
        super.paint(g);
    }
    private void drawButton(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(871,0,1000,1000);
        g.fillRect(0,0,871,125);
        strokeToolbar.draw(g,this);
        shapeToolbar.draw(g, this);
        colorToolbar.draw(g, this);
        layerToolbar.draw(g, this);
        menuToolbar.draw(g, this);
        grid.draw(g,this);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2.0f));
        g2.drawRect(0,0, 871,125);
        g2.drawRect(871,0,1000,1000);
        g2.drawLine(260,0,260,125);
        g2.drawLine(600,0,600,125);
        g2.setStroke(new BasicStroke(0));
    }
    public void IsClicked(int x, int y) throws FileNotFoundException {
        menuToolbar.IsClicked(x, y);
        colorToolbar.IsClicked(x, y);
        shapeToolbar.IsClicked(x, y);
        layerToolbar.IsClicked(x, y);
        if (!(menuToolbar1.getMenu_state().equals("file_menu"))){

            strokeToolbar.IsClicked(x, y);
    }
        grid.IsClicked(x,y);
    }
    public void free_drawing(int x, int y){
        if ((y>125 && x<850) && menuToolbar1.getMenu_state().equals("default"))
            drawing = true;
        else
            drawing = false;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        try {
            IsClicked(e.getX(), e.getY());
        } catch (FileNotFoundException ex) {
        }
        free_drawing(e.getX(), e.getY());
        if (drawing){
            if (SwingUtilities.isLeftMouseButton(e)) {
                x1 = e.getX();
                y1 = e.getY();
            }
    }
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        dragging = true;
        if (drawing && (e.getX()<850 && e.getY()>125) && menuToolbar1.getMenu_state().equals("default")){
            x2 = e.getX();
            y2 = e.getY();
        }
        if (shapeToolbar1.getType() != null && drawing && !shapeToolbar1.getType().equals("free"))
            shape = shapeFactory.create_shape(shapeToolbar1.getType(),x1,y1,x2,y2,colorToolbar1.getFill_color(),colorToolbar1.getStroke_color(),strokeToolbar1.getStroke_width());
        else if (shapeToolbar1.getType() !=null && drawing && shapeToolbar1.getType().equals("free")){
            free_x =free_x1; free_y = free_y1;
            free_x1 = e.getX();
            free_y1 = e.getY();
            try {
                free = new Free(new Point(free_x,free_y), new Point(free_x1,free_y1),colorToolbar1.getStroke_color(),strokeToolbar1.getStroke_width());
                frees.add(free);
                current_layer.add(free);
            }catch (NullPointerException exception){

            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

        strokeToolbar1.getPlus_button().isReleased();
        strokeToolbar1.getMinus_button().isReleased();
        menuToolbar1.isReleased();
        layerToolbar1.isReleased();
        grid.getGrid_button().isReleased();

        current_layer = layerToolbar1.getCurrent_layer_list();
        if (dragging && drawing && menuToolbar1.getMenu_state().equals("default") && shapeToolbar1.getType()!=null && !shapeToolbar1.getType().equals("free") && x2!=null && y2!=null) {
            shape = shapeFactory.create_shape(shapeToolbar1.getType(), x1, y1, x2, y2, colorToolbar1.getFill_color(), colorToolbar1.getStroke_color(), strokeToolbar1.getStroke_width());
            current_layer.add(shape);
            if (menuToolbar1.isRedo_active()){
                menuToolbar1.purgeStack();
                menuToolbar1.setRedo_active(false);
            }
            dragging = false;
            shape = null;
            x1 = null;
            y1 = null;
            y2 = null;
            x2 = null;
        }
        else if (dragging && drawing && menuToolbar1.getMenu_state().equals("default") && shapeToolbar1.getType()!=null && shapeToolbar1.getType().equals("free")){
            current_layer.add(null);
            dragging = false;
            free_x = null;
            free_x1 = null;
            free_y1 = null;
            free_y = null;
            frees.clear();
            if (menuToolbar1.isRedo_active()){
                menuToolbar1.purgeStack();
                menuToolbar1.setRedo_active(false);
            }
        }
        if (menuToolbar1.getUndo_redo() != layerToolbar1.getCurrent_layer_list()){
            menuToolbar1.setUndo_redo(layerToolbar1.getCurrent_layer_list());
            menuToolbar1.purgeStack();
        }
        drawing = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (x > 600 && x < 850 && y > 0 && y < 125) {
            shapeToolbar1.confirm_tooltip(e.getX(), e.getY());
            tooltip.setPosition_help(false);
        }
        if (x>0 && x<=128 && y> 64 && y<125 && menuToolbar1.getMenu_state().equals("default")){
            strokeToolbar1.confirm_tooltip(x,y);
            tooltip.setPosition_help(false);
        }
        else if (x > 0 && x < 270 && y > 0 && y <= 250) {
            menuToolbar1.confirm_tooltip(e.getX(), e.getY());
            tooltip.setPosition_help(false);
        }
        else if (x > 270 && x < 600 && y > 0 && y < 125){
            colorToolbar1.confirm_tooltip(x, y);
            tooltip.setPosition_help(false);
    }
        else if (x > 871 && x < 1000 && y > 125 && y < 1000){
            layerToolbar1.confirm_tooltip(x, y);
            tooltip.setPosition_help(true);
    }
        else if (x>871 && x<1000 && y>0 && y<125){
            grid.confirm_tooltip(x,y);
            tooltip.setPosition_help(false);
        }

        if (dragging) {
            if (e.getX() > 870 || e.getY() < 125){
                drawing = false;
                shape = null;
        }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            int key = e.getKeyCode();
            menuToolbar1.IsPressed(key);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Toolkit.getDefaultToolkit().sync();

        repaint(0,0,850,150);
        repaint(0,150,270,100);
        repaint(870,150,1000,1000);
        repaint(870,75,100,100);
    }

}