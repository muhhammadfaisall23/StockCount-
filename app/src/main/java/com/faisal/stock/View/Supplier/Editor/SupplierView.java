package com.faisal.stock.View.Supplier.Editor;

public interface SupplierView {

    void showProgress();
    void hideProgress();
    void statusSuccess(String message);
    void statusError(String message);
}
