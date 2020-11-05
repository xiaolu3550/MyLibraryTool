package com.xiaolu.mylibrarykotlin.net

import com.xiaolu.mylibrarykotlin.utils.GsonUtils
import okhttp3.MediaType
import okhttp3.RequestBody
import java.util.*

/**
 * ================================================
 * @ProjectName:    MyLibraryTool
 * @Package:        com.xiaolu.mylibrarykotlin.net
 * @ClassName:      RequestBodyUtils
 * @Description:     java类作用描述
 * @Author:          xiaol
 * @CreateDate:     2020/11/4 14:56
 * @UpdateUser:     xiaol
 * @UpdateDate:     2020/11/4 14:56
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 * ================================================
 */
class RequestBodyUtils {
    fun setRequestBody_JSON(stringObjectMap: Map<String, Objects>): RequestBody {
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), GsonUtils.toJson(stringObjectMap))
    }

    fun setRequestBody(requestBody: String): RequestBody {
        return RequestBody.create(MediaType.parse("Accept-Encoding identity"), requestBody)
    }
}