package com.example.gio.blackproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ImageView ivPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpViews();

        // surati
//        RequestBody file = RequestBody.create(MediaType.parse("image/*"),"drawable://" + R.drawable.givii);

        //surati

//         make Retrofit class
                Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.107").addConverterFactory(GsonConverterFactory.create()).build();
        RetrofitApi api = retrofit.create(RetrofitApi.class);

        final Call<List<Name>> names = api.getTag("Interstellar", "name");

            names.enqueue(new Callback<List<Name>>() {
            @Override
            public void onResponse(Call<List<Name>> call, Response<List<Name>> response) {
                Log.d("onRespone", response.body().get(0).getTag());
                Log.d("onRespone", response.body().get(1).getName());

                Glide.with(getBaseContext())
                        .load(response.body().get(2).getImage())
                        .into(ivPoster);
            }

            @Override
            public void onFailure(Call<List<Name>> call, Throwable t) {
                Log.d("hereeeeeeeeeeeee", t.getMessage());
                Log.d("onFail                   :", "fail");
            }
        });


        }


    public void setUpViews(){
        ivPoster = (ImageView)findViewById(R.id.ivPoster);
    }
    }
