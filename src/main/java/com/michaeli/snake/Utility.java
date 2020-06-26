package src.main.java.com.michaeli.snake;

public class Utility {
    public static void sleep(long ms) { 
        try {
            Thread.sleep(ms);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
