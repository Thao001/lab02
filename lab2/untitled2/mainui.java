import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainui extends JFrame {

    JLabel lb14 = new JLabel();
    JLabel lb24 = new JLabel();
    JLabel lb34 = new JLabel();
    JLabel lb44 = new JLabel();
    JLabel lb54 = new JLabel();
    JLabel message4 = new JLabel();
    JTextField input4 = new JTextField();
    JTextField keyfield4 = new JTextField();
    JTextField keyfield44 = new JTextField();
    JTextField encry_output4 = new JTextField();
    JTextField decry_output4 = new JTextField();
    JButton encry_btn4 = new JButton("加密");
    JButton decry_btn4 = new JButton("解密");

    public mainui() {
        setVisible(true);
        mainInit();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void mainInit() {
        this.setTitle("S-AES");
        this.setSize(800, 600);
        // Set the frame to be centered on the screen
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screen.getWidth();
        int screenHeight = (int) screen.getHeight();
        int frameWidth = this.getWidth();
        int frameHeight = this.getHeight();
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;
        this.setLocation(x, y);

        this.setLayout(null); // Set the layout to null so you can position components manually
        getContentPane().setBackground(new Color(173, 216, 230));

        lb14.setText("请输入你要加密的明文或要解密的密文：");
        lb24.setText("请输入二进制密钥:");
        lb54.setText("随机数IV:");
        lb34.setText("加密结果：");
        lb44.setText("解密结果：");
        encry_btn4.setBackground(Color.red);
        encry_btn4.setForeground(Color.white);
        decry_btn4.setBackground(Color.red);
        decry_btn4.setForeground(Color.white);

        lb14.setBounds(50, 40, 250, 50);
        lb24.setBounds(50, 100, 200, 50);
        lb54.setBounds(50, 150, 200, 50);
        lb34.setBounds(50, 300, 100, 50);
        lb44.setBounds(50, 350, 100, 50);
        message4.setBounds(50, 400, 100, 50);
        input4.setBounds(300, 40, 250, 40);
        keyfield4.setBounds(300, 100, 250, 40);
        keyfield44.setBounds(300, 150, 250, 40);
        encry_output4.setBounds(300, 300, 250, 40);
        decry_output4.setBounds(300, 350, 250, 40);
        encry_btn4.setBounds(80, 250, 70, 30);
        decry_btn4.setBounds(300, 250, 70, 30);

        // Add components to the frame
        add(lb14);
        add(lb24);
        add(lb34);
        add(lb44);
        add(lb54);
        add(message4);
        add(input4);
        add(keyfield4);
        add(keyfield44);
        add(encry_output4);
        add(decry_output4);
        add(decry_btn4);
        add(encry_btn4);

        encry_btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (input4.getText().trim().equals("")) {
                    message4.setText("<html><font color='red'>请输入明文!</font> </html>");
                } else if (keyfield4.getText().trim().equals("")) {
                    message4.setText("<html><font color='red'>请输入密钥1!</font> </html>");
                } else if (keyfield44.getText().trim().equals("")) {
                    message4.setText("<html><font color='red'>请输入IV!</font> </html>");
                } else {
                    String plaintext = input4.getText();
                    String key1 = keyfield4.getText();
                    String four = keyfield44.getText();
                    String ciphertext = SAES.CBC_Encrypt(plaintext, key1, four);
                    encry_output4.setText(ciphertext);
                }
            }
        });
        decry_btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement the CBC-A-DES decryption code
                String ciphertext = input4.getText();
                String key1 = keyfield4.getText();
                String four = keyfield44.getText();
                String plaintext = SAES.CBC_Decode(ciphertext, key1, four);
                decry_output4.setText(plaintext);
            }
        });
    }

    public static void main(String[] args) {
        mainui ui = new mainui();
    }
}
