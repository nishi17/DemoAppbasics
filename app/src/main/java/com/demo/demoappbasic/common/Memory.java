package com.demo.demoappbasic.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * Created by Nishi on 4/13/2018.
 */

public class Memory {

    public Context context;


    public Memory(Context context) {
        this.context = context;
    }

    public void hideKeyboard(Context context) {
        View v = new View(context);
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

    }

    public void showToast(Context context, String msg) {

        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }

    public final boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public void setIntValue(String key, int valuue) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(CoomonClasss.LoginPrefrence, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(key, valuue);
        edit.commit();

    }

    public int getIntValue(String key, int defValue) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(CoomonClasss.LoginPrefrence, Context.MODE_PRIVATE);

        int result;
        result = sharedPreferences.getInt(key, defValue);
        return result;
    }


}
