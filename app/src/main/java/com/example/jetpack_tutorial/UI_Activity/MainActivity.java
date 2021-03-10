package com.example.jetpack_tutorial.UI_Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jetpack_tutorial.Adapter.WordListAdapter;
import com.example.jetpack_tutorial.Data.Word;
import com.example.jetpack_tutorial.R;
import com.example.jetpack_tutorial.ViewModel.WordViewModel;

import java.util.List;
public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 1;
    private WordViewModel mWordViewModel;
    private EditText mEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final WordListAdapter adapter = new WordListAdapter(this);                                //Adapter for the recyclerview
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        LiveData<List<Word>> live= mWordViewModel.getAllWords();
        live.observe(this, new Observer<List<Word>>() {                    //To observe if livedata is changed
            @Override
            public void onChanged(@Nullable final List<Word> words) {
                adapter.setWords(words);

            }
        });
        mEditText = findViewById(R.id.editTextTextPersonName);
        Button fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text= mEditText.getText().toString().trim();
                if(text.equals("")) Toast.makeText(getApplicationContext(), "Empty strings do not get saved", Toast.LENGTH_LONG).show();
                else {
                    Word word = new Word(text);
                    mWordViewModel.insert(word);
                    mEditText.setText("");
                }
            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Word word = new Word(data.getStringExtra(NewWordActivity.EXTRA_REPLY));
            mWordViewModel.insert(word);
        }
        else {
            Toast.makeText(getApplicationContext(), "Empty strings do not get saved", Toast.LENGTH_LONG).show();
        }
    }

    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, OnlineActivity.class);
        startActivity(intent);
    }
}