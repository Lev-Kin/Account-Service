import java.time.LocalDate;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LocalDate date1 = LocalDate.parse(scanner.next());
        LocalDate date2 = LocalDate.parse(scanner.next());
        LocalDate date3 = LocalDate.parse(scanner.next());

        boolean result = date1.isAfter(date2) && date1.isBefore(date3) ||
                date1.isAfter(date3) && date1.isBefore(date2);

        System.out.print(result);
    }
}
