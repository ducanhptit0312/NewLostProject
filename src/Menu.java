import java.awt.event.MouseEvent;


public class Menu{
    public double X = -700;
    public double Y = -600;
    void mousePress(MouseEvent e) {     //Các vị trí nhấn chuột trên màn hình
        int mx = e.getX();
        int my = e.getY();
        if (mx>60 && mx<210 && my>250 && my<290) //Play button
            StartingClass.State = "game";
        else if (mx>60 && mx<210 && my>300 && my<340) // exit
            System.exit(1);
    }
}
