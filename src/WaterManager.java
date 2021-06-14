public class WaterManager {

    static int curWaterAmount = 200;
    static int maxWaterAmount = 400;

    WaterManager(){
    }

    static boolean checkNeededWaterAmount(int needed_waterAmount) {
        if ( needed_waterAmount <= curWaterAmount )
        {
            return true;
        }
        else
            return false;
    }

    static void dropWater(int needed_waterAmount){
        decreaseWaterAmount(needed_waterAmount);
        Menufacture.getWater();
    }

    static void decreaseWaterAmount(int needed_waterAmount){
        curWaterAmount -= needed_waterAmount;
    }

    static void increaseWaterAmount(int additionalAmount){
        curWaterAmount += additionalAmount;
    }
}
