public class CupManager {

    static int curCupNumber = 1;
    static int maxCupNumber = 5;

    CupManager(){

    }

    static boolean checkCupExists() {
        if ( curCupNumber > 0 )
        {
            decreaseCupNumber();
            return true;
        }
        else
            return false;
    }

    static void decreaseCupNumber(){
        curCupNumber--;
    }

    static void increaseCupNumber(int addedNumber){
        curCupNumber+=addedNumber;
    }

    static void dropCup(){
        decreaseCupNumber();
        //UserPanel.receiveCup();
        return;
    }
}
