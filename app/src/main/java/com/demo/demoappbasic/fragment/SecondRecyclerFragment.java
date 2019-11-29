package com.demo.demoappbasic.fragment;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.demoappbasic.DatabaseHandler;
import com.demo.demoappbasic.R;
import com.demo.demoappbasic.RecyclerViewClickListener;
import com.demo.demoappbasic.common.CoomonClasss;
import com.demo.demoappbasic.common.Memory;
import com.demo.demoappbasic.model.DataArea;

import java.util.ArrayList;

/**
 * Created by Nishi on 4/16/2018.
 */

public class SecondRecyclerFragment extends Fragment {


    public Context context;
    private Memory memory;
    public ArrayList<DataArea> dataAreas;
    private TextView txt_nodata;
    private RecyclerView recyclerView;
    private RecyclerViewClickListener listener;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        memory = new Memory(context);
        dataAreas = new ArrayList<DataArea>();
        View view = inflater.inflate(R.layout.fragment_second_recycler, container, false);
        txt_nodata = (TextView) view.findViewById(R.id.txt_nodata);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_data);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);

        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 0);
        recyclerView.setLayoutManager(layoutManager);
        retriveDATA();

        return view;
    }


    private void retriveDATA() {

        DatabaseHandler databaseHandler = new DatabaseHandler(context);

        SQLiteDatabase database = databaseHandler.getReadableDatabase();

        int current_user_id = memory.getIntValue(CoomonClasss.CurreentUseriD, 0);

        String[] Colums = {DatabaseHandler.A_ID, DatabaseHandler.A_CITY, DatabaseHandler.A_STATE, DatabaseHandler.A_COUNTRY};


        String whereClause = DatabaseHandler.A_P_ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(current_user_id)};

        Cursor cursor = database.query(DatabaseHandler.TABLE_AREA, Colums, whereClause, whereArgs, null, null, null);
        DataArea dataArea;

        if (cursor.getCount() >= 0) {
            if (cursor.moveToFirst()) {
                do {

                    dataArea = new DataArea();
                    String city = cursor.getString(cursor.getColumnIndex(DatabaseHandler.A_CITY));
                    String state = cursor.getString(cursor.getColumnIndex(DatabaseHandler.A_STATE));
                    String country = cursor.getString(cursor.getColumnIndex(DatabaseHandler.A_COUNTRY));
                    dataArea.setCity(city);
                    dataArea.setState(state);
                    dataArea.setCountry(country);
                    dataAreas.add(dataArea);


                } while (cursor.moveToNext());

            } else {

            }

        } else {

        }
        if (dataAreas.size() > 0) {
            txt_nodata.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.GONE);
        }


        listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position, Object o, int arraySize) {

                Toast.makeText(context, "Click on " + position + " position ", Toast.LENGTH_SHORT).show();
            }
        };


        ListViewAdapterrecyclerView listViewAdapter = new ListViewAdapterrecyclerView(getActivity(), dataAreas, listener);

        recyclerView.setAdapter(listViewAdapter);
    }


}
