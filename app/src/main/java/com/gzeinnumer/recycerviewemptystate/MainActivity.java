package com.gzeinnumer.recycerviewemptystate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.gzeinnumer.recycerviewemptystate.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initRv();
    }

    private AdapterEmptyState adapter;
    private ArrayList<String> list;

    private void initRv() {
        list = new ArrayList<>();
//        list.add("data 1");
//        list.add("data 2");

        adapter = new AdapterEmptyState();
        adapter.setOnClick(position -> {
            Toast.makeText(MainActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
        });
        binding.rv.setAdapter(adapter);
        adapter.setList(list);
        binding.rv.hasFixedSize();
        binding.rv.setLayoutManager(new LinearLayoutManager(this));

        emptyState(list.size(),binding.rv, binding.imgEmpty);
    }

    private void emptyState(int size, RecyclerView rv, ImageView imgEmpty) {
        rv.setVisibility(View.GONE);
        imgEmpty.setVisibility(View.GONE);

        rv.setVisibility(size==0 ? View.GONE : View.VISIBLE);
        imgEmpty.setVisibility(size==0 ? View.VISIBLE : View.GONE);
    }
}