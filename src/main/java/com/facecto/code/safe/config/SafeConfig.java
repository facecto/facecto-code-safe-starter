package com.facecto.code.safe.config;

import com.facecto.code.safe.properties.SafeProperties;
import com.facecto.code.safe.utils.RSAUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;


/**
 * SafeConfig
 *
 * @author Jon So, https://cto.pub, https://facecto.com, https://github.com/facecto
 * @version v1.1.0 (2021/8/08)
 */
@Component
public class SafeConfig {
    @Autowired
    SafeProperties safeProperties;
    @Autowired
    RedisTemplate redisTemplate;

    String privateKeyString;
    String iv;
    String key;
    String secret;

    public Boolean getHasRsa() {
        return safeProperties.getHasRsa();
    }

    public Boolean getHasDynamic() {
        return safeProperties.getHasDynamic();
    }

    public PrivateKey getPrivateKey() throws Exception {
        privateKeyString = safeProperties.getPriKey();
        if (getHasDynamic()) {
            String tmp = (String) redisTemplate.opsForValue().get("pri-key");
            if (StringUtils.isNotEmpty(tmp)) {
                privateKeyString = tmp;
            }
        }
        return RSAUtils.getPrivateKey(privateKeyString);
    }

    public String getIv() throws Exception {
        iv = safeProperties.getIv();
        if (getHasDynamic()) {
            String tmp = (String) redisTemplate.opsForValue().get("iv");
            if (StringUtils.isNotEmpty(tmp)) {
                iv = tmp;
            }
        }
        if (!getHasRsa()) {
            return iv;
        } else {
            return RSAUtils.decrypt(iv, getPrivateKey());
        }
    }

    public String getKey() throws Exception {
        key = safeProperties.getKey();
        if (getHasDynamic()) {
            String tmp = (String) redisTemplate.opsForValue().get("key");
            if (StringUtils.isNotEmpty(tmp)) {
                key = tmp;
            }
        }
        if (!getHasRsa()) {
            return key;
        } else {
            return RSAUtils.decrypt(key, getPrivateKey());
        }
    }

    public String getSecret() throws Exception {
        secret = safeProperties.getSecret();
        if (getHasDynamic()) {
            String tmp = (String) redisTemplate.opsForValue().get("secret");
            if (StringUtils.isNotEmpty(tmp)) {
                secret = tmp;
            }
        }
        if (!getHasRsa()) {
            return secret;
        } else {
            return RSAUtils.decrypt(secret, getPrivateKey());
        }
    }
}
