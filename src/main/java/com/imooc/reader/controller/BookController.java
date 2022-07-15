package com.imooc.reader.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imooc.reader.entity.Book;
import com.imooc.reader.entity.Category;
import com.imooc.reader.entity.Evaluation;
import com.imooc.reader.service.BookService;
import com.imooc.reader.service.CategoryService;
import com.imooc.reader.service.EvaluationService;
import org.junit.runners.Parameterized;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BookController {
    @Resource
    private CategoryService categoryService;
    @Resource
    private BookService bookService;
    @Resource
    private EvaluationService evaluationService;

    @GetMapping("/")
    public ModelAndView showIndex(){
        ModelAndView mav=new ModelAndView("/index");
        List<Category> categoryList=categoryService.selectAll();
        mav.addObject("categoryList",categoryList);
        return mav;
    }

    @GetMapping("/books")
    @ResponseBody
    public IPage<Book> selectPage(Long categoryId,String order,Integer p){

        if(p==null){
            p=1;
        }
        IPage<Book> pageObject=bookService.paging(categoryId,order,p,10);
        return pageObject;
    }

    @GetMapping("/book/{id}")
    public ModelAndView showDetail(@PathVariable("id") Long bookId){
        Book book=bookService.selectBookById(bookId);
        List<Evaluation> evaluationList=evaluationService.selectByBookId(bookId);
        ModelAndView mav=new ModelAndView("/detail");
        mav.addObject("book",book);
        mav.addObject("evaluationList",evaluationList);
        return mav;
    }


}
