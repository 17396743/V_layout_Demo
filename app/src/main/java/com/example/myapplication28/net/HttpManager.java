package com.example.myapplication28.net;

import android.util.Log;

import com.example.myapplication28.api.ServiceApi;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求
 * 整个项目全局使用
 */
public class HttpManager {

    private static HttpManager instance;

    public  static HttpManager getInstance(){
        if(instance == null){
            synchronized(HttpManager.class){
                if(instance == null){
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    private ServiceApi serviceApi;


    private Map<String,Retrofit> map = new HashMap<>();  //retrofit请求对象的对象池




    private Retrofit getRetrofit(String url){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    private OkHttpClient getOkHttpClient(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(new HeaderInterceptor())
                .build();
        return okHttpClient;
    }

    static class LoggingInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            ResponseBody responseBody = response.peekBody(Integer.MAX_VALUE);
            Log.i("responseBody",responseBody.string());
            return chain.proceed(request);
        }
    }

    static class HeaderInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request().newBuilder()
                    .addHeader("ACCESS_TOKEN","x0U9TTgwBTiGglcd2NGF0p6W32hHHZTRqfuD")
                    .addHeader("APP_VERSION","2.4.6")
                    .addHeader("DEVICE_ID","869765024799023")
                    .addHeader("MC","vvJrdl_v01")
                    .addHeader("OS_VERSION","5.1.1")
                    .addHeader("PLATFORM","Android")
                    .build();
            return chain.proceed(request);
        }
    }

    /**
     * ServiceApi
     * @return
     */
    public ServiceApi getService(){
        if(serviceApi == null){
            serviceApi = getRetrofit(ServiceApi.BASE_URL).create(ServiceApi.class);
        }
        return serviceApi;
    }

}
