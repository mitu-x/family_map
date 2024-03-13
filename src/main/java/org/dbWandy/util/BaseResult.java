package org.dbWandy.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BaseResult<T> {

    //结果消息
    private String errorMsg = "操作成功";

    //code 200-成功 300-失败
    private String errorCode = "200";

    //结果集
    private T result;

    //是否成功 true-false
    private boolean success = true;
}
