package com.ujjwal.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<NewsDetail> newsDetailList;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView=findViewById(R.id.logout);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue=VolleySingleton.getmInstance(this).getRequestQueue();

        newsDetailList = new ArrayList<>();
        fetchData();

    }

  private void fetchData(){
     String url="https://saurav.tech/NewsAPI/top-headlines/category/health/in.json";

      JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
          @Override
          public void onResponse(JSONObject response) {
              try {
                  JSONArray jsonArray = response.getJSONArray("articles");
                  for (int i = 0; i < jsonArray.length(); i++) {
                      JSONObject jsonObject = jsonArray.getJSONObject(i);
                      String imageUrl = jsonObject.getString("urlToImage");
                      String time = jsonObject.getString("publishedAt");
                      JSONObject src=jsonObject.getJSONObject("source");
                      String source=src.getString("name");
                      String title = jsonObject.getString("title");
                      String description = jsonObject.getString("description");

                      NewsDetail post = new NewsDetail(title,time,source,description,imageUrl);
                      newsDetailList.add(post);

                  }
                  NewsAdapter adapter = new NewsAdapter(MainActivity.this, newsDetailList);
                  recyclerView.setAdapter(adapter);
                  adapter.notifyDataSetChanged();
              } catch (JSONException e) {
                  e.printStackTrace();
              }

          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
              Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

          }
      }
      );

      requestQueue.add(jsonObjectRequest);

  }
}