import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class attack extends JFrame {
    JLabel plaintext = new JLabel("Enter plaintext:");
    JLabel ciphertext = new JLabel("Enter ciphertext:");
    JLabel result = new JLabel("Cracking Result:");
    JLabel message5 = new JLabel("");
    JTextArea resultField = new JTextArea();
    JTextField plainField = new JTextField();
    JTextField cipherField = new JTextField();
    JButton start = new JButton("Start Cracking");

    public attack() {
        setVisible(true);
        mainInit();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void mainInit() {
        setTitle("S-AES");
        setSize(800, 600);
        getContentPane().setBackground(new Color(173, 216, 230));

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screen.getWidth();
        int screenHeight = (int) screen.getHeight();
        int frameWidth = getWidth();
        int frameHeight = getHeight();
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;
        setLocation(x, y);

        setLayout(null); // Use absolute layout

        start.setBackground(Color.red);
        start.setForeground(Color.white);

        plaintext.setBounds(50, 40, 250, 50);
        ciphertext.setBounds(50, 110, 250, 50);
        plainField.setBounds(300, 42, 250, 40);
        cipherField.setBounds(300, 112, 250, 40);
        result.setBounds(50, 300, 200, 40);
        resultField.setBounds(250, 200, 400, 300);
        start.setBounds(300, 160, 250, 30);
        message5.setBounds(50, 400, 200, 40);


        add(plaintext);
        add(ciphertext);
        add(plainField);
        add(cipherField);
        add(result);
        add(resultField);
        add(start);
        add(message5);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = 0;
                String cipher = cipherField.getText();
                String plain = plainField.getText();
                if (plain.trim().equals("")) {
                    message5.setText("<html><font color='red'>Please enter the plaintext to crack!</font> </html>");
                } else if (cipher.trim().equals("")) {
                    message5.setText("<html><font color='red'>Please enter the ciphertext to crack!</font> </html>");
                } else {
                    message5.setText("Cracking, please wait....");

                    while (resultField.getText().trim().equals("")) {
                        ArrayList keyList = SAES.Meet_in_the_middle_attack(plain, cipher);

                        for (int i = 0; i < keyList.size(); i++) {
                            resultField.setText((String) keyList.get(i) + "\n");
                            num++;
                        }
                        message5.setText("Cracking succeeded!");
                        if (num == 0) {
                            resultField.setText("No results found!");
                        }
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            attack app = new attack();
        });
    }
}
