package com.faisal.stock.View.Barang.Editor;

public interface BarangView {

    void statusSuccess(String message);
    void statusError(String message);
    void showProgress();
    void hideProgress();

}
