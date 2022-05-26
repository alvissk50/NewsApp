package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsapp.Models.Headline;
import com.squareup.picasso.Picasso;

import retrofit2.http.HEAD;

public class DetailActivity extends AppCompatActivity {
    Headline headline;
    TextView text_title, text_author, text_time, text_detail, text_content;
    ImageView image_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        text_title = findViewById(R.id.text_detail_title);
        text_author = findViewById(R.id.text_detail_author);
        text_time = findViewById(R.id.text_detail_time);
        text_detail= findViewById(R.id.text_detail_detail);
        text_content = findViewById(R.id.text_detail_content);
        image_news = findViewById(R.id.img_detail_news);

        headline = (Headline) getIntent().getSerializableExtra("data");

        text_title.setText(headline.getTitle());
        text_author.setText(headline.getAuthor());
        text_time.setText(headline.getPublishedAt() );
        text_detail.setText(headline.getDescription());
        text_content.setText(headline.getContent());

        Picasso.get().load(headline.getUrlToImage()).into(image_news);
    }
}