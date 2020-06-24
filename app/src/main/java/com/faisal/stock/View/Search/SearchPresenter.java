package com.faisal.stock.View.Search;


import com.faisal.stock.Network.ApiClient;
import com.faisal.stock.Network.ApiInterface;
import com.faisal.stock.Network.Response.PenjualanResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter {
    SearchView view;
    ApiInterface apiInterface;
    CompositeDisposable disposable;

    public SearchPresenter(SearchView view) {
        this.view = view;
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        disposable = new CompositeDisposable();
    }

    public void getSearch(String token, String search) {
        view.showProgress();
        disposable.add(
                apiInterface.getPenjualan(token, search)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<PenjualanResponse>(){
                            @Override
                            public void onNext(PenjualanResponse penjualanResponse) {
                                view.statusSuccess(penjualanResponse);
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

    public void detachView() {
        disposable.dispose();
    }
}
