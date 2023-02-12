package session.job;

import java.util.ArrayList;
import java.util.List;

public class JobsList {

    private List<Job> jobs = new ArrayList<>();

    public synchronized Job take() throws InterruptedException{
        while (jobs.isEmpty()){
            wait();
        }
        return jobs.remove(0);
    }

    public synchronized void put(List<Job> jobs){
        this.jobs.addAll(jobs);
        notifyAll();
    }
    public synchronized void put(Job job){
        this.jobs.add(job);
        notifyAll();
    }

    public synchronized List<Job> getJobs(){
        return this.jobs;
    }
}
