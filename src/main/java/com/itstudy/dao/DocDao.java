package com.itstudy.dao;

import com.itstudy.domain.Doc;
import org.apache.ibatis.annotations.*;

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
    @Select("select doc_context, doc_description, doc_title, doc_sees from doc where doc_id = #{docId}")
    Doc selectDocByEmail(Doc doc);


    /**
     * Description: selectAllDocs 查询某个作者的全部文档
     * @return java.util.List<com.itstudy.domain.Doc>
     * @author jinhui-huang
     * @Date 2023/9/2
     * */
    @Select("select doc_id, doc_title, doc_description, doc_date_time, doc_sees from doc where user_email = #{userEmail}")
    List<Doc> selectAllDocs(String userEmail);

    /**
     * Description: selectAllDocsLimit 查询用于展示的文档
     * @return java.util.List<com.itstudy.domain.Doc>
     * @author jinhui-huang
     * @Date 2023/9/2
     * */
    @Select("select doc_id, user_email, doc_title, doc_description, doc_date_time, doc_sees from doc order by doc_date_time desc limit 20 ")
    List<Doc> selectAllDocsLimit();

    /**
     * Description: insertDoc 新增文档
     * @return java.lang.Integer
     * @author jinhui-huang
     * @Date 2023/9/2
     * */
    @Insert("insert into doc(user_email, doc_context, doc_title, doc_description) " +
            "VALUES(#{userEmail}, #{docContext}, #{docTitle}, #{docDescription}) ")
    Integer insertDoc(Doc doc);

    /**
     * Description: selectNewDocId 查询作者新增的最新文档id
     * @return java.lang.Long
     * @author jinhui-huang
     * @Date 2023/9/4
     * */
    @Select("select max(doc_id) from doc where user_email = #{userEmail}")
    Long selectNewDocId(String userEmail);

    /**
     * Description: updateDocSee 更新文章的阅读量
     * @return java.lang.Integer
     * @author jinhui-huang
     * @Date 2023/9/8
     * */
    @Update("update doc set doc_sees = doc_sees + 1 where doc_id = #{docId}")
    Integer updateDocSee(Integer docId);

    /**
     * Description: updateDoc 更新文章的内容包括标题， 描述， 和内容
     * @return java.lang.Integer
     * @author jinhui-huang
     * @Date 2023/9/8
     * */
    @Update("update doc set doc_title = #{docTitle}, doc_description = #{docDescription}, doc_context = #{docContext} " +
            "where doc_id = #{docId}")
    Integer updateDoc(Doc doc);

    /**
     * Description: deleteDoc 根据文档id删除文档
     * @return java.lang.Integer
     * @author jinhui-huang
     * @Date 2023/9/8
     * */
    @Delete("delete from doc where doc_id = #{docId}")
    Integer deleteDoc(Integer docId);

}
