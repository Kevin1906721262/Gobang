import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GobangListener implements MouseListener,GobangConfig{
    Graphics g ;
    int flag = 1;

    //接收画笔
    public void setGraphics(Graphics g){
        this.g = g;
    }

    //根据鼠标点击位置交替画黑白棋
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        //确定棋子对应的行列, SIZE_CHESS/2 其作用是让点击位置超过行列间隔一半时 向上取整
        int r = (x-X+SIZE_CHESS/2)/SIZE;
        int c = (y-Y+SIZE_CHESS/2)/SIZE;

        // 棋盘的范围 X < x < X+列数*间隔 , Y < y < Y+行数数*间隔
        if((x>X-SIZE_CHESS/2&&x<X+(ROW-1)*SIZE+SIZE_CHESS/2)&&
                (y>Y-SIZE_CHESS/2&&y<Y+(COL-1)*SIZE+SIZE_CHESS/2)){
            if(chessArray[r][c] == 0){ //表示该点无棋子
                //绘制椭圆时为了让圆心和交点重合，起点坐标需要在左上角
                g.fillOval(r*SIZE+X-SIZE_CHESS/2, c*SIZE+Y-SIZE_CHESS/2, SIZE_CHESS,SIZE_CHESS);
                chessArray[r][c] = flag;
                if(flag == 1){
                    flag = -1;
                    g.setColor(Color.white);
                }else {
                    flag = 1;
                    g.setColor(Color.black);
                }
            }

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
