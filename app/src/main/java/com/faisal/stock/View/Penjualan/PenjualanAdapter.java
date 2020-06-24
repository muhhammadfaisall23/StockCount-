package com.faisal.stock.View.Penjualan;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.faisal.stock.Model.Penjualan;
import com.faisal.stock.R;

import java.util.List;

public class PenjualanAdapter extends RecyclerView.Adapter<PenjualanAdapter.ViewHolder> {

    List<Penjualan> penjualans;

    public PenjualanAdapter(List<Penjualan> penjualans) {
        this.penjualans = penjualans;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nama, jumlah_harga, jumlah_barang, tanggal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.nama);
            jumlah_harga = itemView.findViewById(R.id.jumlah_harga);
            jumlah_barang = itemView.findViewById(R.id.jumlah_barang);
            tanggal = itemView.findViewById(R.id.tanggal);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_penjualan,
                viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Penjualan penjualan = penjualans.get(i);
        viewHolder.nama.setText(penjualan.getNama());
        viewHolder.jumlah_barang.setText("Jumlah Barang : " + penjualan.getJumlah_barang());
        viewHolder.jumlah_harga.setText("Total : " + penjualan.getJumlah_harga());
        viewHolder.tanggal.setText(penjualan.getTanggal());
    }

    public Penjualan getPenjualan(int position) {
        return penjualans.get(position);
    }

    @Override
    public int getItemCount() {
        return penjualans.size();
    }
}
