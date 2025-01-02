package chat.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static chat.util.MyLogger.log;
import static chat.util.SocketCloseUtil.closeAll;

public class Client {

    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private Socket socket;

    private ReadHandler readHandler;
    private WriteHandler writeHandler;

    private final String host;
    private final int port;

    private boolean closed = false;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws IOException {
        log("클라이언트 시작");
        socket = new Socket(host, port);
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());

        readHandler = new ReadHandler(inputStream, this);
        writeHandler = new WriteHandler(outputStream, this);
        Thread readThread = new Thread(readHandler, "readHandler");
        Thread writeThread = new Thread(writeHandler, "writeHandler");

        readThread.start();
        writeThread.start();
    }

    public synchronized void close() {
        if (closed) {
            return;
        }
        try {
            readHandler.close();
            writeHandler.close();
            closeAll(socket, inputStream, outputStream);
            log("연결 종료: " + socket);
            closed = true;
        } catch (Exception e) {
            log(e);
        }
    }
}
