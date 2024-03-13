package org.dbWandy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("emotion_book")
public class EmotionBook {

    //主键 uuid
    @TableId(type = IdType.ASSIGN_UUID)
    private String UUID;

    //人情簿子名称--加入此数据库表中含有多个人情簿子，则以此用以区分
    @TableField(value = "book_name")
    private String bookName;

    //人名
    private String name;

    //金额
    private int cash;

    //备注
    private String remark;

    //创建时间
    @TableField(value = "create_date")
    private Timestamp createDate;

}
