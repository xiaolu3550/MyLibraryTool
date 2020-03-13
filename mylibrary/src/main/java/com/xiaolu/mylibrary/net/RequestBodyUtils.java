package com.xiaolu.mylibrary.net;

import com.xiaolu.mylibrary.utils.GsonUtils;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RequestBodyUtils {
    public static RequestBody setRequestBody_JSON(Map<String, Object> stringObjectMap) {
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                GsonUtils.toJson(stringObjectMap));
    }

    public static RequestBody setRequestBody(String requestBody) {
        return RequestBody.create(MediaType.parse("Accept-Encoding identity"), requestBody);
    }
}
