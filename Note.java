package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.io.IOException;
import java.util.HashSet;

import static com.example.noteapp.MainActivity.arrayAdapters;
import static com.example.noteapp.MainActivity.notes;

public class Note extends AppCompatActivity {
    EditText editText;
    int val;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        editText= findViewById(R.id.editText);

        Intent intent = getIntent();
        val = intent.getIntExtra("notes",-1);

        if (val !=-1)
            editText.setText(notes.get(val));

        else {
            notes.add("");
            val = notes.size() -1;

        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                notes.set(val,String.valueOf(charSequence));
                arrayAdapters.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.noteapp",MODE_PRIVATE);
                HashSet <String> hashSet =new HashSet(notes);
                sharedPreferences.edit().putStringSet("notes",hashSet).apply();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

}
