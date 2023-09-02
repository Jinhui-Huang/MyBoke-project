package com.itstudy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Description: Doc
 * <br></br>
 * className: Doc
 * <br></br>
 * packageName: com.itstudy.domain
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/9/1 11:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doc {
    private Integer docId;
    private String userEmail;
    private String docContext;
    private String docTitle;
    private String docDescription;
    private LocalDateTime docDateTime;
}
