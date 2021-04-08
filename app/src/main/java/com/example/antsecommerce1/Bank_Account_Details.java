package com.example.antsecommerce1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.antsecommerce1.Interface.ApiClient;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Bank_Account_Details extends AppCompatActivity {

    String sellerid_s;
    EditText bankholdernameed,accountypeed,accountnumbered,reenteraccountnumbered,ifsccodeed;
    TextView saveBankdetailstv,updatebankdetailstv;
    AlertDialog alertDialog;
    Bankacc_responseBody1 bankacc_responseBody1;
    Bankacc_responseBody2 bankacc_responseBody2;
    Bankacc_responseBody3 bankacc_responseBody3;
    String name;
    String mobile;
    String email;
    int s_id;
    public static final String BANKDETAILS = "MYBankdetails";

    RegisterInterface registerInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank__account__details);
       // sellerid_s = getIntent().getExtras().getInt("sellerid");
        name = getIntent().getExtras().getString("name");
        mobile = getIntent().getExtras().getString("mobile");
        email = getIntent().getExtras().getString("email");
        sellerid_s = getIntent().getExtras().getString("sellerid");
        registerInterface = ApiClient.getClient().create(RegisterInterface.class);
        getSupportActionBar().setTitle("Bank Account Details");
        Log.d("seller", String.valueOf(sellerid_s));
        bankholdernameed = findViewById(R.id.accountholdernameid);
        accountnumbered = findViewById(R.id.accountnumberid);
        accountypeed = findViewById(R.id.accounttypeid);
        reenteraccountnumbered = findViewById(R.id.reenteraccountnumberid);
        ifsccodeed  = findViewById(R.id.ifscid);
        saveBankdetailstv = findViewById(R.id.savebankdetailsid);
        updatebankdetailstv = findViewById(R.id.savebankdetailsid2);

        getAPibankdetails();
        SharedPreferences settings = getSharedPreferences(BANKDETAILS, 0);
