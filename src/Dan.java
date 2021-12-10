import java.awt.Rectangle;
import java.util.ArrayList;

public class Dan { //Đạn bắn ra từ người chơi
	
	private int x;				//Tọa độ của đạn
	private final int y;
	private final int speedX;		//Tốc độ
	private boolean visible;		//Có thể nhìn thấy?	
	private Rectangle r;			//Hình chữ nhật của đạn
	public Dan(int startX, int startY, boolean huongSangPhai){		//Khởi tạo đối tượng Đạn, truyền vào tọa độ và hướng (theo hướng nhìn của nhân vật)
		this.x = startX;
		this.y = startY;
		if (huongSangPhai)						//Ở dưới
			this.speedX = 15;			
		else
			this.speedX = -15;
		visible = true;
		r = new Rectangle(0, 0, 0, 0);
	}
	public void update(){
		x += speedX;							//Hướng sang phải là dương => x tăng => hình chữ nhật dịch sang phải, trái thì ngược lại
		r.setBounds(x, y, 10, 5);
		if (x > 1460 || x<0){               //Ra khỏi màn hình thì bỏ đi
			visible = false;	    //Không nhìn thấy nữa
			r = null;		    //Xóa hình chữ nhật tương ứng
		}
		else
			checkCollision();	//Nếu chưa ra khỏi màn hình thì kiểm tra va chạm
	}
	private void checkCollision() {         //Kiểm tra va chạm
		ArrayList tiles = StartingClass.getTileArray();
		for (Object tile : tiles) {			//Xét các đối tượng tile (gạch, đất,...)
			Tile p = (Tile) tile;
			if (r != null) {
				if (r.intersects(p.getRectangle()) && (p.getType() == 2 || p.getType() == 3 || p.getType() == 6)) {	//Nếu hình chữ nhật chứa đạn giao với 1 trong các hình chữ nhật tile thuộc các loại này (xem map)
					if (NguoiChoi.getHuongNhin().equals("phai"))			//Nếu không có cái này thì đạn sẽ đâm vào tile xong mới mất đi, nhìn xấu
						x = p.getTileX() - 35;
					else if (NguoiChoi.getHuongNhin().equals("trai"))
						x = p.getTileX() + 50;
					visible = false;
				}
			}
		}
		for (KeDich i: KeDich.keDich){				//Xét va chạm với kẻ địch
			assert r != null;				//Giả sử r khác null
			if(r.intersects(i.r)){
				visible = false;
				if (i.health > 0) {
					i.health -= 1;
				}
				if (i.health == 0) {
					i.setIsDead(true);
				}
			}
		}
	}
//Các hàm lấy giá trị khi cần
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public boolean isVisible() {
		return visible;
	}
}
