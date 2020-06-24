package com.faisal.stock.View.Login;


import com.faisal.stock.Network.Response.AuthResponse;

public interface LoginView {

        void showProgress();
        void hideProgress();
        void statusSuccess(AuthResponse authResponse);
        void statusError(String message);

}
