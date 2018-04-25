package com.demo.demoappbasic.fragment;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.demo.demoappbasic.DatabaseHandler;
import com.demo.demoappbasic.R;
import com.demo.demoappbasic.common.CoomonClasss;
import com.demo.demoappbasic.common.Memory;
import com.demo.demoappbasic.model.DataArea;

import java.util.ArrayList;

/**
 * Created by Nishi on 4/13/2018.
 */

public class SecondListFragment extends ListFragment implements AdapterView.OnItemClickListener {
    public Context context;
    private Memory memory;
    public ArrayList<DataArea> dataAreas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        memory = new Memory(context);
        dataAreas = new ArrayList<DataArea>();
        View view = inflater.inflate(R.layout.fragment_second, container, false);

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

        ListViewAdapter listViewAdapter  = new ListViewAdapter(getActivity(),dataAreas);
        setListAdapter(listViewAdapter);
//        getListView().setOnItemClickListener(this);

    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
    }
}
