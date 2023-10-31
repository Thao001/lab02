import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class tripleEncryption extends JFrame {

    JLabel lb13 = new JLabel();
    JLabel lb23 = new JLabel();
    JLabel lb33 = new JLabel();
    JLabel lb43 = new JLabel();
    JLabel lb53 = new JLabel("密钥2");
    JLabel lb533 = new JLabel("密钥3");
    JLabel message3 = new JLabel();
    JTextField input3 = new JTextField();
    JTextField keyfield3 = new JTextField();
    JTextField keyfield33 = new JTextField();
    JTextField keyfield333 = new JTextField();
    JTextField encry_output3 = new JTextField();
    JTextField decry_output3 = new JTextField();
    JButton encry_btn3 = new JButton("Encrypt");
    JButton decry_btn3 = new JButton("Decrypt");
    JSeparator line13 = new JSeparator(JSeparator.HORIZONTAL);

    public tripleEncryption() {
        setVisible(true);
        maininit();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void maininit() {
        this.setTitle("S-AES");
        this.setSize(800, 600);
        // 设置常驻屏幕中央
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screen.getWidth();
        int screenHeight = (int) screen.getHeight();
        int frameWidth = this.getWidth();
        int frameHeight = this.getHeight();
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;
        this.setLocation(x, y);

        getContentPane().setBackground(new Color(173, 216, 230));

        this.setLayout(null); // 使用绝对布局

        encry_btn3.setBackground(Color.blue);
        encry_btn3.setForeground(Color.white);
        decry_btn3.setBackground(Color.red);
        decry_btn3.setForeground(Color.white);

        lb13.setText("Enter the plaintext or the ciphertext:");
        lb23.setText("Enter binary key 1:");
        lb53.setText("Enter binary key 2:");
        lb533.setText("Enter binary key 3:");
        lb33.setText("Encryption result:");
        lb43.setText("Decryption result:");


        // 组件布局
        lb13.setBounds(50, 40, 250, 50);
        lb23.setBounds(50, 100, 200, 50);
        lb53.setBounds(50, 150, 200, 50);
        lb533.setBounds(50, 200, 200, 50);
        lb33.setBounds(50, 350, 100, 50);
        lb43.setBounds(50, 400, 100, 50);
        message3.setBounds(50, 480, 100, 50);
        input3.setBounds(300, 40, 250, 40);
        keyfield3.setBounds(300, 100, 250, 40);
        keyfield33.setBounds(300, 150, 250, 40);
        keyfield333.setBounds(300, 200, 250, 40);
        encry_output3.setBounds(300, 350, 250, 40);
        decry_output3.setBounds(300, 400, 250, 40);
        encry_btn3.setBounds(50, 270, 140, 30);
        decry_btn3.setBounds(200, 270, 140, 30);
        line13.setBounds(0, 310, 800, 2);

        // 组件添加到 JFrame
        this.add(lb13);
        this.add(lb23);
        this.add(lb33);
        this.add(lb43);
        this.add(lb53);
        this.add(lb533);
        this.add(message3);
        this.add(input3);
        this.add(keyfield3);
        this.add(keyfield33);
        this.add(keyfield333);
        this.add(encry_output3);
        this.add(decry_output3);
        this.add(decry_btn3);
        this.add(encry_btn3);


        //1.S-ASE
        encry_btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 实现A-DES加密的代码
                if (input3.getText().trim().equals("")){
                    message3.setText("<html><font color='red'>请输入明文!</font> </html>");

                } else if (keyfield3.getText().trim().equals("")) {
                    message3.setText("<html><font color='red'>请输入密钥1!</font> </html>");}
                else if (keyfield33.getText().trim().equals("")) {
                    message3.setText("<html><font color='red'>请输入密钥2!</font> </html>");}
                else {
                    String plaintext = input3.getText();
                    String key1 = keyfield3.getText();
                    String key2=keyfield33.getText();
                    String key3=keyfield333.getText();
                    if(keyfield333.getText().trim().equals("")) {
                        String ciphertext = SAES.tripleEncrypt(plaintext, key1, key2);
                        encry_output3.setText(ciphertext);
                    } else if (keyfield333.getText().length()==16) {
                        String ciphertext=SAES.tripleEncrypt(plaintext,key1,key2,key3);
                        encry_output3.setText( ciphertext);
                    }
                    else {
                        message3.setText("<html><font color='red'>请输入正确的密钥3!</font> </html>");
                    }
                }
            }
        });

        decry_btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //三重加密的SAES分为两种
                String ciphertext = input3.getText();
                String key1 = keyfield3.getText();
                String key2=keyfield33.getText();
                String key3=keyfield333.getText();
                if(key3.trim().equals("")) {
                    String plaintext = SAES.tripleDecode(ciphertext, key1, key2);
                    decry_output3.setText( plaintext );
                }
                else {
                    String plaintext = SAES.tripleDecode(ciphertext, key1, key2,key3);
                    decry_output3.setText(plaintext );
                }

            }
        });

    }

    public static void main(String[] args) {
        tripleEncryption ui = new tripleEncryption();
    }
}
