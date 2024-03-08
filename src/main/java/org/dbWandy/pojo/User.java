package org.dbWandy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User {

    //id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    //账户名
    private String username;

    //真实姓名
    private String trueName;

    //密码
    private String password;
    
    //年龄
    private int age;
}
