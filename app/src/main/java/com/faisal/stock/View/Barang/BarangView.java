package com.faisal.stock.View.Barang;


import com.faisal.stock.Network.Response.BarangResponse;

public interface BarangView {

    void showProgress();
    void hideProgress();
    void statusSuccess(BarangResponse barangResponse);
    void statusError(String message);

}
