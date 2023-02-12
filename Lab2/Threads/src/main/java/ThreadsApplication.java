import session.service.SessionService;
import session.view.SessionStart;
import view.View;

public class ThreadsApplication {
    public static void main(String[] args){
        int numberOfCalculators = Integer.parseInt(args[0]);
        SessionService sessionService = new SessionService(numberOfCalculators);
        View sessionView = new SessionStart(sessionService);
        sessionView.display();
    }

}

