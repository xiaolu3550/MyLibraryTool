package com.xiaolu.mylibrary.utils;

import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.tencent.mmkv.MMKV;


import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrarytool.utils
 * @ClassName: MMKVUtils
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/11/30 11:27
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/11/30 11:27
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
public class MMKV_Utils {
    private static MMKV_Utils mmkvUtils = null;

    public static MMKV_Utils getInstance() {
        if (mmkvUtils == null) {
            mmkvUtils = new MMKV_Utils();
        }
        return mmkvUtils;
    }

    public MMKV_Utils() {
        String rootDir = MMKV.getRootDir();
        if (rootDir == null) {
            throw new IllegalStateException("You should Call MMKV.initialize() first.");
        }
    }

    public String getRootDir() {
        return MMKV.getRootDir();
    }

    private MMKV init() {
        return MMKV.defaultMMKV();
    }

    private MMKV init(String id) {
        return MMKV.mmkvWithID(id);
    }

    public void put(@NotNull String key, @NotNull Object valve) {
        if (valve instanceof Integer) {
            init().encode(key, (int) valve);
        } else if (valve instanceof String) {
            init().encode(key, (String) valve);
        } else if (valve instanceof Boolean) {
            init().encode(key, (boolean) valve);
        } else if (valve instanceof Long) {
            init().encode(key, (long) valve);
        } else if (valve instanceof Float) {
            init().encode(key, (float) valve);
        } else if (valve instanceof Double) {
            init().encode(key, (double) valve);
        } else if (valve instanceof Byte[]) {
            init().encode(key, (byte[]) valve);
        } else {
            return;
        }
    }

    public void put(@NotNull String key, @NotNull Parcelable parcelable) {
        init().encode(key, parcelable);
    }

    public void put(@NotNull String key, @NotNull Set<String> set) {
        init().encode(key, set);
    }

    public void putWithId(@NotNull String id, @NotNull String key, @NotNull Object valve) {
        if (valve instanceof Integer) {
            init(id).encode(key, (int) valve);
        } else if (valve instanceof String) {
            init(id).encode(key, (String) valve);
        } else if (valve instanceof Boolean) {
            init(id).encode(key, (boolean) valve);
        } else if (valve instanceof Long) {
            init(id).encode(key, (long) valve);
        } else if (valve instanceof Float) {
            init(id).encode(key, (float) valve);
        } else if (valve instanceof Double) {
            init(id).encode(key, (double) valve);
        } else if (valve instanceof Byte[]) {
            init(id).encode(key, (byte[]) valve);
        } else {
            return;
        }
    }

    public void putWithId(@NotNull String id, @NotNull String key, @NotNull Parcelable parcelable) {
        init(id).encode(key, parcelable);
    }

    public void putWithId(@NotNull String id, @NotNull String key, @NotNull Set<String> set) {
        init(id).encode(key, set);
    }

    public String getString(String key) {
        return init().decodeString(key);
    }

    public String getString(String key, String defaultValue) {
        return init().decodeString(key, defaultValue);
    }

    public String getStringWithId(String id, String key) {
        return init(id).decodeString(key);
    }

    public String getStringWithId(String id, String key, String defaultValue) {
        return init(id).decodeString(key, defaultValue);
    }

    public boolean getBoolean(String key) {
        return init().decodeBool(key);
    }

    public boolean getBooleanWithId(String id, String key) {
        return init(id).decodeBool(key);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return init().decodeBool(key, defaultValue);
    }

    public boolean getBooleanWithId(String id, String key, boolean defaultValue) {
        return init(id).decodeBool(key, defaultValue);
    }

    public int getInt(String key) {
        return init().decodeInt(key);
    }

    public int getIntWithId(String id, String key) {
        return init(id).decodeInt(key);
    }

    public int getInt(String key, int defaultValue) {
        return init().decodeInt(key, defaultValue);
    }

    public int getIntWithId(String id, String key, int defaultValue) {
        return init(id).decodeInt(key, defaultValue);
    }

    public long getLong(String key) {
        return init().decodeLong(key);
    }

    public long getLongWithId(String id, String key) {
        return init(id).decodeLong(key);
    }

    public long getLong(String key, long defaultValue) {
        return init().decodeLong(key, defaultValue);
    }

    public long getLongWithId(String id, String key, long defaultValue) {
        return init(id).decodeLong(key, defaultValue);
    }

    public float getFloat(String key) {
        return init().decodeFloat(key);
    }

    public float getFloatWithId(String id, String key) {
        return init(id).decodeFloat(key);
    }

    public float getFloat(String key, float defaultValue) {
        return init().decodeFloat(key, defaultValue);
    }

    public float getFloatWithId(String id, String key, float defaultValue) {
        return init(id).decodeFloat(key, defaultValue);
    }

    public double getDouble(String key) {
        return init().decodeDouble(key);
    }

    public double getDoubleWithId(String id, String key) {
        return init(id).decodeDouble(key);
    }

    public double getDouble(String key, double defaultValue) {
        return init().decodeDouble(key, defaultValue);
    }

    public double getDoubleWithId(String id, String key, double defaultValue) {
        return init(id).decodeDouble(key, defaultValue);
    }

    public byte[] getBytes(String key) {
        return init().decodeBytes(key);
    }

    public byte[] getBytesWithId(String id, String key) {
        return init(id).decodeBytes(key);
    }

    public byte[] getBytes(String key, byte[] defaultValue) {
        return init().decodeBytes(key, defaultValue);
    }

    public byte[] getBytesWithId(String id, String key, byte[] defaultValue) {
        return init(id).decodeBytes(key, defaultValue);
    }

    public <T extends Parcelable> T getParcelable(String key, Class<T> aClass) {
        return init().decodeParcelable(key, aClass);
    }

    public <T extends Parcelable> T getParcelableWithId(String id, String key, Class<T> aClass) {
        return init(id).decodeParcelable(key, aClass);
    }

    public <T extends Parcelable> T getParcelable(String key, Class<T> aClass, T defaultValue) {
        return init().decodeParcelable(key, aClass, defaultValue);
    }

    public <T extends Parcelable> T getParcelableWithId(String id, String key, Class<T> aClass, T defaultValue) {
        return init(id).decodeParcelable(key, aClass, defaultValue);
    }

    public Set<String> getSets(String key, Set<String> defaultValue) {
        return init().decodeStringSet(key, defaultValue);
    }

    public Set<String> getSetsWithId(String id, String key, Set<String> defaultValue) {
        return init(id).decodeStringSet(key, defaultValue);
    }

    public Set<String> getSets(String key) {
        return init().decodeStringSet(key);
    }

    public Set<String> getSetsWithId(String id, String key) {
        return init(id).decodeStringSet(key);
    }

    public void removeKey(String key) {
        init().removeValueForKey(key);
    }

    public void clearAll() {
        init().clearAll();
    }

    public void removeKey(String id, String key) {
        init(id).removeValueForKey(key);
    }

    public void clearAll(String id) {
        init(id).clearAll();
    }
}
