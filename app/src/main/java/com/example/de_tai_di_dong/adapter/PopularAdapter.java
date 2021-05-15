package com.example.de_tai_di_dong.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.de_tai_di_dong.R;
import com.example.de_tai_di_dong.main.MainActivity;
import com.example.de_tai_di_dong.main.SanPhamActivity;
import com.example.de_tai_di_dong.model.SanPham;

import java.util.List;


public class PopularAdapter extends RecyclerView.Adapter<com.example.de_tai_di_dong.adapter.PopularAdapter.PopularViewHolder> {

    Context context;
    List<SanPham> PopularList;
    int idKH;
    Animation scaleUp;
    public PopularAdapter(Context context, List<SanPham> PopularList, int idKH) {
        this.context = context;
        this.PopularList = PopularList;
        this.idKH = idKH;
        scaleUp = AnimationUtils.loadAnimation(context, R.anim.zoom_in);
    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.popular_row_item, parent, false);
        return new PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PopularViewHolder holder, int position) {

        Glide.with(context).load(PopularList.get(position).getImage1()).into(holder.sanpham_image);
        holder.name.setText(PopularList.get(position).getName());
        long number = Double.valueOf(PopularList.get(position).getPrice()).longValue();
        holder.price.setText("Giá: " + number);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, SanPhamActivity.class);
                i.putExtra("idSP", PopularList.get(position).getId());
                i.putExtra("idKH", idKH);
                context.startActivity(i);
            }
        });
        holder.itemView.startAnimation(scaleUp);
    }

    @Override
    public int getItemCount() {
        return PopularList.size();
    }


    public static final class PopularViewHolder extends RecyclerView.ViewHolder {
        ImageView sanpham_image;
        TextView price, name;
        CardView sellerItem;

        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);
            sellerItem = itemView.findViewById(R.id.sellerItem);
            sanpham_image = itemView.findViewById(R.id.popular_image);
            price = itemView.findViewById(R.id.popular_price);
            name = itemView.findViewById(R.id.popular_name);


        }

    }

}
