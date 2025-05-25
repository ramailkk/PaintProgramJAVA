import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.*;
import java.util.*;

public class MenuToolbar extends Toolbar{

    private Button file_button;
    private Button disk_button;
    private Button folder_button;
    private Button file_menu_button;
    private Button edit_menu_button;
    private Button undo_button;
    private Button redo_button;
    private ArrayList<Button> buttons = new ArrayList<>();
    private int x;
    private int y;
    private String menu_state;
    private ArrayList<ArrayList<Shape>> shapes;
    private LayerToolbar layerToolbar;
    private ArrayList<Shape> undo_redo;
    private Stack<Shape> redo = new Stack<>();
    private boolean redo_active = false;
    private ArrayList<Shape> free;
    private Tooltip tooltip;
    public MenuToolbar(int x, int y){
        menu_state = "default";
        this.x = x;
        this.y = y;
        ImageIcon file_button_dep = new ImageIcon("src/Shapes/Depressed/file_depressed.png");
        ImageIcon file_button_pre = new ImageIcon("src/Shapes/Pressed/file_pressed.png");
        ImageIcon folder_button_dep = new ImageIcon("src/Shapes/Depressed/folder_depressed.png");
        ImageIcon folder_button_pre = new ImageIcon("src/Shapes/Pressed/folder_pressed.png");
        ImageIcon disk_button_dep = new ImageIcon("src/Shapes/Depressed/disk_depressed.png");
        ImageIcon disk_button_pre = new ImageIcon("src/Shapes/Pressed/disk_pressed.png");
        ImageIcon file_menu_button_pre = new ImageIcon("src/Shapes/Pressed/filemenu_pressed.png");
        ImageIcon file_menu_button_dep = new ImageIcon("src/Shapes/Depressed/filemenu_depressed.png");
        ImageIcon edit_menu_button_pre = new ImageIcon("src/Shapes/Pressed/editmenu_pressed.png");
        ImageIcon edit_menu_button_dep = new ImageIcon("src/Shapes/Depressed/editmenu_depressed.png");
        ImageIcon undo_button_dep = new ImageIcon("src/Shapes/Depressed/undo_depressed.png");
        ImageIcon undo_button_pre = new ImageIcon("src/Shapes/Pressed/undo_pressed.png");
        ImageIcon redo_button_dep = new ImageIcon("src/Shapes/Depressed/redo_depressed.png");
        ImageIcon redo_button_pre = new ImageIcon("src/Shapes/Pressed/redo_pressed.png");

        file_button = new Button(x,y + 64,128,64,file_button_dep.getImage(),file_button_pre.getImage());
        folder_button = new Button(x,y + 128,128,64,folder_button_dep.getImage(),folder_button_pre.getImage());
        disk_button = new Button(x,y + 192,128,64,disk_button_dep.getImage(),disk_button_pre.getImage());
        file_menu_button = new Button(x,y,128,64,file_menu_button_dep.getImage(),file_menu_button_pre.getImage());
        edit_menu_button = new Button(x+128,y,128,64,edit_menu_button_dep.getImage(),edit_menu_button_pre.getImage());
        undo_button = new Button(x+128,y+64,128,64,undo_button_dep.getImage(),undo_button_pre.getImage());
        redo_button = new Button(x+128,y+128,128,64,redo_button_dep.getImage(),redo_button_pre.getImage());

        buttons.add(file_menu_button);
        buttons.add(edit_menu_button);
        buttons.add(file_button);
        buttons.add(folder_button);
        buttons.add(disk_button);
        buttons.add(undo_button);
        buttons.add(redo_button);
    }
    public void draw(Graphics g, ImageObserver that) {
        g.setColor(Color.BLACK);
        g.drawImage(file_menu_button.GetImage(), file_menu_button.x, file_menu_button.y, that);
        g.drawImage(edit_menu_button.GetImage(), edit_menu_button.x, edit_menu_button.y, that);
        g.setColor(Color.BLACK);
        if (menu_state.equals("file_menu")) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 64, 128,196 );
            g.setColor(Color.BLACK);
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(2.0f));
            g2.drawRect(0,64,128,196);
            g2.setStroke(new BasicStroke(0));
            g.drawImage(file_button.GetImage(), file_button.x, file_button.y, that);
            g.drawString("New", file_button.x + 70, file_button.y + 32);
            g.drawImage(folder_button.GetImage(), folder_button.x, folder_button.y, that);
            g.drawString("Open", folder_button.x + 70, folder_button.y + 32);
            g.drawImage(disk_button.GetImage(), disk_button.x, disk_button.y, that);
            g.drawString("Save", disk_button.x + 70, disk_button.y + 32);
    }
        else if (menu_state.equals("edit_menu")){g.setColor(Color.WHITE);
            g.fillRect(128, 64, 128,128);
            g.setColor(Color.BLACK);
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(2.0f));
            g2.drawRect(128,64,128,128);
            g2.setStroke(new BasicStroke(0));
            g.drawImage(undo_button.GetImage(),undo_button.x,undo_button.y,that);
            g.drawString("Undo",undo_button.x + 70, undo_button.y + 32);
            g.drawImage(redo_button.GetImage(), redo_button.x,redo_button.y,that);
            g.drawString("Redo",redo_button.x + 70, redo_button.y + 32);
        }
        tooltip.draw_tooltip(g);
    }

    public void IsPressed(int k){
        char key = (char)k;
        if (key == 'F' || key =='X' || key == 'E' || key == 'D' || key =='N'|| key == 'U' || key == 'R' ) {
            reset(key);
            if (getFile_menu_button().Press(key,'F') && menu_state.equals("file_menu")){
                menu_state = "default";
            }
            else if (getEdit_menu_button().Press(key, 'E') && menu_state.equals("edit_menu")){
                menu_state = "default";
            }
            else if (getFile_menu_button().Press(key,'F')) {
                menu_state = "file_menu";
            } else if (getEdit_menu_button().Press(key, 'E')) {
                menu_state = "edit_menu";
            }
            reset(key);

                if (getFile_button().Press(key, 'N')) {
                    file_selected();
                } else if (getDisk_button().Press(key, 'D')) {
                    disk_selected();
                } else if (getFolder_button().Press(key, 'X')) {
                    folder_selected();
                }
                    getUndo_button().Press(key,'U' );
                    getRedo_button().Press(key, 'R');
                    if (getUndo_button().Press(key, 'U')) {
                        undo_selected();
                    } else if (getRedo_button().Press(key, 'R')) {
                        redo_selected();
                    }
                }
        else
            menu_state = "default";
    }
    public void IsClicked(int x, int y) throws FileNotFoundException {
        if (x>=0 && x<270 && ((y>=0 && y<150) || (y>=150 && y<250))) {

            reset(x, y);
            if (getFile_menu_button().IsClicked(x,y) && menu_state.equals("file_menu")){
                menu_state = "default";
            }
            else if (getEdit_menu_button().IsClicked(x,y) && menu_state.equals("edit_menu")){
                menu_state = "default";
            }

            else if (getFile_menu_button().IsClicked(x, y)){
                menu_state = "file_menu";
            }
            else if (getEdit_menu_button().IsClicked(x, y)) {
                menu_state = "edit_menu";
            }
            reset(x, y);
            if (menu_state.equals("file_menu")) {
                if (getFile_button().IsClicked(x, y)) {
                    file_selected();
                } else if (getDisk_button().IsClicked(x, y)) {
                    disk_selected();
                } else if (getFolder_button().IsClicked(x, y)) {
                    folder_selected();
                }
            } else if (menu_state.equals("edit_menu")) {
                getUndo_button().IsClicked(x, y);
                getRedo_button().IsClicked(x, y);
                if (getUndo_button().IsClicked(x, y)){
                    undo_selected();
                }
                else if (getRedo_button().IsClicked(x, y)) {
                    redo_selected();
                }

            }
        }
        else
            menu_state = "default";
        reset(x, y);
    }

    private void file_selected(){
        for (int i = 0; i <shapes.size() ; i++) {
            shapes.get(i).clear();
        }
        free.clear();
        redo.clear();
    }
    private void folder_selected(){
        JFrame frame = new JFrame("File Directory System V1.31");
        frame.setDefaultCloseOperation(3);
        FolderPanel folderPanel = new FolderPanel(this,frame,layerToolbar);
        frame.add(folderPanel);
        frame.pack();
        frame.addWindowListener(folderPanel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    private void disk_selected(){
        JFrame frame = new JFrame("Save As");
        frame.setDefaultCloseOperation(3);
        PopUp pop = new PopUp(this,frame);
        frame.add(pop);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    private void undo_selected(){
        if (!undo_redo.isEmpty()){
            try {
                for (int i = undo_redo.size()-1; true; --i) {
                    if (undo_redo.get(i) == null){
                        redo.push(undo_redo.remove(undo_redo.size()-1));
                        for (int j = undo_redo.size()-1; (undo_redo.get(j)!=null && (undo_redo.get(j) instanceof Free));  j--) {
                            Shape shape = undo_redo.remove(undo_redo.size()-1);
                            redo.push(shape);
                        }
                        redo_active = true;;
                        break;
                    }
                    else if (!(undo_redo.get(i) instanceof Free)) {
                        Shape shape = undo_redo.remove(undo_redo.size() - 1);
                        redo.push(shape);
                        redo_active = true;
                        break;
                    }
                }
            }
            catch (IndexOutOfBoundsException ignored) {
            }

        }
    }
    private void redo_selected(){
        if (!redo.isEmpty()){
            if (redo.lastElement() instanceof Free) {
                while (redo.lastElement() !=null){
                    undo_redo.add(redo.pop());
                }
                undo_redo.add(redo.pop());
        }
            else
                undo_redo.add(redo.pop());
            if (redo.isEmpty()){
                redo_active = false;
            }
        }
    }
    public void confirm_tooltip(int x, int y) {
        String type = "";
        for (int i = 0; i < buttons.indexOf(edit_menu_button)+1; i++) {
            boolean breaker = tooltip.confirm_tooltip(x, y, buttons.get(i));
            if (tooltip.getSelected_button() == file_menu_button) {
                type = "File Menu (f)";
            } else if (tooltip.getSelected_button() == edit_menu_button) {
                type = "Edit Menu (e)";
            }
            tooltip.setMessage(type);
            if (breaker)
                return;
        }
        if (menu_state.equals("file_menu")){
            for (int i = buttons.indexOf(file_button); i < buttons.indexOf(disk_button)+1; i++) {
                boolean breaker = tooltip.confirm_tooltip(x,y,buttons.get(i));
                 if (tooltip.getSelected_button() == file_button) {
                    type = "New Page (n)";
                } else if (tooltip.getSelected_button() == folder_button) {
                    type = "Open Folder (x)";
                } else if (tooltip.getSelected_button() == disk_button) {
                    type = "Save (d)";
                }
                tooltip.setMessage(type);
                 if (breaker)
                     return;
            }
    }
        else if (menu_state.equals("edit_menu")){
            for (int i = buttons.indexOf(undo_button); i <buttons.indexOf(redo_button)+1 ; i++) {
                boolean breaker = tooltip.confirm_tooltip(x,y,buttons.get(i));
                if (tooltip.getSelected_button() == undo_button) {
                    type = "Undo (u)";
                }
                else if (tooltip.getSelected_button() == redo_button) {
                    type = "Redo (r)";
                }
                tooltip.setMessage(type);
                if (breaker)
                    return;
            }
        }
    }
    public void reset(int x, int y){
        getFile_menu_button().IsClicked(x,y);
        getFolder_button().IsClicked(x,y);
        getFile_button().IsClicked(x,y);
        getDisk_button().IsClicked(x,y);
        getEdit_menu_button().IsClicked(x,y);
        getUndo_button().IsClicked(x,y);
        getRedo_button().IsClicked(x,y);
    }
    public void reset(int key){
        getFile_menu_button().Press(key,'f');
        getFolder_button().Press(key,'x');
        getFile_button().Press(key,'n');
        getDisk_button().Press(key,'d');
        getEdit_menu_button().Press(key,'e');
        getUndo_button().Press(key,'u');
        getRedo_button().IsClicked(key,'r');
    }
    public void isReleased(){
        getFile_menu_button().isReleased();
        getDisk_button().isReleased();
        getRedo_button().isReleased();
        getFolder_button().isReleased();
        getUndo_button().isReleased();
        getEdit_menu_button().isReleased();
        getFile_button().isReleased();
    }
    public void purgeStack(){
        redo.clear();
    }

    public void file_writer(String filename) throws IOException {
        Shape shape;
        FileOutputStream file = new FileOutputStream
                (filename);
        ObjectOutputStream out = new ObjectOutputStream
                (file);
        try {
            for (int i = 0; i < shapes.size(); i++) {
                out.writeObject("add");
                for (int j = 0; j < shapes.get(i).size(); j++) {
                    shape = shapes.get(i).get(j);
                    out.writeObject(shape);
                }
            }
        }catch (IOException ex){
            System.out.println("IO");
        }
        out.close();
    }

    public Button getFile_button() {
        return file_button;
    }
    public void setFile_button(Button file_button) {
        this.file_button = file_button;
    }
    public Button getDisk_button() {
        return disk_button;
    }
    public void setDisk_button(Button disk_button) {
        this.disk_button = disk_button;
    }
    public Button getFolder_button() {
        return folder_button;
    }
    public void setFolder_button(Button folder_button) {
        this.folder_button = folder_button;
    }
    public Button getFile_menu_button() {
        return file_menu_button;
    }
    public void setFile_menu_button(Button file_menu_button) {
        this.file_menu_button = file_menu_button;
    }
    public Button getEdit_menu_button() {
        return edit_menu_button;
    }
    public void setEdit_menu_button(Button edit_menu_button) {
        this.edit_menu_button = edit_menu_button;
    }
    public Button getUndo_button() {
        return undo_button;
    }
    public void setUndo_button(Button undo_button) {
        this.undo_button = undo_button;
    }
    public Button getRedo_button() {
        return redo_button;
    }
    public void setRedo_button(Button redo_button) {
        this.redo_button = redo_button;
    }
    public String getMenu_state() {
        return menu_state;
    }

    public LayerToolbar getLayerToolbar() {
        return layerToolbar;
    }
    public void setLayerToolbar(LayerToolbar layerToolbar) {
        this.layerToolbar = layerToolbar;
    }
    public ArrayList<ArrayList<Shape>> getShapes() {
        return shapes;
    }
    public ArrayList<Shape> getUndo_redo() {
        return undo_redo;
    }
    public void setUndo_redo(ArrayList<Shape> undo_redo) {
        this.undo_redo = undo_redo;
    }
    public void setShapes(ArrayList<ArrayList<Shape>> shapes) {
        this.shapes = shapes;
    }
    public boolean isRedo_active() {
        return redo_active;
    }
    public void setRedo_active(boolean redo_active) {
        this.redo_active = redo_active;
    }

    public Tooltip getTooltip() {
        return tooltip;
    }

    public void setTooltip(Tooltip tooltip) {
        this.tooltip = tooltip;
    }

    public ArrayList<Shape> getFree() {
        return free;
    }

    public void setFree(ArrayList<Shape> free) {

        this.free = free;
    }

    public void setMenu_state(String menu_state) {
        this.menu_state = menu_state;
    }
}


