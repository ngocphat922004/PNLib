package com.example.pnlib.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.example.pnlib.Adapters.Adapterbook;
import com.example.pnlib.Model.Book;
import com.example.pnlib.R;
import com.example.pnlib.SanPhamdao.SanPhamDao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class editbookFragment extends Fragment {

    SanPhamDao sanPhamDao;
    ArrayList<Book> list;
    ImageView imageViewbook;

    private static final int PICK_IMAGE_REQUEST = 1;

    byte[] imageedtbook;

    int position ;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<>();
        sanPhamDao = new SanPhamDao(requireContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editbook, container, false);
        Button btnuploadimage = view.findViewById(R.id.btnuploandanhedit);
        imageViewbook = view.findViewById(R.id.imgBookedit);
        EditText edttensach = view.findViewById(R.id.edttensachedit);
        EditText edtgia = view.findViewById(R.id.edtgiasachedit);
        EditText edtsoluong = view.findViewById(R.id.edtsoluongedit);
        EditText edttheloai = view.findViewById(R.id.edttheloaiedit);
        EditText edtnxb = view.findViewById(R.id.edtnhaxbedit);
        EditText edtngonngu = view.findViewById(R.id.edtngonnguedit);
        EditText edtquocgia = view.findViewById(R.id.edtquocgiaedit);
        EditText edtnamsx = view.findViewById(R.id.edtnamsxedit);
        EditText edtnoidung = view.findViewById(R.id.edtnoidungedit);
        EditText editgiamuon = view.findViewById(R.id.edtgiamuonedit);
        Button btncapnhat = view.findViewById(R.id.btncapnhat);

        AppCompatActivity activity = (AppCompatActivity) getActivity();


        Bundle bundle = getArguments();
        if (bundle != null) {
            position = bundle.getInt("position");
            list.addAll((ArrayList<Book>) bundle.getSerializable("list"));

            Bitmap bitmap = BitmapFactory.decodeByteArray(list.get(position).getImagesach(), 0, list.get(position).getImagesach().length);
            imageViewbook.setImageBitmap(bitmap);
            edttensach.setText(list.get(position).getTensach());
            edtgia.setText(String.valueOf(list.get(position).getGiasach()));
            editgiamuon.setText(String.valueOf(list.get(position).getGiamuon()));
            edtngonngu.setText(list.get(position).getNgonngu());
            edtnamsx.setText(list.get(position).getNamsx());
            edtquocgia.setText(list.get(position).getQuocgia());
            edtnoidung.setText(list.get(position).getNoidung());
            edtsoluong.setText(String.valueOf(list.get(position).getSoluong()));
            edttheloai.setText(list.get(position).getTheloai());
            edtnxb.setText(list.get(position).getNxb());
        }
        btnuploadimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mở hộp thoại chọn  ảnh từ thư viện
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,PICK_IMAGE_REQUEST);
            }
        });

        btncapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tensach = edttensach.getText().toString();
                String giasach = edtgia.getText().toString();
                String giamuon = editgiamuon.getText().toString();
                String soluong = edtsoluong.getText().toString();
                String theloai = edttheloai.getText().toString();
                String nxb = edtnxb.getText().toString();
                String ngonngu = edtngonngu.getText().toString();
                String quocgia = edtquocgia.getText().toString();
                String namsx = edtnamsx.getText().toString();
                String noidung = edtnoidung.getText().toString();
                if (tensach.isEmpty() || giasach.isEmpty() || soluong.isEmpty() || theloai.isEmpty() || nxb.isEmpty() || giamuon.isEmpty()){
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    Book book = new Book();
                    book.setMasach(list.get(position).getMasach()); // updata lại vị trí cũ
                    if (imageedtbook != null){
                        book.setImagesach(imageedtbook);
                    }else {
                        book.setImagesach(list.get(position).getImagesach());
                    }
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
                    boolean check = sanPhamDao.updatebook(book);
                    if (check){
                        Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        FragmentManager fragmentManager = activity.getSupportFragmentManager();
                        Fragment fragment = new Fragment_list_Book();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentmain,fragment);
                        fragmentTransaction.commit();
                    }else {
                        Toast.makeText(getContext(), "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == -1 && data != null && data.getData() != null){
            //lấy đường dẩn ảnh đã chọn
            Uri imagebook = data.getData();
            imageViewbook.setImageURI(imagebook);

            //sử dụng glide để nén ảnh
            Glide.with(getContext())
                    .asBitmap()
                    .load(imagebook)
                    .apply(new RequestOptions().override(600,700))
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            //nén xong tiếp tục xử lí ảnh
                            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                            resource.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                            imageedtbook = outputStream.toByteArray();
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