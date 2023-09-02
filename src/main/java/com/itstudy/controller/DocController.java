package com.itstudy.controller;

import com.itstudy.code.Code;
import com.itstudy.code.RandomCode;
import com.itstudy.domain.Doc;
import com.itstudy.domain.DocAndDocTag;
import com.itstudy.domain.DocTag;
import com.itstudy.exception.BusinessException;
import com.itstudy.service.IDocService;
import com.itstudy.service.IDocTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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

    @Value("http://192.168.1.106:10086/static/img/")
    private String docUrl;

    @Value("src/main/resources/static/img/")
    private String path;

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

    @GetMapping("/all_docs")
    public Result getAllDcsLimit() {
        boolean flag = true;
        List<Doc> docs = docService.selectAllDocsLimit();
        if (docs == null || docs.isEmpty()) {
            flag = false;
        }
        Integer code = flag ? Code.GET_OK : Code.GET_ERR;
        String msg = flag ? "查询到该作者文章" : "该作者没有文章";
        return new Result(code, docs, msg);
    }

    @PutMapping("/add_doc")
    public Result addDoc(@RequestBody DocAndDocTag docObj) {
        try {
            boolean flag = false;
            if (docObj != null && docObj.getDocTags() != null) {
                /*先获取文档对象*/
                Doc doc = docObj.getDoc();
                /*再获取文档标签对象*/
                String[] docTags = docObj.getDocTags();
                flag = docService.insertDoc(doc);

                if (flag && docTags.length != 0) {
                    Integer docId = docService.selectNewDocId(doc.getUserEmail());
                    if (docId > 0) {
                        ArrayList<DocTag> docTagArrayList = new ArrayList<>();
                        for (String docTag : docTags) {
                            DocTag tag = new DocTag();
                            tag.setDocId(docId);
                            tag.setTagName(docTag);
                            docTagArrayList.add(tag);
                        }
                        flag = docTagService.insertDocTags(docTagArrayList);
                    }
                }
            }
            Integer code = flag ? Code.UPDATE_OK : Code.UPDATE_ERR;
            String msg = flag ? "文章添加成功" : "文章添加失败";
            return new Result(code, flag, msg);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(Code.UPDATE_ERR, "数据格式有错");
        }

    }

    @PostMapping("/doc_img")
    public Result getDocImg(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String originalFilename = file.getOriginalFilename();
        String imgUrl = "";
        boolean flag = false;
        if (originalFilename != null) {
            String[] split = originalFilename.split("\\.");
            if (split.length > 1) {
                String imgType = split[split.length - 1];
                String fileName = RandomCode.getRandomCode() + imgType;
                imgUrl = docUrl + fileName;
                File targetFile = new File(path, fileName);
                try {
                    file.transferTo(targetFile.toPath());
                    flag = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new BusinessException(Code.UPDATE_ERR, "数据格式有错");
                }
            }
        }
        Integer code = flag ? Code.UPDATE_OK : Code.UPDATE_ERR;
        String msg = flag ?  "图片上传成功" : "图片上传失败";
        return new Result(code, imgUrl, msg);
    }

}
