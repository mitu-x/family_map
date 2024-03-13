package org.dbWandy.service;

import org.dbWandy.pojo.EmotionBook;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmotionBookService {

    /**
     * 插入多条数据
     *
     * @param bookList book类型列表
     * @return int
     */
    Integer insertBookList(List<EmotionBook> bookList);

    /**
     * 根据人名查询多条数据
     *
     * @param name 人名
     * @return List<EmotionBook>
     */
    List<EmotionBook> getListByName(String name);

    /**
     * 查询所有数据
     *
     * @param bookName 人情簿子名称
     * @return List<EmotionBook>
     */
    List<EmotionBook> getAllByBookName(String bookName);

    /**
     * 删除某个人情簿子
     *
     * @param bookName 人情簿子名称
     * @return int
     */
    int deleteByBookName(String bookName);
}
