import java.time.LocalDate;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String date = scanner.next();
        int days = scanner.nextInt();

        LocalDate localDate = LocalDate.parse(date);
        System.out.println(localDate.plusDays(days).getDayOfYear() == 1);
    }
}

