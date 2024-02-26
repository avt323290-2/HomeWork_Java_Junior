package ru.geekbrains.junior.chat.server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Управляет подключением клиента к серверу и обменом сообщениями.
 */
public class ClientManager implements Runnable {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;
    public static ArrayList<ClientManager> clients = new ArrayList<>();

    /**
     * Инициализирует новый экземпляр класса ClientManager.
     *
     * @param socket Сокет клиента.
     */
    public ClientManager(Socket socket) {
        try {
            this.socket = socket;
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            clients.add(this);
            name = bufferedReader.readLine(); // Получаем имя пользователя
            System.out.println(name + " подключился к чату.");
            broadcastMessage("Server: " + name + " подключился к чату.");
        } catch (Exception e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    /**
     * Удаляет клиента из списка подключенных.
     */
    private void removeClient() {
        clients.remove(this);
        System.out.println(name + " покинул чат.");
        broadcastMessage("Server: " + name + " покинул чат.");
    }

    /**
     * Отправляет личное сообщение заданному клиенту.
     *
     * @param recipient Получатель личного сообщения.
     * @param message   Текст личного сообщения.
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

    /**
     * Отправляет сообщение всем клиентам, кроме отправителя.
     *
     * @param message Сообщение для отправки.
     */
    private void broadcastMessage(String message) {
        if (message.startsWith("@")) {
            String[] parts = message.split(" ", 2);
            String recipient = parts[0].substring(1);
            String privateMessage = name + " (private): " + parts[1];
            sendPrivateMessage(recipient, privateMessage);
        } else {
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
    }

    @Override
    public void run() {
        String messageFromClient;
        while (!socket.isClosed()) {
            try {
                messageFromClient = bufferedReader.readLine();
                broadcastMessage(messageFromClient);
            } catch (Exception e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    /**
     * Закрывает соединение с клиентом и освобождает ресурсы.
     *
     * @param socket         Сокет клиента.
     * @param bufferedReader Поток ввода данных от клиента.
     * @param bufferedWriter Поток вывода данных клиенту.
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