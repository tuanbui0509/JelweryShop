package com.example.de_tai_di_dong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.de_tai_di_dong.model.Orders;

import java.util.ArrayList;

public class DonHangAdapter extends BaseAdapter {
    private final Context context;
    private ArrayList<Orders> listdh;
    private DonHangClickListener clickItem;
    private int idKH;

    public DonHangAdapter(Context context, ArrayList<Orders> listdh, DonHangClickListener clickItem, int idKH) {
        this.context = context;
        this.listdh = listdh;
        this.clickItem = clickItem;
        this.idKH = idKH;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class DonHangViewHolder{
        private TextView txtId;
        private TextView txtGia;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
