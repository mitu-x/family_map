package org.dbWandy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.dbWandy.mapper.EmotionBookMapper;
import org.dbWandy.pojo.EmotionBook;
import org.dbWandy.service.EmotionBookService;

import java.util.List;

public class EmotionBookServiceImpl implements EmotionBookService {

    EmotionBookMapper bookMapper;

    public EmotionBookServiceImpl(EmotionBookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    /**
     * 插入多条数据
     * @param bookList book实体类列表
     * @return int
     */
    @Override
    public int insertBookList(List<EmotionBook> bookList) {
        int r = 0;
        //遍历集合
        for (EmotionBook book : bookList) {
            //查询数据
            r += bookMapper.insert(book) > 0 ? 1 : 0;
        }
        return r;
    }

    /**
     * 根据人名查询多条数据
     * @param name 人名
     * @return List<EmotionBook>
     */
    @Override
    public List<EmotionBook> getListByName(String name) {
        //创建wrapper
        LambdaQueryWrapper<EmotionBook> wrapper = new LambdaQueryWrapper<>();
        //注入查询条件
        wrapper.eq(EmotionBook::getName, name);
        //返回查询结果
        return bookMapper.selectList(wrapper);
    }

    /**
     * 查询所有数据
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
