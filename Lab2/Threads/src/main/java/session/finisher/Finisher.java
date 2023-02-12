package session.finisher;

public class Finisher {
    private boolean finish = false;

    public void setFinish(){
        this.finish = true;
    }

    public boolean shouldFinish(){
        return this.finish;
    }

}
