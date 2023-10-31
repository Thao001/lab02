import java.util.ArrayList;
import java.util.Random;

public class SAES {

    //D.2.1 密钥加     s1:状态矩阵 s2:密钥
    static String[] addKey(String[] s1, String[] s2) {
        String[] result = new String[4];
        for (int i = 0; i < 4; i++) {
            int s1Int = Integer.parseInt(s1[i], 2);
            int s2Int = Integer.parseInt(s2[i], 2);
            int xorResult = s1Int ^ s2Int;
            result[i] = convertTo4BitBinaryString(xorResult);
        }
        return result;
    }

    // 辅助函数：将整数转换为4位的二进制字符串
    private static String convertTo4BitBinaryString(int value) {
        return String.format("%04d", Integer.parseInt(Integer.toBinaryString(value)));
    }

    //D.2.2 半字节代替
    //S盒
    static String SBox[][] = {
            {"1001", "0100", "1010", "1011"},
            {"1101", "0001", "1000", "0101"},
            {"0110", "0010", "0000", "0011"},
            {"1100", "1110", "1111", "0111"}
    };

    static String[] NS(String[] S) {
        String[] result = new String[4];
        for (int i = 0; i < 4; i++) {
            int x = binaryStringToUnsignedInt(S[i].substring(0, 2));
            int y = binaryStringToUnsignedInt(S[i].substring(2, 4));
            result[i] = SBox[x][y];
        }
        return result;
    }

    // 辅助函数：将二进制字符串转换为无符号整数
    private static int binaryStringToUnsignedInt(String binaryString) {
        return Integer.parseUnsignedInt(binaryString, 2);
    }


    //逆 半字节替代
    //逆 S盒
    static String _SBox[][] = {
            {"1010", "0101", "1001", "1011"},
            {"0001", "0111", "1000", "1111"},
            {"0110", "0000", "0010", "0011"},
            {"1100", "0100", "1101", "1110"}
    };

    static String[] _NS(String[] S) {
        String[] result = new String[4];
        for (int i = 0; i < 4; i++) {
            int x = Integer.parseInt(S[i].substring(0, 2), 2);
            int y = Integer.parseInt(S[i].substring(2, 4), 2);
            result[i] = _SBox[x][y];
        }
        return result;
    }


    //D.2.3 行位移(逆 行位移)
    static String[] SR(String[] S) {
        String[] result = {S[0], S[3], S[2], S[1]};
        return result;
    }


