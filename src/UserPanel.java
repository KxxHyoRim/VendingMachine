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

        int[] locX = {152, 215, 280,152,215, 280,152,215, 280,};
        int[] locY = {205, 205, 205, 354,354,354,503, 503, 503 };

        for (int i = 0 ; i < menuName.length; i++){
            new setMenu(locX[i],locY[i],EngToKor.get(menuName[i]) , list_menu.get(menuName[i]).price);

            // 빨간 LED
            JButton c1 = new JButton();
            c1.setFont(new Font("Arial", Font.PLAIN, 20));
            c1.setText("●");
            c1.setBackground(Color.BLACK);     // 투명 만들기 전에 배경 지정해줘야됨. 지우면 에러!
            c1.setOpaque(false);            //배경 투명
            c1.setForeground(Color.RED);
            c1.setSize(50, 15);
            c1.setLocation(locX[i], locY[i] + 60);
            panel.add(c1);

        }


        setAlwaysOnTop(true);
        setLocation(1000, 0);
        setSize(810, 1080);
        setVisible(true);

    }

    class setMenu extends JFrame{
        setMenu(int locX, int locY, String n, int p){

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


        }
    }



    class MyPanel extends JPanel{
        private ImageIcon icon = new ImageIcon("vendingMachineBackground2.png");
        private Image img = icon.getImage();

        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
    }


}
