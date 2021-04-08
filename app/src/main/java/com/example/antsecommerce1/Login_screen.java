package com.example.antsecommerce1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.antsecommerce1.Interface.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_screen extends AppCompatActivity {

    CardView signup_cv;
    TextView forgotpass_tv,loginbtn;
   // private PreferenceHelper preferenceHelper;
    String usertypeseller1,usertypeuser1;
    EditText loginmobileno,loginpassword;
    RegisterInterface registerInterface;
    LoginResponseBody loginResponseBody;
    LoginResponseBody2 loginResponseBody2;
    String usertypeseller2 = "seller";
    String usertypeuser2 = "customer";
    AlertDialog alertDialog;
    String MobilePattern = "[0-9]{10}";
    public static final String PREFS_NAME = "MyLoginPrefsFile";
    public ProgressBar progressBar;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        getSupportActionBar().hide();
        registerInterface= ApiClient.getClient().create(RegisterInterface.class);

        usertypeseller1 = getIntent().getExtras().getString("seller");
        usertypeuser1 = getIntent().getExtras().getString("customer");

        signup_cv = findViewById(R.id.signupcard);
        forgotpass_tv = findViewById(R.id.forgotpass);

        loginmobileno = findViewById(R.id.loginmobile_ed);
        loginpassword = findViewById(R.id.loginpassword_ed);
        loginbtn = findViewById(R.id.login_btn);

        progressBar = (ProgressBar)findViewById(R.id.prg3);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(loginpassword.getText().toString().trim().length()==0){
                    loginpassword.setError("Password is not entered");
                    loginpassword.requestFocus();
                }
                if(loginmobileno.getText().toString().trim().length()==0){
                    loginmobileno.setError("phone number is not entered");
                    loginmobileno.requestFocus();
                }


                else {
                    loginme();
                    progressBar.setVisibility(View.VISIBLE);
                    setTitle("Loading...");
                }

            }
        });






      /*  if(usertypeuser.equals("Seller")){

            Toast.makeText(Login_screen.this, "seller", Toast.LENGTH_SHORT).show();

        }else {

            Toast.makeText(Login_screen.this, "notcustomer", Toast.LENGTH_SHORT).show();

        }*/

        if((usertypeseller1 == ("Seller"))||(usertypeuser1 == getIntent().getExtras().getString("customer"))){

            Toast.makeText(Login_screen.this, usertypeseller1, Toast.LENGTH_SHORT).show();


            signup_cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Login_screen.this,Signup_screen.class);
                    i.putExtra("seller1", usertypeseller1);
                    // i.putExtra("customer1", usertypeuser);
                    startActivity(i);
                }
            });

        }else {

            signup_cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Login_screen.this,Signup_screen.class);
                    i.putExtra("seller2", usertypeuser1);
                    // i.putExtra("customer1", usertypeuser);
                    startActivity(i);
                }
            });

            Toast.makeText(Login_screen.this, usertypeuser1, Toast.LENGTH_SHORT).show();

        }


        if((usertypeseller1 == ("Seller"))||(usertypeuser1 == getIntent().getExtras().getString("customer"))){

            Toast.makeText(Login_screen.this, usertypeseller1, Toast.LENGTH_SHORT).show();


            forgotpass_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Login_screen.this,Forgotpw_screen.class);
                    i.putExtra("seller1", usertypeseller1);
                    // i.putExtra("customer1", usertypeuser);
                    startActivity(i);
                }
            });

        }else {

            forgotpass_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Login_screen.this,Forgotpw_screen.class);
                    i.putExtra("seller2", usertypeuser1);
                    // i.putExtra("customer1", usertypeuser);
                    startActivity(i);
                }
            });

            Toast.makeText(Login_screen.this, usertypeuser1, Toast.LENGTH_SHORT).show();

        }


        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
