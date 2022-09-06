package com.jit.cryptoratejava.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;

public class NetworkUtils {

    public boolean isInternetAvailable(Context context){
        ConnectivityManager manager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return manager.getActiveNetwork() != null && manager.getNetworkCapabilities(manager.getActiveNetwork()) != null;
            } else {
                return manager.getActiveNetworkInfo() != null && manager.getActiveNetworkInfo().isConnectedOrConnecting();
            }
        }
    }

