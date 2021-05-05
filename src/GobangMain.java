import javax.swing.*;
import java.awt.*;

public class GobangMain extends JFrame implements GobangConfig{
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
        gl.setFrame(this);
        //给窗体绑定鼠标监听处理事件，不绑定则点击无效
        this.addMouseListener(gl);
    }


    @Override
    //Graphics 这是一个画笔类，可以通过 Graphics g = frame.getGraphics();来获取窗体的画笔，画笔有绘制各种图形的方法
    public void paint(Graphics g) {
        super.paint(g);
        /*
			ROW 棋盘的行数
			常量本可以直接定义在这个类中，但是考虑到这些常量在其它类中也需要使用，多个类中都需要使用同样的常量
			就考虑使用接口，之后如果修改棋盘的行数、或者棋子的大小，直接在接口中修改一次就可以了
		*/
        for (int i = 0; i < ROW; i++) {
            // 绘制直线，参数分别是直线的起点(x，y)和终点的(x，y)
            g.drawLine(X, Y + i * SIZE, X + (ROW - 1) * SIZE, Y + i * SIZE); // 画横线
        }
        for (int j = 0; j < COL; j++) {
            g.drawLine(X + j * SIZE, Y + (ROW - 1) * SIZE, X + j * SIZE, Y); // 画竖线
        }
        drawChess(g);
    }

    // 重绘棋子
    public void drawChess(Graphics g) {
        for (int a = 0; a < chessArray.length; a++)
            for (int c = 0; c < chessArray.length; c++)
                if (chessArray[a][c] != 0) // 用二维数组对应棋子位置，无子为0，黑子为1，白子为-1
                    if (chessArray[a][c] == 1) {
                        g.setColor(Color.black); //设置画笔为黑色
						/*
						fillOval方法四个参数：分别是椭圆的x、y坐标和两个半轴的长度
						思考：如果(4,5) 这个坐标上有一个黑色的棋子，怎么计算出棋盘上对应位置的x,y值
						x = 左上角点X + 4 * 棋盘间隔 - 棋子的半径(为了让棋子的中心落在棋盘的交点上 )
						*/
                        g.fillOval(X + a * SIZE - SIZE_CHESS / 2, c * SIZE + Y
                                - SIZE_CHESS / 2, SIZE_CHESS, SIZE_CHESS);
                    } else {
                        g.setColor(Color.white);
                        g.fillOval(a * SIZE + X - SIZE_CHESS / 2, c * SIZE + Y
                                - SIZE_CHESS / 2, SIZE_CHESS, SIZE_CHESS);
                    }
    }
}
