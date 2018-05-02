package pakiet;

import javax.swing.*;

public class Bot implements Runnable {
    private JProgressBar progressBar1;
    private JLabel lab;

    public Bot(JProgressBar progressBar1, JLabel lab) {
        this.progressBar1 = progressBar1;
        this.lab = lab;
    }

    @Override
    public void run() {
        while (progressBar1.getValue() != progressBar1.getMaximum()) {
            progressBar1.setValue(progressBar1.getValue() - 1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (progressBar1.getValue() == progressBar1.getMinimum()) {
                lab.setText("Przegrales");
            } else if (progressBar1.getValue() == progressBar1.getMaximum()) {
                lab.setText("Wygrales");

            }
        }
    }
}