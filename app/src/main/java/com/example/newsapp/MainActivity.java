package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.newsapp.Models.Headline;
import com.example.newsapp.Models.NewsApiResponse;

import java.util.List;

public class MainActivity extends AppCompatActivity implements  SelectListener, View.OnClickListener {
    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog dialog;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //search view
        searchView = findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Searching news...");
                dialog.show();
                RequestManager manager = new RequestManager(MainActivity.this);manager.getHeadlines(listener, "science",query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

//fetching news... dialog
        dialog = new ProgressDialog(this);
        dialog.setTitle("Fetching news...");
        dialog.show();

        //category button
        btn1 = findViewById(R.id.btn_1);
        btn1.setOnClickListener(this);
        btn2 = findViewById(R.id.btn_2);
        btn2.setOnClickListener(this);
        btn3 = findViewById(R.id.btn_3);
        btn3.setOnClickListener(this);
        btn4 = findViewById(R.id.btn_4);
        btn4.setOnClickListener(this);
        btn5 = findViewById(R.id.btn_5);
        btn5.setOnClickListener(this);
        btn6 = findViewById(R.id.btn_6);
        btn6.setOnClickListener(this);
        btn7 = findViewById(R.id.btn_7);
        btn7.setOnClickListener(this);

//request API response
        RequestManager manager = new RequestManager(this);
        manager.getHeadlines(listener, "science",null );
    }
    private final OnFetchDataListener<NewsApiResponse> listener = new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<Headline> list, String message) {
            if(list.isEmpty()) {
                Toast.makeText(MainActivity.this, "No matching result!", Toast.LENGTH_LONG);
            }
            else {
                showNews(list);
                dialog.dismiss();
            }
            showNews(list);
            dialog.dismiss();
        }

        @Override
        public void onError(String message) {
            Toast.makeText(MainActivity.this, "Error!!!!", Toast.LENGTH_SHORT);
        }
    };

    private void showNews(List<Headline> list) {
        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new CustomAdapter(this, list, this );
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnNewsClicked(Headline headline) {
        startActivity(new Intent(MainActivity.this, DetailActivity.class).putExtra("data", headline));
    }

    @Override
    public void onClick(View view) {
        Button button = (Button)  view;
        String category = button.getText().toString();
        dialog.setTitle("Fetching news...");
        dialog.show();
        RequestManager manager = new RequestManager(this);
        manager.getHeadlines(listener, category,null );
    }
}

