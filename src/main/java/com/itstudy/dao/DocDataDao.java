package com.itstudy.dao;

import com.itstudy.domain.DocData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Description: DocDataDao
 * <br></br>
 * className: DocDataDao
 * <br></br>
 * packageName: com.itstudy.dao
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/9/6 19:32
 */
@Mapper
public interface DocDataDao {

    /**
     * Description: selectDocsByUserEmail 根据用户的邮箱查询自己的文档数据信息, 默认按综合
     *
     * @return java.util.List<com.itstudy.domain.DocData>
     * @author jinhui-huang
     * @Date 2023/9/6
     */
    @Select("select doc_id, doc_title, doc_sees, clicks, collections from doc_data_view where user_email = #{userEmail}")
    List<DocData> selectDocDataByUserEmail(String userEmail);

    /**
     * Description: selectBestDocData 查询最佳的4篇文章作为精选文章
     * @return java.util.List<com.itstudy.domain.DocData>
     * @author jinhui-huang
     * @Date 2023/9/7
     * */
    @Select("select user_email, doc_id, doc_title, doc_description, doc_sees, clicks, collections " +
            "from doc_data_view order by collections * 5 + doc_sees * 2 + clicks * 3 desc limit 4")
    List<DocData> selectBestDocData();

}
