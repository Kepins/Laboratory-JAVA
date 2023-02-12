package session.thread;

import session.consumer.Consumer;
import session.finisher.Finisher;

import java.util.List;
import java.util.logging.Level;

public class Timeout implements Runnable{

    private List<Thread> threads;
    private Finisher finisher;
    private Consumer consumer;

    public Timeout(List<Thread> threads, Consumer consumer, Finisher finisher) {
        this.threads = threads;
        this.consumer = consumer;
        this.finisher = finisher;
    }

    @Override
    public void run(){
        while(!finisher.shouldFinish()){
            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {

            }
        }
        for (Thread thread : threads) {
            thread.interrupt();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException ex) {

            }
        }
        consumer.printResults();
    }

}
