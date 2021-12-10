import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Animation {
    ArrayList<Anh> frames;           //ArrayList chứa các đối tượng (từng ảnh)
    int Index;           //Index của ArrayList
    boolean lapLai;               //Có lặp không
    private long length;      //Đếm thời gian hoạt hình
    private long sumLength;     //Tổng thời gian
    public Animation(boolean l) {   //Khai báo gọi phương  thức animation, truyền vào boolean l
        lapLai = l;               //Lặp lại không
        frames = new ArrayList<>();   //Khởi tạo ArrayList đối tượng các ảnh
        sumLength = 0;          //Thời gian đếm
        synchronized (this) {
            length = 0;           //Đồng bộ
            Index = 0;
        }
    }
    public synchronized void addFrame(BufferedImage image, long a) { //Thêm ảnh vào trong tập hoạt ảnh
        sumLength += a;                                      //Hoạt ảnh càng ngày càng dài ra
        frames.add(new Anh(image, sumLength));                //ArrayList frame
    }
    public synchronized void update(long elapsedTime) {//Cập nhật hoạt ảnh theo thời gian đã trôi qua
        if (frames.size() > 1) {
            length += elapsedTime;
            if (!(lapLai && Index == frames.size() - 1)) {
                if (length >= sumLength) {
                    length = length % sumLength;
                    Index = 0;
                }
                while (length > getFrame(Index).endTime) {
                    Index++;
                }
            }
        }
    }
    public synchronized BufferedImage getImage() {
        if (frames.size() == 0) {
            return null;
        } else {
            return getFrame(Index).image;
        }
    }
    private Anh getFrame(int i) {
        return frames.get(i);
    }
    private static class Anh {
        BufferedImage image;
        long endTime;
        public Anh(Image image, long endTime) {
            this.image = (BufferedImage) image;
            this.endTime = endTime;
        }
    }
}
