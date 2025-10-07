package com.example.pnlib.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pnlib.Model.Book;
import com.example.pnlib.Model.PhieuMuon;
import com.example.pnlib.R;
import com.example.pnlib.SanPhamdao.SanPhamDao;

import java.util.ArrayList;
import java.util.Calendar;


public class Fragmentthongtinmuon extends Fragment {

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
       View view = inflater.inflate(R.layout.fragment_fragmentthongtinmuon, container, false);
        EditText edttennguoinhap = view.findViewById(R.id.edttennguoinhap);
        EditText edttengnuoimuon = view.findViewById(R.id.edttengnuoimuon);
        EditText edtsoluongmuon = view.findViewById(R.id.edtsoluongmuon);
        TextView edtngaymuon = view.findViewById(R.id.edtngaymuon);
        Button btnxacnhan = view.findViewById(R.id.btnxacnhan);

        Calendar calendar = Calendar.getInstance();
        AppCompatActivity activity = (AppCompatActivity) getActivity();

        Bundle bundle = getArguments();
        if (bundle != null){
            position = bundle.getInt("position");
            list.addAll((ArrayList<Book>)bundle.getSerializable("list"));
        }

        edtngaymuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtngaymuon.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });



        btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tennguoinhap = edttennguoinhap.getText().toString();
                String tennguoimuon = edttengnuoimuon.getText().toString();
                String soluong = edtsoluongmuon.getText().toString();
                String ngaymuon = edtngaymuon.getText().toString();
                int soluongsach = list.get(position).getSoluong();
                int soluonmuon =Integer.parseInt(soluong);

                if (tennguoinhap.isEmpty() || tennguoimuon.isEmpty() || soluong.isEmpty() || ngaymuon.isEmpty()){
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    PhieuMuon phieuMuon = new PhieuMuon();
                    phieuMuon.setImagesachmuon(list.get(position).getImagesach());
                    phieuMuon.setTensach(list.get(position).getTensach());
                    phieuMuon.setTennguoimuon(tennguoimuon);
                    phieuMuon.setTennguoinhap(tennguoinhap);
                    phieuMuon.setSoluong(Integer.parseInt(soluong));
                    phieuMuon.setGiamuon(list.get(position).getGiamuon());
                    phieuMuon.setNgaymuon(ngaymuon);
                    phieuMuon.setTinhtrang("Chưa thanh toán");


                    if (soluongsach - soluonmuon < 0){
                        Toast.makeText(getContext(), "Số lượng sách thư viện không đủ", Toast.LENGTH_SHORT).show();
                    } else { //cập nhật lại số lượng đã còn sau khi mượn
                        Book book = new Book();
                        book.setMasach(list.get(position).getMasach()); // updata lại vị trí cũ
                        book.setImagesach(list.get(position).getImagesach());
                        book.setTensach(list.get(position).getTensach());
                        book.setGiasach(list.get(position).getGiasach());
                        book.setGiamuon(list.get(position).getGiamuon());
                        book.setNgonngu(list.get(position).getNgonngu());
                        book.setNamsx(list.get(position).getNamsx());
                        book.setQuocgia(list.get(position).getQuocgia());
                        book.setNoidung(list.get(position).getNoidung());
                        book.setSoluong(soluongsach - soluonmuon);
                        book.setTheloai(list.get(position).getTheloai());
                        book.setNxb(list.get(position).getNxb());
                        sanPhamDao.updatebook(book);
                        boolean check = sanPhamDao.addphieumuon(phieuMuon);
                        if (check){
                            Toast.makeText(getContext(), "Tạo phiếu mượn thành công", Toast.LENGTH_SHORT).show();
                            FragmentManager fragmentManager = activity.getSupportFragmentManager();
                            Fragment fragment = new Fragmentlistphieumuon();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.fragmentmain,fragment);
                            fragmentTransaction.commit();
                        }
                    }

                }
            }
        });
        return view;
    }
}