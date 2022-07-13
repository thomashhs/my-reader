package com.imooc.reader.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imooc.reader.entity.Book;
import com.imooc.reader.entity.Category;

import java.util.List;

public interface BookService {
    public IPage<Book> paging(Integer page,Integer row);
}
