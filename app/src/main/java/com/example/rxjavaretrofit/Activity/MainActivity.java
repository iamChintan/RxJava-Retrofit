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
import java.util.function.Consumer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
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
                .subscribe(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()))
                .subscribe(new Consumer<List<Root>>() {

                    @Override
                    public void accept(List<Root> roots) {
                        diplayData(roots);
                    }
                })
    }

    private void diplayData(List<Root> roots) {
        PostAdaptor adaptor = new PostAdaptor(this, roots);
        recyclerView.setAdapter(adaptor);
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}