package com.demo.demoappbasic.fragment;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.demo.demoappbasic.R;
import com.demo.demoappbasic.model.DataArea;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nishi on 4/16/2018.
 */

public class ListViewAdapter extends BaseAdapter {

    Context context;
    List<DataArea> rowItem;

    public ListViewAdapter(FragmentActivity context, ArrayList<DataArea> dataAreas) {
        this.context = context;
        this.rowItem = dataAreas;
    }

    @Override
    public int getCount() {
        return rowItem.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowItem.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_item, null);
        }
        TextView txt_city = (TextView) convertView.findViewById(R.id.tv_city_data);
        TextView txt_state = (TextView) convertView.findViewById(R.id.tv_state_data);
        TextView txt_country = (TextView) convertView.findViewById(R.id.tv_country_data);

        DataArea row_pos = rowItem.get(position);

        txt_city.setText(row_pos.getCity());
        txt_state.setText(row_pos.getState());
        txt_country.setText(row_pos.getCountry());

        return convertView;
    }
}
