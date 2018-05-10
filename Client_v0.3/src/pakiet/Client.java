package pakiet;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

class Client implements Runnable {

    Socket socket;
    public PrintWriter out;
    public BufferedReader in;
    private Key listner;
    private JLabel point_stud;
    private JLabel point_uz;
    JProgressBar progressBar1;
    JLabel lab;
    JFrame frame;

    public String strona;
    private int winStud = 0;
    private int winUZ = 0;

    public Client(JLabel point_stud, JLabel point_uz, JProgressBar progressBar1, JLabel lab, JFrame frame, String strona) {
        this.point_stud = point_stud;
        this.point_uz = point_uz;
        this.progressBar1 = progressBar1;
        this.lab = lab;
        this.frame = frame;
        this.strona = strona;
    }

    public void run() {
        try {
            socket = new Socket(/*"localhost*/"rpihome.ddns.net", 9090);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            listner = new Key(progressBar1, out, strona);
            frame.addKeyListener(listner);
            while (true) {           //nasluchuj caly czas
                String dane_str = in.readLine();
                int dane = Integer.parseInt(dane_str);
                progressBar1.setValue(dane);                            //odbieram z serwera i ustawiam wartosc
                if (dane == 50) {
                    lab.setText("");
                }
                System.out.println("Dostalem z serwera: " + dane);
                if (strona == "lewy") {
                    if (progressBar1.getValue() == progressBar1.getMaximum()) {
                        przegrales();
                        winUZ++;
                        point_uz.setText(Integer.toString(winUZ));
                    }
                    if (progressBar1.getValue() == progressBar1.getMinimum()) {
                        wygrales();
                        winStud++;
                        point_stud.setText(Integer.toString(winStud));
                    }
                } else if (strona == "prawy") {
                    if (progressBar1.getValue() == progressBar1.getMaximum()) {
                        wygrales();
                        winUZ++;
                        point_uz.setText(Integer.toString(winUZ));
                    }
                    if (progressBar1.getValue() == progressBar1.getMinimum()) {
                        przegrales();
                        winStud++;
                        point_stud.setText(Integer.toString(winStud));
                    }
                }
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();

        } catch (IOException e) {
            System.out.println("szukam serwera");
            frame.removeKeyListener(listner);
            run();
        }

    }

    public void wygrales() {
        lab.setText("Wygrana");
    }

    public void przegrales() {
        lab.setText("Przegrana");
    }
}