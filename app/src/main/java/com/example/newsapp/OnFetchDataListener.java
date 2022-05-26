package com.example.newsapp;

import com.example.newsapp.Models.Headline;

import java.util.List;

public interface OnFetchDataListener<NewsApiResponse> {
    void onFetchData (List<Headline> list, String message);
    void onError(String message);
}
