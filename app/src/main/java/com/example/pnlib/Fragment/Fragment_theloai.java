package com.example.pnlib.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pnlib.Adapters.Adapter_theloai;
import com.example.pnlib.Adapters.Adapterbook;
import com.example.pnlib.Model.Book;
import com.example.pnlib.Model.Theloai;
import com.example.pnlib.R;
import com.example.pnlib.SanPhamdao.SanPhamDao;

import java.util.ArrayList;
import java.util.List;


public class Fragment_theloai extends Fragment {

        SanPhamDao sanPhamDao;
        Adapter_theloai adaptertheloai;
        ArrayList<Theloai> list;


        Adapterbook adapterbook;
        ArrayList<Book> listbook;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sanPhamDao  = new SanPhamDao(getContext());
        list = new ArrayList<>();
        list.addAll(sanPhamDao.getlisttheloai());


        listbook = new ArrayList<>();
        listbook.addAll(sanPhamDao.getlistbook());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_theloai, container, false);
        RecyclerView recyclerViewtheloai = view.findViewById(R.id.recyclerview_theloai);
        RecyclerView recyclerViewlisttheloai = view.findViewById(R.id.rcvDanhsachtheloai);
        TextView soluongtheloai = view.findViewById(R.id.sltl);

        int number = sanPhamDao.getnumbertheloai()-1;
        soluongtheloai.setText(""+number);

        adaptertheloai = new Adapter_theloai(list, new Adapter_theloai.ItemclickListenertheloai() {
            @Override
            public void onclickitemtheloai(int position) {

                if (list.get(position).getTentheloai().equalsIgnoreCase("Tất cả")){
                    listbook.clear();
                    listbook.addAll(sanPhamDao.getlistbook());
                    adapterbook.notifyDataSetChanged();
                }else {
                    List listtheloai = sanPhamDao.Searchtheloai(list.get(position).getTentheloai());
                    listbook.clear();
                    listbook.addAll(listtheloai);
                    adapterbook.notifyDataSetChanged();
                }
            }
        });

        adapterbook = new Adapterbook(listbook, new Adapterbook.ItemclickListener() {
            @Override
            public void OnItemclick(int position) {
                Toast.makeText(getContext(), ""+listbook.get(position).getTensach(), Toast.LENGTH_SHORT).show();
            }
        });

        //hiển thị các sách có thể loại
        int colum = 2;
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),colum);
        recyclerViewlisttheloai.setLayoutManager(layoutManager);
        recyclerViewlisttheloai.setAdapter(adapterbook);

        //hiển thị danh sách thể loại
        recyclerViewtheloai.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerViewtheloai.setAdapter(adaptertheloai);

        return view;
    }
}