package com.example.myapplication28.interfaces;

import com.example.myapplication28.interfaces.IBaseView;

public interface IBasePresenter<V extends IBaseView> {

    void attachView(V view);

    void unAttachView();


}
