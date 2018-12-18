package com.example.gabrielmoro.baking_app.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gabrielmoro.baking_app.BR;

import java.util.List;

/**
 * This is the general BaseAdapter, all adapters extend of this class.
 *
 * @param <T>
 */
public abstract class GeneralBaseAdapter<T> extends RecyclerView.Adapter<GeneralViewHolder> {

    private List<T> elements;

    public GeneralBaseAdapter(@NonNull List<T> aLstElements) {
        elements = aLstElements;
    }

    public void onUpdateAllElements(@NonNull List<T> newList) {
        elements = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GeneralViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), getLayoutResourceId(), viewGroup, false).getRoot();
        return new GeneralViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GeneralViewHolder generalViewHolder, int i) {
        ViewDataBinding binding = DataBindingUtil.getBinding(generalViewHolder.itemView);
        if (binding != null)
            binding.setVariable(BR.viewModel, elements.get(i));
    }


    public abstract int getLayoutResourceId();

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }


}
