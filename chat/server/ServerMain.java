package chat.server;

import java.io.IOException;

public class ServerMain {

    public static void main(String[] args) throws IOException {
        SessionManager sessionManager = new SessionManager();
        CommandManager commandManager = new CommandManager(sessionManager);

        Server server = new Server(commandManager, sessionManager);
        server.start();
    }
}
