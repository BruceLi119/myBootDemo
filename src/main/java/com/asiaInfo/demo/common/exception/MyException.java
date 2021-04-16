package com.asiaInfo.demo.common.exception;


import com.asiaInfo.demo.common.bean.ErrorCode;
import lombok.Data;

/**
 * 自定义API异常
 * @author li.shuGuang
 * @date 2021/04/16
 */
@Data
public class MyException extends RuntimeException {

    /**
     * 错误码
     */
    protected long errorCode;

    /**
     * 错误信息
     */
    protected String errorMsg;

    public MyException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public MyException(long errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public MyException(long errorCode, String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public MyException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode.getCode();
        this.errorMsg = errorCode.getMessage();
    }

    public MyException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode.getCode();
        this.errorMsg = errorCode.getMessage();
    }
}
