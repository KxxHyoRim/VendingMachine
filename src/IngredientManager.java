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
            decreaseIngredient("sugar", item.needed_sugar);
            decreaseIngredient("creamer", item.needed_creamer);
            decreaseIngredient("yulmu", item.needed_yulmu);
            decreaseIngredient("cocoa", item.needed_cocoa);
            decreaseIngredient("coffeeBeans", item.needed_coffeeBeans);
            return true;
        }
        else
            return false;
    }

    static void increaseIngredient(String ingredient, int additionalAmount){
        switch (ingredient) {
            case "sugar":
                if (curSugarAmount + additionalAmount <= maxSugarAmount) {
                    System.out.println("현재 남아있는 " + ingredient + "의 양은 " + curSugarAmount);
                    curSugarAmount += additionalAmount;
                    System.out.println("추가된 " + ingredient + "의 양은 " + curSugarAmount);
                } else {
                    System.out.println("최대로 저장할 수 있는 양을 초과했습니다.");
                }
                break;
            case "creamer":
                if (curCreamerAmount + additionalAmount <= maxCreamerAmount) {
                    System.out.println("현재 남아있는 " + ingredient + "의 양은 " + curCreamerAmount);
                    curCreamerAmount += additionalAmount;
                    System.out.println("추가된 " + ingredient + "의 양은 " + curCreamerAmount);
                } else {
                    System.out.println("최대로 저장할 수 있는 양을 초과했습니다.");
                }
            case "coffeeBeans":
                if (curCoffeeBeansAmount + additionalAmount <= maxCoffeeBeansAmount) {
                    System.out.println("현재 남아있는 " + ingredient + "의 양은 " + curCoffeeBeansAmount);
                    curCoffeeBeansAmount += additionalAmount;
                    System.out.println("추가된 " + ingredient + "의 양은 " + curCoffeeBeansAmount);
                } else {
                    System.out.println("최대로 저장할 수 있는 양을 초과했습니다.");
                }
            case "yulmu":
                if (curYulmuAmount + additionalAmount <= maxYulmuAmount) {
                    System.out.println("현재 남아있는 " + ingredient + "의 양은 " + curYulmuAmount);
                    curYulmuAmount += additionalAmount;
                    System.out.println("추가된 " + ingredient + "의 양은 " + curYulmuAmount);
                } else {
                    System.out.println("최대로 저장할 수 있는 양을 초과했습니다.");
                }
            case "cocoa":
                if (curCocoaAmount + additionalAmount <= maxCocoaAmount) {
                    System.out.println("현재 남아있는 " + ingredient + "의 양은 " + curCocoaAmount);
                    curCocoaAmount += additionalAmount;
                    System.out.println("추가된 " + ingredient + "의 양은 " + curCocoaAmount);
                } else {
                    System.out.println("최대로 저장할 수 있는 양을 초과했습니다.");
                }
        }
    }

    static void decreaseIngredient(String ingredient, int subtractedAmount){
        switch (ingredient){
            case "sugar":
                curSugarAmount -= subtractedAmount;
                System.out.println("현재 남아있는 " + ingredient + "의 양은 " + curSugarAmount);
                break;
            case "creamer":
                curCreamerAmount -= subtractedAmount;
                System.out.println("현재 남아있는 " + ingredient + "의 양은 " + curCreamerAmount);
                break;
            case "coffeeBeans":
                curCoffeeBeansAmount -= subtractedAmount;
                System.out.println("현재 남아있는 " + ingredient + "의 양은 " + curCoffeeBeansAmount);
                break;
            case "yulmu":
                curYulmuAmount -= subtractedAmount;
                System.out.println("현재 남아있는 " + ingredient + "의 양은 " + curYulmuAmount);
                break;
            case "cocoa":
                curCocoaAmount -= subtractedAmount;
                System.out.println("현재 남아있는 " + ingredient + "의 양은 " + curCocoaAmount);
                break;

        }
    }
}
