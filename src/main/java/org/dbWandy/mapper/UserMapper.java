package org.dbWandy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.dbWandy.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
//@Mapper
public interface UserMapper extends BaseMapper<User> {
}
