package com.jit.cryptoratejava;

import android.app.Application;

import com.jit.cryptoratejava.database.CryptoDatabase;
import com.jit.cryptoratejava.network.CryptoApi;
import com.jit.cryptoratejava.repository.CryptoRepository;

public class CryptoApplication extends Application {
    CryptoRepository repository;

    @Override
    public void onCreate() {
        super.onCreate();

        onInitialized();
    }

    private void onInitialized() {

        repository = new CryptoRepository(CryptoApi.getApiInterface(),this
                ,getApplicationContext());
    }
}
