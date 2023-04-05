package com.example.rxjavaretrofit.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.rxjavaretrofit.Adapter.PostAdaptor;
import com.example.rxjavaretrofit.Interface.IrootService;
import com.example.rxjavaretrofit.Model.Root;
import com.example.rxjavaretrofit.Retrofit.RetrofitClient;
import com.example.rxjavaretrpfot.R;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    IrootService irootService;
    RecyclerView recyclerView;
    CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = RetrofitClient.getInstance();
        irootService = retrofit.create(IrootService.class);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_posts);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchData();
    }

    private void fetchData() {
        compositeDisposable.add(irootService.getRoots()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((Consumer<? super List<Root>>) posts -> displayData(posts)));
    }

    private void displayData(List<Root> posts) {
        PostAdaptor adapter = new PostAdaptor(this,posts);
        recyclerView.setAdapter(adapter);
    }


    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}