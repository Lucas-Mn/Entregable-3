package com.example.entregable3.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.entregable3.R;
import com.example.entregable3.controller.StorageController;
import com.example.entregable3.model.pojo.Obra;
import com.example.entregable3.model.pojo.ObraTable;
import com.example.entregable3.room.MyRoomDatabase;
import com.example.entregable3.util.StringListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdapterObras extends RecyclerView.Adapter {

    List<Obra> obras;
    AdapterObras.Listener listener;
    //guardamos las URLs una vez encontradas para cargarlas directamente
    HashMap<String, String> imgUrls;

    private MyRoomDatabase db;
    private Context context;

    public AdapterObras(AdapterObras.Listener listener, Context context)
    {
        super(); obras = new ArrayList<>(); this.listener = listener; imgUrls = new HashMap<>();
        this.context = context;
        db = Room.databaseBuilder(context, MyRoomDatabase.class, "database")
                .allowMainThreadQueries().build();
    }

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
        final Obra obra = obras.get(position);
        vh.bind(obra, this);
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(obra);
            }
        });
//      sólo sacar la URL del storage si es necesario
        if(obra.getImage()!=null) {
            if (imgUrls.containsKey(obra.getImage()))
                ((ViewHolderObra) holder).loadImage(imgUrls.get(obra.getImage()));
            else
                StorageController.getImageDownloadUrl(obra.getImage(), new StringListener() {
                    @Override
                    public void finish(String s) {
                        ((ViewHolderObra) holder).loadImage(s);
                        imgUrls.put(obra.getImage(), s); }}); }
        else vh.loadImage(getImageFromRoom(obra));
    }

    @Override
    public int getItemCount() {
        return obras.size();
    }

    public static class ViewHolderObra extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView lblTitle;
        private AdapterObras adapter;
        private Obra obra;

        public ViewHolderObra(View view)
        {
            super(view);
            img = view.findViewById(R.id.cell_obra_img);
            lblTitle = view.findViewById(R.id.cell_obra_title);
        }

        public void bind(Obra obra, AdapterObras adapter)
        {
            lblTitle.setText(obra.getName()); this.adapter = adapter;
            this.obra = obra;
        }

        public void loadImage(String url)
        {
            Glide.with(itemView).load(url).addListener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    Bitmap bitmap = ((BitmapDrawable)resource).getBitmap();
                    adapter.saveObraToRoom(obra, bitmap);
                    return false;
                }
            }).into(img);
        }

        public void loadImage(byte[] bytes)
        {
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            img.setImageBitmap(bitmap);
            adapter.saveObraToRoom(obra, bitmap);
        }
    }

    public interface Listener
    {
        void onClick(Obra obra);
    }

    public void saveObraToRoom(Obra obra, Bitmap bitmap)
    {
        //return si ya está guardada la obra
        ObraTable check = db.getObraTableDAO().getObraByName(obra.getName());
        if(check!=null) return;

        ObraTable row = new ObraTable();
        row.setName(obra.getName());
        row.setArtistId(new Long(obra.getArtistId()));
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        row.setImage(stream.toByteArray());
        db.getObraTableDAO().insert(row);
    }

    private byte[] getImageFromRoom(Obra obra)
    {
        return db.getObraTableDAO().getObraByName(obra.getName()).getImage();
    }
}
