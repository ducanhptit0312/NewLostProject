import java.awt.Rectangle;
import java.util.ArrayList;

public class Dan { //Đạn bắn ra từ người chơi

	private int x;
	private final int y;
	private final int speedX;
	private boolean visible;
	private Rectangle r;
	public Dan(int startX, int startY, boolean huongSangPhai){
		this.x = startX;
		this.y = startY;
		if (huongSangPhai)
			this.speedX =30;
		else
			this.speedX = -30;
		visible = true;
		r = new Rectangle(0, 0, 0, 0);
	}
	public void update(){
		x += speedX;
		r.setBounds(x, y, 10, 5);
		if (x > 1920 || x<0){               //Ra khỏi màn hình thì bỏ đi, hiển thị làm gì
			visible = false;
			r = null;
		}
		else
			checkCollision();
	}
	private void checkCollision() {         //Kiểm tra va chạm
		ArrayList tiles = StartingClass.getTileArray();
		for (Object tile : tiles) {
			Tile p = (Tile) tile;
			if (r != null) {
				if (r.intersects(p.getRectangle()) && (p.getType() == 2 || p.getType() == 3 || p.getType() == 6)) {
					if (NguoiChoi.getHuongNhin().equals("phai"))
						x = p.getTileX() - 35;
					else if (NguoiChoi.getHuongNhin().equals("trai"))
						x = p.getTileX() + 50;
					visible = false;
				}
			}
		}
		for (KeDich i: KeDich.keDich){
			assert r != null;
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
