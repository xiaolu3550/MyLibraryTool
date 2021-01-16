package com.xiaolu.mylibrary.net.interceptors;

import android.text.TextUtils;

import com.socks.library.KLog;
import com.xiaolu.mylibrary.utils.GsonUtils;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrary.net
 * @ClassName: LoggingInterceptor
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2021/1/16 9:33
 * @UpdateUser: xiaol
 * @UpdateDate: 2021/1/16 9:33
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        String requestStr = null;
        String responseStr = null;

        Buffer bufferReq = new Buffer();
        if (request.body() != null) {
            request.body().writeTo(bufferReq);
            requestStr = bufferReq.readUtf8();
        }
        long startNs = System.nanoTime();
        Response response = chain.proceed(request);
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();

        if (!InterceptorHelper.bodyEncoded(response.headers())) {
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            Charset charset = StandardCharsets.UTF_8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(StandardCharsets.UTF_8);
                } catch (UnsupportedCharsetException e) {
                    return response;
                }
            }

            if (!InterceptorHelper.isPlaintext(buffer)) {
                return response;
            }

            if (contentLength != 0) {
                responseStr = buffer.clone().readString(charset);


            }

        }
        if (!TextUtils.isEmpty(responseStr)) {
            KLog.d(">>>请求URL--" + request.url().toString() + ">>>请求耗时：" + String.valueOf(tookMs));

            if (!TextUtils.isEmpty(requestStr)) {

                String decode = URLDecoder.decode(requestStr, "utf-8");
                String[] split = decode.split("&");
                KLog.json(GsonUtils.toJson(split));
            }
            if (!TextUtils.isEmpty(requestStr) && requestStr.length() > 500 * 50) {
                requestStr = requestStr.substring(0, 500 * 50 - 1);
            }
            KLog.d(requestStr);
            if (responseStr.length() > 500 * 50) {
                responseStr = responseStr.substring(0, 500 * 50 - 1);
            }
            KLog.d(responseStr);
        }

        return response;
    }
}
