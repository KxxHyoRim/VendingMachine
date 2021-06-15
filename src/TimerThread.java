public class TimerThread extends Thread{
    public void run() {
        int n=0;

        while(n<=4){
            System.out.println("스레드 실행! 잠자라" + n + "초 경과");
            try{
                sleep(1000);
                n++;
            }
            catch (InterruptedException e) {return;}
        }
    }
}
