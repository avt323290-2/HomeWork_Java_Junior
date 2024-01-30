package org.example;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Пример использования Stream API для балансировки карт товаров.
 * Программа находит средний баланс и выводит карты с балансом выше среднего.
 */
public class CardBalancingExample {

    /**
     * Метод балансировки карт товаров с использованием Stream API.
     * @param cards Список карт товаров.
     */
    public static void cardBalancing(List<Card> cards) {
        double averageBalance = cards.stream()
                .mapToDouble(Card::getBalance)
                .average()
                .orElse(0);

        System.out.println("Средний баланс: " + averageBalance);

        cards.stream()
                .filter(card -> card.getBalance() > averageBalance)
                .forEach(card -> System.out.println("Карта с балансом выше среднего: " + card.getBalance()));
    }
}