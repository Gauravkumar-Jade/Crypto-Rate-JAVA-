package com.jit.cryptoratejava.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.jit.cryptoratejava.data.Datum;
import com.jit.cryptoratejava.databinding.CustomLayoutBinding;

public class CryptoAdapter extends ListAdapter<Datum, CryptoAdapter.CryptoHolder> {

    public CryptoAdapter(@NonNull DiffUtil.ItemCallback<Datum> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public CryptoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomLayoutBinding binding = CustomLayoutBinding.inflate(inflater,parent,false);
        return new CryptoHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CryptoHolder holder, int position) {
        Datum data = getItem(position);
        holder.bind(data);
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(data.getExplorer()));
            Context context = holder.itemView.getContext();
            context.startActivity(intent);

            Toast.makeText(context, data.getName(), Toast.LENGTH_SHORT).show();
        });
    }

    static class CryptoHolder extends RecyclerView.ViewHolder{

       CustomLayoutBinding binding;

        public CryptoHolder(@NonNull CustomLayoutBinding bindings) {
            super(bindings.getRoot());

            this.binding = bindings;
        }

        @SuppressLint("SetTextI18n")
        public void bind(Datum datum){

            binding.nameText.setText(datum.getName());
            binding.codeText.setText(datum.getSymbol());
            @SuppressLint("DefaultLocale")
            String price = String.format("%.3f",Double.parseDouble(datum.getPriceUsd()));
            binding.rateText.setText("$ "+price);
        }
    }
}
