package com.demo.demoappbasic.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.demo.demoappbasic.DatabaseHandler;
import com.demo.demoappbasic.R;
import com.demo.demoappbasic.common.CoomonClasss;
import com.demo.demoappbasic.common.Memory;

/**
 * Created by Nishi on 4/13/2018.
 */

public class FirstFragment extends Fragment implements View.OnClickListener {

    private EditText et_city, et_country, et_state;
    private Button bt_add;
    public Context context;
    private Memory memory;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        context = getActivity();
        memory = new Memory(context);

// Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        et_city = (EditText) view.findViewById(R.id.et_city);
        et_state = (EditText) view.findViewById(R.id.et_state);
        et_country = (EditText) view.findViewById(R.id.et_country);

        bt_add = (Button) view.findViewById(R.id.bt_add);
        bt_add.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add:
                memory.hideKeyboard(context);
                if (verifyFillData()) {

                    insertDataDetails(et_city.getText().toString(), et_state.getText().toString(), et_country.getText().toString());


                }

                break;
        }
    }

    private void insertDataDetails(String city, String state, String country) {
        DatabaseHandler databaseHandler = new DatabaseHandler(context);
        SQLiteDatabase helper = databaseHandler.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHandler.A_CITY, city);
        contentValues.put(DatabaseHandler.A_STATE, state);
        contentValues.put(DatabaseHandler.A_COUNTRY, country);
        contentValues.put(DatabaseHandler.A_P_ID, memory.getIntValue(CoomonClasss.CurreentUseriD, 0));

        helper.insert(DatabaseHandler.TABLE_AREA, null, contentValues);

        SecondListFragment fragment2 = new SecondListFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment2);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private boolean verifyFillData() {


        if (et_city.getText().toString().equalsIgnoreCase("")) {
            memory.showToast(context, " Please Enter City ");
        } else if (et_state.getText().toString().equalsIgnoreCase("")) {
            memory.showToast(context, " Please Enter State ");
        } else if (et_country.getText().toString().equalsIgnoreCase("")) {
            memory.showToast(context, " Please Enter Country ");
        }

        return true;
    }
}
