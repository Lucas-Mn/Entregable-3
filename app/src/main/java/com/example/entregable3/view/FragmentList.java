package com.example.entregable3.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.entregable3.DEBUG;
import com.example.entregable3.R;
import com.example.entregable3.controller.ObraController;
import com.example.entregable3.controller.StorageController;
import com.example.entregable3.model.dao.ArtistTableDAO;
import com.example.entregable3.model.pojo.ArtistDetails;
import com.example.entregable3.model.pojo.ArtistTable;
import com.example.entregable3.model.pojo.Obra;
import com.example.entregable3.model.pojo.ObraContainer;
import com.example.entregable3.model.pojo.ObraTable;
import com.example.entregable3.retrofit.ResultListener;
import com.example.entregable3.room.MyRoomDatabase;
import com.example.entregable3.util.FoundListener;
import com.google.android.gms.auth.api.signin.internal.Storage;

import java.util.ArrayList;
import java.util.List;

public class FragmentList extends Fragment {


    public FragmentList() {
        // Required empty public constructor
    }

    ImageView imgBackgroundIcon;

    RecyclerView recycler;
    AdapterObras adapter;

    MyRoomDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        imgBackgroundIcon = view.findViewById(R.id.frag_list_background_icon);
        recycler = view.findViewById(R.id.frag_list_recycler);

        recycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter = new AdapterObras((AdapterObras.Listener) getActivity(), getContext());
        recycler.setAdapter(adapter);

        db = Room.databaseBuilder(getContext(), MyRoomDatabase.class, "database")
                .allowMainThreadQueries().build();

        pullObras();
        //pullArtistDetails();

        return view;
    }

    private void pullArtistDetails()
    {
        StorageController.getAllArtistDetails(new FoundListener<List<ArtistDetails>>() {
            @Override
            public void onFound(List<ArtistDetails> result) {
                foundArtists(result);
            }

            @Override
            public void onNotFound() { }

            @Override
            public void onCancelled() {
                getArtistDetailsFromRoom(); }
        });
    }

    private void pullObras()
    {
        ObraController.getObras(new FoundListener<ObraContainer>() {
            @Override
            public void onFound(ObraContainer result) {
                setList(result.getObras()); }

            @Override
            public void onNotFound() { getObrasFromRoom(); }

            @Override
            public void onCancelled() {
                if(!getObrasFromRoom())
                    Toast.makeText(getContext(), "No internet", Toast.LENGTH_LONG).show();
                    imgBackgroundIcon.setImageResource(R.drawable.ic_no_internet);}
        });
    }

    private void foundArtists(List<ArtistDetails> result)
    {
        ArtistTableDAO dao = db.getArtistDAO();
        dao.clearTable();
        for(ArtistDetails x : result)
            dao.insert(ArtistTable.fromPojo(x));
    }

    private void setList(List<Obra> obras)
    {
        this.adapter.setList(obras);
    }

    private boolean getObrasFromRoom()
    {
        List<Obra> obras = new ArrayList<>();
        List<ObraTable> rows = db.getObraTableDAO().getObras();
        if(rows == null) return false;
        for(ObraTable x : rows)
        { obras.add(x.toObra()); }
        setList(obras);
        return true;
    }

    private boolean getArtistDetailsFromRoom()
    {
        return false;
    }

}
