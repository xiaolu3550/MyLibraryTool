package com.xiaolu.mylibrarykotlin.utils

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import java.util.*

/**
 * ================================================
 * @ProjectName:    MyLibraryTool
 * @Package:        com.xiaolu.mylibrarykotlin
 * @ClassName:      GsonUtils
 * @Description:     java类作用描述
 * @Author:          xiaol
 * @CreateDate:     2020/11/4 15:00
 * @UpdateUser:     xiaol
 * @UpdateDate:     2020/11/4 15:00
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 * ================================================
 */
object GsonUtils {
    private val gson = Gson()

    /**
     * 转成Json字符串
     */
    @JvmStatic
    fun toJson(objects: Any): String {
        return gson.toJson(objects)
    }

    /**
     * Json转Java对象
     */
    @JvmStatic
    fun <T> fromJson(json: String?, clz: Class<T>?): T {
        return gson.fromJson(json, clz)
    }

    /**
     * Json转List集合
     */
    @JvmStatic
    fun <T> jsonToList(json: String?, clazz: Class<T>): List<T> {
        val type = object : TypeToken<List<T>?>() {}.type
        return gson.fromJson(json, type)
    }

    /**
     * Json转List集合,遇到解析不了的，就使用这个
     */
    @JvmStatic
    fun <T> fromJsonList(json: String?, clazz: Class<T>): List<T> {
        val mList: List<T> = ArrayList()
        val toMutableList = mList.toMutableList()
        val asJsonArray: JsonArray = JsonParser().parse(json).asJsonArray
        var gson = Gson()
        for (elem in asJsonArray) {
            toMutableList.add(gson.fromJson(elem, clazz))
        }
        return toMutableList;
    }

    /**
     * Json转换成Map的List集合对象
     */
    @JvmStatic
    fun <T> toListMap(json: String?, clazz: Class<T>): List<Map<String, T>> {
        val type = object : TypeToken<List<Map<String?, T>?>?>() {}.type
        return gson.fromJson(json, type)
    }

    /**
     * Json转Map对象
     */
    @JvmStatic
    fun <T> toMap(json: String?, clazz: Class<T>): Map<String, T> {
        val type = object : TypeToken<Map<String?, T>?>() {}.type
        return gson.fromJson(json, type)
    }
}