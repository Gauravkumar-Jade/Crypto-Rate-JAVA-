package com.jit.cryptoratejava.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.jit.cryptoratejava.data.CryptoData;
import com.jit.cryptoratejava.repository.CryptoRepository;

public class CryptoViewModel extends ViewModel {

    private final CryptoRepository repository;

    public CryptoViewModel(CryptoRepository repository) {
        this.repository = repository;
    }


    public void getCryptoValue(){
        repository.getCryptoRate();
    }

    public LiveData<CryptoData> getCryptoPrice(){
        return repository.getCrypto();
    }










}

