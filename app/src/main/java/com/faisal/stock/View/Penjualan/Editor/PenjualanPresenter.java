package com.faisal.stock.View.Penjualan.Editor;


import com.faisal.stock.Network.ApiClient;
import com.faisal.stock.Network.ApiInterface;
import com.faisal.stock.Network.Response.BarangResponse;
import com.faisal.stock.Network.Response.PenjualanResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;

import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class PenjualanPresenter {
    PenjualanView view;
    ApiInterface apiInterface;
    CompositeDisposable disposable;

    public PenjualanPresenter(PenjualanView view) {
        this.view = view;
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        disposable = new CompositeDisposable();
    }

    void getListBarang(String token) {
        view.showProgress();
        disposable.add(
            apiInterface.getBarangList(token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableObserver<BarangResponse>(){
                        @Override
                        public void onNext(BarangResponse barangResponse) {
                            view.setListBarang(barangResponse);
                        }

                        @Override
                        public void onError(Throwable e) {
                            view.hideProgress();
                            view.statusError(e.getLocalizedMessage());
                        }

                        @Override
                        public void onComplete() {
                            view.hideProgress();
                        }
                    })
        );
    }

    void savePenjualan(String token, String barang_id, String jumlah_barang, String jumlah_harga) {
        view.showProgress();
        disposable.add(
                apiInterface.savePenjualan(token, barang_id, jumlah_barang, jumlah_harga)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<PenjualanResponse>() {
                            @Override
                            public void onNext(PenjualanResponse penjualanResponse) {
                                view.statusSuccess(penjualanResponse.getStatus());
                            }

                            @Override
                            public void onError(Throwable e) {
                                view.hideProgress();
                                view.statusError(e.getLocalizedMessage());
                            }

                            @Override
                            public void onComplete() {
                                view.hideProgress();
                            }
                        })
        );
    }

    void updatePenjualan(String token, String id, String barang_id, String jumlah_barang, String jumlah_harga) {
        view.showProgress();
        disposable.add(
                apiInterface.updatePenjualan(token, id, barang_id, jumlah_barang, jumlah_harga)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableCompletableObserver(){
                            @Override
                            public void onComplete() {
                                view.hideProgress();
                                view.statusSuccess("berhasil update");
                            }

                            @Override
                            public void onError(Throwable e) {
                                view.hideProgress();
                                view.statusError(e.getLocalizedMessage());
                            }
                        })
        );
    }

    void deletePenjualan(String token, String id) {
        view.showProgress();
        disposable.add(
                apiInterface.deletePenjualan(token, id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableCompletableObserver(){
                            @Override
                            public void onComplete() {
                                view.hideProgress();
                                view.statusSuccess("berhasil delete");
                            }

                            @Override
                            public void onError(Throwable e) {
                                view.hideProgress();
                                view.statusError(e.getLocalizedMessage());
                            }
                        })
        );
    }

    public void detachView() {
        disposable.dispose();
    }

}
