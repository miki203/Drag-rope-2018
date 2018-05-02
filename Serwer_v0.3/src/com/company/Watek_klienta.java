package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Watek_klienta implements Runnable {

    private Socket socket;
    private ArrayList<Watek_klienta> lista_klientow;
    private BufferedReader in;
    private PrintWriter out;

    public Watek_klienta(Socket socket, ArrayList<Watek_klienta> lista_klientow) {
        this.socket = socket;
        this.lista_klientow = lista_klientow;
        new Thread(this).start();
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                try {
                    String dane = in.readLine();
                    System.out.println("Klient wyslal: " + dane);
                    sendToAll(dane);
                } catch (Exception e) {
                    socket.close();
                    out.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    private void sendMsg(String msg) {
//        out.println(msg);
//    }

    private void sendToAll(String msg) {
        for (Watek_klienta klient : lista_klientow) {
            klient.out.println(msg);
        }
    }
}
