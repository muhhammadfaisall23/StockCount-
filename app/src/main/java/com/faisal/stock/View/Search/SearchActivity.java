package com.faisal.stock.View.Search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.faisal.stock.Network.Response.PenjualanResponse;
import com.faisal.stock.R;
import com.faisal.stock.Utils.SessionManager;
import com.faisal.stock.Utils.SimpleDividerItemDecoration;
import com.faisal.stock.View.Penjualan.PenjualanAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnTextChanged;

public class SearchActivity extends AppCompatActivity implements SearchView{

    SearchPresenter presenter;
    SessionManager session;
    PenjualanAdapter adapter;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.searchText)
    EditText et_searchText;
    @BindView(R.id.clear)
    ImageButton clear;
    @BindView(R.id.progress)
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        session = new SessionManager(this);
        presenter = new SearchPresenter(this);

    }


    @OnClick(R.id.clear) void clear(View view) {
        if (view.getId() == R.id.clear) {
            et_searchText.setText("");
        }
    }

    @OnClick(R.id.search) void search() {
        presenter.getSearch(
                session.getKeyToken(),
                et_searchText.getText().toString()
        );
    }

    @OnTextChanged(value = R.id.searchText, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void searchTextChanged(Editable s) {
        String text = s.toString();
        if (text.length() == 0) {
            clear.setVisibility(View.INVISIBLE);
        } else {
            clear.setVisibility(View.VISIBLE);
        }
    }

    @OnEditorAction(R.id.searchText) boolean onSearch() {
        search();
        return true;
    }


    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void statusSuccess(PenjualanResponse penjualanResponse) {
        adapter = new PenjualanAdapter(penjualanResponse.getData());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        adapter.notifyDataSetChanged();
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
}
