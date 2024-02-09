package org.example;

import java.io.Serializable;

/**
 * Класс, представляющий студента.
 */
public class Student implements Serializable {
    private String name; // Имя студента
    private int age; // Возраст студента
    private transient double GPA; // Средний балл студента (не сериализуется)

    /**
     * Конструктор класса Student.
     *
     * @param name Имя студента
     * @param age  Возраст студента
     * @param GPA  Средний балл студента
     */
    public Student(String name, int age, double GPA) {
        this.name = name;
        this.age = age;
        this.GPA = GPA;
    }

    /**
     * Получить имя студента.
     *
     * @return Имя студента
     */
    public String getName() {
        return name;
    }

    /**
     * Получить возраст студента.
     *
     * @return Возраст студента
     */
    public int getAge() {
        return age;
    }

    /**
     * Получить средний балл студента.
     *
     * @return Средний балл студента
     */
    public double getGPA() {
        return GPA;
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