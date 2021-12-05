import java.awt.Rectangle;
import java.util.ArrayList;

public class NguoiChoi {

    // Constants are Here
    final int JUMPSPEED = -17;
    final int MOVESPEED = 5;

    private int centerX = 500;              //Tọa độ của nhân vật (x theo chiều ngang, y theo chiều dọc)
    private int centerY = -560;
    private boolean jumped = false;             //Nhảy?
    private boolean huongSangTrai = false;         //Sang trái?
    private boolean huongSangPhai = false;        //Sang phải?
    private boolean ngoiXuong = false;             //Ngồi ?
    private static String huongNhin = "phai";  //Hướng nhìn

    private int speedX = 0;                     //Tốc độ theo chiều ngang
    private int speedY = 0;                     //Tốc độ theo chiều dọc
    public static Rectangle rect = new Rectangle(0, 0, 0, 0);
    public static Rectangle rect2 = new Rectangle(0, 0, 0, 0);
    public static Rectangle yellowRed = new Rectangle(0, 0, 0, 0);
    
    public static Rectangle footleft = new Rectangle(0,0,0,0);
    public static Rectangle footright = new Rectangle(0,0,0,0);
    
    
    private final HinhNen bg1 = StartingClass.getHN1();
    private final HinhNen bg2 = StartingClass.getHN2();
    private final HinhNen bg3 = StartingClass.getHN3();
    private final HinhNen bg4 = StartingClass.getHN4();

    private final ArrayList<Dan> dan = new ArrayList<>();

    public void update() {
        if (speedX == 0){
            bg1.setSpeedX(0);
            bg2.setSpeedX(0);
            bg3.setSpeedX(0);
            bg4.setSpeedX(0);
        }
        
        if (centerX <= 800 && speedX > 0) {
            centerX += speedX;
        }
        if (centerX >=400 && speedX<0){
            centerX += speedX;
        }
        
        if (speedX > 0 && centerX > 800) {
            bg1.setSpeedX(-MOVESPEED / 5);
            bg2.setSpeedX(-MOVESPEED / 5);
            bg3.setSpeedX(-MOVESPEED / 5);
            bg4.setSpeedX(-MOVESPEED / 5);
        }
         if (speedX < 0 && centerX < 400){
            bg1.setSpeedX(MOVESPEED / 5);
            bg2.setSpeedX(MOVESPEED / 5);
            bg3.setSpeedX(MOVESPEED / 5);
            bg4.setSpeedX(MOVESPEED / 5);
        }
        // Updates Y Position
        centerY += speedY;
        // Handles Jumping
          speedY += 1;

        if (speedY > 3){
            jumped = true;
        }

        rect.setRect(centerX -55, centerY - 63	, 35, 70);
        rect2.setRect(rect.getX(), rect.getY() + 70, 35, 70);
        yellowRed.setRect(centerX - 92, centerY - 80, 110, 180);

        footleft.setRect(centerX -60, centerY - 33, 5, 95);
        footright.setRect(centerX -20, centerY - 33, 5, 95);


    }

    public void moveRight() {
        huongNhin = "phai";
        if (!ngoiXuong) {
            speedX = MOVESPEED;
        }
    }

    public void moveLeft() {
        huongNhin = "trai";
        if (!ngoiXuong) {
            speedX = -MOVESPEED;
        }
    }

    public void stopRight() {
        setHuongSangPhai(false);
        stop();
    }

    public void stopLeft() {
        setHuongSangTrai(false);
        stop();
    }

    private void stop() {
        if (!ishuongSangPhai() && !ishuongSangTrai()) {
            speedX = 0;
        }

        if (!ishuongSangPhai() && ishuongSangTrai()) {
            moveLeft();
        }

        if (ishuongSangPhai() && !ishuongSangTrai()) {
            moveRight();
        }

    }

    public void jump() {
        if (!jumped) {
            speedY = JUMPSPEED;
            jumped = true;
        }

    }

    public void shoot() {
        Dan p;
        if (getHuongNhin().equals("phai"))
            p = new Dan(centerX-5, centerY-5,true);
        else
            p = new Dan(centerX-5, centerY-5,false);
	dan.add(p);
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public boolean isJumped() {
        return jumped;
    }

    public int getSpeedX() {
        return speedX;
    }


    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public void setJumped(boolean jumped) {
        this.jumped = jumped;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public boolean isngoiXuong() {
        return ngoiXuong;
    }

    public void setngoiXuong(boolean ngoiXuong) {
        this.ngoiXuong = ngoiXuong;
    }

    public boolean ishuongSangPhai() {
        return huongSangPhai;
    }

    public void setHuongSangPhai(boolean huongSangPhai) {
        this.huongSangPhai = huongSangPhai;
    }

    public boolean ishuongSangTrai() {
        return huongSangTrai;
    }

    public void setHuongSangTrai(boolean huongSangTrai) {
        this.huongSangTrai = huongSangTrai;
    }

    public ArrayList getDan() {
        return dan;
    }

   
    public static String getHuongNhin(){
        return huongNhin;
    }

}