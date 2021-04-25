package com.example.myapplication28.api;

import com.example.myapplication28.mode.data.HomeBean;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * @创建时间 2021/4/12 11:56
 */
public interface ServiceApi {
    /**
     * 域名
     */
    String BASE_URL = "https://gitee.com";

//    Observable 旧版网络请求格式，难以使用背压式。
//    Flowable 新版网络请求格式，支持背压式。

    /**
     * 网站子域名
     * @return
     */
    @GET("/ccyy2019/server/raw/master/workbook_test1")
    Flowable<HomeBean> gethome();



}
