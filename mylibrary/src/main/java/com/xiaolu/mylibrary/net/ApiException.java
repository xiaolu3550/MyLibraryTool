package com.xiaolu.mylibrary.net;

/**
 * Created by ljn on 2018/3/30.
 * Explain 请求异常封装类
 */
public class ApiException extends RuntimeException {
    private String resultCode;
    private String timeSteam;

    /**
     * 动态指定message
     * @param resultCode
     */
    public ApiException(String resultCode) {
        this(resultCode, getApiExceptionMessage(resultCode));
    }

    /**
     * 静态指定message
     * @param resultCode
     * @param detailMessage
     */
    public ApiException(String resultCode, String detailMessage) {
        this(resultCode, detailMessage, "");
    }

    /**
     * 静态指定message
     * @param resultCode
     * @param detailMessage
     */
    public ApiException(String resultCode, String detailMessage, String timeSteam) {
        super(detailMessage);
        this.resultCode = resultCode;
        this.timeSteam = timeSteam;
    }

    /**
     * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
     * 需要根据错误码对错误信息进行一个转换，在显示给用户
     * @param code
     * @return
     */
    private static String getApiExceptionMessage(String code) {
        String message = "";
        return message;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getTimeSteam() {
        return timeSteam;
    }
}