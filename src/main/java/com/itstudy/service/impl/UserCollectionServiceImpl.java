package com.itstudy.service.impl;

import com.itstudy.code.Code;
import com.itstudy.dao.DocDao;
import com.itstudy.dao.UserCollectionDao;
import com.itstudy.domain.Collection;
import com.itstudy.domain.Doc;
import com.itstudy.domain.UserCollection;
import com.itstudy.exception.SystemException;
import com.itstudy.service.IUserCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: UserCollectionServiceImpl
 * <br></br>
 * className: UserCollectionServiceImpl
 * <br></br>
 * packageName: com.itstudy.service.impl
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/9/4 20:53
 */
@Service
public class UserCollectionServiceImpl implements IUserCollectionService {
    @Autowired
    private UserCollectionDao userCollectionDao;

    @Autowired
    private DocDao docDao;


    /**
     * Description: selectUserCollection 查询用户收藏的全部文章, 过滤条件enabledSee==true
     *
     * @param userEmail
     * @return com.itstudy.domain.UserCollection
     * @author jinhui-huang
     * @Date 2023/9/4
     */
    @Override
    public List<Collection> selectUserCollection(String userEmail) {
        try {
            return userCollectionDao.selectUserCollection(userEmail);
        } catch (Exception e) {
            throw new SystemException(Code.SYSTEM_TIMEOUT_ERR, "服务器超时, 请重试!", e);
        }
    }

    /**
     * Description: selectUserCollection 查询用户点击过的文章, 过滤条件clicks > 0
     * 返回点击的时间点, 不存再会返回null
     * @param docId
     * @param userEmail
     * @return com.itstudy.domain.UserCollection
     * @author jinhui-huang
     * @Date 2023/9/4
     */
    @Override
    public LocalDateTime selectUserClick(Integer docId, String userEmail) {
        try {
            return userCollectionDao.selectUserClick(docId, userEmail);
        } catch (Exception e) {
            throw new SystemException(Code.SYSTEM_TIMEOUT_ERR, "服务器超时, 请重试!", e);
        }
    }

    /**
     * Description: insertIntoCollection 用户收藏时对文章的添加, 添加时判断用户是否对这篇文章进行了点赞
     * 添加文章收藏时间和docId
     * @param userCollection
     * @return java.lang.Integer
     * @author jinhui-huang
     * @Date 2023/9/4
     */
    @Override
    public Boolean insertIntoCollection(UserCollection userCollection) {
        try {
            return userCollectionDao.insertIntoCollection(userCollection) > 0;
        } catch (Exception e) {
            throw new SystemException(Code.SYSTEM_TIMEOUT_ERR, "服务器超时, 请重试!", e);
        }
    }

    /**
     * Description: updateCollection 用户收藏或取消收藏时对enabledSee的更新, 这个方法的收藏前提用户之前对这篇文章进行了点赞
     *
     * @param docId
     * @param userEmail
     * @return java.lang.Integer
     * @author jinhui-huang
     * @Date 2023/9/4
     */
    @Override
    public Boolean updateCollection(Integer docId, String userEmail) {
        try {
            return userCollectionDao.updateCollection(docId, userEmail) > 0;
        } catch (Exception e) {
            throw new SystemException(Code.SYSTEM_TIMEOUT_ERR, "服务器超时, 请重试!", e);
        }
    }

    /**
     * Description: 判断用户是否收藏了这篇文章
     *
     * @param docId
     * @param userEmail
     * @return Boolean
     * @author jinhui-huang
     * @Date 2023/9/4
     */
    @Override
    public Boolean selectEnabledSee(Integer docId, String userEmail) {
        try {
            return userCollectionDao.selectEnabledSee(docId, userEmail);
        } catch (Exception e) {
            throw new SystemException(Code.SYSTEM_TIMEOUT_ERR, "服务器超时, 请重试!", e);
        }
    }

    /**
     * Description: insertClick 用户点赞文章对文章的点赞进行添加, 添加时判断用户是否收藏了该文章
     * 添加点赞的时间
     * @param userCollection
     * @return java.lang.Integer
     * @author jinhui-huang
     * @Date 2023/9/4
     */
    @Override
    public Boolean insertClick(UserCollection userCollection) {
        try {
            return userCollectionDao.insertClick(userCollection) > 0;
        } catch (Exception e) {
            throw new SystemException(Code.SYSTEM_TIMEOUT_ERR, "服务器超时, 请重试!", e);
        }
    }

    /**
     * Description: updateClicks 用户再次点击时增加点击量
     *
     * @param docId
     * @param userEmail
     * @return java.lang.Integer
     * @author jinhui-huang
     * @Date 2023/9/4
     */
    @Override
    public Boolean updateClicks(Integer docId, String userEmail) {
        try {
            return userCollectionDao.updateClicks(docId, userEmail) > 0;
        } catch (Exception e) {
            throw new SystemException(Code.SYSTEM_TIMEOUT_ERR, "服务器超时, 请重试!", e);
        }
    }

    /**
     * Description: deleteDocDataByDocId 根据文章id删除文章数据
     *
     * @param docId
     * @return java.lang.Boolean
     * @author jinhui-huang
     * @Date 2023/9/8
     */
    @Override
    public Boolean deleteDocDataByDocId(Integer docId) {
        try {
            return userCollectionDao.deleteDocById(docId) >= 0;
        } catch (Exception e) {
            throw new SystemException(Code.SYSTEM_TIMEOUT_ERR, "服务器超时, 请重试!", e);
        }
    }
}
