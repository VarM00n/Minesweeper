import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Utilities;
import java.awt.*;
import java.util.Set;
import java.util.HashSet;

public class MinesweeperBeginnerFrame extends JFrame implements ActionListener {


    private JButton [][] buttons = new JButton[12][10];
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
    private Icon Fill = new ImageIcon("src\\pics\\Border\\Blank.png");
    private Icon FillWithBorder = new ImageIcon("src\\pics\\Border\\BlankWithBorder.png");
    private Icon Logo = new ImageIcon("src\\pics\\Border\\Logo.png");

    /**
     * Timer/Mines
     */
    private Icon Zeros = new ImageIcon("src\\pics\\Timer\\TimerZero.png");
    /**
     * Faces
     */
    private Icon Smile = new ImageIcon("src\\pics\\Faces\\SmileFace.png");
    private Icon Dead = new ImageIcon("src\\pics\\Faces\\DeadFace.png");
    private Icon Bro = new ImageIcon("src\\pics\\Faces\\BroFace.png");
    /**
     * Game
     */
    private Icon Untouched = new ImageIcon("src\\pics\\Game\\BlankBlock.png");
    private Icon Touched = new ImageIcon("src\\pics\\Game\\clickedBlock.png");
    private Icon OneB = new ImageIcon("src\\pics\\Game\\One.png");
    private Icon TwoB = new ImageIcon("src\\pics\\Game\\Two.png");
    private Icon ThreeB = new ImageIcon("src\\pics\\Game\\Three.png");
    private Icon FourB = new ImageIcon("src\\pics\\Game\\Four.png");
    private Icon FiveB = new ImageIcon("src\\pics\\Game\\Five.png");
    private Icon SixB = new ImageIcon("src\\pics\\Game\\Six.png");
    private Icon SevenB = new ImageIcon("src\\pics\\Game\\Seven.png");
    private Icon EightB = new ImageIcon("src\\pics\\Game\\Eight.png");
    private Icon MineClicked = new ImageIcon("src\\pics\\Game\\bombClicked.png");
    private Icon NotClicked = new ImageIcon("src\\pics\\Game\\BombNotClicked.png");
    private Icon Flag = new ImageIcon("src\\pics\\Game\\flag.png");

    /**
     * Mines - -1
     * It also contains numbers of neighbour mines
     */
    int NUMBER_OF_MINES = 10;
    private int [][] mines = new int[8][8];

    int FLAG_COUNTER = 10;

