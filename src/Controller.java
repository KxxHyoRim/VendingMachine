public class Controller {

    int cash;
    boolean bool_ledOn[];
    DataManager dm;

    Controller() {

        dm = new DataManager();

        //LED state 초기화
        bool_ledOn = new boolean[9];
        for(int i=0; i<9; i++){
            bool_ledOn[i] = false;
        }
    }

    void checkLEDon(int cash){

    }
}

