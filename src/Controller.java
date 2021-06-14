import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Controller {

    int cash;
    int menuNum;
    int change;
    int price;
    DataManager dm;
    moneyManager mm;
    HashMap<String, Boolean> bool_ledOn = new HashMap<>();
    //HashMap<String, AbsDataManager> list_menu= new HashMap<>();
    Set<String> menu_keys;

    Controller() {
        dm = new DataManager();
        //copyList_menu();

        //LED state 초기화
        menu_keys = dm.list_menu.keySet();
        Iterator<String> it = menu_keys.iterator();

        while(it.hasNext()){
            String key = it.next();
            bool_ledOn.put(key,false);
        }

    }

//    void copyList_menu() {
//        //DataManager 클래스 HashMap 가져오기
//        this.list_menu.putAll(dm.getList_menu());
//        menuNum = list_menu.size();
//    }

    public HashMap<String, Boolean> checkLEDon(int cash){
        Iterator<String> it = menu_keys.iterator();

        while(it.hasNext()){
           String key = it.next();
           int tmp_price = dm.list_menu.get(key).price;

           if(cash >= tmp_price) {
               bool_ledOn.replace(key,true);
           }
        }
        return bool_ledOn;
    }

//    public HashMap<String, AbsDataManager> getList_menu() {
//        return list_menu;
//    }

    public void getCustomerInput(int cash, String selection){
        price = dm.checkSelectiedItemPrice(selection);
        checkForChange(cash, price);
    }

    public void checkForChange(int cash, int price){
        change = cash - price;
    }
}

