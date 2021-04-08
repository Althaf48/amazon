package com.example.antsecommerce1;

/**

 */
import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    private final String INTRO = "intro";
    private final String NAME = "name";
    private final String HOBBY = "hobby";
    private final String SELLER = "Seller";
    private final String CUSTOMER = "Customer";
    private SharedPreferences app_prefs;
    private Context context;

    public PreferenceHelper(Context context) {
        app_prefs = context.getSharedPreferences("shared",
                Context.MODE_PRIVATE);
        this.context = context;
    }

    public void putIsLogin(boolean loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putBoolean(INTRO, loginorout);
        edit.commit();
    }
    public boolean getIsLogin() {
        return app_prefs.getBoolean(INTRO, false);
    }

    public void putName(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(NAME, loginorout);
        edit.commit();
    }
    public String getName() {
        return app_prefs.getString(NAME, "");
    }

    public void putHobby(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(HOBBY, loginorout);
        edit.commit();
    }

    public void putSeller(String seller) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(SELLER, seller);
        edit.remove(CUSTOMER);
        edit.apply();
    }

    public void putCustomer(String customer) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(CUSTOMER, customer);
        edit.remove(SELLER);
        edit.apply();
    }



    public String getSELLER() {
        return app_prefs.getString("Seller", "");
    }

    public String getCUSTOMER() {
        return app_prefs.getString("Customer", "");
    }

    public String getHobby() {
        return app_prefs.getString(HOBBY, "");
    }

 }
