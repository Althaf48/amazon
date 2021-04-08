package com.example.antsecommerce1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.antsecommerce1.Interface.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsEdit_Screen extends AppCompatActivity {

    String productvariantid_s,variantname_s,sku_s,price_s,availability_s,mrp_s;
    EditText variantname_ed,variantid_ed,sku_ed,mrp_ed,price_ed,availabilty_ed;
    CheckBox activecb,inactivecb;
    TextView tvupdate;
    Boolean active = true;
    Product_Detailsedit_RequestBody productDetailseditRequestBody;
    RegisterInterface registerInterface;
    AlertDialog alertDialog;
    int sellerid_s;

    ProductdetaileditResponseBody2 productdetaileditResponseBody2;
    Productdetails_ResponseBody1 productdetailsResponseBody1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details_edit__screen);
        registerInterface = ApiClient.getClient().create(RegisterInterface.class);
        sellerid_s = getIntent().getExtras().getInt("sellerid");


        productvariantid_s = getIntent().getExtras().getString("productvariantid");
        variantname_s = getIntent().getExtras().getString("VariantName");
        sku_s = getIntent().getExtras().getString("sku");
        price_s = getIntent().getExtras().getString("Price");
        availability_s = getIntent().getExtras().getString("AvailabilityCount");
        mrp_s = getIntent().getExtras().getString("mrpr");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Products");


        variantname_ed = findViewById(R.id.productdetaileditname);
        sku_ed = findViewById(R.id.productdetaileditsku);
        price_ed = findViewById(R.id.productdetaileditprice);
        availabilty_ed = findViewById(R.id.productdetaileditavailability);
        activecb = findViewById(R.id.activeproductdetails);
        inactivecb = findViewById(R.id.inactiveproductdetails);
        mrp_ed = findViewById(R.id.productdetaileditmrp);
        tvupdate = findViewById(R.id.savelisting_id);

        tvupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateproductdetailsapi();
                Toast.makeText(ProductDetailsEdit_Screen.this, "hhdsg", Toast.LENGTH_SHORT).show();
            }
        });


        activecb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(activecb.isChecked()){

                    inactivecb.setChecked(false);
                    inactivecb.setChecked(active = false);
                    activecb.setChecked(active = true);


                }


            }
        });

        inactivecb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(inactivecb.isChecked()){

                    activecb.setChecked(false);
                    inactivecb.setChecked(active = true);
                    activecb.setChecked(active = false);

                }

            }
        });

        variantname_ed.setText(variantname_s);
        sku_ed.setText(sku_s);
        price_ed.setText(price_s);
        availabilty_ed.setText(availability_s);
        //variantid_ed.setText(variantname_s);
        mrp_ed.setText(mrp_s);

        Toast.makeText(ProductDetailsEdit_Screen.this, "Id" + productvariantid_s, Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.cartid).setVisible(false);
        return true;
    }

    private void updateproductdetailsapi() {


        final int variantid = Integer.parseInt(productvariantid_s);
        final String variantname = variantname_ed.getText().toString();
        final Double price = Double.valueOf(price_ed.getText().toString());
        final String mrp =  mrp_ed.getText().toString();
        final String sku = sku_ed.getText().toString();
        final String availability = availabilty_ed.getText().toString();
        // final String states = spinner_val_edt_det.getSelectedItem().toString();

//        Log.e("date",usertypeuser);

        Product_Detailsedit_RequestBody bankdetailsseller_requestBody = new Product_Detailsedit_RequestBody();
        bankdetailsseller_requestBody.setProductVariantId(variantid);
        bankdetailsseller_requestBody.setProductVariantName(variantname);
        bankdetailsseller_requestBody.setPrice(price);
        bankdetailsseller_requestBody.setMrp(mrp);
        bankdetailsseller_requestBody.setSku(sku);
        bankdetailsseller_requestBody.setAvailabilityCount(availability);
        bankdetailsseller_requestBody.setIsActive(active);

        // Toast.makeText(Login_screen.this, usertypeuser1, Toast.LENGTH_SHORT).show();

        Call<ProductdetaileditResponseBody2> call = registerInterface.getProducteditdetails(bankdetailsseller_requestBody);
        call.enqueue(new Callback<ProductdetaileditResponseBody2>() {
            @Override
            public void onResponse(Call<ProductdetaileditResponseBody2> call, Response<ProductdetaileditResponseBody2> response) {


                Log.e("date", String.valueOf(Integer.parseInt(productvariantid_s)));
                Log.e("date",variantname_ed.getText().toString());
                Log.e("date", String.valueOf(Double.valueOf(price_ed.getText().toString())));
                Log.e("date",mrp_ed.getText().toString());
                Log.e("date",sku_ed.getText().toString());
                Log.e("date",availabilty_ed.getText().toString());
                Log.e("messagelkwdj", response.toString());
                Log.e("messagelkwdj", response.message());
                if (response.code() == 200) {

                    Toast.makeText(ProductDetailsEdit_Screen.this, "update successfully", Toast.LENGTH_SHORT).show();
                  /*  Intent i = new Intent(ProductDetailsEdit_Screen.this,Manage_Productlist_Screen.class);
                    i.putExtra("sellerid",sellerid_s);
                    startActivity(i);*/
                    alertDialog = new AlertDialog.Builder(ProductDetailsEdit_Screen.this).create();
                    alertDialog.setTitle("ANTS App");
                    alertDialog.setMessage("products Details Updated Successfully");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();



                }


            }

            @Override
            public void onFailure(Call<ProductdetaileditResponseBody2> call, Throwable t) {
                Log.i("sendingfail", "Returned empty response");
                alertDialog = new AlertDialog.Builder(ProductDetailsEdit_Screen.this).create();
                alertDialog.setTitle("ANTS App");
                alertDialog.setMessage("products Details Updated Successfully");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();

            }
        });
    }


}
