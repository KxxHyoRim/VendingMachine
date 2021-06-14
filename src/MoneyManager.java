import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class MoneyManager {
    static int InputTotalCash = 0;
    static int coin50 = 10;
    static int coin100 = 10;
    static int coin500 = 10;
    static int coin1000 = 10;
    static int input_50won=0;
    static int input_100won=0;
    static int input_500won=0;
    static int input_1000won=0;
    Controller controller = new Controller();
    HashMap<String, Boolean> bool_ledOn = new HashMap<>();

    MoneyManager(){

    }

    void calcInputCash (int cash) {

        if(!(cash==50 || cash==100 || cash==500 || cash==1000))
            //UserPanel.receive(cash);

        //투입된 돈 갯수 count
        switch (cash){
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

        //원래는 userPanel에서 해야되는데 일단 여기서 test
        bool_ledOn = controller.checkLEDon(cash);

        //출력해보기
        Set<String> keys = bool_ledOn.keySet();
        Iterator<String> it = keys.iterator();
        System.out.println("\n------------켜져야하는 led list------------");

        while(it.hasNext()) {
            String key = it.next();
            if (bool_ledOn.get(key))
                System.out.println("key :: " + key + "--> price :: " + controller.dm.list_menu.get(key).price);
        }
    }

    static boolean checkChangeAvailable(int change) {
        int tmp_1000 = change/1000;
        change = change%1000;
        int tmp_500 = change/500;
        change = change%500;
        int tmp_100 = change/100;
        change = change%100;
        int tmp_50 = change/50;

        if(tmp_50<=coin50 && tmp_100<=coin100 && tmp_500<=coin500 && tmp_1000<=coin1000)
            return false;
        else
            return true;
    }

    static void makeReturnCash(int cash){
        InputTotalCash = 0;
        coin1000 -= input_50won;
        coin500 -= input_500won;
        coin100 -= input_100won;
        coin50 -= input_50won;
        //UserPanel.receive(cash);
    }


}
