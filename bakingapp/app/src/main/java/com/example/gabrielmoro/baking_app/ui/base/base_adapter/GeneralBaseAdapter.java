package com.example.gabrielmoro.baking_app.ui.base.base_adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.gabrielmoro.baking_app.R;

import java.util.List;

public class GeneralBaseAdapter<T> extends BaseAdapter {

    private List<T> elements;
    private LayoutInflater inflater;
    private ViewTypes viewType;
    private ViewContractBaseAdapter contract;

    public GeneralBaseAdapter(@NonNull List<T> aLstElements, ViewTypes aViewType, LayoutInflater aInflater, @NonNull ViewContractBaseAdapter aContract) {
        elements = aLstElements;
        inflater = aInflater;
        viewType = aViewType;
        contract = aContract;
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
            contract.bindView(item, view);
        }
        return view;
    }


    private int getLayoutResourceAccordingViewType() {
        switch (viewType) {
            case RECIPE_ITEM:
                return R.layout.layout_recipe_item;
            case STEP_ITEM:
                return R.layout.layout_step_item;
            case INGREDIENT_ITEM:
                return R.layout.layout_ingredient_item;
            case UNKNOW:
                return R.layout.unknow_layout_item;
            default:
                return R.layout.unknow_layout_item;
        }
    }

}
