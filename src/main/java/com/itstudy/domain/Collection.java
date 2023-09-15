package com.itstudy.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Description: Collection
 * <br></br>
 * className: Collection
 * <br></br>
 * packageName: com.itstudy.domain
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/9/5 14:09
 */
@Data
public class Collection {
    private String userName;
    private String userEmail;
    private String userImg;
    private Integer docId;
    private String docTitle;
    private String docDescription;
    private Integer docSees;
    private LocalDateTime docDateTime;
    private LocalDateTime collectionDateTime;
}
