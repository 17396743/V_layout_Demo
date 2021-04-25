package com.example.myapplication28.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.FloatLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.bumptech.glide.Glide;
import com.example.myapplication28.R;
import com.example.myapplication28.mode.data.HomeBean;

import java.util.List;

/**
 * @创建时间 2021/4/25 19:19
 */
public class TitlesRecyclerViewAdapter extends DelegateAdapter.Adapter {
    private HomeBean.Data.Catalog catalog;
    private Context context;

    //接收数据
    public TitlesRecyclerViewAdapter( HomeBean.Data.Catalog catalog, Context context) {
        this.catalog = catalog;
        this.context = context;
    }

    //适配器预设
    // 具体详情请看：
    //  https://blog.csdn.net/sw950729/article/details/67634747
    //
    //* 1：LinearLayoutHelper: 线性布局
    //* 2：GridLayoutHelper: Grid布局， 支持横向的colspan
    //* 3：FixLayoutHelper: 固定布局，始终在屏幕固定位置显示
    //* 4：ScrollFixLayoutHelper: 固定布局，但之后当页面滑动到该图片区域才显示, 可以用来做返回顶部或其他书签等
    //* 5：FloatLayoutHelper: 浮动布局，可以固定显示在屏幕上，但用户可以拖拽其位置
    //* 6：ColumnLayoutHelper: 栏格布局，都布局在一排，可以配置不同列之间的宽度比值
    //* 7：SingleLayoutHelper: 通栏布局，只会显示一个组件View
    //* 8：OnePlusNLayoutHelper: 一拖N布局，可以配置1-5个子元素
    //* 9：StickyLayoutHelper: stikcy布局， 可以配置吸顶或者吸底
    //* 10：StaggeredGridLayoutHelper: 瀑布流布局，可配置间隔高度/宽度

    //绑定布局适配器预设
    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

    //绑定布局id
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.titlesitem, parent, false);
        return new HomeHolder(inflate);
    }

    //设置数据
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HomeHolder) {
            Glide.with(context).load(catalog.getIcon()).into(((HomeHolder) holder).ivItemOne);
            ((HomeHolder) holder).tvItemOne.setText(catalog.getName());
        }
    }

    //设置显示数量
    @Override
    public int getItemCount() {
        return 1;
    }

    //转换组件id
    class HomeHolder extends RecyclerView.ViewHolder {
        ImageView ivItemOne;
        TextView tvItemOne;

        public HomeHolder(@NonNull View itemView) {
            super(itemView);
            ivItemOne = (ImageView) itemView.findViewById(R.id.iv_item_one);
            tvItemOne = (TextView) itemView.findViewById(R.id.tv_item_one);
        }
    }
}
