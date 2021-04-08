package com.example.antsecommerce1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

public class Forgotpw_screen extends AppCompatActivity {

    EditText otped;
   // TextView nexttv;
    TextView next_tv;
    AlertDialog alertDialog;
    String MobilePattern = "[0-9]{10}";
    RegisterInterface registerInterface;
    Generateotp_responseBody registerResponseBody;
    String date,namef,mobilef,emailf,passwordf;


   PreferenceHelper preferenceHelper;
   String userseller,usercustomer;
    public ProgressBar progressBar;
    String usertypeseller1,usertypeuser1;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpw_screen);
        registerInterface= ApiClient.getClient().create(RegisterInterface.class);
        getSupportActionBar().hide();
        preferenceHelper = new PreferenceHelper(this);
         userseller =  preferenceHelper.getSELLER();
         usercustomer = preferenceHelper.getCUSTOMER();
        usertypeseller1 = getIntent().getExtras().getString("seller1");
        usertypeuser1 = getIntent().getExtras().getString("seller2");

       // Log.d("tokendone",userseller);
      /*  Log.d("tokendone",usercustomer);
        Log.d("tokendone",userseller);*/

       /* Log.d("tokendoneas",usertypeseller1);
        Log.d("tokendoneas",usertypeuser1);*/




        otped = findViewById(R.id.enterotpmobile_ed);
        next_tv = findViewById(R.id.tv_next);

        progressBar = (ProgressBar)findViewById(R.id.prg4);
        date = String.valueOf(android.text.format.DateFormat.format("yyyy-MM-dd", new java.util.Date()));


        next_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent i = new Intent(Forgotpw_screen.this,Forgotpassword_validationscreen.class);
                startActivity(i);*/


                if (otped.getText().toString().trim().length() == 0) {
                    otped.setError("phone number is not entered");
                    otped.requestFocus();
                } else {

                    if (otped.getText().toString().matches(MobilePattern)) {
                        // registerMe();


                        final String mobileno = otped.getText().toString();


                       // generateotp2();
                        progressBar.setVisibility(View.VISIBLE);
                        setTitle("Loading...");
                        registerMe();
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


                    }else if (!otped.getText().toString().matches(MobilePattern)) {
                        progressBar.setVisibility(View.GONE);
                        setTitle("Done");
                        alertDialog = new AlertDialog.Builder(Forgotpw_screen.this).create();
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

    private void registerMe() {
       final String mobileno = otped.getText().toString();
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

        if((usertypeseller1 == ("SELLER"))||(usertypeuser1 == getIntent().getExtras().getString("seller2"))){

            Toast.makeText(Forgotpw_screen.this,usertypeseller1, Toast.LENGTH_SHORT).show();


            GenererateotpforgotrequestBody signupRequestbody = new GenererateotpforgotrequestBody();

            signupRequestbody.setMobileNo(mobileno);
            signupRequestbody.setUserType(usertypeseller1);

            Call<Generateotp_responseBody> call = registerInterface.getGenerateotp2(signupRequestbody);
            call.enqueue(new Callback<Generateotp_responseBody>() {
                @Override
                public void onResponse(Call<Generateotp_responseBody> call, Response<Generateotp_responseBody> response) {

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
                        registerResponseBody = response.body();
                        String status = registerResponseBody.getMessage();
                        //SignupResponsebody1 status2 = signupresponsebody2.getData();



                        Log.d("status",status);

                        if(status.equalsIgnoreCase("Otp Sent successfully")) {

                            Intent i = new Intent(Forgotpw_screen.this,Forgotpassword_validationscreen.class);
                            i.putExtra("seller1",usertypeseller1);
                            i.putExtra("mobileno",mobileno);
                            startActivity(i);
                            progressBar.setVisibility(View.GONE);
                            setTitle("Done");

                             Toast.makeText(Forgotpw_screen.this, status, Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Forgotpw_screen.this, status, Toast.LENGTH_SHORT).show();

                        }
                        // Do awesome stuff
                        // signupresponsebodymsg = response.body();
                        // String message1 = signupresponsebodymsg.getMessage();
                        //String status = signupresponsebodymsg.getStatus();

                    } else
                    if (response.code() == 404) {
                        progressBar.setVisibility(View.GONE);
                        setTitle("Done");
                    alertDialog = new AlertDialog.Builder(Forgotpw_screen.this).create();
                    alertDialog.setTitle("ANTS App");
                    alertDialog.setMessage("Mobile number not registerd as Seller");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                        // Handle unauthorized
                        // Toast.makeText(RegisterScreen.this,"User already registered with this Phone number",Toast.LENGTH_SHORT).show();
                    } else

                        {

                        // Handle other responses
                    }

                }

                @Override
                public void onFailure(Call<Generateotp_responseBody> call, Throwable t) {
                    Log.i("sendingfail", "Returned empty response");
                    Toast.makeText(Forgotpw_screen.this,"connection error",Toast.LENGTH_LONG).show();

                }
            });

        }else
        {

            Toast.makeText(Forgotpw_screen.this,usertypeuser1, Toast.LENGTH_SHORT).show();

            GenererateotpforgotrequestBody signupRequestbody = new GenererateotpforgotrequestBody();

            signupRequestbody.setMobileNo(mobileno);
            signupRequestbody.setUserType(usertypeuser1);

            Call<Generateotp_responseBody> call = registerInterface.getGenerateotp2(signupRequestbody);
            call.enqueue(new Callback<Generateotp_responseBody>() {
                @Override
                public void onResponse(Call<Generateotp_responseBody> call, Response<Generateotp_responseBody> response) {

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
                        registerResponseBody = response.body();
                        String status = registerResponseBody.getMessage();
                        //SignupResponsebody1 status2 = signupresponsebody2.getData();







                        Log.d("status",status);

                        if(status.equalsIgnoreCase("Otp Sent successfully")) {


                            Intent i = new Intent(Forgotpw_screen.this,Forgotpassword_validationscreen.class);
                            i.putExtra("seller2",usertypeuser1);
                            i.putExtra("mobileno",mobileno);
                            startActivity(i);
                            progressBar.setVisibility(View.GONE);
                            setTitle("Done");

                            // Toast.makeText(Verifyaccount_screen.this, status, Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Forgotpw_screen.this, status, Toast.LENGTH_SHORT).show();

                        }
                        // Do awesome stuff
                        // signupresponsebodymsg = response.body();
                        // String message1 = signupresponsebodymsg.getMessage();
                        //String status = signupresponsebodymsg.getStatus();

                    } else
                    if (response.code() == 404) {
                        progressBar.setVisibility(View.GONE);
                        setTitle("Done");
                        alertDialog = new AlertDialog.Builder(Forgotpw_screen.this).create();
                        alertDialog.setTitle("ANTS App");
                        alertDialog.setMessage("Mobile number not registerd as Customer");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
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
                public void onFailure(Call<Generateotp_responseBody> call, Throwable t) {
                    Log.i("sendingfail", "Returned empty response");
                    Toast.makeText(Forgotpw_screen.this,"connection error",Toast.LENGTH_LONG).show();

                }
            });


        }




        //signupRequestbody.setUsertype(usertypeuser);

       /* Toast.makeText(Signup_screen.this, usertypeseller, Toast.LENGTH_SHORT).show();
        Toast.makeText(Signup_screen.this, usertypeuser, Toast.LENGTH_SHORT).show();*/


    }



}
