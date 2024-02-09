package org.example;

import java.io.*;

public class SerializationDemo {
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

    private static void serializeStudent(Student student) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("student.ser"))) {
            outputStream.writeObject(student);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Student deserializeStudent() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("student.ser"))) {
            return (Student) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}