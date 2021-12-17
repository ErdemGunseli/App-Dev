package concurrency;

public class Main {

    public static void main(String[] args) {

//        // Concurrency (asynchronous)  is doing multiple things at the same time.
//        // The following code demonstrates this:
//
//
//        // We need to create a thread for this, which is a unit of execution in the device's CPU.
//
//        // Creating a Thread:
//
//        Thread myThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 5; i++){
//                    System.out.println("From Worker Thread Thread: " + i);
//
//                    // Sleeping the thread
//                    // Using try - catch
//
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//        });
//
//        myThread.start();
//
//        for (int i = 0; i < 5; i++){
//            System.out.println("From Main Thread: " + i);
//
//            // Sleeping the thread
//            // Using try - catch
//
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        int a = 2;
        int b = 0;

        // The following will produce an arithmetic exception as a number cannot be divided by 0.
        // What we can do to avoid this is to catch it.

        try {
            System.out.println(a / b);

        } catch (ArithmeticException e) { // If we do not know the type of exception that we may encounter, we can just use Exception
           e.printStackTrace(); // This is useful for debugging
            System.out.println("Invalid Operation");
        }
    }
}
