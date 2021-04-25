package com.example.myapplication28.interfaces;

public interface IBaseView {
    void tips(String tip);
    void loading(int visible);

    /**
     * 用于报错信息转移的接口。
     * @param msg
     * @return
     */
    void showToast(String msg, int time);

}
