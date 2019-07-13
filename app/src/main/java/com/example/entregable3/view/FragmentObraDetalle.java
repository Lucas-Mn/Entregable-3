package com.example.entregable3.view;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.entregable3.R;
import com.example.entregable3.controller.StorageController;
import com.example.entregable3.model.dao.ArtistTableDAO;
import com.example.entregable3.model.dao.ObraTableDAO;
import com.example.entregable3.model.pojo.ArtistDetails;
import com.example.entregable3.model.pojo.ObraTable;
import com.example.entregable3.room.MyRoomDatabase;
import com.example.entregable3.util.FoundListener;
import com.example.entregable3.util.StringListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentObraDetalle extends Fragment
implements StringListener, FoundListener<ArtistDetails> {

    public static final String TAG_OBRA_TITLE = "obra_title";
    public static final String TAG_ARTIST_ID = "artist_id";
    public static final String TAG_OBRA_IMAGE_URL = "obra_image_url";

    View view;
    ImageView imgMain;

    TextView lblArtistName, lblInfluence;

    public FragmentObraDetalle() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_obra_detalle, container, false);

        Bundle bundle = getArguments();
        //find views
        TextView lblTitle = view.findViewById(R.id.frag_obra_detalle_lbl_title);
        imgMain = view.findViewById(R.id.frag_obra_detalle_img_main);
        lblArtistName = view.findViewById(R.id.frag_obra_detalle_lbl_artist_name);
        lblInfluence = view.findViewById(R.id.frag_obra_detalle_lbl_influence);

        lblTitle.setText(bundle.getString(TAG_OBRA_TITLE));

        MyRoomDatabase db = Room.databaseBuilder(getContext(), MyRoomDatabase.class, "database")
                .allowMainThreadQueries().build();
        ArtistTableDAO artDao = db.getArtistDAO();
        ObraTableDAO obraDao = db.getObraTableDAO();

        //get image

        ObraTable table = obraDao.getObraByName(bundle.getString(TAG_OBRA_TITLE));
        if(table != null) loadBiteArray(table.getImage());
        //StorageController.getImageDownloadUrl(bundle.getString(TAG_OBRA_IMAGE_URL), this);

        //get artist details
        ArtistDetails artistDetails = artDao.getArtistById(new Long(bundle.getInt(TAG_ARTIST_ID))).toPojo();
        if(artistDetails != null) loadArtistDetails(artistDetails);
        else StorageController.getArtistDetails(bundle.getInt(TAG_ARTIST_ID), this);

        return view;
    }

    //listener for getting image URL
    @Override
    public void finish(String s) {
        Glide.with(view).load(s).into(imgMain);
    }

    //finish getting artist details from Storage
    @Override
    public void onFound(ArtistDetails result) {
        loadArtistDetails(result);
    }

    @Override
    public void onNotFound() {
        Toast.makeText(getContext(), "Error loading artist details", Toast.LENGTH_LONG).show(); }

    @Override
    public void onCancelled() {
        Toast.makeText(getContext(), "No internet", Toast.LENGTH_LONG).show(); }
    
    private void loadArtistDetails(ArtistDetails art)
    {
        lblArtistName.setText("by " + art.getName() + " from " + art.getNationality());
        lblInfluence.setText("influenced by " + art.getInfluenced_by());
    }

    private void loadBiteArray(byte[] bytes)
    {
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        imgMain.setImageBitmap(bitmap);
    }
}
