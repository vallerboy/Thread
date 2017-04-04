/**
 * Created by OskarPraca on 2017-04-04.
 */
public class Main {
    public static void main(String[] args) {


        Runnable r = new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 500; i++) {
                    System.out.println("W1("+i+")");
                    Thread.yield();
                }
            }
        };

        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 500; i++) {
                    System.out.println(" W2("+i+")");
                    Thread.yield();
                }
            }
        };

        new Thread(r).start();
        new Thread(r1).start();
        System.out.println("Start time!");
    }
}
