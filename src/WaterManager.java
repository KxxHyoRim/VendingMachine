public class WaterManager {

    static int curWaterAmount = 50;
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
        Manufacture.getWater();
    }

    static void decreaseWaterAmount(int needed_waterAmount){
        System.out.println("현재 물의 양은 " + curWaterAmount + " 입니다.");
        curWaterAmount -= needed_waterAmount;
        System.out.println("감소된 후, 물의 양은 " + curWaterAmount + " 입니다.");
    }

    static void increaseWaterAmount(int additionalAmount){
        System.out.println("현재 물의 양은 " + curWaterAmount + " 입니다.");
        curWaterAmount += additionalAmount;
        System.out.println("증가된 후, 물의 양은 " + curWaterAmount + " 입니다.");
    }
}
