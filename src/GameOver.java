import java.awt.event.MouseEvent;
import java.io.File;


public class GameOver{       //Lớp này quản lý mành hình hiển thị sau khi chết

    public double X = -110; /* x-coordinate in orbit's center */    //Vị trí đặt ảnh
    public double Y = -60; /* y-coordinate in orbit's center */
    void mousePress(MouseEvent e) {
        int mx = e.getX();              //Cái này là lấy vị trí của chuột
        int my = e.getY();
        
        StartingClass.restart();//Thua thì sẽ thiết lập lại tất cả thông số

        if (mx>60 && mx<210 && my>350 && my<390){   //Đây là vị trí click chuột
            StartingClass.State = "menu";
        }
        else if(mx>60 && mx<210 && my>400 && my<440){
            StartingClass.State="game";
        }
    }
}
