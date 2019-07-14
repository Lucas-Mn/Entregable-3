package com.example.entregable3.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.entregable3.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentChat extends Fragment {
    public FragmentChat() { }

    private RecyclerView recycler;
    private AdapterChat adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        recycler = view.findViewById(R.id.frag_chat_recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter = new AdapterChat();
        recycler.setAdapter(adapter);

        return view;
    }

}
