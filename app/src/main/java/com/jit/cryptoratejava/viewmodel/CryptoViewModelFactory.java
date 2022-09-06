package com.jit.cryptoratejava.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.jit.cryptoratejava.repository.CryptoRepository;

public class CryptoViewModelFactory implements ViewModelProvider.Factory {

    private final CryptoRepository repository;

    public CryptoViewModelFactory(CryptoRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CryptoViewModel.class)){
            return (T) new CryptoViewModel(repository);
        }
        throw new IllegalArgumentException("No View Model");
    }
}
