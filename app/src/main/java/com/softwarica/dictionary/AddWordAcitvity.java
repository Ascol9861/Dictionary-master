package com.softwarica.dictionary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintStream;

public class AddWordAcitvity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAddWord;
    private EditText etMeaning, etWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word_acitvity);
        btnAddWord = findViewById(R.id.btnAddWord);
        etMeaning = findViewById(R.id.etMeaning);
        etWord = findViewById(R.id.etWord);

        btnAddWord.setOnClickListener(this);
    }



    private void Save(){
        try {
            PrintStream printStream = new PrintStream(openFileOutput("words.txt", MODE_PRIVATE | MODE_APPEND));
            printStream.println(etWord.getText().toString() + "->" + etMeaning.getText().toString());
            Toast.makeText(this, "Saved to" + getFilesDir(), Toast.LENGTH_SHORT).show();
            printStream.close();
        } catch (IOException e) {
            Log.d("Dictionary app", "Error: " + e.toString());
        }
    }

    @Override
    public void onClick(View v) {
        Save();
    }
}