    public MinesweeperBeginnerFrame(){
        super("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(12, 10));
        setSize(266, 339);
        enableInputMethods(true);
        this.setResizable(false);
        drawBoard();
        generateMines();
        countNeighbourMines();
        setVisible(true);

    }

    /**
     * Function to count all nearby mines on board
     */

    public void countNeighbourMines(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                // check if its a mine if is dont change
                if(mines[i][j] != -1) {
                    int[][] temporaryTable = new int[8][2];
                    // first position
                    temporaryTable[0][0] = i - 1;
                    temporaryTable[0][1] = j - 1;
                    // second position
                    temporaryTable[1][0] = i - 1;
                    temporaryTable[1][1] = j;
                    // third position
                    temporaryTable[2][0] = i - 1;
                    temporaryTable[2][1] = j + 1;
                    // fourth position
                    temporaryTable[3][0] = i;
                    temporaryTable[3][1] = j + 1;
                    // fifth position
                    temporaryTable[4][0] = i + 1;
                    temporaryTable[4][1] = j + 1;
                    // sixth position
                    temporaryTable[5][0] = i + 1;
                    temporaryTable[5][1] = j;
                    // seventh position
                    temporaryTable[6][0] = i + 1;
                    temporaryTable[6][1] = j - 1;
                    // eight position
                    temporaryTable[7][0] = i;
                    temporaryTable[7][1] = j - 1;
                    int count = 0;
                    for (int k = 0; k < 8; k++) {
                        // check if any is out of bound: which is < 0 or > 7
                        if (!(temporaryTable[k][0] < 0 || temporaryTable[k][1] < 0 || temporaryTable[k][0] > 7 ||
                                temporaryTable[k][1] > 7)) {
                            // if not check if mine is there
                            if(mines[temporaryTable[k][0]][temporaryTable[k][1]] == -1){
                                count++;
                            }
                        }
                    }
                    mines[i][j] = count;
                }
            }
        }
    }

    /**
     * Function to draw GUI
     */

    public void drawBoard(){
        for(int i = 0; i < 12; i++){
            for(int j = 0; j < 10; j++){
            /*
              Borders & Corners
             */
                if(i == 0 && j == 0) {
                    buttons[i][j] = new JButton(TopRightCorner);
                }
                else if(i == 11 && j == 0) {
                    buttons[i][j] = new JButton(DownRightCorner);
                }
                else if(i == 0 && j == 9){
                    buttons[i][j] = new JButton(TopLeftCorner);
                }
                else if(i == 11 && j == 9){
                    buttons[i][j] = new JButton(DownLeftCorner);
                }
                else if(i == 0) {
                    buttons[i][j] = new JButton(TopBorder);
                }
                else if(j == 0) {
                    buttons[i][j] = new JButton(LeftBorder);
                }
                else if(j == 9){
                    buttons[i][j] = new JButton(RightBorder);
                }
                else if(i == 11){
                    buttons[i][j] = new JButton(DownBorder);
                }
             /*
              Mines counter
             */
                else if(i == 1 && (j == 1 || j == 2)){
                    buttons[i][j] = new JButton(Zeros);
                }
             /*
              Upper fill
             */
                else if(i == 1 && (j == 6 || j == 7 || j == 8)){
                    buttons[i][j] = new JButton(Zeros);
                }
             /*
              Face
             */
                else if(i == 1 && j == 4){
                    buttons[i][j] = new JButton(Smile);
                    buttons[i][j].addActionListener(this);
                }
             /*
              Upper fill
             */
                else if(i == 1){
                    buttons[i][j] = new JButton(Fill);
                }
                else if(i == 2 && j == 8){
                    buttons[i][j] = new JButton(Logo);
                }
                else if(i == 2){
                    buttons[i][j] = new JButton(FillWithBorder);
                }
                else {
                    buttons[i][j] = new JButton(Untouched);
                    buttons[i][j].addActionListener(this);
                    JButton temp = buttons[i][j];
                    buttons[i][j].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (e.getButton() == MouseEvent.BUTTON3) {
                                if(temp.getIcon() != Flag)
                                    temp.setIcon(Flag);
                                else
                                    temp.setIcon(Untouched);
                            }
                        }
                    });
                }
                buttons[i][j].setBorder(new EmptyBorder(0, 0, 0, 0));
                add(buttons[i][j]);
            }
        }
    }

    /**
     * Function to generate Mines in 8x8 field and save to mines
     */

    public void generateMines(){
        int [] temporaryTable = new int[10];
        for(int i = 0; i < 10; i++){
            temporaryTable[i] = 65;
        }
        while(NUMBER_OF_MINES > 0){
            int numberToCheck = getRandomNumberInRange(1, 64);
            boolean check = false;
            for(int i = 0; i < 10; i++){
                if (numberToCheck == temporaryTable[i]) {
                    check = true;
                    break;
                }
            }
            if(!check){
                temporaryTable[NUMBER_OF_MINES-1] = numberToCheck;
                addToMineTable(numberToCheck);
                NUMBER_OF_MINES -= 1;
            }
        }
    }

    /**
     *
     * @param number to put into mineTable
     */

    public void addToMineTable(int number){
        int row = 0;
        int column = 0;
        while(number > 8){
            number -= 8;
            row++;
        }
        column = number;
        mines[row][column-1] = -1;
    }


    /**
     *
     * @param min - minimal value of integer
     * @param max - maximal value of integer
     * @return random integer
     */
    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    /**
     * Function adds points to a list in order to open them automatically
     * @param set of Boxes to check
     * @param point as a place to start
     */

    private void addToListNonRepeatable(Set<Point> set, int[] point){
        int[][] temporaryTable = new int[8][2];
        // first position
        temporaryTable[0][0] = point[0] - 1;
        temporaryTable[0][1] = point[1] - 1;
        // second position
        temporaryTable[1][0] = point[0] - 1;
        temporaryTable[1][1] = point[1];
        // third position
        temporaryTable[2][0] = point[0] - 1;
        temporaryTable[2][1] = point[1] + 1;
        // fourth position
        temporaryTable[3][0] = point[0];
        temporaryTable[3][1] = point[1] + 1;
        // fifth position
        temporaryTable[4][0] = point[0] + 1;
        temporaryTable[4][1] = point[1] + 1;
        // sixth position
        temporaryTable[5][0] = point[0] + 1;
        temporaryTable[5][1] = point[1];
        // seventh position
        temporaryTable[6][0] = point[0] + 1;
        temporaryTable[6][1] = point[1] - 1;
        // eight position
        temporaryTable[7][0] = point[0];
        temporaryTable[7][1] = point[1] - 1;
        for (int k = 0; k < 8; k++) {
            // check if any is out of bound: which is < 0 or > 7
            if (!(temporaryTable[k][0] < 0 || temporaryTable[k][1] < 0 || temporaryTable[k][0] > 7 ||
                                  temporaryTable[k][1] > 7)) {
                Point pNext = new Point();
                pNext.coordinates = temporaryTable[k];
                pNext.touched = false;
                int counter = 0;
                for(Point p: set) {
                    if(Arrays.equals(pNext.coordinates, p.coordinates)){
                        counter++;
                    }
                }
                if(counter == 0) {
                    set.add(pNext);
                }
                }
            }
    }

    /**
     * Function to finish adding do set
     * @param set of Boxes to check
     * @param point as a place to start
     */

    private void finishAddToListNonRepeatable(Set<Point> set, int[] point){
        Point pStart = new Point();
        pStart.coordinates = point;
        set.add(pStart);
        addToListNonRepeatable(set, point);
        pStart.touched = true;
        while(true){
            int count = 0;
            for(Point p: set){
                if(!p.touched){
                    p.touched = true;
                    if (mines[p.coordinates[0]][p.coordinates[1]] == 0) {
                        addToListNonRepeatable(set, p.coordinates);
                        count++;
                        break;
                    }
                }
            }
            if(count == 0){
                break;
            }
        }


    }

    public boolean checkIfGameWon(){
        for(int i = 3; i < 11; i++) {
            for (int j = 1; j < 9; j++) {
                if(mines[i-3][j-1] != -1){
                    if(buttons[i][j].getIcon() == Untouched){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void addAllFlags(){
        for(int i = 3; i < 11; i++) {
            for (int j = 1; j < 9; j++) {
                if(mines[i-3][j-1] == -1 && buttons[i][j].getIcon() != Flag){
                    buttons[i][j].setIcon(Flag);
                }
            }
        }
    }

    public void showAllMines(){
        for(int i = 3; i < 11; i++) {
            for (int j = 1; j < 9; j++) {
                if(mines[i-3][j-1] == -1){
                    buttons[i][j].setIcon(NotClicked);
                }
            }
        }
    }

    public void disableAllButtons(){
        for(int i = 3; i < 11; i++){
            for(int j = 1; j < 9; j++) {
                buttons[i][j].setEnabled(false);
                buttons[i][j].setDisabledIcon(buttons[i][j].getIcon());
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == buttons[1][4]){
            dispose();
            new MinesweeperBeginnerFrame();
        }
        for(int i = 3; i < 11; i++){
            for(int j = 1; j < 9; j++){
                if(source == buttons[i][j]) {
                    if (buttons[i][j].getIcon() != Flag) {
                        if (mines[i - 3][j - 1] == -1) {
                            showAllMines();
                            buttons[i][j].setIcon(MineClicked);
                            disableAllButtons();
                            buttons[1][4].setIcon(Dead);
                        } else if (mines[i - 3][j - 1] == 0) {
                            Set<Point> set = new HashSet<>();
                            int[] var = new int[2];
                            var[0] = i - 3;
                            var[1] = j - 1;
                            finishAddToListNonRepeatable(set, var);
                            for (Point p : set) {
                                if (mines[p.coordinates[0]][p.coordinates[1]] == 0) {
                                    buttons[p.coordinates[0] + 3][p.coordinates[1] + 1].setIcon(Touched);
                                } else if (mines[p.coordinates[0]][p.coordinates[1]] == 1) {
                                    buttons[p.coordinates[0] + 3][p.coordinates[1] + 1].setIcon(OneB);
                                } else if (mines[p.coordinates[0]][p.coordinates[1]] == 2) {
                                    buttons[p.coordinates[0] + 3][p.coordinates[1] + 1].setIcon(TwoB);
                                } else if (mines[p.coordinates[0]][p.coordinates[1]] == 3) {
                                    buttons[p.coordinates[0] + 3][p.coordinates[1] + 1].setIcon(ThreeB);
                                } else if (mines[p.coordinates[0]][p.coordinates[1]] == 4) {
                                    buttons[p.coordinates[0] + 3][p.coordinates[1] + 1].setIcon(FourB);
                                } else if (mines[p.coordinates[0]][p.coordinates[1]] == 5) {
                                    buttons[p.coordinates[0] + 3][p.coordinates[1] + 1].setIcon(FiveB);
                                } else if (mines[p.coordinates[0]][p.coordinates[1]] == 6) {
                                    buttons[p.coordinates[0] + 3][p.coordinates[1] + 1].setIcon(SixB);
                                } else if (mines[p.coordinates[0]][p.coordinates[1]] == 7) {
                                    buttons[p.coordinates[0] + 3][p.coordinates[1] + 1].setIcon(SevenB);
                                } else if (mines[p.coordinates[0]][p.coordinates[1]] == 8) {
                                    buttons[p.coordinates[0] + 3][p.coordinates[1] + 1].setIcon(EightB);
                                }
                            }
                        } else if (mines[i - 3][j - 1] == 1) {
                            buttons[i][j].setIcon(OneB);
                        } else if (mines[i - 3][j - 1] == 2) {
                            buttons[i][j].setIcon(TwoB);
                        } else if (mines[i - 3][j - 1] == 3) {
                            buttons[i][j].setIcon(ThreeB);
                        } else if (mines[i - 3][j - 1] == 4) {
                            buttons[i][j].setIcon(FourB);
                        } else if (mines[i - 3][j - 1] == 5) {
                            buttons[i][j].setIcon(FiveB);
                        } else if (mines[i - 3][j - 1] == 6) {
                            buttons[i][j].setIcon(SixB);
                        } else if (mines[i - 3][j - 1] == 7) {
                            buttons[i][j].setIcon(SevenB);
                        } else if (mines[i - 3][j - 1] == 8) {
                            buttons[i][j].setIcon(EightB);
                        }
                    }
                    if(checkIfGameWon()){
                        buttons[1][4].setIcon(Bro);
                        addAllFlags();
                        disableAllButtons();
                    }
                }
            }
        }

    }
}
