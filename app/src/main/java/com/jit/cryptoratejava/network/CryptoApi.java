package com.jit.cryptoratejava.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CryptoApi {

    private static final String BASE_URL = "https://api.coincap.io/";

    public static CryptoApiService getApiInterface(){

        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        CryptoApiService apiService = retrofit.create(CryptoApiService.class);
        return apiService;
    }
}
