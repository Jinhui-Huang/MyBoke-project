package com.itstudy.controller;

import com.itstudy.code.Code;
import com.itstudy.domain.Doc;
import com.itstudy.domain.DocTag;
import com.itstudy.service.IDocService;
import com.itstudy.service.IDocTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: DocController
 * <br></br>
 * className: DocController
 * <br></br>
 * packageName: com.itstudy.controller
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/9/1 11:19
 */
@CrossOrigin
@RestController
@RequestMapping("/docs")
public class DocController {
    @Autowired
    private IDocService docService;

    @Autowired
    private IDocTagService docTagService;

    @PostMapping("/doc")
    public Result getDoc(@RequestBody Doc findDoc) {
        Doc doc = docService.selectDocByUserEmail(findDoc);
        List<DocTag> docTags = docTagService.selectDocTags(findDoc.getDocId());
        Integer code = doc != null ? Code.GET_OK : Code.GET_ERR;
        String msg = doc != null ? "文章查询成功" : "文章查询失败";
        if (doc != null) {
            findDoc.setDocContext(doc.getDocContext());
            findDoc.setDocTitle(doc.getDocTitle());
            findDoc.setDocDescription(doc.getDocDescription());
        }
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(docTags);
        arrayList.add(findDoc);
        return new Result(code, arrayList, msg);
    }

    @GetMapping("/all_docs/{userEmail}")
    public Result getAllDcs(@PathVariable String userEmail) {
        boolean flag = false;
        List<Doc> docs = docService.selectAllDocs(userEmail);
        if (docs != null && !docs.isEmpty()) {
            flag = true;
        }
        Integer code = flag ? Code.GET_OK : Code.GET_ERR;
        String msg = flag ? "查询到该作者文章" : "该作者没有文章";
        return new Result(code, docs, msg);
    }

}
