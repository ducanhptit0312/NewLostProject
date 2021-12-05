import java.awt.event.MouseEvent;


public class Menu{
    
    public double X = -700; /* x-coordinate in orbit's center */
    public double Y = -600; /* y-coordinate in orbit's center */
  
    
    void mousePress(MouseEvent e) {     //Các vị trí nhấn chuột trên màn hình
        int mx = e.getX();
        int my = e.getY();
        
        if (mx>60 && mx<210 && my>250 && my<290) //Play button
            StartingClass.State = "game";
        else if (mx>60 && mx<210 && my>300 && my<340) // intro button
            StartingClass.State = "guide";
        else if (mx>60 && mx<210 && my>350 && my<390) //guide button
            System.exit(1);
//        else if (mx>60 && mx<210 && my>300 && my<340) // intro button
//            StartingClass.State = "intro";
//        else if (mx>60 && mx<210 && my>350 && my<390) //guide button
//            StartingClass.State = "guide";
//        else if (mx>60 && mx<210 && my>400 && my<440) // credits button
//            StartingClass.State = "credits";
//        else if(mx>60 && mx<210 && my>450 && my<490)
//            System.exit(1);
        
    }
}