//Get "hasLoggedIn" value. If the value doesn't exist yet false is returned
        boolean hasLoggedInBankdetails = settings.getBoolean("MYBankdetails", false);
        String accountholdername_s = settings.getString("accountholdername",null);
        String accounttype_s = settings.getString("accounttype",null);
        String accountnumber_s = settings.getString("accountnumber",null);
        String ifsccode_s = settings.getString("ifsccode",null);
        s_id = settings.getInt("id",0);

        // String usertypeg = settings.getString("usertype1",null);
        if((hasLoggedInBankdetails)) {

            bankholdernameed.setText(accountholdername_s);
            accountypeed.setText(accounttype_s);
            accountnumbered.setText(accountnumber_s);
            reenteraccountnumbered.setText(accountnumber_s);
            ifsccodeed.setText(ifsccode_s);

        }
        else
        {

            Toast.makeText(Bank_Account_Details.this,"Enter Bankdetails",Toast.LENGTH_SHORT).show(); // Show Login Activity
        }





        saveBankdetailstv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bankholdernameed.getText().toString().trim().length() == 0) {
                    bankholdernameed.setError("Name is not entered");
                    bankholdernameed.requestFocus();
                }
                if (accountnumbered.getText().toString().trim().length() == 0) {
                    accountnumbered.setError("Account Number is not entered");
                    accountnumbered.requestFocus();
                }
                if (accountypeed.getText().toString().trim().length() == 0) {
                    accountypeed.setError("Password is not entered");
                    accountypeed.requestFocus();
                }
                if (reenteraccountnumbered.getText().toString().trim().length() == 0) {
                    reenteraccountnumbered.setError("Conform Password is not entered");
                    reenteraccountnumbered.requestFocus();
                }
                if (ifsccodeed.getText().toString().trim().length() == 0) {
                    ifsccodeed.setError("Conform Password is not entered");
                    ifsccodeed.requestFocus();
                }
                 else {


                        // registerMe();


                        final String password = accountnumbered.getText().toString();
                        final String confirmpassword = reenteraccountnumbered.getText().toString();

                        if (password.equals(confirmpassword)) {

                            bankdetailsapi();
                            Toast.makeText(Bank_Account_Details.this,"save",Toast.LENGTH_SHORT).show();


                        } else {
                            // Toast.makeText(Signup_screen.this,"Password Not matching",Toast.LENGTH_SHORT).show();
                            alertDialog = new AlertDialog.Builder(Bank_Account_Details.this).create();
                            alertDialog.setTitle("ANTS App");
                            alertDialog.setMessage("Account number not matching");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                        }


                        // Toast.makeText(getApplicationContext(), "phone number is valid", Toast.LENGTH_SHORT).show();


                }



            }
        });


        updatebankdetailstv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updatebankdetailsapi();

            }
        });




    }



    private void bankdetailsapi() {

        final String selleridf = String.valueOf(sellerid_s);
        final String accounholdername = bankholdernameed.getText().toString();
        final String accounttype = accountypeed.getText().toString();
        final String accountnumber =  accountnumbered.getText().toString();
        final String ifsccode = ifsccodeed.getText().toString();
        // final String states = spinner_val_edt_det.getSelectedItem().toString();

//        Log.e("date",usertypeuser);

        Bankdetailsseller_requestBody bankdetailsseller_requestBody = new Bankdetailsseller_requestBody();
        bankdetailsseller_requestBody.setSellerId(selleridf);
        bankdetailsseller_requestBody.setAccountHolderName(accounholdername);
        bankdetailsseller_requestBody.setAccountType(accounttype);
        bankdetailsseller_requestBody.setAccountNumber(accountnumber);
        bankdetailsseller_requestBody.setIfscCode(ifsccode);




        // Toast.makeText(Login_screen.this, usertypeuser1, Toast.LENGTH_SHORT).show();

        Call<Bankacc_responseBody3> call = registerInterface.getBankaccountdetails(bankdetailsseller_requestBody);
        call.enqueue(new Callback<Bankacc_responseBody3>() {
            @Override
            public void onResponse(Call<Bankacc_responseBody3> call, Response<Bankacc_responseBody3> response) {

             /*   Log.e("date", String.valueOf(selleridf));
                Log.e("date",addressf);
                Log.e("date",statef);
                Log.e("date",cityf);
                Log.e("date",pincodef);
                Log.e("date",lattitudef);
                Log.e("date",longitudef);*/

                if (response.isSuccessful()) {
//                    String message = signupresponsebodymsg.getMessage();

                    bankacc_responseBody3 = response.body();
                    String status = bankacc_responseBody3.getMessage();
                   // String status2 = bankacc_responseBody3.getData();


                    //Homepage_ResponseBody1 status2 = homepageseller_responsebody2.getData();

                    if (response.code() == 200) {

                       // Toast.makeText(Bank_Account_Details.this, status, Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(Bank_Account_Details.this, Taxdetails_Screen.class);
                        //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                   /* i.putExtra("name",status2.getName());
                                    i.putExtra("mobile",status2.getPhonenumber());
                                    i.putExtra("email",status2.getEmailid());*/
                        i.putExtra("name",name);
                        i.putExtra("mobile",mobile);
                        i.putExtra("email",email);
                        i.putExtra("sellerid",sellerid_s);

                        Bankacc_responseBody2 bankacc_responseBody2 = bankacc_responseBody3.getData();

                        i.putExtra("id",bankacc_responseBody2.getId());

                        final Integer id = bankacc_responseBody2.getId();

                        SharedPreferences settings = getSharedPreferences(BANKDETAILS, 0); // 0 - for private mode
                        SharedPreferences.Editor editor = settings.edit();


                        //Set "hasLoggedIn" to true
                        editor.putBoolean("MYBankdetails", true);
                        editor.putString("accountholdername",accounholdername);
                        editor.putString("accounttype",accounttype);
                        editor.putString("accountnumber",accountnumber);
                        editor.putString("ifsccode",ifsccode);
                        editor.putInt("id",id);
                        // editor.remove(usertypeseller1);
                        // editor.putString("usertype1",usertypeuser1);
                        // Commit the edits!
                        editor.apply();



                        startActivity(i);
                       /* alertDialog = new androidx.appcompat.app.AlertDialog.Builder(Bank_Account_Details.this).create();
                        alertDialog.setTitle("ANTS App");
                        alertDialog.setMessage("Seller BankDetails saved Success fully");
                        alertDialog.setButton(androidx.appcompat.app.AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();

                                    }
                                });
                        alertDialog.show();*/

                        /* if (status.equalsIgnoreCase("Seller Address saved Success fully")) {

                         *//* addressed.setText("");
                                stateed.setText("");
                                cityed.setText("");
                                pincodeed.setText("");*//*

                                alertDialog = new androidx.appcompat.app.AlertDialog.Builder(Homepageseller.this).create();
                                alertDialog.setTitle("ANTS App");
                                alertDialog.setMessage("Seller Address saved Success fully");
                                alertDialog.setButton(androidx.appcompat.app.AlertDialog.BUTTON_NEUTRAL, "OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                alertDialog.show();


                                // Toast.makeText(Verifyaccount_screen.this, status, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Homepageseller.this, status, Toast.LENGTH_SHORT).show();

                            }*/
                    } else {
                        Toast.makeText(Bank_Account_Details.this, "check", Toast.LENGTH_SHORT).show();

                    }
                    // Do awesome stuff
                    // signupresponsebodymsg = response.body();
                    // String message1 = signupresponsebodymsg.getMessage();
                    //String status = signupresponsebodymsg.getStatus();

                } else if (response.code() == 401) {

                    //Toast.makeText(Login_screen.this, "Invalid password", Toast.LENGTH_SHORT).show();
                    alertDialog = new androidx.appcompat.app.AlertDialog.Builder(Bank_Account_Details.this).create();
                    alertDialog.setTitle("ANTS App");
                    alertDialog.setMessage("Invalid password");
                    alertDialog.setButton(androidx.appcompat.app.AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                    // Handle unauthorized
                    // Toast.makeText(RegisterScreen.this,"User already registered with this Phone number",Toast.LENGTH_SHORT).show();
                } else {

                }
                if (response.code() == 404) {

                    Toast.makeText(Bank_Account_Details.this, "Invalid UserName", Toast.LENGTH_SHORT).show();
                    alertDialog = new androidx.appcompat.app.AlertDialog.Builder(Bank_Account_Details.this).create();
                    alertDialog.setTitle("ANTS App");
                    alertDialog.setMessage("Invalid UserName");
                    alertDialog.setButton(androidx.appcompat.app.AlertDialog.BUTTON_NEUTRAL, "OK",
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
            public void onFailure(Call<Bankacc_responseBody3> call, Throwable t) {
                Log.i("sendingfail", "Returned empty response");
                Toast.makeText(Bank_Account_Details.this, "connection error", Toast.LENGTH_LONG).show();

            }
        });
    }


    private void updatebankdetailsapi() {


        final int sid = s_id;
        final int selleridf = Integer.parseInt(sellerid_s);
        final String accounholdername = bankholdernameed.getText().toString();
        final String accounttype = accountypeed.getText().toString();
        final String accountnumber =  accountnumbered.getText().toString();
        final String ifsccode = ifsccodeed.getText().toString();
        // final String states = spinner_val_edt_det.getSelectedItem().toString();

//        Log.e("date",usertypeuser);

        Bankaccupdate_RequestBody bankdetailsseller_requestBody = new Bankaccupdate_RequestBody();
        bankdetailsseller_requestBody.setId(sid);
        bankdetailsseller_requestBody.setSellerId(selleridf);
        bankdetailsseller_requestBody.setAccountHolderName(accounholdername);
        bankdetailsseller_requestBody.setAccountType(accounttype);
        bankdetailsseller_requestBody.setAccountNumber(accountnumber);
        bankdetailsseller_requestBody.setIfscCode(ifsccode);




        // Toast.makeText(Login_screen.this, usertypeuser1, Toast.LENGTH_SHORT).show();

        Call<Bankacc_responseBody3> call = registerInterface.getBankaccountupdatedetails(bankdetailsseller_requestBody);
        call.enqueue(new Callback<Bankacc_responseBody3>() {
            @Override
            public void onResponse(Call<Bankacc_responseBody3> call, Response<Bankacc_responseBody3> response) {

             /*   Log.e("date", String.valueOf(selleridf));
                Log.e("date",addressf);
                Log.e("date",statef);
                Log.e("date",cityf);
                Log.e("date",pincodef);
                Log.e("date",lattitudef);
                Log.e("date",longitudef);*/

                if (response.isSuccessful()) {
//                    String message = signupresponsebodymsg.getMessage();

                    bankacc_responseBody3 = response.body();
                    String status = bankacc_responseBody3.getMessage();
                    // String status2 = bankacc_responseBody3.getData();


                    //Homepage_ResponseBody1 status2 = homepageseller_responsebody2.getData();

                    if (response.code() == 200) {


                        alertDialog = new androidx.appcompat.app.AlertDialog.Builder(Bank_Account_Details.this).create();
                        alertDialog.setTitle("ANTS App");
                        alertDialog.setMessage("Bank Details Updated");
                        alertDialog.setButton(androidx.appcompat.app.AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();

                        // Toast.makeText(Bank_Account_Details.this, status, Toast.LENGTH_SHORT).show();

                      /*  Intent i = new Intent(Bank_Account_Details.this, Taxdetails_Screen.class);
                                   *//* i.putExtra("name",status2.getName());
                                    i.putExtra("mobile",status2.getPhonenumber());
                                    i.putExtra("email",status2.getEmailid());*//*
                        i.putExtra("name",name);
                        i.putExtra("mobile",mobile);
                        i.putExtra("email",email);
                        i.putExtra("sellerid",sellerid_s);

                        Bankacc_responseBody2 bankacc_responseBody2 = bankacc_responseBody3.getData();

                        i.putExtra("id",bankacc_responseBody2.getId());


                        SharedPreferences settings = getSharedPreferences(BANKDETAILS, 0); // 0 - for private mode
                        SharedPreferences.Editor editor = settings.edit();


                        //Set "hasLoggedIn" to true
                        editor.putBoolean("MYBankdetails", true);
                        editor.putString("accountholdername",accounholdername);
                        editor.putString("accounttype",accounttype);
                        editor.putString("accountnumber",accountnumber);
                        editor.putString("ifsccode",ifsccode);
                        // editor.remove(usertypeseller1);
                        // editor.putString("usertype1",usertypeuser1);
                        // Commit the edits!
                        editor.apply();



                        startActivity(i);*/
                       /* alertDialog = new androidx.appcompat.app.AlertDialog.Builder(Bank_Account_Details.this).create();
                        alertDialog.setTitle("ANTS App");
                        alertDialog.setMessage("Seller BankDetails saved Success fully");
                        alertDialog.setButton(androidx.appcompat.app.AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();

                                    }
                                });
                        alertDialog.show();*/

                        /* if (status.equalsIgnoreCase("Seller Address saved Success fully")) {

                         *//* addressed.setText("");
                                stateed.setText("");
                                cityed.setText("");
                                pincodeed.setText("");*//*

                                alertDialog = new androidx.appcompat.app.AlertDialog.Builder(Homepageseller.this).create();
                                alertDialog.setTitle("ANTS App");
                                alertDialog.setMessage("Seller Address saved Success fully");
                                alertDialog.setButton(androidx.appcompat.app.AlertDialog.BUTTON_NEUTRAL, "OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                alertDialog.show();


                                // Toast.makeText(Verifyaccount_screen.this, status, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Homepageseller.this, status, Toast.LENGTH_SHORT).show();

                            }*/
                    } else {
                        Toast.makeText(Bank_Account_Details.this, "check", Toast.LENGTH_SHORT).show();

                    }
                    // Do awesome stuff
                    // signupresponsebodymsg = response.body();
                    // String message1 = signupresponsebodymsg.getMessage();
                    //String status = signupresponsebodymsg.getStatus();

                } else if (response.code() == 401) {

                    //Toast.makeText(Login_screen.this, "Invalid password", Toast.LENGTH_SHORT).show();
                    alertDialog = new androidx.appcompat.app.AlertDialog.Builder(Bank_Account_Details.this).create();
                    alertDialog.setTitle("ANTS App");
                    alertDialog.setMessage("Invalid password");
                    alertDialog.setButton(androidx.appcompat.app.AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                    // Handle unauthorized
                    // Toast.makeText(RegisterScreen.this,"User already registered with this Phone number",Toast.LENGTH_SHORT).show();
                } else {

                }
                if (response.code() == 404) {

                    Toast.makeText(Bank_Account_Details.this, "Invalid UserName", Toast.LENGTH_SHORT).show();
                    alertDialog = new androidx.appcompat.app.AlertDialog.Builder(Bank_Account_Details.this).create();
                    alertDialog.setTitle("ANTS App");
                    alertDialog.setMessage("Invalid UserName");
                    alertDialog.setButton(androidx.appcompat.app.AlertDialog.BUTTON_NEUTRAL, "OK",
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
            public void onFailure(Call<Bankacc_responseBody3> call, Throwable t) {
                Log.i("sendingfail", "Returned empty response");
                Toast.makeText(Bank_Account_Details.this, "connection error", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void getAPibankdetails(){

       /* final ArrayList<String> list = new ArrayList<String>();
        list.clear();
        list.add("select city");*/


        Call<JsonObject> call = registerInterface.getgettingBankdetails(sellerid_s);
        // Call<String> call2 = api.getJSONString2();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                // Log.i("Responsestring", response.body().toString());
                Log.d("dataresponssavedetails",response.toString());
                Log.e("statussave details",response.message());
                //Toast.makeText(Homepageseller.this, Infocitiesandstatesresponsebody2., Toast.LENGTH_SHORT).show();
                //Toast.makeText()

                if (response.body()!=null){

                    Log.e("statusavedetails",response.body().toString());
                    if (response.body().toString().contains(AppConstant.MESSAGE5)){
                        Log.e("statsasus",response.body().toString());
                        // AppUtils.showToast(mContext,response.body().toString());



                        try
                        {

                            JSONObject jsonObject1 = new JSONObject(response.body().toString());
                            Log.d("savedetaijafls",jsonObject1.toString()+"");

                            //  JSONArray jsondata = jsonObject.getJSONArray("data");
                            // JSONObject dataobj = jsondata.getJSONObject(0);


                           /* recoveredtv.setText(jsonObject1.getString("recovered"));
                            diseasedtv.setText(jsonObject1.getString("deceased"));
                            conformedtv.setText(jsonObject1.getString("conformed"));*/
                            // JSONArray jsonArray = jsonObject1.getJSONArray("data");

                            //  JSONArray jsonArray = jsonObject1.getJSONArray("data");

                            String s = jsonObject1.getString("data");
                            Log.d("savedetassddsijafls",s);

                          // JSONObject jsonObject2 = new JSONObject(jsonObject1.getString("data"));
                          // Log.d("savedetdjkdjssdaijafls",jsonObject2.toString());
                          //  JSONArray jsonArray = jsonObject1.getJSONArray("data");

                             JSONArray jsonArray2 = jsonObject1.getJSONArray("data");

                           // Log.d("savjaijdkjkdetails",jsonArray2.toString());

                            for (int i =0;i<jsonArray2.length();i++)
                            {
                                 JSONObject msgobj = jsonArray2.getJSONObject(i);
                                Log.d("savedetailsjjkj",msgobj.toString());
                                // cityed.setText(msgobj.getString("city"));

                                // Log.d("savedeatilsjj",msgobj.getString("city"));

                                // addressed = msgobj.getString("address");
                                bankholdernameed.setText(msgobj.getString("accountHolderName"));
                                accountypeed.setText(msgobj.getString("accountType"));
                                accountnumbered.setText(msgobj.getString("accountNumber"));
                                reenteraccountnumbered.setText(msgobj.getString("accountNumber"));
                                ifsccodeed.setText(msgobj.getString("ifscCode"));

                                // JSONArray jsonArray3 = jsonObject1.getJSONArray("city");

                              /*  addressed.setText(msgobj.getString("address"));
                                cityed.setText(jsonObject1.getString("city"));
                                stateed.setText(jsonObject1.getString("state"));
                                pincodeed.setText(jsonObject1.getString("pin"));*/
                                // list.add(msgobj.getString("city"));


                                // Toast.makeText(Homepageseller.this, msgobj.getString("city"), Toast.LENGTH_SHORT).show();


                            }
                           // accountypeed.setText(jsonArray2.);
                          /*  accountnumbered.setText(jsonObject2.getString("accountHolderName"));
                            accountypeed.setText(jsonObject2.getString("accountType"));
                            accountnumbered.setText(jsonObject2.getString("accountNumber"));
                            ifsccodeed.setText(jsonObject2.getString("ifscCode"));*/




                          /*  ArrayAdapter<String> spinnerMenu = new ArrayAdapter<String>(Bank_Account_Details.this, android.R.layout.simple_list_item_1, list);
                            spinnerMenu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                            spinner.setAdapter(spinnerMenu);*/



                        }  catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //  Log.e("locations",response.body().getLocations().toString());

                    }
                    else {
                        Log.e("statuses",response.body().toString());

                    }
                }
                else {

                    //Log.e("statuse",response.body().toString());
                    Log.e("statuse",response.toString());

                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });

    }



}
