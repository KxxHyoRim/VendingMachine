import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class UserPanel extends JFrame {

    static int exception = -1;
    static int menu_num;

    static int receive_50won;
    static int receive_100won;
    static int receive_500won;
    static int receive_1000won;
    static int receive_wrong;

    static private  MyPanel panel = new MyPanel();
    static private DataManager dm = new DataManager();
    static HashMap<String, AbsDataManager> list_menu = dm.list_menu;
    static HashMap<String, String> EngToKor = dm.EngToKor;


    static String[] menuName;
    static JButton[] pushLED;
    int[] price;

    int[] locX = {152, 215, 280,152, 215, 280,152,215, 280,};
    int[] locY = {205, 205, 205, 354,354,354,503, 503, 503 };

    int cash = 0;

    static JLabel status;
    static JLabel money;
    static JButton btnWrong;
    static JButton btn1000;
    static JButton btn500;
    static JButton btn100;
    static JButton btn50;
    static JButton reset;
    static JLabel makingCup;
    static JLabel smoke;



    public UserPanel(){
        /** GUI basic setting */
        setTitle("Vending Machine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(null);
        panel.setLayout(null);
        setContentPane(panel);
        new MoneyManager();
        new Controller();

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


        /** reset 버튼 */
//        reset = new JButton();
//        reset.setBorder(BorderFactory.createEmptyBorder());
//        reset.setBackground(Color.BLACK);
//        reset.setOpaque(false);
//        reset.setSize(30, 30);
//        reset.setLocation(470, 520);
//        panel.add(reset);
//        reset.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                cash = 0;
//                MoneyManager.InputTotalCash = 0;
//                MoneyManager.calcInputCash(0);
////                for (int i = 0 ; i< menu_num; i++){
////                    pushLED[i].setEnabled(false);
////                }
//                money.setText("- - - - - - -");
//
//            }
//        });



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
                    Controller.getCustomerInput(MoneyManager.InputTotalCash,menuName[i]);
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

            if (exception == 1|| exception == 0)
                receive(exception);

            if ((JButton)e.getSource() == btnWrong){
                tempCash = -1;
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
                money.setText(String.valueOf(MoneyManager.InputTotalCash) + "원");
            }
            if (cash == -1 ){
                money.setText("- - - - - - -");
            }
        }
    }

    static void menuLEDon(){
        HashMap<String, Boolean>  bool_ledOn =  Controller.bool_ledOn;
        for (int i = 0 ; i< menu_num; i++){
            Boolean check = bool_ledOn.get(menuName[i]);
            if (check == true)
                pushLED[i].setEnabled(true);
            else
                pushLED[i].setEnabled(false);
        }
    }

    static void exception(int e){
        if (e == 0) {
            exception = 0;
            displayPrompt("No Water");
        }
        if (e == 1) {
            exception = 1;
            displayPrompt("No Cup");
        }
        if (e == 2) {
            exception = 2;
            displayPrompt("No Ingredient");
        }
        money.setText("- - - - - - -");
        Controller.checkLEDon(0);


    }

    static void receive(int wrong){

        if (wrong == -1){
            new FlickeringLabel(1, 650, 270);

            /**정상코드*/
            displayPrompt("반환중");
            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    displayPrompt("판매중");
                }
            };
            Timer timer = new Timer(500, listener);
            timer.setRepeats(false);
            timer.start();
        }

        if (wrong == 0 || wrong == 1) {
            receive_50won = MoneyManager.input_50won;
            receive_100won = MoneyManager.input_100won;
            receive_500won = MoneyManager.input_500won;
            receive_1000won = MoneyManager.input_1000won;

            System.out.println("\nIn receive");
            System.out.println("1000원 지폐: " + receive_1000won);
            System.out.println("500원 동전: " + receive_500won);
            System.out.println("100원 동전: " + receive_100won);
            System.out.println("50원 동전: " + receive_50won);

            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new FlickeringLabel(receive_50won, 650, 590);
                    new FlickeringLabel(receive_100won, 650, 510);
                    new FlickeringLabel(receive_500won, 650, 430);
                    new FlickeringLabel(receive_1000won, 650, 350);


                    int sum = receive_50won + receive_100won + receive_500won + receive_1000won;


                    if (sum != 0)
                        displayPrompt("반환중");
                    ActionListener listener = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (exception == 2){
                                displayPrompt("판매중");
                            }

                            if (Controller.noCup){
                                displayPrompt("No Cup");
                            }

                            if (Controller.noWater){
                                displayPrompt("No Water");
                            }
                        }
                    };
                    Timer timer = new Timer(sum*1000, listener);
                    timer.setRepeats(false);
                    timer.start();

                }
            };
            Timer timer = new Timer(2500, listener);
            timer.setRepeats(false);
            timer.start();        }


    }


    static class FlickeringLabel extends JLabel implements Runnable{
        int count;
        int blink;
        JLabel check = new JLabel(new ImageIcon("check_small.png"));

        public FlickeringLabel(int blink, int locX, int locY){
            this.blink = blink;
            check.setLocation(locX, locY);
            check.setSize(100, 100);
            panel.add(check);
            check.setVisible(false);

            Thread th = new Thread(this);
            th.start();
        }

        @Override
        public void run() {
            int n = 0;
            while(true){
                if (n == 0){
                    if(blink == count) return;
                    count++;
                    check.setVisible(true);
                    setBackground(Color.YELLOW);
                }
                else{
                    check.setVisible(false);
                    setBackground(Color.GREEN);
                }
                if (n == 0) n = 1;
                else n = 0;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    return;
                }

            }
        }
    }

    static void removeCup() {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserPanel.makingCup.setVisible(false);
                UserPanel.smoke.setVisible(false);
            }
        };
        Timer timer = new Timer(3000, listener);
        timer.setRepeats(false);
        timer.start();

    }


    static void receive(){

        receive_50won = MoneyManager.change_50won;
        receive_100won = MoneyManager.change_100won;
        receive_500won = MoneyManager.change_500won;
        receive_1000won = MoneyManager.change_1000won;

        System.out.println("\nIn receive");
        System.out.println("1000원 지폐: " + receive_1000won);
        System.out.println("500원 동전: " + receive_500won);
        System.out.println("100원 동전: " + receive_100won);
        System.out.println("50원 동전: " + receive_50won);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FlickeringLabel(receive_50won, 650, 590);
                new FlickeringLabel(receive_100won, 650, 510);
                new FlickeringLabel(receive_500won, 650, 430);
                new FlickeringLabel(receive_1000won, 650, 350);


                int sum = receive_50won + receive_100won + receive_500won + receive_1000won;


                if (sum != 0 && exception ==-1)
                    displayPrompt("반환중");
                ActionListener listener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (Controller.noCup){
                            displayPrompt("No Cup");
                        }

                        if (Controller.noWater){
                            displayPrompt("No Water");
                        }
                        else{
                            displayPrompt("판매중");
                        }

                    }
                };
                Timer timer = new Timer(sum*1000, listener);
                timer.setRepeats(false);
                timer.start();

            }
        };
        Timer timer = new Timer(2500, listener);
        timer.setRepeats(false);
        timer.start();
    }


    static void displayPrompt(String s){
        String newS = "";

        // 영어면 그대로
        if (s.charAt(0) >= 'A' || s.charAt(0) <= 'Z')
            newS = s;
        // 한국어면 띄어쓰기
        else {
            for(int i = 0 ; i < s.length() ; i++){
                newS += s.charAt(i) + " ";    // 한글자 띄우기
            }
        }

        System.out.println(newS);
        status.setText(newS);
        panel.repaint();
    }

    static void receiveCup(){
        System.out.println("Receive cup");

        makingCup = new JLabel(new ImageIcon("cup.png"));
        makingCup.setVisible(true);
        makingCup.setBorder(BorderFactory.createEmptyBorder());
        makingCup.setBackground(Color.BLACK);
        makingCup.setOpaque(false);
        makingCup.setSize(100, 100);
        makingCup.setLocation(190, 750);
        panel.add(makingCup);
        panel.repaint();



    }

    static void receiveCoffee(){

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Receive coffee");

                smoke = new JLabel(new ImageIcon("smoke.png"));
                smoke.setVisible(true);
                smoke.setBorder(BorderFactory.createEmptyBorder());
                smoke.setBackground(Color.BLACK);
                smoke.setOpaque(false);
                smoke.setSize(90, 90);
                smoke.setLocation(190, 700);

                panel.add(smoke);
                panel.repaint();
                removeCup();
            }
        };
        Timer timer = new Timer(1500, listener);
        timer.setRepeats(false);
        timer.start();

        Controller.getCompleteMessage();
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

    static class MyPanel extends JPanel{
        private ImageIcon icon = new ImageIcon("vendingMachineBackground2.png");
        private Image img = icon.getImage();

        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
    }


}
