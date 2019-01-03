package com.example.student.bluethoothactivity;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DeviceAdapter extends BaseAdapter {

    ArrayList<BluetoothDevice> list;
    Context context;
    int layout_id;
    LayoutInflater layoutInflater;

    public DeviceAdapter(Context context, int layout_id,ArrayList<BluetoothDevice> list) {
        this.list = list;
        this.context = context;
        this.layout_id = layout_id;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int pos = i;

        if(view == null) {
            view = layoutInflater.inflate(layout_id, viewGroup, false);
        }

        TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
        TextView tv_addr = (TextView) view.findViewById(R.id.tv_addr);

        tv_name.setText(list.get(pos).getName());
        tv_addr.setText(list.get(pos).getAddress());

        return view;
    }
}
