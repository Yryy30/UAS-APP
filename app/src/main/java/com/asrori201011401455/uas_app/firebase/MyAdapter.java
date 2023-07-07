package com.asrori201011401455.uas_app.firebase;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.asrori201011401455.uas_app.DetailWisata;
import com.asrori201011401455.uas_app.R;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<wisata> list;

    public MyAdapter(Context context, ArrayList<wisata> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        wisata wisata = list.get(position);
        holder.nama.setText(wisata.getNama());
        Glide.with(context).load(wisata.getGambar()).into(holder.gambar);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailWisata.class);
                intent.putExtra("namaWisata", list.get(holder.getAdapterPosition()).getNama());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nama;
        ImageView gambar;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.recyclerCaption);
            gambar = itemView.findViewById(R.id.recyclerImage);
            cardView = itemView.findViewById(R.id.cardV);
        }
    }
}
