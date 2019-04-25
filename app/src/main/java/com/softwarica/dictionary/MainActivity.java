package com.softwarica.dictionary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

//    public static final String countries[] = {
//            "Nepal", "Kathmandu",
//            "India", "Dehli",
//            "China", "Beijing",
//            "USA", "D.C.",
//            "Uk", "London",
//            "Canada", "Ottawa"
//    };
    private ListView lstDictionary;

    private Map<String,String> dictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstDictionary = findViewById(R.id.lstDictionary);

        dictionary = new HashMap<>();
//        for (int i=0; i<countries.length; i+=2){
//            dictionary.put(countries[i],countries[i+1]);
//        }

        readFromFile();

        ArrayAdapter countryAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(dictionary.keySet())
        );
        lstDictionary.setAdapter(countryAdapter);

        lstDictionary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String key = parent.getItemAtPosition(position).toString();
                String meaning = dictionary.get(key);

                Intent intent = new Intent(MainActivity.this,CapitalActivity.class);
                intent.putExtra("Meaning",meaning);
                startActivity(intent);
            }
        });
    }

    private void readFromFile(){
        try{
            FileInputStream fos = openFileInput("words.txt");
            InputStreamReader isr = new InputStreamReader(fos);
            BufferedReader br = new BufferedReader(isr);
            String line="";
            while ((line=br.readLine()) != null){
                String[] parts = line.split("->");
                dictionary.put(parts[0], parts[1]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
