package com.imooc.reader.service;

import com.imooc.reader.entity.Test;
import com.imooc.reader.mapper.TestMapper;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MyBatisPlusTest {

    @Resource
    private TestMapper testMapper;

    @org.junit.Test
    public void insertTest(){
        Test test=new Test();
        test.setContent("MyBatisPlus测试");
        testMapper.insert(test);
    }

    @org.junit.Test
    public void testUpdate(){
        Test test=testMapper.selectById(4);
        test.setContent("update测试");
        testMapper.updateById(test);
    }

    @org.junit.Test
    public void testDelete(){
        testMapper.deleteById(5);
    }
}
