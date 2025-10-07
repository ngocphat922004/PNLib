package com.example.pnlib.Fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pnlib.Model.Book;
import com.example.pnlib.R;
import com.example.pnlib.SanPhamdao.SanPhamDao;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class Fragmentmuon extends Fragment {

    SanPhamDao sanPhamDao;
    ArrayList<Book> list;

    int position;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<>();
        sanPhamDao = new SanPhamDao(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragmentmuon, container, false);
        ImageView imageViewmuong = view.findViewById(R.id.imagemuon);
        TextView tvtensach = view.findViewById(R.id.tensachmuon);
        TextView tvgiasach = view.findViewById(R.id.giaban);
        TextView tvgiamuon = view.findViewById(R.id.giamuon);
        TextView tvtheloai = view.findViewById(R.id.theloaimuon);
        TextView tvngonngu = view.findViewById(R.id.ngonngumuon);
        TextView tvnamsx = view.findViewById(R.id.namsxmuon);
        TextView tvquocgia = view.findViewById(R.id.quocgiamuon);
        TextView tvnxb = view.findViewById(R.id.nxbmuon);
        TextView tvnoidung = view.findViewById(R.id.noidungmuon);
        Button btnmuon = view.findViewById(R.id.btnmuon);
        Button btnmua = view.findViewById(R.id.btnmua);

        AppCompatActivity activity = (AppCompatActivity) getActivity();

        Bundle bundle = getArguments();
        if (bundle != null){
            position = bundle.getInt("position");
            list.addAll((ArrayList<Book>) bundle.getSerializable("list"));
        }

        DecimalFormat decimalFormat = new DecimalFormat("###,### Vnđ");

        Bitmap bitmap = BitmapFactory.decodeByteArray(list.get(position).getImagesach(),0,list.get(position).getImagesach().length);
        imageViewmuong.setImageBitmap(bitmap);
        tvtensach.setText(list.get(position).getTensach());
        tvgiasach.setText(""+decimalFormat.format(list.get(position).getGiasach()));
        tvgiamuon.setText(""+decimalFormat.format(list.get(position).getGiamuon()));
        tvtheloai.setText(list.get(position).getTheloai());
        tvngonngu.setText(list.get(position).getNgonngu());
        tvnamsx.setText(list.get(position).getNamsx());
        tvquocgia.setText(list.get(position).getQuocgia());
        tvnxb.setText(list.get(position).getNxb());
        tvnoidung.setText(list.get(position).getNoidung());

        btnmuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                Fragment fragment = new Fragmentthongtinmuon();

                Bundle bundle =new Bundle();
                bundle.putInt("position",position);
                bundle.putSerializable("list",list);
                fragment.setArguments(bundle);

                FragmentTransaction  fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentmain,fragment);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}