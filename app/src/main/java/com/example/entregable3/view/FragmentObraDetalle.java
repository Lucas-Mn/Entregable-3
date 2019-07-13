package com.example.entregable3.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.entregable3.R;
import com.example.entregable3.controller.StorageController;
import com.example.entregable3.model.pojo.ArtistDetails;
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
        StorageController.getImageDownloadUrl(bundle.getString(TAG_OBRA_IMAGE_URL), this);
        StorageController.getArtistDetails(bundle.getInt(TAG_ARTIST_ID), this);

        return view;
    }

    @Override
    public void finish(String s) {
        Glide.with(view).load(s).into(imgMain);
    }

    @Override
    public void onFound(ArtistDetails result) {
        lblArtistName.setText("by " + result.getName() + " from " + result.getNationality());
        lblInfluence.setText("influenced by " + result.getInfluenced_by());
    }

    @Override
    public void onNotFound() {
        Toast.makeText(getContext(), "lol oops", Toast.LENGTH_LONG).show();
    }
}
