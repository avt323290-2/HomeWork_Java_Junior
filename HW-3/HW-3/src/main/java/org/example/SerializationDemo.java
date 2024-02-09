package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Демонстрация сериализации и десериализации объектов класса Student.
 */
public class SerializationDemo {
    /**
     * Основной метод программы.
     *
     * @param args Параметры командной строки (не используются)
     */
    public static void main(String[] args) {
        // Создаем список студентов
        List<Student> students = new ArrayList<>();
        students.add(new Student("John", 20, 3.5));
        students.add(new Student("Alice", 21, 3.8));
        students.add(new Student("Bob", 19, 3.2));

        // Сериализация объектов в файл
        serializeStudents(students);

        // Десериализация объектов из файла
        List<Student> deserializedStudents = deserializeStudents();
        if (deserializedStudents != null) {
            System.out.println("Deserialized students:");
            for (Student student : deserializedStudents) {
                System.out.println(student);
            }
        }
    }

    /**
     * Сериализует список студентов в файл.
     *
     * @param students Список студентов для сериализации
     */
    private static void serializeStudents(List<Student> students) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("students.ser"))) {
            outputStream.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Десериализует список студентов из файла.
     *
     * @return Десериализованный список студентов
     */
    private static List<Student> deserializeStudents() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("students.ser"))) {
            return (List<Student>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}