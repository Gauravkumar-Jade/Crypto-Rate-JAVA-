
package com.jit.cryptoratejava.data;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@Entity
public class Datum {

    @PrimaryKey(autoGenerate = true)
    private Integer ids;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("symbol")
    @Expose
    private String symbol;

    @SerializedName("priceUsd")
    @Expose
    private String priceUsd;


    @SerializedName("explorer")
    @Expose
    private String explorer;

    public Integer getIds() {
        return ids;
    }

    public void setIds(Integer ids) {
        this.ids = ids;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public String getPriceUsd() {
        return priceUsd;
    }

    public String getExplorer() {
        return explorer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setPriceUsd(String priceUsd) {
        this.priceUsd = priceUsd;
    }

    public void setExplorer(String explorer) {
        this.explorer = explorer;
    }

    public static DiffUtil.ItemCallback<Datum> itemCallback =
            new DiffUtil.ItemCallback<Datum>() {
                @Override
                public boolean areItemsTheSame(@NonNull Datum oldItem, @NonNull Datum newItem) {
                    return oldItem.getPriceUsd().equals(newItem.getPriceUsd());
                }

                @Override
                public boolean areContentsTheSame(@NonNull Datum oldItem, @NonNull Datum newItem) {
                    return oldItem.getName().equals(newItem.getName());
                }
            };

}
