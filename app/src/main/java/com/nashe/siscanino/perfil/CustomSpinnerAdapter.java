package com.nashe.siscanino.perfil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.google.android.material.textview.MaterialTextView;
import com.nashe.siscanino.R;

@SuppressLint({"SetTextI18n","ViewHolder"})
public class CustomSpinnerAdapter extends BaseAdapter {

    private Context context;
    private int [] ids;
    private String[] texts;
    private LayoutInflater inflater;

    public CustomSpinnerAdapter(Context context, int[] ids, String[] texts) {
        this.context = context;
        this.ids = ids;
        this.texts = texts;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return ids.length;
    }

    @Override
    public Object getItem(int position) {
        return texts[position];
    }

    @Override
    public long getItemId(int position) {
        return ids[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_spinner,null);
        MaterialTextView id = convertView.findViewById(R.id.lblSpinner_id);
        MaterialTextView name = convertView.findViewById(R.id.lblSpinner_name);
        id.setText(ids[position] + "");
        name.setText(texts[position]);
        return convertView;
    }
}
