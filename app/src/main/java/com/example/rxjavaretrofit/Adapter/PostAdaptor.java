package com.example.rxjavaretrofit.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rxjavaretrofit.Model.Root;
import com.example.rxjavaretrpfot.R;

import java.util.List;


public class PostAdaptor extends RecyclerView.Adapter<PostAdaptor.ViewHolder> {

    List<Root> rootList;
    Context context;

    public PostAdaptor(Context ctx, List<Root> list) {
        rootList = list;
        context = ctx;
    }

    @NonNull
    @Override
    public PostAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdaptor.ViewHolder holder, int position) {
        holder.txtTitle.setText(rootList.get(position).title);
        holder.txtContent.setText(rootList.get(position).body);
    }

    @Override
    public int getItemCount() {
        Log.e("PostAdaptor", "getItemCount: " + rootList.size() );
        return rootList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle, txtContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtContent = (TextView) itemView.findViewById(R.id.txtContent);
        }
    }
}
