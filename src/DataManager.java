import java.util.*;

class DataManager {

    int minPrice;
    HashMap<String, AbsDataManager> list_menu = new HashMap<>();

    DataManager() {

        //각 상품들 객체 생성해서 Hash-map에 저장
        list_menu.put("sugarCoffee", new AbsDataManager(600,10,0, 5,0,0));
        list_menu.put("milkCoffee", new AbsDataManager(600, 10,5,10,0,0));
        list_menu.put("blackCoffee", new AbsDataManager(500, 0,0,0,0,0));
        list_menu.put("cappuccino", new AbsDataManager(700, 0,5,10,0,0));
        list_menu.put("cafelatte", new AbsDataManager(700, 0, 5,8,0,0));
        list_menu.put("cafemocha", new AbsDataManager(700, 0,5,8,0,3));
        list_menu.put("cocoa", new AbsDataManager(600,3,0,0,0,5));
        list_menu.put("yulmu", new AbsDataManager(700,3,0,0,5,0));
        list_menu.put("milk", new AbsDataManager(500, 3,0,15,0,0));

    }

//    public int setMinPrice() {
//        Set<String> keys = list_menu.keySet();
//        Iterator<String> it = keys.iterator();
//
//        int minPrice = Integer.MAX_VALUE;
//
//        while(it.hasNext()){
//            String key = it.next();
//            int tmp_price = list_menu.get(key).price;
//            if(tmp_price < minPrice) {
//                minPrice = tmp_price;
//            }
//        }
//
//        this.minPrice = minPrice;
//        return this.minPrice;
//    }




}
