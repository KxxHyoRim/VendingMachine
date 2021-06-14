import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Set;

public class UserPanel extends JFrame {

    private MyPanel panel = new MyPanel();
    private DataManager dm = new DataManager();
    HashMap<String, AbsDataManager> list_menu = dm.list_menu;
    HashMap<String, String> EngToKor = dm.EngToKor;


    class MyItemListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {

        }
    }


    public UserPanel(){
        setTitle("Vending Machine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(null);
        panel.setLayout(null);
        setContentPane(panel);

        Set<String> menuNameSet = list_menu.keySet();       // dataManager의 메뉴 이름 가져오기
        String[] menuName = new String[menuNameSet.size()]; // 메뉴 영어 이름 set을 Array로 변환
        menuNameSet.toArray(menuName);

        new oneMenu(147,210,EngToKor.get(menuName[0]) , list_menu.get(menuName[0]).price);
        new oneMenu(210,210,EngToKor.get(menuName[1]) , list_menu.get(menuName[1]).price);
        new oneMenu(273,210,EngToKor.get(menuName[2]) , list_menu.get(menuName[2]).price);
        new oneMenu(147,361,EngToKor.get(menuName[3]) , list_menu.get(menuName[3]).price);
        new oneMenu(210,361,EngToKor.get(menuName[4]) , list_menu.get(menuName[4]).price);
        new oneMenu(273,361,EngToKor.get(menuName[5]) , list_menu.get(menuName[5]).price);
        new oneMenu(147,512,EngToKor.get(menuName[6]) , list_menu.get(menuName[6]).price);
        new oneMenu(210,512,EngToKor.get(menuName[7]) , list_menu.get(menuName[7]).price);
        new oneMenu(273,512,EngToKor.get(menuName[8]) , list_menu.get(menuName[8]).price);


        setAlwaysOnTop(true);
        setLocation(1300, 0);
        setSize(600, 1200);
        setVisible(true);

    }

    class oneMenu extends JFrame{
        oneMenu(int locX, int locY, String n, int p){

            JLabel name = new JLabel();
            name.setFont(new Font("고딕체", Font.BOLD, 12));
            name.setHorizontalAlignment(SwingConstants.CENTER);
            name.setText(n);
//            name.setBorder(BorderFactory.createLineBorder(Color.black));
            name.setBackground(Color.BLUE);
            name.setOpaque(false);
            name.setForeground(Color.BLACK);
            name.setSize(55, 15);
            name.setLocation(locX, locY);
            panel.add(name);

            JLabel price = new JLabel();
            price.setFont(new Font("고딕체", Font.PLAIN, 13));
            price.setHorizontalAlignment(SwingConstants.CENTER);
            price.setText(String.valueOf(p));
            price.setBackground(Color.gray);
            price.setForeground(Color.BLACK);
            price.setSize(55, 15);
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
