package com.faisal.stock.View.Penjualan.Editor;


import com.faisal.stock.Network.Response.BarangResponse;

public interface PenjualanView {

    void showProgress();
    void hideProgress();
    void statusSuccess(String message);
    void statusError(String message);
    void setListBarang(BarangResponse barangResponse);
}
