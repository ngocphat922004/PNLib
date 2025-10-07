package com.example.pnlib.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.pnlib.Model.Book;
import com.example.pnlib.Model.Theloai;
import com.example.pnlib.R;
import com.example.pnlib.SanPhamdao.SanPhamDao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class Add_book extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;

    byte[] imagebook;
    ImageView imageViewbook;

    SanPhamDao sanPhamDao;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sanPhamDao = new SanPhamDao(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_add_book, container, false);

        AppCompatActivity activity = (AppCompatActivity) getActivity();

        Button btnuploadimage = view.findViewById(R.id.btnuploandanh);
        imageViewbook = view.findViewById(R.id.imgBook);
        EditText edttensach = view.findViewById(R.id.edttensach);
        EditText edtgia = view.findViewById(R.id.edtgiasach);
        EditText edtsoluong = view.findViewById(R.id.edtsoluong);
        EditText edttheloai = view.findViewById(R.id.edttheloai);
        EditText edtnxb = view.findViewById(R.id.edtnhaxb);
        EditText edtngonngu = view.findViewById(R.id.edtngonngu);
        EditText edtquocgia = view.findViewById(R.id.edtquocgia);
        EditText edtnamsx = view.findViewById(R.id.edtnamsx);
        EditText edtnoidung = view.findViewById(R.id.edtnoidung);
        EditText edtgiamuon = view.findViewById(R.id.edtgiamuon);
        Button btnadd = view.findViewById(R.id.btnthemsach);


        btnuploadimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mở hộp thoại ảnh từ thư viện
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,PICK_IMAGE_REQUEST);
            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tensach = edttensach.getText().toString();
                String giasach = edtgia.getText().toString();
                String soluong = edtsoluong.getText().toString();
                String theloai = edttheloai.getText().toString();
                String nxb = edtnxb.getText().toString();
                String ngonngu = edtngonngu.getText().toString();
                String quocgia = edtquocgia.getText().toString();
                String namsx = edtnamsx.getText().toString();
                String noidung = edtnoidung.getText().toString();
                String giamuon = edtgiamuon.getText().toString();
                if (tensach.isEmpty() || giasach.isEmpty() || soluong.isEmpty() ||theloai.isEmpty() ||nxb.isEmpty() || giamuon.isEmpty() || ngonngu.isEmpty() || quocgia.isEmpty() || namsx.isEmpty() || noidung.isEmpty()){
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    Book book = new Book();
                    book.setImagesach(imagebook);
                    book.setTensach(tensach);
                    book.setGiasach(Double.parseDouble(giasach));
                    book.setGiamuon(Double.parseDouble(giamuon));
                    book.setNgonngu(ngonngu);
                    book.setNamsx(namsx);
                    book.setQuocgia(quocgia);
                    book.setNoidung(noidung);
                    book.setSoluong(Integer.parseInt(soluong));
                    book.setTheloai(theloai);
                    book.setNxb(nxb);
                    boolean check = sanPhamDao.addbook(book);
                    if (check){
                        Toast.makeText(getContext(), "Đã thêm sản phẩm mới", Toast.LENGTH_SHORT).show();
                        FragmentManager fragmentManager = activity.getSupportFragmentManager();
                        Fragment fragment = new Fragment_list_Book();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentmain,fragment);
                        fragmentTransaction.commit();
                    }
                    String tentheloai = theloai.toLowerCase();
                    String newtheloai = sanPhamDao.checktheloai(tentheloai);

                    if (newtheloai.equalsIgnoreCase(tentheloai)){

                    }else {
                        Theloai theloai1 = new Theloai();
                        theloai1.setTentheloai(theloai.toLowerCase());
                        boolean checktheloai = sanPhamDao.addtheloai(theloai1);
                        
                    }

                }
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == -1 && data.getData() != null){
        // lấy đường dẩn ảnh đã chọn
        Uri selectImageUri = data.getData();
        //hiển thị ảnh đã chọn lên imageview
        imageViewbook.setImageURI(selectImageUri);

        //sử dụng alide để nén ảnh
        Glide.with(getContext())
                .asBitmap()
                .load(selectImageUri) //đường dẩn ảnh đã chọn
                .apply(new RequestOptions().override(600,700))// Điều chỉnh kích thước ảnh nếu cần 600px , 700px
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        //nén xong tiếp tục sử lí ảnh nén
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        resource.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                        imagebook = outputStream.toByteArray();
                        try {
                            outputStream.close();
                        }catch (IOException e){
                            throw new RuntimeException(e);
                        }
                    }
                });
        }
    }
}