package com.itstudy.code;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Description: VerCodeGenerateUtil
 * <br></br>
 * className: VerCodeGenerateUtil
 * <br></br>
 * packageName: com.itstudy.code
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/9/6 20:26
 */
public class VerCodeGenerateUtil {
    private static final String VER_CODE = "0ABC1DE2FG3HI4JK5LM6NO7PQ8RS9TUVWXYZ";
    private static final Random RANDOM = new SecureRandom();

    private VerCodeGenerateUtil(){}
    public static String generateVerCode() {
        char[] code = new char[6];
        for (int i = 0; i < code.length; i++) {
            code[i] = VER_CODE.charAt(RANDOM.nextInt(VER_CODE.length()));
        }
        return new String(code);
    }
}
