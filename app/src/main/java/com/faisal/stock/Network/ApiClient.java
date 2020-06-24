package com.faisal.stock.Network;


import com.faisal.stock.Utils.Const;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String url = Const.URL + "api/";
    private static final String url_insert = Const.URL + "insertbarang.php";
    private static final String url_select = Const.URL + "selectbarang.php";
    private static final String url_edit = Const.URL + "editbarang.php";
    private static final String url_update = Const.URL + "updatebarang.php";
    private static final String url_delete = Const.URL + "insertbarang.php";

    private static Retrofit retrofit;

    public static Retrofit getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }

        return retrofit;

    }

}
