/**
 * Created by Владислав on 29.11.2015.
 */
public class TimeChecker implements Runnable{

    private long initialTime;

    public TimeChecker(long initialTime){
        this.initialTime = initialTime;
    }

    @Override
    public void run() {
        while (true){
            if((System.currentTimeMillis() - initialTime) > 3000){
                Runtime.getRuntime().exit(0);
            }
        }
    }
}
