import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GobangListener implements MouseListener,GobangConfig, ActionListener {
    public Graphics g ;
    public int flag = 1;
    public JFrame frame;

    public void setFrame(JFrame frame){
        this.frame = frame;
        this.g = frame.getGraphics();
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
                chessWin(r,c);
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

    public void chessWin(int a,int c) {
        int x,y;
        int n1=0,n2=0,n3=0,n4=0; //横、竖、左斜、右斜四个方向
        if(chessArray[a][c]==1){ //判断黑棋
            for(x = a,y = c-1;y>0&&chessArray[x][y]==1;y--){
                n1+=1;
            }
            for(x = a,y = c+1;y<15&&chessArray[x][y]==1;y++){
                n1+=1;
            }
            for(x = a-1,y = c;x>0&&chessArray[x][y]==1;x--){
                n2++;
            }
            for(x = a+1,y = c;x<15&&chessArray[x][y]==1;x++){
                n2++;
            }
            for(x = a-1,y = c-1;y>0&&x>0&&chessArray[x][y]==1;y--,x--){
                n3++;
            }
            for(x = a+1,y = c+1;y<15&&x<15&&chessArray[x][y]==1;y++,x++){
                n3++;
            }
            for(x = a+1,y = c-1;x<15&&y>0&&chessArray[x][y]==1;x++,y--){
                n4++;
            }
            for(x = a-1,y = c+1;y<15&&x>0&&chessArray[x][y]==1;x--,y++){
                n4++;
            }
            if(n1==4||n2==4||n3==4||n4==4) //除自身之外有4个即结束
                JOptionPane.showMessageDialog(frame, "黑棋赢了");
        }
        else if(chessArray[a][c]==-1){
            for(x = a,y = c-1;y>0&&chessArray[x][y]==-1;y--){
                n1+=1;
            }
            for(x = a,y = c+1;y<15&&chessArray[x][y]==-1;y++){
                n1+=1;
            }
            for(x = a-1,y = c;x>0&&chessArray[x][y]==-1;x--){
                n2++;
            }
            for(x = a+1,y = c;x<15&&chessArray[x][y]==-1;x++){
                n2++;
            }
            for(x = a-1,y = c-1;y>0&&x>0&&chessArray[x][y]==-1;y--,x--){
                n3+=1;
            }
            for(x = a+1,y = c+1;y<15&&x<15&&chessArray[x][y]==-1;y++,x++){
                n3+=1;
            }
            for(x = a+1,y = c-1;x<15&&y>0&&chessArray[x][y]==-1;x++,y--){
                n4++;
            }
            for(x = a-1,y = c+1;y<15&&x>0&&chessArray[x][y]==-1;x--,y++){
                n4++;
            }
            if(n1==4||n2==4||n3==4||n4==4)
                JOptionPane.showMessageDialog(frame, "白棋赢了");
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
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
