package com.imooc.reader.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.reader.entity.Book;
import com.imooc.reader.entity.Category;
import com.imooc.reader.mapper.BookMapper;
import com.imooc.reader.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("bookService")
public class BookServiceImpl implements BookService{

    @Resource
    private BookMapper bookMapper;


    @Override
    public IPage<Book> paging(Integer page, Integer row) {
        Page<Book> p=new Page<>(page,row);
        IPage<Book> pageObject=bookMapper.selectPage(p,new QueryWrapper<>());
        return pageObject;
    }
}
