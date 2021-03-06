package com.example.jetpack_tutorial.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.jetpack_tutorial.Repository.WordRepository;
import com.example.jetpack_tutorial.Data.Word;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository mRepository;
    private LiveData<List<Word>> mAllWords;
    public WordViewModel (Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }
    public LiveData<List<Word>> getAllWords() { return mAllWords; }
    public void insert(Word word) { mRepository.insert(word); }
}