package com.example.pnlib.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pnlib.Adapters.Adapterphieumuon;
import com.example.pnlib.Model.PhieuMuon;
import com.example.pnlib.R;
import com.example.pnlib.SanPhamdao.SanPhamDao;

import java.util.ArrayList;


public class Fragmentlistphieumuon extends Fragment {

    Adapterphieumuon adapterphieumuon;
    SanPhamDao sanPhamDao ;
    ArrayList<PhieuMuon> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<>();
        sanPhamDao = new SanPhamDao(getContext());
        list.addAll(sanPhamDao.getlistphieumuon());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragmentlistphieumuon, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerviewphieumuon);

        adapterphieumuon = new Adapterphieumuon(list, new Adapterphieumuon.Itemclickphieumuon() {
            @Override
            public void OnItemclickphieumuon(int position) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Fragment fragment = new Fragment_chitiet_phieumuon();

                Bundle bundle = new Bundle();
                bundle.putInt("position",position);
                bundle.putSerializable("list",list);
                fragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentmain,fragment);
                fragmentTransaction.commit();

            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapterphieumuon);


        return view;
    }
}