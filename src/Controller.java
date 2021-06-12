public class Controller {

    int minMenuPrice;

    Controller() {
        minMenuPrice =  new DataManager().setMinPrice();
        System.out.println("minMenu :: " + minMenuPrice);

    }



}

