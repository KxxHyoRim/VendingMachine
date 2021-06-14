public class IngredientManager {

    static int curSugarAmount = 10;
    static int curCreamerAmount = 10;
    static int curCoffeeBeansAmount = 10;
    static int curYulmuAmount = 10;
    static int curCocoaAmount = 10;

    static int maxSugarAmount = 30;
    static int maxCreamerAmount = 30;
    static int maxCoffeeBeansAmount = 30;
    static int maxYulmuAmount = 30;
    static int maxCocoaAmount = 30;

    IngredientManager() {
    }

    static boolean checkIngredientAvailable(String menu){
        AbsDataManager item = DataManager.list_menu.get(menu);

        if(item.needed_cocoa <= curCocoaAmount && item.needed_yulmu <= curYulmuAmount && item.needed_creamer <= curCreamerAmount &&
                item.needed_coffeeBeans <= curCoffeeBeansAmount && item.needed_sugar <= curSugarAmount)
        {
            return true;
        }
        else
            return false;
    }

    static void increaseIngredient(){

    }
}
