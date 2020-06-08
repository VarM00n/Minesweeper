import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class Menu extends JFrame implements ActionListener {

    int ROWS = 20;
    int COLS = 20;
    private JTextField Rows = new JTextField();
    private JTextField Cols = new JTextField();
    private JTextField Mines = new JTextField();
    private JButton Beginner = new JButton();
    private JButton Medium = new JButton();
    private JButton Hard = new JButton();
    private JButton Custom = new JButton();
    private JButton Logo = new JButton();
    private JButton Cancel = new JButton();
    private JButton Ok = new JButton();

    public Menu(){
        super("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(COLS*25 + 16, ROWS*25 + 39);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        enableInputMethods(true);
        paintBackground("res\\pics\\MenuPic.png");
        setButtons();
        this.setResizable(false);
        setVisible(true);
    }


    public void setButtons(){
        Beginner.addActionListener(this);
        Beginner.setBorder(new EmptyBorder(0, 0, 0, 0));
        Beginner.setSize(200, 50);
        Beginner.setLocation(40,100);
        Beginner.setIcon(Pics.BeginnerB);
        Beginner.setDisabledIcon(Pics.BeginnerB);
        Beginner.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(Beginner.isEnabled()) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        Beginner.setIcon(Pics.BeginnerClickedB);
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Beginner.setIcon(Pics.BeginnerB);
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
                if(Medium.isEnabled()) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        Medium.setIcon(Pics.MediumClickedB);
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Medium.setIcon(Pics.MediumB);
            }
        });
        Medium.setIcon(Pics.MediumB);
        Medium.setDisabledIcon(Pics.MediumB);
        add(Medium);

        Hard.addActionListener(this);
        Hard.setBorder(new EmptyBorder(0, 0, 0, 0));
        Hard.setSize(124, 50);
        Hard.setLocation(40,250);
        Hard.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(Hard.isEnabled()) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        Hard.setIcon(Pics.HardClickedB);
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Hard.setIcon(Pics.HardB);
            }
        });
        Hard.setIcon(Pics.HardB);
        Hard.setDisabledIcon(Pics.HardB);
        add(Hard);

        Logo.addActionListener(this);
        Logo.setBorder(new EmptyBorder(0, 0, 0, 0));
        Logo.setSize(50, 50);
        Logo.setLocation(420,420);
        Logo.setIcon(Pics.LogoB);
        Logo.setDisabledIcon(Pics.LogoB);
        add(Logo);

        Custom.addActionListener(this);
        Custom.setBorder(new EmptyBorder(0, 0, 0, 0));
        Custom.setSize(174, 50);
        Custom.setLocation(40,325);
        Custom.setIcon(Pics.CustomB);
        Custom.setDisabledIcon(Pics.CustomB);
        Custom.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(Custom.isEnabled()) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        Custom.setIcon(Pics.CustomClickedB);
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Custom.setIcon(Pics.CustomB);
            }
        });
        add(Custom);

        Cancel.addActionListener(this);
        Cancel.setBorder(new EmptyBorder(0, 0, 0, 0));
        Cancel.setSize(104, 37);
        Cancel.setLocation(268,345);
        Cancel.setIcon(Pics.CancelB);
        Cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1){
                    Cancel.setIcon(Pics.CancelClickedB);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Cancel.setIcon(Pics.CancelB);
            }
        });

        Ok.addActionListener(this);
        Ok.setBorder(new EmptyBorder(0, 0, 0, 0));
        Ok.setSize(50, 37);
        Ok.setLocation(383,345);
        Ok.setIcon(Pics.OkB);
        Ok.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1){
                    Ok.setIcon(Pics.OkClickedB);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Ok.setIcon(Pics.OkB);
            }
        });
        
        Rows.setBounds(370, 257, 40, 23);
        Rows.setBackground(Color.LIGHT_GRAY);
        Cols.setBounds(370, 288, 40, 23);
        Cols.setBackground(Color.LIGHT_GRAY);
        Mines.setBounds(370, 319, 40, 23);
        Mines.setBackground(Color.LIGHT_GRAY);
        Rows.setText("");Cols.setText("");Mines.setText("");
    }

    public void paintBackground(String path){
        try {
            this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File(path)))));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean checkIfValid(String rows, String cols, String mines){
        if(rows.equals("") || cols.equals("") || mines.equals("")){
            return false;
        }
        return Integer.parseInt(Rows.getText()) >= 8 && Integer.parseInt(Cols.getText()) >= 8 &&
                Integer.parseInt(Rows.getText()) <= 40 && Integer.parseInt(Rows.getText()) <= 60;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == Beginner){
            dispose();
            new MinesweeperBeginnerFrame(1, 12, 10, 10);
        } else if(source == Medium){
            dispose();
            new MinesweeperBeginnerFrame(2, 20, 18, 40);
        } else if(source == Hard){
            dispose();
            new MinesweeperBeginnerFrame(3, 20, 32, 99);
        } else if(source == Custom){
            paintBackground("res\\pics\\MenuPicAfterCustom.png");
            setVisible(true);
            add(Beginner);add(Medium);add(Hard);add(Custom);add(Logo);add(Cancel);add(Ok);add(Rows);add(Cols);add(Mines);
            Beginner.setEnabled(false);Medium.setEnabled(false);Hard.setEnabled(false);Custom.setEnabled(false);

        } else if(source == Cancel){
            paintBackground("res\\pics\\MenuPic.png");
            setVisible(true);
            add(Beginner);add(Medium);add(Hard);add(Custom);add(Logo);
            Beginner.setEnabled(true);Medium.setEnabled(true);Hard.setEnabled(true);Custom.setEnabled(true);
            Rows.setText("");Cols.setText("");Mines.setText("");
        } else if(source == Ok){
            if(checkIfValid(Rows.getText(), Cols.getText(), Mines.getText())){
                    dispose();
                    new MinesweeperBeginnerFrame(4, Integer.parseInt(Rows.getText()) + 4,
                            Integer.parseInt(Cols.getText()) + 2, Integer.parseInt(Mines.getText()));
            }
             else{
                Rows.setText("");Cols.setText("");Mines.setText("");
            }
        }
        }
    }
