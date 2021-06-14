import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class MoneyManager {
    int cash;
    int coin50 = 200;
    int coin100 = 200;
    int coin500 = 200;
    int coin1000 = 200;
    Controller controller;
    HashMap<String, Boolean> bool_ledOn = new HashMap<>();

    MoneyManager(){
        controller = new Controller();
        cash =600; //일단 test

        calcInputCash(cash);
    }

    void calcInputCash(int cash){
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

//    boolean checkChangeAvailable() {
//
//    }
}
