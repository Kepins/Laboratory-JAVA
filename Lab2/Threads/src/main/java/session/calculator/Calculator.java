package session.calculator;

import session.job.Job;
import session.job.JobsList;

import static java.lang.Math.PI;
import static java.lang.Math.abs;

public class Calculator implements Runnable {
    private int id;
    private JobsList jobsToDo;
    private JobsList jobsDone;
    private boolean finishedJob;

    public Calculator(int id,JobsList jobsToDo, JobsList jobsDone) {
        this.id = id;
        this.jobsToDo = jobsToDo;
        this.jobsDone = jobsDone;
    }

    private void calculate(Job job){
        this.finishedJob = false;
        job.setPiValue(4.0);
        job.setError(abs(PI-4.0));
        job.setnIterations(1);
        double sum = 0;
        int n = 1;
        while(job.getError()>Math.pow(10.0, -job.getPrecision() -1 ) ){
            if(Thread.interrupted()){
                return;
            }
            sum += (Math.pow(-1, n-1))/(2*n-1);
            n+=1;
            job.setPiValue(4*sum);
            job.setError(Math.abs(PI-job.getPiValue()));
        }
        job.setnIterations(n);
        this.finishedJob = true;
        System.out.println("Calculator " + this.id + " finished job " + job.getJobId());
    }

    @Override
    public void run(){
        while (!Thread.interrupted()) {
            try {
                Job job = jobsToDo.take();
                calculate(job);
                if(this.finishedJob) {
                    jobsDone.put(job);
                }
                else{
                    break;
                }
            } catch (InterruptedException ex) {
                break;
            }
        }
    }





}
