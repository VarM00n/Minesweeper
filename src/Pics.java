import javax.swing.*;

public class Pics {
    /**
     * Corners
     */
    public static Icon TopRightCorner = Pics.getIcon("Border/RightUpCorner.png");
    public static Icon TopLeftCorner = Pics.getIcon("Border/LeftUpCorner.png");
    public static Icon DownRightCorner = Pics.getIcon("Border/RightDownCorner.png");
    public static Icon DownLeftCorner = Pics.getIcon("Border/LeftDownCorner.png");
    /**
     * Borders
     */
    public static Icon DownBorder = Pics.getIcon("Border/DownBorder.png");
    public static Icon TopBorder = Pics.getIcon("Border/UpBorder.png");
    public static Icon LeftBorder = Pics.getIcon("Border/LeftBorder.png");
    public static Icon RightBorder = Pics.getIcon("Border/RightBorder.png");
    /**
     * FIll
     */
    public static Icon FillWithBorder = Pics.getIcon("Border/BlankWithBorder.png");
    public static Icon Logo = Pics.getIcon("Border/Logo.png");
    public static Icon Blank = Pics.getIcon("Border/Blank.png");
    /**
     * Timer/Mines
     */
    public static Icon Zeros = Pics.getIcon("Timer/TimerZero.png");
    public static Icon Ones = Pics.getIcon("Timer/TimerOne.png");
    public static Icon Twos = Pics.getIcon("Timer/TimerTwo.png");
    public static Icon Threes = Pics.getIcon("Timer/TimerThree.png");
    public static Icon Fours = Pics.getIcon("Timer/TimerFour.png");
    public static Icon Fives = Pics.getIcon("Timer/TimerFive.png");
    public static Icon Sixs = Pics.getIcon("Timer/TimerSix.png");
    public static Icon Sevens = Pics.getIcon("Timer/TimerSeven.png");
    public static Icon Eights = Pics.getIcon("Timer/TimerEight.png");
    public static Icon Nines = Pics.getIcon("Timer/TimerNine.png");
//    public static Icon minuss = Pics.getIcon("Timer/Timer-.png");
    /**
     * Smile
     */
    public static Icon Smile1 = Pics.getIcon("Faces/Smile/S1.png");
    public static Icon Smile2 = Pics.getIcon("Faces/Smile/S2.png");
    public static Icon Smile3 = Pics.getIcon("Faces/Smile/S3.png");
    public static Icon Smile4 = Pics.getIcon("Faces/Smile/S4.png");
    /**
     * Dead
     */
    public static Icon Dead1 = Pics.getIcon("Faces/Dead/D1.png");
    public static Icon Dead2 = Pics.getIcon("Faces/Dead/D2.png");
    public static Icon Dead3 = Pics.getIcon("Faces/Dead/D3.png");
    public static Icon Dead4 = Pics.getIcon("Faces/Dead/D4.png");
    /**
     * Bro
     */
    public static Icon Bro1 = Pics.getIcon("Faces/Bro/B1.png");
    public static Icon Bro2 = Pics.getIcon("Faces/Bro/B2.png");
    public static Icon Bro3 = Pics.getIcon("Faces/Bro/B3.png");
    public static Icon Bro4 = Pics.getIcon("Faces/Bro/B4.png");
    /**
     * Bro
     */
    public static Icon Wow1 = Pics.getIcon("Faces/WOW/W1.png");
    public static Icon Wow2 = Pics.getIcon("Faces/WOW/W2.png");
    public static Icon Wow3 = Pics.getIcon("Faces/WOW/W3.png");
    public static Icon Wow4 = Pics.getIcon("Faces/WOW/W4.png");
    /**
     * Game
     */
    public static Icon Untouched = Pics.getIcon("Game/BlankBlock.png");
    public static Icon Touched = Pics.getIcon("Game/clickedBlock.png");
    public static Icon OneB = Pics.getIcon("Game/One.png");
    public static Icon TwoB = Pics.getIcon("Game/Two.png");
    public static Icon ThreeB = Pics.getIcon("Game/Three.png");
    public static Icon FourB = Pics.getIcon("Game/Four.png");
    public static Icon FiveB = Pics.getIcon("Game/Five.png");
    public static Icon SixB = Pics.getIcon("Game/Six.png");
    public static Icon SevenB = Pics.getIcon("Game/Seven.png");
    public static Icon EightB = Pics.getIcon("Game/Eight.png");
    public static Icon MineClicked = Pics.getIcon("Game/bombClicked.png");
    public static Icon NotClicked = Pics.getIcon("Game/BombNotClicked.png");
    public static Icon Flag = Pics.getIcon("Game/flag.png");

    /**
     * Menu
     */

    private static Icon BeginnerB = Pics.getIcon("Menu\\LvlHardness\\Beginner.png");
//    private static Icon BeginnerClickedB = new ImageIcon("src\\pics\\Menu\\LvlHardness\\BeginnerClicked.png");
    private static Icon MediumB = Pics.getIcon("Menu\\LvlHardness\\Medium.png");
//    private static Icon MediumClickedB = new ImageIcon("src\\pics\\Menu\\LvlHardness\\MediumClicked.png");
//    private static Icon HardB = new ImageIcon("src\\pics\\Menu\\LvlHardness\\Hard.png");
//    private static Icon HardClickedB = new ImageIcon("src\\pics\\Menu\\LvlHardness\\HardClicked.png");
//    private static Icon CustomB = new ImageIcon("src\\pics\\Menu\\LvlHardness\\Custom.png");
//    private static Icon CustomClickedB = new ImageIcon("src\\pics\\Menu\\LvlHardness\\CustomClicked.png");
//    private static Icon LogoB = new ImageIcon("src\\pics\\Menu\\LogoForMenu.png");
//    private static Icon CancelB = new ImageIcon("src\\pics\\Menu\\LvlHardness\\Cancel.png");
//    private static Icon CancelClickedB = new ImageIcon("src\\pics\\Menu\\LvlHardness\\CancelClicked.png");
//    private static Icon OkB = new ImageIcon("src\\pics\\Menu\\LvlHardness\\Ok.png");
//    private static Icon OkClickedB = new ImageIcon("src\\pics\\Menu\\LvlHardness\\OkClicked.png");



    private static ImageIcon getIcon(String path) {
        return new ImageIcon(Pics.class.getResource("/pics/" + path));
    }
}
