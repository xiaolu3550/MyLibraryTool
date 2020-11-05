package com.xiaolu.mylibrarykotlin.net

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okio.Buffer
import retrofit2.Converter
import retrofit2.Retrofit
import java.io.IOException
import java.io.OutputStreamWriter
import java.io.Writer
import java.lang.reflect.Type
import java.nio.charset.Charset

/**
 * Created by Jane on 2018/8/16.
 */
class LenientGsonConverterFactory private constructor(gson: Gson?) : Converter.Factory() {
    private val gson: Gson
    override fun responseBodyConverter(type: Type, annotations: Array<Annotation>,
                                       retrofit: Retrofit): Converter<ResponseBody, *> {
        val adapter = gson.getAdapter(TypeToken.get(type))
        return LenientGsonResponseBodyConverter<Any>(gson, adapter as TypeAdapter<Any>)
    }

    override fun requestBodyConverter(type: Type,
                                      parameterAnnotations: Array<Annotation>, methodAnnotations: Array<Annotation>, retrofit: Retrofit): Converter<*, RequestBody> {
        val adapter = gson.getAdapter(TypeToken.get(type))
        return LenientGsonRequestBodyConverter<Any>(gson, adapter as TypeAdapter<Any>)
    }

    inner class LenientGsonResponseBodyConverter<T> internal constructor(private val gson: Gson, private val adapter: TypeAdapter<T>) : Converter<ResponseBody, T> {
        @Throws(IOException::class)
        override fun convert(value: ResponseBody): T {
            val jsonReader = gson.newJsonReader(value.charStream())
            jsonReader.isLenient = true
            return try {
                adapter.read(jsonReader)
            } finally {
                value.close()
            }
        }

    }

    inner class LenientGsonRequestBodyConverter<T> internal constructor(private val gson: Gson, private val adapter: TypeAdapter<T>) : Converter<T, RequestBody> {
        private val MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8")
        private val UTF_8 = Charset.forName("UTF-8")

        @Throws(IOException::class)
        override fun convert(value: T): RequestBody {
            val buffer = Buffer()
            val writer: Writer = OutputStreamWriter(buffer.outputStream(), UTF_8)
            val jsonWriter = gson.newJsonWriter(writer)
            jsonWriter.isLenient = true
            adapter.write(jsonWriter, value)
            jsonWriter.close()
            return RequestBody.create(MEDIA_TYPE, buffer.readByteString())
        }

    }

    companion object {
        /**
         * Create an instance using `gson` for conversion. Encoding to JSON and
         * decoding from JSON (when no charset is specified by a header) will use UTF-8.
         */
        /**
         * Create an instance using a default [Gson] instance for conversion. Encoding to JSON and
         * decoding from JSON (when no charset is specified by a header) will use UTF-8.
         */
        @JvmOverloads
        fun create(gson: Gson? = Gson()): LenientGsonConverterFactory {
            return LenientGsonConverterFactory(gson)
        }
    }

    init {
        if (gson == null) throw NullPointerException("gson == null")
        this.gson = gson
    }
}