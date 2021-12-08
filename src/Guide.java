import java.awt.event.MouseEvent;


public class Guide{
    public double X = -20; /* x-coordinate in orbit's center */
    public double Y = -20; /* y-coordinate in orbit's center */
    void mousePress(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if (mx>60 && mx<210 && my>350 && my<390) StartingClass.State = "guide";
        else if(mx>20 && mx<170 && my>700 && my<740) StartingClass.State="menu";
    }
}
