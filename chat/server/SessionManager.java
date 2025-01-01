package chat.server;


import java.util.ArrayList;
import java.util.List;

import static chat.util.MyLogger.log;

public class SessionManager {

    private List<Session> sessions = new ArrayList<>();

    public synchronized void add(Session session) {
        sessions.add(session);
    }

    public synchronized void remove(Session session) {
        sessions.remove(session);
    }

    public synchronized void sendAll(String message) {
        for (Session session : sessions) {
            try {
                session.send(message);
            } catch (Exception e) {
                log(e);
            }
        }
    }

    public synchronized List<String> getAllUsernames() {
        List<String> names = new ArrayList<>();
        for (Session session : sessions) {
            String username = session.getUsername();
            names.add(username);
        }
        return names;
    }

    public synchronized void closeAll() {
        for (Session session : sessions) {
            session.close();
        }
    }
}
