package com.itstudy.code;

import lombok.Data;

import java.util.Random;

/**
 * Description: RandomCode
 * <br></br>
 * className: RandomCode
 * <br></br>
 * packageName: com.itstudy.code
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/9/2 20:48
 */
@Data
public class RandomCode {
    private static final Random random = new Random();

    private RandomCode() {
    }

    public static String getRandomCode() {
        int i = random.nextInt(90000) + 10000;
        long l = System.currentTimeMillis();
        return l + "-" + i + ".";
    }
}
