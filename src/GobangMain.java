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
        this.setSize(650, 650);
        this.setDefaultCloseOperation(3); //可以通过点击窗体右上角的“X”来关闭窗体
        this.setResizable(false); //设置大小不可更改
        this.setLocationRelativeTo(null);//设置窗体位置默认在屏幕中央(注意要先设置好大小，再使用这条语句才生效)
        this.setVisible(true);//设置窗体可见

        //需要在窗体可见之后再获取画笔
        Graphics g = this.getGraphics();
        GobangListener gl = new GobangListener();
        gl.setGraphics(g); //传递画笔,在GobangListener中需要用画笔进行绘制

        //给窗体绑定鼠标监听处理事件，不绑定则点击无效
        this.addMouseListener(gl);
    }


    @Override
    //Graphics 这是一个画笔类，可以通过 Graphics g = frame.getGraphics();来获取窗体的画笔，画笔有绘制各种图形的方法
    public void paint(Graphics g) {
        super.paint(g);
        int row= 15, col= 15;
        //棋盘左上角坐标
        int x = 30, y = 60;
        int size= 35;
        // 画15条横线
        for (int i = 0; i < row; i++) {
            // 绘制直线，参数分别是直线的起点(x，y)和终点的(x，y)
            g.drawLine(x, y + i * size, x + (row- 1) * size, y + i * size);
        }
        // 画15条竖线
        for (int j = 0; j < col; j++) {
            g.drawLine(x + j * size, y + (row- 1) * size, x + j * size, y);
        }
    }
}
