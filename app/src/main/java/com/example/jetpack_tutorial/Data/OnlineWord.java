package com.example.jetpack_tutorial.Data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
public class OnlineWord {
    private String mWord;

    public OnlineWord(@NonNull String word) {this.mWord = word;}

    public String getWord(){return this.mWord;}
}