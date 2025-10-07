package com.example.pnlib.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pnlib.Model.Book;
import com.example.pnlib.R;
import com.example.pnlib.SanPhamdao.SanPhamDao;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Collection;


public class BottomSheetDialog extends BottomSheetDialogFragment {


    SanPhamDao sanPhamDao;
    ArrayList<Book> list;
    int masach;
    String tensach;
    int position;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<>();
        sanPhamDao = new SanPhamDao(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bottom_sheet_dialog, container, false);
        TextView iceditsach = view.findViewById(R.id.iceditsach);
        TextView icdeletesach = view.findViewById(R.id.icdeletesach);
        TextView tvclose = view.findViewById(R.id.tvclose);

        AppCompatActivity activity = (AppCompatActivity) getActivity();

        Bundle bundle = getArguments();
        if (bundle != null){
            position = bundle.getInt("position");
            masach = bundle.getInt("masach");
            tensach = bundle.getString("tensach");
            list.addAll((ArrayList<Book>) bundle.getSerializable("list"));
        }

        iceditsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                Fragment fragment = new editbookFragment();

                Bundle bundle1 = new Bundle();
                bundle1.putInt("position",position);
                bundle1.putSerializable("list",list);
                fragment.setArguments(bundle1);

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentmain,fragment);
                fragmentTransaction.commit();
                dismiss();
            }
        });

        icdeletesach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Cảnh báo");
                builder.setIcon(R.drawable.ic_warning);
                builder.setMessage("Bạn có chắc chắn muốn xóa sách "+tensach+" không ?");

                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sanPhamDao.delete(masach);
                        Toast.makeText(activity, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        FragmentManager fragmentManager = activity.getSupportFragmentManager();
                        Fragment fragment = new Fragment_list_Book();
                        FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                        fragmentTransaction1.replace(R.id.fragmentmain,fragment);
                        fragmentTransaction1.commit();
                        dismiss();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


        tvclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }

}