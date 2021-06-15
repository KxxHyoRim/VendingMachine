import java.util.*;

public class DataManager {

    static int minPrice;
    final static int needed_water = 50;
    static HashMap<String, AbsDataManager> list_menu;
    static HashMap<String, String > EngToKor;

    DataManager() {
        list_menu = new HashMap<>();
        //각 상품들 객체 생성해서 Hash-map에 저장
        /*-----------일반커피---------------*/
        list_menu.put("SugarCoffee", new AbsDataManager(600,10,0, 5,0,0));
        list_menu.put("MilkCoffee", new AbsDataManager(600, 10,5,10,0,0));
        list_menu.put("BlackCoffee", new AbsDataManager(500, 0,0,0,0,0));
        /*-----------고급커피---------------*/
        list_menu.put("Cappuccino", new AbsDataManager(700, 0,5,10,0,0));
        list_menu.put("Cafelatte", new AbsDataManager(700, 0, 5,8,0,0));
        list_menu.put("Cafemocha", new AbsDataManager(700, 0,5,8,0,3));
        /*-----------우리차---------------*/
        list_menu.put("Cocoa", new AbsDataManager(600,3,0,0,0,5));
        list_menu.put("Yulmu", new AbsDataManager(700,3,0,0,5,0));
        list_menu.put("Milk", new AbsDataManager(500, 3,0,15,0,0));


        EngToKor = new HashMap<>();
        EngToKor.put("MilkCoffee", "밀크커피");
        EngToKor.put("BlackCoffee", "블랙커피");
        EngToKor.put("Yulmu", "율무차");
        EngToKor.put("Cafemocha", "카페모카");
        EngToKor.put("SugarCoffee", "설탕커피");
        EngToKor.put("Cafelatte", "카페라떼");
        EngToKor.put("Cocoa", "코코아");
        EngToKor.put("Cappuccino", "카푸치노");
        EngToKor.put("Milk", "우유");

        Set<String> menu_keys = list_menu.keySet();
        Iterator<String> it = menu_keys.iterator();

        minPrice = Integer.MAX_VALUE;
        while(it.hasNext()){
            String key = it.next();
            int tmp_price = list_menu.get(key).price;

            if(minPrice > tmp_price) {
                minPrice = tmp_price;
            }
        }
    }

    //이건 그냥 바뀌는거 확인하려구용
    static void changeMenu(String key, AbsDataManager value){
        list_menu.replace(key,value);
    }

    static int checkSelectedItemPrice(String menu){
        return list_menu.get(menu).price;
    }

    static int checkNeededWaterAmount(){
        return needed_water;
    }

    static void checkNeededIngredient(String menu){
        AbsDataManager item = list_menu.get(menu);

        Controller.needed_sugar = item.needed_sugar;
        Controller.needed_creamer = item.needed_creamer;
        Controller.needed_cocoa = item.needed_cocoa;
        Controller.needed_yulmu = item.needed_yulmu;
        Controller.needed_coffeeBeans = item.needed_coffeeBeans;
        return;
    }

}
