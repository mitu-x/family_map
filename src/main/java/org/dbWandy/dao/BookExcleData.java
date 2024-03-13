package org.dbWandy.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 人情簿子的中转器类
 * 与数据库相关的EmotionBook 会多几个字段
 * 这几个字段需要自定义值
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookExcleData {
    private String name;
    private int cash;
    private String remark;
}
