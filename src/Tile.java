
import java.awt.Image;
import java.awt.Rectangle;

public class Tile {
    
    private int tileX;
    private final int tileY;
    private int type;
    public Image tileImage;
    private final NguoiChoi NguoiChoi = StartingClass.getNguoiChoi();
    private final HinhNen bg = StartingClass.getHN1();
    private final Rectangle r;

    
    public Tile(int x, int y, int typeint){     //Tạo các đối tượng trong map
        tileX = x*40;
        tileY = y*40;
        type = typeint;
        r = new Rectangle();    
        if (type == 2)
            tileImage = StartingClass.grasstop; 
        else if (type == 3)
            tileImage = StartingClass.tiledirt;
        else if (type == 4)
            tileImage = StartingClass.tilestone;
        else if (type == 5)
            tileImage = StartingClass.tiletree;
        else if (type == 6)
            tileImage = StartingClass.tilerock;
        else if (type == 8)
            tileImage = StartingClass.wave;
        else {
            type = 0;
        }
    }
    public void update(){
        int speedX = bg.getSpeedX() * 5;
        tileX += speedX;  
        r.setBounds(tileX, tileY, 40, 40);
        
        if (r.intersects(NguoiChoi.yellowRed) && type != 0) {
                                
				checkVerticalCollision(NguoiChoi.rect, NguoiChoi.rect2);
				checkSideCollision(NguoiChoi.footleft, NguoiChoi.footright);
			}
        for (KeDich i: KeDich.keDich){
                if(r.intersects(i.r)  && ( type == 2 || type == 3 || type == 6 )){
                        i.setSpeedX(0);
                        if (i.getCenterX()<tileX)
                            i.setCenterX(tileX-55);
                        else if (i.getCenterX()>tileX)
                            i.setCenterX(tileX+56);
                    }
            }
    }
    
   public int getTileX() {
        return tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public Rectangle getRectangle(){
        return r;
    }
    public int getType(){
        return type;
    }

    public Image getTileImage() {
        return tileImage;
    }

    public void checkVerticalCollision(Rectangle rtop, Rectangle rbot) {    //Va chạm theo chiều dọc
        if ( type == 2 || type == 3 || type == 6 ){     
            if (rtop.intersects(r)) {                       
                NguoiChoi.setCenterY(tileY+100);            //Xóa cũng được
                NguoiChoi.setSpeedY(-NguoiChoi.JUMPSPEED);      //Tốc độ khi chạm vào (âm là hướng ngược lại => đụng đầu rơi xuống
            }

            if (rbot.intersects(r)) {
                NguoiChoi.setJumped(false);         //Đáp xuống thì bỏ trạng thái nhảy đi
                NguoiChoi.setSpeedY(0);             //Vận tốc khi đứng trên đó, dương thì trượt về sau
                NguoiChoi.setCenterY(tileY - 75);   //Thiết lập vị trí sau khi chạm, -75 là ở trên vật đó
            }
        }
    }
    
     public void checkSideCollision(Rectangle leftfoot, Rectangle rightfoot) {  //Kiểm tra va chạm theo chiều ngang
        if (type == 2 || type == 3 || type == 6 ){
            
           if (leftfoot.intersects(r)) {
                NguoiChoi.setCenterX(tileX + 100);      //Đi đến từ bên phải, cứ đến thì sẽ bị dịch về bên phải => ko vượt qua được
                NguoiChoi.setSpeedX(0);                 //Dừng lại
            }
            
            else if (rightfoot.intersects(r)) {     //Ngược lại
                NguoiChoi.setCenterX(tileX + 15);
                NguoiChoi.setSpeedX(0);
            }
        }
    }

}