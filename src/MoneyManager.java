import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class MoneyManager {
    static int InputTotalCash = 0;
    /** 수정함 나중에 10으로 바꾸기 */
    static int coin50 = 6;
    static int coin100 = 0;
    static int coin500 = 1;
    static int coin1000 = 10;

    static int input_50won=0;
    static int input_100won=0;
    static int input_500won=0;
    static int input_1000won=0;

    static int change_50won = 0;
    static int change_100won = 0;
    static int change_500won = 0;
    static int change_1000won = 0;
    static int input_wrong=0;


    static HashMap<String, Boolean> bool_ledOn = new HashMap<>();

    MoneyManager(){
    }

    static void calcInputCash(int cash) {


        if(!(cash==50 || cash==100 || cash==500 || cash==1000)){
            input_wrong++;
            UserPanel.receive(-1);
            input_wrong = 0;
        }

        //투입된 돈 갯수 count
        switch (cash) {
            case 50:
                coin50++;
                input_50won++;
                break;
            case 100:
                coin100++;
                input_100won++;
                break;
            case 500:
                coin500++;
                input_500won++;
                break;
            case 1000:
                coin1000++;
                input_1000won++;
                break;
        }
        InputTotalCash += cash;


        Boolean isException = Controller.checkException();
        if (!isException){
            Controller.checkLEDon(InputTotalCash);
            bool_ledOn = Controller.bool_ledOn;
        }

    }

    static void calcChange(int cash){

        change_1000won = cash/1000;
        cash = cash%1000;
        change_500won  = cash/500;
        cash = cash%500;
        change_100won = cash/100;
        cash = cash%100;
        change_50won = cash/50;

        if (change_1000won > coin1000) {
            change_1000won -= coin1000;
            change_500won += change_1000won * 1000 / 500;
            change_1000won = coin1000;
        }
        if (change_500won > coin500) {
            change_500won -= coin500;
            change_100won += change_500won * 500 / 100;
            change_500won = coin500;
        }
        if (change_100won > coin100) {
            change_100won -= coin100;
            change_50won += change_100won * 100 / 50;
            change_100won = coin100;
        }

        System.out.println("1000원 지폐: " + change_1000won);
        System.out.println("500원 동전: " + change_500won);
        System.out.println("100원 동전: " + change_100won);
        System.out.println("50원 동전: " + change_50won);

    }

    static boolean checkChangeAvailable(int change) {

        change_1000won = change/1000;
        change = change%1000;
        change_500won  = change/500;
        change = change%500;
        change_100won = change/100;
        change = change%100;
        change_50won = change/50;

        if (change_1000won > coin1000) {
            change_1000won -= coin1000;
            change_500won += change_1000won * 1000 / 500;
            change_1000won = coin1000;
        }
        if (change_500won > coin500) {
            change_500won -= coin500;
            change_100won += change_500won * 500 / 100;
            change_500won = coin500;
        }
        if (change_100won > coin100) {
            change_100won -= coin100;
            change_50won += change_100won * 100 / 50;
            change_100won = coin100;
        }

        System.out.println("1000원 지폐: " + change_1000won);
        System.out.println("500원 동전: " + change_500won);
        System.out.println("100원 동전: " + change_100won);
        System.out.println("50원 동전: " + change_50won);

        if(change_50won<=coin50 && change_100won<=coin100 && change_500won<=coin500 && change_1000won<=coin1000)
            return false;
        else
            return true;
    }

    static void makeReturnCash(int cash){
        InputTotalCash = 0;

        coin1000 -= change_1000won;
        coin500 -= change_500won;
        coin100 -= change_100won;
        coin50 -= change_50won;

        UserPanel.receive();
        change_1000won=0;
        change_500won=0;
        change_100won=0;
        change_50won=0;

        input_1000won=0;
        input_500won=0;
        input_100won=0;
        input_50won=0;
    }
}
