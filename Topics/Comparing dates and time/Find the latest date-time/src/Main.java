import java.time.LocalDateTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number =  Integer.parseInt(scanner.nextLine());
        LocalDateTime localDateTime = LocalDateTime.parse(scanner.nextLine());

        for (int i = 0; i < number - 1; i++) {
            LocalDateTime next = LocalDateTime.parse(scanner.nextLine());
            if (next.isAfter(localDateTime)) {
                localDateTime = next;
            }
        }

        System.out.println(localDateTime);
    }
}
