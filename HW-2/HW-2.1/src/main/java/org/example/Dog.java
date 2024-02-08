package org.example;
/**
 * Класс, представляющий собаку.
 */
public class Dog extends Animal {
    private String breed;

    /**
     * Конструктор для инициализации имени, возраста и породы собаки.
     * @param name Имя собаки.
     * @param age Возраст собаки.
     * @param breed Порода собаки.
     */
    public Dog(String name, int age, String breed) {
        super(name, age);
        this.breed = breed;
    }

    @Override
    public void makeSound() {
        System.out.println("Гав-Гав!");
    }
}