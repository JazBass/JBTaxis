package com.jazbass.jbtaxis;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryAdapter extends BaseAdapter {

    Activity activity;
    ArrayList<String[]> arrayList;

    public HistoryAdapter(Activity activity, ArrayList<String[]> arrayList) {
        this.activity = activity;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        @SuppressLint({"ViewHolder", "InflateParams"}) View view1 = layoutInflater.inflate(R.layout.list_element, null, true);
        TextView txtCarRegistration, txtName, txtSurname, txtDate;
        txtCarRegistration = view1.findViewById(R.id.txtCarResgistration);
        txtName =  view1.findViewById(R.id.txtName);
        txtSurname =  view1.findViewById(R.id.txtSurname);
        txtDate =  view1.findViewById(R.id.txtDate);

        txtCarRegistration.setText(arrayList.get(position)[1]);
        txtDate.setText(arrayList.get(position)[2]);
        txtName.setText(arrayList.get(position)[3]);
        txtSurname.setText(arrayList.get(position)[4]);
        return null;
    }
}
