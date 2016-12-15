package edu.upc.util;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by asierig on 15/12/2016.
 */
public class MyArrayAdapter extends ArrayAdapter<Message> {
    public MyArrayAdapter() {
        // ...
    }
    @Override
public View getView(int position, View convertView, ViewGroup parent) {
    return getItem(position).getView(getContext(), convertView, parent);
}

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getItemViewType();
    }

    @Override
    public int getViewTypeCount() {
        return 3; // Count of different layouts
    }
}