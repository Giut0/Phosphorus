package di.uniba.map.game;

/**
 * The GameTimer class provides methods to manage a game timer.
 * It extends the Thread class, allowing it to run in a separate thread.
 */
public class GameTimer extends Thread {

    private int seconds;
    private boolean running;

    /**
     * Constructor for the GameTimer class.
     * Initializes the timer to 0 seconds and sets it to not running.
     */
    public GameTimer() {
        seconds = 0;
        running = false;
    }

    /**
     * Starts the timer.
     * Sets running to true and starts the thread.
     */
    public void startTimer() {
        running = true;
        this.start();
    }

    /**
     * Resets the timer to 0 seconds.
     */
    public void resetTimer() {
        this.seconds = 0;
    }

    /**
     * Sets the timer to a specific number of seconds.
     * 
     * @param seconds The number of seconds to set the timer to.
     */
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    /**
     * Stops the timer.
     * Sets running to false.
     */
    public void stopTimer() {
        running = false;
    }

    /**
     * Gets the current time on the timer.
     * 
     * @return The current time in seconds.
     */
    public int getTime() {
        return seconds;
    }

    /**
     * The run method required for any class extending Thread.
     * Continuously increments the timer by 1 second if the timer is running.
     */
    @Override
    public void run() {
        while (true) {
            if (running) {
                seconds++;
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
