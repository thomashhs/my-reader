package com.imooc.reader.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imooc.reader.entity.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BookServiceImplTest {

    @Resource
    private BookService bookService;
    @Test
    public void paging() {
        IPage<Book> pageObject=bookService.paging(2l,"quantity",2,10);
        List<Book> bookList=pageObject.getRecords();
        for(Book book:bookList){
            System.out.println(book.getBookId());
        }
        System.out.println("===================");
        System.out.println(pageObject.getPages());
        System.out.println(pageObject.getTotal());
    }
}