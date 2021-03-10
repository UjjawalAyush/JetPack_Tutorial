package com.example.jetpack_tutorial.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.jetpack_tutorial.Data.OnlineWord;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OnlineWordRepository {
    private MutableLiveData<List<OnlineWord>> mAllWords;

    public OnlineWordRepository(Application application) {
        mAllWords=new MutableLiveData<>();
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Input").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<OnlineWord> temp=new ArrayList<>();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    OnlineWord i=new OnlineWord((String) dataSnapshot.child("word").getValue());
                    temp.add(i);
                }
                mAllWords.postValue(temp);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public MutableLiveData<List<OnlineWord>> getAllWords() {
        return mAllWords;
    }
    public void insert (OnlineWord word) {
        new insertAsyncTask().execute(word);
    }
    private static class insertAsyncTask extends AsyncTask<OnlineWord, Void, Void> {
        insertAsyncTask(){

        }
        @Override
        protected Void doInBackground(final OnlineWord... params) {
            DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
            DatabaseReference databaseReference1=databaseReference.child("Input").push();
            databaseReference1.setValue(params[0]);
            return null;
        }
    }
}