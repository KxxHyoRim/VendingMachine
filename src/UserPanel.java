import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class UserPanel extends JFrame {

    private MyPanel panel = new MyPanel();
    private DataManager dm = new DataManager();
    HashMap<String, AbsDataManager> list_menu = dm.list_menu;


    public UserPanel(){
        setTitle("Vending Machine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(null);
        panel.setLayout(null);
        setContentPane(panel);

        System.out.println(list_menu.keySet());

        new oneMenu(147,210, "Sugar", list_menu.get("Sugar").price);
        new oneMenu(210,210, "Milk",list_menu.get("Milk").price);
        new oneMenu(273,210, "Black",list_menu.get("Black").price);

        setAlwaysOnTop(true);
        setLocation(1300, 0);
        setSize(600, 1200);
        setVisible(true);

    }

    class oneMenu extends JFrame{
        oneMenu(int locX, int locY, String n, int p){

            JLabel name = new JLabel();
            name.setFont(new Font("Arial", Font.BOLD, 15));
            name.setHorizontalAlignment(SwingConstants.CENTER);
            name.setText(n);
//            name.setBorder(BorderFactory.createLineBorder(Color.black));
            name.setBackground(Color.BLUE);
            name.setOpaque(false);
            name.setForeground(Color.BLACK);
            name.setSize(50, 15);
            name.setLocation(locX, locY);
            panel.add(name);

            JLabel price = new JLabel();
            price.setFont(new Font("Arial", Font.PLAIN, 13));
            price.setHorizontalAlignment(SwingConstants.CENTER);
            price.setText(String.valueOf(p));
            price.setBackground(Color.gray);
            price.setForeground(Color.BLACK);
            price.setSize(50, 15);
            price.setLocation(locX, locY + 25);
            panel.add(price);

            JButton c1 = new JButton();
            c1.setFont(new Font("Arial", Font.PLAIN, 20));
            c1.setText("●");
            c1.setBackground(Color.BLUE);
            c1.setOpaque(false);
            c1.setForeground(Color.RED);
            c1.setSize(50, 15);
            c1.setLocation(locX, locY + 60);
            panel.add(c1);
        }


    }

    class MyPanel extends JPanel{
        private ImageIcon icon = new ImageIcon("vendingMachineBackground.png");
        private Image img = icon.getImage();

        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
    }


}
