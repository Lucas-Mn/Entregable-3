package com.example.entregable3.controller;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.entregable3.DEBUG;
import com.example.entregable3.model.pojo.ArtistDetails;
import com.example.entregable3.util.FoundListener;
import com.example.entregable3.util.StringListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class StorageController {

    FirebaseStorage storage;
    FirebaseDatabase database;

    public StorageController()
    {
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();
    }

    public static void getImageDownloadUrl(String path, final StringListener listener)
    {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference sr = storage.getReference(path);
        sr.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                listener.finish(uri.toString());
            }
        });
    }

    public static void uploadDatabase(String path, Object obj)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(path);
        reference.setValue(obj).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                DEBUG.LOG("uploadDatabase ERROR : " + e.getMessage());
            }
        });
    }

    public static final String PATH_ARTISTS = "Artists";

    public static void getArtistDetails(Integer artistId, final FoundListener<ArtistDetails> listener)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(PATH_ARTISTS).child(artistId.toString());
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()==null)
                    listener.onNotFound();
                else
                {
                    listener.onFound(dataSnapshot.getValue(ArtistDetails.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                DEBUG.LOG("getArtistDetails cancelled: " + databaseError.getMessage());
            }
        });
    }


}
