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

/**
 * This is the general BaseAdapter, all adapters extend of this class.
 *
 * @param <T>
 */
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

    /**
     * To create the databinding call I use the follow reference:
     * Reference: https://stackoverflow.com/questions/43973490/how-to-do-android-data-binding-a-customadapter-inherited-from-baseadapter-for-sp
     *
     * @param position    defines the element index at list
     * @param convertView is the current view
     * @param parent      is the parent view
     * @return the current view with the information from the data object
     */
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


    /**
     * This method allows to define the layout resource address in the subclasses.
     *
     * @return resource address
     */
    protected abstract int getLayoutResourceAccordingViewType();

}
