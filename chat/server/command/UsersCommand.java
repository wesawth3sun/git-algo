package chat.server.command;

import chat.server.Session;
import chat.server.SessionManager;

import java.io.IOException;
import java.util.List;

public class UsersCommand implements Command {

    private final SessionManager sessionManager;

    public UsersCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String[] args, Session session) throws IOException {
        List<String> allUsernames = sessionManager.getAllUsernames();
        StringBuilder sb = new StringBuilder();
        sb.append("전체 접속자: ").append(allUsernames.size()).append("\n");
        for (String username : allUsernames) {
            sb.append(" - ").append(username).append("\n");
        }
        sessionManager.sendAll(sb.toString());
    }
}
