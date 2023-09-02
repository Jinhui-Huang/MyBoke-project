package com.itstudy.service;

import com.itstudy.domain.Doc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description: IDocService
 * <br></br>
 * className: IDocService
 * <br></br>
 * packageName: com.itstudy.service
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/9/1 11:12
 */
@Transactional
public interface IDocService {
    Doc selectDocByUserEmail(Doc doc);

    List<Doc> selectAllDocs(String userEmail);
}
