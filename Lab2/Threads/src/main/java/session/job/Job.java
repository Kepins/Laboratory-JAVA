package session.job;

public class Job {
    private int jobId;
    private int precision;
    private double piValue;
    private double error;
    private int nIterations;

    public Job(int jobId, int precision) {
        this.jobId = jobId;
        this.precision = precision;
    }

    public int getJobId() {
        return jobId;
    }

    public int getPrecision() {
        return precision;
    }

    public double getPiValue() {
        return piValue;
    }

    public void setPiValue(double piValue) {
        this.piValue = piValue;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }

    public int getnIterations() {
        return nIterations;
    }

    public void setnIterations(int nIterations) {
        this.nIterations = nIterations;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobId=" + jobId +
                ", precision=" + precision +
                ", piValue=" + piValue +
                ", error=" + error +
                ", nIterations=" + nIterations +
                '}';
    }
}
