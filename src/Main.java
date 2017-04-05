import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

      //  new Thread(r).start();
      //  new Thread(r1).start();
      //  System.out.println("Start time!");
        new Main();
    }

    private ExecutorService executorService;

    public Main() {
          executorService = Executors.newCachedThreadPool();

        Runnable runnable = new Runnable() {
              @Override
              public void run() {
                  try {
                      Thread.sleep(5000);
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
                  for(int i = 0; i <= 100; i++) {
                      if(Thread.currentThread().isInterrupted()){
                          return;
                      }

                      System.out.println(Thread.currentThread().getName() + " Jestem zadaniem 1 ( " + i + ")");
                     // Thread.yield();
                  }
              }
          };

       

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i <= 100; i++) {
                    if(Thread.currentThread().isInterrupted()){
                        return;
                    }
                    System.out.println(Thread.currentThread().getName() + " Jestem zadaniem 2 ( " + i + ")");
                   // Thread.yield();
                }
            }
        };

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i <= 100; i++) {
                    if(Thread.currentThread().isInterrupted()){
                        return;
                    }
                    System.out.println(Thread.currentThread().getName() + " Jestem zadaniem 3 ( " + i + ")");
                }
            }
        };



        // Stare podejście
        //new Thread(runnable).start();
        //new Thread(runnable1).start();

        //Nowe podejście
        executorService.execute(runnable);
        executorService.execute(runnable1);
       // executorService.execute(runnable2);
        //Executor zamknięcie, ale poprzednie zadania wykonają się do końca, każde nowe powoduje błąd.
        //executorService.shutdown();
        //Executor zamknięcie, zmienia także status każdego zadania na przerwany!
       // executorService.shutdownNow();
        //executorService.execute(runnable1);

    }

    private static class Operation {
        private int oskar = 0;
         synchronized public int operation() {
            oskar++;
            oskar--;
            return oskar;
        }
    }

    private static class Thread1 extends Thread  {
        Operation operation;

        public Thread1(Operation o ) {
            operation = o;
        }

        @Override
        public void run() {
            for(int i = 0; i <= 100; i++){
                operation.operation();
            }

            System.out.println(operation.oskar);

        }
    }
}
