package com.example.entregable3.view;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.entregable3.R;
import com.example.entregable3.model.pojo.Obra;

import java.util.ArrayList;
import java.util.List;

public class AdapterObras extends RecyclerView.Adapter {

    List<Obra> obras;

    public AdapterObras(List<Obra> obras)
    {
        super();
        this.obras = obras;
    }

    public AdapterObras()
    { super(); obras = new ArrayList<>(); }

    public void setList(List<Obra> obras)
    {
        this.obras = obras;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ViewHolderObra vh = new ViewHolderObra(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_obra, parent, false));
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderObra vh = (ViewHolderObra) holder;
        vh.bind(obras.get(position));
    }

    @Override
    public int getItemCount() {
        return obras.size();
    }

    public static class ViewHolderObra extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView lblTitle;

        public ViewHolderObra(View view)
        {
            super(view);
            img = view.findViewById(R.id.cell_obra_img);
            lblTitle = view.findViewById(R.id.cell_obra_title);
        }

        public void bind(Obra obra)
        {
            lblTitle.setText(obra.getName());
        }
    }

}
