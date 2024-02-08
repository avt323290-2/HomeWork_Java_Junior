package org.example;
import java.lang.reflect.Method;

/**
 * Главный класс, в котором выполняются действия с использованием Reflection API.
 */
public class Main {
    public static void main(String[] args) {
        Animal[] animals = {
                new Dog("Бони", 3, "Доберман"),
                new Cat("Дарик", 2, true)
        };

        for (Animal animal : animals) {
            System.out.println("Information about " + animal.getClass().getSimpleName() + ":");
            printObjectInfo(animal);
            callMakeSoundMethod(animal);
            System.out.println();
        }
    }

    /**
     * Метод для вывода информации о объекте.
     * @param obj Объект, о котором необходимо вывести информацию.
     */
    public static void printObjectInfo(Object obj) {
        Class<?> clazz = obj.getClass();
        System.out.println("Class name: " + clazz.getSimpleName());
        System.out.println("Fields:");
        for (var field : clazz.getDeclaredFields()) {
            System.out.println(field.getName() + ": " + getFieldValue(obj, field.getName()));
        }
    }

    /**
     * Метод для вызова метода "makeSound()" объекта, если такой метод присутствует.
     * @param obj Объект, у которого необходимо вызвать метод "makeSound()".
     */
    public static void callMakeSoundMethod(Object obj) {
        Class<?> clazz = obj.getClass();
        try {
            Method makeSoundMethod = clazz.getMethod("makeSound");
            makeSoundMethod.invoke(obj);
        } catch (Exception e) {
            // Handle if the makeSound method is not found or cannot be invoked
        }
    }

    /**
     * Метод для получения значения поля объекта по его имени.
     * @param obj Объект, у которого необходимо получить значение поля.
     * @param fieldName Имя поля.
     * @return Значение поля объекта.
     */
    public static Object getFieldValue(Object obj, String fieldName) {
        try {
            Class<?> clazz = obj.getClass();
            var field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}