package ru.geekbrains.junior.chat.server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Класс, отвечающий за управление клиентами в чате.
 */
public class ClientManager implements Runnable {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;
    public static ArrayList<ClientManager> clients = new ArrayList<>();

    /**
     * Конструктор класса ClientManager.
     *
     * @param socket Сокет для клиента.
     */
    public ClientManager(Socket socket) {
        try {
            this.socket = socket;
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            clients.add(this);
            name = bufferedReader.readLine();
            System.out.println(name + " подключился к чату.");
            broadcastMessage("Server: " + name + " подключился к чату.");
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    /**
     * Закрывает соединение с клиентом и освобождает ресурсы.
     *
     * @param socket         Сокет клиента.
     * @param bufferedReader Поток чтения данных от клиента.
     * @param bufferedWriter Поток записи данных к клиенту.
     */
    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        removeClient();
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

    /**
     * Удаляет клиента из списка активных клиентов.
     */
    private void removeClient() {
        clients.remove(this);
        System.out.println(name + " покинул чат.");
        broadcastMessage("Server: " + name + " покинул чат.");
    }

    /**
     * Отправляет сообщение всем клиентам.
     *
     * @param message Сообщение для отправки.
     */
    private void broadcastMessage(String message) {
        for (ClientManager client : clients) {
            try {
                if (!client.equals(this) && message != null) {
                    client.bufferedWriter.write(message);
                    client.bufferedWriter.newLine();
                    client.bufferedWriter.flush();
                }
            } catch (Exception e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    /**
     * Запускает поток для чтения сообщений от клиента.
     */
    @Override
    public void run() {
        String message;
        while (!socket.isClosed()) {
            try {
                message = bufferedReader.readLine();
                if (message.startsWith("@")) {
                    String[] parts = message.split(" ", 2);
                    String recipient = parts[0].substring(1);
                    String privateMessage = name + " (private): " + parts[1];
                    sendPrivateMessage(recipient, privateMessage);
                } else {
                    broadcastMessage(name + ": " + message);
                }
            } catch (Exception e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    /**
     * Отправляет личное сообщение конкретному клиенту.
     *
     * @param recipient Получатель личного сообщения.
     * @param message   Личное сообщение.
     */
    private void sendPrivateMessage(String recipient, String message) {
        for (ClientManager client : clients) {
            try {
                if (client.name.equals(recipient)) {
                    client.bufferedWriter.write(message);
                    client.bufferedWriter.newLine();
                    client.bufferedWriter.flush();
                }
            } catch (Exception e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }
}