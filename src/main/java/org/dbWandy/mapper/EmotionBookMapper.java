package org.dbWandy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.dbWandy.pojo.EmotionBook;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface EmotionBookMapper extends BaseMapper<EmotionBook> {
}
