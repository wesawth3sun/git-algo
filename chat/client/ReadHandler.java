package chat.client;

import java.io.DataInputStream;
import java.io.IOException;

import static chat.util.MyLogger.log;

public class ReadHandler implements Runnable {

    private DataInputStream inputStream;
    private boolean closed = false;
    private final Client client;

    public ReadHandler(DataInputStream inputStream, Client client) {
        this.inputStream = inputStream;
        this.client = client;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String received = inputStream.readUTF();
                System.out.println(received);
                //예외가 터지면 while 문을 빠져나갈 수 있도록 함
            }
        } catch (IOException e) {
            log(e);
        } finally {
            client.close();
        }
    }

    public synchronized void close() {
        //자원 정리
        if (closed) {
            return;
        }
        closed = true;
        log("readHandler 종료");
    }
}

