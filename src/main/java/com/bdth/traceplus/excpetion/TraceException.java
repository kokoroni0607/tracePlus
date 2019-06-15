package com.bdth.traceplus.excpetion;

/**
 * @author hj
 * @create 2019-04-23 15:23
 */
public class TraceException extends RuntimeException {

    private ExceptionEnum exceptionEnum;


    public TraceException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
    }
}
