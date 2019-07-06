package com.example.entregable3.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.entregable3.R;
import com.example.entregable3.model.dao.ObraDAO;
import com.example.entregable3.model.pojo.Obra;
import com.example.entregable3.model.pojo.ObraContainer;
import com.example.entregable3.retrofit.ResultListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentList extends Fragment {


    public FragmentList() {
        // Required empty public constructor
    }

    RecyclerView recycler;
    AdapterObras adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        recycler = view.findViewById(R.id.frag_list_recycler);

        recycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter = new AdapterObras();
        recycler.setAdapter(adapter);

        ObraDAO dao = new ObraDAO();
        dao.getObras(new ResultListener<ObraContainer>() {
            @Override
            public void finish(ObraContainer result) {
                setList(result.getObras());
            }
        });

        return view;
    }

    private void setList(List<Obra> obras)
    {
        this.adapter.setList(obras);
    }

}
