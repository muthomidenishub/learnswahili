package com.example.swahiliapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;

import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    //create the layouts to be used
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private Button Btntranslate;
    private Button Btnexit;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find the views

        recyclerView = findViewById(R.id.result);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Btntranslate = findViewById(R.id.btn_translate);
        Btnexit = findViewById(R.id.btn_exit);
        editText = findViewById(R.id.editText);

        myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);


        Btntranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAdapter.setDataSet(
                        translations(editText.getText().toString()));
                myAdapter.notifyDataSetChanged();

            }
        });

        Btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    //Read the files from the files words have been stored

    String[] translations(String word){
        ArrayList<String> swa_words = new ArrayList<>();
        try (
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(getAssets().open("data.csv")))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[1].equals(word)){
                    swa_words.add(values[0]);
                }

            }

            //ArrayList to String array conversion
            String[] arr = new String[swa_words.size()];
            for (int i = 0; i < swa_words.size(); i++) {
                // Assign each value to String array
                arr[i] = swa_words.get(i);
            }

            return arr;

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return new String[]{"Empty"};

    }
}
