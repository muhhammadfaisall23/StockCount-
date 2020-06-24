package com.faisal.stock.View.Supplier.Editor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.faisal.stock.R;
import com.faisal.stock.Utils.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SupplierActivity extends AppCompatActivity implements SupplierView{

    SupplierPresenter presenter;
    ProgressDialog progressDialog;
    SessionManager session;

    String id, nama, no_hp, alamat;

    @BindView(R.id.nama)
    EditText et_nama;

    @BindView(R.id.no_hp)
    EditText et_no_hp;

    @BindView(R.id.alamat)
    EditText et_alamat;

    @BindView(R.id.content_simpan)
    LinearLayout content_simpan;

    @BindView(R.id.content_update)
    LinearLayout content_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier);
        ButterKnife.bind(this);

        session = new SessionManager(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading ...");
        presenter = new SupplierPresenter(this);

        initDataIntent();
        setTextEditor();
    }

    @OnClick(R.id.simpan) void simpan(){
        presenter.saveSupplier(
                session.getKeyToken(),
                et_nama.getText().toString(),
                et_no_hp.getText().toString(),
                et_alamat.getText().toString()
        );
    }

    @OnClick(R.id.update) void update() {
        presenter.updateSupplier(
                session.getKeyToken(),
                id,
                et_nama.getText().toString(),
                et_no_hp.getText().toString(),
                et_alamat.getText().toString()
        );
    }


    @OnClick(R.id.hapus) void hapus() {
        presenter.deleteSupplier(
                session.getKeyToken(),
                id
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
    public void statusSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void statusError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private void initDataIntent() {
        Intent intent= getIntent();
        id = intent.getStringExtra("id");
        nama = intent.getStringExtra("nama");
        no_hp = intent.getStringExtra("no_hp");
        alamat = intent.getStringExtra("alamat");
    }

    private void setTextEditor() {
        if (id != null) {
            getSupportActionBar().setTitle("Update data");
            et_nama.setText(nama);
            et_alamat.setText(alamat);
            et_no_hp.setText(no_hp);
            content_update.setVisibility(View.VISIBLE);
            content_simpan.setVisibility(View.GONE);
        } else {
            getSupportActionBar().setTitle("Simpan data");
        }
    }
}
