package com.faisal.stock.View.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.faisal.stock.Network.Response.AuthResponse;
import com.faisal.stock.R;
import com.faisal.stock.Utils.SessionManager;
import com.faisal.stock.View.Main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {

    LoginPresenter presenter;
    ProgressDialog progressDialog;
    SessionManager sessionManager;

    @BindView(R.id.txtUsername)
    EditText et_username;
    @BindView(R.id.txtPassword) EditText et_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");

        presenter = new LoginPresenter(this);
        sessionManager = new SessionManager(getApplicationContext());
    }

    @OnClick(R.id.login) void login() {
        presenter.loginAuth(
                et_username.getText().toString(),
                et_password.getText().toString()
        );
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void statusSuccess(AuthResponse authResponse) {
        sessionManager.createLoginSession(
                authResponse.getId(),
                authResponse.getUsername(),
                "Bearer " + authResponse.getToken()
        );
        finish();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void statusError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Log.d("Error", "statusError: " + message);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
