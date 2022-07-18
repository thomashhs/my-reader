package com.imooc.reader.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.reader.entity.Book;
import com.imooc.reader.entity.Evaluation;
import com.imooc.reader.entity.Member;
import com.imooc.reader.mapper.BookMapper;
import com.imooc.reader.mapper.EvaluationMapper;
import com.imooc.reader.mapper.MemberMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("evaluationService")
public class EvaluationServiceImpl implements EvaluationService{
    @Resource
    private EvaluationMapper evaluationMapper;
    @Resource
    private MemberMapper memberMapper;
    @Resource
    private BookMapper bookMapper;

    @Override
    public List<Evaluation> selectByBookId(Long bookId) {
        Book book=bookMapper.selectById(bookId);
        QueryWrapper<Evaluation> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("book_id",bookId);
        queryWrapper.eq("state","enable");
        queryWrapper.orderByDesc("create_time");
        List<Evaluation> evaluationList=evaluationMapper.selectList(queryWrapper);
        for(Evaluation evaluation:evaluationList){
            Member member=memberMapper.selectById(evaluation.getMemberId());
            evaluation.setMember(member);
            evaluation.setBook(book);
        }
        return evaluationList;
    }

    @Override
    public IPage<Evaluation> paging(Integer page, Integer row) {
        Page<Evaluation> p=new Page<>(page,row);
        QueryWrapper<Evaluation> queryWrapper=new QueryWrapper<>();
        IPage<Evaluation> pageObject=evaluationMapper.selectPage(p,queryWrapper);
        List<Evaluation> evaluationList=pageObject.getRecords();
        for(Evaluation evaluation:evaluationList){
            Book book=bookMapper.selectById(evaluation.getBookId());
            Member member=memberMapper.selectById(evaluation.getMemberId());
            evaluation.setMember(member);
            evaluation.setBook(book);
        }
        return pageObject;
    }

    @Override
    public Evaluation updateById(Long evaluationId,String reason) {
        Evaluation evaluation=evaluationMapper.selectById(evaluationId);
        if(evaluation.getState().equals("enable")){
            evaluation.setState("disable");
            evaluation.setDisableReason(reason);
            evaluation.setDisableTime(new Date());
            evaluationMapper.updateById(evaluation);
        }
        return evaluation;
    }
}
