package com.example.antsecommerce1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.antsecommerce1.Interface.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Chnagepassword_screen extends AppCompatActivity {

    CardView verify;
    String mobile;
    String userseller,usercustomer;
    PreferenceHelper preferenceHelper;
    TextView changepasswordbtntv;
    AlertDialog alertDialog;
    EditText password1,password2;
    RegisterInterface registerInterface;
    Reset_ResponseBody reset_responseBody;
    String usertypeseller1,usertypeuser1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chnagepassword_screen);
        getSupportActionBar().hide();
        preferenceHelper = new PreferenceHelper(this);
        registerInterface= ApiClient.getClient().create(RegisterInterface.class);
        mobile = getIntent().getExtras().getString("mobile");
        userseller =  preferenceHelper.getSELLER();
        usercustomer = preferenceHelper.getCUSTOMER();
        usertypeseller1 = getIntent().getExtras().getString("seller1");
        usertypeuser1 = getIntent().getExtras().getString("seller2");


        password1 = findViewById(R.id.password_ed_c1);
        password2 = findViewById(R.id.password_ed_c2);
        changepasswordbtntv = findViewById(R.id.tv_next_c);
        Toast.makeText(Chnagepassword_screen.this, mobile, Toast.LENGTH_SHORT).show();

        changepasswordbtntv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (password1.getText().toString().trim().length() == 0) {
                    password1.setError("Password is not entered");
                    password1.requestFocus();
                }
                if (password2.getText().toString().trim().length() == 0) {
                    password2.setError("Confirm Password is not entered");
                    password2.requestFocus();
                }
                else {

                    final String password = password1.getText().toString();
                    final String confirmpassword = password2.getText().toString();
                    if (password.equals(confirmpassword)) {

                        resetmethod();

                    } else {
                         Toast.makeText(Chnagepassword_screen.this,"Password Not matching",Toast.LENGTH_SHORT).show();
                      /*  alertDialog = new AlertDialog.Builder(Chnagepassword_screen.this).create();
                        alertDialog.setTitle("ANTS App");
                        alertDialog.setMessage("Password not matching");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();*/
                    }
                }


            }
        });





    }


    private void resetmethod() {

        //final String mobileno = mobile.getText().toString();
        final String newpassword = password1.getText().toString();
        //final String usertypeuser = usertype.getSelectedItem().toString();

        //  final int otp = Integer.parseInt(validateotp_ed.getText().toString());

        if((usertypeseller1 == ("SELLER"))||(usertypeuser1 == getIntent().getExtras().getString("seller2"))){

            Toast.makeText(Chnagepassword_screen.this,usertypeseller1, Toast.LENGTH_SHORT).show();


            Reset_RequestBody reseteRequestBody1 = new Reset_RequestBody();
            reseteRequestBody1.setMobileNo(mobile);
            reseteRequestBody1.setPassword(newpassword);
            reseteRequestBody1.setUserType(usertypeseller1);
            Call<Reset_ResponseBody> call = registerInterface.getReset(reseteRequestBody1);
            call.enqueue(new Callback<Reset_ResponseBody>() {
                @Override
                public void onResponse(Call<Reset_ResponseBody> call, Response<Reset_ResponseBody> response) {

                    if (response.body()!=null){
                        reset_responseBody = response.body();
                        String message = reset_responseBody.getMessage();
                        String status = reset_responseBody.getStatus();
                       // Log.d("mobilejsash",mobile.getText().toString());
                        if (status.equalsIgnoreCase("true")){
                            Toast.makeText(Chnagepassword_screen.this,message,Toast.LENGTH_SHORT).show();
                            Intent i =new Intent(Chnagepassword_screen.this,MainActivity.class);
                            startActivity(i);


                        }else {
                            Toast.makeText(Chnagepassword_screen.this,"check",Toast.LENGTH_SHORT).show();
                        }

                    }

                }

                @Override
                public void onFailure(Call<Reset_ResponseBody> call, Throwable t) {
                    Log.i("sendingfail", "Returned empty response");
                    Toast.makeText(Chnagepassword_screen.this,"Nothing returned",Toast.LENGTH_LONG).show();

                }
            });

        }else
        {

            Toast.makeText(Chnagepassword_screen.this,usertypeuser1, Toast.LENGTH_SHORT).show();

           // Toast.makeText(Chnagepassword_screen.this,usercustomer, Toast.LENGTH_SHORT).show();


            Reset_RequestBody reseteRequestBody1 = new Reset_RequestBody();
            reseteRequestBody1.setMobileNo(mobile);
            reseteRequestBody1.setPassword(newpassword);
            reseteRequestBody1.setUserType(usertypeuser1);
            Call<Reset_ResponseBody> call = registerInterface.getReset(reseteRequestBody1);
            call.enqueue(new Callback<Reset_ResponseBody>() {
                @Override
                public void onResponse(Call<Reset_ResponseBody> call, Response<Reset_ResponseBody> response) {

                    if (response.body()!=null){
                        reset_responseBody = response.body();
                        String message = reset_responseBody.getMessage();
                        String status = reset_responseBody.getStatus();
                        // Log.d("mobilejsash",mobile.getText().toString());
                        if (status.equalsIgnoreCase("true")){

                            Toast.makeText(Chnagepassword_screen.this,message,Toast.LENGTH_SHORT).show();
                            Intent i =new Intent(Chnagepassword_screen.this,MainActivity.class);
                            startActivity(i);


                        }else {
                            Toast.makeText(Chnagepassword_screen.this,"Invalid number",Toast.LENGTH_SHORT).show();
                        }

                    }

                }

                @Override
                public void onFailure(Call<Reset_ResponseBody> call, Throwable t) {
                    Log.i("sendingfail", "Returned empty response");
                    Toast.makeText(Chnagepassword_screen.this,"Nothing returned",Toast.LENGTH_LONG).show();

                }
            });


        }


    }





}
