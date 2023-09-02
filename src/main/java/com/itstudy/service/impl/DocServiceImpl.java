package com.itstudy.service.impl;

import com.itstudy.code.Code;
import com.itstudy.dao.DocDao;
import com.itstudy.domain.Doc;
import com.itstudy.exception.SystemException;
import com.itstudy.service.IDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            return docDao.selectAllDocs(userEmail);
        } catch (Exception e) {
            throw new SystemException(Code.SYSTEM_TIMEOUT_ERR, "服务器超时, 请重试!", e);
        }
    }
}
