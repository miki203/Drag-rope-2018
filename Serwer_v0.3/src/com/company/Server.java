package com.company;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable {

    private ArrayList<Watek_klienta> lista_klientow = new ArrayList<>();

    public void run() {

        try(ServerSocket serverSocket = new ServerSocket(9090)) {
            while (true) {
                Socket socket = serverSocket.accept();
                lista_klientow.add(new Watek_klienta(socket, lista_klientow));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}