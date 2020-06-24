package com.faisal.stock.View.Supplier;


import com.faisal.stock.Network.Response.SupplierResponse;

public interface SupplierView {

    void showProgress();
    void hideProgress();
    void statusSuccess(SupplierResponse supplierResponse);
    void loadMore(SupplierResponse supplierResponse);
    void statusError(String message);

}
