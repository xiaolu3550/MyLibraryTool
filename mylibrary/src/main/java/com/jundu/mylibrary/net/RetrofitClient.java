package com.jundu.mylibrary.net;


import com.google.gson.Gson;
import com.jundu.mylibrary.helper.LenientGsonConverterFactory;
import com.socks.library.KLog;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 *
 */
public class RetrofitClient {

    private static volatile RetrofitClient instance;
    private Object apiService;
    private String baseUrl;

    private RetrofitClient() {
    }

    public static RetrofitClient getInstance() {
        if (instance == null) {
            synchronized (RetrofitClient.class) {
                if (instance == null) {
                    instance = new RetrofitClient();
                }
            }
        }
        return instance;
    }

    public RetrofitClient setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public Object getApi(Class clazz) {
        //初始化一个client,不然retrofit会自己默认添加一个
        OkHttpClient client = new OkHttpClient().newBuilder()
                //设置拦截器
                .addInterceptor(mLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                //设置网络请求的Url地址
                .baseUrl(baseUrl)
                //设置数据解析器
                .addConverterFactory(LenientGsonConverterFactory.create(new Gson()))
                //设置网络请求适配器，使其支持RxJava与RxAndroid
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        //创建—— 网络请求接口—— 实例
        apiService = retrofit.create(clazz);
        return apiService;
    }

    private final Interceptor mLoggingInterceptor = new Interceptor() {
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
//            KLog.i(String.format("Sending request: %s on %s%n%s", request.url(), chain.connection()
//            , request.headers(), request.body().toString()));
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            KLog.i(String.format(Locale.getDefault(), "Received response: for %s in %.1fms%n%s",
                    response.request().url(), (double) (t2 - t1) / 1000000.0D, response.headers()));
            ResponseBody body = response.body();
            BufferedSource source = body.source();
            source.request(9223372036854775807L);
            Buffer buffer = source.buffer();
            Charset charset = Charset.defaultCharset();
            MediaType contentType = body.contentType();
            if (contentType != null) {
                charset = contentType.charset(charset);
            }

            String bodyString = buffer.clone().readString(charset);
            KLog.i(bodyString);
            return response;
        }
    };
}
