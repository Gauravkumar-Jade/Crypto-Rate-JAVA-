package com.jit.cryptoratejava.network;


import com.jit.cryptoratejava.data.CryptoData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoApiService {

    @GET("v2/assets")
    Call<CryptoData> getCryptoData();
}
