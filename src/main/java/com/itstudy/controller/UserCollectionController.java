package com.itstudy.controller;

import com.itstudy.code.Code;
import com.itstudy.domain.Collection;
import com.itstudy.domain.Doc;
import com.itstudy.domain.UserCollection;
import com.itstudy.service.IUserCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: UserCollectionController
 * <br></br>
 * className: UserCollectionController
 * <br></br>
 * packageName: com.itstudy.controller
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/9/5 09:59
 */
@CrossOrigin
@RestController
@RequestMapping("/doc_info")
public class UserCollectionController {
    @Autowired
    private IUserCollectionService userCollectionService;

    /**
     * Description: collectDoc 插入收藏的文章
     * @return com.itstudy.controller.Result
     * @author jinhui-huang
     * @Date 2023/9/5
     * */
    @PutMapping("/collection")
    public Result collectDoc(@RequestBody UserCollection userCollection) {
        Boolean flag = false;
        String msg = "操作失败";
        if (userCollection != null && userCollection.getDocId() != null
                && userCollection.getUserEmail() != null) {
            Integer docId = userCollection.getDocId();
            String userEmail = userCollection.getUserEmail();
            flag = userCollectionService.selectEnabledSee(docId, userEmail);
            if (flag == null) {
                flag = userCollectionService.insertIntoCollection(userCollection);
                msg = "收藏成功";
            } else {
                /*可以同时收藏文章和取消文章*/
                if (flag) {
                    msg = "取消收藏";
                    flag = !userCollectionService.updateCollection(docId, userEmail);
                } else {
                    msg = "收藏成功";
                    flag = userCollectionService.updateCollection(docId, userEmail);
                }

            }
        }
        Integer code = flag ? Code.SAVE_OK : Code.SAVE_ERR;
        return new Result(code, flag, msg);
    }

    /**
     * Description: selectDocEnabledSee 查询文章是否收藏
     * @return com.itstudy.controller.Result
     * @author jinhui-huang
     * @Date 2023/9/5
     * */
    @PostMapping("/collection/enabled")
    public Result selectDocEnabledSee(@RequestBody UserCollection userCollection){
        Boolean flag = false;
        if (userCollection != null && userCollection.getDocId() != null
                && userCollection.getUserEmail() != null) {
            Integer docId = userCollection.getDocId();
            String userEmail = userCollection.getUserEmail();
            flag = userCollectionService.selectEnabledSee(docId, userEmail);
        }
        flag = flag != null && flag;
        Integer code = flag ? Code.GET_OK : Code.GET_ERR;
        String msg = flag ? "文章已收藏" : "文章未收藏";
        return new Result(code, flag, msg);
    }

    /**
     * Description: getUserCollections 获取用户全部收藏的文章
     * @return com.itstudy.controller.Result
     * @author jinhui-huang
     * @Date 2023/9/5
     * */
    @GetMapping("/collection/{userEmail}")
    public Result getUserCollections(@PathVariable String userEmail) {
        boolean flag = false;
        List<Collection> userCollections = null;
        if (userEmail != null) {
            userCollections = userCollectionService.selectUserCollection(userEmail);
            if (!userCollections.isEmpty()) {
                flag = true;
            }
        }
        Integer code = flag ? Code.GET_OK : Code.GET_ERR;
        String msg = flag ? "获取成功" : "未收藏文章";
        return new Result(code, userCollections, msg);
    }

    /**
     * Description: clickDoc 记录用户点赞的文章, 存储文章id和点赞次数, 可以在用户已经点赞的基础上添加点赞数量
     * @return com.itstudy.controller.Result
     * @author jinhui-huang
     * @Date 2023/9/5
     * */
    @PutMapping("/click")
    public Result clickDoc(@RequestBody UserCollection userCollection) {
        Boolean flag = false;
        if (userCollection != null && userCollection.getDocId() != null
                && userCollection.getUserEmail() != null) {
            Integer docId = userCollection.getDocId();
            String userEmail = userCollection.getUserEmail();
            flag = userCollectionService.selectEnabledSee(docId, userEmail);
            if (flag == null) {
                /*该文章未添加到表中过, 进行添加*/
                flag = userCollectionService.insertClick(userCollection);
            } else {
                /*该文章已经加入表中*/
                /*判断用户的点赞时间间隔不低于半小时*/
                LocalDateTime time = userCollectionService.selectUserClick(docId, userEmail);
                if (time == null) {
                    flag = userCollectionService.updateClicks(docId, userEmail);
                } else {
                    flag = time.plusHours(1).isBefore(LocalDateTime.now());
                    if (flag) {
                        flag = userCollectionService.updateClicks(docId, userEmail);
                    }
                }
            }
        }
        Integer code = flag ? Code.SAVE_OK : Code.SAVE_ERR;
        String msg = flag ? "点赞+1" : "1小时后可再次点赞";
        return new Result(code, flag, msg);
    }
}
