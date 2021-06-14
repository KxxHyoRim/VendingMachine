import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Set;

public class UserPanel extends JFrame {

    final int ON_SALE = 1;
    final int MAKING = 2;
    int menu_num;

    private MyPanel panel = new MyPanel();
    private DataManager dm = new DataManager();
    HashMap<String, AbsDataManager> list_menu = dm.list_menu;
    HashMap<String, String> EngToKor = dm.EngToKor;

    String[] menuName;
    JButton[] pushLED;
    int[] price;

    int[] locX = {152, 215, 280,152,215, 280,152,215, 280,};
    int[] locY = {205, 205, 205, 354,354,354,503, 503, 503 };

    int cash = 0;

    JLabel status;
    JLabel money;
    JButton btnWrong;
    JButton btn1000;
    JButton btn500;
    JButton btn100;
    JButton btn50;



    public UserPanel(){
        /** GUI basic setting */
        setTitle("Vending Machine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(null);
        panel.setLayout(null);
        setContentPane(panel);


        /** 메뉴이름(영어) _ String[] Array */
        Set<String> menuNameSet = list_menu.keySet();       // dataManager의 메뉴 이름 가져오기
        menuName = new String[menuNameSet.size()]; // 메뉴 영어 이름 set을 Array로 변환
        menuNameSet.toArray(menuName);

        menu_num = menuNameSet.size();

        /** 메뉴가격 _ int[] Array */
        price = new int[menuName.length];
        for(int i = 0 ; i < menuName.length; i++){
            price[i] =  list_menu.get(menuName[i]).price;
        }

        /** LED _ JButton[] Array */
        pushLED = new JButton[menuName.length];
        for(int i = 0 ; i< menuName.length; i++){
            // 빨간 LED
            pushLED[i] = new JButton();
            pushLED[i].setFont(new Font("Arial", Font.PLAIN, 20));
            pushLED[i].setText("●");
            pushLED[i].setBackground(Color.BLACK);     // 투명 만들기 전에 배경 지정해줘야됨. 지우면 에러!
            pushLED[i].setOpaque(false);            //배경 투명
            pushLED[i].setForeground(Color.RED);
            pushLED[i].setSize(50, 15);
            pushLED[i].setLocation(locX[i], locY[i] + 60);
            pushLED[i].setEnabled(false);
            panel.add(pushLED[i]);
            pushLED[i].addActionListener( new pushBtnListener());
        }


        /** 종이컵 위 메뉴 이름, 가격 표시 */
        for (int i = 0 ; i < menuName.length; i++){
            new setMenu(locX[i],locY[i],EngToKor.get(menuName[i]) , price[i]);
        }

        /** 상태 */
        status = new JLabel();
        status.setFont(new Font("고딕체", Font.BOLD, 14));
        status.setHorizontalAlignment(SwingConstants.CENTER);
        status.setText("판 매 중");
        status.setBackground(Color.BLUE);
        status.setOpaque(false);
        status.setForeground(Color.RED);
        status.setSize(100, 15);
        status.setLocation(463, 420);
        panel.add(status);


        /** 투입 금액 */
        money = new JLabel();
        money.setFont(new Font("고딕체", Font.BOLD, 14));
        money.setHorizontalAlignment(SwingConstants.RIGHT);
        money.setText("- - - - - - -");
        money.setBackground(Color.BLUE);
        money.setOpaque(false);
        money.setForeground(Color.RED);
        money.setSize(100, 15);
        money.setLocation(442, 438);
        panel.add(money);

        /** 동전 button */
        // wrong button ( 달러)
        btnWrong = new JButton();
        btnWrong.setBackground(Color.BLACK);
        btnWrong.setOpaque(false);
        btnWrong.setSize(130, 50);
        btnWrong.setLocation(630, 320);
        panel.add(btnWrong);
        btnWrong.addActionListener(new InputMoneyListener());

        // 천원 ( 1000 won)
        btn1000 = new JButton();
        btn1000.setBackground(Color.BLACK);
        btn1000.setOpaque(false);
        btn1000.setSize(130, 70);
        btn1000.setLocation(630, 383);
        panel.add(btn1000);
        btn1000.addActionListener(new InputMoneyListener());

        // 오백원 ( 500 won)
        btn500 = new JButton();
        btn500.setBackground(Color.BLACK);
        btn500.setOpaque(false);
        btn500.setSize(90, 90);
        btn500.setLocation(650, 458);
        panel.add(btn500);
        btn500.addActionListener(new InputMoneyListener());

        // 백원 ( 100 won)
        btn100 = new JButton();
        btn100.setBackground(Color.BLACK);
        btn100.setOpaque(false);
        btn100.setSize(85, 85);
        btn100.setLocation(652, 553);
        panel.add(btn100);
        btn100.addActionListener(new InputMoneyListener());


        // 오십원 ( 50 won)
        btn50 = new JButton();
        btn50.setBackground(Color.BLACK);
        btn50.setOpaque(false);
        btn50.setSize(80, 80);
        btn50.setLocation(654, 648);
        panel.add(btn50);
        btn50.addActionListener(new InputMoneyListener());
        /** ====== 동전 button end  ======== */


        setAlwaysOnTop(true);
        setLocation(1000, 0);
        setSize(810, 1080);
        setVisible(true);

    }


    class pushBtnListener implements  ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0 ; i < menu_num; i++){
                if ((JButton)e.getSource() == pushLED[i]) {
                    System.out.println(menuName[i] + " : " + price[i]);
                }
            }
        }
    }


    class InputMoneyListener implements ActionListener{

        /** Money Manager 변수 ++ 변경해야됨 !!! */
        int tempCash=0;

        @Override
        public void actionPerformed(ActionEvent e) {
            if ((JButton)e.getSource() == btn1000){
                System.out.println("Log :: Wrong Money ");
            }

            if ((JButton)e.getSource() == btn1000){
                tempCash = 1000;
                cash += 1000;
                System.out.println("Log :: 1000 won ");
            }

            if ((JButton)e.getSource() == btn500){
                tempCash = 500;
                cash += 500;
                System.out.println("Log :: 500 won ");
            }

            if ((JButton)e.getSource() == btn100){
                tempCash = 100;
                cash += 100;
                System.out.println("Log :: 100 won ");
            }

            if ((JButton)e.getSource() == btn50){
                tempCash = 50;
                cash += 50;
                System.out.println("Log :: 50 won ");
            }

            MoneyManager.calcInputCash(tempCash);

            if (cash != 0){
                money.setText(String.valueOf(cash) + "원");
            }
        }
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
