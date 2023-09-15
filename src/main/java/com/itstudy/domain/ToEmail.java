package com.itstudy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description: ToEmail
 * <br></br>
 * className: ToEmail
 * <br></br>
 * packageName: com.itstudy.domain
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/9/6 20:25
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToEmail implements Serializable {
    private String toUser;
    private String subject;
    private String content;
}
