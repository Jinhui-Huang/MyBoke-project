package com.itstudy.dao;

import com.itstudy.domain.DocTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Description: DocTagDao
 * <br></br>
 * className: DocTagDao
 * <br></br>
 * packageName: com.itstudy.dao
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/9/1 16:27
 */
@Mapper
public interface DocTagDao {
    @Select("select tag_id, tag_name, doc_id from doc_tag where doc_id = #{docId}")
    List<DocTag> selectDocTagByDocId(Integer docId);
}
