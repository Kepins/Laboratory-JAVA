package session.producer;

import session.calculator.Calculator;
import session.finisher.Finisher;
import session.job.Job;
import session.job.JobsList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Producer implements Runnable{
    private JobsList jobsToDo;
    private Finisher finisher;

    public Producer(JobsList jobsToDo, Finisher finisher) {
        this.jobsToDo = jobsToDo;
        this.finisher = finisher;
    }

    @Override
    public void run(){

        int jobId = 0;
        //default values to calculate
        jobsToDo.put(new Job(jobId++, 2));
        jobsToDo.put(new Job(jobId++, 7));
        jobsToDo.put(new Job(jobId++, 4));
        jobsToDo.put(new Job(jobId++, 5));
        jobsToDo.put(new Job(jobId++, 3));

        String input;
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        while(!Objects.equals(input, "q")){
            jobsToDo.put(new Job(jobId,Integer.parseInt(input)));
            jobId = jobId + 1;
            input = scanner.nextLine();
        }
        finisher.setFinish();
    }
}
