package com.jit.cryptoratejava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.jit.cryptoratejava.adapter.CryptoAdapter;
import com.jit.cryptoratejava.data.Datum;
import com.jit.cryptoratejava.databinding.ActivityMainBinding;
import com.jit.cryptoratejava.repository.CryptoRepository;
import com.jit.cryptoratejava.viewmodel.CryptoViewModel;
import com.jit.cryptoratejava.viewmodel.CryptoViewModelFactory;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private CryptoViewModel viewModel;
    private ActivityMainBinding binding;
    private CryptoAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

         CryptoApplication application = (CryptoApplication)getApplication();
         CryptoRepository repository = application.repository;

        viewModel = new ViewModelProvider(this,new CryptoViewModelFactory(repository))
                .get(CryptoViewModel.class);


        onInitialize();

        binding.fab.setOnClickListener(view -> onInitialize());


    }

    private void onInitialize() {

        viewModel.getCryptoValue();

        adapter = new CryptoAdapter(Datum.itemCallback);
        binding.recylerView.setAdapter(adapter);

        viewModel.getCryptoPrice().observe(this, cryptoData -> {
            adapter.submitList(cryptoData.getData());

            Date date = new Date(cryptoData.getTimestamp());

            binding.titleText.setText(date.toString());

        });
    }
}