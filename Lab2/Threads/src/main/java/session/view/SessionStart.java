package session.view;

import session.service.SessionService;
import view.View;

import java.util.logging.Level;

public class SessionStart implements View {

    /**
     * Service for session.
     */
    private final SessionService sessionService;

    /**
     * @param sessionService service for session
     */
    public SessionStart(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public void display() {
        System.out.println("Starting new session");
        try {
            sessionService.startGenericSession().join();
            System.out.println("Session ends");
        } catch (InterruptedException ex) {

        }
    }

}
