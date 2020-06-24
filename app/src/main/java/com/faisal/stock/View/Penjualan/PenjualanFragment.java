package com.faisal.stock.View.Penjualan;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.faisal.stock.Model.Penjualan;
import com.faisal.stock.Network.Response.PenjualanResponse;
import com.faisal.stock.R;
import com.faisal.stock.Utils.RecyclerItemClickListener;
import com.faisal.stock.Utils.SessionManager;
import com.faisal.stock.Utils.SimpleDividerItemDecoration;
import com.faisal.stock.View.Penjualan.Editor.PenjualanActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class PenjualanFragment extends Fragment implements PenjualanView {


    SessionManager session;
    PenjualanPresenter presenter;
    PenjualanAdapter adapter;

    private static final int REQUEST_ADD = 1;
    private static final int REQUEST_UPDATE = 2;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipe;

    public PenjualanFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View x = inflater.inflate(R.layout.fragment_barang, container, false);
        ButterKnife.bind(this, x );
        getActivity().setTitle("Penjualan / Transaksi ");

        session = new SessionManager(getActivity());
        presenter = new PenjualanPresenter(this);
        presenter.getPenjualan(session.getKeyToken(), "");

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getPenjualan(session.getKeyToken(), "");
            }
        });

        return x;

    }

    @OnClick(R.id.fab) void editor() {
        Intent intent = new Intent(getActivity(), PenjualanActivity.class);
        startActivityForResult(intent, REQUEST_ADD);
    }

    @Override
    public void showProgress() {
        swipe.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipe.setRefreshing(false);
    }

    @Override
    public void statusSuccess(PenjualanResponse penjualanResponse) {
        adapter = new PenjualanAdapter(penjualanResponse.getData());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(),
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Penjualan penjualan = adapter.getPenjualan(position);

                        Intent intent = new Intent(getActivity(), PenjualanActivity.class);

                        intent.putExtra("id", penjualan.getId());
                        intent.putExtra("barang_id", penjualan.getBarang_id());
                        intent.putExtra("jumlah_barang", penjualan.getJumlah_barang());
                        intent.putExtra("jumlah_harga", penjualan.getJumlah_harga());

                        startActivityForResult(intent, REQUEST_UPDATE);
                    }
                }));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void statusError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD && resultCode == RESULT_OK) {
            presenter.getPenjualan(session.getKeyToken(), "");
        } else if (requestCode == REQUEST_UPDATE && resultCode == RESULT_OK) {
            presenter.getPenjualan(session.getKeyToken(), "");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

}
