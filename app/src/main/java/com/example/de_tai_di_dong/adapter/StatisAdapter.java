package com.example.de_tai_di_dong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.de_tai_di_dong.R;
import com.example.de_tai_di_dong.model.SanPham;

import java.util.ArrayList;

public class StatisAdapter extends BaseAdapter {
    private Context context;
    private ItemClickListener clickItem;
    private ArrayList<SanPham> list_SanPham;

    public StatisAdapter(Context context,ArrayList<SanPham> listSanPham,ItemClickListener clickItem) {
        this.context=context;
        this.list_SanPham= listSanPham;
        this.clickItem=clickItem;
    }

    @Override
    public int getCount() {
        if (list_SanPham == null)
            return 0;
        else
            return list_SanPham.size();
    }

    @Override
    public Object getItem(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private  class ViewHolder{
        ImageView imgHinh;
        TextView tenHinh;
        TextView gia;
        LinearLayout list;
        TextView sl;
    }

    @Override
    public View getView(int position, @Nullable View view, @Nullable ViewGroup parent) {
        StatisAdapter.ViewHolder holder;
        if( view == null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_statis, parent, false);
            holder = new StatisAdapter.ViewHolder();
            holder.imgHinh= view.findViewById(R.id.imgSanPham);
            holder.tenHinh=view.findViewById(R.id.tvSanPham);
            holder.gia=view.findViewById(R.id.tvPrice);
            holder.list=view.findViewById(R.id.list);
            holder.sl = view.findViewById(R.id.tvQuanlity);
            view.setTag(holder);
        }else holder = (StatisAdapter.ViewHolder)view.getTag();

        final SanPham sanPham=list_SanPham.get(position);

        Glide.with(context).load(sanPham.getImage1()).into(holder.imgHinh);
        holder.tenHinh.setText(sanPham.getName());
        holder.gia.setText(sanPham.getPrice()+"");
        holder.sl.setText(sanPham.getQuantity()+"");
        holder.list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickItem.ClickItem(sanPham.getId());
            }
        });
        return view;
    }
}
