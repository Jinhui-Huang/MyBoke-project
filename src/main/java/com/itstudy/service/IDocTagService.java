package com.itstudy.service;

import com.itstudy.domain.DocTag;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description: IDocTagService
 * <br></br>
 * className: IDocTagService
 * <br></br>
 * packageName: com.itstudy.service
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/9/1 16:30
 */
@Transactional
public interface IDocTagService {
    List<DocTag> selectDocTags(Integer docId);

    Boolean insertDocTags(List<DocTag> docTags);

    Boolean deleteDocTagByDocId(Integer docId);

    Boolean deleteDocTagByTagId(List<DocTag> docTags);

    Boolean updateDocTagByTagId(List<DocTag> docTags);
}
