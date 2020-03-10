package com.jundu.mylibrary.helper;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jundu.mylibrary.utils.GsonUtils;
import com.socks.library.KLog;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    private Context mCntext;

    OkHttpClient client = new OkHttpClient();
    GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder().create());
    private static RetrofitHelper instance = null;
    private Retrofit mRetrofit = null;
    private String baseUrl;

    public static RetrofitHelper getInstance(Context context) {
        if (instance == null) {
            instance = new RetrofitHelper(context);
        }
        return instance;
    }

    /**
     * @param mContext
     */
    private RetrofitHelper(Context mContext) {
        mCntext = mContext;
    }

    public RetrofitHelper setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public RetrofitHelper init() {
        resetApp();
        return this;
    }

    private void resetApp() {
        client = client.newBuilder()
                .addInterceptor(mLoggingInterceptor)
                //.retryOnConnectionFailure(false)
               // .connectTimeout(1000, TimeUnit.MILLISECONDS)
                .build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(LenientGsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    public Object getServer(Class aClass) {
        return mRetrofit.create(aClass);
    }

    public RequestBody setRequestBody(Map<String, Object> stringObjectMap) {
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                GsonUtils.toJson(stringObjectMap));
    }

    public RequestBody setRequestBody(String requestBody) {
        return RequestBody.create(MediaType.parse("Accept-Encoding identity"), requestBody);
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
