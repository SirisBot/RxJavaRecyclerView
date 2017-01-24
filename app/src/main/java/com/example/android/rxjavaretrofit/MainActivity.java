package com.example.android.rxjavaretrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity_TAG";

    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText) findViewById(R.id.edit_main);

        /*Retrofit retrofit = RetroFitHelper.Factory.create();
        RetroFitHelper.GitHubService service = retrofit.create(RetroFitHelper.GitHubService.class);

        Call<List<ResultAPI>> call = service.getRepos("sirisgadson");

        call.enqueue(new Callback<List<ResultAPI>>() {
            @Override
            public void onResponse(Call<List<ResultAPI>> call, Response<List<ResultAPI>> response) {

                List<ResultAPI> myRepos = response.body();

                if (response.isSuccessful()) {
                    // do work
                    for(ResultAPI repo:myRepos) {
                        Log.d(TAG, "onResponse: " + repo.toString());
                    }
                }
                else {
                    // log error
                    Log.d(TAG, "onResponse: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ResultAPI>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                t.printStackTrace();
            }
        });*/
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void searchGitHub(View view) {

        String repo = et.getText().toString();

        if (repo != null) {
            Intent i = new Intent(this, RepoActivity.class);
            i.putExtra("key", repo);
            startActivity(i);
        }
    }
}
