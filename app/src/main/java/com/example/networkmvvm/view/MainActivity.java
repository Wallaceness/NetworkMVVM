package com.example.networkmvvm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.networkmvvm.R;
import com.example.networkmvvm.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private RecyclerView tvDisplay;
    private Button btnLoad;
    private EditText etCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLoad = findViewById(R.id.btnLoad);
        etCount = findViewById(R.id.etCount);
        etCount.setText("5");
        tvDisplay = findViewById(R.id.displayData);
        viewModel = new ViewModelProvider.NewInstanceFactory().create(MainViewModel.class);

        setupObservers();
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String count = etCount.getText().toString();
                viewModel.fetchShibeData(Integer.parseInt(count));
            }
        });
    }

    private void setupObservers() {
        viewModel.getShibesLiveData().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                RecycleAdapter adapter;
                if (strings != null) {
                    if (strings.isEmpty())
                        adapter = new RecycleAdapter(new ArrayList<String>(Arrays.asList("EMPTY LIST")), MainActivity.this);
                    else
                        adapter = new RecycleAdapter((ArrayList<String>)strings, MainActivity.this);
                    tvDisplay.setAdapter(adapter);
                    tvDisplay.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                }
            }
        });

        viewModel.getErrorLiveData().observe(this, isError -> {
            if (!isError.isEmpty())
                Toast.makeText(this, isError, Toast.LENGTH_SHORT).show();
        });
    }
}
