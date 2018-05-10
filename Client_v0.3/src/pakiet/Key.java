package pakiet;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.PrintWriter;

public class Key implements KeyListener {

    private JProgressBar progressBar1;
    private PrintWriter out;
    private String strona;

    public Key(JProgressBar progressBar1, PrintWriter out, String strona) {
        this.progressBar1 = progressBar1;
        this.out = out;
        this.strona = strona;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        char znak = e.getKeyChar();
        if (znak == 'l' && progressBar1.getValue() != progressBar1.getMaximum() && progressBar1.getValue() != progressBar1.getMinimum()) {
            if (strona == "lewy") {
                zmniejsz();
                int wyslij = progressBar1.getValue();
                out.println(Integer.toString(wyslij));
            } else if (strona == "prawy") {
                zwieksz();
                int wyslij = progressBar1.getValue();
                out.println(Integer.toString(wyslij));
            }
        }
        if (znak == 'r' && (progressBar1.getValue() == progressBar1.getMaximum() || progressBar1.getValue() == progressBar1.getMinimum())) {
            out.println(50);
        }
    }

    public void zwieksz() {
        progressBar1.setValue(progressBar1.getValue() + 1);
    }

    public void zmniejsz() {
        progressBar1.setValue(progressBar1.getValue() - 1);
    }

}
