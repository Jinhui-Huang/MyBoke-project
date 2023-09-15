package com.itstudy.service.impl;

import com.itstudy.code.Code;
import com.itstudy.code.SortCode;
import com.itstudy.dao.DocDataDao;
import com.itstudy.domain.DocData;
import com.itstudy.exception.BusinessException;
import com.itstudy.exception.SystemException;
import com.itstudy.service.IDocDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * Description: DocDataServiceImpl
 * <br></br>
 * className: DocDataServiceImpl
 * <br></br>
 * packageName: com.itstudy.service.impl
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/9/6 20:07
 */
@Service
public class DocDataServiceImpl implements IDocDataService {

    @Autowired
    private DocDataDao docDataDao;

    @Override
    public List<DocData> selectDocDataByUserEmail(String userEmail) {
        try {
            return docDataDao.selectDocDataByUserEmail(userEmail);
        } catch (Exception e) {
            throw new SystemException(Code.SYSTEM_TIMEOUT_ERR, "服务器超时, 请重试!", e);
        }
    }

    /**
     * Description: selectBestDocData 查询最佳的4篇文章
     *
     * @return java.util.List<com.itstudy.domain.DocData>
     * @author jinhui-huang
     * @Date 2023/9/7
     */
    @Override
    public List<DocData> selectBestDocData() {
        try {
            return docDataDao.selectBestDocData();
        } catch (Exception e) {
            throw new SystemException(Code.SYSTEM_TIMEOUT_ERR, "服务器超时, 请重试!", e);
        }
    }

    /**
     * Description: sortDocDataByScore 按综合评分对文档进行排序, 1升序 0降序
     *
     * @param userEmail
     * @param sortCode
     * @return java.util.List<com.itstudy.domain.DocData>
     * @author jinhui-huang
     * @Date 2023/9/7
     */
    @Override
    public List<DocData> sortDocDataByScore(String userEmail, Integer sortCode) {
        if (sortCode.equals(SortCode.SCORE_UP) || sortCode.equals(SortCode.SCORE_DOWN)) {
            try {
                List<DocData> docData = docDataDao.selectDocDataByUserEmail(userEmail);
                if (docData != null && !docData.isEmpty()) {
                    docData.sort((o1, o2) -> {
                        int score1 = score(o1);
                        int score2 = score(o2);
                        if (sortCode.intValue() == SortCode.SCORE_UP.intValue()) {
                            return score1 - score2;
                        } else {
                            return score2 - score1;
                        }
                    });
                }
                return docData;
            } catch (Exception e) {
                throw new SystemException(Code.SYSTEM_TIMEOUT_ERR, "服务器超时, 请重试!", e);
            }
        } else {
            throw new BusinessException(Code.BUSINESS_ERR, "参数异常");
        }
    }


    /**
     * Description: score 计算文档的综合性得分
     *
     * @return int
     * @author jinhui-huang
     * @Date 2023/9/7
     */
    private int score(DocData docData) {
        int collections = docData.getCollections() == null ? 0 : docData.getCollections();
        int sees = docData.getDocSees() == null ? 0 : docData.getDocSees();
        int clicks = docData.getClicks() == null ? 0 : docData.getClicks();
        return collections * 5 + sees * 3 + clicks * 2;
    }

