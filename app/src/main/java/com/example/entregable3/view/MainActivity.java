package com.example.entregable3.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.entregable3.DEBUG;
import com.example.entregable3.R;
import com.example.entregable3.model.dao.ArtistTableDAO;
import com.example.entregable3.model.pojo.ArtistTable;
import com.example.entregable3.model.pojo.Obra;
import com.example.entregable3.room.MyRoomDatabase;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class MainActivity extends AppCompatActivity
implements AdapterObras.Listener
{

    NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navView = findViewById(R.id.act_main_nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                MainActivity.this.onNavigationItemSelected(menuItem.getItemId());
                return false;
            }
        });

//        //region DEBUG
//        MyRoomDatabase database = Room.databaseBuilder(this, MyRoomDatabase.class, "database")
//                .allowMainThreadQueries().build();
//        ArtistTableDAO artistTableDAO = database.getArtistDAO();
//        artistTableDAO.clearTable();
//        database.getObraTableDAO().clearTable();
//        //endregion

        pegarFragment(new FragmentList());
    }

    public void pegarFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.act_main_frag_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void onNavigationItemSelected(int id)
    {
        switch(id)
        {
            case R.id.nav_item_obras:
                pegarFragment(new FragmentList());
                break;

            case R.id.nav_item_chat:
                pegarFragment(new FragmentChat());
                break;

            case R.id.nav_item_logout:
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();
                goToLogin();
                break;
        }
    }

    private void goToLogin()
    {
        Intent intent = new Intent(this, ActivityLogin.class);
        startActivity(intent);
    }

    @Override
    public void onClick(Obra obra) {
        FragmentObraDetalle frag = new FragmentObraDetalle();
        Bundle bundle = new Bundle();
        bundle.putString(FragmentObraDetalle.TAG_OBRA_TITLE, obra.getName());
        bundle.putInt(FragmentObraDetalle.TAG_ARTIST_ID, obra.getArtistId());
        bundle.putString(FragmentObraDetalle.TAG_OBRA_IMAGE_URL, obra.getImage());
        frag.setArguments(bundle);
        pegarFragment(frag);
    }

    //region OLD
//    private void putJsonIntoDatabase()
//    {
//        ArtistController.getArtists(new ResultListener<ArtistContainer>() {
//            @Override
//            public void finish(ArtistContainer result) {
//                finishJsonIntoDatabase(result);
//            }
//        });
//    }
//
//    private void finishJsonIntoDatabase(ArtistContainer result)
//    {
//        for(Artist artist : result.getArtists())
//        {
//            StorageController.uploadDatabase("Artists/" + artist.getArtistId(), artist);
//        }
//    }
    //endregion

}
