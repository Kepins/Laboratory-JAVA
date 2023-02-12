package session.service;

import session.calculator.Calculator;
import session.consumer.Consumer;
import session.finisher.Finisher;
import session.job.JobsList;
import session.producer.Producer;
import session.thread.Timeout;

import java.util.ArrayList;
import java.util.List;

public class SessionService {
    private int numberOfCalculators;
    public SessionService(int numberOfCalculators) {
        this.numberOfCalculators = numberOfCalculators;
    }

    public Thread startGenericSession(){
        JobsList jobsToDo = new JobsList();
        JobsList jobsDone = new JobsList();

        Finisher finisher = new Finisher();
        Producer producer = new Producer(jobsToDo, finisher);
        List<Calculator> calculators = new ArrayList<>();
        for(int i=0;i<numberOfCalculators;i++) {
            calculators.add(new Calculator(i, jobsToDo, jobsDone));
        }

        List<Thread> threads = new ArrayList<>();
        threads.add(new Thread(producer));

        for(Calculator calculator : calculators){
            threads.add(new Thread(calculator));
        }

        Timeout timeout = new Timeout(threads, new Consumer(jobsDone) ,finisher);
        Thread timeoutThread = new Thread(timeout);
        timeoutThread.start();

        for (Thread thread : threads) {
            thread.start();
        }
        return timeoutThread;
    }
}
