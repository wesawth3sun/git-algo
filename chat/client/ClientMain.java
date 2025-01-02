package chat.client;

import java.io.IOException;

import static chat.util.MyLogger.log;

public class ClientMain {

    public static void main(String[] args) throws IOException {
        Client client = new Client("localhost", 12345);
        log("클라이언트 시작");
        client.start();
    }

}
