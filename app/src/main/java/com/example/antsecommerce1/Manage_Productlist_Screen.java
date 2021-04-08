package com.example.antsecommerce1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.antsecommerce1.Interface.ApiClient;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Manage_Productlist_Screen extends AppCompatActivity {

    private RecyclerView reportsrecyclerview;
    Productdetails_sellerid_categoryid_responseBody2 productdetailsSelleridCategoryidResponseBody2;
    Productdetails_sellerid_categoryid_responseBody1 productdetailsSelleridCategoryidResponseBody1;
    RegisterInterface registerInterface;
    private Context mContext;
    int productid;
    int productvariantid;
    int productcategory;
    Double price;
    Boolean active;
    String mrp;
    TextView groceriestv,electronicstv,shoestv,clothestv;
    EditText searchbaredittext;

    String Productvariantname,skunumber,date,piecesavailable,edit,productimg;
    private ArrayList<Productdetails_sellerid_categoryid_responseBody1> datumArrayList=  new ArrayList<Productdetails_sellerid_categoryid_responseBody1>();;
    private Dashboard_recyclervw_normaltable_adapter fwreportGridAdapterdatum;
    androidx.appcompat.app.AlertDialog alertDialog;
    int sellerid_s;
    String categoryidClothes = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage__productlist__screen);
        registerInterface = ApiClient.getClient().create(RegisterInterface.class);
        sellerid_s = getIntent().getExtras().getInt("sellerid");
        groceriestv = findViewById(R.id.groceriestvid);
        electronicstv = findViewById(R.id.electronicstvid);
        shoestv = findViewById(R.id.shoestvid);
        clothestv = findViewById(R.id.clothestvid);
        searchbaredittext = findViewById(R.id.edittextsearchbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("List of Products");

        electronicstv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ManageProductdetailselectronics();
                groceriestv.setBackgroundColor(Color.parseColor("#EBEBEB"));
                shoestv.setBackgroundColor(Color.parseColor("#EBEBEB"));
                clothestv.setBackgroundColor(Color.parseColor("#EBEBEB"));
                electronicstv.setBackgroundColor(Color.parseColor("#555555"));

            }
        });


        searchbaredittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });

        groceriestv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ManageProductdetailgroceries();
                groceriestv.setBackgroundColor(Color.parseColor("#555555"));
                electronicstv.setBackgroundColor(Color.parseColor("#EBEBEB"));
                shoestv.setBackgroundColor(Color.parseColor("#EBEBEB"));
                clothestv.setBackgroundColor(Color.parseColor("#EBEBEB"));

            }
        });

        clothestv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ManageProductdetailclothes();
                groceriestv.setBackgroundColor(Color.parseColor("#EBEBEB"));
                electronicstv.setBackgroundColor(Color.parseColor("#EBEBEB"));
                shoestv.setBackgroundColor(Color.parseColor("#EBEBEB"));
                clothestv.setBackgroundColor(Color.parseColor("#555555"));

            }
        });

        shoestv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ManageProductdetailshoes();
                groceriestv.setBackgroundColor(Color.parseColor("#EBEBEB"));
                electronicstv.setBackgroundColor(Color.parseColor("#EBEBEB"));
                shoestv.setBackgroundColor(Color.parseColor("#555555"));
                clothestv.setBackgroundColor(Color.parseColor("#EBEBEB"));

            }
        });

        mContext= Manage_Productlist_Screen.this;
        reportsrecyclerview = findViewById(R.id.reportdetails_fwd_nextscreen_hdlss);

        ManageProductdetailselectronics();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.cartid).setVisible(false);
        return true;
    }
    @Override
    public void onRestart() {
        super.onRestart();
        //When BACK BUTTON is pressed, the activity on the stack is restarted
        //Do what you want on the refresh procedure here
        finish();
        startActivity(getIntent());
    }

    private void filter(String text){

         ArrayList<Productdetails_sellerid_categoryid_responseBody1> datumArrayList2=  new ArrayList<Productdetails_sellerid_categoryid_responseBody1>();;


        for(Productdetails_sellerid_categoryid_responseBody1 item: datumArrayList ){

            if(item.getProductVariantName().toLowerCase().contains(text.toLowerCase())){
                datumArrayList2.add(item);
            }
        }

        fwreportGridAdapterdatum.filterList(datumArrayList2);
    }



    private void ManageProductdetailselectronics(){

        // loginResponseBody = new LoginResponseBody();

        // String token = preferences.getString("token",);
        datumArrayList.clear();
        Call<JsonObject> call=registerInterface.getmanageproductdetails(String.valueOf(sellerid_s),"1");
        call.enqueue(new Callback<JsonObject>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("dataresponse",response.toString());
                Log.e("statusg",response.message());
//                   Log.e("statusg",response.body().toString()+"");
                // Log.e("statusg",response.body().getStatus());
                //AppUtils.dismissCustomProgress(mCustomProgressDialog);
                // Toast.makeText(Dashboard1.this, " "+response.body(), Toast.LENGTH_SHORT).show();


                if (response.body()!=null){

                    Log.e("statuse",response.body().toString());
                    if (response.body().toString().contains(AppConstant.MESSAGE7)){
                        Log.e("status",response.body().toString());
                        // AppUtils.showToast(mContext,response.body().toString());

                        try
                        {

                            JSONObject jsonObject1 = new JSONObject(response.body().toString());
                            Log.d("jsonssarr",jsonObject1.toString()+"");

                            //  JSONArray jsondata = jsonObject.getJSONArray("data");
                            // JSONObject dataobj = jsondata.getJSONObject(0);

                          /*  recoveredtv.setText(jsonObject1.getString("recovered"));
                            diseasedtv.setText(jsonObject1.getString("deceased"));
                            conformedtv.setText(jsonObject1.getString("conformed"));*/


                            JSONArray jsonArray = jsonObject1.getJSONArray("data");

                            for (int i =0;i<jsonArray.length();i++)
                            {
                                JSONObject msgobj = jsonArray.getJSONObject(i);
                                Log.d("mssgobj",msgobj.toString());

                                Productvariantname = msgobj.getString("productVariantName");
                                skunumber = msgobj.getString("sku");
                                date = msgobj.getString("mrp");
                                active = msgobj.getBoolean("isActive");
                                price = Double.valueOf(msgobj.getString("price"));
                               // mrp = msgobj.getString(msgobj.getString("mrp"));
                                productimg = msgobj.getString("productImage1");
                                piecesavailable = msgobj.getString("availabilityCount");
                                productvariantid = msgobj.getInt("productVariantId");
                                //Picasso.get().load("http://mcommerce.alpha-numero.com:8070/antsEcommerce/downloadFile/"+msgobj.getString("availabilityCount")).into(imageView);

                                Productdetails_sellerid_categoryid_responseBody1 pojo = new Productdetails_sellerid_categoryid_responseBody1(productid,productvariantid,Productvariantname,skunumber,price,piecesavailable,"","","",date,active,productcategory,productimg);
                                datumArrayList.add(pojo);

                               /* Dashboardnormal_pojo1requestbody pojo = new Dashboardnormal_pojo1requestbody(recovered_st,deceased_st,state_st,confirmed_st);
                                datumArrayList.add(pojo);*/


                                  /*  grade = msgobj.getString("Grade");
                                    date = msgobj.getString("Date_Time");
                                    noofnets = msgobj.getString("No_of_Nets_Wt");
                                    nettarewt = msgobj.getString("Net_Tare_Wt");
                                    totalweight = msgobj.getString("Total_Weight");
                                    totnetwt = msgobj.getString("Total_Net_Wt");
                                    netwt = msgobj.getString("Net_Weight");
                                    cumulativecount = msgobj.getString("Cumulative_Wt");*/



                                   // Log.d("mssgdate",date+"");

                                   /* Hdlsgdatum pojo = new Hdlsgdatum(grade,date,noofnets,nettarewt,totalweight,totnetwt,netwt,cumulativecount);
                                    datumArrayList.add(pojo);*/
                            }

                            fwreportGridAdapterdatum=new Dashboard_recyclervw_normaltable_adapter(mContext,datumArrayList);
                            Log.d("mssgobj",datumArrayList.toString());
                            reportsrecyclerview.setHasFixedSize(true);
                            reportsrecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
                            reportsrecyclerview.setAdapter(fwreportGridAdapterdatum);


                              /*  fwreportGridAdapterdatum=new HdlsgreportGridAdapterdatum(mContext,datumArrayList);
                                Log.d("mssgobj",datumArrayList.toString());
                                reportsrecyclerview.setHasFixedSize(true);
                                reportsrecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
                                reportsrecyclerview.setAdapter(fwreportGridAdapterdatum);
*/
//                                Fwreportgetlotnodatastatus jsonArray = new Fwreportgetlotnodatastatus(status,fw_Lot_no_Report);
//
//                                jsonArray.getMessage();
//                               jsonArray.getStatus();
//                                jsonArray.getMessage();
//                                jsonobject.getDate();
//                                jsonobject.getAgent();
//                                jsonobject.getLot_No();
//                                jsonobject.getFarmer();
//                                jsonobject.getLocation();
//
//                                Log.d("output", jsonobject.toString());
//                                Log.e("statusddd",response.body().toString());
//                                jsonobject.getLocation();
//
//                                String startdate = tv_selectstartdate.getText().toString();
//                                String tostring = tv_selecttodate.getText().toString();
//
//
//
//                                    //JsonArray  jarray =  new JsonArray(jsonArray.);
//
//
//
//
//

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



    private void ManageProductdetailgroceries(){

        // loginResponseBody = new LoginResponseBody();

        // String token = preferences.getString("token",);
        datumArrayList.clear();
        Call<JsonObject> call=registerInterface.getmanageproductdetails(String.valueOf(sellerid_s),"3");
        call.enqueue(new Callback<JsonObject>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("dataresponse",response.toString());
                Log.e("statusg",response.message());
//                   Log.e("statusg",response.body().toString()+"");
                // Log.e("statusg",response.body().getStatus());
                //AppUtils.dismissCustomProgress(mCustomProgressDialog);
                // Toast.makeText(Dashboard1.this, " "+response.body(), Toast.LENGTH_SHORT).show();


                if (response.body()!=null){

                    Log.e("statuse",response.body().toString());
                    if (response.body().toString().contains(AppConstant.MESSAGE7)){
                        Log.e("status",response.body().toString());
                        // AppUtils.showToast(mContext,response.body().toString());

                        try
                        {

                            JSONObject jsonObject1 = new JSONObject(response.body().toString());
                            Log.d("jsonssarr",jsonObject1.toString()+"");

                            //  JSONArray jsondata = jsonObject.getJSONArray("data");
                            // JSONObject dataobj = jsondata.getJSONObject(0);

                          /*  recoveredtv.setText(jsonObject1.getString("recovered"));
                            diseasedtv.setText(jsonObject1.getString("deceased"));
                            conformedtv.setText(jsonObject1.getString("conformed"));*/


                            JSONArray jsonArray = jsonObject1.getJSONArray("data");

                            for (int i =0;i<jsonArray.length();i++)
                            {
                                JSONObject msgobj = jsonArray.getJSONObject(i);
                                Log.d("mssgobj",msgobj.toString());

                                Productvariantname = msgobj.getString("productVariantName");
                                skunumber = msgobj.getString("sku");
                                date = msgobj.getString("mrp");
                                active = msgobj.getBoolean("isActive");
                                price = Double.valueOf(msgobj.getString("price"));
                                // mrp = msgobj.getString(msgobj.getString("mrp"));
                                productimg = msgobj.getString("productImage1");
                                piecesavailable = msgobj.getString("availabilityCount");
                                productvariantid = msgobj.getInt("productVariantId");
                                //Picasso.get().load("http://mcommerce.alpha-numero.com:8070/antsEcommerce/downloadFile/"+msgobj.getString("availabilityCount")).into(imageView);

                                Productdetails_sellerid_categoryid_responseBody1 pojo = new Productdetails_sellerid_categoryid_responseBody1(productid,productvariantid,Productvariantname,skunumber,price,piecesavailable,"","","",date,active,productcategory,productimg);
                                datumArrayList.add(pojo);

                               /* Dashboardnormal_pojo1requestbody pojo = new Dashboardnormal_pojo1requestbody(recovered_st,deceased_st,state_st,confirmed_st);
                                datumArrayList.add(pojo);*/


                                  /*  grade = msgobj.getString("Grade");
                                    date = msgobj.getString("Date_Time");
                                    noofnets = msgobj.getString("No_of_Nets_Wt");
                                    nettarewt = msgobj.getString("Net_Tare_Wt");
                                    totalweight = msgobj.getString("Total_Weight");
                                    totnetwt = msgobj.getString("Total_Net_Wt");
                                    netwt = msgobj.getString("Net_Weight");
                                    cumulativecount = msgobj.getString("Cumulative_Wt");*/



                                // Log.d("mssgdate",date+"");

                                   /* Hdlsgdatum pojo = new Hdlsgdatum(grade,date,noofnets,nettarewt,totalweight,totnetwt,netwt,cumulativecount);
                                    datumArrayList.add(pojo);*/
                            }

                            fwreportGridAdapterdatum=new Dashboard_recyclervw_normaltable_adapter(mContext,datumArrayList);
                            Log.d("mssgobj",datumArrayList.toString());
                            reportsrecyclerview.setHasFixedSize(true);
                            reportsrecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
                            reportsrecyclerview.setAdapter(fwreportGridAdapterdatum);


                              /*  fwreportGridAdapterdatum=new HdlsgreportGridAdapterdatum(mContext,datumArrayList);
                                Log.d("mssgobj",datumArrayList.toString());
                                reportsrecyclerview.setHasFixedSize(true);
                                reportsrecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
                                reportsrecyclerview.setAdapter(fwreportGridAdapterdatum);
*/
//                                Fwreportgetlotnodatastatus jsonArray = new Fwreportgetlotnodatastatus(status,fw_Lot_no_Report);
//
//                                jsonArray.getMessage();
//                               jsonArray.getStatus();
//                                jsonArray.getMessage();
//                                jsonobject.getDate();
//                                jsonobject.getAgent();
//                                jsonobject.getLot_No();
//                                jsonobject.getFarmer();
//                                jsonobject.getLocation();
//
//                                Log.d("output", jsonobject.toString());
//                                Log.e("statusddd",response.body().toString());
//                                jsonobject.getLocation();
//
//                                String startdate = tv_selectstartdate.getText().toString();
//                                String tostring = tv_selecttodate.getText().toString();
//
//
//
//                                    //JsonArray  jarray =  new JsonArray(jsonArray.);
//
//
//
//
//

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
    private void ManageProductdetailclothes(){

        // loginResponseBody = new LoginResponseBody();

        // String token = preferences.getString("token",);
        datumArrayList.clear();
        Call<JsonObject> call=registerInterface.getmanageproductdetails(String.valueOf(sellerid_s),"2");
        call.enqueue(new Callback<JsonObject>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("dataresponse",response.toString());
                Log.e("statusg",response.message());
//                   Log.e("statusg",response.body().toString()+"");
                // Log.e("statusg",response.body().getStatus());
                //AppUtils.dismissCustomProgress(mCustomProgressDialog);
                // Toast.makeText(Dashboard1.this, " "+response.body(), Toast.LENGTH_SHORT).show();


                if (response.body()!=null){

                    Log.e("statuse",response.body().toString());
                    if (response.body().toString().contains(AppConstant.MESSAGE7)){
                        Log.e("status",response.body().toString());
                        // AppUtils.showToast(mContext,response.body().toString());

                        try
                        {

                            JSONObject jsonObject1 = new JSONObject(response.body().toString());
                            Log.d("jsonssarr",jsonObject1.toString()+"");

                            //  JSONArray jsondata = jsonObject.getJSONArray("data");
                            // JSONObject dataobj = jsondata.getJSONObject(0);

                          /*  recoveredtv.setText(jsonObject1.getString("recovered"));
                            diseasedtv.setText(jsonObject1.getString("deceased"));
                            conformedtv.setText(jsonObject1.getString("conformed"));*/


                            JSONArray jsonArray = jsonObject1.getJSONArray("data");

                            for (int i =0;i<jsonArray.length();i++)
                            {
                                JSONObject msgobj = jsonArray.getJSONObject(i);
                                Log.d("mssgobj",msgobj.toString());

                                Productvariantname = msgobj.getString("productVariantName");
                                skunumber = msgobj.getString("sku");
                                date = msgobj.getString("mrp");
                                active = msgobj.getBoolean("isActive");
                                price = Double.valueOf(msgobj.getString("price"));
                                // mrp = msgobj.getString(msgobj.getString("mrp"));
                                productimg = msgobj.getString("productImage1");
                                piecesavailable = msgobj.getString("availabilityCount");
                                productvariantid = msgobj.getInt("productVariantId");
                                //Picasso.get().load("http://mcommerce.alpha-numero.com:8070/antsEcommerce/downloadFile/"+msgobj.getString("availabilityCount")).into(imageView);

                                Productdetails_sellerid_categoryid_responseBody1 pojo = new Productdetails_sellerid_categoryid_responseBody1(productid,productvariantid,Productvariantname,skunumber,price,piecesavailable,"","","",date,active,productcategory,productimg);
                                datumArrayList.add(pojo);

                               /* Dashboardnormal_pojo1requestbody pojo = new Dashboardnormal_pojo1requestbody(recovered_st,deceased_st,state_st,confirmed_st);
                                datumArrayList.add(pojo);*/


                                  /*  grade = msgobj.getString("Grade");
                                    date = msgobj.getString("Date_Time");
                                    noofnets = msgobj.getString("No_of_Nets_Wt");
                                    nettarewt = msgobj.getString("Net_Tare_Wt");
                                    totalweight = msgobj.getString("Total_Weight");
                                    totnetwt = msgobj.getString("Total_Net_Wt");
                                    netwt = msgobj.getString("Net_Weight");
                                    cumulativecount = msgobj.getString("Cumulative_Wt");*/



                                // Log.d("mssgdate",date+"");

                                   /* Hdlsgdatum pojo = new Hdlsgdatum(grade,date,noofnets,nettarewt,totalweight,totnetwt,netwt,cumulativecount);
                                    datumArrayList.add(pojo);*/
                            }

                            fwreportGridAdapterdatum=new Dashboard_recyclervw_normaltable_adapter(mContext,datumArrayList);
                            Log.d("mssgobj",datumArrayList.toString());
                            reportsrecyclerview.setHasFixedSize(true);
                            reportsrecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
                            reportsrecyclerview.setAdapter(fwreportGridAdapterdatum);


                              /*  fwreportGridAdapterdatum=new HdlsgreportGridAdapterdatum(mContext,datumArrayList);
                                Log.d("mssgobj",datumArrayList.toString());
                                reportsrecyclerview.setHasFixedSize(true);
                                reportsrecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
                                reportsrecyclerview.setAdapter(fwreportGridAdapterdatum);
*/
//                                Fwreportgetlotnodatastatus jsonArray = new Fwreportgetlotnodatastatus(status,fw_Lot_no_Report);
//
//                                jsonArray.getMessage();
//                               jsonArray.getStatus();
//                                jsonArray.getMessage();
//                                jsonobject.getDate();
//                                jsonobject.getAgent();
//                                jsonobject.getLot_No();
//                                jsonobject.getFarmer();
//                                jsonobject.getLocation();
//
//                                Log.d("output", jsonobject.toString());
//                                Log.e("statusddd",response.body().toString());
//                                jsonobject.getLocation();
//
//                                String startdate = tv_selectstartdate.getText().toString();
//                                String tostring = tv_selecttodate.getText().toString();
//
//
//
//                                    //JsonArray  jarray =  new JsonArray(jsonArray.);
//
//
//
//
//

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



    private void ManageProductdetailshoes(){

        // loginResponseBody = new LoginResponseBody();

        // String token = preferences.getString("token",);
        datumArrayList.clear();
        Call<JsonObject> call=registerInterface.getmanageproductdetails(String.valueOf(sellerid_s),"4");
        call.enqueue(new Callback<JsonObject>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("dataresponse",response.toString());
                Log.e("statusg",response.message());
//                   Log.e("statusg",response.body().toString()+"");
                // Log.e("statusg",response.body().getStatus());
                //AppUtils.dismissCustomProgress(mCustomProgressDialog);
                // Toast.makeText(Dashboard1.this, " "+response.body(), Toast.LENGTH_SHORT).show();


                if (response.body()!=null){

                    Log.e("statuse",response.body().toString());
                    if (response.body().toString().contains(AppConstant.MESSAGE7)){
                        Log.e("status",response.body().toString());
                        // AppUtils.showToast(mContext,response.body().toString());

                        try
                        {

                            JSONObject jsonObject1 = new JSONObject(response.body().toString());
                            Log.d("jsonssarr",jsonObject1.toString()+"");

                            //  JSONArray jsondata = jsonObject.getJSONArray("data");
                            // JSONObject dataobj = jsondata.getJSONObject(0);

                          /*  recoveredtv.setText(jsonObject1.getString("recovered"));
                            diseasedtv.setText(jsonObject1.getString("deceased"));
                            conformedtv.setText(jsonObject1.getString("conformed"));*/


                            JSONArray jsonArray = jsonObject1.getJSONArray("data");

                            for (int i =0;i<jsonArray.length();i++)
                            {
                                JSONObject msgobj = jsonArray.getJSONObject(i);
                                Log.d("mssgobj",msgobj.toString());

                                Productvariantname = msgobj.getString("productVariantName");
                                skunumber = msgobj.getString("sku");
                                date = msgobj.getString("mrp");
                                active = Boolean.valueOf(msgobj.getString("isActive"));
                                price = Double.valueOf(msgobj.getString("price"));
                                productimg = msgobj.getString("productImage1");
                                piecesavailable = msgobj.getString("availabilityCount");
                                //Picasso.get().load("http://mcommerce.alpha-numero.com:8070/antsEcommerce/downloadFile/"+msgobj.getString("availabilityCount")).into(imageView);

                                Productdetails_sellerid_categoryid_responseBody1 pojo = new Productdetails_sellerid_categoryid_responseBody1(productid,productvariantid,Productvariantname,skunumber,price,piecesavailable,"","","",date,active,productcategory,productimg);
                                datumArrayList.add(pojo);

                               /* Dashboardnormal_pojo1requestbody pojo = new Dashboardnormal_pojo1requestbody(recovered_st,deceased_st,state_st,confirmed_st);
                                datumArrayList.add(pojo);*/


                                  /*  grade = msgobj.getString("Grade");
                                    date = msgobj.getString("Date_Time");
                                    noofnets = msgobj.getString("No_of_Nets_Wt");
                                    nettarewt = msgobj.getString("Net_Tare_Wt");
                                    totalweight = msgobj.getString("Total_Weight");
                                    totnetwt = msgobj.getString("Total_Net_Wt");
                                    netwt = msgobj.getString("Net_Weight");
                                    cumulativecount = msgobj.getString("Cumulative_Wt");*/



                                // Log.d("mssgdate",date+"");

                                   /* Hdlsgdatum pojo = new Hdlsgdatum(grade,date,noofnets,nettarewt,totalweight,totnetwt,netwt,cumulativecount);
                                    datumArrayList.add(pojo);*/
                            }

                            fwreportGridAdapterdatum=new Dashboard_recyclervw_normaltable_adapter(mContext,datumArrayList);
                            Log.d("mssgobj",datumArrayList.toString());
                            reportsrecyclerview.setHasFixedSize(true);
                            reportsrecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
                            reportsrecyclerview.setAdapter(fwreportGridAdapterdatum);


                              /*  fwreportGridAdapterdatum=new HdlsgreportGridAdapterdatum(mContext,datumArrayList);
                                Log.d("mssgobj",datumArrayList.toString());
                                reportsrecyclerview.setHasFixedSize(true);
                                reportsrecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
                                reportsrecyclerview.setAdapter(fwreportGridAdapterdatum);
*/
//                                Fwreportgetlotnodatastatus jsonArray = new Fwreportgetlotnodatastatus(status,fw_Lot_no_Report);
//
//                                jsonArray.getMessage();
//                               jsonArray.getStatus();
//                                jsonArray.getMessage();
//                                jsonobject.getDate();
//                                jsonobject.getAgent();
//                                jsonobject.getLot_No();
//                                jsonobject.getFarmer();
//                                jsonobject.getLocation();
//
//                                Log.d("output", jsonobject.toString());
//                                Log.e("statusddd",response.body().toString());
//                                jsonobject.getLocation();
//
//                                String startdate = tv_selectstartdate.getText().toString();
//                                String tostring = tv_selecttodate.getText().toString();
//
//
//
//                                    //JsonArray  jarray =  new JsonArray(jsonArray.);
//
//
//
//
//

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
