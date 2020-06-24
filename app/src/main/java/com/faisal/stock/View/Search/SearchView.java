package com.faisal.stock.View.Search;


import com.faisal.stock.Network.Response.PenjualanResponse;

public interface SearchView {

    void showProgress();
    void hideProgress();
    void statusSuccess(PenjualanResponse penjualanResponse);
    void statusError(String message);
}
