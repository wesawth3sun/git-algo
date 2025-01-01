package chat.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static chat.util.MyLogger.log;

public class WriteHandler implements Runnable {

    private static final String DELIMITER = " | ";

    private final DataOutputStream outputStream;
    private final Client client;

    private boolean closed = false;

    public WriteHandler(DataOutputStream outputStream, Client client) {
        this.outputStream = outputStream;
        this.client = client;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        // System.in 을 close() 하면 (콘솔로부터 입력을 받지 않겠다) 예외가 발생함
        // NoSuchElementException
        try {
            String username = inputUsername(scanner);
            outputStream.writeUTF("/join" + DELIMITER + username);

            while (true) {
                // 여기서 빠져나오려면 system.in 을 닫아야 exception 터지면서 빠져나올 수 있음.
                String toSend = scanner.nextLine(); // 블로킹
                if (toSend.isEmpty()) {
                    continue;
                    //아무것도 입력 안 하고 엔터 누르면 continue -> 다시 입력 받기
                }
                if (toSend.equals("/exit")) {
                    outputStream.writeUTF(toSend);
                    break;
                }
                if (toSend.startsWith("/")) {
                    //명령어란 것임
                    outputStream.writeUTF(toSend);
                } else {
                    //일반 메세지
                    outputStream.writeUTF("/message" + DELIMITER + toSend);
                }
            }
        } catch (IOException | NoSuchElementException e) {
            log(e);
        } finally {
            client.close();
        }
    }

    private static String inputUsername(Scanner scanner) {
        System.out.print("이름을 입력하세요: ");
        String username;
        do {
            username = scanner.nextLine();
        } while (username.isEmpty());
        //사용자 이름에 값이 들어오지 않으면 계속 돌면서 이름 받을 것임
        return username;
    }

    public synchronized void close() {
        if (closed) {
            return;
        }
        try {
            System.in.close(); // 스캐너를 닫아버리는 것, 콘솔 입력 중지
        } catch (IOException e) {
            log(e);
        }
        closed = true;
        log("writeHandler 종료");
    }
}

