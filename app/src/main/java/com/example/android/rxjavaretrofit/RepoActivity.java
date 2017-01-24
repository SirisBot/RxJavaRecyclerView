package com.example.android.rxjavaretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;

public class RepoActivity extends AppCompatActivity {

    private static final String TAG = "RepoActivity_TAG";
    RecyclerView rv;
    RepoViewAdapter rAdapter;
    List<ResultAPI> listRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);

        listRepo = new ArrayList<ResultAPI>();

        /*Observable<List<ResultAPI>> resultObs = RetroFitHelper.Factory.create(getIntent().getStringExtra("key"));*/

        Retrofit retrofit = RetroFitHelper.Factory.create();
        RetroFitHelper.GitHubService service = retrofit.create(RetroFitHelper.GitHubService.class);

        String user = getIntent().getStringExtra("key");
        Call<List<ResultAPI>> call = service.getRepos(user);

        call.enqueue(new Callback<List<ResultAPI>>() {
            @Override
            public void onResponse(Call<List<ResultAPI>> call, Response<List<ResultAPI>> response) {

                List<ResultAPI> myRepos = response.body();

                if (response.isSuccessful()) {
                    // do work
                    for(ResultAPI repo: myRepos) {
                        listRepo.add(repo);
                    }

                    rAdapter = new RepoViewAdapter(listRepo);

                    rv = (RecyclerView) findViewById(R.id.recycler_view);
                    rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rv.setItemAnimator(new DefaultItemAnimator());
                    rv.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));
                    rv.setAdapter(rAdapter);
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
        });
    }
}
