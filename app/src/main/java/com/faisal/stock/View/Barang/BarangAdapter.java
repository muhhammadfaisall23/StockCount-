package com.faisal.stock.View.Barang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.faisal.stock.Model.Barang;
import com.faisal.stock.R;
import com.faisal.stock.Utils.Const;

import java.util.List;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.ViewHolder> {

    List<Barang> barangs;
    Context mContext;

    public BarangAdapter(List<Barang> barangs, Context context) {
        mContext = context;
        this.barangs = barangs;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_barang,
                viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Barang barang = barangs.get(i);
        viewHolder.nama.setText(barang.getNama());
        viewHolder.harga.setText(barang.getHarga());
        viewHolder.stock.setText(barang.getStock());
        viewHolder.ukuran.setText(barang.getUkuran());

        String URL = Const.URL + "upload/";

        Glide.with(mContext).load(URL + barang.getGambar())
                .thumbnail(0.5f)
                .transition(new DrawableTransitionOptions().crossFade())
                .into(viewHolder.gambar);

    }

    @Override
    public int getItemCount() {
        return barangs.size();
    }

    public Barang getBarang(int position) {
        return barangs.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nama, harga, stock, ukuran;
        ImageView gambar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama);
            harga = itemView.findViewById(R.id.harga);
            stock = itemView.findViewById(R.id.stock);
            ukuran = itemView.findViewById(R.id.ukuran);
            gambar = itemView.findViewById(R.id.gambar);
        }
    }
}
