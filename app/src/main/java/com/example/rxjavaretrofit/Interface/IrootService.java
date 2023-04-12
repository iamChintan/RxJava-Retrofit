package com.example.rxjavaretrofit.Interface;

import com.example.rxjavaretrofit.Model.Root;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IrootService {

    @GET("posts/")
    Observable<List<Root>> getRoots();
}
