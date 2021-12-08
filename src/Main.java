import java.awt.*;
import javax.swing.*;


public class Main {
    public static void main(String[] args){
        JFrame frame = new JFrame("LOST");      //Tạo cửa số
        frame.setSize(1366, 768);
        frame.setBackground(Color.black);
        StartingClass game = new StartingClass();   //
        frame.add(game);
        //frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Thoát frame thì sẽ thoát chương trình
        try{
            game.init();
            game.start();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

