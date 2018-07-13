package com.acadview.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.channelList);

        String [] channelName = {"ABC News","The Times Of India","National Geographic","Globo","Google News (India)","CNN","ABC News","IGN"};
        final String[] channelID = {"abc-news","the-times-of-india","national-geographic","globo","google-news-in","cnn","abc-news","ign"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,channelName);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String id = channelID[i];

                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }
}