    /**
     * Description: sortDocDataByClicks 按点赞数对文档进行排序, 1升序 0降序
     *
     * @param userEmail
     * @param sortCode
     * @return java.util.List<com.itstudy.domain.DocData>
     * @author jinhui-huang
     * @Date 2023/9/7
     */
    @Override
    public List<DocData> sortDocDataByClicks(String userEmail, Integer sortCode) {
        if (sortCode.equals(SortCode.CLICKS_UP) || sortCode.equals(SortCode.CLICKS_DOWN)) {
            try {
                List<DocData> docData = docDataDao.selectDocDataByUserEmail(userEmail);
                if (docData != null && !docData.isEmpty()) {
                    if (sortCode.intValue() == SortCode.CLICKS_UP.intValue()) {
                        docData.sort((o1, o2) -> {
                            int clicks1 = o1.getClicks() == null ? 0 : o1.getClicks();
                            int clicks2 = o2.getClicks() == null ? 0 : o2.getClicks();
                            return clicks1 - clicks2;
                        });
                    } else {
                        docData.sort((o1, o2) -> {
                            int clicks1 = o1.getClicks() == null ? 0 : o1.getClicks();
                            int clicks2 = o2.getClicks() == null ? 0 : o2.getClicks();
                            return clicks2 - clicks1;
                        });
                    }
                }
                return docData;
            } catch (Exception e) {
                throw new SystemException(Code.SYSTEM_TIMEOUT_ERR, "服务器超时, 请重试!", e);
            }
        } else {
            throw new BusinessException(Code.BUSINESS_ERR, "参数异常");
        }
    }

    /**
     * Description: sortDocDataByCollections 按收藏数对文档进行排序, 1升序 0降序
     *
     * @param userEmail
     * @param sortCode
     * @return java.util.List<com.itstudy.domain.DocData>
     * @author jinhui-huang
     * @Date 2023/9/7
     */
    @Override
    public List<DocData> sortDocDataByCollections(String userEmail, Integer sortCode) {
        if (sortCode.equals(SortCode.COLLECTIONS_UP) || sortCode.equals(SortCode.COLLECTIONS_DOWN)) {
            try {
                List<DocData> docData = docDataDao.selectDocDataByUserEmail(userEmail);
                if (docData != null && !docData.isEmpty()) {
                    if (sortCode.intValue() == SortCode.COLLECTIONS_UP.intValue()) {
                        docData.sort((o1, o2) -> {
                            int clicks1 = o1.getCollections() == null ? 0 : o1.getCollections();
                            int clicks2 = o2.getCollections() == null ? 0 : o2.getCollections();
                            return clicks1 - clicks2;
                        });
                    } else {
                        docData.sort((o1, o2) -> {
                            int clicks1 = o1.getCollections() == null ? 0 : o1.getCollections();
                            int clicks2 = o2.getCollections() == null ? 0 : o2.getCollections();
                            return clicks2 - clicks1;
                        });
                    }
                }
                return docData;
            } catch (Exception e) {
                throw new SystemException(Code.SYSTEM_TIMEOUT_ERR, "服务器超时, 请重试!", e);
            }
        } else {
            throw new BusinessException(Code.BUSINESS_ERR, "参数异常");
        }
    }

    /**
     * Description: sortDocDataBySees 按阅读量对文档进行排序, 1升序 0降序
     *
     * @param userEmail
     * @param sortCode
     * @return java.util.List<com.itstudy.domain.DocData>
     * @author jinhui-huang
     * @Date 2023/9/7
     */
    @Override
    public List<DocData> sortDocDataBySees(String userEmail, Integer sortCode) {
        if (sortCode.equals(SortCode.SEES_UP) || sortCode.equals(SortCode.SEES_DOWN)) {
            try {
                List<DocData> docData = docDataDao.selectDocDataByUserEmail(userEmail);
                if (docData != null && !docData.isEmpty()) {
                    if (sortCode.intValue() == SortCode.SEES_UP.intValue()) {
                        docData.sort((o1, o2) -> {
                            int clicks1 = o1.getDocSees() == null ? 0 : o1.getDocSees();
                            int clicks2 = o2.getDocSees() == null ? 0 : o2.getDocSees();
                            return clicks1 - clicks2;
                        });
                    } else {
                        docData.sort((o1, o2) -> {
                            int clicks1 = o1.getDocSees() == null ? 0 : o1.getDocSees();
                            int clicks2 = o2.getDocSees() == null ? 0 : o2.getDocSees();
                            return clicks2 - clicks1;
                        });
                    }
                }
                return docData;
            } catch (Exception e) {
                throw new SystemException(Code.SYSTEM_TIMEOUT_ERR, "服务器超时, 请重试!", e);
            }
        } else {
            throw new BusinessException(Code.BUSINESS_ERR, "参数异常");
        }
    }
}
