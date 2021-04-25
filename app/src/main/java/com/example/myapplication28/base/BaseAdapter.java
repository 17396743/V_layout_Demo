package com.example.myapplication28.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseAdapter<D> extends RecyclerView.Adapter {

    List<D> mData; //adapter的数据
    protected Context context;
    protected IListClick click;

    protected IItemViewClick iItemViewClick;

    public void addItemViewClick(IItemViewClick click){
        this.iItemViewClick = click;
    }

    public BaseAdapter(Context context,List<D> data){
        this.context = context;
        mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(getLayout(),parent,false);
        VH vh = new VH(view);
        vh.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //接口回调的调用
                if(click != null){
                    click.itemClick(vh.getLayoutPosition());
                }
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        bindData(mData.get(position), (VH) holder);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    protected  List<D> getData(){
        return mData;
    }


    protected abstract int getLayout();

    protected abstract void bindData(D data,VH vh);

    public void addListClick(IListClick click){
        this.click = click;
    }

    //定义回调接口
    public interface IListClick{
        void itemClick(int pos);
    }

    public interface IItemViewClick<D>{
        //条目中的元素点击
        void itemViewClick(int viewid,D data);
    }


    public class VH extends RecyclerView.ViewHolder{

        SparseArray views = new SparseArray();

        public VH(@NonNull View itemView) {
            super(itemView);
        }

        //查找item的view
        public View getViewById(int id){
            View view = (View) views.get(id);
            if(view == null){
                view = itemView.findViewById(id);
                views.append(id,view);
            }
            return view;
        }

    }


}
