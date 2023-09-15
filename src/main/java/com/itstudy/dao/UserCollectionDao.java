package com.itstudy.dao;

import com.itstudy.domain.Collection;
import com.itstudy.domain.Doc;
import com.itstudy.domain.UserCollection;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Description: UserCollectionDao
 * <br></br>
 * className: UserCollectionDao
 * <br></br>
 * packageName: com.itstudy.dao
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/9/4 20:24
 */
@Mapper
public interface UserCollectionDao {

    /**
     * Description: selectUserCollection 查询用户收藏的全部文章, 过滤条件enabledSee==true
     * @return com.itstudy.domain.UserCollection
     * @author jinhui-huang
     * @Date 2023/9/4
     * */
    @Select("select u.user_email, u.user_name, u.user_img, d.doc_id, d.doc_title, d.doc_sees, d.doc_description, d.doc_date_time, uu.collection_date_time from user_collection uu\n" +
            "         join doc d on d.doc_id = uu.doc_id\n" +
            "         join user u on d.user_email = u.user_email\n" +
            "         where uu.user_email = #{userEmail} and uu.enabled_see = true" +
            "         order by uu.collection_date_time desc")
    List<Collection> selectUserCollection(String userEmail);

    /**
     * Description: selectUserCollection 查询用户点击过的文章, 过滤条件clicks > 0, 返回文章的上次点击时间
     * @return com.itstudy.domain.UserCollection
     * @author jinhui-huang
     * @Date 2023/9/4
     * */
    @Select("select click_date_time from user_collection " +
            "where doc_id = #{docId} " +
            "and user_email = #{userEmail} " +
            "and clicks > 0 ")
    LocalDateTime selectUserClick(Integer docId, String userEmail);

    /**
     * Description: insertIntoCollection 用户收藏时对文章的添加, 添加时判断用户是否对这篇文章进行了点赞
     * @return java.lang.Integer
     * @author jinhui-huang
     * @Date 2023/9/4
     * */
    @Insert("insert into user_collection(user_email, doc_id, enabled_see, collection_date_time) " +
            "VALUES(#{userEmail}, #{docId}, true, now()) ")
    Integer insertIntoCollection(UserCollection userCollection);
    
    /**
     * Description: updateCollection 用户收藏或取消收藏时对enabledSee的更新, 这个方法的收藏前提用户之前对这篇文章进行了点赞
     * @return java.lang.Integer
     * @author jinhui-huang
     * @Date 2023/9/4
     * */
    @Update("update user_collection set enabled_see = !enabled_see, collection_date_time = now() " +
            "where doc_id = #{docId} " +
            "and user_email = #{userEmail}")
    Integer updateCollection(Integer docId, String userEmail);
    
    /**
     * Description: 判断用户是否收藏了这篇文章
     * @return Boolean
     * @author jinhui-huang
     * @Date 2023/9/4
     * */
    @Select("select enabled_see from user_collection " +
            "where doc_id = #{docId} " +
            "and user_email = #{userEmail}")
    Boolean selectEnabledSee(Integer docId, String userEmail);
            
    /**
     * Description: insertClick 用户点赞文章对文章的点赞进行添加, 添加时判断用户是否收藏了该文章
     * @return java.lang.Integer
     * @author jinhui-huang
     * @Date 2023/9/4
     * */
    @Insert("insert into user_collection(user_email, doc_id, clicks, click_date_time) " +
            "VALUES(#{userEmail}, #{docId}, 1, now()) ")
    Integer insertClick(UserCollection userCollection);

    /**
     * Description: updateClicks 用户再次点击时增加点击量
     * @return java.lang.Integer
     * @author jinhui-huang
     * @Date 2023/9/4
     * */
    @Update("update user_collection set clicks = clicks + 1, click_date_time = now() " +
            "where doc_id = #{docId} " +
            "and user_email = #{userEmail}")
    Integer updateClicks(Integer docId, String userEmail);


    /**
     * Description: deleteDocById 根据文章的id删除数据
     * @return java.lang.Integer
     * @author jinhui-huang
     * @Date 2023/9/8
     * */
    @Delete("delete from user_collection where doc_id = #{docId}")
    Integer deleteDocById(Integer docId);

}
