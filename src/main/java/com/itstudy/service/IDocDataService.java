package com.itstudy.service;

import com.itstudy.code.SortCode;
import com.itstudy.domain.DocData;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description: IDocDataService
 * <br></br>
 * className: IDocDataService
 * <br></br>
 * packageName: com.itstudy.service
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/9/6 20:06
 */
@Transactional
public interface IDocDataService {
    /**
     * Description: selectDocDataByUserEmail 查询用户的文档数据信息, 1升序 0降序
     * @return java.util.List<com.itstudy.domain.DocData>
     * @author jinhui-huang
     * @Date 2023/9/7
     * */
    List<DocData> selectDocDataByUserEmail(String userEmail);

    /**
     * Description: selectBestDocData 查询最佳的4篇文章
     * @return java.util.List<com.itstudy.domain.DocData>
     * @author jinhui-huang
     * @Date 2023/9/7
     * */
    List<DocData> selectBestDocData();

    /**
     * Description: sortDocDataByScore 按综合评分对文档进行排序, 1升序 0降序
     * @return java.util.List<com.itstudy.domain.DocData>
     * @author jinhui-huang
     * @Date 2023/9/7
     * */
    List<DocData> sortDocDataByScore(String userEmail, Integer sortCode);


    /**
     * Description: sortDocDataByClicks 按点赞数对文档进行排序, 1升序 0降序
     * @return java.util.List<com.itstudy.domain.DocData>
     * @author jinhui-huang
     * @Date 2023/9/7
     * */
    List<DocData> sortDocDataByClicks(String userEmail, Integer sortCode);

    /**
     * Description: sortDocDataByCollections 按收藏数对文档进行排序, 1升序 0降序
     * @return java.util.List<com.itstudy.domain.DocData>
     * @author jinhui-huang
     * @Date 2023/9/7
     * */
    List<DocData> sortDocDataByCollections(String userEmail, Integer sortCode);

    /**
     * Description: sortDocDataBySees 按阅读量对文档进行排序, 1升序 0降序
     * @return java.util.List<com.itstudy.domain.DocData>
     * @author jinhui-huang
     * @Date 2023/9/7
     * */
    List<DocData> sortDocDataBySees(String userEmail, Integer sortCode);
}
