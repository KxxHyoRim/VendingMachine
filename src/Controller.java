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
    static boolean noWater;
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

        if(noCup) {
            MoneyManager.calcChange(MoneyManager.InputTotalCash);
            MoneyManager.makeReturnCash(MoneyManager.InputTotalCash );
            UserPanel.exception(1);
            return;
        }

        needed_waterAmount = DataManager.checkNeededWaterAmount();
        noWater = !WaterManager.checkNeededWaterAmount(needed_waterAmount);
        if(noWater) {
            MoneyManager.calcChange(MoneyManager.InputTotalCash);
            MoneyManager.makeReturnCash(MoneyManager.InputTotalCash );
            UserPanel.exception(0);
            return;
        }


        DataManager.checkNeededIngredient(selection);
        noIngredient = !IngredientManager.checkIngredientAvailable(selection);
        if(noIngredient){
            MoneyManager.calcChange(MoneyManager.InputTotalCash);
            MoneyManager.makeReturnCash(MoneyManager.InputTotalCash );
            UserPanel.exception(2);
            return;
        }

        /** No Exception */
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
           } else{
               bool_ledOn.replace(key,false);

           }
        }
        UserPanel.menuLEDon();
    }

     static void getCustomerInput(int cash, String sel){
        selection = sel;
        price = DataManager.checkSelectedItemPrice(selection);
        checkForChange(cash, price);
     }


     static void checkForChange(int cash, int price){
        change = cash - price;

        if (cash >= price)
            noChange= MoneyManager.checkChangeAvailable(change);

        if (noChange) {
            MoneyManager.calcChange(cash);
            MoneyManager.makeReturnCash(MoneyManager.InputTotalCash );
            checkLEDon(cash);
            UserPanel.menuLEDon();
            UserPanel.displayPrompt("No Change");
            UserPanel.money.setText("- - - - - - -");
            return;
        }
        else {
            UserPanel.money.setText(change + "원");
            noCup = !CupManager.checkCupExists();
        }
        Run();
        return;
    }

    static Boolean checkException(){
        if (noCup || noWater){
            MoneyManager.calcChange(MoneyManager.InputTotalCash );
            MoneyManager.makeReturnCash(MoneyManager.InputTotalCash );
            return true;
        }
        return false;
    }

    static void getCompleteMessage(){
        System.out.println("음료 제조가 모두 완료되었습니다.");
        update();
    }

    static void checkOtherMenuAvailable(int cash){
        if(cash >= DataManager.minPrice)
            checkLEDon(cash);
        else{

//            if (noCup){
//                UserPanel.displayPrompt("No Cup");
//            }

            MoneyManager.makeReturnCash(MoneyManager.InputTotalCash );
            checkLEDon(cash);
            UserPanel.menuLEDon();
            UserPanel.money.setText("- - - - - - -");
//            UserPanel.removeCup();

        }

    }

    static void update(){
        MoneyManager.InputTotalCash -= price;
        checkOtherMenuAvailable(MoneyManager.InputTotalCash);

    }

}

