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
import java.util.List;

public class SanPhamAdapter extends BaseAdapter {
    private Context context;
    private ItemClickListener clickItem;
    private ArrayList<SanPham> list_SanPham;
    private ValueFilter valueFilter;
    private ArrayList<SanPham> spfilter;

    public SanPhamAdapter(Context context,ArrayList<SanPham> listSanPham,ItemClickListener clickItem) {
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

    public Filter getFilter() {

        if(valueFilter==null) {

            valueFilter=new ValueFilter();
        }
        return valueFilter;
    }
    private  class ViewHolder{
        ImageView imgHinh;
        TextView tenHinh;
        TextView gia;
        LinearLayout list;
    }

    @Override
    public View getView(int position, @Nullable View view,@Nullable ViewGroup parent) {
       ViewHolder holder;
        if( view == null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanpham, parent, false);
            holder = new ViewHolder();
            holder.imgHinh= view.findViewById(R.id.imgSanPham);
            holder.tenHinh=view.findViewById(R.id.tvSanPham);
            holder.gia=view.findViewById(R.id.tvPrice);
            holder.list=view.findViewById(R.id.list);
            view.setTag(holder);
        }else holder = (ViewHolder)view.getTag();

        final SanPham sanPham=list_SanPham.get(position);

        Glide.with(context).load(sanPham.getImage1()).into(holder.imgHinh);
        holder.tenHinh.setText(sanPham.getName());
        holder.gia.setText(sanPham.getPrice()+"");
        holder.list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickItem.ClickItem(sanPham.getId());
            }
        });
        return view;
    }
    private  class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results=new FilterResults();
            if(constraint!=null && constraint.length()>0){
                ArrayList<SanPham> filterList=new ArrayList<SanPham>();
                for(int i=0;i<spfilter.size();i++){
                    if((spfilter.get(i).getName().toUpperCase())
                            .contains(constraint.toString().toUpperCase())) {
                        SanPham c = new SanPham();
                        c.setName(spfilter.get(i).getName());
                        c.setId(spfilter.get(i).getId());
                        c.setImage1(spfilter.get(i).getImage1());
                        c.setProGroupId(spfilter.get(i).getProGroupId());
                        c.setPrice(spfilter.get(i).getPrice());
                        filterList.add(c);
                    }
                }
                results.count=filterList.size();
                results.values=filterList;
            }else{
                results.count=spfilter.size();
                results.values=spfilter;
            }
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list_SanPham=(ArrayList<SanPham>) results.values;
            notifyDataSetChanged();

        }
    }
}
