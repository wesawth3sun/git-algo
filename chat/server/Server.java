package chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static chat.util.MyLogger.log;

public class Server {

    private final CommandManager commandManager;
    private final SessionManager sessionManager;
    private ServerSocket serverSocket;

    public Server(CommandManager commandManager, SessionManager sessionManager) {
        this.commandManager = commandManager;
        this.sessionManager = sessionManager;
    }

    public void start() throws IOException {
        log("서버 시작: " + commandManager.getClass());
        serverSocket = new ServerSocket(12345);
        log("서버 소켓 - 리스닝 포트: " + serverSocket);

        addShutdownHook();
        running();
    }

    private void running() {
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                log("소켓 연결: " + socket);
                Session session = new Session(socket, sessionManager, commandManager);
                Thread thread = new Thread(session);
                thread.start();
            }
        } catch (IOException e) {
            log("서버 소켓 종료: " + e);
        }
    }

    private void addShutdownHook() {
        ShutdownHook shutdownHook = new ShutdownHook(serverSocket, sessionManager);
        Runtime.getRuntime().addShutdownHook(new Thread(shutdownHook, "shutdown"));

    }

    private static class ShutdownHook implements Runnable {

        private final ServerSocket serverSocket;
        private final SessionManager sessionManager;

        private ShutdownHook(ServerSocket serverSocket, SessionManager sessionManager) {
            this.serverSocket = serverSocket;
            this.sessionManager = sessionManager;
        }

        @Override
        public void run() {
            log("ShutdownHook 실행");
            try {
                sessionManager.closeAll();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("e = " + e);
            }

        }
    }
}
