package com.facecto.code.safe.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Jon So
 * @author page https://cto.pub
 * @date 2021/8/8
 */
@Slf4j
public class JSONUtils {
    public static boolean hasJsonString(String jsonStr) {
        try {
            Object object = JSON.parse(jsonStr);
            if (object instanceof JSONObject) {
                return true;
            } else return object instanceof JSONArray;
        } catch (Exception e) {
            log.info("the target is not json object!");
        }
        return false;
    }
}
