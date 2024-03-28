package org.dbWandy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@ToString
@TableName("personal_information")
public class PersonalInformation {

    /**
     * uuid 自增 主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String uuid;

    /**
     * 姓名
     */
    private String name;

    /**
     * 代数 11代表第一代 12 第二代 以此类推
     */
    private Integer generation;

    /**
     * 排行 10 第一个 1x 第一个的配偶 20 第二个 以此类推
     */
    private Integer ranking;

    /**
     * 出生日期
     */
    @TableField(value = "date_birth")
    private Timestamp dateBirth;

    /**
     * 逝世日期
     */
    @TableField(value = "date_death")
    private Timestamp dateDeath;

    /**
     * 籍贯--家乡
     */
    private String home_town;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 民族
     */
    private String ethnic;

    /**
     * 工作
     */
    private String profession;

    /**
     * 学历
     */
    private String degree;

    /**
     * 政治面貌
     */
    @TableField(value = "politics_status")
    private String politicsStatus;

    /**
     * 个人简介
     */
    @TableField(value = "personal_profile")
    private String personalProfile;

    /**
     * 父亲的uuid
     */
    @TableField(value = "uuid_father")
    private String uuidFather;

    /**
     * 母亲的uuid
     */
    @TableField(value = "uuid_mother")
    private String uuidMother;

    /**
     * 配偶的uuid 以逗号隔开的字符串 例如“uuid，uuid,uuid”
     */
    @TableField(value = "uuid_spouse")
    private String uuidSpouse;

    ///**
    // * 兄弟的uuid 以逗号隔开的字符串 例如“uuid，uuid,uuid”
    // */
    //@TableField(value = "uuid_siblings")
    //private String uuidSiblings;
    //
    ///**
    // * 子女的uuid 以逗号隔开的字符串 例如“uuid，uuid,uuid”
    // */
    //@TableField(value = "uuid_children")
    //private String uuidChildren;
}
