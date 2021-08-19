package com.facecto.code.safe.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * SafeProperties
 * @author Jon So, https://cto.pub, https://facecto.com, https://github.com/facecto
 * @version v1.1.0 (2021/8/08)
 */
@Configuration
@ConfigurationProperties(prefix = "app.safe")
public class SafeProperties {
    private final static String DEFAULT_KEY = "01234567891234560123456789123456";
    private final static String DEFAULT_IV = "0123456789123456";
    private final static String DEFAULT_SECRET = "1829b4abbba0794301a075fc2283d2ba";
    private final static String DEFAULT_PRIKEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCvsTNnB1So7kr89mlLt2FVRPoMGyWnMpwCCtXwaY+eSyNsfhDMzk6sy14++Ys/EtkbFzg8KVKjT7EtWxz0L3JxCzvXEH/xNwNxa6ChYwhMkCKpsHWYXTOjRqu6SUksllLKNIttaplcTt1xbZ/zoVD4J24GllaiNNbZZVFI/u6vEZHylzZHGbbVu8ZbSL0GBtRWCu9FnGu9e0v2lQM8VnD3p4Hzlljynv8NvPrIr4kQ1GfZ48iwuF8Pa639iVHRJ8+rk08oCa068fYTBrBTpdDbiLV2m41E9N1BQRP1zvwsdx8aVa9jZxx2C515wdH7YLuE3mjCynl3oWdVbgRXmo0ZAgMBAAECggEBAJ1qyAOoLdKur8G3huCwjiU4iFIQgMwuLFooG0gtTOEnsbWjvqnCr/tivC7wqDL7UEtVoq1E2SY6YSWiXW7slD11M0ifIAOgpxUYufPgJ69x9ZzU3oiF+Z5x06w1vbqVddh2+/YtbPuoLgFTl91SzJmLuqlsSnNLpLVkclBVGscAGckFz/7X0jW4M8G9nN53SwFPa57t0kXi1KPmW3HEZnH8x/SluiOzg/3UzpzBFqUy42jIsLrVplnTzdrBHg4UEEQxKdyPESmkqXfONanAMWfttubcy7MBnRq3faE16o1oWEZcYIfUWfoVstd55S1KgVwkgx3vLOHZYG0G9Mt6UAECgYEA5nisyEvHf/YUiumuJVU60UW3J3nIxLCv9v7MmhwWTMAMPwuN/CziHQw8+2VYdey51jubBqiC2e/OBfmms24kpCA04yQ40k5xbg350Z6aCnwyQeQFi4ISzCM7DS3/w0G4dvXCNRun19SZ9Wvn3l68lIEZ7jvsyZXQIF2L3c5NOukCgYEAwycwNv6ioxLyhqpysul9SYxgI/MOusY//FmZ0TwDfSBvEDA9LArgAXWacz5w8GAw2lJWmQA6rTwZ7QJJDM/j7VtfDub326c1z3bJbZmbWfAnBffItRoEvAhPTWd2cWdiRtcyoMQ89VOHgMtjqYBAnkQx/zCRvydnzE++AnZnArECgYEAowEWs1NldouFW/qKwLzXLMwyDimEZhjRW9A6xYS4APCTuMNjWMgl5IWTW/sB5eXYSj0+GCd25M1G2dMIpD9yiuJ8hWpJBfWVJFGeLzQNnvzYTuIHSfRldxwz186A5ojE/t4yzX0R6QTlHz+CbzRyDFaDEIwxnSHoYfvHwMj4toECgYApJ9GON4Ma+vr0lWuc+Yq3gQghbLfVgcuqr0a2Dn7522YwtsufxydYh3GsDiYJO2/yCt1CZP7626roBcxcDFeYDeXtIZ5xz0CU4Qs3tuZBQFkqdAf60WLgOKxouYIMBm5+XBHMP7ZPUm9IJRx+eZKnDeCxXY/PDUNhnguHP2sX8QKBgH1UBvYdre62uunCc+nexSlV4Ce7u/owPYgLe/JAbl72JlTOiKuvfiPIjD5KT1Ga6U4BgLjg8S0io9oNi+s2sjgv+LiwOGGAIRfz2f5u58srTCKxFjhnBT7LIpfsJOvmgyZ62yPV6BADYY1IJS79YHbWm2Ku2u3nMrdrT2rB59EF";
    private final static boolean DEFAULT_RSA = true;
    private final static boolean DEFAULT_DYNAMIC = false;
    private String key = DEFAULT_KEY;
    private String iv = DEFAULT_IV;
    private String secret = DEFAULT_SECRET;
    private boolean hasRsa = DEFAULT_RSA;
    private boolean hasDynamic = DEFAULT_DYNAMIC;
    private String priKey = DEFAULT_PRIKEY;

    public boolean getHasRsa() {
        return hasRsa;
    }

    public boolean getHasDynamic() {
        return hasDynamic;
    }

    public String getPriKey() {
        return priKey;
    }

    public String getKey() {
        return key;
    }

    public String getIv() {
        return iv;
    }

    public String getSecret() {
        return secret;
    }

    public void setHasDynamic(Boolean value) {
        this.hasDynamic = value;
    }

    public void setPriKey(String value) {
        this.priKey = value;
    }

    public void setHasRsa(boolean value) {
        this.hasRsa = value;
    }

    public void setKey(String value) {
        this.key = value;
    }

    public void setIv(String value) {
        this.iv = value;
    }

    public void setSecret(String value) {
        this.secret = value;
    }
}
