package pakiet;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ktora_strona {

    private JFrame frame;
    private JPanel Panel;
    private JButton lewyButton;
    private JButton prawyButton;

    public ktora_strona(String title) {
        frame = new JFrame(title);
        frame.setContentPane(Panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        lewyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Main("Przeciaganie liny", "lewy");
                frame.setVisible(false);
            }
        });
        prawyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Main("Przeciaganie liny", "prawy");
                frame.setVisible(false);
            }
        });
    }
}
