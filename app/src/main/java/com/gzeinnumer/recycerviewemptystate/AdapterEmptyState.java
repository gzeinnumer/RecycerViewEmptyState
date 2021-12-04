package com.gzeinnumer.recycerviewemptystate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gzeinnumer.recycerviewemptystate.databinding.ItemAdapterRvBinding;
import com.gzeinnumer.recycerviewemptystate.databinding.ItemEmptyBinding;

import java.util.ArrayList;

public class AdapterEmptyState extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<String> list;

    public AdapterEmptyState() {
        list = new ArrayList<>();
    }

    public MyOnClick onClick;

    public void setOnClick(MyOnClick onClick) {
        this.onClick = onClick;
    }

    interface MyOnClick {
        void myOnClick(int position);
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void addList(String data) {
        this.list.add(data);
        notifyItemChanged(list.size() - 1); // untuk dinamis recyclerview
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(ItemAdapterRvBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyHolder) holder).bindData(list.get(position), onClick);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class MyHolder extends RecyclerView.ViewHolder {
        ItemAdapterRvBinding binding;

        public MyHolder(ItemAdapterRvBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void bindData(String data, final MyOnClick onClick) {
            binding.text.setText(data);

            if (onClick != null) {
                binding.text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClick.myOnClick(getAdapterPosition());
                    }
                });
            }
        }
    }
}
