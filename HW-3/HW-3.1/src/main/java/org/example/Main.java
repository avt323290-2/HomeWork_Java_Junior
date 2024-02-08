import java.io.*;

public class Main {
    public static void main(String[] args) {
        // Создание и инициализация объекта Student
        Student student = new Student("John", 20, 3.5);

        // Сериализация объекта в файл
        serializeStudent(student, "student.ser");

        // Десериализация объекта из файла
        Student deserializedStudent = deserializeStudent("student.ser");

        // Вывод всех полей объекта
        deserializedStudent.display();

        // Ответ на вопрос: почему значение GPA не было сохранено/восстановлено
        System.out.println("Значение GPA не было сохранено/восстановлено, потому что оно помечено как transient.");
    }

    private static void serializeStudent(Student student, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(student);
            System.out.println("Объект успешно сериализован.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Student deserializeStudent(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Student student = (Student) ois.readObject();
            System.out.println("Объект успешно десериализован.");
            return student;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}