package com.bdth.traceplus.excpetion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author hj
 * @create 2019-04-23 15:25
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ExceptionEnum {

    UNKNOWN_ERROR(500, "未知错误"),
    UPLOAD_FAIL(500, "图片上传失败"),
    DELETE_FAIL(500, "根据id删除日志记录失败"),
    DATA_CONVERTER_ERROR(500, "数据转化异常"),
    ACCOUNT_ERROR(500, "用户不存在"),
    TOKEN_ERROR(201, "token 不正确或不存在"),
    ;

    private Integer code;
    private String message;


}
