package com.example.gabrielmoro.baking_app.ui.base;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.gabrielmoro.baking_app.BR;

import java.util.List;

public abstract class GeneralBaseAdapter<T> extends BaseAdapter {

    private List<T> elements;
    private LayoutInflater inflater;

    public GeneralBaseAdapter(@NonNull List<T> aLstElements, LayoutInflater aInflater) {
        elements = aLstElements;
        inflater = aInflater;
    }

    public void onUpdateAllElements(@NonNull List<T> newList) {
        elements = newList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return elements.size();
    }

    @Override
    public T getItem(int position) {
        return elements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @SuppressWarnings("unchecked")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;

        if (convertView == null) {
            view = inflater.inflate(getLayoutResourceAccordingViewType(), parent, false);
        } else {
            view = convertView;
        }

        T item = this.elements.get(position);

        if (item != null) {
            ViewDataBinding dataBinding = DataBindingUtil.bind(view);
            if (dataBinding != null) {
                dataBinding.setVariable(BR.viewModel, item);
                dataBinding.executePendingBindings();
            }
        }
        return view;
    }


    protected abstract int getLayoutResourceAccordingViewType();

}
