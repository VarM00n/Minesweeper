import java.awt.EventQueue;

public class Play {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MinesweeperBeginnerFrame();
            }
        });
    }
}