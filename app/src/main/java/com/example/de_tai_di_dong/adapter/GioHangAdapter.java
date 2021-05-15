package com.example.de_tai_di_dong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.de_tai_di_dong.R;
import com.example.de_tai_di_dong.model.SanPham;
import java.util.ArrayList;

public class GioHangAdapter extends BaseAdapter {
    private final Context context;
    private ArrayList<SanPham> listsp;
    private CarItemListener clickItem;
//    private ItemClickListener clickCart;
    private int idKH;
    public GioHangAdapter(Context context, ArrayList<SanPham>listsp, int idKH, CarItemListener clickItem){
        this.context=context;
        this.listsp=listsp;
        this.idKH=idKH;
        this.clickItem=clickItem;
    }

    @Override
    public int getCount() {
        if (listsp == null)
            return 0;
        else
            return listsp.size();
    }

    @Override
    public Object getItem(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        ImageView imgHinh;
        ImageView imgDelete;
        ImageView imgEdit;
        TextView txtSL;
        TextView txtGia;
        TextView txtTenSP;
        LinearLayout listCart;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if( view == null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giohang, parent, false);
            holder = new ViewHolder();
            holder.imgHinh= view.findViewById(R.id.imgSP);
            holder.imgDelete=view.findViewById(R.id.imgDelete);
            holder.txtTenSP=view.findViewById(R.id.tvTenSP);
            holder.txtGia=view.findViewById(R.id.tvGia);
            holder.txtSL =view.findViewById(R.id.tvSL);
            holder.listCart=view.findViewById(R.id.listCart);
            view.setTag(holder);
        }else holder=(ViewHolder)view.getTag();
        final  SanPham sp = listsp.get(position);
        Glide.with(context).load(sp.getImage1()).into(holder.imgHinh);
        holder.txtSL.setText("Số lượng: "+sp.getQuantity());
        holder.txtGia.setText("Giá: "+sp.getPrice());
        holder.txtTenSP.setText(sp.getName());
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickItem.onDeleteCartItem(sp.getId(),position);
            }
        });
        holder.listCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickItem.ClickItem(sp.getId());
            }
        });
        return view;
    }
}
