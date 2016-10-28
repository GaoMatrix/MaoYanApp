package com.mao.movie.retrofit;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by GaoMatrix on 2016/9/5.
 */
public class RetrofitClient {

    private static final int DEFAULT_TIMEOUT = 5;

    private static Retrofit sRetrofit = buildClient();

    private static Retrofit buildClient() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        //if (BuildConfig.DEBUG) {
        // Log信息拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //设置 Debug Log 模式
        builder.addInterceptor(loggingInterceptor);
        builder.addNetworkInterceptor(new StethoInterceptor());
        //}

        sRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(ApiService.API_SERVER_URL)
                .build();

        return sRetrofit;
    }

    /**
     * 根据传入的不同ApiService接口类返回不同的Client
     *
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T getClient(Class<? extends T> type) {
        return sRetrofit.create(type);
    }

}
