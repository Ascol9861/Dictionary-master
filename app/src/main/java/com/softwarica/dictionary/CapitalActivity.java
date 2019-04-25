package com.softwarica.dictionary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class CapitalActivity extends AppCompatActivity {

    private TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capital);

        Bundle bundle = getIntent().getExtras();

        if(bundle!=null){
            String capital = bundle.getString("capital");
            tvMessage = findViewById(R.id.tv_message);
            tvMessage.setText(capital);
        }
        else {
            Toast.makeText(this, "No meaning", Toast.LENGTH_LONG).show();
        }
    }
}
