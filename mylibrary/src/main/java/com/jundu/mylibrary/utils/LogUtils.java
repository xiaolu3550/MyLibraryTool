package com.jundu.mylibrary.utils;

import android.content.Context;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class LogUtils {
    /**
     * isWrite:用于开关是否吧日志写入txt文件中
     */
    private static boolean isWrite = false;
    /**
     * isDebug :是用来控制，是否打印日志
     */
    private static boolean isDeBug;
    /**
     * 存放日志文件的所在路径
     */
    private static final String DIRPATH = "/log";
    // private static final String DIRPATH = "/log";
    /**
     * 存放日志的文本名
     */
    private static final String LOGNAME = "log.txt";
    // private static final String LOGNAME = "log.txt";
    /**
     * 设置时间的格式
     */
    private static final String INFORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * VERBOSE日志形式的标识符
     */
    public static final int VERBOSE = 5;
    /**
     * DEBUG日志形式的标识符
     */
    public static final int DEBUG = 4;
    /**
     * INFO日志形式的标识符
     */
    public static final int INFO = 3;
    /**
     * WARN日志形式的标识符
     */
    public static final int WARN = 2;
    /**
     * ERROR日志形式的标识符
     */
    public static final int ERROR = 1;

    private static final String TAG = "LogUtils";

    public static void showLongToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG)
                .show();
    }

    public static void showShortToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT)
                .show();
    }

    /**
     * 把异常用来输出日志的综合方法
     *
     * @param tag       日志标识
     * @param throwable 抛出的异常
     * @param type      日志类型
     */
    public static void log(String tag, Throwable throwable, int type) {
        log(tag, exToString(throwable), type);
    }

    /**
     * 用来输出日志的综合方法（文本内容）
     *
     * @param tag  日志标识
     * @param msg  要输出的内容
     * @param type 日志类型
     */
    public static void log(String tag, String msg, int type) {
        switch (type) {
            case VERBOSE:
                V(tag, msg);// verbose等级
                break;
            case DEBUG:
                D(tag, msg);// debug等级
                break;
            case INFO:
                I(tag, msg);// info等级
                break;
            case WARN:
                W(tag, msg);// warn等级
                break;
            case ERROR:
                E(tag, msg);// error等级
                break;
            default:
                break;
        }
    }

    public static void log(String msg, int type) {
        switch (type) {
            case VERBOSE:
                V(TAG, msg);// verbose等级
                break;
            case DEBUG:
                D(TAG, msg);// debug等级
                break;
            case INFO:
                I(TAG, msg);// info等级
                break;
            case WARN:
                W(TAG, msg);// warn等级
                break;
            case ERROR:
                E(TAG, msg);// error等级
                break;
            default:
                break;
        }
    }

    public static void isWrite(boolean isWrite) {
        LogUtils.isWrite = isWrite;
    }

    public static void isDeBug(boolean isDeBug) {
        LogUtils.isDeBug = isDeBug;
    }

    /**
     * verbose等级的日志输出
     *
     * @param msg 要输出的内容
     */
    private static void V(String tag, String msg) {
        // 是否开启日志输出
        if (isDeBug) {
            Log.v(tag, msg);
        }
        // 是否将日志写入文件
        if (isWrite) {
            write(tag, msg);
        }
    }

    /**
     * debug等级的日志输出
     *
     * @param msg 内容
     */
    private static void D(String tag, String msg) {
        if (isDeBug) {
            Log.d(tag, msg);
        }
        if (isWrite) {
            write(tag, msg);
        }
    }

    /**
     * info等级的日志输出
     *
     * @param msg 内容
     */
    private static void I(String tag, String msg) {
        if (isDeBug) {
            Log.i(tag, msg);
        }
        if (isWrite) {
            write(tag, msg);
        }
    }

    /**
     * warn等级的日志输出
     *
     * @param msg 内容
     */
    private static void W(String tag, String msg) {
        if (isDeBug) {
            Log.w(tag, msg);
        }
        if (isWrite) {
            write(tag, msg);
        }
    }

    /**
     * error等级的日志输出
     *
     * @param msg 内容
     */
    private static void E(String tag, String msg) {
        if (isDeBug) {
            Log.e(tag, msg);
        }
        if (isWrite) {
            write(tag, msg);
        }
    }

    /**
     * 用于把日志内容写入制定的文件
     *
     * @param ex 异常
     */
    public static void write(Throwable ex) {
        write("", exToString(ex));
    }

    /**
     * 用于把日志内容写入制定的文件
     *
     * @param tag 标识
     * @param msg 要输出的内容
     */
    private static void write(String tag, String msg) {
        String path = FileUtils.createMkdirsAndFiles(DIRPATH, LOGNAME);
        if (TextUtils.isEmpty(path)) {
            return;
        }
        String log = DateFormat.format(INFORMAT, System.currentTimeMillis())
                + tag
                + "========>>"
                + msg
                + "\n=================================分割线=================================";
        FileUtils.write2File(path, log, true);
    }


    /**
     * 把异常信息转化为字符串
     *
     * @param ex 异常信息
     * @return 异常信息字符串
     */
    private static String exToString(Throwable ex) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        printWriter.close();
        String result = writer.toString();
        return result;
    }
}
