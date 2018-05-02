package pakiet;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private JFrame frame;
    private JPanel Panel1;
    private JProgressBar progressBar1;
    private JLabel lab;


    String strona;

    public Main(String title, String strona) throws HeadlessException {

        this.strona = strona;

        frame = new JFrame(title);
        frame.setContentPane(Panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        init();
        Client client = new Client(progressBar1, lab, frame, strona);
        Thread thread = new Thread(client);
        thread.start();
        System.out.println(strona);
    }

    public static void main(String[] args) {
        new ktora_strona("wybor stron");
        //    new Main("Przeciaganie liny");
    }

    public void init() {

        progressBar1.setMinimum(0);
        progressBar1.setMaximum(100);
        progressBar1.setValue(50);

    }
}