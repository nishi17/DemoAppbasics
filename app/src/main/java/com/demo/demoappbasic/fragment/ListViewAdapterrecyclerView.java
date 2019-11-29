package com.demo.demoappbasic.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.demoappbasic.R;
import com.demo.demoappbasic.RecyclerViewClickListener;
import com.demo.demoappbasic.model.DataArea;

import java.util.List;

/**
 * Created by Nishi on 4/16/2018.
 */

class ListViewAdapterrecyclerView extends RecyclerView.Adapter<ListViewAdapterrecyclerView.MyViewHolder> {

    private Context context;
    private List<DataArea> dataAreas;
    private RecyclerViewClickListener mListener;

    public ListViewAdapterrecyclerView(Context context, List<DataArea> dataAreas, RecyclerViewClickListener listener) {
        this.context = context;
        this.dataAreas = dataAreas;
        this.mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.list_item, parent, false);

        //  context = parent.getContext();
        return new MyViewHolder(itemView, mListener);
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

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RecyclerViewClickListener mListener;
        TextView txt_city, txt_state, txt_country;

        public MyViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            mListener = listener;


            txt_city = (TextView) itemView.findViewById(R.id.tv_city_data);
            txt_city.setOnClickListener(this);
            txt_state = (TextView) itemView.findViewById(R.id.tv_state_data);
            txt_country = (TextView) itemView.findViewById(R.id.tv_country_data);

        }


        @Override
        public void onClick(View v) {
            mListener.onClick(v, getAdapterPosition(), /*list.get(getAdapterPosition())*/null, /*list.size()*/0);

        }
    }


}
