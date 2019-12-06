package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.Boolean.valueOf;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AtomicReference<EditText> editText = new AtomicReference<>(findViewById(R.id.inputbox));
        ArrayList<String> url = new ArrayList<String>();

        Button button  = findViewById(R.id.btn);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener((view) -> {
            editText.set((EditText) findViewById(R.id.inputbox));
           String Input = editText.get().getText().toString();

//            if(url.contains(editText.getText().toString())) {
//                Toast.makeText(MainActivity.this,"Item already in list", Toast.LENGTH_LONG).show();
//            }
//            else{
                url.add(Input);





               ArrayAdapter arrayAdapter = new ArrayAdapter<String>(MainActivity.this,
                       android.R.layout.simple_list_item_1, url);


                   ListView listView = (ListView) findViewById(R.id.listView);
                   listView.setAdapter(arrayAdapter);


                   listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                       @Override
                       public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                           Intent intent = new Intent (getApplicationContext (), Titles.class);

                           intent.putExtra ("Title", url.get (i));
                           startActivity(intent);
                       }
                   });




        });
    }
}
