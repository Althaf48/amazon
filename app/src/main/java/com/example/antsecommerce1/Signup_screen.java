package com.example.antsecommerce1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.antsecommerce1.Interface.ApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Signup_screen extends AppCompatActivity {

    String usertypeseller,usertypeuser,date;
    EditText nameed,mobileed,emailed,passworded,conformpassworded,shopnameed;
    CardView sigunupcv;
    RegisterInterface registerInterface;
    Signupresponsebody2 signupresponsebody2;
    Signupresponsebodymsg signupresponsebodymsg;
    AlertDialog alertDialog;
    Generateotp_responseBody registerResponseBody;
    String MobilePattern = "[0-9]{10}";
    public ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);
        registerInterface = ApiClient.getClient().create(RegisterInterface.class);
        date = String.valueOf(android.text.format.DateFormat.format("yyyy-MM-dd", new java.util.Date()));

        usertypeseller = getIntent().getExtras().getString("seller1");
        usertypeuser = getIntent().getExtras().getString("seller2");
        nameed = findViewById(R.id.name_ed);
        mobileed = findViewById(R.id.mobileno_ed);
        emailed = findViewById(R.id.emailid_ed);
        passworded = findViewById(R.id.password_ed);
        conformpassworded = findViewById(R.id.c_password_ed);
        sigunupcv = findViewById(R.id.signup_cv);
        progressBar = (ProgressBar)findViewById(R.id.prg);
        shopnameed =findViewById(R.id.shopname_ed);

        // String usertypeseller1 = getIntent().getExtras().getString("Seller");
        final String name = nameed.getText().toString();
        final String mobileno = mobileed.getText().toString();
        final String email = emailed.getText().toString();
        final String password = passworded.getText().toString();
        getSupportActionBar().hide();


        if((usertypeseller == ("Seller"))||(usertypeuser == getIntent().getExtras().getString("seller2"))){

            Toast.makeText(Signup_screen.this, usertypeseller, Toast.LENGTH_SHORT).show();

           shopnameed.setVisibility(View.VISIBLE);

        }
        else {


            shopnameed.setVisibility(View.GONE);

        }




        // Toast.makeText(Signup_screen.this, usertypeseller, Toast.LENGTH_SHORT).show();
        sigunupcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nameed.getText().toString().trim().length() == 0) {
                    nameed.setError("Name is not entered");
                    nameed.requestFocus();
                }
                if (emailed.getText().toString().trim().length() == 0) {
                    emailed.setError("Email is not entered");
                    emailed.requestFocus();
                }
                if (passworded.getText().toString().trim().length() == 0) {
                    passworded.setError("Password is not entered");
                    passworded.requestFocus();
                }
                if (conformpassworded.getText().toString().trim().length() == 0) {
                    conformpassworded.setError("Conform Password is not entered");
                    conformpassworded.requestFocus();
                }
                if (mobileed.getText().toString().trim().length() == 0) {
                    mobileed.setError("phone number is not entered");
                    mobileed.requestFocus();
                } else {

                    if (mobileed.getText().toString().matches(MobilePattern)) {
                        // registerMe();

                        final String name = nameed.getText().toString();
                        final String mobileno = mobileed.getText().toString();
                        final String email = emailed.getText().toString();
                        final String password = passworded.getText().toString();
                        final String confirmpassword = conformpassworded.getText().toString();

                        if (password.equals(confirmpassword)) {


                                progressBar.setVisibility(View.VISIBLE);
                                setTitle("Loading...");
                            generateotp2();
                           /* if ((usertypeseller == ("Seller")) || (usertypeuser == getIntent().getExtras().getString("seller2"))) {


                                Toast.makeText(Signup_screen.this, usertypeseller, Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(Signup_screen.this, Forgotpw_screen.class);
                                i.putExtra("name", name);
                                i.putExtra("mobile", mobileno);
                                i.putExtra("email", email);
                                i.putExtra("password", password);
                                i.putExtra("seller1", usertypeseller);
                                i.putExtra("date", date);
                                startActivity(i);


                            } else {


                                Toast.makeText(Signup_screen.this, usertypeuser, Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(Signup_screen.this, Forgotpw_screen.class);
                                i.putExtra("name", name);
                                i.putExtra("mobile", mobileno);
                                i.putExtra("email", email);
                                i.putExtra("password", password);
                                i.putExtra("seller2", usertypeuser);
                                i.putExtra("date", date);
                                startActivity(i);

                            }*/

                        } else {
                            // Toast.makeText(Signup_screen.this,"Password Not matching",Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            setTitle("Done");
                            alertDialog = new AlertDialog.Builder(Signup_screen.this).create();
                            alertDialog.setTitle("ANTS App");
                            alertDialog.setMessage("Password not matching");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                        }


                        // Toast.makeText(getApplicationContext(), "phone number is valid", Toast.LENGTH_SHORT).show();

                    } else if (!mobileed.getText().toString().matches(MobilePattern)) {
                        progressBar.setVisibility(View.GONE);
                        setTitle("Done");
                        alertDialog = new AlertDialog.Builder(Signup_screen.this).create();
                        alertDialog.setTitle("ANTS App");
                        alertDialog.setMessage("Please enter valid 10 digit phone number");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                        // Toast.makeText(getApplicationContext(), "Please enter valid 10 digit phone number", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

    private void generateotp2() {


       /* final String token =  preferenceHelper.getTokens();

        Log.d("tokendone",token);*/
        final String name = nameed.getText().toString();
        final String mobileno = mobileed.getText().toString();
        final String email = emailed.getText().toString();
        final String password = passworded.getText().toString();
        final String shopname = shopnameed.getText().toString();

        Generateotp_requestBody registerRequestBody = new Generateotp_requestBody();
        registerRequestBody.setPhone(mobileno);
        registerRequestBody.setEmail(email);
        Call<Generateotp_responseBody> call = registerInterface.getGenerateotp(registerRequestBody);
        call.enqueue(new Callback<Generateotp_responseBody>() {
            @Override
            public void onResponse(Call<Generateotp_responseBody> call, Response<Generateotp_responseBody> response) {

                if (response.isSuccessful()) {
//                    String message = signupresponsebodymsg.getMessage();
                    registerResponseBody = response.body();
                    String status = registerResponseBody.getMessage();

                    Log.d("status",status);

                    if(status.equalsIgnoreCase("Otp Sent successfully")) {

                       /* Intent i = new Intent(Forgotpw_screen.this,Verifyaccount_screen.class);
                        i.putExtra("mobileno",mobileno);

                        startActivity(i);*/
                        if((usertypeseller == ("Seller"))||(usertypeuser == getIntent().getExtras().getString("seller2"))){

                            Toast.makeText(Signup_screen.this, usertypeseller, Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(Signup_screen.this,Verifyaccount_screen.class);
                            i.putExtra("name",name);
                            i.putExtra("mobile",mobileno);
                            i.putExtra("email",email);
                            i.putExtra("password",password);
                            i.putExtra("shopname",shopname);
                            i.putExtra("seller1",usertypeseller);
                            i.putExtra("date",date);
                            startActivity(i);
                            progressBar.setVisibility(View.GONE);
                            setTitle("Done");

                        }
                        else {


                            Toast.makeText(Signup_screen.this, usertypeuser, Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(Signup_screen.this,Verifyaccount_screen.class);
                            i.putExtra("name",name);
                            i.putExtra("mobile",mobileno);
                            i.putExtra("email",email);
                            i.putExtra("password",password);
                            i.putExtra("seller2",usertypeuser);
                            i.putExtra("date",date);
                            startActivity(i);
                            progressBar.setVisibility(View.GONE);
                            setTitle("Done");

                        }

                        Toast.makeText(Signup_screen.this, status, Toast.LENGTH_SHORT).show();


                    }else{
                        Toast.makeText(Signup_screen.this, status, Toast.LENGTH_SHORT).show();

                    }
                    // Do awesome stuff
                    // signupresponsebodymsg = response.body();
                    // String message1 = signupresponsebodymsg.getMessage();
                    //String status = signupresponsebodymsg.getStatus();

                } else
                if (response.code() == 409) {
                    progressBar.setVisibility(View.GONE);
                    setTitle("Done");
                  /*  alertDialog = new AlertDialog.Builder(RegisterScreen.this).create();
                    alertDialog.setTitle("Covid-19 App");
                    alertDialog.setMessage("User already registered with this Phone number");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();*/
                    // Handle unauthorized
                    // Toast.makeText(RegisterScreen.this,"User already registered with this Phone number",Toast.LENGTH_SHORT).show();
                } else {
                    // Handle other responses
                }

            }

            @Override
            public void onFailure(Call<Generateotp_responseBody> call, Throwable t) {
                Log.i("sendingfail", "Returned empty response");
                Toast.makeText(Signup_screen.this,"Nothing returned",Toast.LENGTH_LONG).show();

            }
        });
    }


}
