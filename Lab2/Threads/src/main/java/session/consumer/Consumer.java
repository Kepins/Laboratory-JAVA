package session.consumer;

import session.job.Job;
import session.job.JobsList;

public class Consumer {
    private JobsList jobsDone;

    public Consumer(JobsList jobsDone) {
        this.jobsDone = jobsDone;
    }

    public void printResults(){
        System.out.println("Results: ");
        for(Job job : jobsDone.getJobs()){
            System.out.println(job);
        }
    }
}
