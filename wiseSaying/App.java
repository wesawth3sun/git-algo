package wiseSaying;

import java.util.Scanner;

public class App {
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.print("명령) ");
            String command = scanner.nextLine();
            if (command.equals("등록")) {
                System.out.print("명언: ");
                String wiseSaying = scanner.nextLine();
                System.out.print("작가: ");
                String author = scanner.nextLine();
                System.out.println("명언: " + wiseSaying +
                        ", 작가: " + author);
            } else if (command.equals("종료")) {
                break;
            }
        }
    }
}