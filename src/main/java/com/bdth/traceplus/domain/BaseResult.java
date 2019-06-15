package com.bdth.traceplus.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author weiming.zhu
 * @date 2019/4/22 9:45
 */
@Data
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Builder
public class BaseResult implements Serializable {

    private static final long serialVersionUID = 1L;
    private String msg;
    private Integer code;
    private Object data;
    private Page page;

    public BaseResult(String msg, Integer code, Object data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public BaseResult(String msg, Integer code, Object data, Page page) {
        this.msg = msg;
        this.code = code;
        this.data = data;
        this.page = page;
    }

    public static BaseResult success(Object data) {
        return new BaseResult("success", 200, data);
    }

    public static BaseResult successPage(Object data, Page page) {
        return new BaseResult("success", 200, data, page);
    }

    public static BaseResult failed(Object data) {
        return new BaseResult("failed", 201, data);
    }

    public static BaseResult failed(String message,Object data) {
        return new BaseResult(message, 201, data);
    }

    public static BaseResult detail(String msg, Integer code, Object data) {
        return new BaseResult(msg, code, data);
    }
}
