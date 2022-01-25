package com.facecto.code.safe.handler;

import com.alibaba.fastjson.JSONObject;
import com.facecto.code.base.CodeResult;
import com.facecto.code.safe.SafeResult;
import com.facecto.code.safe.annotation.Decrypt;
import com.facecto.code.safe.config.SafeConfig;
import com.facecto.code.safe.properties.SafeProperties;
import com.facecto.code.safe.utils.AESUtils;
import com.facecto.code.safe.utils.CryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/**
 * RequestHandler
 * @author Jon So, https://cto.pub, https://facecto.com, https://github.com/facecto
 * @version v1.1.0 (2021/8/08)
 */
@EnableConfigurationProperties(SafeProperties.class)
@ControllerAdvice
public class RequestHandler extends RequestBodyAdviceAdapter {

    @Autowired
    SafeConfig safeConfig;

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return methodParameter.hasMethodAnnotation(Decrypt.class) || methodParameter.hasParameterAnnotation(Decrypt.class);
    }

    @Override
    public HttpInputMessage beforeBodyRead(final HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        byte[] body = new byte[inputMessage.getBody().available()];
        inputMessage.getBody().read(body);
        try {
            String key = safeConfig.getKey();
            String iv = safeConfig.getIv();

            SafeResult safeResult = JSONObject.parseObject(new String(body), SafeResult.class);
            CodeResult codeResult = CryptUtils.decryptData(safeResult, key, iv);
            String jsonString = JSONObject.toJSONString(codeResult.getData());

            final ByteArrayInputStream stream = new ByteArrayInputStream(jsonString.getBytes(StandardCharsets.UTF_8));
            return new HttpInputMessage() {
                @Override
                public InputStream getBody() throws IOException {
                    return stream;
                }

                @Override
                public HttpHeaders getHeaders() {
                    return inputMessage.getHeaders();
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.beforeBodyRead(inputMessage, parameter, targetType, converterType);
    }
}
