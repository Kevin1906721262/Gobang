import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// MouseListener鼠标事件处理接口 重写方法处理 窗体上监听到的鼠标点击、按下、释放、移动、拖拽等事件
// ActionListener动作事件监听接口 重写actionPerformed方法处理按钮等组件的监听事件,获取按钮String等
public class GobangListener implements MouseListener,GobangConfig, ActionListener {

    private Graphics g ;
    private int player_chess = 1; //
    private int game_mode;
    private int first_chess;
    private JFrame frame;
    private JRadioButton[] radioButtons;
    private boolean isready = false;

    public GobangListener(JRadioButton[] radioButtons) {
        this.radioButtons = radioButtons;
        radioButtons[0].setSelected(true); //默认为人人对战
        radioButtons[2].setSelected(true); //默认黑棋先下
    }

    public void setFrame(JFrame frame){
        this.frame = frame;
        this.g = frame.getGraphics();
    }
//
//    public void setRB(JRadioButton[] radioButtons) {
//        this.radioButtons = radioButtons;
//        radioButtons[0].setSelected(true); //默认为人人对战
//        radioButtons[2].setSelected(true); //默认黑棋先下
//    }

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

            if(!isready){
                JOptionPane.showMessageDialog(frame, "请先开始游戏");
                return ;
            }

            if(chessArray[r][c] == 0){ //表示该点无棋子
                if(player_chess == -1){
                    g.setColor(Color.white);
                }else {
                    g.setColor(Color.black);
                }
                //绘制椭圆时为了让圆心和交点重合，起点坐标需要在左上角
                g.fillOval(r*SIZE+X-SIZE_CHESS/2, c*SIZE+Y-SIZE_CHESS/2, SIZE_CHESS,SIZE_CHESS);
                chessArray[r][c] = player_chess;
                chessWin(r,c);
                player_chess = -player_chess;

            }

        }

    }

    private void chessWin(int a,int c) {
        int x,y;
        int n1=0,n2=0,n3=0,n4=0; //横、竖、左斜、右斜四个方向

        for(x = a,y = c-1;y>0&&chessArray[x][y]==player_chess;y--){
            n1+=1;
        }
        for(x = a,y = c+1;y<15&&chessArray[x][y]==player_chess;y++){
            n1+=1;
        }
        for(x = a-1,y = c;x>0&&chessArray[x][y]==player_chess;x--){
            n2++;
        }
        for(x = a+1,y = c;x<15&&chessArray[x][y]==player_chess;x++){
            n2++;
        }
        for(x = a-1,y = c-1;y>0&&x>0&&chessArray[x][y]==player_chess;y--,x--){
            n3++;
        }
        for(x = a+1,y = c+1;y<15&&x<15&&chessArray[x][y]==player_chess;y++,x++){
            n3++;
        }
        for(x = a+1,y = c-1;x<15&&y>0&&chessArray[x][y]==player_chess;x++,y--){
            n4++;
        }
        for(x = a-1,y = c+1;y<15&&x>0&&chessArray[x][y]==player_chess;x--,y++){
            n4++;
        }
        if(n1==4||n2==4||n3==4||n4==4) { //除自身之外有4个即结束
            if (player_chess == 1)
                JOptionPane.showMessageDialog(frame, "黑棋赢了");
            else
                JOptionPane.showMessageDialog(frame, "白棋赢了");
            isready = false;
            //可选择对战模式和先后手切换
            for (int i = 0; i < radioButtons.length; i++) {
                radioButtons[i].setEnabled(true);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 判断按钮点击事件
        if (e.getActionCommand() == "认输") {
            JOptionPane.showMessageDialog(frame,"正在开发中，敬请期待");
        } else if (e.getActionCommand() == "悔棋") {
            JOptionPane.showMessageDialog(frame,"正在开发中，敬请期待");
        } else if (e.getActionCommand() == "开始新游戏") {// 实现开始新游戏功能
            funcStart();
        }
    }

    private void funcStart() {

        selectMode();   // 判断游戏类型及先后手

        // 清空棋子,重绘棋盘
        for (int p = 0; p < ROW; p++)
            for (int q = 0; q < COL; q++) {
                chessArray[p][q] = 0;
            }
        frame.repaint();
        if (game_mode == -1) {
            JOptionPane.showMessageDialog(frame,"正在开发中，敬请期待");
            return;
        }
        //对战模式和先后手禁止切换
        for (int i = 0; i < radioButtons.length; i++) {
            radioButtons[i].setEnabled(false);
        }

        isready = true; // 表示可以下棋了
        // 人机对战且电脑(白棋)先手

    }

    private void selectMode() {
        if (radioButtons[0].isSelected()) { // 人人对战被选中
            game_mode = 1;
        }
        if (radioButtons[1].isSelected()) { // 人机对战被选中
            game_mode = -1;
        }
        if (radioButtons[2].isSelected()) { // 黑棋先手被选中
            first_chess = 2;
            player_chess = 1; //棋子颜色改为黑色
        }
        if (radioButtons[3].isSelected()) { // 白棋先手被选中
            first_chess = -2;
            player_chess = -1;
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
