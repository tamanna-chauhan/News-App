package com.acadview.app;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.networkutil.NetworkUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;

    String id;
    String API_KEY = "51b8bc13416242b593968baca622091b";
    String URL = "https://newsapi.org/v2/top-headlines?sources=";
    String data;

    Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = findViewById(R.id.detailList);
        progressBar = findViewById(R.id.progressBar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        adapter = new Adapter(this);
        recyclerView.setAdapter(adapter);

        new FetchNews().execute();

    }

    class FetchNews extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {

            String finalURL = URL+id+"&apiKey=51b8bc13416242b593968baca622091b";

            data = NetworkUtil.makeServiceCall(finalURL);

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(ProgressBar.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void aVoid) {



            super.onPostExecute(aVoid);

            ArrayList<News> newsArrayList = new ArrayList<>();
            progressBar.setVisibility(progressBar.INVISIBLE);



            if (data == null) {
                Toast.makeText(Main2Activity.this, "No data Returned", Toast.LENGTH_SHORT).show();
            } else {
                try {

                    JSONObject jsonData = new JSONObject(data);
                    JSONArray articleArray = jsonData.getJSONArray("articles");

                    for (int i = 0; i < articleArray.length(); i++) {
                        JSONObject news = articleArray.getJSONObject(i);
                        String title = news.getString("title");
                        String description = news.getString("description");
                        String imageURL = news.getString("urlToImage");

                        newsArrayList.add(new News(title, description, imageURL));

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            adapter.swap(newsArrayList);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(0);

        }
    }

}
