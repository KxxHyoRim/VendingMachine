import java.util.*;

class DataManager {

    int minPrice;
    HashMap<String, AbsDataManager> list_menu = new HashMap<>();

    DataManager() {

        list_menu.put("sugarCoffee", new AbsDataManager(100, 200, 30));
        list_menu.put("milkCoffee", new AbsDataManager(200, 200, 30));
        list_menu.put("blackCoffee", new AbsDataManager(50, 200, 30));

        setMinPrice();
    }

    public int setMinPrice() {

        Set<String> keys = list_menu.keySet();
        Iterator<String> it = keys.iterator();

        int minPrice = Integer.MAX_VALUE;

        while(it.hasNext()){
            String key = it.next();
            int tmp_price = list_menu.get(key).price;
            if(tmp_price < minPrice) {
                minPrice = tmp_price;
            }
        }

        this.minPrice = minPrice;

        return this.minPrice;


    }




}
