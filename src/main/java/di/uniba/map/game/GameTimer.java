package di.uniba.map.game;

public class GameTimer extends Thread{

    private int seconds;
    private boolean running;

    public GameTimer() {
        seconds = 0;
        running = false;
    }

    public void startTimer() {
        running = true;
        this.start();
    }

    public void resetTimer(){
        this.seconds = 0;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void stopTimer() {
        running = false;
    }

    public int getTime() {
        return seconds;
    }

    @Override
    public void run(){
        while (true) {
            if(running){
                seconds++;
            }
            try{
                Thread.sleep(1000);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
}
