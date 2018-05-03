package com.demo.demoappbasic.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.demo.demoappbasic.DatabaseHandler;
import com.demo.demoappbasic.R;
import com.demo.demoappbasic.common.CoomonClasss;
import com.demo.demoappbasic.common.Memory;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText et_email, et_pass;
    private Button bt_login;
    private TextView tv_register;
    private Memory memory;

    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = Login.this;
        memory = new Memory(context);
        init();
    }

    private void init() {

        et_email = (EditText) findViewById(R.id.et_email);
        et_pass = (EditText) findViewById(R.id.et_pass);


        bt_login = (Button) findViewById(R.id.bt_Login);

        tv_register = (TextView) findViewById(R.id.tv_register);

        bt_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register:

                memory.hideKeyboard(context);
                Intent i_reg = new Intent(context, Registration.class);
                startActivity(i_reg);

                break;

            case R.id.bt_Login:
                memory.hideKeyboard(context);
                checkLogin();
                break;

        }
    }

    private void checkLogin() {

        if (verifyDetails()) {

            retriveProfileDetails(et_email.getText().toString(), et_pass.getText().toString());


        }

    }

    private void retriveProfileDetails(String etemail, String etpass) {

        DatabaseHandler databaseHandler = new DatabaseHandler(context);

        SQLiteDatabase database = databaseHandler.getReadableDatabase();

        String[] Colums = {DatabaseHandler.P_ID, DatabaseHandler.P_EMAIL, DatabaseHandler.P_PASSWPORD};

        Cursor cursor = database.query(DatabaseHandler.TABLE_PROFILE, Colums, null, null, null, null, null);

        if (cursor.getCount() >= 0) {
            if (cursor.moveToFirst()) {
                do {

                    String email = cursor.getString(cursor.getColumnIndex(DatabaseHandler.P_EMAIL));
                    String password = cursor.getString(cursor.getColumnIndex(DatabaseHandler.P_PASSWPORD));

                    if (email.equalsIgnoreCase(etemail) && password.equalsIgnoreCase(etpass)) {

                        int currentUser_id = cursor.getInt(cursor.getColumnIndex(DatabaseHandler.P_ID));

                        memory.setIntValue(CoomonClasss.CurreentUseriD, currentUser_id);

                        finish();
                        Intent i_main = new Intent(context, NavigationActivity.class);
                        startActivity(i_main);
                    } else {

                        if (email.equalsIgnoreCase(etemail)) {

                            if (password.equalsIgnoreCase(etpass)) {

                            } else {
                                memory.showToast(context, "Please Enter Valid Password");
                            }


                        } else {
                            memory.showToast(context, "Please Enter Valid Id");

                        }
                    }

                } while (cursor.moveToNext());

            } else {
                memory.showToast(context, "Please Register");
            }

        } else {
            memory.showToast(context, "Please No User");
        }


    }

    private boolean verifyDetails() {

        if (et_email.getText().toString().equalsIgnoreCase("")) {
            memory.showToast(context, " Please Enter Email ");
        } else if (!memory.isValidEmail(et_email.getText().toString())) {
            memory.showToast(context, " Please Valid Email ");
        } else if (et_pass.getText().toString().equalsIgnoreCase("")) {
            memory.showToast(context, " Please Enter Password ");
        }

        return true;
    }
}
