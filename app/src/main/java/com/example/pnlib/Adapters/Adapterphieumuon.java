package com.example.pnlib.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pnlib.Model.PhieuMuon;
import com.example.pnlib.R;

import java.util.List;

public class Adapterphieumuon extends RecyclerView.Adapter<Adapterphieumuon.ViewHolder> {

    private List<PhieuMuon> list;

    private Itemclickphieumuon itemclickphieumuon;

    //inteface để lấy vị trí
    public interface Itemclickphieumuon{
        void OnItemclickphieumuon(int position);
    }

    public Adapterphieumuon(List<PhieuMuon> list, Itemclickphieumuon itemclickphieumuon) {
        this.list = list;
        this.itemclickphieumuon = itemclickphieumuon;
    }

    @NonNull
    @Override
    public Adapterphieumuon.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phieumuon,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapterphieumuon.ViewHolder holder, int position) {
        PhieuMuon phieuMuon = list.get(position);
        holder.tvsophieu.setText(String.valueOf(phieuMuon.getMaphieumuon()));
        holder.tvtennguoimuon.setText(phieuMuon.getTennguoimuon());
        holder.tvtensachmuon.setText(phieuMuon.getTensach());
        holder.tvsoluong.setText(String.valueOf(phieuMuon.getSoluong()));
        holder.tvtinhtrang.setText(list.get(position).getTinhtrang());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bắt sự kiện click vào item phiếu mượn
                if (itemclickphieumuon != null){
                    itemclickphieumuon.OnItemclickphieumuon(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvsophieu, tvtennguoimuon, tvtensachmuon, tvsoluong, tvtinhtrang;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvsophieu = itemView.findViewById(R.id.tvsophieu);
            tvtennguoimuon = itemView.findViewById(R.id.tvtennguoimuon);
            tvtensachmuon = itemView.findViewById(R.id.tvtensachmuon);
            tvsoluong = itemView.findViewById(R.id.tvsoluongmuon);
            tvtinhtrang= itemView.findViewById(R.id.tvtinhtrang);
        }
    }
}
