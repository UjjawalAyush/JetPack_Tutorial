package com.example.jetpack_tutorial.UI_Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jetpack_tutorial.Adapter.OnlineWordListAdapter;
import com.example.jetpack_tutorial.Data.OnlineWord;
import com.example.jetpack_tutorial.R;
import com.example.jetpack_tutorial.ViewModel.OnlineWordViewModel;

import java.util.List;

public class OnlineActivity extends AppCompatActivity {
    private OnlineWordViewModel mWordViewModel;
    OnlineWordListAdapter adapter;
    Bundle temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.online_activity);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        adapter=new OnlineWordListAdapter(OnlineActivity.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mWordViewModel = ViewModelProviders.of(OnlineActivity.this).get(OnlineWordViewModel.class);
        mWordViewModel.getAllWords().observe(this, new Observer<List<OnlineWord>>(){                    //To observe if livedata is changed
            @Override
            public void onChanged(@Nullable final List<OnlineWord> words) {
                adapter.setWords(words);
            }
        });
        EditText mEditText=findViewById(R.id.editTextTextPersonName);
        Button fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=mEditText.getText().toString().trim();
                if(text.equals("")) Toast.makeText(getApplicationContext(), "Empty strings do not get saved", Toast.LENGTH_LONG).show();
                else {
                    OnlineWord word = new OnlineWord(text);
                    mWordViewModel.insert(word);
                    mEditText.setText("");
                }
            }
        });
    }
    public void onClick(View view){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            OnlineWord word = new OnlineWord(data.getStringExtra(NewWordActivity.EXTRA_REPLY));
            mWordViewModel.insert(word);
        }
        else {
            Toast.makeText(getApplicationContext(), "Empty strings do not get saved", Toast.LENGTH_LONG).show();
        }
    }
}
