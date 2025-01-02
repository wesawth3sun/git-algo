package chat.server.command;

import chat.server.Session;
import chat.server.SessionManager;

import java.io.IOException;


public class MessageCommand implements Command {

    private final SessionManager sessionManager;

    public MessageCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String[] args, Session session) throws IOException {
        String message = args[2];
        sessionManager.sendAll("[" + session.getUsername() + "] " + message);
    }
}
