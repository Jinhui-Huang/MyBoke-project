package com.itstudy.service;

import com.itstudy.domain.Collection;
import com.itstudy.domain.Doc;
import com.itstudy.domain.UserCollection;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Description: IUserCollectionService
 * <br></br>
 * className: IUserCollectionService
 * <br></br>
 * packageName: com.itstudy.service
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/9/4 20:53
 */
@Transactional
public interface IUserCollectionService {
    /**
     * Description: selectUserCollection 查询用户收藏的全部文章, 过滤条件enabledSee==true
     * @return com.itstudy.domain.UserCollection
     * @author jinhui-huang
     * @Date 2023/9/4
     * */
    List<Collection> selectUserCollection(String userEmail);

    /**
     * Description: selectUserCollection 查询用户是否点击过该文章, 过滤条件clicks > 0
     * 返回文章点击时间
     * @return com.itstudy.domain.UserCollection
     * @author jinhui-huang
     * @Date 2023/9/4
     * */
    LocalDateTime selectUserClick(Integer docId, String userEmail);

    /**
     * Description: insertIntoCollection 用户收藏时对文章的添加, 添加时判断用户是否对这篇文章进行了点赞
     * @return java.lang.Integer
     * @author jinhui-huang
     * @Date 2023/9/4
     * */
    Boolean insertIntoCollection(UserCollection userCollection);

    /**
     * Description: updateCollection 用户收藏或取消收藏时对enabledSee的更新, 这个方法的收藏前提用户之前对这篇文章进行了点赞
     * @return java.lang.Integer
     * @author jinhui-huang
     * @Date 2023/9/4
     * */
    Boolean updateCollection(Integer docId, String userEmail);

    /**
     * Description: 判断用户是否收藏了这篇文章
     * @return Boolean
     * @author jinhui-huang
     * @Date 2023/9/4
     * */
    Boolean selectEnabledSee(Integer docId, String userEmail);

    /**
     * Description: insertClick 用户点赞文章对文章的点赞进行添加, 添加时判断用户是否收藏了该文章
     * @return java.lang.Integer
     * @author jinhui-huang
     * @Date 2023/9/4
     * */
    Boolean insertClick(UserCollection userCollection);

    /**
     * Description: updateClicks 用户再次点击时增加点击量
     * @return java.lang.Integer
     * @author jinhui-huang
     * @Date 2023/9/4
     * */
    Boolean updateClicks(Integer docId, String userEmail);

    /**
     * Description: deleteDocDataByDocId 根据文章id删除文章数据
     * @return java.lang.Boolean
     * @author jinhui-huang
     * @Date 2023/9/8
     * */
    Boolean deleteDocDataByDocId(Integer docId);

}


