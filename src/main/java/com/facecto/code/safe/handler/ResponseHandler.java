package com.facecto.code.safe.handler;

import com.facecto.code.base.CodeResult;
import com.facecto.code.safe.SafeResult;
import com.facecto.code.safe.annotation.Encrypt;
import com.facecto.code.safe.config.SafeConfig;
import com.facecto.code.safe.properties.SafeProperties;
import com.facecto.code.safe.utils.CryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author Jon So
 * @author page https://cto.pub
 * @date 2021/8/8
 */
@EnableConfigurationProperties(SafeProperties.class)
@ControllerAdvice
public class ResponseHandler implements ResponseBodyAdvice<Object> {
    @Autowired
    SafeConfig safeConfig;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return methodParameter.hasMethodAnnotation(Encrypt.class);
    }

    @Override
    public SafeResult beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        try {
            String key = safeConfig.getKey();
            String iv = safeConfig.getIv();
            String secret = safeConfig.getSecret();
            if (body instanceof CodeResult) {
                CodeResult codeResult = (CodeResult) body;
                if (codeResult.getData() != null) {
                    SafeResult safeResult = CryptUtils.encryptData(codeResult, key, iv, secret);
                    return safeResult;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
