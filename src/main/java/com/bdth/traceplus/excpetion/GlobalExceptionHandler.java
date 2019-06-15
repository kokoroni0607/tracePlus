package com.bdth.traceplus.excpetion;

import com.bdth.traceplus.domain.BaseResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hj
 * @create 2019-04-23 15:31
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TraceException.class)
    @ResponseBody
    public BaseResult handler(TraceException e) {
        return BaseResult.failed(e.getMessage(), false);
    }
}
