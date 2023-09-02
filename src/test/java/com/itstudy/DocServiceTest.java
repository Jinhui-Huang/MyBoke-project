package com.itstudy;

import com.itstudy.domain.Doc;
import com.itstudy.service.IDocService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Description: DocServiceTest
 * <br></br>
 * className: DocServiceTest
 * <br></br>
 * packageName: com.itstudy
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/9/1 11:15
 */
@SpringBootTest
public class DocServiceTest {
    @Autowired
    private IDocService docService;

    @Test
    void testSelectDoc() {
        Doc doc = new Doc();
        doc.setUserEmail("2634692718@qq.com");
        doc.setDocId(1001);
        Doc doc1 = docService.selectDocByUserEmail(doc);
        System.out.println(doc1);
    }
}
