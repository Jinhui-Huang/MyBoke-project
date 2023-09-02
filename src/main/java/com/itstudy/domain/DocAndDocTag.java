package com.itstudy.domain;

import lombok.Data;

/**
 * Description: DocAndDocTag
 * <br></br>
 * className: DocAndDocTag
 * <br></br>
 * packageName: com.itstudy.domain
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/9/2 18:20
 */
@Data
public class DocAndDocTag {
    private Doc doc;
    private String[] docTags;
}
