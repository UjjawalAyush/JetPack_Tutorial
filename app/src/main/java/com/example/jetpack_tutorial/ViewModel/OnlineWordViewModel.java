package com.example.jetpack_tutorial.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.jetpack_tutorial.Data.OnlineWord;
import com.example.jetpack_tutorial.Repository.OnlineWordRepository;

import java.util.List;

public class OnlineWordViewModel extends AndroidViewModel {
    private MutableLiveData<List<OnlineWord>> mAllWords= new MutableLiveData<>();;
    OnlineWordRepository mRepository;
    public OnlineWordViewModel (Application application) {
        super(application);
        mRepository = new OnlineWordRepository(application);
        mAllWords=mRepository.getAllWords();
    }
    public MutableLiveData<List<OnlineWord>> getAllWords() {
        return mAllWords;
    }
    public void insert(OnlineWord word) {
        mRepository.insert(word);
    }
}
