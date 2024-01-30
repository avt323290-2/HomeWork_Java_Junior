package org.example;

/**
 * Простой класс, представляющий карту с балансом.
 */
public class Card {
    private int balance;

    /**
     * Конструктор класса Card.
     * @param balance Баланс карты.
     */
    public Card(int balance) {
        this.balance = balance;
    }

    /**
     * Получить баланс карты.
     * @return Баланс карты.
     */
    public int getBalance() {
        return balance;
    }
}