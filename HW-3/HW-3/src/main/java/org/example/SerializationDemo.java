package org.example;

import java.io.*;

/**
 * Демонстрация сериализации и десериализации объекта класса Student.
 */
public class SerializationDemo {
    /**
     * Основной метод программы.
     *
     * @param args Параметры командной строки (не используются)
     */
    public static void main(String[] args) {
        // Создаем объект Student
        Student student = new Student("John", 20, 3.5);

        // Сериализация объекта в файл
        serializeStudent(student);

        // Десериализация объекта из файла
        Student deserializedStudent = deserializeStudent();
        if (deserializedStudent != null) {
            System.out.println("Deserialized student: " + deserializedStudent);
        }
    }

    /**
     * Сериализует объект Student в файл.
     *
     * @param student Студент для сериализации
     */
    private static void serializeStudent(Student student) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("student.ser"))) {
            outputStream.writeObject(student);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Десериализует объект Student из файла.
     *
     * @return Десериализованный студент
     */
    private static Student deserializeStudent() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("student.ser"))) {
            return (Student) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}