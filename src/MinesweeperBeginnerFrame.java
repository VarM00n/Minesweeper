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
     * Attributed used within game itself
     */
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

    public MinesweeperBeginnerFrame(int lvl, int ROWs, int COLs, int MINEs) {
        super("Minesweeper");
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
        setLocationRelativeTo(null);
        enableInputMethods(true);
        this.setResizable(false);
        drawBoard();
        generateMines();
        countNeighbourMines();
        setVisible(true);
        countFlagsForMines();
        Timer timer = new Timer();
        // timer to add seconds and change timer
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SECONDS_COUNTER++;
                setTimer();
            }
        }, 1000, 1000);
    }

    /**
     * Count all nearby mines on board. Used for drawing at the beginning.
     */
    public void countNeighbourMines() {
        // generating data for double dimensional table representing 8 neighboring positions
        for (int i = 0; i < ROWS - 4; i++) {
            for (int j = 0; j < COLS - 2; j++) {
                // check if its a mine if is, dont change
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
                    //counting all nearby mines by going though all positions
                    for (int k = 0; k < 8; k++) {
                        //if position is not in game boundaries
                        if (!(temporaryTable[k][0] < 0 || temporaryTable[k][1] < 0 || temporaryTable[k][0] > COLS - 3 ||
                                temporaryTable[k][1] > ROWS - 5)) {
                            if (mines[temporaryTable[k][1]][temporaryTable[k][0]] == -1) {
                                count++;
                            }
                        }
                    }
                    //adds the amount of mines in specific place in table for further processes
                    mines[i][j] = count;
                }
            }
        }
}

    /**
     * Draw graphical user interface by changing icon of every button
     */
    public void drawBoard(){
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                //Borders & Corners
                if(i == 0 && j == 0) {
                    buttons[i][j] = new JButton(Pics.TopRightCorner);
                    buttons[i][j].addActionListener(this);
                }
                else if(i == ROWS - 1 && j == 0) {
                    buttons[i][j] = new JButton(Pics.DownRightCorner);
                }
                else if(i == 0 && j == COLS - 1){
                    buttons[i][j] = new JButton(Pics.TopLeftCorner);
                }
                else if(i == ROWS - 1 && j == COLS - 1){
                    buttons[i][j] = new JButton(Pics.DownLeftCorner);
                }
                else if(i == 0) {
                    buttons[i][j] = new JButton(Pics.TopBorder);
                }
                else if(j == 0) {
                    buttons[i][j] = new JButton(Pics.LeftBorder);
                }
                else if(j == COLS - 1){
                    buttons[i][j] = new JButton(Pics.RightBorder);
                }
                else if(i == ROWS - 1){
                    buttons[i][j] = new JButton(Pics.DownBorder);
                }
                //Mines & Time counter
                else if(i == 1 && (j == 1 || j == 2 || j == 3 || j == COLS - 4 || j == COLS - 3 || j == COLS - 2)){
                    buttons[i][j] = new JButton(Pics.Zeros);
                }
                //Face
                else if((i == 1 && j == COLS/2 - 1)){
                    buttons[i][j] = new JButton(Pics.Smile1);
                    buttons[i][j].addActionListener(this);
                }
                else if(i == 1 && j == COLS/2){
                    buttons[i][j] = new JButton(Pics.Smile2);
                    buttons[i][j].addActionListener(this);
                }
                else if(i == 1) {
                    buttons[i][j] = new JButton(Pics.Blank);
                    buttons[i][j].addActionListener(this);
                }else if(i == 2 && j == COLS/2 - 1){
                    buttons[i][j] = new JButton(Pics.Smile4);
                    buttons[i][j].addActionListener(this);
                }
                else if(i == 2 && j == COLS/2 ){
                    buttons[i][j] = new JButton(Pics.Smile3);
                    buttons[i][j].addActionListener(this);
                }
                //Upper fill
                else if((i == 2 && j == COLS - 2) ){
                    buttons[i][j] = new JButton(Pics.Logo);
                }
                else if(i == 2){
                    buttons[i][j] = new JButton(Pics.FillWithBorder);
                }
                else {
                    buttons[i][j] = new JButton(Pics.Untouched);
                    buttons[i][j].addActionListener(this);
                    JButton temp = buttons[i][j];
                    buttons[i][j].addMouseListener(new MouseAdapter() {
                        //part of code used for setting flag icon if clicked with right mouse button
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if(GAME_STATUS) {
                                if (e.getButton() == MouseEvent.BUTTON3) {
                                    if (temp.getIcon() == Pics.Untouched) {
                                        if (temp.getIcon() != Pics.Flag)
                                            temp.setIcon(Pics.Flag);
                                    } else if (temp.getIcon() == Pics.Flag) {
                                        temp.setIcon(Pics.Untouched);
                                    }
                                }
                                countFlagsForMines();
                            }
                        }
                        //if mouse is pressed change face to Wow
                        @Override
                        public void mousePressed(MouseEvent e) {
                            paintFace(Pics.Wow1, Pics.Wow2, Pics.Wow3, Pics.Wow4);
                        }
                        //if mouse is released and game is in progress change face back to smile
                        @Override
                        public void mouseReleased(MouseEvent e) {
                            if(GAME_STATUS) {
                                paintFace(Pics.Smile1, Pics.Smile2, Pics.Smile3, Pics.Smile4);
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
     * Generate mines in given field (row/cols/mines)
     */
    public void generateMines(){
        int [] temporaryTable = new int[NUMBER_OF_MINES];
        int TemporaryNumberOfMines = NUMBER_OF_MINES;
        //setting all values in temporary table to ones unable to being generated later in method
        for(int i = 0; i < NUMBER_OF_MINES; i++){
            temporaryTable[i] = (ROWS - 4)*(COLS-2) + 1;
        }
        //setting in place all mines
        while(NUMBER_OF_MINES > 0){
            //generating random number representing a place on board
            int numberToCheck = getRandomNumberInRange((ROWS - 4)*(COLS-2));
            int check = 0;
            //checking if generated number is already in use
            for(int i = 0; i < TemporaryNumberOfMines; i++){
                if (numberToCheck == temporaryTable[i]) {
                    check = 1;
                    break;
                }
            }
            //if number is not in use change temporary table and put -1(mine) in correct place
            if(check == 0){
                temporaryTable[NUMBER_OF_MINES-1] = numberToCheck;
                addToMineTable(numberToCheck);
                NUMBER_OF_MINES -= 1;
            }
        }
    }

    /**
     * complementary method for generateMines(). Calculates a place in mine table
     *
     * @param number variable specifying place of mine
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
     * Generating random number in range
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
        // double dimensional table to save all neighboring positions
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
            // checking if a position is in bounds
            if (!(temporaryTable[k][0] < 0 || temporaryTable[k][1] < 0 || temporaryTable[k][0] > ROWS - 5 ||
                    temporaryTable[k][1] > COLS - 3)) {
                // new point in order to not work on origin coordinates
                Point pNext = new Point();
                pNext.coordinates = temporaryTable[k];
                pNext.touched = false;
                int counter = 0;
                for (Point p : set) {
                    //if position pNext is not within set - add
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
     * Used for revealing all positions with empty filling or numbers. Leaves positions with mines unrevealed.
     * @param set   positions to check
     * @param point place to start
     */
    private void finishAddToListNonRepeatable(Set<Point> set, int[] point){
        Point pStart = new Point();
        pStart.coordinates = point;
        set.add(pStart);
        addToListNonRepeatable(set, point);
        pStart.touched = true;
        //repeats as long as counter != 0
        while(true){
            int count = 0;
            // every position in set has two attributes. If a given position has been processed attribute 'touched'
            // change so it won't be processed again
            for(Point p: set){
                if(!p.touched){
                    p.touched = true;
                    // at this moment a set has only positions with no mine or number in it
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

    /**
     * Check if game won. It goes through every position and check if icon is different than untouched which means
     * it hasn't been clicked.
     *
     * @return true if game won, false if game yet not won
     */
    public boolean checkIfGameWon(){
        for(int i = 3; i < ROWS - 1; i++) {
            for (int j = 1; j < COLS - 1; j++) {
                if(mines[i-3][j-1] != -1){
                    if(buttons[i][j].getIcon() == Pics.Untouched){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * In Minesweeper there is no condition of marking all flags, so if game is won and some flags are not marked
     * this function do it.
     */
    public void addAllFlags(){
        for(int i = 3; i < ROWS - 1; i++) {
            for (int j = 1; j < COLS - 1; j++) {
                if(mines[i-3][j-1] == -1 && buttons[i][j].getIcon() != Pics.Flag){
                    buttons[i][j].setIcon(Pics.Flag);
                }
            }
        }
    }

    /**
     * Change counter based on a difference between number of mines and flags on board
     */
    public void countFlagsForMines(){
        NUMBER_OF_FLAGS = 0;
        // counting flags
        for(int i = 3; i <ROWS - 1; i++) {
            for (int j = 1; j < COLS - 1; j++) {
                if(buttons[i][j].getIcon() == Pics.Flag){
                    NUMBER_OF_FLAGS++;
                }
            }
        }
        NUMBER_FOR_COUNTER = NUMBER_OF_MINES_FOR_COUNTER - NUMBER_OF_FLAGS;
        int forCounter = NUMBER_FOR_COUNTER;
        int hundreds = 0;
        int tens = 0;
        int ones = forCounter;
        // change number into hundreds, tens and ones
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
        //change counter based on number given above
        changeCounter(hundreds, tens, ones);
    }

    /**
     * converts number into icon
     *
     * @param number    number to be checked and converted
     * @return          returns an icon based on number
     */
    public Icon checkNumber(int number){
        if(number == 0){
            return Pics.Zeros;
        }if(number == 1){
            return Pics.Ones;
        }if(number == 2){
            return Pics.Twos;
        }if(number == 3){
            return Pics.Threes;
        }if(number == 4){
            return Pics.Fours;
        }if(number == 5){
            return Pics.Fives;
        }if(number == 6){
            return Pics.Sixs;
        }if(number == 7){
            return Pics.Sevens;
        }if(number == 8){
            return Pics.Eights;
        }if(number == 9){
            return Pics.Nines;
        }
        return Pics.Zeros;
    }

    /**
     * change icon of buttons responsible for counter
     * @param hundreds  number of hundreds
     * @param tens      number of tens
     * @param ones      number of ones
     */
    public void changeCounter(int hundreds, int tens, int ones){
        //i == 1 && (j == 1 || j == 2 || j == 3)
        buttons[1][1].setIcon(checkNumber(hundreds));
        buttons[1][2].setIcon(checkNumber(tens));
        buttons[1][3].setIcon(checkNumber(ones));
    }

    /**
     * Painting face with a correct icons. Face is divided into 4 different parts.
     * @param F1        icon for face
     * @param F2        icon for face
     * @param F3        icon for face
     * @param F4        icon for face
     */
    public void paintFace(Icon F1, Icon F2,Icon F3,Icon F4){
        buttons[1][COLS/2 - 1].setIcon(F1);
        buttons[1][COLS/2].setIcon(F2);
        buttons[2][COLS/2 - 1].setIcon(F4);
        buttons[2][COLS/2].setIcon(F3);
    }

    /**
     * cheating method that shows all the mines
     */
    public void showAllMines(){
        for(int i = 3; i < ROWS - 1; i++) {
            for (int j = 1; j < COLS - 1; j++) {
                if(mines[i-3][j-1] == -1){
                    buttons[i][j].setIcon(Pics.NotClicked);
                }
            }
        }
    }

    /**
     * disabling all buttons so none can be clicked or change
     */
    public void disableAllButtons(){
        for(int i = 3; i < ROWS - 1; i++){
            for(int j = 1; j < COLS - 1; j++) {
                buttons[i][j].setEnabled(false);
                buttons[i][j].setDisabledIcon(buttons[i][j].getIcon());
            }
        }
    }

    /**
     * method to change timer based on parameter that change every second
     */
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

    /**
     * handling all clicks
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        // if face is clicked restart game
        if(source == buttons[1][COLS/2 - 1] || source == buttons[1][COLS/2] || source == buttons[2][COLS/2 - 1] || source == buttons[2][COLS/2]){
            new MinesweeperBeginnerFrame(GAME_LVL, ROWS, COLS, MINESS);
            dispose();
        }
        // cheat button to show all mines
        if(source == buttons[0][0]){
            showAllMines();
        }
        // handling all buttons within game
        for(int i = 3; i < ROWS - 1; i++){
            for(int j = 1; j < COLS - 1; j++){
                if(source == buttons[i][j]) {
                    if (buttons[i][j].getIcon() != Pics.Flag) {
                        // if mine has been clicked game lost, show all mines, disable all buttons, change face to dead
                        if (mines[i - 3][j - 1] == -1) {
                            GAME_STATUS = false;
                            showAllMines();
                            buttons[i][j].setIcon(Pics.MineClicked);
                            disableAllButtons();
                            paintFace(Pics.Dead1, Pics.Dead2, Pics.Dead3, Pics.Dead4);
                        }
                        // if empty spot has been clicked reveals all empty spot and numbers that neighbors
                        else if (mines[i - 3][j - 1] == 0) {
                            Set<Point> set = new HashSet<>();
                            int[] var = new int[2];
                            var[0] = i - 3;
                            var[1] = j - 1;
                            finishAddToListNonRepeatable(set, var);
                            for (Point p : set) {
                                if (mines[p.coordinates[0]][p.coordinates[1]] == 0) {
                                    buttons[p.coordinates[0] + 3][p.coordinates[1] + 1].setIcon(Pics.Touched);
                                } else if (mines[p.coordinates[0]][p.coordinates[1]] == 1) {
                                    buttons[p.coordinates[0] + 3][p.coordinates[1] + 1].setIcon(Pics.OneB);
                                } else if (mines[p.coordinates[0]][p.coordinates[1]] == 2) {
                                    buttons[p.coordinates[0] + 3][p.coordinates[1] + 1].setIcon(Pics.TwoB);
                                } else if (mines[p.coordinates[0]][p.coordinates[1]] == 3) {
                                    buttons[p.coordinates[0] + 3][p.coordinates[1] + 1].setIcon(Pics.ThreeB);
                                } else if (mines[p.coordinates[0]][p.coordinates[1]] == 4) {
                                    buttons[p.coordinates[0] + 3][p.coordinates[1] + 1].setIcon(Pics.FourB);
                                } else if (mines[p.coordinates[0]][p.coordinates[1]] == 5) {
                                    buttons[p.coordinates[0] + 3][p.coordinates[1] + 1].setIcon(Pics.FiveB);
                                } else if (mines[p.coordinates[0]][p.coordinates[1]] == 6) {
                                    buttons[p.coordinates[0] + 3][p.coordinates[1] + 1].setIcon(Pics.SixB);
                                } else if (mines[p.coordinates[0]][p.coordinates[1]] == 7) {
                                    buttons[p.coordinates[0] + 3][p.coordinates[1] + 1].setIcon(Pics.SevenB);
                                } else if (mines[p.coordinates[0]][p.coordinates[1]] == 8) {
                                    buttons[p.coordinates[0] + 3][p.coordinates[1] + 1].setIcon(Pics.EightB);
                                }
                            }
                        }
                        // if a spot with number has been clicked reveals that number
                        else if (mines[i - 3][j - 1] == 1) {
                            buttons[i][j].setIcon(Pics.OneB);
                        } else if (mines[i - 3][j - 1] == 2) {
                            buttons[i][j].setIcon(Pics.TwoB);
                        } else if (mines[i - 3][j - 1] == 3) {
                            buttons[i][j].setIcon(Pics.ThreeB);
                        } else if (mines[i - 3][j - 1] == 4) {
                            buttons[i][j].setIcon(Pics.FourB);
                        } else if (mines[i - 3][j - 1] == 5) {
                            buttons[i][j].setIcon(Pics.FiveB);
                        } else if (mines[i - 3][j - 1] == 6) {
                            buttons[i][j].setIcon(Pics.SixB);
                        } else if (mines[i - 3][j - 1] == 7) {
                            buttons[i][j].setIcon(Pics.SevenB);
                        } else if (mines[i - 3][j - 1] == 8) {
                            buttons[i][j].setIcon(Pics.EightB);
                        }
                        countFlagsForMines();
                    }
                    // handling game lost or won
                    if(checkIfGameWon()){
                        GAME_STATUS = false;
                        paintFace(Pics.Bro1, Pics.Bro2, Pics.Bro3, Pics.Bro4);
                        addAllFlags();
                        disableAllButtons();
                    }
                }
            }
        }
    }
}