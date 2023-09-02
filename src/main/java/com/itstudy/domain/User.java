package com.itstudy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description: User
 * <br></br>
 * className: User
 * <br></br>
 * packageName: com.itstudy.domain
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/8/31 12:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String userEmail;
    private String userName;
    private String userPassword;
    private String userImg;
}
