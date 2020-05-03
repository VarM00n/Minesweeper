import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class Menu extends JFrame implements ActionListener {

    int ROWS = 20;
    int COLS = 20;
    private JButton Beginner = new JButton();
    private JButton Medium = new JButton();
    private JButton Hard = new JButton();
    private JButton Custom = new JButton();
    private JButton Logo = new JButton();

    private Icon BeginnerB = new ImageIcon("src\\pics\\Menu\\LvlHardness\\Beginner.png");
    private Icon BeginnerClickedB = new ImageIcon("src\\pics\\Menu\\LvlHardness\\BeginnerClicked.png");
    private Icon MediumB = new ImageIcon("src\\pics\\Menu\\LvlHardness\\Medium.png");
    private Icon MediumClickedB = new ImageIcon("src\\pics\\Menu\\LvlHardness\\MediumClicked.png");
    private Icon HardB = new ImageIcon("src\\pics\\Menu\\LvlHardness\\Hard.png");
    private Icon HardClickedB = new ImageIcon("src\\pics\\Menu\\LvlHardness\\HardClicked.png");
    private Icon CustomB = new ImageIcon("src\\pics\\Menu\\LvlHardness\\Custom.png");
    private Icon CustomClickedB = new ImageIcon("src\\pics\\Menu\\LvlHardness\\CustomClicked.png");
    private Icon LogoB = new ImageIcon("src\\pics\\Menu\\LogoForMenu.png");

    public Menu(){
        super("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(COLS*25 + 16, ROWS*25 + 39);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        enableInputMethods(true);
        try {
            this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src\\pics\\MenuPic.png")))));
        } catch (IOException e){
            e.printStackTrace();
        }
        setButtons();
        this.setResizable(false);
        setVisible(true);
    }


    public void setButtons(){
        Beginner.addActionListener(this);
        Beginner.setBorder(new EmptyBorder(0, 0, 0, 0));
        Beginner.setSize(200, 50);
        Beginner.setLocation(40,100);
        Beginner.setIcon(BeginnerB);
        Beginner.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1){
                    Beginner.setIcon(BeginnerClickedB);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Beginner.setIcon(BeginnerB);
            }
        });
        add(Beginner);
        Medium.addActionListener(this);
        Medium.setBorder(new EmptyBorder(0, 0, 0, 0));
        Medium.setSize(177, 50);
        Medium.setLocation(40,175);
        Medium.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1){
                    Medium.setIcon(MediumClickedB);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Medium.setIcon(MediumB);
            }
        });
        Medium.setIcon(MediumB);
        add(Medium);
        Hard.addActionListener(this);
        Hard.setBorder(new EmptyBorder(0, 0, 0, 0));
        Hard.setSize(124, 50);
        Hard.setLocation(40,250);
        Hard.setIcon(HardB);
        Hard.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1){
                    Hard.setIcon(HardClickedB);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Hard.setIcon(HardB);
            }
        });
        add(Hard);
        Logo.addActionListener(this);
        Logo.setBorder(new EmptyBorder(0, 0, 0, 0));
        Logo.setSize(50, 50);
        Logo.setLocation(420,420);
        Logo.setIcon(LogoB);
        add(Logo);
        Custom.addActionListener(this);
        Custom.setBorder(new EmptyBorder(0, 0, 0, 0));
        Custom.setSize(174, 50);
        Custom.setLocation(40,325);
        Custom.setIcon(CustomB);
        Custom.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1){
                    Custom.setIcon(CustomClickedB);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Custom.setIcon(CustomB);
            }
        });
        add(Custom);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == Beginner){
            dispose();
            new MinesweeperBeginnerFrame(1);
        } else if(source == Medium){
            dispose();
            new MinesweeperBeginnerFrame(2);
        } else if(source == Hard){
            dispose();
            new MinesweeperBeginnerFrame(3);
        }
    }
}
