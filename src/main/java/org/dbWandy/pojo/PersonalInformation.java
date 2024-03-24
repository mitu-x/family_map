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

    //主键 uuid
    @TableId(type = IdType.ASSIGN_UUID)
    private String uuid;

    //人名
    private String name;

    private Integer generation;

    private Integer ranking;

    @TableField(value = "date_birth")
    private Timestamp dateBirth;

    @TableField(value = "date_death")
    private Timestamp dateDeath;

    private String home_town;

    private Integer gender;

    private String ethnic;

    private String profession;

    private String degree;

    @TableField(value = "politics_status")
    private String politicsStatus;

    @TableField(value = "personal_profile")
    private String personalProfile;

    @TableField(value = "uuid_father")
    private String uuidFather;

    @TableField(value = "uuid_mother")
    private String uuidMother;

    @TableField(value = "uuid_spouse")
    private String uuidSpouse;

    @TableField(value = "uuid_siblings")
    private String uuidSiblings;

    @TableField(value = "uuid_children")
    private String uuidChildren;
}
