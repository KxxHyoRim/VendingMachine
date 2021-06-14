import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Controller {

    int cash;
    int menuNum;
    int change;
    int price;
    boolean Anavailable;
    DataManager dm;
    HashMap<String, Boolean> bool_ledOn = new HashMap<>();
    HashMap<String, AbsDataManager> list_menu = new HashMap<>();
    Set<String> menu_keys;

    Controller() {
        dm = new DataManager();
        list_menu =dm.list_menu;
        Anavailable = false;

        //LED state 초기화
        menu_keys = list_menu.keySet();
        Iterator<String> it = menu_keys.iterator();

        while(it.hasNext()){
            String key = it.next();
            bool_ledOn.put(key,false);
        }
    }

    public HashMap<String, Boolean> checkLEDon(int cash){
        Iterator<String> it = menu_keys.iterator();

        while(it.hasNext()){
           String key = it.next();
           int tmp_price = list_menu.get(key).price;

           if(cash >= tmp_price) {
               bool_ledOn.replace(key,true);
           }
        }
        return bool_ledOn;
    }

    public void getCustomerInput(int cash, String selection){
        this.cash = cash;
        price = dm.checkSelectiedItemPrice(selection);
        checkForChange(cash, price);
    }

    public void checkForChange(int cash, int price){
        change = cash - price;

        if(cash > price)
            Anavailable = MoneyManager.checkChangeAvailable(change);

        if(Anavailable)
            MoneyManager.makeReturnCash(cash);
    }
}

