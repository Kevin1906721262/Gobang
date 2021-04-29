import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GobangListener implements MouseListener {
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

        g.fillOval(x,y,30,30);
        if(flag == 1){
            flag = -1;
            g.setColor(Color.white);
        }else {
            flag = 1;
            g.setColor(Color.black);
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
