package com.imooc.reader.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imooc.reader.entity.Book;
import com.imooc.reader.entity.Category;

import java.util.List;

public interface BookService {
    public IPage<Book> paging(Long categoryId,String order,Integer page,Integer row);

    public Book selectBookById(Long bookId);

    public Book createBook(Book book);

    public Book updateBook(Book book);
}
