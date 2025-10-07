package com.example.pnlib.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pnlib.Model.Book;
import com.example.pnlib.Model.Theloai;
import com.example.pnlib.R;

import java.util.List;

public class Adapter_theloai extends RecyclerView.Adapter<Adapter_theloai.ViewHoder> {
    private List<Theloai> list;

    private ItemclickListenertheloai itemclickListenertheloai;


    public interface  ItemclickListenertheloai{
       void onclickitemtheloai(int position);
    }

    public Adapter_theloai(List<Theloai> list, ItemclickListenertheloai itemclickListenertheloai) {
        this.list = list;
        this.itemclickListenertheloai = itemclickListenertheloai;
    }

    @NonNull
    @Override
    public Adapter_theloai.ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theloai,parent,false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_theloai.ViewHoder holder, int position) {
        Theloai theloai = list.get(position);
        holder.tvtheloai.setText(theloai.getTentheloai().substring(0,1).toUpperCase() + theloai.getTentheloai().substring(1).toLowerCase());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemclickListenertheloai != null){
                    itemclickListenertheloai.onclickitemtheloai(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        TextView tvtheloai;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            tvtheloai = itemView.findViewById(R.id.tvtheloai);
        }
    }
}
