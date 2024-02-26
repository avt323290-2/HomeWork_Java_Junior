package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.*;

class Student {
    private String name;
    private int age;
    private transient double GPA;

    public Student(String name, int age, double GPA) {
        this.name = name;
        this.age = age;
        this.GPA = GPA;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", GPA=" + GPA +
                '}';
    }
}

public class SerializationDemo {
    public static void main(String[] args) {
        // Создаем объекты Student
        Student student1 = new Student("John", 20, 3.5);
        Student student2 = new Student("Alice", 21, 3.8);
        Student student3 = new Student("Bob", 19, 3.2);

        // Сериализация объектов в JSON
        serializeToJSON(student1, "student1.json");
        serializeToJSON(student2, "student2.json");
        serializeToJSON(student3, "student3.json");

        // Десериализация объектов из JSON
        Student deserializedStudent1 = deserializeFromJSON("student1.json");
        Student deserializedStudent2 = deserializeFromJSON("student2.json");
        Student deserializedStudent3 = deserializeFromJSON("student3.json");

        // Вывод результатов
        System.out.println("Deserialized student from JSON 1: " + deserializedStudent1);
        System.out.println("Deserialized student from JSON 2: " + deserializedStudent2);
        System.out.println("Deserialized student from JSON 3: " + deserializedStudent3);

        // Сериализация объектов в XML
        serializeToXML(student1, "student1.xml");
        serializeToXML(student2, "student2.xml");
        serializeToXML(student3, "student3.xml");

        // Десериализация объектов из XML
        Student deserializedStudentXML1 = deserializeFromXML("student1.xml");
        Student deserializedStudentXML2 = deserializeFromXML("student2.xml");
        Student deserializedStudentXML3 = deserializeFromXML("student3.xml");

        // Вывод результатов
        System.out.println("Deserialized student from XML 1: " + deserializedStudentXML1);
        System.out.println("Deserialized student from XML 2: " + deserializedStudentXML2);
        System.out.println("Deserialized student from XML 3: " + deserializedStudentXML3);
    }

    private static void serializeToJSON(Student student, String fileName) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(fileName), student);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Student deserializeFromJSON(String fileName) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new File(fileName), Student.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void serializeToXML(Student student, String fileName) {
        try {
            Serializer serializer = new Persister();
            serializer.write(student, new File(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Student deserializeFromXML(String fileName) {
        try {
            Serializer serializer = new Persister();
            return serializer.read(Student.class, new File(fileName));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}