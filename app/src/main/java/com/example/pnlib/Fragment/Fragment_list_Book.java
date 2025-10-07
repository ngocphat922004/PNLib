package com.example.pnlib.Fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.pnlib.Adapters.Adapterbook;
import com.example.pnlib.Database.Dbhelper;
import com.example.pnlib.Model.Book;
import com.example.pnlib.R;
import com.example.pnlib.SanPhamdao.SanPhamDao;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;


public class Fragment_list_Book extends Fragment {

    Adapterbook adapterbook;
    SanPhamDao sanPhamDao;
    ArrayList<Book> list;




    Dbhelper dbhelper;

    RecyclerView recyclerView;
    SearchView searchbook;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list = new ArrayList<>();
        sanPhamDao = new SanPhamDao(requireContext());
        list.addAll(sanPhamDao.getlistbook());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list__book, container, false);
        recyclerView = view.findViewById(R.id.rcvBook);
        searchbook = view.findViewById(R.id.Search_book);
        AppCompatActivity activity = (AppCompatActivity) getActivity();

        dbhelper = new Dbhelper(getContext());


        //--adapter click xem sách
        apdapterclick();
        //---


        searchbook.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()){
                    list.clear();
                    list.addAll(sanPhamDao.getlistbook());
                    recyclerView.setAdapter(adapterbook);
                }else {
                    List listsearch = dbhelper.Searchbook(newText);
                    list.clear();
                    list.addAll(listsearch);
                    adapterbook.notifyDataSetChanged();
                }
                return false;
            }
        });

        int numberOfColumns = 2; // Điều này có thể được thay đổi thành số cột bạn muốn
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), numberOfColumns);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterbook);


        //----ấn giữ show bottomsheetdiglogfragment chỉnh sửa xóa
        adapterclickgiu();
        //------

        return view;
    }

    public void adapterclickgiu(){

        //----ấn giữ show bottomsheetdiglogfragment chỉnh sửa xóa
        adapterbook.setOnItemTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int position = adapterbook.getLongPressedPosition();
                if (position != -1){
                    int masach = list.get(position).getMasach();
                    String tensach = list.get(position).getTensach();

                    Bundle bundle = new Bundle();
                    bundle.putInt("position",position);
                    bundle.putInt("masach",masach);
                    bundle.putString("tensach",tensach);
                    bundle.putSerializable("list",list);

                    BottomSheetDialogFragment bottomSheetFragment = new BottomSheetDialog();//bottomsheetdialogfragment
                    bottomSheetFragment.setArguments(bundle);
                    bottomSheetFragment.show(getActivity().getSupportFragmentManager(),bottomSheetFragment.getTag());//bottomsheetdialogfragment
                }

                return true;
            }
        });
        //------
    }
    public void apdapterclick(){
        adapterbook = new Adapterbook(list, new Adapterbook.ItemclickListener() {
            @Override
            public void OnItemclick(int position) {
               FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
               Fragment fragment = new Fragmentmuon();

               Bundle bundle = new Bundle();
               bundle.putInt("position",position);
               bundle.putSerializable("list",list);
               fragment.setArguments(bundle);

               FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
               fragmentTransaction.replace(R.id.fragmentmain,fragment);
               fragmentTransaction.commit();

            }
        });
    }

}