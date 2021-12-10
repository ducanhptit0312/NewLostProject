public class HinhNen {
	
	private int X, Y, speedX;
	
	public HinhNen(int x, int y){
		this.X = x;
		this.Y = y;
		this.speedX = 0;		//Speed sẽ được dùng để di chuyển nền khi người chơi di chuyển
	}
	public void update() {
		X += speedX;
		if (X <  -1920*3){
			X = 1920;
		}
		if (X >  1920*2){
			X = -1920*2;
		}
	}
	//Các hàm để lấy giá trị các biến
	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}

	public int getSpeedX() {
		return speedX;
	}
	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	
	
	
}
