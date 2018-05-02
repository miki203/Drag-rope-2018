package pakiet;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

class Client implements Runnable {

    Socket socket;
    public PrintWriter out;
    public BufferedReader in;

    JProgressBar progressBar1;
    JLabel lab;
    JFrame frame;

    public String strona;

    public Client(JProgressBar progressBar1, JLabel lab, JFrame frame, String strona) {
        this.progressBar1 = progressBar1;
        this.lab = lab;
        this.frame = frame;
        this.strona = strona;
    }


    public void run() {
        try {
            socket = new Socket("localhost"/*rpihome.ddns.net"*/, 9090);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);


            frame.addKeyListener(new Key(progressBar1, out, strona));
            while (strona != "bot") {           //nasluchuj caly czas
                String dane_str = in.readLine();
                int dane = Integer.parseInt(dane_str);
                progressBar1.setValue(dane);                            //odbieram z serwera i ustawiam wartosc
                if(dane==50) {
                    lab.setText("");
                }
                System.out.println("Dostalem z serwera: " + dane);
                if (strona == "lewy") {
                    if (progressBar1.getValue() == progressBar1.getMaximum()) {
                        przegrales();
                    }
                    if (progressBar1.getValue() == progressBar1.getMinimum()) {
                        wygrales();
                    }
                } else if (strona == "prawy") {
                    if (progressBar1.getValue() == progressBar1.getMaximum()) {
                        wygrales();
                    }
                    if (progressBar1.getValue() == progressBar1.getMinimum()) {
                        przegrales();
                    }
                }
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();

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

    public void wygrales() {
        lab.setText("Wygrana");
    }

    public void przegrales() {
        lab.setText("Przegrana");
    }
}