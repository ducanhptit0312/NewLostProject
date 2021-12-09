import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StartingClass extends JPanel implements Runnable, KeyListener, MouseListener {

        private boolean ctrl_press;
        ArrayList dan = new ArrayList<>();

        
        
	private static NguoiChoi NguoiChoi;
	private BufferedImage  currentSprite, c0, s0,
                        menu,danPhai,danTrai,
			nhaySangPhai, nhaySangTrai, HinhNen1,HinhNen2, 
                        play,exit,backButton,
                        guideback,guidefront,guideButton,logo,start,
                        deathimg,menubutton,playagain;
    private Sound  Nhacgame,tiengban,deatheffect;
	private static HinhNen bg1, bg2,bg3,bg4;
	public Animation diSangPhai;
    public Animation diSangTrai;
    public Animation anim;
    public Animation ngoiPhai;
    public Animation ngoiTrai;
        public static Animation qv1_trai,qv1_phai;
        
        public static BufferedImage tiledirt,grasstop, tilestone,tiletree,tilerock,wave;
        private static final ArrayList<Tile> tilearray = new ArrayList<>();
        
        static String State = "start";
        Menu MENU;
        Guide GUIDE;
        GameOver Chet;
        
        public static void restart(){               //Khởi động lại
                NguoiChoi.getDan().clear();     //
                tilearray.clear();                  //
                KeDich.keDich.clear();              // xóa hết tất cả
                bg1 = new HinhNen(0, 0);         // Lớp lưu dữ liệu tọa độ hình nền
		bg2 = new HinhNen(1920, 0);      //
                bg3 = new HinhNen(-1920,0);      //
                bg4 = new HinhNen(-1920*2,0);    // 
                NguoiChoi = new NguoiChoi();                //
                try {
                    new StartingClass().loadMap();    //Tải lại map
                } 
                catch (IOException e) {
                    e.printStackTrace();
                }
        }
        
	
	public void init() throws Exception{
                
		setFocusable(true);
		addKeyListener(this);
        addMouseListener(this);
                
		// tải vào các ảnh

                    nhaySangPhai = ImageIO.read(new File("data/jumpedr.png"));
                    nhaySangTrai = ImageIO.read(new File("data/jumpedl.png"));

                    HinhNen1 = ImageIO.read(new File("data/bg1.png"));
                    HinhNen2 = ImageIO.read(new File("data/bg2.png"));

                    wave =  ImageIO.read(new File("data/wave.png"));
                    tiledirt = ImageIO.read(new File("data/tiledirt.png"));     //Đất
                    grasstop = ImageIO.read(new File("data/grasstop.png"));     //Cỏ
                    tilestone = ImageIO.read(new File("data/tilestone.png"));   //Đá tảng
                    tiletree = ImageIO.read(new File("data/tiletree.png"));     //Cây
                    tilerock = ImageIO.read(new File("data/tilerock.png"));//ĐÁ

                    danPhai = ImageIO.read(new File("data/Dan/danPhai.png"));       

                    danTrai = ImageIO.read(new File("data/Dan/danTrai.png"));
                
                    //Hoạt ảnh của nhân vật
                    
                    c0= ImageIO.read(new File("data/DiChuyen/Phai/1.png"));
                    
                    s0 = ImageIO.read(new File("data/DiChuyen/Trai/1.png"));
                    //Nhạc game
                    Nhacgame = new Sound(new File("data/BackgroundMusic.wav"));
                    tiengban = new Sound(new File("data/shoteffect.wav"));
                    deatheffect = new Sound(new File("data/deatheffect.wav"));
                    // Menu Images // Lấy ảnh liên quan đến menu
                    
                    menu = ImageIO.read(new File("data/menu.png"));
                    logo= ImageIO.read(new File("data/logo.png"));
                    play= ImageIO.read(new File("data/play.png"));
                    exit= ImageIO.read(new File("data/exit.png"));
                    guideButton= ImageIO.read(new File("data/guideButton.png"));
                    
                    start = ImageIO.read(new File("data/intro1.png"));
                    
                    deathimg = ImageIO.read(new File("data/deathimg.png"));
                    menubutton = ImageIO.read(new File("data/menuButton.png"));
                    playagain = ImageIO.read(new File("data/playagainButton.png"));
                    
                    
                    backButton= ImageIO.read(new File("data/backButton.png"));
                    
                    
                    //Guide stuff
                    guideback=ImageIO.read(new File("data/guideback.png"));
                    guidefront=ImageIO.read(new File("data/guidefront.png"));
                    
//////////////////Tạo hoạt ảnh (Cần thêm Animation nào thì add vào đây, hàm như mẫu, nhớ / cuối link)
                //Quai Vat 1//
                qv1_trai = new Animation(false);
		themFrameVaoAnimation(qv1_trai, "data/KeDich/QuaiVat1/Trai/", 2, 500);
                qv1_phai = new Animation(false);
		themFrameVaoAnimation(qv1_phai, "data/KeDich/QuaiVat1/Phai/", 2, 500);

                ////Ngồi xuống////
                ngoiPhai = new Animation(true);    
                themFrameVaoAnimation(ngoiPhai, "data/Ngoi/Phai/", 4, 20);
                ngoiTrai = new Animation(true);    
                themFrameVaoAnimation(ngoiTrai, "data/Ngoi/Trai/", 4, 20);

                ////Di chuyển////
                diSangPhai = new Animation(false);
                themFrameVaoAnimation(diSangPhai, "data/DiChuyen/Phai/", 12, 50);  
                diSangTrai = new Animation(false);
                themFrameVaoAnimation(diSangTrai, "data/DiChuyen/Trai/", 12, 50);

                
                anim = diSangPhai;
		currentSprite = c0;  //Bat dau nhan vat nhin sang phai
	}

	
	public void start() {
		bg1 = new HinhNen(0, 0);             //Hình nền trong trò chơi
		bg2 = new HinhNen(1920, 0);          //dài 1920 => hình 2 bắt đầu từ 1920
                bg3 = new HinhNen(-1920,0);          //hình 3 bắt đầu từ bên trái, -1920
                bg4 = new HinhNen(-1920*2,0);        //
                NguoiChoi = new NguoiChoi();                    //Tạo đối tượng NguoiChoi(người chơi)
                MENU = new Menu();                      //
                GUIDE= new Guide();                     //
                Chet = new GameOver();           //Tạo các đối tượng

                Nhacgame.playloop();
                // Initialize Tiles
                try {
                    loadMap();//Tải map

                } 
                catch (IOException e) {
                    e.printStackTrace();
                }
                
		Thread thread = new Thread(this);
		thread.start();
	}


	@Override
	public void run() {
		while (true) {

            //Khi state là game, sau khi bấm play => cập nhật liên tục
            if ("game".equals(State)) {
                gameUpdate();
            }
			repaint();
                        
			try {
				Thread.sleep(17);
			} 
                        catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
    }

    public void gameUpdate(){

        NguoiChoi.update();
        bg1.update();
        bg2.update();
        bg3.update();
        bg4.update();
        KeDich.update();
        updateTiles();

        ////Của kẻ địch
        qv1_trai.update(50);
        qv1_phai.update(50);
        //....
        //....
        /////////////////

        ArrayList dan = NguoiChoi.getDan();
        for (int i = 0; i < dan.size(); i++) {
            Dan p = (Dan) dan.get(i);
            if (p.isVisible()) {
//                    tiengban.playloop();
                p.update();
            } else {
                dan.remove(i);
            }
        }

        for (int i = 0; i < KeDich.keDich.size(); i++){
            if (KeDich.keDich.get(i).getIsDead())
                KeDich.keDich.remove(i);
        }

        if (NguoiChoi.isJumped() && NguoiChoi.getHuongNhin().equals("phai")) {
            currentSprite = nhaySangPhai;
        }
        else if (NguoiChoi.isJumped() && NguoiChoi.getHuongNhin().equals("trai")) {
            currentSprite = nhaySangTrai;
        }
        else if ((NguoiChoi.getHuongNhin().equals("phai")) && (NguoiChoi.getSpeedX() == 0)&& !NguoiChoi.isngoiXuong()){
            currentSprite = c0;
        }
        else if ((NguoiChoi.getHuongNhin().equals("trai")) && (NguoiChoi.getSpeedX() == 0) && !NguoiChoi.isngoiXuong()){
            currentSprite = s0;
        }

        else {
            if (NguoiChoi.getSpeedX()<0){
                anim = diSangTrai;
            }
            else if (NguoiChoi.getSpeedX()>0){
                anim = diSangPhai;
            }
            else if (NguoiChoi.isngoiXuong() && NguoiChoi.getHuongNhin().equals("phai")){
                anim = ngoiPhai;
            }
            else if (NguoiChoi.isngoiXuong() && NguoiChoi.getHuongNhin().equals("trai")){
                anim = ngoiTrai;
            }
            currentSprite = anim.getImage();
            anim.update(10);
        }
        if (NguoiChoi.getCenterY()>1400){
            State = "dead";
        }
    }


        @Override
	public void paint(Graphics g) {         //Vẽ lên màn hình/ Gán ảnh
            
            switch (State) {                    //Xét từng state hiện tại
                
                case "start":
                    g.drawImage(start, 0, 0, this);
                    break;
                
                case "menu":        // Đây là ngoài menu
                    
                    try{
                    g.drawImage(menu, (int) MENU.X,(int)MENU.Y,this);
                    g.drawImage(logo, 510, 32, this);
                    g.drawImage(play,60,250,this);
                    g.drawImage(guideButton,60,300, this);
                    g.drawImage(exit,60,350,this);
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }
                    break;
                    
                case "dead":                    //Khi chết
                    g.drawImage(deathimg, (int) Chet.X,(int)Chet.Y,this);
                    g.drawImage(menubutton,60,350, this);
                    g.drawImage(playagain,60,400, this);
                    break;
                    
                case "guide":
                    g.drawImage(guideback,(int)GUIDE.X,(int)GUIDE.Y, this);
                    g.drawImage(guidefront,478,170, this);
                    g.drawImage(backButton, 20,700, this);
                    break;
                    
                case "game": // Nền của các màn
                    
                    g.drawImage(HinhNen1, bg1.getX(), bg1.getY(), this); 
                    g.drawImage(HinhNen2, bg2.getX(), bg2.getY(), this);
                    g.drawImage(HinhNen2, bg3.getX(), bg3.getY(), this);
                    g.drawImage(HinhNen1, bg4.getX(), bg4.getY(), this);
                    hienThiTiles(g);
                    hienThiDan(g);
                    paintEnemies(g);
                    g.drawImage(currentSprite, NguoiChoi.getCenterX() - 61,NguoiChoi.getCenterY() - 63, this);
                    break;
            }
	}
        
        
        private void updateTiles() {

            for (Tile tile : tilearray) {
                tile.update();
            }
	}
        
        private void paintEnemies(Graphics g){
            for (KeDich i: KeDich.keDich){
                        if (i.getCenterX()>-50 && i.getCenterX()<1366){     //Vị trí mà quái hiển thị (ngoài khoảng này) => ngoài màn hình => ko hiển thị
                            if (i.huongNhin.equals("phai"))
                                g.drawImage(qv1_phai.getImage(), i.getCenterX() - 48, i.getCenterY() - 48, this);
                            else if (i.huongNhin.equals("trai"))
                                g.drawImage(qv1_trai.getImage(), i.getCenterX() - 48, i.getCenterY() - 48, this);
                        }
                    }
        }
        
        private void hienThiDan(Graphics g){
                    dan = NguoiChoi.getDan();

            for (Object o : dan) {
                Dan p = (Dan) o;

                if (NguoiChoi.getHuongNhin().equals("phai"))
                    g.drawImage(danPhai, p.getX(), p.getY(), this);
                else if (NguoiChoi.getHuongNhin().equals("trai"))
                    g.drawImage(danTrai, p.getX(), p.getY(), this);
            }
        }
        
	private void hienThiTiles(Graphics g) {
        for (Tile tile : tilearray) {
            g.drawImage(tile.getTileImage(), tile.getTileX(), tile.getTileY(), this);
        }
	}
    private void loadMap() throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        int width = 0;

        BufferedReader reader = new BufferedReader(new FileReader("data/map1.txt"));
        while (true) {
            String line = reader.readLine();
            // no more lines to read
            if (line == null) {
                reader.close();
                break;
            }
            if (!line.startsWith("!")) {                //Các dòng bắt đầu = ! thì không đọc
                lines.add(line);                        //Cho thêm vào ArayList
                width = Math.max(width, line.length()); //Chiều dài của string mô tả nền trong file

            }
        }

        for (int n = 0; n < 21; n++) {      //Tối thiểu 22 dòng (cái này là để vừa vào màn hình)
            String line = lines.get(n);    //Lấy ra từng dòng trong arrayList lines
            for (int m = 0; m < width; m++) {       //Xét dọc map (lấy độ dài dòng dài nhất)

                if (m < line.length()) {            //Chỉ dọc map có dòng dài dòng ngắn (chỉ xet độ dài dòng hiện tại)
                    char ch = line.charAt(m);
                    if (ch == '*'){                 // * là quái vật
                        KeDich.keDich.add(new KeDich(m*40, n*40-80));    // Hai thông số là tọa độ x, y của quái vật
                    }
                    else{                           //Các ô khác được phân số, ứng với mỗi số => loại gạch
                        Tile t = new Tile(m, n, Character.getNumericValue(ch));   //Tham số 3 là số trong file txt <=> loại gạch
                        tilearray.add(t);
                    }
                }

            }
        }
    }
    private void themFrameVaoAnimation(Animation A, String path, int soLuong, int t) throws IOException{ //t là thời gian 1 khung hình
        for(int i = 1; i<= soLuong; i++){
            A.addFrame(ImageIO.read(new File(path+i+".png")), t);
        }
    }
	@Override
	public void keyPressed(KeyEvent e) {
        if (State.equals("start")) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE)
                State = "menu";
        }

        if (State.equals("guide")) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                State = "menu";
            }
        }
        if (State.equals("game")) {     //Khi trạng thái đang là game
            switch (e.getKeyCode()) {
                case KeyEvent.VK_ESCAPE ->    //Bấm Esc
                        State = "menu";       //Chuyển state sang "menu"
                case KeyEvent.VK_UP ->                    //Bấm lên
                        System.out.println("Move up");  //Nó in ra (cái này để test)
                case KeyEvent.VK_DOWN ->                  //Ngồi
                        NguoiChoi.setngoiXuong(true);
                case KeyEvent.VK_LEFT -> NguoiChoi.moveLeft();
                case KeyEvent.VK_RIGHT -> NguoiChoi.moveRight();
                case KeyEvent.VK_SPACE -> NguoiChoi.jump();
                case KeyEvent.VK_CONTROL ->             //Bấm Ctrl
                        ctrl_press = true;
            }
        }
    }

	@Override
	public void keyReleased(KeyEvent e) {
                
            
            switch (e.getKeyCode()) {
                
		case KeyEvent.VK_UP:
			break;

		case KeyEvent.VK_DOWN:
                    if (State.equals("game")){
                        ngoiPhai.Index = 0;
                        ngoiTrai.Index = 0;
			NguoiChoi.setngoiXuong(false);
                    }
			break;

		case KeyEvent.VK_LEFT:
                        if (State.equals("game"))
                        	NguoiChoi.stopLeft();
			break;

		case KeyEvent.VK_RIGHT:
                        if (State.equals("game"))
        			NguoiChoi.stopRight();
			break;

		case KeyEvent.VK_SPACE:
                        if (State.equals("game")){
                            NguoiChoi.setHuongSangTrai(false);
                            NguoiChoi.setHuongSangPhai(false);
                        }
			break;
                
                case KeyEvent.VK_CONTROL:
                    if (State.equals("game")){
                        if (ctrl_press){
                            NguoiChoi.shoot();
                        }
                    }
                    break;

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
            
	}


    @Override
    public void mouseClicked(MouseEvent me) {
      
    }

    @Override
    public void mousePressed(MouseEvent me) {           //Bấm chuột vào các nút
        
        switch (State){
            case "dead":
                Chet.mousePress(me);
                break;
            case "menu":
                MENU.mousePress(me);
                break;
            case "guide":
                GUIDE.mousePress(me);
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        

    }

    @Override
    public void mouseEntered(MouseEvent me) {
       
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }
    
        // Mấy hàm lấy các biến nếu cần
        
        public static HinhNen getHN1() {
		return bg1;
	}

	public static HinhNen getHN2() {
		return bg2;
	}
        
        public static HinhNen getHN3(){
            return bg3;
        }
        
        public static HinhNen getHN4(){
            return bg4;
        }
        
        public static NguoiChoi getNguoiChoi(){
		return NguoiChoi;
	}
        public static ArrayList<Tile> getTileArray(){
            return tilearray;
        }

}