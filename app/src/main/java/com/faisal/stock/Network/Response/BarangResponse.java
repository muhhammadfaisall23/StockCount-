package com.faisal.stock.Network.Response;


import com.faisal.stock.Model.Barang;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class BarangResponse extends BaseResponse {

    @Expose
    @SerializedName("data")
    List<Barang> data;

    public List<Barang> getData() {
        return data;
    }

    public void setData(List<Barang> data) {
        this.data = data;
    }
}
