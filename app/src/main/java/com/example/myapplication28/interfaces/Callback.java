package com.example.myapplication28.interfaces;

public interface Callback<T> {
    /**
     * 报错信息
     * @param msg
     * @return
     */
    void fail(String msg);
    /**
     * 获取到的网络数据
     * @param t
     * @return
     */
    void success(T t);

}
