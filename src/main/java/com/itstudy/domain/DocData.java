package com.itstudy.domain;

import lombok.Data;

/**
 * Description: DocData
 * <br></br>
 * className: DocData
 * <br></br>
 * packageName: com.itstudy.domain
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/9/6 09:40
 */
@Data
public class DocData {
    private String userEmail;
    private Integer docId;
    private String docTitle;
    private String docDescription;
    private Integer docSees;
    private Integer clicks;
    private Integer collections;
}
