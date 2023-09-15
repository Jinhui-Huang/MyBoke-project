package com.itstudy.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Description: UserCollection
 * <br></br>
 * className: UserCollection
 * <br></br>
 * packageName: com.itstudy.domain
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/9/3 20:19
 */
@Data
public class UserCollection {
    private String userEmail;
    private Integer docId;
    private Boolean enabledSee;
    private Integer clicks;
    private LocalDateTime clickDateTime;
    private LocalDateTime collectionDateTime;
}
