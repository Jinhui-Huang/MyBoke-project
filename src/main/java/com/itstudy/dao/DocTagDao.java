package com.itstudy.dao;

import com.itstudy.domain.DocTag;
import org.apache.ibatis.annotations.*;

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
            "insert into doc_tag(tag_name, doc_id)  values" +
            "<foreach collection = 'list' item = 'item' separator=','>" +
            "(#{item.tagName}, #{item.docId})" +
            "</foreach>" +
            "</script>")
    Integer insertDocTags(@Param(value = "list") List<DocTag> docTags);

    /**
     * Description: deleteDocTagByDocId 根据文档id删除对应的多个DocTag
     * @return java.lang.Integer
     * @author jinhui-huang
     * @Date 2023/9/8
     * */
    @Delete("delete from doc_tag where doc_id = #{docId}")
    Integer deleteDocTagByDocId(Integer docId);
    
    /**
     * Description: deleteDocTagByTagId 根据docTag的id批量删除docTag
     * @return java.lang.Integer
     * @author jinhui-huang
     * @Date 2023/9/8
     * */
    @Delete("<script>" +
            "delete from doc_tag where tag_id in" +
            "<foreach collection = 'list' item = 'item' open='(' separator=',' close=')'>" +
            "#{item.tagId}" +
            "</foreach>" +
            "</script>")
    Integer deleteDocTagByTagId(@Param(value = "list") List<DocTag> docTags);
    
    /**
     * Description: updateDocTagByTagId 根据docTag的id批量更新docTag
     * @return java.lang.Integer
     * @author jinhui-huang
     * @Date 2023/9/8
     * */
    @Update("<script>" +
            "<foreach collection = 'list' item = 'item' separator=';'>" +
            "update doc_tag set tag_name = #{item.tagName} where tag_id = #{item.tagId}" +
            "</foreach>" +
            "</script>")
    Integer updateDocTagByTagId(List<DocTag> docTags);
}
