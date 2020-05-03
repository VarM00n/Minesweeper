import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends JFrame implements ActionListener {

    int ROWS = 20;
    int COLS = 20;
    private JButton [][] buttons = new JButton[ROWS][COLS];

    /**
     * Corners
     */
    private Icon TopRightCorner = new ImageIcon("src\\pics\\Border\\RightUpCorner.png");
    private Icon TopLeftCorner = new ImageIcon("src\\pics\\Border\\LeftUpCorner.png");
    private Icon DownRightCorner = new ImageIcon("src\\pics\\Border\\RightDownCorner.png");
    private Icon DownLeftCorner = new ImageIcon("src\\pics\\Border\\LeftDownCorner.png");
    /**
     * Borders
     */
    private Icon DownBorder = new ImageIcon("src\\pics\\Border\\DownBorder.png");
    private Icon TopBorder = new ImageIcon("src\\pics\\Border\\UpBorder.png");
    private Icon LeftBorder = new ImageIcon("src\\pics\\Border\\LeftBorder.png");
    private Icon RightBorder = new ImageIcon("src\\pics\\Border\\RightBorder.png");
    /**
     * FIll
     */
    private Icon Blank = new ImageIcon("src\\pics\\Border\\Blank.png");
    /**
     * Menu
     */
    private Icon OneB = new ImageIcon("src\\pics\\Menu\\Bomb\\B1.png");
    private Icon TwoB = new ImageIcon("src\\pics\\Menu\\Bomb\\B2.png");
    private Icon ThreeB = new ImageIcon("src\\pics\\Menu\\Bomb\\B3.png");
    private Icon FourB = new ImageIcon("src\\pics\\Menu\\Bomb\\B4.png");
    private Icon OneO = new ImageIcon("src\\pics\\Menu\\One\\O1.png");
    private Icon TwoO = new ImageIcon("src\\pics\\Menu\\One\\O2.png");
    private Icon ThreeO = new ImageIcon("src\\pics\\Menu\\One\\O3.png");
    private Icon FourO = new ImageIcon("src\\pics\\Menu\\One\\O4.png");
    private Icon OneF = new ImageIcon("src\\pics\\Menu\\Flag\\F1.png");
    private Icon TwoF = new ImageIcon("src\\pics\\Menu\\Flag\\F2.png");
    private Icon ThreeF = new ImageIcon("src\\pics\\Menu\\Flag\\F3.png");
    private Icon FourF = new ImageIcon("src\\pics\\Menu\\Flag\\F4.png");
    private Icon OneTH = new ImageIcon("src\\pics\\Menu\\Three\\TH1.png");
    private Icon TwoTH = new ImageIcon("src\\pics\\Menu\\Three\\TH2.png");
    private Icon ThreeTH = new ImageIcon("src\\pics\\Menu\\Three\\TH3.png");
    private Icon FourTH = new ImageIcon("src\\pics\\Menu\\Three\\TH4.png");
    private Icon OneT = new ImageIcon("src\\pics\\Menu\\Two\\T1.png");
    private Icon TwoT = new ImageIcon("src\\pics\\Menu\\Two\\T2.png");
    private Icon ThreeT = new ImageIcon("src\\pics\\Menu\\Two\\T3.png");
    private Icon FourT = new ImageIcon("src\\pics\\Menu\\Two\\T4.png");

    /**
     * Easy
     */

    private Icon Easy1 = new ImageIcon("src\\pics\\Menu\\EasyButton\\Ez1.png");
    private Icon Easy2 = new ImageIcon("src\\pics\\Menu\\EasyButton\\Ez2.png");
    private Icon Easy3 = new ImageIcon("src\\pics\\Menu\\EasyButton\\Ez3.png");
    private Icon Easy4 = new ImageIcon("src\\pics\\Menu\\EasyButton\\Ez4.png");
    private Icon Easy5 = new ImageIcon("src\\pics\\Menu\\EasyButton\\Ez5.png");
    private Icon Easy6 = new ImageIcon("src\\pics\\Menu\\EasyButton\\Ez6.png");
    private Icon Easy7 = new ImageIcon("src\\pics\\Menu\\EasyButton\\Ez7.png");
    private Icon Easy8 = new ImageIcon("src\\pics\\Menu\\EasyButton\\Ez8.png");
    private Icon Easy9 = new ImageIcon("src\\pics\\Menu\\EasyButton\\Ez9.png");
    private Icon Easy10 = new ImageIcon("src\\pics\\Menu\\EasyButton\\Ez10.png");
    private Icon Easy11 = new ImageIcon("src\\pics\\Menu\\EasyButton\\Ez11.png");
    private Icon Easy12 = new ImageIcon("src\\pics\\Menu\\EasyButton\\Ez12.png");

    /**
     * Medium
     */

    private Icon Medium1 = new ImageIcon("src\\pics\\Menu\\MediumButton\\M1.png");
    private Icon Medium2 = new ImageIcon("src\\pics\\Menu\\MediumButton\\M2.png");
    private Icon Medium3 = new ImageIcon("src\\pics\\Menu\\MediumButton\\M3.png");
    private Icon Medium4 = new ImageIcon("src\\pics\\Menu\\MediumButton\\M4.png");
    private Icon Medium5 = new ImageIcon("src\\pics\\Menu\\MediumButton\\M5.png");
    private Icon Medium6 = new ImageIcon("src\\pics\\Menu\\MediumButton\\M6.png");
    private Icon Medium7 = new ImageIcon("src\\pics\\Menu\\MediumButton\\M7.png");
    private Icon Medium8 = new ImageIcon("src\\pics\\Menu\\MediumButton\\M8.png");
    private Icon Medium9 = new ImageIcon("src\\pics\\Menu\\MediumButton\\M9.png");
    private Icon Medium10 = new ImageIcon("src\\pics\\Menu\\MediumButton\\M10.png");
    private Icon Medium11 = new ImageIcon("src\\pics\\Menu\\MediumButton\\M11.png");
    private Icon Medium12 = new ImageIcon("src\\pics\\Menu\\MediumButton\\M12.png");

    /**
     * Hard
     */

    private Icon Hard1 = new ImageIcon("src\\pics\\Menu\\HardButton\\H1.png");
    private Icon Hard2 = new ImageIcon("src\\pics\\Menu\\HardButton\\H2.png");
    private Icon Hard3 = new ImageIcon("src\\pics\\Menu\\HardButton\\H3.png");
    private Icon Hard4 = new ImageIcon("src\\pics\\Menu\\HardButton\\H4.png");
    private Icon Hard5 = new ImageIcon("src\\pics\\Menu\\HardButton\\H5.png");
    private Icon Hard6 = new ImageIcon("src\\pics\\Menu\\HardButton\\H6.png");
    private Icon Hard7 = new ImageIcon("src\\pics\\Menu\\HardButton\\H7.png");
    private Icon Hard8 = new ImageIcon("src\\pics\\Menu\\HardButton\\H8.png");
    private Icon Hard9 = new ImageIcon("src\\pics\\Menu\\HardButton\\H9.png");
    private Icon Hard10 = new ImageIcon("src\\pics\\Menu\\HardButton\\H10.png");
    private Icon Hard11 = new ImageIcon("src\\pics\\Menu\\HardButton\\H11.png");
    private Icon Hard12 = new ImageIcon("src\\pics\\Menu\\HardButton\\H12.png");


    public Menu(){
        super("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(ROWS, COLS));
        setSize(COLS*25 + 16, ROWS*25 + 39);
        enableInputMethods(true);
        this.setResizable(false);
        drawMenu();
        setVisible(true);


    }

    private void drawMenu(){
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
            /*
              Borders & Corners
             */
                if(i == 0 && j == 0) {
                    buttons[i][j] = new JButton(TopRightCorner);
                    buttons[i][j].addActionListener(this);
                }else if(i == ROWS - 1 && j == 0) {
                    buttons[i][j] = new JButton(DownRightCorner);
                }else if(i == 0 && j == COLS - 1){
                    buttons[i][j] = new JButton(TopLeftCorner);
                }else if(i == ROWS - 1 && j == COLS - 1){
                    buttons[i][j] = new JButton(DownLeftCorner);
                }else if(i == 0) {
                    buttons[i][j] = new JButton(TopBorder);
                }else if(j == 0) {
                    buttons[i][j] = new JButton(LeftBorder);
                }else if(j == COLS - 1){
                    buttons[i][j] = new JButton(RightBorder);
                }else if(i == ROWS - 1){
                    buttons[i][j] = new JButton(DownBorder);
                }else if(i == 3 && j == 11){
                    buttons[i][j] = new JButton(OneB);
                }else if(i == 3 && j == 12){
                    buttons[i][j] = new JButton(TwoB);
                }else if(i == 4 && j == 11){
                    buttons[i][j] = new JButton(ThreeB);
                }else if(i == 4 && j == 12){
                    buttons[i][j] = new JButton(FourB);
                }
                else if(i == 3 && j == 13 || i == 5 && j == 11){
                buttons[i][j] = new JButton(OneO);
                }else if(i == 3 && j == 14 || i == 5 && j == 12){
                buttons[i][j] = new JButton(TwoO);
                }else if(i == 4 && j == 13 || i == 6 && j == 11){
                buttons[i][j] = new JButton(ThreeO);
                }else if(i == 4 && j == 14 || i == 6 && j == 12){
                buttons[i][j] = new JButton(FourO);
                }
                else if(i == 3 && j == 15 || i == 7 && j == 11 || i == 7 && j == 15){
                    buttons[i][j] = new JButton(OneF);
                }else if(i == 3 && j == 16 || i == 7 && j == 12 || i == 7 && j == 16){
                    buttons[i][j] = new JButton(TwoF);
                }else if(i == 4 && j == 16 || i == 8 && j == 12 || i == 8 && j == 16){
                    buttons[i][j] = new JButton(ThreeF);
                }else if(i == 4 && j == 15 || i == 8 && j == 11 || i == 8 && j == 15){
                    buttons[i][j] = new JButton(FourF);
                }
                else if(i == 5 && j == 13){
                    buttons[i][j] = new JButton(OneTH);
                }else if(i == 5 && j == 14){
                    buttons[i][j] = new JButton(TwoTH);
                }else if(i == 6 && j == 14){
                    buttons[i][j] = new JButton(ThreeTH);
                }else if(i == 6 && j == 13){
                    buttons[i][j] = new JButton(FourTH);
                }
                else if(i == 7 && j == 13 || i == 5 && j == 15){
                    buttons[i][j] = new JButton(OneT);
                }else if(i == 7 && j == 14 || i == 5 && j == 16){
                    buttons[i][j] = new JButton(TwoT);
                }else if(i == 8 && j == 14 || i == 6 && j == 16){
                    buttons[i][j] = new JButton(ThreeT);
                }else if(i == 8 && j == 13 || i == 6 && j == 15){
                    buttons[i][j] = new JButton(FourT);
                }
                else if(i == 4 && j == 2 ) {
                    buttons[i][j] = new JButton(Easy1);
                }else if(i == 4 && j == 3 ) {
                    buttons[i][j] = new JButton(Easy2);
                }else if(i == 4 && j == 4 ) {
                    buttons[i][j] = new JButton(Easy3);
                }else if(i == 4 && j == 5 ) {
                    buttons[i][j] = new JButton(Easy4);
                }else if(i == 4 && j == 6 ) {
                    buttons[i][j] = new JButton(Easy5);
                }else if(i == 4 && j == 7 ) {
                    buttons[i][j] = new JButton(Easy6);
                }else if(i == 5 && j == 2 ) {
                    buttons[i][j] = new JButton(Easy7);
                }else if(i == 5 && j == 3 ) {
                    buttons[i][j] = new JButton(Easy8);
                }else if(i == 5 && j == 4 ) {
                    buttons[i][j] = new JButton(Easy9);
                }else if(i == 5 && j == 5 ) {
                    buttons[i][j] = new JButton(Easy10);
                }else if(i == 5 && j == 6 ) {
                    buttons[i][j] = new JButton(Easy11);
                }else if(i == 5 && j == 7 ) {
                    buttons[i][j] = new JButton(Easy12);
                }
                else if(i == 7 && j == 2 ) {
                    buttons[i][j] = new JButton(Medium1);
                }else if(i == 7 && j == 3 ) {
                    buttons[i][j] = new JButton(Medium2);
                }else if(i == 7 && j == 4 ) {
                    buttons[i][j] = new JButton(Medium3);
                }else if(i == 7 && j == 5 ) {
                    buttons[i][j] = new JButton(Medium4);
                }else if(i == 7 && j == 6 ) {
                    buttons[i][j] = new JButton(Medium5);
                }else if(i == 7 && j == 7 ) {
                    buttons[i][j] = new JButton(Medium6);
                }else if(i == 8 && j == 2 ) {
                    buttons[i][j] = new JButton(Medium12);
                }else if(i == 8 && j == 3 ) {
                    buttons[i][j] = new JButton(Medium11);
                }else if(i == 8 && j == 4 ) {
                    buttons[i][j] = new JButton(Medium10);
                }else if(i == 8 && j == 5 ) {
                    buttons[i][j] = new JButton(Medium9);
                }else if(i == 8 && j == 6 ) {
                    buttons[i][j] = new JButton(Medium8);
                }else if(i == 8 && j == 7 ) {
                    buttons[i][j] = new JButton(Medium7);
                }
                else if(i == 10 && j == 2 ) {
                    buttons[i][j] = new JButton(Hard1);
                }else if(i == 10 && j == 3 ) {
                    buttons[i][j] = new JButton(Hard2);
                }else if(i == 10 && j == 4 ) {
                    buttons[i][j] = new JButton(Hard3);
                }else if(i == 10 && j == 5 ) {
                    buttons[i][j] = new JButton(Hard4);
                }else if(i == 10 && j == 6 ) {
                    buttons[i][j] = new JButton(Hard5);
                }else if(i == 10 && j == 7 ) {
                    buttons[i][j] = new JButton(Hard6);
                }else if(i == 11 && j == 2 ) {
                    buttons[i][j] = new JButton(Hard7);
                }else if(i == 11 && j == 3 ) {
                    buttons[i][j] = new JButton(Hard8);
                }else if(i == 11 && j == 4 ) {
                    buttons[i][j] = new JButton(Hard9);
                }else if(i == 11 && j == 5 ) {
                    buttons[i][j] = new JButton(Hard10);
                }else if(i == 11 && j == 6 ) {
                    buttons[i][j] = new JButton(Hard11);
                }else if(i == 11 && j == 7 ) {
                    buttons[i][j] = new JButton(Hard12);
                }
                else{
                    buttons[i][j] = new JButton(Blank);
                }
                buttons[i][j].addActionListener(this);
                buttons[i][j].setBorder(new EmptyBorder(0, 0, 0, 0));
                add(buttons[i][j]);
            }
        }

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == buttons[4][2] || source == buttons[4][3] || source == buttons[4][4] || source == buttons[4][5] ||
                source == buttons[4][6] || source == buttons[4][7] || source == buttons[5][2] || source == buttons[5][3] ||
                source == buttons[5][4] || source == buttons[5][5] || source == buttons[5][6] || source == buttons[5][7]){
            dispose();
            new MinesweeperBeginnerFrame(1);
        }
        if(source == buttons[7][2] || source == buttons[7][3] || source == buttons[7][4] || source == buttons[7][5] ||
                source == buttons[7][6] || source == buttons[7][7] || source == buttons[8][2] || source == buttons[8][3] ||
                source == buttons[8][4] || source == buttons[8][5] || source == buttons[8][6] || source == buttons[8][7]){
            dispose();
            new MinesweeperBeginnerFrame(2);
        }
        if(source == buttons[10][2] || source == buttons[10][3] || source == buttons[10][4] || source == buttons[10][5] ||
                source == buttons[10][6] || source == buttons[10][7] || source == buttons[11][2] || source == buttons[11][3] ||
                source == buttons[11][4] || source == buttons[11][5] || source == buttons[11][6] || source == buttons[11][7]){
            dispose();
            new MinesweeperBeginnerFrame(3);
        }

    }
}
