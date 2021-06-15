public class CupManager {

    static int curCupNumber = 1;
    static int maxCupNumber = 5;

    CupManager(){

    }

    static boolean checkCupExists() {
        if ( curCupNumber > 0 )
        {
            return true;
        }
        else
            return false;
    }

    static void decreaseCupNumber(){
        System.out.println("현재 컵의 갯수는 " + curCupNumber + " 입니다.");
        curCupNumber--;
        System.out.println("감소된 후, 컵의 갯수는 " + curCupNumber + " 입니다.");
    }

    static void increaseCupNumber(int addedNumber){
        System.out.println("현재 컵의 갯수는 " + curCupNumber + " 입니다.");
        curCupNumber+=addedNumber;
        System.out.println("추가된 후, 컵의 갯수는 " + curCupNumber + " 입니다.");
    }

    static void dropCup(){
        decreaseCupNumber();
        //UserPanel.receiveCup();
        return;
    }
}
