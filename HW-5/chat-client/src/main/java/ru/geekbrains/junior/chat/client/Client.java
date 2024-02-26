package ru.geekbrains.junior.chat.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Представляет клиентское приложение для обмена сообщениями с сервером.
 */
public class Client {

    private final Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private final String name;

    /**
     * Инициализирует новый экземпляр класса Client.
     *
     * @param socket   Сокет для подключения к серверу.
     * @param userName Имя пользователя.
     */
    public Client(Socket socket, String userName) {
        this.socket = socket;
        name = userName;

        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    /**
     * Отправляет сообщение на сервер.
     */
    public void sendMessage() {
        try {
            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String input = scanner.nextLine();
                if (input.startsWith("/private")) {
                    String[] parts = input.split(" ", 3);
                    String recipient = parts[1];
                    String message = parts[2];
                    sendPrivateMessage(recipient, message);
                } else {
                    bufferedWriter.write(name + ": " + input);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
            }
        } catch (Exception e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    /**
     * Отправляет личное сообщение заданному получателю.
     *
     * @param recipient Получатель личного сообщения.
     * @param message   Текст личного сообщения.
     */
    private void sendPrivateMessage(String recipient, String message) {
        try {
            bufferedWriter.write("@" + recipient + " " + message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (Exception e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    /**
     * Закрывает соединение с сервером и освобождает ресурсы.
     *
     * @param socket         Сокет для подключения к серверу.
     * @param bufferedReader Поток ввода данных от сервера.
     * @param bufferedWriter Поток вывода данных на сервер.
     */
    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}