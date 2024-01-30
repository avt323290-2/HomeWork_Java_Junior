package org.example;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Создаем список карт для демонстрации
        List<Card> cards = Arrays.asList(
                new Card(1000),
                new Card(2000),
                new Card(3060),
                new Card(4034),
                new Card(5000)
        );

        // Вызываем метод балансировки
        CardBalancingExample.cardBalancing(cards);
    }
}