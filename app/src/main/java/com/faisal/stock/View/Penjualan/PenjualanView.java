package com.faisal.stock.View.Penjualan;


import com.faisal.stock.Network.Response.PenjualanResponse;

public interface PenjualanView {

    void showProgress();
    void hideProgress();
    void statusSuccess(PenjualanResponse penjualanResponse);
    void statusError(String message);

}
