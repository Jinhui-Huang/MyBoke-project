package com.itstudy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description: DocTag
 * <br></br>
 * className: DocTag
 * <br></br>
 * packageName: com.itstudy.domain
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/9/1 16:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocTag {
    private Integer tagId;
    private String tagName;
    private Integer docId;
}
