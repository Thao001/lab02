import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class doubleEncrypt extends JFrame {
    private JLabel lb12 = new JLabel("Enter the plain text to encrypt or the ciphertext to decrypt:");
    private JLabel lb22 = new JLabel("Enter binary key 1:");
    private JLabel lb32 = new JLabel("Encryption result:");
    private JLabel lb42 = new JLabel("Decryption result:");
    private JLabel lb52 = new JLabel("Key 2:");
    private JLabel message2 = new JLabel();
    private JTextField input2 = new JTextField();
    private JTextField keyfield2 = new JTextField();
    private JTextField keyfiedld22 = new JTextField();
    private JTextField encry_output2 = new JTextField();
    private JTextField decry_output2 = new JTextField();
    private JButton encry_btn2 = new JButton("Encrypt");
    private JButton decry_btn2 = new JButton("Decrypt");

    public doubleEncrypt() {
        setVisible(true);
        mainInit();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void mainInit() {
        setTitle("S-AES");
        setSize(800, 600);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screen.getWidth();
        int screenHeight = (int) screen.getHeight();
        int frameWidth = getWidth();
        int frameHeight = getHeight();
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;
        setLocation(x, y);

        // 设置背景颜色为浅蓝色
        getContentPane().setBackground(new Color(173, 216, 230));

        setLayout(null); // 使用绝对布局

        // 设置按钮背景为绿色，按钮文本颜色为红色
        encry_btn2.setBackground(Color.blue);
        encry_btn2.setForeground(Color.white);
        decry_btn2.setBackground(Color.red);
        decry_btn2.setForeground(Color.white);

        lb12.setBounds(50, 40, 250, 50);
        lb22.setBounds(50, 100, 200, 50);
        lb52.setBounds(50, 150, 200, 50);
        lb32.setBounds(50, 300, 100, 50);
        lb42.setBounds(50, 350, 100, 50);
        message2.setBounds(50, 400, 100, 50);
        input2.setBounds(300, 40, 250, 40);
        keyfield2.setBounds(300, 100, 250, 40);
        keyfiedld22.setBounds(300, 150, 250, 40);
        encry_output2.setBounds(300, 300, 250, 40);
        decry_output2.setBounds(300, 350, 250, 40);
        encry_btn2.setBounds(50, 245, 140, 30);
        decry_btn2.setBounds(200, 245, 140, 30);

        add(lb12);
        add(lb22);
        add(lb32);
        add(lb42);
        add(lb52);
        add(message2);
        add(input2);
        add(keyfield2);
        add(keyfiedld22);
        add(encry_output2);
        add(decry_output2);
        add(decry_btn2);
        add(encry_btn2);

        encry_btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (input2.getText().trim().equals("")) {
                    message2.setText("<html><font color='red'>Please enter the plain text!</font> </html>");
                } else if (keyfield2.getText().trim().equals("")) {
                    message2.setText("<html><font color='red'>Please enter key 1!</font> </html>");
                } else if (keyfiedld22.getText().trim().equals("")) {
                    message2.setText("<html><font color='red'>Please enter key 2!</font> </html>");
                } else {
                    String plaintext = input2.getText();
                    String key1 = keyfield2.getText();
                    String key2 = keyfiedld22.getText();
                    String ciphertext = SAES.doubleEncrypt(plaintext, key1, key2);
                    encry_output2.setText( ciphertext);
                }
            }
        });
        decry_btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ciphertext = input2.getText();
                String key1 = keyfield2.getText();
                String key2 = keyfiedld22.getText();
                String plaintext = SAES.doubleDecode(ciphertext, key1, key2);
                decry_output2.setText( plaintext);
            }
        });
    }

    public static void main(String[] args) {
        doubleEncrypt ui = new doubleEncrypt();
    }
}
