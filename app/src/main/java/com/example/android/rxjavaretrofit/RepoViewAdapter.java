package com.example.android.rxjavaretrofit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Siris on 1/24/2017.
 */

public class RepoViewAdapter extends RecyclerView.Adapter<RepoViewAdapter.CustomViewHolder> {

    private List<ResultAPI> repoList;

    public RepoViewAdapter(List<ResultAPI> repoList) {
        this.repoList = repoList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{
        protected TextView tv;

        public CustomViewHolder(View view) {
            super(view);
            this.tv = (TextView) view.findViewById(R.id.text_rv);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_view, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        ResultAPI item = repoList.get(position);
        holder.tv.setText(item.getName());

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "test", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }
}