//Get "hasLoggedIn" value. If the value doesn't exist yet false is returned
        boolean hasLoggedIn = settings.getBoolean("hasLoggedIn", false);
        String username = settings.getString("usernumber",null);
        String password = settings.getString("password",null);
       // String usertypeg = settings.getString("usertype1",null);
        if((hasLoggedIn)) {

            loginmobileno.setText(username);
            loginpassword.setText(password);
            /*usertypeseller1.equals(usertypeg);
            usertypeuser1.equals(usertypeg);*/
            loginme();

        }
        else
        {

            Toast.makeText(Login_screen.this,"please login",Toast.LENGTH_SHORT).show(); // Show Login Activity
        }


      //  Log.d("seller",usertypeseller);

//        Log.d("seller",usertypeseller);
//        Log.d("customer",usertypeuser);
       // Log.d("date",date);
       // Toast.makeText(Login_screen.this, usertypeseller, Toast.LENGTH_SHORT).show();
        //Toast.makeText(Login_screen.this, usertypeseller, Toast.LENGTH_SHORT).show();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET}
                        ,10);
            }
            return;
        }







    }


    private void loginme() {

        final String mobilenoed = loginmobileno.getText().toString();
        final String passworded = loginpassword.getText().toString();
        // final String states = spinner_val_edt_det.getSelectedItem().toString();



        /*Log.e("date",name);
        Log.e("date",mobileno);
        Log.e("date",email);
        Log.e("date",password);
        Log.e("date",date);
        Log.e("date",usertypeuser);*/
//        Log.e("date",usertypeuser);


        if((usertypeseller1 == ("Seller"))||(usertypeuser1 == getIntent().getExtras().getString("customer"))){

            Toast.makeText(Login_screen.this,usertypeseller1, Toast.LENGTH_SHORT).show();


            LoginRequestbody loginRequestbody = new LoginRequestbody();
            loginRequestbody.setUserName(mobilenoed);
            loginRequestbody.setPassword(passworded);
            loginRequestbody.setUserType(usertypeseller1);


            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0); // 0 - for private mode
            SharedPreferences.Editor editor = settings.edit();

            //Set "hasLoggedIn" to true
            editor.putBoolean("hasLoggedIn", true);
            editor.putString("usernumber",mobilenoed);
            editor.putString("password",passworded);
           // editor.remove(usertypeuser1);
           // editor.putString("usertype1",usertypeseller1);
            // Commit the edits!
            editor.apply();

            Call<LoginResponseBody2> call = registerInterface.getLogin(loginRequestbody);
            call.enqueue(new Callback<LoginResponseBody2>() {
                @Override
                public void onResponse(Call<LoginResponseBody2> call, Response<LoginResponseBody2> response) {

                  /*  loginResponseBody = response.body();
                    final String named =   loginResponseBody.getName();
                    final String mobiled = loginResponseBody.getPhonenumber();
                    final String emaild = loginResponseBody.getEmailid();*/

                   // Log.d("name",named);
              /*  Log.e("date",name);
                Log.e("date",mobileno);
                Log.e("date",email);
                Log.e("date",password);
                Log.e("date",date);
                Log.e("date",usertypeseller);*/
                    //  String message = signupresponsebodymsg.getMessage();
                    //  Toast.makeText(Signup_screen.this, usertypeseller, Toast.LENGTH_SHORT).show();
                    //  Log.d("status",message);

//                String message2 = signupresponsebodymsg.getMessage();
                    //  Toast.makeText(Signup_screen.this, message2, Toast.LENGTH_SHORT).show();

                    if (response.isSuccessful()) {
//                    String message = signupresponsebodymsg.getMessage();


                        loginResponseBody2 = response.body();
                        String status = loginResponseBody2.getMessage();
                        LoginResponseBody status2 = loginResponseBody2.getData();

                        Integer status3 = status2.getSellerId();



                        if(response.code() == 200) {


                            if (status.equalsIgnoreCase("Seller logged in Success fully")) {

                                if (status2.getUserType().equalsIgnoreCase("SELLER")) {

                                    Intent i = new Intent(Login_screen.this, Homepageseller.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    i.putExtra("name",status2.getName());
                                    i.putExtra("mobile",status2.getPhonenumber());
                                    i.putExtra("email",status2.getEmailid());
                                    i.putExtra("shopname",status2.getCompanyName());
                                    i.putExtra("sellerimage",status2.getSellerImage());
                                    i.putExtra("sellerid",status3);
                                    startActivity(i);

                                    progressBar.setVisibility(View.GONE);
                                    setTitle("Done");
                                    Toast.makeText(Login_screen.this, status2.getName(), Toast.LENGTH_SHORT).show();
                                } else {
                              /*  Intent i = new Intent(Verifyaccount_screen.this,Login_screen.class);
                                i.putExtra("Customer",usertypeuser);
                                startActivity(i);*/
                                    Toast.makeText(Login_screen.this, "check seller login", Toast.LENGTH_SHORT).show();
                                }


                                // Toast.makeText(Verifyaccount_screen.this, status, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Login_screen.this, status, Toast.LENGTH_SHORT).show();

                            }
                        }
                        // Do awesome stuff
                        // signupresponsebodymsg = response.body();
                        // String message1 = signupresponsebodymsg.getMessage();
                        //String status = signupresponsebodymsg.getStatus();

                    } else
                    if (response.code() == 401) {

                       // Toast.makeText(Login_screen.this, "Invalid password", Toast.LENGTH_SHORT).show();
                    alertDialog = new AlertDialog.Builder(Login_screen.this).create();
                    alertDialog.setTitle("ANTS App");
                    alertDialog.setMessage("Invalid password");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();

                                }
                            });
                    alertDialog.show();
                        progressBar.setVisibility(View.GONE);
                        setTitle("Done");
                        // Handle unauthorized
                        // Toast.makeText(RegisterScreen.this,"User already registered with this Phone number",Toast.LENGTH_SHORT).show();
                    } else {
                        // Handle other responses
                    }
                    if (response.code() == 404) {

                        Toast.makeText(Login_screen.this, "Invalid username", Toast.LENGTH_SHORT).show();
                    alertDialog = new AlertDialog.Builder(Login_screen.this).create();
                    alertDialog.setTitle("ANTS App");
                    alertDialog.setMessage("Invalid username");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                        progressBar.setVisibility(View.GONE);
                        setTitle("Done");
                        // Handle unauthorized
                        // Toast.makeText(RegisterScreen.this,"User already registered with this Phone number",Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void onFailure(Call<LoginResponseBody2> call, Throwable t) {
                    Log.i("sendingfail", "Returned empty response");
                    progressBar.setVisibility(View.GONE);
                    setTitle("Done");
                    Toast.makeText(Login_screen.this,"connection error",Toast.LENGTH_LONG).show();

                }
            });

        }else
            {

            LoginRequestbody loginRequestbody = new LoginRequestbody();
            loginRequestbody.setUserName(mobilenoed);
            loginRequestbody.setPassword(passworded);
            loginRequestbody.setUserType(usertypeuser1);

            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0); // 0 - for private mode
            SharedPreferences.Editor editor = settings.edit();

            //Set "hasLoggedIn" to true
            editor.putBoolean("hasLoggedIn", true);
            editor.putString("usernumber",mobilenoed);
            editor.putString("password",passworded);
           // editor.remove(usertypeseller1);
           // editor.putString("usertype1",usertypeuser1);
            // Commit the edits!
            editor.apply();

            Toast.makeText(Login_screen.this, usertypeuser1, Toast.LENGTH_SHORT).show();

            Call<LoginResponseBody2> call = registerInterface.getLogin(loginRequestbody);
            call.enqueue(new Callback<LoginResponseBody2>() {
                @Override
                public void onResponse(Call<LoginResponseBody2> call, Response<LoginResponseBody2> response) {

                /*Log.e("date",name);
                Log.e("date",mobileno);
                Log.e("date",email);
                Log.e("date",password);
                Log.e("date",date);
                Log.e("date",usertypeseller);*/
                    //  String message = signupresponsebodymsg.getMessage();
                    //  Toast.makeText(Signup_screen.this, usertypeseller, Toast.LENGTH_SHORT).show();
                    //  Log.d("status",message);

//                String message2 = signupresponsebodymsg.getMessage();
                    //  Toast.makeText(Signup_screen.this, message2, Toast.LENGTH_SHORT).show();

                    if (response.isSuccessful()) {
//                    String message = signupresponsebodymsg.getMessage();

                        loginResponseBody2 = response.body();
                        String status = loginResponseBody2.getMessage();
                        LoginResponseBody status2 = loginResponseBody2.getData();


                        Integer status3 = status2.getSellerId();

                        if(response.code() == 200) {

                            if (status.equalsIgnoreCase("Customer logged in Success fully")) {

                                if (status2.getUserType().equalsIgnoreCase("CUSTOMER")) {

                                    Intent i = new Intent(Login_screen.this, Homepage_Customer.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    i.putExtra("name",status2.getName());
                                    i.putExtra("mobile",status2.getPhonenumber());
                                    i.putExtra("email",status2.getEmailid());
                                    i.putExtra("sellerid",status3);
                                    startActivity(i);
                                    progressBar.setVisibility(View.GONE);
                                    setTitle("Done");
                                    Toast.makeText(Login_screen.this, status2.getName(), Toast.LENGTH_SHORT).show();
                                } else {
                              /*  Intent i = new Intent(Verifyaccount_screen.this,Login_screen.class);
                                i.putExtra("Customer",usertypeuser);
                                startActivity(i);*/
                                    Toast.makeText(Login_screen.this, "check seller login", Toast.LENGTH_SHORT).show();
                                }


                                // Toast.makeText(Verifyaccount_screen.this, status, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Login_screen.this, status, Toast.LENGTH_SHORT).show();

                            }
                        }else{
                            Toast.makeText(Login_screen.this, "check", Toast.LENGTH_SHORT).show();

                        }
                        // Do awesome stuff
                        // signupresponsebodymsg = response.body();
                        // String message1 = signupresponsebodymsg.getMessage();
                        //String status = signupresponsebodymsg.getStatus();

                    } else
                        if (response.code() == 401) {

                        //Toast.makeText(Login_screen.this, "Invalid password", Toast.LENGTH_SHORT).show();
                    alertDialog = new AlertDialog.Builder(Login_screen.this).create();
                    alertDialog.setTitle("ANTS App");
                    alertDialog.setMessage("Invalid password");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                        // Handle unauthorized
                            progressBar.setVisibility(View.GONE);
                            setTitle("Done");
                        // Toast.makeText(RegisterScreen.this,"User already registered with this Phone number",Toast.LENGTH_SHORT).show();
                    } else {

                        }
                            if (response.code() == 404) {

                                Toast.makeText(Login_screen.this, "Invalid UserName", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                setTitle("Done");
                    alertDialog = new AlertDialog.Builder(Login_screen.this).create();
                    alertDialog.setTitle("ANTS App");
                    alertDialog.setMessage("Invalid UserName");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();

                                    progressBar.setVisibility(View.GONE);
                                    setTitle("Done");
                                }
                            });
                    alertDialog.show();
                                // Handle unauthorized
                                // Toast.makeText(RegisterScreen.this,"User already registered with this Phone number",Toast.LENGTH_SHORT).show();
                            } else {
                        // Handle other responses
                    }

                }

                @Override
                public void onFailure(Call<LoginResponseBody2> call, Throwable t) {
                    Log.i("sendingfail", "Returned empty response");
                    progressBar.setVisibility(View.GONE);
                    setTitle("Done");
                    Toast.makeText(Login_screen.this,"connection error",Toast.LENGTH_LONG).show();

                }
            });


        }




        //signupRequestbody.setUsertype(usertypeuser);

       /* Toast.makeText(Signup_screen.this, usertypeseller, Toast.LENGTH_SHORT).show();
        Toast.makeText(Signup_screen.this, usertypeuser, Toast.LENGTH_SHORT).show();*/


    }



}
