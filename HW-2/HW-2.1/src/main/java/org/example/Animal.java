package org.example;
/**
 * Абстрактный класс, представляющий животное.
 */
public abstract class Animal {
    protected String name;
    protected int age;

    /**
     * Конструктор для инициализации имени и возраста животного.
     * @param name Имя животного.
     * @param age Возраст животного.
     */
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Абстрактный метод, представляющий звук, издаваемый животным.
     */
    public abstract void makeSound();
};