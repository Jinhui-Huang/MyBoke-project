package com.itstudy.service.impl;

import com.itstudy.code.Code;
import com.itstudy.dao.DocTagDao;
import com.itstudy.domain.DocTag;
import com.itstudy.exception.SystemException;
import com.itstudy.service.IDocTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description: DocTagServiceImpl
 * <br></br>
 * className: DocTagServiceImpl
 * <br></br>
 * packageName: com.itstudy.service.impl
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/9/1 16:31
 */
@Service
public class DocTagServiceImpl implements IDocTagService {
    @Autowired
    private DocTagDao docTagDao;
    @Override
    public List<DocTag> selectDocTags(Integer docId) {
        try {
            return docTagDao.selectDocTagByDocId(docId);
        } catch (Exception e) {
            throw new SystemException(Code.SYSTEM_TIMEOUT_ERR, "服务器超时, 请重试!", e);
        }
    }

    @Override
    public Boolean insertDocTags(List<DocTag> docTags) {
        try {
            return docTagDao.insertDocTags(docTags) > 0;
        } catch (Exception e) {
            throw new SystemException(Code.SYSTEM_TIMEOUT_ERR, "服务器超时, 请重试!", e);
        }
    }
}
