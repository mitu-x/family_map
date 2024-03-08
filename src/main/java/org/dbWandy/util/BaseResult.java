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
    private String errorMsg;

    //code
    private String errorCode;

    //结果集
    private T result;
}
