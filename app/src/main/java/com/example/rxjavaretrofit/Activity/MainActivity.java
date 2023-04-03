package com.example.rxjavaretrofit.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.rxjavaretrofit.Interface.IrootService;
import com.example.rxjavaretrpfot.R;

public class MainActivity extends AppCompatActivity {

    IrootService irootService;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}