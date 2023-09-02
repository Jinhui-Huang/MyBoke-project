package com.itstudy.dao;

import com.itstudy.domain.DocTag;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
    /**
     * Description: selectDocTagByDocId 根据文档id查询文档标签
     * @return java.util.List<com.itstudy.domain.DocTag>
     * @author jinhui-huang
     * @Date 2023/9/2
     * */
    @Select("select tag_id, tag_name, doc_id from doc_tag where doc_id = #{docId}")
    List<DocTag> selectDocTagByDocId(Integer docId);

    /**
     * Description: insertDocTags 根据文档id插入多个文档tag
     * @return java.lang.Integer
     * @author jinhui-huang
     * @Date 2023/9/2
     * */
    @Insert("<script>" +
            "insert into doc_tag(tag_name, doc_id) values" +
            "<foreach collection = 'list' item = 'item' separator=','>" +
            "(#{item.tagName}, #{item.docId})" +
            "</foreach>" +
            "</script>")
    Integer insertDocTags(@Param(value = "list") List<DocTag> docTags);
}
