import java.awt.*;
public class Tooltip {
    private static Tooltip instance;
    private Button selected_button;
    private String message;
    private boolean position_help = false;
    private Tooltip() {
        selected_button = null;
    }
    public static Tooltip getInstance(){
        if (instance == null){
            instance = new Tooltip();
        }
        return instance;
    }

    public boolean confirm_tooltip(int x, int y, Button button) {
        if (x > button.x && x < button.x + button.getWidth() && y > button.y && y < button.y + button.getHeight()) {
            this.selected_button = button;
            return true;
        } else{
            this.selected_button = null;
            return false;
    }
    }
        public void draw_tooltip(Graphics g){
        if (selected_button!=null){
            int x = this.selected_button.x;
            int y = this.selected_button.y;
            Font temp = g.getFont();
            draw_rect(x,y,message.length()*7,g);
            Font font = new Font("Times New Roman",Font.BOLD,12);
            g.setFont(font);
            if (position_help){
                g.drawString(message, x-33, y+13);
            }
            else
                g.drawString(message,x+25,y+13);
            g.setFont(temp);
        }
    }
        public void draw_rect(int x, int y, int width, Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(new Color(160, 160, 160));
        if (position_help)
            g2.fillRect(x-35,y,width,20);
        else
            g2.fillRect(x+20,y,width,20);
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2.0f));
        if (position_help)
            g2.drawRect(x-35,y,width,20);
        else
            g2.drawRect(x+20,y,width,20);
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(0));
    }

    public Button getSelected_button() {
        return selected_button;
    }

    public void setSelected_button(Button selected_button) {
        this.selected_button = selected_button;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static void setInstance(Tooltip instance) {
        Tooltip.instance = instance;
    }

    public boolean isPosition_help() {
        return position_help;
    }

    public void setPosition_help(boolean position_help) {
        this.position_help = position_help;
    }
}
