import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class normalEncryption extends JFrame {
    private JLabel lb1 = new JLabel();
    private JLabel lb2 = new JLabel();
    private JLabel lb3 = new JLabel();
    private JLabel lb4 = new JLabel();
    private JLabel message = new JLabel();
    private JLabel background;

    private JTextField input = new JTextField();
    private JTextField keyfield = new JTextField();
    private JTextField encry_output = new JTextField();
    private JTextField decry_output = new JTextField();
    private JButton encry_btn = new JButton("Encrypt");
    private JButton decry_btn = new JButton("Decrypt");
    private JSeparator line1 = new JSeparator(JSeparator.HORIZONTAL);

    public normalEncryption() {
        setTitle("S-AES");
        setSize(800, 600);

        setLayout(null);

        lb1.setText("Enter the plaintext or ciphertext:");
        lb2.setText("Enter the binary key:");
        lb3.setText("Encryption result:");
        lb4.setText("Decryption result:");

        lb1.setBounds(50, 40, 250, 50);
        input.setBounds(50, 75, 250, 40);
        lb2.setBounds(50, 100, 250, 50);
        keyfield.setBounds(50, 135, 250, 40);
        encry_btn.setBounds(50, 180, 140, 30);
        decry_btn.setBounds(200, 180, 140, 30);
        lb3.setBounds(50, 210, 250, 50);
        lb4.setBounds(50, 270, 250, 50);
        encry_output.setBounds(50, 245, 250, 40);
        decry_output.setBounds(50, 305, 250, 40);

        add(lb1);
        add(lb2);
        add(lb3);
        add(lb4);
        add(message);
        add(input);
        add(keyfield);
        add(encry_output);
        add(decry_output);
        add(decry_btn);
        add(encry_btn);


        // 设置背景色和字体
        getContentPane().setBackground(new Color(173, 216, 230)); // 浅蓝色背景
        Font labelFont = new Font("Arial", Font.PLAIN, 16);
        lb1.setFont(labelFont);
        lb2.setFont(labelFont);
        lb3.setFont(labelFont);
        lb4.setFont(labelFont);
        message.setFont(labelFont);

        input.setFont(labelFont);
        keyfield.setFont(labelFont);
        encry_output.setFont(labelFont);
        decry_output.setFont(labelFont);

        // 设置按钮的颜色
        encry_btn.setBackground(Color.BLUE);
        encry_btn.setForeground(Color.WHITE);
        decry_btn.setBackground(Color.RED);
        decry_btn.setForeground(Color.WHITE);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        //1.S-ASE
        encry_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 实现A-DES加密的代码
                if (input.getText().trim().equals("")) {
                    message.setText("<html><font color='red'>请输入明文!</font> </html>");

                } else if (keyfield.getText().trim().equals("")) {
                    message.setText("<html><font color='red'>请输入密钥!</font> </html>");

                } else {
                    String plaintext = input.getText();
                    String key = keyfield.getText();
                    String ciphertext = SAES.encryptString(plaintext, key);
                    encry_output.setText(ciphertext);
                }
            }
        });

        decry_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 实现A-DES解密的代码
                String ciphertext = input.getText();
                String key = keyfield.getText();
                String plaintext = SAES.decodeString(ciphertext, key);

                decry_output.setText(plaintext);
            }
        });

    }

    public static void main(String[] args) {
        normalEncryption ui=new normalEncryption();

    }
}