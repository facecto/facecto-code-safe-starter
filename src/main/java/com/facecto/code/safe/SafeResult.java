package com.facecto.code.safe;

import lombok.Data;
import org.apache.http.HttpStatus;

import java.io.Serializable;

/**
 * SafeResult
 *
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

    /**
     * SafeResult
     *
     * @param code    code
     * @param message message
     * @param sign    sign
     * @param data    data
     */
    public SafeResult(Integer code, String message, String sign, String data) {
        this.code = 0;
        this.sign = sign;
        this.data = data;
        this.message = message;
    }

    /**
     * SafeResult
     *
     * @return SafeResult
     */
    public static SafeResult error() {
        return new SafeResult(HttpStatus.SC_INTERNAL_SERVER_ERROR, null, null, null);
    }

    /**
     * SafeResult
     *
     * @param code code
     * @return SafeResult
     */
    public static SafeResult error(Integer code) {
        return new SafeResult(code, null, null, null);
    }

    /**
     * SafeResult
     *
     * @param code    code
     * @param message message
     * @return SafeResult
     */
    public static SafeResult error(Integer code, String message) {
        return new SafeResult(code, message, null, null);
    }

    /**
     * SafeResult
     *
     * @param sign sign
     * @param data data
     * @return SafeResult
     */
    public static SafeResult ok(String sign, String data) {
        return new SafeResult(0, null, sign, data);
    }

    /**
     * SafeResult
     *
     * @param sign    sign
     * @param data    data
     * @param message message
     * @return SafeResult
     */
    public static SafeResult ok(String sign, String data, String message) {
        return new SafeResult(0, message, sign, data);
    }

    /**
     * SafeResult
     *
     * @param sign    sign
     * @param data    data
     * @param message message
     * @param code    code
     * @return SafeResult
     */
    public static SafeResult ok(String sign, String data, String message, Integer code) {
        return new SafeResult(code, message, sign, data);
    }
}
