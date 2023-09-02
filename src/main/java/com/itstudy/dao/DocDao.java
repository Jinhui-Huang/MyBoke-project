package com.itstudy.dao;

import com.itstudy.domain.Doc;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Description: DocDao
 * <br></br>
 * className: DocDao
 * <br></br>
 * packageName: com.itstudy.dao
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/9/1 11:06
 */
@Mapper
public interface DocDao {
    /**
     * Description: selectDocByEmail 查询单个文档
     * @return com.itstudy.domain.Doc
     * @author jinhui-huang
     * @Date 2023/9/1
     * */
    @Select("select doc_context, doc_description, doc_title from doc where doc_id = #{docId} and user_email = #{userEmail}")
    Doc selectDocByEmail(Doc doc);


    /**
     * Description: selectAllDocs 查询某个作者的全部文档
     * @return java.util.List<com.itstudy.domain.Doc>
     * @author jinhui-huang
     * @Date 2023/9/2
     * */
    @Select("select doc_id, doc_title, doc_description, doc_date_time from doc where user_email = #{userEmail}")
    List<Doc> selectAllDocs(String userEmail);

    /**
     * Description: insertDoc 新增文档
     * @return java.lang.Integer
     * @author jinhui-huang
     * @Date 2023/9/2
     * */
    @Insert("insert into doc(user_email, doc_context, doc_title, doc_description) " +
            "VALUES(#{userEmail}, #{docContext}, #{docTitle}, #{docDescription}) ")
    Integer insertDoc(Doc doc);
}
