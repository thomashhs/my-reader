package com.imooc.reader.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imooc.reader.entity.Book;
import com.imooc.reader.entity.Evaluation;

import java.util.List;

public interface EvaluationService {
    public List<Evaluation> selectByBookId(Long bookId);

    public IPage<Evaluation> paging(Integer page,Integer row);

    public Evaluation updateById(Long evaluationId,String reason);
}
