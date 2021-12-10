import java.awt.Rectangle;
import java.util.ArrayList;
public class KeDich {
	private int speedX;
	private int centerX;
	private int centerY;
	private final HinhNen bg = StartingClass.getHN1();
	public Rectangle r = new Rectangle(0,0,0,0);
	public int health = 5;
	private boolean isdead;
	public static ArrayList<KeDich> keDich = new ArrayList<>();
	private int movementSpeed;
	public String huongNhin = "trai";

	public KeDich(int centerX, int centerY) {
		setCenterX(centerX);
		setCenterY(centerY);
	}
	// Cập nhật hành vi của quái vật
	public static void update() {
		for (KeDich i: keDich) {
			i.follow();// Quái vật theo nhân vật chính
			i.centerX += i.speedX;
			i.speedX = i.bg.getSpeedX() * 5 + i.movementSpeed;
			i.r.setBounds(i.centerX - 30, i.centerY-10, 85, 60);
			if (i.r.intersects(NguoiChoi.yellowRed))
				i.checkCollision();
		}

	}
        
	public void follow() {      // Xuất hiện theo người chơi
		if (Math.abs(centerX - StartingClass.getNguoiChoi().getCenterX()) >650){      //Hàm getNguoiChoi() trả về đối tượng NguoiChoi, getCenterX() của NguoiChoi trả về tọa độ Ox của người chơi
			this.movementSpeed = 0;                                     // Cái này tức là cách 1 khoảng nào đó thì quái không đi theo người chơi nữa
		}
		else if (Math.abs(StartingClass.getNguoiChoi().getCenterX() - centerX) < 5) {
			this.movementSpeed = 0;
		}
		else {
			if (StartingClass.getNguoiChoi().getCenterX() >= centerX) {//CenterX của nhân vật lớn hơn của kẻ địch
				this.huongNhin = "phai";
				this.movementSpeed = 2;
			} 
			else {
				this.huongNhin = "trai";
				this.movementSpeed = -2;
			}
		}
	}
        
	private void checkCollision() {//Nếu va chạm quái vật va chạm với nhân vật thì đưa về trạng thái chết
		if (r.intersects(NguoiChoi.rect) || r.intersects(NguoiChoi.rect2)){
			StartingClass.State = "dead";
		}
	}
	//Các hàm để lấy giá trị các biến
	public int getCenterX() {
		return centerX;
	}
	public int getCenterY() {
		return centerY;
	}
	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}
	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}
	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}
	public boolean getIsDead(){
		return isdead;
	}
	public void setIsDead(boolean s){
		isdead = s;
	}
}

