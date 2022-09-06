
package com.jit.cryptoratejava.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class CryptoData {

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("timestamp")
    @Expose
    private Long timestamp;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

}
