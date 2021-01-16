package com.xiaolu.mylibrary.net.interceptors;

import java.io.EOFException;

import okhttp3.Headers;
import okio.Buffer;

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrary.net
 * @ClassName: InterceptorHelper
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2021/1/16 9:34
 * @UpdateUser: xiaol
 * @UpdateDate: 2021/1/16 9:34
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
public class InterceptorHelper {

    public static boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }

    public static boolean isPlaintext(Buffer buffer) throws EOFException {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }
}
