package com.example.myapplication28.net;

import android.text.TextUtils;

import com.example.myapplication28.interfaces.Callback;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * 处理网络请求结果的错误和完成
 * @param <T>
 */
public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {

    private Callback callback;
    private String errorMsg;
    private boolean isShowErrorState = false;

    protected CommonSubscriber(Callback callback){
        this.callback = callback;
    }

    protected CommonSubscriber(Callback callback, String emsg){
        this.callback = callback;
        errorMsg = emsg;
    }

    @Override
    public void onError(Throwable t) {
        /**
         *  在此处DeBug可以获取报错信息。
         * @param if(callback == null) return;
         * @return
         */
//        LoadingUtil.getInstance().hideLoading();
        if(callback == null) return;
        if(errorMsg != null && TextUtils.isEmpty(errorMsg)){
            callback.fail(errorMsg);
        }
    }

    @Override
    public void onComplete() {
        //LoadingUtil.getInstance().hideLoading();
        //mView.showLoading(View.GONE);
    }
}
