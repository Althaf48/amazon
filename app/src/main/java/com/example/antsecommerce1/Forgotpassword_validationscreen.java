package com.example.antsecommerce1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.example.antsecommerce1.Interface.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Forgotpassword_validationscreen extends AppCompatActivity {

    CardView verify;


    EditText submitotp_ed;
    CardView generateotp,submitotp;
    Generateotp_responseBody registerResponseBody;
    Validateotp_ResponseBody validateotp_responseBody;
    RegisterInterface registerInterface;
    String generateotp_ed;
    Signupresponsebodymsg signupresponsebodymsg;
    SignupResponsebody1 signupResponsebody1;
    Signupresponsebody2 signupresponsebody2;
    PinEntryEditText pinEntry;
    AlertDialog alertDialog;
    int pinEntry1;
    String MobilePattern = "[0-9]{10}";

    PreferenceHelper preferenceHelper;
    String userseller,usercustomer;
    public ProgressBar progressBar;
    String usertypeseller1,usertypeuser1;

    String usertypeseller,usertypeuser,date,namef,mobilef,emailf,passwordf;
    String mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword_validationscreen);
        preferenceHelper = new PreferenceHelper(this);
        mobile = getIntent().getExtras().getString("mobileno");
        usertypeseller1 = getIntent().getExtras().getString("seller1");
        usertypeuser1 = getIntent().getExtras().getString("seller2");

        Toast.makeText(Forgotpassword_validationscreen.this, mobile, Toast.LENGTH_SHORT).show();

        registerInterface= ApiClient.getClient().create(RegisterInterface.class);
        getSupportActionBar().hide();

        userseller =  preferenceHelper.getSELLER();
        usercustomer = preferenceHelper.getCUSTOMER();
        progressBar = (ProgressBar)findViewById(R.id.prg5);

        date = String.valueOf(android.text.format.DateFormat.format("yyyy-MM-dd", new java.util.Date()));



   /*     namef = getIntent().getExtras().getString("name");
        mobilef = getIntent().getExtras().getString("mobile");
        emailf =  getIntent().getExtras().getString("email");
        passwordf =  getIntent().getExtras().getString("password");
        date =  getIntent().getExtras().getString("date");
        usertypeseller = getIntent().getExtras().getString("seller1");
        usertypeuser = getIntent().getExtras().getString("seller2");

        generateotp_ed = getIntent().getExtras().getString("mobileno");*/

        // submitotp_ed = findViewById(R.id.submitotped);
       // submitotp = findViewById(R.id.submitotpcard);
        pinEntry = (PinEntryEditText) findViewById(R.id.pin_entryid_f);

        verify = findViewById(R.id.submitotpcardfpvf);

        verify.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

              /*  Intent i = new Intent(Forgotpassword_validationscreen.this,Chnagepassword_screen.class);
                startActivity(i);*/
                progressBar.setVisibility(View.VISIBLE);
                setTitle("Loading...");
                validateotp();

            }
        });

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void validateotp() {

        // final String mobileno = generateotp_ed;
        final int pinEntry1 = Integer.parseInt(pinEntry.getText().toString());
        Calendar calendar = Calendar.getInstance();
        String currentDate24Hrs = (String) DateFormat.format(
                "yyyy-MM-dd kk:mm:ss", calendar.getTime());
        Log.i("DEBUG_TAG", "24Hrs format date: " + currentDate24Hrs);
        // String datetime = dateformat.format(c.getTime());


        Validateotp_RequestBody registerRequestBody1 = new Validateotp_RequestBody();
        registerRequestBody1.setPhone(mobile);
        registerRequestBody1.setOtp(pinEntry1);
        registerRequestBody1.setOtpexpirytime(currentDate24Hrs);

     /*   Log.d("otpdhsjh",generateotp_ed);
        Log.d("otpdhsjh", String.valueOf(otp));
        Log.d("otpdhsjh",currentDate24Hrs);*/

       /* final String token =  preferenceHelper.getTokens();

        Log.d("tokendone",token);*/


        Call<Validateotp_ResponseBody> call = registerInterface.getValidate2(registerRequestBody1);
        call.enqueue(new Callback<Validateotp_ResponseBody>() {
            @Override
            public void onResponse(Call<Validateotp_ResponseBody> call, Response<Validateotp_ResponseBody> response) {




                if (response.isSuccessful()) {
//                    String message = signupresponsebodymsg.getMessage();


                    if(response.code() == 200) {


                        if((usertypeseller1 == ("SELLER"))||(usertypeuser1 == getIntent().getExtras().getString("seller2"))){


                            Intent i = new Intent(Forgotpassword_validationscreen.this,Chnagepassword_screen.class);
                            i.putExtra("seller1",usertypeseller1);
                            i.putExtra("mobile",mobile);
                            startActivity(i);
                            progressBar.setVisibility(View.GONE);
                            setTitle("Done");
                            //registerMe();
                            Toast.makeText(Forgotpassword_validationscreen.this, "OTP Sent", Toast.LENGTH_SHORT).show();

                        }else
                        {

                            Intent i = new Intent(Forgotpassword_validationscreen.this,Chnagepassword_screen.class);
                            i.putExtra("seller2",usertypeuser1);
                            i.putExtra("mobile",mobile);
                            startActivity(i);
                            progressBar.setVisibility(View.GONE);
                            setTitle("Done");
                            //registerMe();
                            Toast.makeText(Forgotpassword_validationscreen.this, "OTP Sent", Toast.LENGTH_SHORT).show();


                        }




                    }else{
                        Toast.makeText(Forgotpassword_validationscreen.this, "check", Toast.LENGTH_SHORT).show();

                    }
                    // Do awesome stuff
                    // signupresponsebodymsg = response.body();
                    // String message1 = signupresponsebodymsg.getMessage();
                    //String status = signupresponsebodymsg.getStatus();

                }
                else{
                    Toast.makeText(Forgotpassword_validationscreen.this, "Wrong Password", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<Validateotp_ResponseBody> call, Throwable t) {
                Log.i("sendingfail", "Returned empty response");
                Toast.makeText(Forgotpassword_validationscreen.this,"check connection",Toast.LENGTH_LONG).show();

            }
        });
    }


    private void registerMe() {

      /*  final String name = namef.getText().toString();
        final String mobileno = mobileed.getText().toString();
        final String email = emailed.getText().toString();
        final String password = passworded.getText().toString();*/
        // final String states = spinner_val_edt_det.getSelectedItem().toString();



        /*Log.e("date",name);
        Log.e("date",mobileno);
        Log.e("date",email);
        Log.e("date",password);
        Log.e("date",date);
        Log.e("date",usertypeuser);*/
//        Log.e("date",usertypeuser);


        if((userseller ==("SELLER"))||(usercustomer ==  preferenceHelper.getCUSTOMER())){

            Toast.makeText(Forgotpassword_validationscreen.this,usertypeseller, Toast.LENGTH_SHORT).show();


            SignupRequestbody signupRequestbody = new SignupRequestbody();
            signupRequestbody.setEmailid(emailf);
            signupRequestbody.setPassword(passwordf);
            signupRequestbody.setName(namef);
            signupRequestbody.setPhonenumber(mobilef);
            signupRequestbody.setRegistrationdate(date);
            signupRequestbody.setUsertype(usertypeseller);

            Call<Signupresponsebody2> call = registerInterface.getUserRegi(signupRequestbody);
            call.enqueue(new Callback<Signupresponsebody2>() {
                @Override
                public void onResponse(Call<Signupresponsebody2> call, Response<Signupresponsebody2> response) {

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
                        signupresponsebody2 = response.body();
                        String status = signupresponsebody2.getMessage();
                        SignupResponsebody1 status2 = signupresponsebody2.getData();







                        Log.d("status",status);

                        if(status.equalsIgnoreCase("Seller Created Successfully")) {

                            if(status2.getUserType().equalsIgnoreCase("SELLER")){

                                Intent i = new Intent(Forgotpassword_validationscreen.this,Login_screen.class);
                                i.putExtra("seller",usertypeseller);
                                startActivity(i);
                            }else{
                              /*  Intent i = new Intent(Verifyaccount_screen.this,Login_screen.class);
                                i.putExtra("Customer",usertypeuser);
                                startActivity(i);*/
                                Toast.makeText(Forgotpassword_validationscreen.this, "check seller signup", Toast.LENGTH_SHORT).show();
                            }


                            // Toast.makeText(Verifyaccount_screen.this, status, Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Forgotpassword_validationscreen.this, status, Toast.LENGTH_SHORT).show();

                        }
                        // Do awesome stuff
                        // signupresponsebodymsg = response.body();
                        // String message1 = signupresponsebodymsg.getMessage();
                        //String status = signupresponsebodymsg.getStatus();

                    } else
                    if (response.code() == 409) {
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
                public void onFailure(Call<Signupresponsebody2> call, Throwable t) {
                    Log.i("sendingfail", "Returned empty response");
                    Toast.makeText(Forgotpassword_validationscreen.this,"connection error",Toast.LENGTH_LONG).show();

                }
            });

        }else
        {

            SignupRequestbody signupRequestbody = new SignupRequestbody();
            signupRequestbody.setEmailid(emailf);
            signupRequestbody.setPassword(passwordf);
            signupRequestbody.setName(namef);
            signupRequestbody.setPhonenumber(mobilef);
            signupRequestbody.setRegistrationdate(date);
            signupRequestbody.setUsertype(usertypeuser);


            Toast.makeText(Forgotpassword_validationscreen.this, usertypeuser, Toast.LENGTH_SHORT).show();

            Call<Signupresponsebody2> call = registerInterface.getUserRegi(signupRequestbody);
            call.enqueue(new Callback<Signupresponsebody2>() {
                @Override
                public void onResponse(Call<Signupresponsebody2> call, Response<Signupresponsebody2> response) {

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

                        signupresponsebody2 = response.body();
                        String status = signupresponsebody2.getMessage();
                        SignupResponsebody1 status2 = signupresponsebody2.getData();

                        Log.d("status",status);

                        if(status.equalsIgnoreCase("User Created Successfully")) {

                            if(status2.getUserType().equalsIgnoreCase("CUSTOMER")){
                                Intent i = new Intent(Forgotpassword_validationscreen.this,Login_screen.class);
                                i.putExtra("customer",usertypeuser);
                                startActivity(i);

                            }else{
                                Toast.makeText(Forgotpassword_validationscreen.this, "check customer signup", Toast.LENGTH_SHORT).show();

                               /* Intent i = new Intent(Verifyaccount_screen.this,Login_screen.class);
                                i.putExtra("Seller",usertypeseller);
                                startActivity(i);*/
                            }
                        }else{
                            Toast.makeText(Forgotpassword_validationscreen.this, status, Toast.LENGTH_SHORT).show();

                        }
                        // Do awesome stuff
                        // signupresponsebodymsg = response.body();
                        // String message1 = signupresponsebodymsg.getMessage();
                        //String status = signupresponsebodymsg.getStatus();

                    } else if (response.code() == 409) {
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
                public void onFailure(Call<Signupresponsebody2> call, Throwable t) {
                    Log.i("sendingfail", "Returned empty response");
                    Toast.makeText(Forgotpassword_validationscreen.this,"connection error",Toast.LENGTH_LONG).show();

                }
            });


        }




        //signupRequestbody.setUsertype(usertypeuser);

       /* Toast.makeText(Signup_screen.this, usertypeseller, Toast.LENGTH_SHORT).show();
        Toast.makeText(Signup_screen.this, usertypeuser, Toast.LENGTH_SHORT).show();*/


    }




}
