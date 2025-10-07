package com.example.pnlib.Adapters;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pnlib.Fragment.BottomSheetDialog;
import com.example.pnlib.Model.Book;
import com.example.pnlib.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.DecimalFormat;
import java.util.List;

public class Adapterbook extends RecyclerView.Adapter<Adapterbook.ViewHolder> {

    AppCompatActivity activity;

    private List<Book> list;

    private ItemclickListener itemclickListener;

    // Biến để lưu trữ vị trí của item được nhấn giữ
    private int longPressedPosition = -1;

    //-----khai báo phương thức ấn giữ hiển thị bottomsheetdialogfragment chỉnh sửa xóa
    private View.OnTouchListener onTouchListener;

    public void setOnItemTouchListener(View.OnTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
    }
    //-------

    //dùng interface để lấy vị trí
    public interface  ItemclickListener{
        void OnItemclick(int position);

    }

    public Adapterbook(List<Book> list, ItemclickListener itemclickListener) {
        this.list = list;
        this.itemclickListener = itemclickListener;
    }

    @NonNull
    @Override
    public Adapterbook.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapterbook.ViewHolder holder, int position) {
        Book book = list.get(position);
        DecimalFormat decimalFormat = new DecimalFormat("###,### Vnđ");

        holder.tvtenbook.setText(book.getTensach());
        holder.tvgiabook.setText(String.valueOf(decimalFormat.format(book.getGiasach())));
        holder.tvsoluong.setText(String.valueOf(book.getSoluong()));
        holder.imgAvatarBook.setImageBitmap(BitmapFactory.decodeByteArray(
                book.getImagesach(),0,book.getImagesach().length
        ));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý sự kiện khi bạn click (ngắn) vào phần tử
                if (itemclickListener != null) {
                    itemclickListener.OnItemclick(position);
                }
            }
        });
        

        //-----khai báo phương thức ấn giữ hiển thị bottomsheetdialogfragment chỉnh sửa xóa

        // Lấy tham chiếu đến phần tử bạn muốn thêm touch listener
        final View itemView = holder.itemView;
        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            private GestureDetector gestureDetector = new GestureDetector(itemView.getContext(), new GestureDetector.SimpleOnGestureListener() {
                @Override
                public void onLongPress(MotionEvent e) {
                    // Xử lý sự kiện khi bạn giữ phần tử (long press)
                    if (onTouchListener != null) {
                        longPressedPosition = position;
                        onTouchListener.onTouch(holder.itemView, e);
                    }

                }
            });

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return false;// Chúng ta trả về false ở đây để vẫn cho phép các sự kiện click ngắn hoạt động.
            }
        });



    }
    // Phương thức này để lấy vị trí của item được nhấn giữ
    public int getLongPressedPosition() {
        return longPressedPosition;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Book getItem (int position){
        return list.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAvatarBook;
        TextView tvtenbook, tvgiabook, tvsoluong ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatarBook = itemView.findViewById(R.id.imgAvatarBook);
            tvtenbook = itemView.findViewById(R.id.tvtenbook);
            tvgiabook = itemView.findViewById(R.id.tvgiabook);
            tvsoluong = itemView.findViewById(R.id.tvsoluong);
        }
    }
}
