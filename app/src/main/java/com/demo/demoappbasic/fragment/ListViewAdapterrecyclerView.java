package com.demo.demoappbasic.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.demoappbasic.R;
import com.demo.demoappbasic.model.DataArea;

import java.util.List;

/**
 * Created by Nishi on 4/16/2018.
 */

class ListViewAdapterrecyclerView extends RecyclerView.Adapter<ListViewAdapterrecyclerView.MyViewHolder> {

    private Context context;
    private List<DataArea> dataAreas;


    public ListViewAdapterrecyclerView(Context context, List<DataArea> dataAreas) {
        this.context = context;
        this.dataAreas = dataAreas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.list_item, parent, false);

      //  context = parent.getContext();
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        int p = holder.getAdapterPosition();

        DataArea row_pos = dataAreas.get(p);

        holder.txt_city.setText(row_pos.getCity());
        holder.txt_state.setText(row_pos.getState());
        holder.txt_country.setText(row_pos.getCountry());
    }

    @Override
    public int getItemCount() {
        return dataAreas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_city, txt_state, txt_country;

        public MyViewHolder(View itemView) {
            super(itemView);


            txt_city = (TextView) itemView.findViewById(R.id.tv_city_data);
            txt_state = (TextView) itemView.findViewById(R.id.tv_state_data);
            txt_country = (TextView) itemView.findViewById(R.id.tv_country_data);

        }
    }
}
