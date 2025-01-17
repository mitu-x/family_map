package org.dbWandy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.dbWandy.mapper.EmotionBookMapper;
import org.dbWandy.pojo.EmotionBook;
import org.dbWandy.service.EmotionBookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmotionBookServiceImpl implements EmotionBookService {

    EmotionBookMapper bookMapper;

    public EmotionBookServiceImpl(EmotionBookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    /**
     * 插入多条数据
     *
     * @param bookList book实体类列表
     * @return int
     */
    @Override
    public Integer insertBookList(List<EmotionBook> bookList) {
        return bookMapper.insertBatchSomeColumn(bookList);
    }

    /**
     * 根据人名查询多条数据
     *
     * @param name 人名
     * @return List<EmotionBook>
     */
    @Override
    public List<EmotionBook> getListByName(String name) {
        //创建wrapper
        LambdaQueryWrapper<EmotionBook> wrapper = new LambdaQueryWrapper<>();
        //注入查询条件
        wrapper.like(!Objects.equals(name, ""), EmotionBook::getName, name);
        //返回查询结果
        return bookMapper.selectList(wrapper);
    }

    /**
     * 查询所有数据
     *
     * @param bookName 人情簿子名称
     * @return List<EmotionBook>
     */
    @Override
    public List<EmotionBook> getAllByBookName(String bookName) {
        //创建wrapper
        LambdaQueryWrapper<EmotionBook> wrapper = new LambdaQueryWrapper<>();
        //注入查询条件
        wrapper.eq(EmotionBook::getBookName, bookName);
        //返回查询结果
        return bookMapper.selectList(wrapper);
    }

    /**
     * 删除某个人情簿子
     *
     * @param bookName 人情簿子名称
     * @return int
     */
    @Override
    public int deleteByBookName(String bookName) {
        //创建wrapper
        LambdaQueryWrapper<EmotionBook> wrapper = new LambdaQueryWrapper<>();
        //注入条件
        wrapper.eq(EmotionBook::getBookName, bookName);
        //返回结果
        return bookMapper.delete(wrapper);
    }

}
