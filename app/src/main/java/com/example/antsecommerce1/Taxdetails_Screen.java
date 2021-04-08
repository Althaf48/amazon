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

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Taxdetails_Screen extends AppCompatActivity {

   // int sellerid_s;
    String sellerid_s;
    EditText pancarded,gsted;
    AlertDialog alertDialog;
    TextView savetvtaxdetails;
    RegisterInterface registerInterface;
    TaxdetailsResponseBody1 taxdetailsResponseBody1;
    Taxdetails_ResponseBody2 taxdetails_responseBody2;
    public static final String TAXDETAILS = "Taxdetails";
    String name;
    String mobile;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxdetails__screen);
        registerInterface = ApiClient.getClient().create(RegisterInterface.class);
        name = getIntent().getExtras().getString("name");
        mobile = getIntent().getExtras().getString("mobile");
        email = getIntent().getExtras().getString("email");

        sellerid_s = getIntent().getExtras().getString("sellerid");
        getSupportActionBar().setTitle("Tax Details");

        pancarded = findViewById(R.id.pancardid);
        gsted = findViewById(R.id.gstid);
        savetvtaxdetails = findViewById(R.id.savetaxdetails_t);





        SharedPreferences settings = getSharedPreferences(TAXDETAILS, 0);
//Get "hasLoggedIn" value. If the value doesn't exist yet false is returned
        boolean hasLoggedInaddress = settings.getBoolean("Taxdetails", false);
        String pancard_s = settings.getString("pancard",null);
        String gstnumber_s = settings.getString("gstnumber",null);
        String accountnumber_s = settings.getString("accountnumber",null);
        String ifsccode_s = settings.getString("ifsccode",null);

        // String usertypeg = settings.getString("usertype1",null);
        if((hasLoggedInaddress)) {

            pancarded.setText(pancard_s);
            gsted.setText(gstnumber_s);
            /*usertypeseller1.equals(usertypeg);
            usertypeuser1.equals(usertypeg);*/
            //loginme();

        }
        else
        {

            Toast.makeText(Taxdetails_Screen.this,"Enter Address",Toast.LENGTH_SHORT).show(); // Show Login Activity
        }

        

        savetvtaxdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (pancarded.getText().toString().trim().length() == 0) {
                    pancarded.setError("Name is not entered");
                    pancarded.requestFocus();
                }
                if (pancarded.getText().toString().trim().length() == 0) {
                    pancarded.setError("Account Number is not entered");
                    pancarded.requestFocus();
                }
                else
                {

                    bankdetailsapi();

                }


            }
        });
        getAPitaxdetails();

    }


    private void bankdetailsapi() {

        final String selleridf = String.valueOf(sellerid_s);
        final String pancard = pancarded.getText().toString();
        final String gstnumber = gsted.getText().toString();
        // final String states = spinner_val_edt_det.getSelectedItem().toString();

//        Log.e("date",usertypeuser);

        Taxdetails_RequestBody taxdetails_requestBody = new Taxdetails_RequestBody();
        taxdetails_requestBody.setSellerId(selleridf);
        taxdetails_requestBody.setPanNumber(pancard);
        taxdetails_requestBody.setGstNumber(gstnumber);





        // Toast.makeText(Login_screen.this, usertypeuser1, Toast.LENGTH_SHORT).show();

        Call<Taxdetails_ResponseBody2> call = registerInterface.getTaxdetails(taxdetails_requestBody);
        call.enqueue(new Callback<Taxdetails_ResponseBody2>() {
            @Override
            public void onResponse(Call<Taxdetails_ResponseBody2> call, Response<Taxdetails_ResponseBody2> response) {

             /*   Log.e("date", String.valueOf(selleridf));
                Log.e("date",addressf);
                Log.e("date",statef);
                Log.e("date",cityf);
                Log.e("date",pincodef);
                Log.e("date",lattitudef);
                Log.e("date",longitudef);*/

                if (response.isSuccessful()) {
//                    String message = signupresponsebodymsg.getMessage();

                    taxdetails_responseBody2 = response.body();
                    String status = taxdetails_responseBody2.getMessage();
                    //Homepage_ResponseBody1 status2 = homepageseller_responsebody2.getData();

                    if (response.code() == 200) {

                        // Toast.makeText(Bank_Account_Details.this, status, Toast.LENGTH_SHORT).show();

                        final Integer sellerid = Integer.valueOf(sellerid_s);
                        Intent i = new Intent(Taxdetails_Screen.this, Homepageseller.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                   /* i.putExtra("name",status2.getName());
                                    i.putExtra("mobile",status2.getPhonenumber());
                                    i.putExtra("email",status2.getEmailid());*/
                        i.putExtra("name",name);
                        i.putExtra("mobile",mobile);
                        i.putExtra("email",email);
                        i.putExtra("sellerid",sellerid);

                        SharedPreferences settings = getSharedPreferences(TAXDETAILS, 0); // 0 - for private mode
                        SharedPreferences.Editor editor = settings.edit();


                        //Set "hasLoggedIn" to true
                        editor.putBoolean("Taxdetails", true);
                        editor.putString("pancard",pancard);
                        editor.putString("gstnumber",gstnumber);
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
                        Toast.makeText(Taxdetails_Screen.this, "check", Toast.LENGTH_SHORT).show();

                    }
                    // Do awesome stuff
                    // signupresponsebodymsg = response.body();
                    // String message1 = signupresponsebodymsg.getMessage();
                    //String status = signupresponsebodymsg.getStatus();

                } else if (response.code() == 401) {

                    //Toast.makeText(Login_screen.this, "Invalid password", Toast.LENGTH_SHORT).show();
                    alertDialog = new androidx.appcompat.app.AlertDialog.Builder(Taxdetails_Screen.this).create();
                    alertDialog.setTitle("ANTS App");
                    alertDialog.setMessage("Invalid P");
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

                    Toast.makeText(Taxdetails_Screen.this, "Invalid U", Toast.LENGTH_SHORT).show();
                    alertDialog = new androidx.appcompat.app.AlertDialog.Builder(Taxdetails_Screen.this).create();
                    alertDialog.setTitle("ANTS App");
                    alertDialog.setMessage("Invalid U");
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
            public void onFailure(Call<Taxdetails_ResponseBody2> call, Throwable t) {
                Log.i("sendingfail", "Returned empty response");
                Toast.makeText(Taxdetails_Screen.this, "connection error", Toast.LENGTH_LONG).show();

            }
        });
    }


    /*====tax details fetching data START=====*/

    private void getAPitaxdetails(){

       /* final ArrayList<String> list = new ArrayList<String>();
        list.clear();
        list.add("select city");*/


        Call<JsonObject> call = registerInterface.getgettingtaxdetails(sellerid_s);
        // Call<String> call2 = api.getJSONString2();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                // Log.i("Responsestring", response.body().toString());
                Log.d("jhgasfa",response.toString());
                Log.e("statuasdasssave details",response.message());
                //Toast.makeText(Homepageseller.this, Infocitiesandstatesresponsebody2., Toast.LENGTH_SHORT).show();
                //Toast.makeText()

                if (response.body()!=null){

                    Log.e("statusavsadasedetails",response.body().toString());
                    if (response.body().toString().contains(AppConstant.MESSAGE6)){
                        Log.e("statsadasadsus",response.body().toString());
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
                            Log.d("savedetasdsijafls",s);

                            JSONObject jsonObject2 = new JSONObject(jsonObject1.getString("data"));
                            Log.d("savedetdsdaijafls",jsonObject2.toString());

                            // JSONArray jsonArray2 = jsonObject1.getJSONArray("data");

                            //Log.d("savjaidetails",jsonObject2.getString("city"));

                            pancarded.setText(jsonObject2.getString("panNumber"));
                            gsted.setText(jsonObject2.getString("gstNumber"));


                            for (int i =0;i<jsonObject2.length();i++)
                            {
                                // JSONObject msgobj = jsonArray2.getJSONObject(i);
                                //Log.d("savedetailsjjkj",msgobj.toString());
                                // cityed.setText(msgobj.getString("city"));

                                // Log.d("savedeatilsjj",msgobj.getString("city"));

                                // addressed = msgobj.getString("address");
                               // addressed.setText(jsonObject2.getString("address"));

                                // JSONArray jsonArray3 = jsonObject1.getJSONArray("city");

                            /*    addressed.setText(msgobj.getString("address"));
                                cityed.setText(jsonObject1.getString("city"));
                                stateed.setText(jsonObject1.getString("state"));
                                pincodeed.setText(jsonObject1.getString("pin"));*/
                                // list.add(msgobj.getString("city"));


                                // Toast.makeText(Homepageseller.this, msgobj.getString("city"), Toast.LENGTH_SHORT).show();


                            }

                           /* ArrayAdapter<String> spinnerMenu = new ArrayAdapter<String>(ProfileUpdate_screen.this, android.R.layout.simple_list_item_1, list);
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

    /*========TAX DETAILS fetching end============*/



}
