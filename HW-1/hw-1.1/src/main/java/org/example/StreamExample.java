import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StreamExample {

    public static void main(String[] args) {
        List<Integer> numbers = readNumbersFromConsole();

        double average = numbers.stream()
                .filter(n -> n % 2 == 0)  // фильтрация четных чисел
                .mapToDouble(Integer::doubleValue)
                .average()
                .orElse(0);

        System.out.println("Среднее значение четных чисел: " + average);
    }

    private static List<Integer> readNumbersFromConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите числа через пробел:");

        List<Integer> numbers = new ArrayList<>();
        String input = scanner.nextLine();
        String[] numberStrings = input.split("\\s+");

        for (String numberString : numberStrings) {
            try {
                int number = Integer.parseInt(numberString);
                numbers.add(number);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка ввода: '" + numberString + "' не является целым числом. Попробуйте снова.");
                return readNumbersFromConsole();
            }
        }

        return numbers;
    }
}