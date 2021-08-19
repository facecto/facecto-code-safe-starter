package com.facecto.code.safe;

import lombok.Data;
import org.apache.http.HttpStatus;

import java.io.Serializable;

/**
 * SafeResult
 * @author Jon So, https://cto.pub, https://facecto.com, https://github.com/facecto
 * @version v1.1.0 (2021/8/08)
 */
@Data
public class SafeResult implements Serializable {
    private static final long serialVersionUID = -3301191414290151056L;
    private Integer code;
    private String message;
    private String sign;
    private String data;

    public SafeResult(Integer code, String message, String sign, String data) {
        this.code = 0;
        this.sign = sign;
        this.data = data;
        this.message = message;
    }

    public static SafeResult error() {
        return new SafeResult(HttpStatus.SC_INTERNAL_SERVER_ERROR, null, null, null);
    }

    public static SafeResult error(Integer code) {
        return new SafeResult(code, null, null, null);
    }

    public static SafeResult error(Integer code, String message) {
        return new SafeResult(code, message, null, null);
    }

    public static SafeResult ok(String sign, String data) {
        return new SafeResult(0, null, sign, data);
    }

    public static SafeResult ok(String sign, String data, String message) {
        return new SafeResult(0, message, sign, data);
    }
}
