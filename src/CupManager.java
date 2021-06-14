public class CupManager {

    static int curCupNumber = 1;
    static int maxCupNumber = 5;

    CupManager(){

    }

    static boolean checkCupExists() {
        if (curCupNumber>0)
            return true;
        else
            return false;
    }

    static void decreaseCupNumber(){
        curCupNumber--;
    }

    static void increaseCupNumber(){
        curCupNumber++;
    }
}
