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
     * Beginner level
     */
    // rows -> 8 + 4 // cols -> 8 + 2 // minesB -> 10
    int rowsB = 12;
    int colsB = 10;
    int minesB = 10;

    /**
     * Medium level
     */
    // rows -> 16 + 4 // cols -> 16 + 2 // minesB -> 40
    int rowsM = 20;
    int colsM = 18;
    int minesM = 40;

    /**
     * Hard level
     */
    // rows -> 16 + 4 // cols -> 30 + 2 // minesB -> 99
    int rowsH = 20;
    int colsH = 32;
    int minesH = 99;


    /**
     * For usage
     */
    int ROWS = 0;
    int COLS = 0;
    int NUMBER_OF_MINES = 0;
    private JButton [][] buttons;
    private int [][] mines;
    int NUMBER_OF_MINES_FOR_COUNTER = 0;
    int NUMBER_OF_FLAGS = 0;
    int NUMBER_FOR_COUNTER = 0;
    int SECONDS_COUNTER = 0;
    boolean GAME_STATUS = true;
    int GAME_LVL = 0;

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
    private Icon FillWithBorder = new ImageIcon("src\\pics\\Border\\BlankWithBorder.png");
    private Icon Logo = new ImageIcon("src\\pics\\Border\\Logo.png");
    private Icon Blank = new ImageIcon("src\\pics\\Border\\Blank.png");

    /**
     * Timer/Mines
     */
    private Icon Zeros = new ImageIcon("src\\pics\\Timer\\TimerZero.png");
    private Icon Ones = new ImageIcon("src\\pics\\Timer\\TimerOne.png");
    private Icon Twos = new ImageIcon("src\\pics\\Timer\\TimerTwo.png");
    private Icon Threes = new ImageIcon("src\\pics\\Timer\\TimerThree.png");
    private Icon Fours = new ImageIcon("src\\pics\\Timer\\TimerFour.png");
    private Icon Fives = new ImageIcon("src\\pics\\Timer\\TimerFive.png");
    private Icon Sixs = new ImageIcon("src\\pics\\Timer\\TimerSix.png");
    private Icon Sevens = new ImageIcon("src\\pics\\Timer\\TimerSeven.png");
    private Icon Eights = new ImageIcon("src\\pics\\Timer\\TimerEight.png");
    private Icon Nines = new ImageIcon("src\\pics\\Timer\\TimerNine.png");
    private Icon minuss = new ImageIcon("src\\pics\\Timer\\Timer-.png");
     /**
     * Faces
     */
        /**
         * Smile
         */
            private Icon Smile1 = new ImageIcon("src\\pics\\Faces\\Smile\\S1.png");
            private Icon Smile2 = new ImageIcon("src\\pics\\Faces\\Smile\\S2.png");
            private Icon Smile3 = new ImageIcon("src\\pics\\Faces\\Smile\\S3.png");
            private Icon Smile4 = new ImageIcon("src\\pics\\Faces\\Smile\\S4.png");
        /**
         * Dead
         */
            private Icon Dead1 = new ImageIcon("src\\pics\\Faces\\Dead\\D1.png");
            private Icon Dead2 = new ImageIcon("src\\pics\\Faces\\Dead\\D2.png");
            private Icon Dead3 = new ImageIcon("src\\pics\\Faces\\Dead\\D3.png");
            private Icon Dead4 = new ImageIcon("src\\pics\\Faces\\Dead\\D4.png");
        /**
        * Bro
        */
            private Icon Bro1 = new ImageIcon("src\\pics\\Faces\\Bro\\B1.png");
            private Icon Bro2 = new ImageIcon("src\\pics\\Faces\\Bro\\B2.png");
            private Icon Bro3 = new ImageIcon("src\\pics\\Faces\\Bro\\B3.png");
            private Icon Bro4 = new ImageIcon("src\\pics\\Faces\\Bro\\B4.png");
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



    public MinesweeperBeginnerFrame(int lvl){
        super("Minesweeper");
        lvlChoose(lvl);
        GAME_LVL = lvl;
        buttons = new JButton[ROWS][COLS];
        mines = new int[ROWS - 4][COLS - 2];
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(ROWS, COLS));
        setSize(COLS*25 + 16, ROWS*25 + 39);
        enableInputMethods(true);
        this.setResizable(false);
        drawBoard();
        generateMines();
        countNeighbourMines();
        setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
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

    public void lvlChoose(int lvl){
        if(lvl == 1){
            ROWS = rowsB;
            COLS = colsB;
            NUMBER_OF_MINES = minesB;
            NUMBER_OF_MINES_FOR_COUNTER = minesB;
        }
        else if(lvl == 2){
            ROWS = rowsM;
            COLS = colsM;
            NUMBER_OF_MINES = minesM;
            NUMBER_OF_MINES_FOR_COUNTER = minesM;
        }
        else if(lvl == 3){
            ROWS = rowsH;
            COLS = colsH;
            NUMBER_OF_MINES = minesH;
            NUMBER_OF_MINES_FOR_COUNTER = minesH;
        }
        GAME_LVL = lvl;
    }

    /**
     * Function to count all nearby mines on board
     */

    public void countNeighbourMines(){
        if(GAME_LVL == 1 || GAME_LVL == 2) {
            for (int i = 0; i < ROWS - 4; i++) {
                for (int j = 0; j < COLS - 2; j++) {
                    // check if its a mine if is dont change
                    if (mines[i][j] != -1) {
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
                            if (GAME_LVL == 1) {
                                if (!(temporaryTable[k][0] < 0 || temporaryTable[k][1] < 0 || temporaryTable[k][0] > 7 ||
                                        temporaryTable[k][1] > 7)) {
                                    // if not check if mine is there
                                    if (mines[temporaryTable[k][0]][temporaryTable[k][1]] == -1) {
                                        count++;
                                    }
                                }
                            }
                            if (GAME_LVL == 2) {
                                if (!(temporaryTable[k][0] < 0 || temporaryTable[k][1] < 0 || temporaryTable[k][0] > 15 ||
                                        temporaryTable[k][1] > 15)) {
                                    // if not check if mine is there
                                    if (mines[temporaryTable[k][0]][temporaryTable[k][1]] == -1) {
                                        count++;
                                    }
                                }
                            }
                        }
                        mines[i][j] = count;
                    }
                }
            }
        }
        if(GAME_LVL == 3) {
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
                            if (!(temporaryTable[k][0] < 0 || temporaryTable[k][1] < 0 || temporaryTable[k][0] > 29 ||
                                    temporaryTable[k][1] > 15)) {
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
                    buttons[i][j] = new JButton(TopRightCorner);
                    buttons[i][j].addActionListener(this);
                }
                else if(i == ROWS - 1 && j == 0) {
                    buttons[i][j] = new JButton(DownRightCorner);
                }
                else if(i == 0 && j == COLS - 1){
                    buttons[i][j] = new JButton(TopLeftCorner);
                }
                else if(i == ROWS - 1 && j == COLS - 1){
                    buttons[i][j] = new JButton(DownLeftCorner);
                }
                else if(i == 0) {
                    buttons[i][j] = new JButton(TopBorder);
                }
                else if(j == 0) {
                    buttons[i][j] = new JButton(LeftBorder);
                }
                else if(j == COLS - 1){
                    buttons[i][j] = new JButton(RightBorder);
                }
                else if(i == ROWS - 1){
                    buttons[i][j] = new JButton(DownBorder);
                }
             /*
              Mines counter
             */
                else if(i == 1 && (j == 1 || j == 2 || j == 3)){
                    buttons[i][j] = new JButton(Zeros);
                }
             /*
              Timer counter
             */
                else if(i == 1 && (j == COLS - 4 || j == COLS - 3 || j == COLS - 2)){
                    buttons[i][j] = new JButton(Zeros);
                }
             /*
              Face
             */
                else if((i == 1 && j == COLS/2 - 1)){
                    buttons[i][j] = new JButton(Smile1);
                    buttons[i][j].addActionListener(this);
                }
                else if(i == 1 && j == COLS/2){
                    buttons[i][j] = new JButton(Smile2);
                    buttons[i][j].addActionListener(this);
                }
                else if(i == 1) {
                    buttons[i][j] = new JButton(Blank);
                    buttons[i][j].addActionListener(this);
                }else if(i == 2 && j == COLS/2 - 1){
                    buttons[i][j] = new JButton(Smile4);
                    buttons[i][j].addActionListener(this);
                }
                else if(i == 2 && j == COLS/2 ){
                    buttons[i][j] = new JButton(Smile3);
                    buttons[i][j].addActionListener(this);
                }
             /*
              Upper fill
             */
                else if((i == 2 && j == COLS - 2) ){
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
                                if(temp.getIcon() == Untouched) {
                                    if (temp.getIcon() != Flag)
                                        temp.setIcon(Flag);
                                }
                                else if(temp.getIcon()==Flag){
                                    temp.setIcon(Untouched);
                                }
                            }
                            countFlagsForMines();
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
            int numberToCheck = getRandomNumberInRange(1, (ROWS - 4)*(COLS-2));
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
        if(GAME_LVL == 1 || GAME_LVL == 2) {
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
                if (GAME_LVL == 1) {
                    if (!(temporaryTable[k][0] < 0 || temporaryTable[k][1] < 0 || temporaryTable[k][0] > 7 ||
                            temporaryTable[k][1] > 7)) {
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
                if (GAME_LVL == 2) {
                    if (!(temporaryTable[k][0] < 0 || temporaryTable[k][1] < 0 || temporaryTable[k][0] > 15 ||
                            temporaryTable[k][1] > 15)) {
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
        }
        if(GAME_LVL == 3){
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
                if (!(temporaryTable[k][0] < 0 || temporaryTable[k][1] < 0 || temporaryTable[k][0] > 15 ||
                        temporaryTable[k][1] > 29)) {
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
                    if(buttons[i][j].getIcon() == Untouched){
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
                if(mines[i-3][j-1] == -1 && buttons[i][j].getIcon() != Flag){
                    buttons[i][j].setIcon(Flag);
                }
            }
        }
    }

    public void countFlagsForMines(){
        NUMBER_OF_FLAGS = 0;
        for(int i = 3; i <ROWS - 1; i++) {
            for (int j = 1; j < COLS - 1; j++) {
                if(buttons[i][j].getIcon() == Flag){
                    NUMBER_OF_FLAGS++;
                }
            };
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
        if(number == 0){
            return Zeros;
        }if(number == 1){
            return Ones;
        }if(number == 2){
            return Twos;
        }if(number == 3){
            return Threes;
        }if(number == 4){
            return Fours;
        }if(number == 5){
            return Fives;
        }if(number == 6){
            return Sixs;
        }if(number == 7){
            return Sevens;
        }if(number == 8){
            return Eights;
        }if(number == 9){
            return Nines;
        }
        return Zeros;
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
                    buttons[i][j].setIcon(NotClicked);
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
            //i == 1 && (j == 6 || j == 7 || j == 8
            buttons[1][COLS - 4].setIcon(checkNumber(hundreds));
            buttons[1][COLS - 3].setIcon(checkNumber(tens));
            buttons[1][COLS - 2].setIcon(checkNumber(ones));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == buttons[1][COLS/2 - 1] || source == buttons[1][COLS/2] || source == buttons[2][COLS/2 - 1] || source == buttons[2][COLS/2]){
            dispose();
            new MinesweeperBeginnerFrame(GAME_LVL);
        }
        if(source == buttons[0][0]){
            showAllMines();
        }
        for(int i = 3; i < ROWS - 1; i++){
            for(int j = 1; j < COLS - 1; j++){
                if(source == buttons[i][j]) {
                    if (buttons[i][j].getIcon() != Flag) {
                        if (mines[i - 3][j - 1] == -1) {
                            GAME_STATUS = false;
                            showAllMines();
                            buttons[i][j].setIcon(MineClicked);
                            disableAllButtons();
                            paintFace(Dead1, Dead2, Dead3, Dead4);
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
                        countFlagsForMines();
                    }
                    if(checkIfGameWon()){
                        GAME_STATUS = false;
                        paintFace(Bro1, Bro2, Bro3, Bro4);
                        addAllFlags();
                        disableAllButtons();
                    }
                }
            }
        }

    }
}
