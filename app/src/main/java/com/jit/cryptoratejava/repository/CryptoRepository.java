package com.jit.cryptoratejava.repository;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.jit.cryptoratejava.data.CryptoData;
import com.jit.cryptoratejava.data.Datum;
import com.jit.cryptoratejava.database.CryptoDao;
import com.jit.cryptoratejava.database.CryptoDatabase;
import com.jit.cryptoratejava.database.TimeStampPref;
import com.jit.cryptoratejava.network.CryptoApiService;
import com.jit.cryptoratejava.utils.NetworkUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CryptoRepository {

    private final CryptoApiService apiService;
    private final Context context;
    private final CryptoDao dao;
    private List<Datum> data;

    public CryptoRepository(CryptoApiService apiService, Application application, Context context) {
        this.apiService = apiService;
        this.context = context;
        CryptoDatabase database = CryptoDatabase.getDatabase(application);
        dao = database.cryptoDao();
    }

    private MutableLiveData<CryptoData> _crypto;

    public LiveData<CryptoData> getCrypto(){
        if(_crypto == null){
            _crypto = new MutableLiveData<>();
        }
        return _crypto;
    }


    public void getCryptoRate(){

        Thread thread = new Thread(){
            @Override
            public void run() {

                if(new NetworkUtils().isInternetAvailable(context)){
                    Call<CryptoData> result = apiService.getCryptoData();

                    result.enqueue(new Callback<CryptoData>() {
                        @Override
                        public void onResponse(Call<CryptoData> call, Response<CryptoData> response) {

                            if(response.isSuccessful()){
                                CryptoDatabase.databaseWriteExecutor.execute(() -> {
                                    dao.deleteData();
                                    dao.insertData(response.body().getData());
                                });
                                new TimeStampPref(context).onSave(response.body().getTimestamp());
                                _crypto.postValue(response.body());
                            }
                        }

                        @Override
                        public void onFailure(Call<CryptoData> call, Throwable t) {

                        }
                    });
                }else {

                        Long timeStamp = new TimeStampPref(context).getTime();
                        data = dao.getCrypto();

                        CryptoData cryptoData = new CryptoData();
                        cryptoData.setData(data);
                        cryptoData.setTimestamp(timeStamp);

                        _crypto.postValue(cryptoData);

                }


            }
        };
        thread.start();
    }

}
