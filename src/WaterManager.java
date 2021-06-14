public class WaterManager {

    static int curWaterAmount = 200;
    static int maxWaterAmount = 400;

    WaterManager(){
    }

    static boolean checkNeededWaterAmount(int needed_waterAmount) {
        if ( needed_waterAmount <= curWaterAmount )
        {
            decreaseWaterAmount();
            return true;
        }
        else
            return false;
    }

    static void decreaseWaterAmount(){
        curWaterAmount -= 50;
    }
}
