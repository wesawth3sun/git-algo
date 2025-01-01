package chat.server.command;

import chat.server.Session;
import chat.server.SessionManager;

import java.io.IOException;

public class JoinCommand implements Command {

    public static final String DELIMITER = " | ";
    private final SessionManager sessionManager;

    public JoinCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String[] args, Session session) throws IOException {
        String username = args[2];
        session.setUsername(username);
        sessionManager.sendAll(username + " 님이 입장했습니다.");
    }
}
