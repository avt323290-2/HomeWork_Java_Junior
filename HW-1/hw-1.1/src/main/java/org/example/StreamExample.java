import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Программа для вычисления среднего значения четных чисел в списке с использованием Stream API.
 */
public class StreamExample {

    /**
     * Главный метод программы.
     * @param args Аргументы командной строки (не используются).
     */
    public static void main(String[] args) {
        List<Integer> numbers = readNumbersFromConsole();

        double average = numbers.stream()
                .filter(n -> n % 2 == 0)  // фильтрация четных чисел
                .mapToDouble(Integer::doubleValue)
                .average()
                .orElse(0);

        System.out.println("Среднее значение четных чисел: " + average);
    }

    /**
     * Метод для чтения чисел из консоли.
     * @return Список введенных чисел.
     */
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