    //D.2.4 列混淆
    static String[][] GFmlp = {
            {"0000", "0000", "0000", "0000", "0000", "0000", "0000", "0000", "0000", "0000", "0000", "0000", "0000", "0000", "0000", "0000"},
            {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"},
            {"0000", "0010", "0100", "0110", "1000", "1010", "1100", "1110", "0011", "0001", "0111", "0101", "1011", "1001", "1111", "1101"},
            {"0000", "0011", "0110", "0101", "1100", "1111", "1010", "1001", "1011", "1000", "1101", "1110", "0111", "0100", "0001", "0010"},
            {"0000", "0100", "1000", "1100", "0011", "0111", "1011", "1111", "0110", "0010", "1110", "1010", "0101", "0001", "1101", "1001"},
            {"0000", "0101", "1010", "1111", "0111", "0010", "1101", "1000", "1110", "1011", "0100", "0001", "1001", "1100", "0011", "0110"},
            {"0000", "0110", "1100", "1010", "1011", "1101", "0111", "0001", "0101", "0011", "1001", "1111", "1110", "1000", "0010", "0100"},
            {"0000", "0111", "1110", "1001", "1111", "1000", "0001", "0110", "1101", "1010", "0011", "0100", "0010", "0101", "1100", "1011"},
            {"0000", "1000", "0011", "1011", "0110", "1110", "0101", "1101", "1100", "0100", "1111", "0111", "1010", "0010", "1001", "0001"},
            {"0000", "1001", "0001", "1000", "0010", "1011", "0011", "1010", "0100", "1101", "0101", "1100", "0110", "1111", "0111", "1110"},
            {"0000", "1010", "0111", "1101", "1110", "0100", "1001", "0011", "1111", "0101", "1000", "0010", "0001", "1011", "0110", "1100"},
            {"0000", "1011", "0101", "1110", "1010", "0001", "1111", "0100", "0111", "1100", "0010", "1001", "1101", "0110", "1000", "0011"},
            {"0000", "1100", "1011", "0111", "0101", "1001", "1110", "0010", "1010", "0110", "0001", "1101", "1111", "0011", "0100", "1000"},
            {"0000", "1101", "1001", "0100", "0001", "1100", "1000", "0101", "0010", "1111", "1011", "0110", "0011", "1110", "1010", "0111"},
            {"0000", "1110", "1111", "0001", "1101", "0011", "0010", "1100", "1001", "0111", "0110", "1000", "0100", "1010", "1011", "0101"},
            {"0000", "1111", "1101", "0010", "1001", "0110", "0100", "1011", "0001", "1110", "1100", "0011", "1000", "0111", "0101", "1010"}
    };

    static String[] MC(String[] S) {
        // 将S中的二进制字符串转换为整数数组
        int[] a = {
                Integer.parseInt(S[0], 2),
                Integer.parseInt(S[1], 2),
                Integer.parseInt(S[2], 2),
                Integer.parseInt(S[3], 2)
        };

        // 初始化一个用于存储结果的新数组
        String[] result = new String[4];

        // 执行GFmlp运算和XOR操作
        result[0] = performGFmlpXOR(GFmlp[1][a[0]], GFmlp[4][a[1]]);
        result[2] = performGFmlpXOR(GFmlp[1][a[2]], GFmlp[4][a[3]]);
        result[1] = performGFmlpXOR(GFmlp[4][a[0]], GFmlp[1][a[1]]);
        result[3] = performGFmlpXOR(GFmlp[4][a[2]], GFmlp[1][a[3]]);

        // 确保结果字符串的长度为4，如果需要则用零进行填充
        for (int i = 0; i < 4; i++) {
            result[i] = padTo4Bits(result[i]);
        }

        return result;
    }

    // 辅助函数：执行GFmlp运算并返回XOR结果
    private static String performGFmlpXOR(String a, String b) {
        int intA = Integer.parseInt(a, 2);
        int intB = Integer.parseInt(b, 2);
        int xorResult = intA ^ intB;
        return Integer.toBinaryString(xorResult);
    }

    // 辅助函数：确保字符串的长度为4，如果需要则用零进行填充
    private static String padTo4Bits(String str) {
        while (str.length() < 4) {
            str = "0" + str;
        }
        return str;
    }

    //逆列混淆
    static String[] _MC(String[] S) {
        // 将输入字符串数组解析为整数数组
        int[] a = parseBinaryStringsToIntArray(S);

        // 执行特定的操作并存储结果
        String[] s = computeAndStoreResults(a);

        return s;
    }

    // 辅助函数：将二进制字符串数组解析为整数数组
    private static int[] parseBinaryStringsToIntArray(String[] binaryStrings) {
        int[] result = new int[binaryStrings.length];

        for (int i = 0; i < binaryStrings.length; i++) {
            result[i] = Integer.parseInt(binaryStrings[i], 2);
        }

        return result;
    }

    // 辅助函数：执行特定的操作并存储结果
    private static String[] computeAndStoreResults(int[] a) {
        String[] s = new String[4];

        s[0] = Integer.toBinaryString(Integer.parseInt(GFmlp[9][a[0]], 2) ^ Integer.parseInt(GFmlp[2][a[1]], 2));
        s[2] = Integer.toBinaryString(Integer.parseInt(GFmlp[9][a[2]], 2) ^ Integer.parseInt(GFmlp[2][a[3]], 2));
        s[1] = Integer.toBinaryString(Integer.parseInt(GFmlp[2][a[0]], 2) ^ Integer.parseInt(GFmlp[9][a[1]], 2));
        s[3] = Integer.toBinaryString(Integer.parseInt(GFmlp[2][a[2]], 2) ^ Integer.parseInt(GFmlp[9][a[3]], 2));

        // 确保每个结果都有4位
        for (int i = 0; i < 4; i++) {
            while (s[i].length() < 4) {
                s[i] = "0" + s[i];
            }
        }

        return s;
    }



    //D3 密钥扩展
    //  RCON
    static String[] RC = {"10000000", "00110000"};

    static String[] keyExpend(String key) {
        String[] w = new String[6];

        w[0] = key.substring(0, 8);
        w[1] = key.substring(8, 16);
        w[2] = Integer.toBinaryString(
                Integer.parseInt(w[0], 2) ^
                        Integer.parseInt(RC[0], 2) ^
                        Integer.parseInt(
                                SBox[Integer.parseInt(w[1].substring(4, 6), 2)][Integer.parseInt(w[1].substring(6, 8), 2)] +
                                        SBox[Integer.parseInt(w[1].substring(0, 2), 2)][Integer.parseInt(w[1].substring(2, 4), 2)]
                                , 2));
        w[3] = Integer.toBinaryString(Integer.parseInt(w[2], 2) ^ Integer.parseInt(w[1], 2));
        for (int i = 2; i < 4; i++) {
            while (w[i].length() < 8) {
                w[i] = "0" + w[i];
            }
        }
        w[4] = Integer.toBinaryString(
                Integer.parseInt(w[2], 2) ^
                        Integer.parseInt(RC[1], 2) ^
                        Integer.parseInt(
                                SBox[Integer.parseInt(w[3].substring(4, 6), 2)][Integer.parseInt(w[3].substring(6, 8), 2)] +
                                        SBox[Integer.parseInt(w[3].substring(0, 2), 2)][Integer.parseInt(w[3].substring(2, 4), 2)]
                                , 2));
        w[5] = Integer.toBinaryString(Integer.parseInt(w[4], 2) ^ Integer.parseInt(w[3], 2));
        for (int i = 2; i < 6; i++) {
            while (w[i].length() < 8) {
                w[i] = "0" + w[i];
            }
        }
        return w;
    }

    static String encrypt(String plaintext, String key) {
        //密钥扩展
        String[] Key01 = {keyExpend(key)[0].substring(0, 4),
                keyExpend(key)[0].substring(4, 8),
                keyExpend(key)[1].substring(0, 4),
                keyExpend(key)[1].substring(4, 8),};
        String[] Key23 = {keyExpend(key)[2].substring(0, 4),
                keyExpend(key)[2].substring(4, 8),
                keyExpend(key)[3].substring(0, 4),
                keyExpend(key)[3].substring(4, 8),};
        String[] Key45 = {keyExpend(key)[4].substring(0, 4),
                keyExpend(key)[4].substring(4, 8),
                keyExpend(key)[5].substring(0, 4),
                keyExpend(key)[5].substring(4, 8),};

        //将plaintext转化成数组
        String[] plainText = {
                plaintext.substring(0, 4),
                plaintext.substring(4, 8),
                plaintext.substring(8, 12),
                plaintext.substring(12, 16)
        };

        String[] ciphertext = addKey(SR(NS(addKey(MC(SR(NS(addKey(plainText, Key01)))), Key23))), Key45);

        return ciphertext[0] + ciphertext[1] + ciphertext[2] + ciphertext[3];
    }

    public static String decode(String ciphertext, String key) {
        //密钥扩展
        String[] Key01 = {keyExpend(key)[0].substring(0, 4),
                keyExpend(key)[0].substring(4, 8),
                keyExpend(key)[1].substring(0, 4),
                keyExpend(key)[1].substring(4, 8),};
        String[] Key23 = {keyExpend(key)[2].substring(0, 4),
                keyExpend(key)[2].substring(4, 8),
                keyExpend(key)[3].substring(0, 4),
                keyExpend(key)[3].substring(4, 8),};
        String[] Key45 = {keyExpend(key)[4].substring(0, 4),
                keyExpend(key)[4].substring(4, 8),
                keyExpend(key)[5].substring(0, 4),
                keyExpend(key)[5].substring(4, 8),};
        //密文数组
        String[] cipherText = {
                ciphertext.substring(0, 4),
                ciphertext.substring(4, 8),
                ciphertext.substring(8, 12),
                ciphertext.substring(12, 16)
        };
        String[] plaintext = addKey(_NS(SR(_MC(addKey(_NS(SR(addKey(cipherText, Key45))), Key23)))), Key01);
        return plaintext[0] + plaintext[1] + plaintext[2] + plaintext[3];
    }

    //加密字符串
    static String encryptString(String plainText, String key) {
        String ciphertext = "";                       //密文
        String binaryText = "";                       //单个字符的二进制表示
        String cipherBinaryText = "";                 //加密后的二进制字符串
        String plainBinaryType = "[0*1*]*[1*0*]*";    //二进制类型的明文
        //如果明文是二进制类型
        if (plainText.matches(plainBinaryType)) {
            while ((plainText.length() % 16) != 0) {       //不是16的整数，左补零
                plainText = "0" + plainText;
            }
            for (int i = 0; i < plainText.length() / 16; i++) {
                ciphertext += encrypt(plainText.substring(0 + 16 * i, 16 + 16 * i), key);
            }
        }
        //如果明文是字符串类型
        else {
            char[] strChar = plainText.toCharArray();     //将字符串表示为二进制字符串,对每一个二进制字符串加密
            for (int i = 0; i < strChar.length; i++) {
                binaryText = Integer.toBinaryString(strChar[i]);

                while (binaryText.length() < 16) {
                    binaryText = "0" + binaryText;      //小于16位，左补0
                }
                //将加密后的二进制字符串转换成字符，然后拼接成明文
                cipherBinaryText = encrypt(binaryText, key);
                ciphertext += Character.toString((char) Integer.parseInt(cipherBinaryText, 2));
            }
        }
        return ciphertext;
    }

    //解密字符串
    static String decodeString(String cipherText, String key) {
        String plaintext = "";                        //明文
        String binaryText = "";                       //单个字符的二进制表示
        String plainBinaryText = "";                  //解密后的二进制字符串
        String cipherBinaryType = "[0*1*]*[1*0*]*";   //二进制类型的密文

        //如果密文是二进制类型
        if (cipherText.matches(cipherBinaryType)) {
            while ((cipherText.length() % 16) != 0) {       //不是16的整数，左补零
                cipherText = "0" + cipherText;
            }
            for (int i = 0; i < cipherText.length() / 16; i++) {
                plaintext += decode(cipherText.substring(0 + 16 * i, 16 + 16 * i), key);
            }
        }
        //如果密文是字符串类型
        else {
            char[] strChar = cipherText.toCharArray();     //将字符串表示为二进制字符串,对每一个二进制字符串解密
            for (int i = 0; i < strChar.length; i++) {
                binaryText = Integer.toBinaryString(strChar[i]);

                while (binaryText.length() < 16) {
                    binaryText = "0" + binaryText;      //小于16位，左补0
                }
                //将解密后的二进制字符串转换成字符
                plainBinaryText = decode(binaryText, key);
                plaintext += Character.toString((char) Integer.parseInt(plainBinaryText, 2));
            }
        }
        return plaintext;
    }
    //双重加密
    static String doubleEncrypt(String plainText, String key1, String key2) {
        return encryptString(encryptString(plainText, key1), key2);
    }

    //双重解密
    static String doubleDecode(String cipherText, String key1, String key2) {
        return decodeString(decodeString(cipherText, key2), key1);
    }
    //中间相遇攻击
    static ArrayList Meet_in_the_middle_attack(String plainText, String cipherText) {

        //密钥一 密钥二
        String key1 = "";
        String key2 = "";
        //加密后的文本
        String[] plaintextArr = new String[65536];
        //解密后的文本
        String[] ciphertextArr = new String[65536];
        //密钥对个数
        ArrayList key = new ArrayList();

        for (int i = 0; i < 65536; i++) {
            key1 = Integer.toBinaryString(i);
            while (key1.length() < 16) {               //不够16位向左补0
                key1 = "0" + key1;
            }
            plaintextArr[i] = encryptString(plainText, key1);
        }

        for (int j = 0; j < 65536; j++) {
            key2 = Integer.toBinaryString(j);
            while (key2.length() < 16) {               //不够16位向左补0
                key2 = "0" + key2;
            }
            ciphertextArr[j] = decodeString(cipherText, key2);
        }
        for (int i = 0; i < 65536; i++) {
            for (int j = 0; j < 65536; j++) {
                if (plaintextArr[i].equals(ciphertextArr[j])) {
                    String k1 = Integer.toBinaryString(i);
                    String k2 = Integer.toBinaryString(j);
                    while (k1.length() < 16) {
                        k1 = "0" + k1;
                    }
                    while (k2.length() < 16) {
                        k2 = "0" + k2;
                    }
                    key.add(k1 + "+" + k2);
                }
            }
        }
        return key;
    }

    //三重加密
    static String tripleEncrypt(String plainText, String key1, String key2) {
        return encryptString(decodeString(encryptString(plainText, key1), key2), key1);
    }

    //Key(k1+k2+k3) 模式
    static String tripleEncrypt(String plainText, String key1, String key2, String key3) {
        return encryptString(encryptString(encryptString(plainText, key1), key2), key3);
    }

    //三重解密
    static String tripleDecode(String cipherText, String key1, String key2) {
        return decodeString(encryptString(decodeString(cipherText, key1), key2), key1);
    }

    //Key(k1+k2+k3) 模式
    static String tripleDecode(String cipherText, String key1, String key2, String key3) {
        return decodeString(decodeString(decodeString(cipherText, key3), key2), key1);
    }

    //生成初始向量
    static String generateVI() {
        Random r = new Random();
        String VI = Integer.toBinaryString(r.nextInt(65536));
        while (VI.length() < 16) {
            VI = "0" + VI;
        }
        System.out.println(VI);
        return VI;
    }
    //CBC加密
    static String CBC_Encrypt(String plainText, String key, String IV) {
        String ciphertext = "";                       //密文
        String binaryText = "";                       //单个字符的二进制表示
        String plainBinaryType = "[0*1*]*[1*0*]*";    //二进制类型的明文
        //如果明文是二进制类型
        if (plainText.matches(plainBinaryType)) {
            while ((plainText.length() % 16) != 0) {       //不是16的整数，左补零
                plainText = "0" + plainText;
            }
            //处理第一个分组
            String segment = plainText.substring(0, 16);
            String res = Integer.toBinaryString(Integer.parseInt(segment, 2) ^ Integer.parseInt(IV, 2));
            while (res.length() < 16) {
                res = "0" + res;
            }
            String temp = encrypt(res, key);
            ciphertext = ciphertext + temp;
            //处理后面的分组
            for (int i = 1; i < plainText.length() / 16; i++) {
                segment = plainText.substring(0 + 16 * i, 16 + 16 * i);
                res = Integer.toBinaryString(Integer.parseInt(segment, 2) ^ Integer.parseInt(temp, 2));
                while (res.length() < 16) {
                    res = "0" + res;
                }
                temp = encrypt(res, key);
                ciphertext = ciphertext + temp;
            }
        }
        //如果明文是字符串类型
        else {
            char[] strChar = plainText.toCharArray();     //将字符串表示为二进制字符串,对每一个二进制字符串加密
            //处理第一个分组
            binaryText = Integer.toBinaryString(strChar[0]);
            while (binaryText.length() < 16) {
                binaryText = "0" + binaryText;            //小于16位，左补0
            }
            String res = Integer.toBinaryString(Integer.parseInt(binaryText, 2) ^ Integer.parseInt(IV, 2));
            while (res.length() < 16) {
                res = "0" + res;
            }
            String temp = encrypt(res, key);
            ciphertext += Character.toString((char) Integer.parseInt(temp, 2));
            //处理后面的分组
            for (int i = 1; i < strChar.length; i++) {
                binaryText = Integer.toBinaryString(strChar[i]);
                while (binaryText.length() < 16) {
                    binaryText = "0" + binaryText;      //小于16位，左补0
                }
                res = Integer.toBinaryString(Integer.parseInt(binaryText, 2) ^ Integer.parseInt(temp, 2));
                while (res.length() < 16) {
                    res = "0" + res;
                }
                temp = encrypt(res, key);
                ciphertext += Character.toString((char) Integer.parseInt(temp, 2));
            }
        }
        return ciphertext;
    }
    //CBC解密
    static String CBC_Decode(String cipherText, String key, String IV) {
        String plaintext = "";                        //明文
        String binaryText = "";                       //单个字符的二进制表示
        String plainBinaryText = "";                  //解密后的二进制字符串
        String cipherBinaryType = "[0*1*]*[1*0*]*";   //二进制类型的密文
        //如果密文是二进制类型
        if (cipherText.matches(cipherBinaryType)) {
            while ((cipherText.length() % 16) != 0) {       //不是16的整数，左补零
                cipherText = "0" + cipherText;
            }
            //处理第一个分组
            String segment = cipherText.substring(0, 16);
            String temp = segment;
            String cipherRes = decode(segment, key);     //解密结果
            String res = Integer.toBinaryString(Integer.parseInt(cipherRes, 2) ^ Integer.parseInt(IV, 2));  //相与结果
            while (res.length() < 16) {
                res = "0" + res;
            }
            plaintext += res;
            //处理后面的分组
            for (int i = 1; i < cipherText.length() / 16; i++) {
                segment += decode(cipherText.substring(0 + 16 * i, 16 + 16 * i), key);
                cipherRes = decode(segment, key);
                res = Integer.toBinaryString(Integer.parseInt(cipherRes, 2) ^ Integer.parseInt(temp, 2));  //相与结果
                while (res.length() < 16) {
                    res = "0" + res;
                }
                plaintext += res;
                temp = segment;
            }
        }
        //如果密文是字符串类型
        else {
            char[] strChar = cipherText.toCharArray();     //将字符串表示为二进制字符串,对每一个二进制字符串解密
            //处理第一个分组
            binaryText = Integer.toBinaryString(strChar[0]);
            while (binaryText.length() < 16) {
                binaryText = "0" + binaryText;            //小于16位，左补0
            }
            String temp = binaryText;
            String cipherRes = decode(binaryText, key);     //解密结果
            String res = Integer.toBinaryString(Integer.parseInt(cipherRes, 2) ^ Integer.parseInt(IV, 2));  //相与结果
            while (res.length() < 16) {
                res = "0" + res;
            }
            plaintext += Character.toString((char) Integer.parseInt(res, 2));

            for (int i = 1; i < strChar.length; i++) {
                // 处理每个字符
                char currentChar = strChar[i];

                // 将字符转化为16位二进制字符串
                String currentBinaryText = String.format("%16s", Integer.toBinaryString(currentChar)).replace(' ', '0');

                // 解密
                String cipherRe = decode(currentBinaryText, key);

                // 异或操作
                String re = Integer.toBinaryString(Integer.parseInt(cipherRe, 2) ^ Integer.parseInt(temp, 2));

                // 补足为16位二进制字符串
                re = String.format("%16s", re).replace(' ', '0');

                // 将解密后的二进制字符串转换成字符
                plaintext += (char) Integer.parseInt(re, 2);

                // 更新temp
                temp = currentBinaryText;
            }

            //将解密后的二进制字符串转换成字符
                plaintext += Character.toString((char) Integer.parseInt(res, 2));
                temp = binaryText;
            }
        return plaintext;
    }

    }
