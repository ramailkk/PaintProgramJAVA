import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class LayerToolbar extends Toolbar {

    private Button current_selected;
    private int layer_counter;
    private Button up_button;
    private Button down_button;

    private Button add_button;
    private Button remove_button;
    private int list_x;
    private int list_y;
    ArrayList<ImageIcon> dep = new ArrayList<>();
    ArrayList<ImageIcon> pre = new ArrayList<>();

    LinkedList<Button> list = new LinkedList<>();
    private ArrayList<ArrayList<Shape>> shapeLists = new ArrayList<>();
    private ArrayList<Shape> current_layer_list = new ArrayList<>();
    private MenuToolbar menuToolbar;
    private int y1;
    private Tooltip tooltip;
    private ArrayList<Button> buttons = new ArrayList<>();

    public LayerToolbar(int x, int y) {

        list_x = x;
        list_y = y;
        y1 = y;
        layer_counter = 0;
        dep.add(new ImageIcon("src/Shapes/Depressed/layer1_depressed.png"));
        pre.add(new ImageIcon("src/Shapes/Pressed/layer1_pressed.png"));
        dep.add(new ImageIcon("src/Shapes/Depressed/layer2_depressed.png"));
        pre.add(new ImageIcon("src/Shapes/Pressed/layer2_pressed.png"));
        dep.add(new ImageIcon("src/Shapes/Depressed/layer3_depressed.png"));
        pre.add(new ImageIcon("src/Shapes/Pressed/layer3_pressed.png"));
        dep.add(new ImageIcon("src/Shapes/Depressed/layer4_depressed.png"));
        pre.add(new ImageIcon("src/Shapes/Pressed/layer4_pressed.png"));
        dep.add(new ImageIcon("src/Shapes/Depressed/layer5_depressed.png"));
        pre.add(new ImageIcon("src/Shapes/Pressed/layer5_pressed.png"));
        dep.add(new ImageIcon("src/Shapes/Depressed/layer6_depressed.png"));
        pre.add(new ImageIcon("src/Shapes/Pressed/layer6_pressed.png"));
        dep.add(new ImageIcon("src/Shapes/Depressed/layer7_depressed.png"));
        pre.add(new ImageIcon("src/Shapes/Pressed/layer7_pressed.png"));
        dep.add(new ImageIcon("src/Shapes/Depressed/layer8_depressed.png"));
        pre.add(new ImageIcon("src/Shapes/Pressed/layer8_pressed.png"));
        dep.add(new ImageIcon("src/Shapes/Depressed/layer9_depressed.png"));
        pre.add(new ImageIcon("src/Shapes/Pressed/layer9_pressed.png"));
        ImageIcon up_button_dep = new ImageIcon("src/Shapes/Depressed/up_depressed.png");
        ImageIcon up_button_pre = new ImageIcon("src/Shapes/Pressed/up_pressed.png");
        ImageIcon down_button_dep = new ImageIcon("src/Shapes/Depressed/down_depressed.png");
        ImageIcon down_button_pre = new ImageIcon("src/Shapes/Pressed/down_pressed.png");
        ImageIcon add_button_dep = new ImageIcon("src/Shapes/Depressed/add_depressed.png");
        ImageIcon add_button_pre = new ImageIcon("src/Shapes/Pressed/add_pressed.png");
        ImageIcon remove_button_dep = new ImageIcon("src/Shapes/Depressed/remove_depressed.png");
        ImageIcon remove_button_pre = new ImageIcon("src/Shapes/Pressed/remove_pressed.png");
        up_button = new Button(x + 64, y + 64 + 128, 64, 64, up_button_dep.getImage(), up_button_pre.getImage());
        down_button = new Button(x + 64, y + 128 + 128, 64, 64, down_button_dep.getImage(), down_button_pre.getImage());
        add_button = new Button(x + 64, y + 64, 64, 64, add_button_dep.getImage(), add_button_pre.getImage());
        remove_button = new Button(x + 64, y + 128, 64, 64, remove_button_dep.getImage(), remove_button_pre.getImage());
        buttons.add(up_button);
        buttons.add(down_button);
        buttons.add(add_button);
        buttons.add(remove_button);
        add_layer();
    }
    public void up_layer(){

        try {
            if (getCurrent_selected() != null) {
                layer_counter = getList().indexOf(getCurrent_selected());
                ArrayList<Shape> temp = shapeLists.get(layer_counter);
                shapeLists.set(layer_counter,shapeLists.get(layer_counter - 1));
                shapeLists.set(layer_counter - 1,temp);
                int y = getCurrent_selected().y;
                getCurrent_selected().y = getList().get(layer_counter - 1).y;
                getList().get(layer_counter - 1).y = y;
                getList().set(layer_counter, getList().get(layer_counter - 1));
                getList().set(layer_counter - 1, getCurrent_selected());
                layer_selector();
                getCurrent_selected().setCurrent_image(getCurrent_selected().getImage_pressed());
            }
        }
        catch (IndexOutOfBoundsException ex){
            getCurrent_selected().setCurrent_image(getCurrent_selected().getImage_pressed());
        }
    }
    public void down_layer(){
        try {
            if (getCurrent_selected() != null) {
                layer_counter = getList().indexOf(getCurrent_selected());
                int y = getCurrent_selected().y;
                getCurrent_selected().y = getList().get(layer_counter + 1).y;
                ArrayList<Shape> temp = shapeLists.get(layer_counter);
                shapeLists.set(layer_counter,shapeLists.get(layer_counter + 1));
                shapeLists.set(layer_counter + 1,temp);
                getList().get(layer_counter + 1).y = y;
                getList().set(layer_counter, getList().get(layer_counter + 1));
                getList().set(layer_counter + 1, getCurrent_selected());
                getCurrent_selected().setCurrent_image(getCurrent_selected().getImage_pressed());
                layer_selector();
            }
        }
        catch (IndexOutOfBoundsException ex){
            getCurrent_selected().setCurrent_image(getCurrent_selected().getImage_pressed());
        }
    }
    public void new_file(){
        list.clear();
        list_y = y1;
        for (int i = 0; i <shapeLists.size() ; i++) {
            list_y +=64;
            list.add(new Button(list_x, list_y, 64, 64, dep.get(list.size()).getImage(), pre.get(list.size()).getImage()));
        }
        setCurrent_layer_list(shapeLists.get(0));
        setCurrent_selected(list.get(0));
        current_selected.setCurrent_image(current_selected.getImage_pressed());
    }
    public void add_layer(){
        list_y +=64;
        try {
            list.add(new Button(list_x, list_y, 64, 64, dep.get(list.size()).getImage(), pre.get(list.size()).getImage()));
            shapeLists.add(new ArrayList<>());
        }
        catch (IndexOutOfBoundsException ex){
        }

        setCurrent_selected(list.getLast());
        setCurrent_layer_list(shapeLists.get(list.indexOf(list.getLast())));
        layer_selector();
        current_selected.setCurrent_image(current_selected.getImage_pressed());
    }
    public void remove_layer(){
        if (list_y>=0 && getCurrent_selected() != null & list.size() > 1){
            layer_counter = list.indexOf(getCurrent_selected());

            int x = list.get(layer_counter).x;
            int y = list.get(layer_counter).y;
            shapeLists.remove(list.indexOf(getCurrent_selected()));
            setCurrent_layer_list(shapeLists.get(0));
            list.remove(getCurrent_selected());
            for (int i =layer_counter; i<list.size();i++){
                list.get(i).x = x;
                list.get(i).y = y;
                y=y+64;
            }
            list_x = x;
            list_y = y-64;
            if (list.size()>0){
                setCurrent_selected(list.get(0));
                current_selected.setCurrent_image(current_selected.getImage_pressed());
            }
            else setCurrent_selected(null);
        }
    }

    public void draw(Graphics g, ImageObserver that){
        g.drawImage(up_button.GetImage(), up_button.x, up_button.y, that);
        g.drawImage(down_button.GetImage(), down_button.x, down_button.y, that);
        g.drawImage(add_button.GetImage(), add_button.x, add_button.y, that);
        g.drawImage(remove_button.GetImage(), remove_button.x, remove_button.y, that);
            Iterator<Button> c = list.iterator();
            while (c.hasNext()){
                Button temp = c.next();
                g.drawImage(temp.GetImage(),temp.x,temp.y,that);
            }
            tooltip.draw_tooltip(g);
    }

    public ArrayList<Shape> layer_selector(){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == getCurrent_selected()){
                return shapeLists.get(i);
            }
        }
        return shapeLists.get(0);
    }
    public void IsClicked(int x, int y){

        if (x > 850 && y> 125) {


            for (int i = 0; i < getList().size(); i++) {
                if (getList().get(i).IsClicked(x, y)) {
                    setCurrent_selected(getList().get(i));
                    setCurrent_layer_list(layer_selector());
                }
            }

            if (getRemove_button().IsClicked(x, y)) {
                remove_layer();
            }

            if (getAdd_button().IsClicked(x, y)) {
                add_layer();
            }
            if (getUp_button().IsClicked(x, y)) {
                up_layer();
            } else if (getDown_button().IsClicked(x, y)) {
                down_layer();
            }
        }
    }
    public void display(Graphics g){
        for (int i = shapeLists.size()-1; i >=0 ; i--) {
            for (int j = 0; j <shapeLists.get(i).size() ; j++) {
                    if (shapeLists.get(i).get(j) != null){
                        shapeLists.get(i).get(j).draw(g);
                    }
            }
        }
    }
    public void isReleased(){
        up_button.isReleased();
        down_button.isReleased();
        add_button.isReleased();
        remove_button.isReleased();
    }

    public void confirm_tooltip(int x, int y){
        String type = "";
        for (Button value : buttons) {
            boolean breaker = tooltip.confirm_tooltip(x, y, value);
            Button button = tooltip.getSelected_button();
            if (button == up_button)
                type = "Move Layer Up";
            else if (button == down_button)
                type = "Move Layer Down";
            else if (button == add_button)
                type = "Add Layer";
            else if (button == remove_button)
                type = "Remove Layer";
            tooltip.setMessage(type);
            if (breaker){
                return;
        }
        }
    }

    public ArrayList<ArrayList<Shape>> getShapeLists() {
        return shapeLists;
    }
    public void setShapeLists(ArrayList<ArrayList<Shape>> shapeLists) {

        this.shapeLists = shapeLists;
    }
    public Button getUp_button() {
        return up_button;
    }
    public Button getDown_button() {
        return down_button;
    }
    public int getLayer_counter() {
        return layer_counter;
    }

    public void setLayer_counter(int layer_counter) {

        this.layer_counter = layer_counter;
    }

    public ArrayList<Shape> getCurrent_layer_list() {
        return current_layer_list;
    }

    public void setCurrent_layer_list(ArrayList<Shape> current_layer_list) {
        this.current_layer_list = current_layer_list;
    }

    public Button getCurrent_selected() {
        return current_selected;
    }

    public LinkedList<Button> getList() {
        return list;
    }
    public Button getAdd_button() {
        return add_button;
    }
    public Button getRemove_button() {
        return remove_button;
    }
    public void setCurrent_selected(Button current_selected) {

        this.current_selected = current_selected;
    }

    public Tooltip getTooltip() {
        return tooltip;
    }

    public void setTooltip(Tooltip tooltip) {
        this.tooltip = tooltip;
    }

}
