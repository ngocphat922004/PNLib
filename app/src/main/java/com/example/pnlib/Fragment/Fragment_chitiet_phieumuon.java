package com.example.pnlib.Fragment;

import android.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pnlib.Model.PhieuMuon;
import com.example.pnlib.R;
import com.example.pnlib.SanPhamdao.SanPhamDao;

import java.io.Console;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


public class Fragment_chitiet_phieumuon extends Fragment {

    SanPhamDao sanPhamDao;
    ArrayList<PhieuMuon> list;
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
        View view = inflater.inflate(R.layout.fragment_chitiet_phieumuon, container, false);
        EditText edttennguoinhap = view.findViewById(R.id.edttennguoinhapphieumuon);
        EditText edttengnuoimuon = view.findViewById(R.id.edttengnuoimuonphieumuon);
        EditText edtsoluongmuon = view.findViewById(R.id.edtsoluongphieumuon);
        TextView edtngaymuon = view.findViewById(R.id.edtngayphieumuon);
        TextView edttensachmuon = view.findViewById(R.id.edttensachphieumuon);
        EditText edtgiaphieumuon = view.findViewById(R.id.edtgiamuonphieumuon);
        Button btnxacnhantra = view.findViewById(R.id.btnxacnhantrasach);

        AppCompatActivity activity = (AppCompatActivity) getActivity();

        Bundle bundle = getArguments();
        if (bundle != null){
            position = bundle.getInt("position");
            list.addAll((ArrayList<PhieuMuon>) bundle.getSerializable("list"));
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,### Vnđ");

        edttennguoinhap.setText(list.get(position).getTennguoinhap());
        edttengnuoimuon.setText(list.get(position).getTennguoimuon());
        edtsoluongmuon.setText(""+list.get(position).getSoluong());
        edtgiaphieumuon.setText(""+decimalFormat.format(list.get(position).getGiamuon()));
        edtngaymuon.setText(list.get(position).getNgaymuon());
        edttensachmuon.setText(list.get(position).getTensach());

        btnxacnhantra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater =activity.getLayoutInflater();
                View view = inflater.inflate(R.layout.layout_trasach,null);
                builder.setView(view);
                AlertDialog dialog = builder.create();

                TextView tvngaytrathanhtoan = view.findViewById(R.id.tvngaytrathanhtoan);
                TextView tvtennguoimuonthanhtoan = view.findViewById(R.id.tvtennguoimuonthanhtoan);
                TextView tvsoluongthanhtoan = view.findViewById(R.id.tvsoluongthanhtoan);
                TextView tvtongngaymuonthanhtoan = view.findViewById(R.id.tvtongngaymuonthanhtoan);
                TextView tvgiamuonthanhtoan = view.findViewById(R.id.tvgiamuonthanhtoan);
                TextView tvtongthanhtoan = view.findViewById(R.id.tvtongthanhtoan);
                TextView tvtensachthanhtoan = view.findViewById(R.id.tvtensachthanhtoan);
                ImageView imagemuonthanhtoan = view.findViewById(R.id.imagemuonthanhtoan);
                Button btnxacnhanthanhtoan = view.findViewById(R.id.btnxacnhanthanhtoan);

                Bitmap bitmap = BitmapFactory.decodeByteArray(list.get(position).getImagesachmuon(),0,list.get(position).getImagesachmuon().length);
                imagemuonthanhtoan.setImageBitmap(bitmap);
                tvtensachthanhtoan.setText(list.get(position).getTensach());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String day = sdf.format(new Date());

                tvngaytrathanhtoan.setText(day);

                int tongsongaymuon = 0;
                try {
                    Date ngaymuon = sdf.parse(list.get(position).getNgaymuon());
                    Date ngaytra = sdf.parse(day);
                    long milisecondsngaymuon = ngaymuon.getTime();
                    long milisecondsngaytra = ngaytra.getTime();
                    long milisecondstong = milisecondsngaytra - milisecondsngaymuon;
                    int songaymuon = (int) (milisecondstong/(1000*60*60*24));
                    tvtongngaymuonthanhtoan.setText(""+songaymuon);
                    tongsongaymuon = songaymuon;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                tvtennguoimuonthanhtoan.setText(list.get(position).getTennguoimuon());
                tvsoluongthanhtoan.setText(""+list.get(position).getSoluong());

                Double tong = list.get(position).getGiamuon()*tongsongaymuon*list.get(position).getSoluong();
                DecimalFormat decimalFormat = new DecimalFormat("###,### Vnđ");
                String giavnd = decimalFormat.format(tong);
                tvtongthanhtoan.setText(""+(giavnd));

                tvgiamuonthanhtoan.setText(""+decimalFormat.format(list.get(position).getGiamuon()));

                btnxacnhanthanhtoan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentManager fragmentManager = activity.getSupportFragmentManager();
                        Fragment fragment = new Fragmentlistphieumuon();

                        PhieuMuon phieuMuon = new PhieuMuon();
                        phieuMuon.setMaphieumuon(list.get(position).getMaphieumuon());
                        phieuMuon.setImagesachmuon(list.get(position).getImagesachmuon());
                        phieuMuon.setTensach(list.get(position).getTensach());
                        phieuMuon.setTennguoimuon(list.get(position).getTennguoimuon());
                        phieuMuon.setTennguoinhap(list.get(position).getTennguoinhap());
                        phieuMuon.setSoluong(list.get(position).getSoluong());
                        phieuMuon.setGiamuon(list.get(position).getGiamuon());
                        phieuMuon.setNgaymuon(list.get(position).getNgaymuon());
                        phieuMuon.setTinhtrang("Đã thanh toán");
                        boolean check = sanPhamDao.updatephieumuon(phieuMuon);
                        if (check){
                            Toast.makeText(getContext(), "Thanh toán thành công", Toast.LENGTH_SHORT).show();

                        }
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentmain,fragment);
                        fragmentTransaction.commit();
                        dialog.dismiss();
                    }
                });




                dialog.show();
            }
        });
        return view;
    }
}