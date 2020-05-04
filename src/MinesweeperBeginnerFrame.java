import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Timer;

public class MinesweeperBeginnerFrame extends JFrame implements ActionListener {

    /**
     * For usage
     */
    Pics pics = new Pics();
    int ROWS;
    int COLS;
    int NUMBER_OF_MINES;
    private JButton[][] buttons;
    private int[][] mines;
    int NUMBER_OF_MINES_FOR_COUNTER;
    int NUMBER_OF_FLAGS = 0;
    int NUMBER_FOR_COUNTER = 0;
    int SECONDS_COUNTER = 0;
    boolean GAME_STATUS = true;
    int GAME_LVL;
    int MINESS;


    /**
     * Faces
     */


    public MinesweeperBeginnerFrame(int lvl, int ROWs, int COLs, int MINEs) {
        super("Minesweeper");
//        lvlChoose(lvl);
        GAME_LVL = lvl;
        ROWS = ROWs;
        COLS = COLs;
        MINESS = MINEs;
        NUMBER_OF_MINES = MINESS;
        NUMBER_OF_MINES_FOR_COUNTER = MINESS;
        buttons = new JButton[ROWS][COLS];
        mines = new int[ROWS - 4][COLS - 2];
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(ROWS, COLS));
        setSize(COLS * 25 + 16, ROWS * 25 + 39);
        enableInputMethods(true);
        this.setResizable(false);
        drawBoard();
        generateMines();
        countNeighbourMines();
        setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        countFlagsForMines();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SECONDS_COUNTER++;
                setTimer();
            }
        }, 1000, 1000);
    }

    /**
     * Function to count all nearby mines on board
     */

    public void countNeighbourMines() {
        for (int i = 0; i < ROWS - 4; i++) {
            for (int j = 0; j < COLS - 2; j++) {
                // check if its a mine if is dont change
                if (mines[i][j] != -1) {
                    int[][] temporaryTable = new int[8][2];
                    // first position
                    temporaryTable[0][1] = i - 1;
                    temporaryTable[0][0] = j - 1;
                    // second position
                    temporaryTable[1][1] = i - 1;
                    temporaryTable[1][0] = j;
                    // third position
                    temporaryTable[2][1] = i - 1;
                    temporaryTable[2][0] = j + 1;
                    // fourth position
                    temporaryTable[3][1] = i;
                    temporaryTable[3][0] = j + 1;
                    // fifth position
                    temporaryTable[4][1] = i + 1;
                    temporaryTable[4][0] = j + 1;
                    // sixth position
                    temporaryTable[5][1] = i + 1;
                    temporaryTable[5][0] = j;
                    // seventh position
                    temporaryTable[6][1] = i + 1;
                    temporaryTable[6][0] = j - 1;
                    // eight position
                    temporaryTable[7][1] = i;
                    temporaryTable[7][0] = j - 1;
                    int count = 0;
                    for (int k = 0; k < 8; k++) {
                        if (!(temporaryTable[k][0] < 0 || temporaryTable[k][1] < 0 || temporaryTable[k][0] > COLS - 3 ||
                                temporaryTable[k][1] > ROWS - 5)) {
                            // if not check if mine is there
                            if (mines[temporaryTable[k][1]][temporaryTable[k][0]] == -1) {
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
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
            /*
              Borders & Corners
             */
                if(i == 0 && j == 0) {
                    buttons[i][j] = new JButton(pics.TopRightCorner);
                    buttons[i][j].addActionListener(this);
                }
                else if(i == ROWS - 1 && j == 0) {
                    buttons[i][j] = new JButton(pics.DownRightCorner);
                }
                else if(i == 0 && j == COLS - 1){
                    buttons[i][j] = new JButton(pics.TopLeftCorner);
                }
                else if(i == ROWS - 1 && j == COLS - 1){
                    buttons[i][j] = new JButton(pics.DownLeftCorner);
                }
                else if(i == 0) {
                    buttons[i][j] = new JButton(pics.TopBorder);
                }
                else if(j == 0) {
                    buttons[i][j] = new JButton(pics.LeftBorder);
                }
                else if(j == COLS - 1){
                    buttons[i][j] = new JButton(pics.RightBorder);
                }
                else if(i == ROWS - 1){
                    buttons[i][j] = new JButton(pics.DownBorder);
                }
             /*
              Mines & Time counter
             */
                else if(i == 1 && (j == 1 || j == 2 || j == 3 || j == COLS - 4 || j == COLS - 3 || j == COLS - 2)){
                    buttons[i][j] = new JButton(pics.Zeros);
                }
             /*
              Face
             */
                else if((i == 1 && j == COLS/2 - 1)){
                    buttons[i][j] = new JButton(pics.Smile1);
                    buttons[i][j].addActionListener(this);
                }
                else if(i == 1 && j == COLS/2){
                    buttons[i][j] = new JButton(pics.Smile2);
                    buttons[i][j].addActionListener(this);
                }
                else if(i == 1) {
                    buttons[i][j] = new JButton(pics.Blank);
                    buttons[i][j].addActionListener(this);
                }else if(i == 2 && j == COLS/2 - 1){
                    buttons[i][j] = new JButton(pics.Smile4);
                    buttons[i][j].addActionListener(this);
                }
                else if(i == 2 && j == COLS/2 ){
                    buttons[i][j] = new JButton(pics.Smile3);
                    buttons[i][j].addActionListener(this);
                }
             /*
              Upper fill
             */
                else if((i == 2 && j == COLS - 2) ){
                    buttons[i][j] = new JButton(pics.Logo);
                }
                else if(i == 2){
                    buttons[i][j] = new JButton(pics.FillWithBorder);
                }
                else {
                    buttons[i][j] = new JButton(pics.Untouched);
                    buttons[i][j].addActionListener(this);
                    JButton temp = buttons[i][j];
                    buttons[i][j].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if(GAME_STATUS) {
                                if (e.getButton() == MouseEvent.BUTTON3) {
                                    if (temp.getIcon() == pics.Untouched) {
                                        if (temp.getIcon() != pics.Flag)
                                            temp.setIcon(pics.Flag);
                                    } else if (temp.getIcon() == pics.Flag) {
                                        temp.setIcon(pics.Untouched);
                                    }
                                }
                                countFlagsForMines();
                            }
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {
                            paintFace(pics.Wow1, pics.Wow2, pics.Wow3, pics.Wow4);
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {
                            if(GAME_STATUS) {
                                paintFace(pics.Smile1, pics.Smile2, pics.Smile3, pics.Smile4);
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
        int [] temporaryTable = new int[NUMBER_OF_MINES];
        int TemporaryNumberOfMines = NUMBER_OF_MINES;
        for(int i = 0; i < NUMBER_OF_MINES; i++){
            temporaryTable[i] = (ROWS - 4)*(COLS-2) + 1;
        }
        while(NUMBER_OF_MINES > 0){
            int numberToCheck = getRandomNumberInRange((ROWS - 4)*(COLS-2));
            int check = 0;
            for(int i = 0; i < TemporaryNumberOfMines; i++){
                if (numberToCheck == temporaryTable[i]) {
                    check = 1;
                    break;
                }
            }
            if(check == 0){
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
        while(number > COLS - 2){
            number -= (COLS - 2);
            row++;
        }
        int column = number;
        mines[row][column-1] = -1;
    }

    /**
     *
     * @param max - maximal value of integer
     * @return random integer
     */
    private static int getRandomNumberInRange(int max) {

        if (1 >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - 1) + 1) + 1;
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
            if (!(temporaryTable[k][0] < 0 || temporaryTable[k][1] < 0 || temporaryTable[k][0] > ROWS - 5 ||
                    temporaryTable[k][1] > COLS - 3)) {
                Point pNext = new Point();
                pNext.coordinates = temporaryTable[k];
                pNext.touched = false;
                int counter = 0;
                for (Point p : set) {
                    if (Arrays.equals(pNext.coordinates, p.coordinates)) {
                        counter++;
                    }
                }
                if (counter == 0) {
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
        for(int i = 3; i < ROWS - 1; i++) {
            for (int j = 1; j < COLS - 1; j++) {
                if(mines[i-3][j-1] != -1){
                    if(buttons[i][j].getIcon() == pics.Untouched){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void addAllFlags(){
        for(int i = 3; i < ROWS - 1; i++) {
            for (int j = 1; j < COLS - 1; j++) {
                if(mines[i-3][j-1] == -1 && buttons[i][j].getIcon() != pics.Flag){
                    buttons[i][j].setIcon(pics.Flag);
                }
            }
        }
    }

    public void countFlagsForMines(){
        NUMBER_OF_FLAGS = 0;
        for(int i = 3; i <ROWS - 1; i++) {
            for (int j = 1; j < COLS - 1; j++) {
                if(buttons[i][j].getIcon() == pics.Flag){
                    NUMBER_OF_FLAGS++;
                }
            }
        }
        NUMBER_FOR_COUNTER = NUMBER_OF_MINES_FOR_COUNTER - NUMBER_OF_FLAGS;
        int forCounter = NUMBER_FOR_COUNTER;
        int hundreds = 0;
        int tens = 0;
        int ones = forCounter;
        if(forCounter > 100){
            hundreds = forCounter/100;
            int rest = NUMBER_FOR_COUNTER - hundreds* 100;
            tens = rest/10;
            rest = rest - tens*10;
            ones = rest;
        }
        if(forCounter >= 10){
            tens = forCounter/10;
            ones = NUMBER_FOR_COUNTER - tens*10;
        }
        changeCounter(hundreds, tens, ones);
    }

    public Icon checkNumber(int number){
        Pics pics = new Pics();
        if(number == 0){
            return pics.Zeros;
        }if(number == 1){
            return pics.Ones;
        }if(number == 2){
            return pics.Twos;
        }if(number == 3){
            return pics.Threes;
        }if(number == 4){
            return pics.Fours;
        }if(number == 5){
            return pics.Fives;
        }if(number == 6){
            return pics.Sixs;
        }if(number == 7){
            return pics.Sevens;
        }if(number == 8){
            return pics.Eights;
        }if(number == 9){
            return pics.Nines;
        }
        return pics.Zeros;
    }

    public void changeCounter(int hundreds, int tens, int ones){
        //i == 1 && (j == 1 || j == 2 || j == 3)
        buttons[1][1].setIcon(checkNumber(hundreds));
        buttons[1][2].setIcon(checkNumber(tens));
        buttons[1][3].setIcon(checkNumber(ones));
    }

    public void paintFace(Icon F1, Icon F2,Icon F3,Icon F4){
        buttons[1][COLS/2 - 1].setIcon(F1);
        buttons[1][COLS/2].setIcon(F2);
        buttons[2][COLS/2 - 1].setIcon(F4);
        buttons[2][COLS/2].setIcon(F3);
    }

    public void showAllMines(){
        for(int i = 3; i < ROWS - 1; i++) {
            for (int j = 1; j < COLS - 1; j++) {
                if(mines[i-3][j-1] == -1){
                    buttons[i][j].setIcon(pics.NotClicked);
                }
            }
        }
    }

    public void disableAllButtons(){
        for(int i = 3; i < ROWS - 1; i++){
            for(int j = 1; j < COLS - 1; j++) {
                buttons[i][j].setEnabled(false);
                buttons[i][j].setDisabledIcon(buttons[i][j].getIcon());
            }
        }
    }

    public void setTimer(){
        if(GAME_STATUS) {
            int forCounter = SECONDS_COUNTER;
            int temp = SECONDS_COUNTER;
            int hundreds = 0;
            int tens = 0;
            int ones = forCounter;
            if (forCounter > 100) {
                hundreds = forCounter / 100;
                int rest = temp - hundreds * 100;
                tens = rest / 10;
                rest = rest - tens * 10;
                ones = rest;
            }
            if (forCounter >= 10) {
                tens = forCounter / 10;
                ones = temp - tens * 10;
            }
            buttons[1][COLS - 4].setIcon(checkNumber(hundreds));
            buttons[1][COLS - 3].setIcon(checkNumber(tens));
            buttons[1][COLS - 2].setIcon(checkNumber(ones));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        Pics pics = new Pics();
        if(source == buttons[1][COLS/2 - 1] || source == buttons[1][COLS/2] || source == buttons[2][COLS/2 - 1] || source == buttons[2][COLS/2]){
            dispose();
            new MinesweeperBeginnerFrame(GAME_LVL, ROWS, COLS, MINESS);
        }
        if(source == buttons[0][0]){
            showAllMines();
        }
        for(int i = 3; i < ROWS - 1; i++){
            for(int j = 1; j < COLS - 1; j++){
                if(source == buttons[i][j]) {
                    if (buttons[i][j].getIcon() != pics.Flag) {
                        if (mines[i - 3][j - 1] == -1) {
                            GAME_STATUS = false;
                            showAllMines();
                            buttons[i][j].setIcon(pics.MineClicked);
                            disableAllButtons();
                            paintFace(pics.Dead1, pics.Dead2, pics.Dead3, pics.Dead4);
                        } else if (mines[i - 3][j - 1] == 0) {
                            Set<Point> set = new HashSet<>();
                            int[] var = new int[2];
                            var[0] = i - 3;
                            var[1] = j - 1;
                            finishAddToListNonRepeatable(set, var);
                            for (Point p : set) {
                                if (mines[p.coordinates[0]][p.coordinates[1]] == 0) {
                                    buttons[p.coordinates[0] + 3][p.coordinates[1] + 1].setIcon(pics.Touched);
                                } else if (mines[p.coordinates[0]][p.coordinates[1]] == 1) {
                                    buttons[p.coordinates[0] + 3][p.coordinates[1] + 1].setIcon(pics.OneB);
                                } else if (mines[p.coordinates[0]][p.coordinates[1]] == 2) {
                                    buttons[p.coordinates[0] + 3][p.coordinates[1] + 1].setIcon(pics.TwoB);
                                } else if (mines[p.coordinates[0]][p.coordinates[1]] == 3) {
                                    buttons[p.coordinates[0] + 3][p.coordinates[1] + 1].setIcon(pics.ThreeB);
                                } else if (mines[p.coordinates[0]][p.coordinates[1]] == 4) {
                                    buttons[p.coordinates[0] + 3][p.coordinates[1] + 1].setIcon(pics.FourB);
                                } else if (mines[p.coordinates[0]][p.coordinates[1]] == 5) {
                                    buttons[p.coordinates[0] + 3][p.coordinates[1] + 1].setIcon(pics.FiveB);
                                } else if (mines[p.coordinates[0]][p.coordinates[1]] == 6) {
                                    buttons[p.coordinates[0] + 3][p.coordinates[1] + 1].setIcon(pics.SixB);
                                } else if (mines[p.coordinates[0]][p.coordinates[1]] == 7) {
                                    buttons[p.coordinates[0] + 3][p.coordinates[1] + 1].setIcon(pics.SevenB);
                                } else if (mines[p.coordinates[0]][p.coordinates[1]] == 8) {
                                    buttons[p.coordinates[0] + 3][p.coordinates[1] + 1].setIcon(pics.EightB);
                                }
                            }
                        } else if (mines[i - 3][j - 1] == 1) {
                            buttons[i][j].setIcon(pics.OneB);
                        } else if (mines[i - 3][j - 1] == 2) {
                            buttons[i][j].setIcon(pics.TwoB);
                        } else if (mines[i - 3][j - 1] == 3) {
                            buttons[i][j].setIcon(pics.ThreeB);
                        } else if (mines[i - 3][j - 1] == 4) {
                            buttons[i][j].setIcon(pics.FourB);
                        } else if (mines[i - 3][j - 1] == 5) {
                            buttons[i][j].setIcon(pics.FiveB);
                        } else if (mines[i - 3][j - 1] == 6) {
                            buttons[i][j].setIcon(pics.SixB);
                        } else if (mines[i - 3][j - 1] == 7) {
                            buttons[i][j].setIcon(pics.SevenB);
                        } else if (mines[i - 3][j - 1] == 8) {
                            buttons[i][j].setIcon(pics.EightB);
                        }
                        countFlagsForMines();
                    }
                    if(checkIfGameWon()){
                        GAME_STATUS = false;
                        paintFace(pics.Bro1, pics.Bro2, pics.Bro3, pics.Bro4);
                        addAllFlags();
                        disableAllButtons();
                    }
                }
            }
        }

    }
}
