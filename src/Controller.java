import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Controller {

    static int change;
    static int price;
    static String selection;

    static int needed_waterAmount;
    static int needed_sugar;
    static int needed_creamer;
    static int needed_coffeeBeans;
    static int needed_yulmu;
    static int needed_cocoa;

    static boolean noChange;
    static boolean noCup;
    static boolean noIngredient;

    static HashMap<String, Boolean> bool_ledOn = new HashMap<>();
    static HashMap<String, AbsDataManager> list_menu = new HashMap<>();
    static Set<String> menu_keys;

    Controller() {
        list_menu = DataManager.list_menu;
        noChange = false;

        //LED state 초기화
        menu_keys = list_menu.keySet();
        Iterator<String> it = menu_keys.iterator();

        while(it.hasNext()){
            String key = it.next();
            bool_ledOn.put(key,false);
        }
    }

    static void Run(){
        if(noCup)
        {
            MoneyManager.makeReturnCash();
            UserPanel.displayPrompt("No Cup");
            return;
        }
        needed_waterAmount = DataManager.checkNeededWaterAmount();

        if(!WaterManager.checkNeededWaterAmount(needed_waterAmount))
        {
            MoneyManager.makeReturnCash();
            UserPanel.displayPrompt("No Water");
            return;
        }
        DataManager.checkNeededIngredient(selection);


        if(!IngredientManager.checkIngredientAvailable(selection)){
            MoneyManager.makeReturnCash();
            UserPanel.displayPrompt("No Ingredient");
            return;
        }
        CupManager.dropCup();
        WaterManager.dropWater(needed_waterAmount);
        IngredientManager.dropIngredient(selection);


    }

    static void checkLEDon(int cash){
        Iterator<String> it = menu_keys.iterator();

        while(it.hasNext()){
           String key = it.next();
           int tmp_price = list_menu.get(key).price;

           if(cash >= tmp_price) {
               bool_ledOn.replace(key,true);
           }
        }
        UserPanel.menuLEDon();
        return;
    }

     static void getCustomerInput(int cash, String sel){
        selection = sel;
        price = DataManager.checkSelectiedItemPrice(selection);
        checkForChange(cash, price);
        return;
    }

     static void checkForChange(int cash, int price){
        change = cash - price;

        if (cash > price)
            noChange= MoneyManager.checkChangeAvailable(change);

        if (noChange)
        {
            MoneyManager.makeReturnCash();
            UserPanel.displayPrompt("No Change");
        }
        else {
            noCup = !CupManager.checkCupExists();
        }
        Run();
        return;
    }

    static void getCompleteMessage(){
        System.out.println("음료 제조가 모두 완료되었습니다.");
        update();
    }

    static void checkOtherMenuAvailable(int cash){
        if(cash >= DataManager.minPrice)
            checkLEDon(cash);
        else
            MoneyManager.makeReturnCash();

    }

    static void update(){
        MoneyManager.InputTotalCash -= price;
        checkOtherMenuAvailable(MoneyManager.InputTotalCash);
    }

}

