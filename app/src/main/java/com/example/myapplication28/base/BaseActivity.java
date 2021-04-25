package com.example.myapplication28.base;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication28.interfaces.IBasePresenter;
import com.example.myapplication28.interfaces.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * activity基类
 * @param <P>
 */
public abstract class BaseActivity<P extends IBasePresenter> extends AppCompatActivity implements IBaseView {


    //p层关联
    protected P presenter;
    Unbinder unbinder; //butterknife


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //需要界面view
        setContentView(getLayout());
        unbinder = ButterKnife.bind(this);
        //初始化界面
        initView();
        presenter = createPersenter();
        if(presenter != null){
            presenter.attachView(this);
        }
        //初始化界面数据
        initData();

    }




    //定义一个获取当前界面的方法  由子类提供的
    protected abstract int getLayout();
    //初始化界面
    protected abstract void initView();
    //初始化p层的方法
    protected abstract P createPersenter();
    //初始化界面数据
    protected abstract void initData();


    @Override
    public void tips(String tip) {

    }

    @Override
    public void loading(int visible) {

    }

    @Override
    public void showToast(String msg,int time){
        Toast.makeText(this, msg, time).show();
    }

    /**
     * 界面销毁
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(unbinder != null){
            unbinder.unbind();
        }
        //释放p关联的v的引用
        if(presenter != null){
            presenter.unAttachView();
        }
    }


}
