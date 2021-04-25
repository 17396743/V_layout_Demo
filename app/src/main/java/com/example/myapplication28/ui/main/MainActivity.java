package com.example.myapplication28.ui.main;

import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.example.myapplication28.R;

import com.example.myapplication28.interfaces.main.IMain;
import com.example.myapplication28.base.BaseActivity;
import com.example.myapplication28.mode.data.HomeBean;
import com.example.myapplication28.presenter.home.HomePresenter;
import com.example.myapplication28.ui.main.adapter.BannerRecyclerViewAdapter;
import com.example.myapplication28.ui.main.adapter.TitlesRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * 主界面
 */
public class MainActivity extends BaseActivity<IMain.Presenter> implements IMain.View {


    androidx.recyclerview.widget.RecyclerView rvMain;
    VirtualLayoutManager virtualLayoutManager;
    RecyclerView.RecycledViewPool viewPool;
    DelegateAdapter delegateAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        rvMain = (RecyclerView) findViewById(R.id.rv_main);

        //V_layout基础组件设置，必须要有。
        //初始化布局模块
        virtualLayoutManager = new VirtualLayoutManager(this);
        rvMain.setLayoutManager(virtualLayoutManager);
        //初始化布局模块
        viewPool = new RecyclerView.RecycledViewPool();
        rvMain.setRecycledViewPool(viewPool);
        ////初始化最大值
        viewPool.setMaxRecycledViews(0, 10);
        //初始化适配器模块
        delegateAdapter = new DelegateAdapter(virtualLayoutManager);
        //初始化列表绑定 （这个位置可有可无，只要设置好自己所做的布局，后面加上就可以了）
        rvMain.setAdapter(delegateAdapter);
        //======================================
    }

    @Override
    protected IMain.Presenter createPersenter() {
        return new HomePresenter();
    }

    @Override
    protected void initData() {
        //调用网络适配器
        presenter.getHome();
    }

    @Override
    public void getHomeReturn(HomeBean result) {
        List<HomeBean.Data.Catalog> catalog = result.getData().getCatalog();

        //banner轮播图
        ArrayList<String> imgs = new ArrayList<>();

        for (int i = 0; i < catalog.size(); i++) {
            if (i == 0 || i  == 2) {
                imgs.add(catalog.get(i).getIcon());
            }
        }


        ArrayList<HomeBean.Data.Catalog> catalogs = new ArrayList<>();
        ArrayList<HomeBean.Data.Catalog> catalogs1 = new ArrayList<>();
        BannerRecyclerViewAdapter bannerRecyclerViewAdapter = new BannerRecyclerViewAdapter(imgs, this);
        delegateAdapter.addAdapter(bannerRecyclerViewAdapter);
        bannerRecyclerViewAdapter.notifyDataSetChanged();


        for (int i = 0; i < catalog.size(); i++) {

            if (i == 1) {
                catalogs.add(catalog.get(i));
            }
            if (i == 3) {
                catalogs1.add(catalog.get(i));
            }

        }

        //普通列表
        for (int i = 0; i < 2; i++) {

            if (i == 1) {
                TitlesRecyclerViewAdapter titlesRecyclerViewAdapter = new TitlesRecyclerViewAdapter(catalog.get(i), this);
                delegateAdapter.addAdapter(titlesRecyclerViewAdapter);
                titlesRecyclerViewAdapter.notifyDataSetChanged();

            } else {
                TitlesRecyclerViewAdapter titlesRecyclerViewAdapter = new TitlesRecyclerViewAdapter(catalogs1.get(i), this);
                delegateAdapter.addAdapter(titlesRecyclerViewAdapter);
                titlesRecyclerViewAdapter.notifyDataSetChanged();
            }


        }

        //绑定适配器
        rvMain.setAdapter(delegateAdapter);
    }
}