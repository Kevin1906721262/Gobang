import javax.swing.*;
import java.awt.*;

public class GobangMain extends JFrame {
    public static void main(String[] args) {
        GobangMain gobang = new GobangMain();
        gobang.init(); //初始化窗体
    }

    public void init() {
        // 继承了JFrame，this表示本类的对象
        this.setTitle("五子棋"); // 五子棋窗体属性，this表示JFrame的实例对象
        this.setSize(650, 550);
        this.setDefaultCloseOperation(3); //可以通过点击窗体右上角的“X”来关闭窗体
        this.setResizable(false); //设置大小不可更改
        this.setLocationRelativeTo(null);//设置窗体位置默认在屏幕中央(注意要先设置好大小，再使用这条语句才生效)
        this.setVisible(true);//设置窗体可见
    }


    @Override
    //Graphics 这是一个画笔类，可以通过 Graphics g = frame.getGraphics();来获取窗体的画笔，画笔有绘制各种图形的方法
    public void paint(Graphics g) {
        super.paint(g);
        // 绘制直线，参数分别是直线的起点(x，y)和终点的(x，y)
        g.drawLine(100, 100, 300, 300);
        // 绘制椭圆，参数是椭圆的起点和半长轴
        g.fillOval(150, 200, 30, 30);
        g.setColor(Color.white);
        g.fillOval(200, 150, 30, 30);
    }
}
