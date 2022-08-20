import java.time.LocalTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            LocalTime now = LocalTime.of(19, 30);
            int amount = scanner.nextInt();

            for (int i = 0; i < amount; i++) {
                String name = scanner.next();
                LocalTime time = LocalTime.parse(scanner.next());

                if (time.isAfter(now.plusMinutes(30)))
                    System.out.println(name);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}