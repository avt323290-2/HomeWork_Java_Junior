package org.example;
/**
 * Класс, представляющий кошку.
 */
public class Cat extends Animal {

    /**
     * Конструктор для инициализации имени, возраста и информации о том, является ли кошка бродячей.
     * @param name Имя кошки.
     * @param age Возраст кошки.
     * @param isStray Признак того, является ли кошка бродячей.
     */
    public Cat(String name, int age, boolean isStray) {
        super(name, age);
    }

    @Override
    public void makeSound() {
        System.out.println("Мяу-Мяу!");
    }
}