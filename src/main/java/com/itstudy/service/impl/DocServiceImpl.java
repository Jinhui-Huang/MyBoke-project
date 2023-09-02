package com.itstudy.service.impl;

import com.itstudy.code.Code;
import com.itstudy.dao.DocDao;
import com.itstudy.domain.Doc;
import com.itstudy.exception.SystemException;
import com.itstudy.service.IDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * Description: DocServiceImpl
 * <br></br>
 * className: DocServiceImpl
 * <br></br>
 * packageName: com.itstudy.service.impl
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/9/1 11:12
 */
@Service
public class DocServiceImpl implements IDocService {


    @Autowired
    private DocDao docDao;

    @Override
    public Doc selectDocByUserEmail(Doc doc) {
        try {
            return docDao.selectDocByEmail(doc);
        } catch (Exception e) {
            throw new SystemException(Code.SYSTEM_TIMEOUT_ERR, "服务器超时, 请重试!", e);
        }

    }

    @Override
    public List<Doc> selectAllDocs(String userEmail) {
        try {
            List<Doc> docs = docDao.selectAllDocs(userEmail);
            docs.sort((o1, o2) -> o2.getDocId() - o1.getDocId());
            return docs;
        } catch (Exception e) {
            throw new SystemException(Code.SYSTEM_TIMEOUT_ERR, "服务器超时, 请重试!", e);
        }
    }

    @Override
    public List<Doc> selectAllDocsLimit() {
        try {
            return docDao.selectAllDocsLimit();
        } catch (Exception e) {
            throw new SystemException(Code.SYSTEM_TIMEOUT_ERR, "服务器超时, 请重试!", e);
        }
    }

    @Override
    public Boolean insertDoc(Doc doc) {
        try {
            return docDao.insertDoc(doc) > 0;
        } catch (Exception e) {
            throw new SystemException(Code.SYSTEM_TIMEOUT_ERR, "服务器超时, 请重试!", e);
        }
    }

    @Override
    public Integer selectNewDocId(String userEmail) {
        try {
            Long docId = docDao.selectNewDocId(userEmail);
            return Math.max(docId.intValue(), 0);
        } catch (Exception e) {
            throw new SystemException(Code.SYSTEM_TIMEOUT_ERR, "服务器超时, 请重试!", e);
        }
    }
}
