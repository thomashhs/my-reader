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
    public IPage<Book> paging(Long categoryId,String order,Integer page,Integer row) {
        Page<Book> p=new Page<>(page,row);
        QueryWrapper<Book> queryWrapper=new QueryWrapper<>();
        if(categoryId!=null && categoryId!=-1){
            queryWrapper.eq("category_id",categoryId);
        }
        if(order!=null){
            if(order.equals("quantity")){
                queryWrapper.orderByDesc("evaluation_quantity");
            }else if(order.equals("score")){
                queryWrapper.orderByDesc("evaluation_score");
            }
        }

        IPage<Book> pageObject=bookMapper.selectPage(p,queryWrapper);
        return pageObject;
    }

    @Override
    public Book selectBookById(Long bookId) {
        Book book=bookMapper.selectById(bookId);
        return book;
    }

    @Override
    public Book createBook(Book book) {
        bookMapper.insert(book);
        return book;
    }

    @Override
    public Book updateBook(Book book) {
        bookMapper.updateById(book);
        return book;
    }

}
