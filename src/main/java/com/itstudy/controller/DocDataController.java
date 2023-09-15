package com.itstudy.controller;

import com.itstudy.code.Code;
import com.itstudy.code.SortCode;
import com.itstudy.domain.DocData;
import com.itstudy.domain.User;
import com.itstudy.exception.BusinessException;
import com.itstudy.service.IDocDataService;
import com.itstudy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: DocDataController
 * <br></br>
 * className: DocDataController
 * <br></br>
 * packageName: com.itstudy.controller
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/9/6 21:59
 */
@CrossOrigin
@RestController
@RequestMapping("/doc_data")
public class DocDataController {

    @Autowired
    private IDocDataService docDataService;

    @Autowired
    private IUserService userService;

    @GetMapping("{userEmail}")
    public Result getDocDataByEmail(@PathVariable String userEmail) {
        boolean flag = false;
        List<DocData> docData = docDataService.selectDocDataByUserEmail(userEmail);
        if (docData != null && !docData.isEmpty()) {
            flag = true;
        }
        Integer code = flag ? Code.GET_OK : Code.GET_ERR;
        String msg = flag ? "文章数据查询成功" : "文章数据查询失败";
        return new Result(code, docData, msg);
    }

    @GetMapping("/score")
    public Result getDocDataSortByScore(@RequestParam String userEmail, @RequestParam Integer sortCode) {
        if (sortCode == null) {
            throw new BusinessException(Code.BUSINESS_ERR, "排序码非法!");
        }
        boolean flag = false;
        List<DocData> docData = docDataService.sortDocDataByScore(userEmail, sortCode);
        if (docData != null && !docData.isEmpty()) {
            flag = true;
        }
        Integer code = flag ? Code.GET_OK : Code.GET_ERR;
        String msg = flag ? "文章数据查询成功" : "文章数据查询失败";
        return new Result(code, docData, msg);
    }

    @GetMapping("/click")
    public Result getDocDataSortByClicks(@RequestParam String userEmail, @RequestParam Integer sortCode) {
        if (sortCode == null) {
            throw new BusinessException(Code.BUSINESS_ERR, "排序码非法!");
        }
        boolean flag = false;
        List<DocData> docData = docDataService.sortDocDataByClicks(userEmail, sortCode);
        if (docData != null && !docData.isEmpty()) {
            flag = true;
        }
        Integer code = flag ? Code.GET_OK : Code.GET_ERR;
        String msg = flag ? "文章数据查询成功" : "文章数据查询失败";
        return new Result(code, docData, msg);
    }

    @GetMapping("/collection")
    public Result getDocDataSortByCollections(@RequestParam String userEmail, @RequestParam Integer sortCode) {
        if (sortCode == null) {
            throw new BusinessException(Code.BUSINESS_ERR, "排序码非法!");
        }
        boolean flag = false;
        List<DocData> docData = docDataService.sortDocDataByCollections(userEmail, sortCode);
        if (docData != null && !docData.isEmpty()) {
            flag = true;
        }
        Integer code = flag ? Code.GET_OK : Code.GET_ERR;
        String msg = flag ? "文章数据查询成功" : "文章数据查询失败";
        return new Result(code, docData, msg);
    }

    @GetMapping("/see")
    public Result getDocDataSortBySees(@RequestParam String userEmail, @RequestParam Integer sortCode) {
        if (sortCode == null) {
            throw new BusinessException(Code.BUSINESS_ERR, "排序码非法!");
        }
        boolean flag = false;
        List<DocData> docData = docDataService.sortDocDataBySees(userEmail, sortCode);
        if (docData != null && !docData.isEmpty()) {
            flag = true;
        }
        Integer code = flag ? Code.GET_OK : Code.GET_ERR;
        String msg = flag ? "文章数据查询成功" : "文章数据查询失败";
        return new Result(code, docData, msg);
    }


    @GetMapping("/best")
    public Result getBestDocData() {
        boolean flag = false;
        List<DocData> docData = docDataService.selectBestDocData();
        Object[] objects;
        List<Object[]> objectList = null;
        if (docData != null && !docData.isEmpty()) {
            objectList = new ArrayList<>();
            for (DocData data : docData) {
                User user = userService.selectUserByEmail(data.getUserEmail());
                user.setUserPassword("");
                objects = new Object[2];
                if (data.getCollections() == null) {
                    data.setCollections(0);
                }
                objects[0] = data;
                objects[1] = user;
                objectList.add(objects);
            }
            flag = true;
        }
        Integer code = flag ? Code.GET_OK : Code.GET_ERR;
        String msg = flag ? "文章数据查询成功" : "文章数据查询失败";
        return new Result(code, objectList, msg);
    }
}
