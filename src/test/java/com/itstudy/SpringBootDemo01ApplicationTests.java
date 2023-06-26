package com.itstudy;

import com.itstudy.dao.BookDao;
import com.itstudy.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootDemo01ApplicationTests {
    @Autowired
    private BookService bookService;
    @Test
    void contextLoads() {
        System.out.println(bookService.selectBookById(2));
    }

}
