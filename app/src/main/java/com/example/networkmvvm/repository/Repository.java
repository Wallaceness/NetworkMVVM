package com.example.networkmvvm.repository;

import com.example.networkmvvm.retrofit.RetrofitInstance;
import com.example.networkmvvm.retrofit.ShibeService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class Repository {

    private Retrofit retrofit;

    private Repository() {
        retrofit = RetrofitInstance.getInstance();
    }

    private static class RepositoryHolder {
        private static final Repository INSTANCE = new Repository();
    }

    public static Repository getInstance() {
        return RepositoryHolder.INSTANCE;
    }

    public Call<List<String>> getShibes(int count) {
        return retrofit.create(ShibeService.class)
                .getShibes(
                        String.valueOf(count),
                        true,
                        true
                );
    }
}
