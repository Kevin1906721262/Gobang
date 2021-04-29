import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GobangListener implements MouseListener ,GobangConfig{
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
        // 棋盘的范围 X < x < X+列数*间隔 , Y < y < Y+行数数*间隔
        if((x>X-SIZE_CHESS/2&&x<X+(ROW-1)*SIZE+SIZE_CHESS/2)&&
                (y>Y-SIZE_CHESS/2&&y<Y+(COL-1)*SIZE+SIZE_CHESS/2)){
            g.fillOval(x,y,30,30);
            if(flag == 1){
                flag = -1;
                g.setColor(Color.white);
            }else {
                flag = 1;
                g.setColor(Color.black);
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
