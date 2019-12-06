package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Titles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ListView listView =  findViewById(R.id.listView);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_titles);


        final OkHttpClient client = new OkHttpClient();
        final ArrayList<String> headings = new ArrayList<>();
        Intent intent = getIntent ();
        String title=intent.getStringExtra ("Title");

        String url = "  https://www.reddit.com/r/"+title+".json";
        final Request request = new Request.Builder().url(url).build();

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Response response = client.newCall(request).execute();
                    String data = response.body().string();
                    JSONObject jsonObject = (JSONObject) new JSONTokener(data).nextValue();
                    JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("children");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i);
                        String y= item.getJSONObject("data").getString("title");
                        headings.add(y);
                    }
                    runOnUiThread(() -> {
                        if(headings.isEmpty ()){
                            headings.add ("No Match found");
                        }
                        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(Titles.this,
                                android.R.layout.simple_list_item_1, headings);

                        ListView listView = (ListView) findViewById(R.id.listView);
                        listView.setAdapter(arrayAdapter);

                    });
                } catch (IOException | JSONException e) {
                    runOnUiThread(() -> {
                        headings.add("Error");

                    });
                }
                ;
            }
        };

        thread.start();

    }
}

