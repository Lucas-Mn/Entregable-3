package com.example.entregable3.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.entregable3.R;
import com.example.entregable3.controller.StorageController;
import com.example.entregable3.model.pojo.Obra;
import com.example.entregable3.util.StringListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdapterObras extends RecyclerView.Adapter {

    List<Obra> obras;
    AdapterObras.Listener listener;
    //guardamos las URLs una vez encontradas para cargarlas directamente
    HashMap<String, String> imgUrls;

    public AdapterObras(List<Obra> obras)
    {
        super();
        this.obras = obras;
    }

    public AdapterObras(AdapterObras.Listener listener)
    { super(); obras = new ArrayList<>(); this.listener = listener; imgUrls = new HashMap<>();}

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
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        ViewHolderObra vh = (ViewHolderObra) holder;
        vh.bind(obras.get(position));
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(obras.get(position));
            }
        });
        //sólo sacar la URL del storage si es necesario
        if(imgUrls.containsKey(obras.get(position).getImage()))
            ((ViewHolderObra) holder).loadImage(imgUrls.get(obras.get(position).getImage()));
        else
            StorageController.getImageDownloadUrl(obras.get(position).getImage(), new StringListener() {
                @Override
                public void finish(String s) {
                    ((ViewHolderObra) holder).loadImage(s);
                    imgUrls.put(obras.get(position).getImage(), s);
                }
            });
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

        public void loadImage(String url)
        {
            Glide.with(itemView).load(url).into(img);
        }
    }

    public interface Listener
    {
        void onClick(Obra obra);
    }

}
