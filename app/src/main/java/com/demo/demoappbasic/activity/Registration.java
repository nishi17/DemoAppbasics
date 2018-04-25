package com.demo.demoappbasic.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.demo.demoappbasic.DatabaseHandler;
import com.demo.demoappbasic.R;
import com.demo.demoappbasic.common.Memory;

public class Registration extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_toolbar_title;

    private EditText et_firstname, et_lastname, et_email, et_password;
    private Button bt_register;
    public Context context;
    private Memory memory;
    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registration);

        context = Registration.this;
        memory = new Memory(context);

        databaseHandler = new DatabaseHandler(context);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        tv_toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        tv_toolbar_title.setText("Registration");

        init();

    }

    private void init() {

        et_firstname = (EditText) findViewById(R.id.et_firstname);
        et_lastname = (EditText) findViewById(R.id.et_lastname);
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);

        bt_register = (Button) findViewById(R.id.bt_register);
        bt_register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bt_register:
                memory.hideKeyboard(context);
                registration();

                break;
        }


    }

    private void registration() {

        if (isVerifyDetails()) {

            insertDataProfile(et_firstname.getText().toString(), et_lastname.getText().toString(),
                    et_email.getText().toString(), et_password.getText().toString());

        }

    }


    private boolean isVerifyDetails() {

        if (et_firstname.getText().toString().equalsIgnoreCase("")) {
            memory.showToast(context, " Please Enter FirstName ");
        } else if (et_lastname.getText().toString().equalsIgnoreCase("")) {
            memory.showToast(context, " Please Enter LastName ");
        } else if (et_email.getText().toString().equalsIgnoreCase("")) {
            memory.showToast(context, " Please Enter Email ");
        } else if (!memory.isValidEmail(et_email.getText().toString())) {
            memory.showToast(context, " Please Valid Email ");
        } else if (et_password.getText().toString().equalsIgnoreCase("")) {
            memory.showToast(context, " Please Enter Password ");
        }


        return true;
    }


    private void insertDataProfile(String fn, String ln, String email, String password) {


        if (!isEmailExists(email)) {
            SQLiteDatabase helper = databaseHandler.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseHandler.P_FIRSTNAME, fn);
            contentValues.put(DatabaseHandler.P_LASTNAME, ln);
            contentValues.put(DatabaseHandler.P_EMAIL, email);
            contentValues.put(DatabaseHandler.P_PASSWPORD, password);

            helper.insert(DatabaseHandler.TABLE_PROFILE, null, contentValues);

            finish();
            Intent i_login = new Intent(context, Login.class);
            startActivity(i_login);


        } else {
            memory.showToast(context, "Email already Exist !!");
        }


    }

    private boolean isEmailExists(String email) {

        String query = " SELECT " + DatabaseHandler.P_EMAIL + " FROM " + DatabaseHandler.TABLE_PROFILE + " WHERE " +
                DatabaseHandler.P_EMAIL + " = '" + email + "';";

        SQLiteDatabase helper = databaseHandler.getReadableDatabase();

        Cursor cursor = helper.rawQuery(query, null);

        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        } else {
            cursor.close();
            return true;
        }

    }


}
