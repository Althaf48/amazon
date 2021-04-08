package com.example.antsecommerce1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.example.antsecommerce1.Interface.ApiClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;
import static com.example.antsecommerce1.Login_screen.PREFS_NAME;

public class ProfileUpdate_screen extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener,
        OnMapReadyCallback,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnMarkerClickListener,
        ResultCallback<Status>,View.OnClickListener{

    /*===profile pic start===*/

   // ImageView imageView;
    Button pickImage, upload;
    private static final int REQUEST_TAKE_PHOTO = 0;
    private static final int REQUEST_PICK_PHOTO = 2;
    private Uri mMediaUri;
    private static final int CAMERA_PIC_REQUEST = 1111;

    private static final String TAG2 = ProfileUpdate_screen.class.getSimpleName();

    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;

    public static final int MEDIA_TYPE_IMAGE = 1;

    private Uri fileUri;

    private String mediaPath;

    private Button btnCapturePicture;

    private String mImageFileLocation = "";
    public static final String IMAGE_DIRECTORY_NAME = "Android File Upload";
    ProgressDialog pDialog;
    private String postPath;


    /*====profilc end====*/





    private static final int INTENT_REQUEST_CODE = 100;
    private String mImageUrl = "";
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mtoggle;
    private Context mContext;
    Bitmap b;
    private ProgressBar mProgressBar;
    String TAG;
   // public static final String URL = "http://113.11.231.20:8070";
    private CircularImageView imageView;
    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int STORAGE_PERMISSION_CODE = 101;
    androidx.appcompat.app.AlertDialog alertDialog;
    Infocitiesandstatesresponsebody1 infocitiesandstatesresponsebody1;
    Infocitiesandstatesresponsebody2 infocitiesandstatesresponsebody2;
    EditText nameed,mobileed,emailed,latandlonged,addressed,cityed,stateed,pincodeed,shopnameed;
    String name,citys,statess,cityss;
    String mobile;
    String email;
    String lat_s;
    String long_s;
    String sellerid_s;
    String state_s;
    String shopname;
    String sellerimage;
    String sellerimg;
    TextView shopnamebtn;
    Companyname_responseBody2 companynameResponseBody2;
    Companyname_responseBody1 companynameResponseBody1;
    Profilepicnamedb_ResponseBody2 profilepicnamedb_responseBody2;
    Profilepicnamedb_ResponseBody1 profilepicnamedbResponseBody1;
    ArrayList<String> list = new ArrayList<String>();
    final ArrayList<String> list2 = new ArrayList<String>();

    //String token;

    NavigationView navigationView;
    private GoogleMap map;
    private GoogleApiClient googleApiClient;
    private Location lastLocation;
    private Button submitbtn;
    // ArrayList<String> list = new ArrayList<String>();
    ArrayList<String> listt2 = new ArrayList<String>();
    private int state_si;

    RegisterInterface registerInterface;
    Homepage_ResponseBody1 homepage_responseBody1;
    Homepageseller_Responsebody2 homepageseller_responsebody2;
    EditText spinnertextstate,spinnertextcity;
    Profiledetails_getsaveaddressresponsebody1 profiledetailsGetsaveaddressresponsebody1;
    Profiledetails_getaddresresponseBody2 profiledetailsGetaddresresponseBody2;

    Context context;

    private TextView textLat, textLong,save_tv;
    private GeofencingClient geofencingClient;
    // private MapFragment mapFragment;
    SupportMapFragment mapFragment;
    private Spinner spinner,spinner2;
    public static final String PREFS_ADDRESS = "MYAddress";
    Bitmap bmp;


    private static final String NOTIFICATION_MSG = "NOTIFICATION MSG";
    public static Intent makeNotificationIntent(Context context, String msg) {
        Intent intent = new Intent(context, Homepageseller.class);
        intent.putExtra(NOTIFICATION_MSG, msg);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update_screen);
        name = getIntent().getExtras().getString("name");
        mobile = getIntent().getExtras().getString("mobile");
        email = getIntent().getExtras().getString("email");
        sellerid_s = getIntent().getExtras().getString("sellerid");
        //shopname = getIntent().getExtras().getString("shopname");
        sellerimage = getIntent().getExtras().getString("sellerimage");


       // sellerid_s = getIntent().getExtras().getInt("sellerid");
        /*====Circleview Image start====*/
        imageView = findViewById(R.id.imagepic);
        imageView.setBorderWidth(3f);
        imageView.setShadowEnable(true);
        imageView.setShadowRadius(3f);
        imageView.setShadowColor(Color.RED);
        imageView.setShadowGravity(CircularImageView.ShadowGravity.CENTER);
        spinnertextcity =findViewById(R.id.spinnertextcity);
        spinnertextstate = findViewById(R.id.spinnertextstate);

        /*====Circleview Image end====*/

        TAG = Homepage_Customer.class.getSimpleName();
        mContext= ProfileUpdate_screen.this;
        registerInterface= ApiClient.getClient().create(RegisterInterface.class);

       // getSupportActionBar().setTitle("Profile Update");
        getSupportActionBar().setTitle("Profile Update");

        checkPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                STORAGE_PERMISSION_CODE);

        /*========*/

        nameed = findViewById(R.id.name_ed_sp);
        mobileed =findViewById(R.id.mobileno_ed_sp);
        emailed = findViewById(R.id.emailid_ed_sp);
        latandlonged = findViewById(R.id.lattandlong);
        addressed = findViewById(R.id.address_id);
        pincodeed = findViewById(R.id.pincode_id);
        save_tv = findViewById(R.id.saveupdate_id);
        spinner = findViewById(R.id.spinner_h_l_g);
        spinner2 = findViewById(R.id.spinner_h_l_VarietyName);
        shopnameed = findViewById(R.id.shopname_ed_sp);
        shopnamebtn = findViewById(R.id.shopnamed_enterbtn);




        shopnameed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shopnameed.setCursorVisible(true);
                shopnamebtn.setVisibility(View.VISIBLE);


            }
        });


        shopnamebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                shopnameAPi();
                shopnamebtn.setVisibility(View.GONE);
                shopnameed.setCursorVisible(false);

            }
        });





        fetchJSON();


        imageView.setOnClickListener(this);
       // save_tv.setOnClickListener(this);

        nameed.setText(name);
        mobileed.setText(mobile);
        emailed.setText(email);



        Log.d("status",name);
        Log.d("status",mobile);
        Log.d("status",email);
        Log.d("status", String.valueOf(sellerid_s));

        initGMaps();

        // create GoogleApiClient
        createGoogleApi();
        getAPisaveaddress();


        initDialog();

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                //spinner.setSelection(((ArrayAdapter<String>)spinner.getAdapter()).getPosition(cityss));
                state_s = parent.getSelectedItem().toString();
                //String city = list2.get(position);

                //  state_si = position;
                Log.e("state",state_s);

                if (state_s != "select state") {

                    state_s=list.get(position);

                    if (state_s!=null) {
                        Log.e("listkj",state_s);
                       // getAPisaveaddress();


                        fetchJSON2();


                    } else {
                        Log.e("listkj",state_s);
                        // AppUtils.showToast(context, getString(R.string.error_network));
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SharedPreferences settings = getSharedPreferences(PREFS_ADDRESS, 0);
//Get "hasLoggedIn" value. If the value doesn't exist yet false is returned
        boolean hasLoggedInaddress = settings.getBoolean("MYAddress", false);
        String address_sp = settings.getString("address",null);
        String state_sp = settings.getString("state",null);
        String city_sp = settings.getString("city",null);
        String pin_sp = settings.getString("pin",null);
        String lat_sp = settings.getString("lattitude",null);
        String long_sp = settings.getString("longitude",null);
        String sellerimageldb = settings.getString("sellerimage",null);
        //getAPisaveaddress();
        // String usertypeg = settings.getString("usertype1",null);
        if((hasLoggedInaddress)) {

          //  addressed.setText(address_sp);
            //spinner2.setSelection(((ArrayAdapter<String>)spinner2.getAdapter()).getPosition(jsonObject2.getString("state")));
            //spinner.setSelection(((ArrayAdapter<String>)spinner.getAdapter()).getPosition(jsonObject2.getString("city")));
           // spinner2.setSelection(((ArrayAdapter<String>)spinner2.getAdapter()).getPosition(state_sp));
            //spinner.setSelection(((ArrayAdapter<String>)spinner.getAdapter()).getPosition(city_sp));
            spinner.setSelection(((ArrayAdapter<String>)spinner.getAdapter()).getPosition(cityss));
            spinner2.setSelection(((ArrayAdapter<String>)spinner2.getAdapter()).getPosition(statess));

           // pincodeed.setText(pin_sp);
           // lat_s.equalsIgnoreCase("lattitude");
            //long_s.equalsIgnoreCase("longitude");
            //latandlonged.setText(lat_sp);

            Log.e("state_Spjh",state_sp);
            Log.e("city_spjg",city_sp);



        }
        else
        {
            Toast.makeText(ProfileUpdate_screen.this,"Enter Address",Toast.LENGTH_SHORT).show();

        }


        save_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadFile();
                if(addressed.getText().toString().trim().length()==0){
                    addressed.setError("Address is not entered");
                    addressed.requestFocus();
                }
                if(spinner.getSelectedItem().toString().trim().length()==0){
                   // spinner.setError("please select state and city");
                    spinner.requestFocus();
                }
                if(spinner2.getSelectedItem().toString().trim().length()==0){
                    //spinner2.setError("please select  city");
                    spinner2.requestFocus();
                }
              /*  if(cityed.getText().toString().trim().length()==0){
                    cityed.setError("state is not entered");
                    cityed.requestFocus();
                }*/
                if(pincodeed.getText().toString().trim().length()==0){
                    pincodeed.setError("state is not entered");
                    pincodeed.requestFocus();
                }


                else {


                    loginme();
                    uploadFile();




                    Toast.makeText(ProfileUpdate_screen.this, "save", Toast.LENGTH_SHORT).show();




                }


            }
        });


       /* imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

              // selectImage(ProfileUpdate_screen.this);



            }
        });*/



    }

    private void fetchJSON(){


        list.clear();
        list.add("select state");

        // Call<Homepageseller_Responsebody2> call = registerInterface.getHomepagesellerprofileupdate(homepageseller_requestBody);

        // RegisterInterface api = retrofit.create(SpinnerInterface.class);

        Call<JsonObject> call2 = registerInterface.getJSONString();
        // Call<String> call2 = api.getJSONString2();
        call2.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                // Log.i("Responsestring", response.body().toString());
                Log.d("dataresponse",response.toString());
                Log.e("statusg",response.message());
                //Toast.makeText(Homepageseller.this, Infocitiesandstatesresponsebody2., Toast.LENGTH_SHORT).show();
                //Toast.makeText()

                if (response.body()!=null){

                    Log.e("statuse",response.body().toString());
                    if (response.body().toString().contains(AppConstant.MESSAGE2)){
                        Log.e("status",response.body().toString());
                        // AppUtils.showToast(mContext,response.body().toString());

                        try
                        {

                            JSONObject jsonObject1 = new JSONObject(response.body().toString());
                            Log.d("jsonssarr",jsonObject1.toString()+"");

                            //  JSONArray jsondata = jsonObject.getJSONArray("data");
                            // JSONObject dataobj = jsondata.getJSONObject(0);

                           /* recoveredtv.setText(jsonObject1.getString("recovered"));
                            diseasedtv.setText(jsonObject1.getString("deceased"));
                            conformedtv.setText(jsonObject1.getString("conformed"));*/


                            //  JSONArray jsonArray = jsonObject1.getJSONArray("data");

                            JSONArray jsonArray2 = jsonObject1.getJSONArray("data");

                            Log.d("jsonssarr2",jsonArray2.toString());

                            for (int i =0;i<jsonArray2.length();i++)
                            {
                                /*JSONObject msgobj = jsonArray2.getJSONObject(i);
                                Log.d("mssgobfjgjjsj",msgobj.toString());*/
                                // cityed.setText(msgobj.getString("city"));

                               // String states = msgobj.getString("state");
                                Log.d("jsonssarr2",jsonArray2.toString());
                                // list.add("select state");
                                list.add(jsonArray2.getString(i));
                                Log.d("mssgobfjgjjsdsdj",jsonArray2.getString(i));
                              /*  Set<String> set = new HashSet<>(list);
                                list.clear();
                                list.add("select state");
                                list.addAll(set);*/







                                //Toast.makeText(Homepageseller.this, msgobj.getString("state"), Toast.LENGTH_SHORT).show();




                            }

                            ArrayAdapter<String> spinnerMenu = new ArrayAdapter<String>(ProfileUpdate_screen.this, android.R.layout.simple_list_item_1, list);
                            spinnerMenu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                            spinner2.setAdapter(spinnerMenu);



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
    private void fetchJSON2(){
        spinner.setSelection(((ArrayAdapter<String>)spinner.getAdapter()).getPosition(cityss));

        listt2.clear();
        listt2.add("select city");

        // Call<Homepageseller_Responsebody2> call = registerInterface.getHomepagesellerprofileupdate(homepageseller_requestBody);

        // RegisterInterface api = retrofit.create(SpinnerInterface.class);

        Call<JsonObject> call = registerInterface.getJSONString2(state_s);
        // Call<String> call2 = api.getJSONString2();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                // Log.i("Responsestring", response.body().toString());
                Log.d("dataresponsdaadsde",response.toString());
                Log.e("statusg",response.message());
                //Toast.makeText(Homepageseller.this, Infocitiesandstatesresponsebody2., Toast.LENGTH_SHORT).show();
                //Toast.makeText()

                if (response.body()!=null){

                    Log.e("stasastuse",response.body().toString());
                    if (response.body().toString().contains(AppConstant.MESSAGE3)){
                        Log.e("statsasus",response.body().toString());
                        // AppUtils.showToast(mContext,response.body().toString());

                        try
                        {

                            JSONObject jsonObject1 = new JSONObject(response.body().toString());
                            Log.d("jsonssksldkarr",jsonObject1.toString()+"");

                            //  JSONArray jsondata = jsonObject.getJSONArray("data");
                            // JSONObject dataobj = jsondata.getJSONObject(0);

                           /* recoveredtv.setText(jsonObject1.getString("recovered"));
                            diseasedtv.setText(jsonObject1.getString("deceased"));
                            conformedtv.setText(jsonObject1.getString("conformed"));*/


                            //  JSONArray jsonArray = jsonObject1.getJSONArray("data");

                            JSONArray jsonArray2 = jsonObject1.getJSONArray("data");





                            for (int i =0;i<jsonArray2.length();i++)
                            {
                                JSONObject msgobj = jsonArray2.getJSONObject(i);
                                Log.d("mssgskjdhobjjsj",msgobj.toString());
                                // cityed.setText(msgobj.getString("city"));

                                Log.d("mssgskjdhobjkdjahsjsj",msgobj.getString("city"));

                                // JSONArray jsonArray3 = jsonObject1.getJSONArray("city");


                                listt2.add(msgobj.getString("city"));


                                // Toast.makeText(Homepageseller.this, msgobj.getString("city"), Toast.LENGTH_SHORT).show();


                            }
                            spinner.setSelection(((ArrayAdapter<String>)spinner.getAdapter()).getPosition(cityss));
                            ArrayAdapter<String> spinnerMenu = new ArrayAdapter<String>(ProfileUpdate_screen.this, android.R.layout.simple_list_item_1, listt2);
                            spinnerMenu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                            spinner.setAdapter(spinnerMenu);
                            spinnerMenu.notifyDataSetChanged();
                            spinner.setSelection(((ArrayAdapter<String>)spinner.getAdapter()).getPosition(cityss));




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






    /*====enable permission app storage= start=*/

    // Function to check and request permission.
    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(ProfileUpdate_screen.this, permission)
                == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(ProfileUpdate_screen.this,
                    new String[] { permission },
                    requestCode);
        }
        else {
            Toast.makeText(ProfileUpdate_screen.this,
                    "Permission already granted",
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }

    // This function is called when the user accepts or decline the permission.
    // Request Code is used to check which permission called this function.
    // This request code is provided when the user is prompt for permission.

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super
                .onRequestPermissionsResult(requestCode,
                        permissions,
                        grantResults);

        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(ProfileUpdate_screen.this,
                        "Camera Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            else {
                Toast.makeText(ProfileUpdate_screen.this,
                        "Camera Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
        else if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(ProfileUpdate_screen.this,
                        "Storage Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            else {
                Toast.makeText(ProfileUpdate_screen.this,
                        "Storage Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    /*====enable permission app storage= end=*/



    private void selectImage(Context context) {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (mtoggle.onOptionsItemSelected(item)) {
// Inflate the menu; this adds item selected to the action bar if it is present.
            return true;

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public byte[] getBytes(InputStream is) throws IOException {
        ByteArrayOutputStream byteBuff = new ByteArrayOutputStream();

        int buffSize = 1024;
        byte[] buff = new byte[buffSize];

        int len = 0;
        while ((len = is.read(buff)) != -1) {
            byteBuff.write(buff, 0, len);
        }

        return byteBuff.toByteArray();
    }
    /*===Image pick from Gallery===*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:


                    break;
                case 1:
                  *//*  if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();

                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                cursor.close();
                            }
                        }

                    }*//*
                    break;
               *//* case 2:
                        if (resultCode == RESULT_OK && data != null) {

                            try {

                                InputStream is = getContentResolver().openInputStream(data.getData());

                                uploadImage(getBytes(is));

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    break;*//*
            }
        }*/
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_TAKE_PHOTO || requestCode == REQUEST_PICK_PHOTO) {
                if (data != null) {
                    // Get the Image from data
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    assert cursor != null;
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    mediaPath = cursor.getString(columnIndex);
                    // Set the Image in ImageView for Previewing the Media
                    imageView.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    cursor.close();


                    postPath = mediaPath;
                }


            }else if (requestCode == CAMERA_PIC_REQUEST){
                if (Build.VERSION.SDK_INT > 21) {

                  /*  Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    assert cursor != null;
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    mediaPath = cursor.getString(columnIndex);
                    // Set the Image in ImageView for Previewing the Media
                    imageView.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    cursor.close();
*/
                }

            }

        }

    }

    /*===Image pick from Gallery===*/





    /*========Google maps start======*/




    private void createGoogleApi() {
        Log.d(TAG, "createGoogleApi()");

        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            // startGeofence();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Call GoogleApiClient connection when starting the Activity
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Disconnect GoogleApiClient when stopping Activity
        googleApiClient.disconnect();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }



    private final int REQ_PERMISSION = 999;

    // Check for permission to access Location
    private boolean checkPermission() {
        Log.d(TAG, "checkPermission()");
        // Ask for permission if it wasn't granted yet
        return (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED);
    }

    // Asks for permission
    private void askPermission() {
        Log.d(TAG, "askPermission()");
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                REQ_PERMISSION
        );
    }



    // App cannot work without the permissions
    private void permissionsDenied() {
        Log.w(TAG, "permissionsDenied()");
        // TODO close app and warn user
    }

    // Initialize GoogleMaps
    private void initGMaps() {
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);
    }

    // Callback called when Map is ready
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG, "onMapReady()");
        map = googleMap;
        //  map.setOnMapClickListener(this);
        // map.setOnMarkerClickListener(this);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        Log.d(TAG, "onMapClick(" + latLng + ")");
        // markerForGeofence(latLng);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Log.d(TAG, "onMarkerClickListener: " + marker.getPosition());
        return false;
    }

    private LocationRequest locationRequest;
    // Defined in mili seconds.
    // This number in extremely low, and should be used only for debug
    private final int UPDATE_INTERVAL = 50000;
    private final int FASTEST_INTERVAL = 50000;

    // Start location Updates
    private void startLocationUpdates() {
        Log.i(TAG, "startLocationUpdates()");
        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL)
                .setFastestInterval(FASTEST_INTERVAL);

        if (checkPermission())
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged [" + location + "]");
        lastLocation = location;
        writeActualLocation(location);

    }

    // GoogleApiClient.ConnectionCallbacks connected
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i(TAG, "onConnected()");
        getLastKnownLocation();
        recoverGeofenceMarker();
    }

    // GoogleApiClient.ConnectionCallbacks suspended
    @Override
    public void onConnectionSuspended(int i) {
        Log.w(TAG, "onConnectionSuspended()");
    }

    // GoogleApiClient.OnConnectionFailedListener fail
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.w(TAG, "onConnectionFailed()");
    }

    // Get last known location
    private void getLastKnownLocation() {
        Log.d(TAG, "getLastKnownLocation()");
        if (checkPermission()) {
            lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            if (lastLocation != null) {
                Log.i(TAG, "LasKnown location. " +
                        "Long: " + lastLocation.getLongitude() +
                        " | Lat: " + lastLocation.getLatitude());
                writeLastLocation();
                startLocationUpdates();
            } else {
                Log.w(TAG, "No location retrieved yet");
                startLocationUpdates();
            }
        } else askPermission();
    }

    public void writeActualLocation(Location location) {
        latandlonged.setText(location.getLatitude()+","+location.getLongitude());
        // longitude_ed.setText("" + location.getLongitude());

        lat_s =(""+location.getLatitude());
        long_s = (""+location.getLongitude());

        Log.d("status",long_s);
        Log.d("status",lat_s);

        markerLocation(new LatLng(location.getLatitude(), location.getLongitude()));
        //startGeofence();
    }

    private void writeLastLocation() {
        writeActualLocation(lastLocation);
    }

    private Marker locationMarker;

    private void markerLocation(LatLng latLng) {
        Log.i(TAG, "markerLocation(" + latLng + ")");
        String title = latLng.latitude + ", " + latLng.longitude;
        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .title(title);
        if (map != null) {
            if (locationMarker != null)
                locationMarker.remove();
            locationMarker = map.addMarker(markerOptions);
            float zoom = 14f;
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
            map.animateCamera(cameraUpdate);
        }
    }


    private Marker geoFenceMarker;

    private void markerForGeofence(LatLng latLng) {
        Log.i(TAG, "markerForGeofence(" + latLng + ")");
        String title = latLng.latitude + ", " + latLng.longitude;
        // Define marker options
        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                .title(title);
        if (map != null) {
            // Remove last geoFenceMarker
            if (geoFenceMarker != null)
                geoFenceMarker.remove();

            geoFenceMarker = map.addMarker(markerOptions);

        }
    }

    // Start Geofence creation process
    private void startGeofence() {
        Log.i(TAG, "startGeofence()");
        if (geoFenceMarker != null) {
            Geofence geofence = createGeofence(geoFenceMarker.getPosition(), GEOFENCE_RADIUS);
            GeofencingRequest geofenceRequest = createGeofenceRequest(geofence);
            addGeofence(geofenceRequest);
        } else {
            Log.e(TAG, "Geofence marker is null");
        }
    }

    private static final long GEO_DURATION = 60 * 60 * 1000;
    private static final String GEOFENCE_REQ_ID = "My Geofence";
    private static final float GEOFENCE_RADIUS = 100.0f; // in meters

    // Create a Geofence
    private Geofence createGeofence(LatLng latLng, float radius) {
        Log.d(TAG, "createGeofence");
        return new Geofence.Builder()
                .setRequestId(GEOFENCE_REQ_ID)
                .setCircularRegion(latLng.latitude, latLng.longitude, radius)
                .setExpirationDuration(GEO_DURATION)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER
                        | Geofence.GEOFENCE_TRANSITION_EXIT)
                .build();
    }

    // Create a Geofence Request
    private GeofencingRequest createGeofenceRequest(Geofence geofence) {
        Log.d(TAG, "createGeofenceRequest");
        return new GeofencingRequest.Builder()
                .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
                .addGeofence(geofence)
                .build();
    }

    private PendingIntent geoFencePendingIntent;
    private final int GEOFENCE_REQ_CODE = 0;

    private PendingIntent createGeofencePendingIntent() {
        Log.d(TAG, "createGeofencePendingIntent");
        if (geoFencePendingIntent != null)
            return geoFencePendingIntent;

        Intent intent = new Intent(this, GeofenceTransitionService.class);
        return PendingIntent.getService(
                this, GEOFENCE_REQ_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    // Add the created GeofenceRequest to the device's monitoring list
    private void addGeofence(GeofencingRequest request) {
        Log.d(TAG, "addGeofence");
        if (checkPermission())
            LocationServices.GeofencingApi.addGeofences(
                    googleApiClient,
                    request,
                    createGeofencePendingIntent()
            ).setResultCallback(this);
    }
    @Override
    public void onResult(@NonNull Status status) {
        Log.i(TAG, "onResult: " + status);
        if (status.isSuccess()) {
            saveGeofence();
            drawGeofence();
        } else {
            // inform about fail
        }
    }

    // Draw Geofence circle on GoogleMap
    private Circle geoFenceLimits;

    private void drawGeofence() {
        Log.d(TAG, "drawGeofence()");

        if (geoFenceLimits != null)
            geoFenceLimits.remove();

        CircleOptions circleOptions = new CircleOptions()
                .center(geoFenceMarker.getPosition())
                .strokeColor(Color.argb(50, 70, 70, 70))
                .fillColor(Color.argb(100, 150, 150, 150))
                .radius(GEOFENCE_RADIUS);
        geoFenceLimits = map.addCircle(circleOptions);
    }

    private final String KEY_GEOFENCE_LAT = "GEOFENCE LATITUDE";
    private final String KEY_GEOFENCE_LON = "GEOFENCE LONGITUDE";

    // Saving GeoFence marker with prefs mng
    private void saveGeofence() {
        Log.d(TAG, "saveGeofence()");
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putLong(KEY_GEOFENCE_LAT, Double.doubleToRawLongBits(geoFenceMarker.getPosition().latitude));
        editor.putLong(KEY_GEOFENCE_LON, Double.doubleToRawLongBits(geoFenceMarker.getPosition().longitude));
        editor.apply();
    }

    // Recovering last Geofence marker
    private void recoverGeofenceMarker() {
        Log.d(TAG, "recoverGeofenceMarker");
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

        if (sharedPref.contains(KEY_GEOFENCE_LAT) && sharedPref.contains(KEY_GEOFENCE_LON)) {
            double lat = Double.longBitsToDouble(sharedPref.getLong(KEY_GEOFENCE_LAT, -1));
            double lon = Double.longBitsToDouble(sharedPref.getLong(KEY_GEOFENCE_LON, -1));
            LatLng latLng = new LatLng(lat, lon);
            markerForGeofence(latLng);
            drawGeofence();
        }
    }

    // Clear Geofence
    private void clearGeofence() {
        Log.d(TAG, "clearGeofence()");
        LocationServices.GeofencingApi.removeGeofences(
                googleApiClient,
                createGeofencePendingIntent()
        ).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess()) {
                    // remove drawing
                    removeGeofenceDraw();
                }
            }
        });
    }

    private void removeGeofenceDraw() {
        Log.d(TAG, "removeGeofenceDraw()");
        if (geoFenceMarker != null)
            geoFenceMarker.remove();
        if (geoFenceLimits != null)
            geoFenceLimits.remove();
    }





    /*=========Google maps end===========*/



    /*=========save btn start=======*/

    private void loginme() {

        final String selleridf = String.valueOf(sellerid_s);
        final String addressf = addressed.getText().toString();
        //final String statef = stateed.getText().toString();
        // final String cityf = cityed.getText().toString();
        final String cityf = spinner.getSelectedItem().toString();
        final String statef = spinner2.getSelectedItem().toString();
        final String pincodef = pincodeed.getText().toString();
        final String lattitudef = lat_s;
        final String longitudef =long_s;
        // final String states = spinner_val_edt_det.getSelectedItem().toString();





//        Log.e("date",usertypeuser);




        Homepageseller_RequestBody homepageseller_requestBody = new Homepageseller_RequestBody();
        homepageseller_requestBody.setSellerId(selleridf);
        homepageseller_requestBody.setAddress(addressf);
        homepageseller_requestBody.setCity(cityf);
        homepageseller_requestBody.setState(statef);
        homepageseller_requestBody.setPin(pincodef);
        homepageseller_requestBody.setLongitude(longitudef);
        homepageseller_requestBody.setLatitude(lattitudef);


        // Toast.makeText(Login_screen.this, usertypeuser1, Toast.LENGTH_SHORT).show();

        Call<Homepageseller_Responsebody2> call = registerInterface.getHomepagesellerprofileupdate(homepageseller_requestBody);
        call.enqueue(new Callback<Homepageseller_Responsebody2>() {
            @Override
            public void onResponse(Call<Homepageseller_Responsebody2> call, Response<Homepageseller_Responsebody2> response) {

                Log.e("date", String.valueOf(selleridf));
                Log.e("date",addressf);
                Log.e("date",statef);
                Log.e("date",cityf);
                Log.e("date",pincodef);
                Log.e("date",lattitudef);
                Log.e("date",longitudef);

                if (response.isSuccessful()) {
//                    String message = signupresponsebodymsg.getMessage();

                    homepageseller_responsebody2 = response.body();
                    String status = homepageseller_responsebody2.getMessage();
                    //Homepage_ResponseBody1 status2 = homepageseller_responsebody2.getData();

                    if (response.code() == 200) {



                        Toast.makeText(ProfileUpdate_screen.this, status, Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(ProfileUpdate_screen.this, Bank_Account_Details.class);
                        //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                   /* i.putExtra("name",status2.getName());
                                    i.putExtra("mobile",status2.getPhonenumber());
                                    i.putExtra("email",status2.getEmailid());*/
                        i.putExtra("name",name);
                        i.putExtra("mobile",mobile);
                        i.putExtra("email",email);
                        i.putExtra("sellerid",sellerid_s);

                        SharedPreferences settings = getSharedPreferences(PREFS_ADDRESS, 0); // 0 - for private mode
                        SharedPreferences.Editor editor = settings.edit();

                        //Set "hasLoggedIn" to true
                        editor.putBoolean("MYAddress", true);
                        editor.putString("address",addressf);
                        editor.putString("state",statef);
                        editor.putString("city",cityf);
                        editor.putString("pin",pincodef);
                        editor.putString("lattitude",lattitudef);
                        editor.putString("longitude",longitudef);
                        // editor.remove(usertypeseller1);
                        // editor.putString("usertype1",usertypeuser1);
                        // Commit the edits!
                        editor.apply();

                        startActivity(i);

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
                        Toast.makeText(ProfileUpdate_screen.this, "check", Toast.LENGTH_SHORT).show();

                    }
                    // Do awesome stuff
                    // signupresponsebodymsg = response.body();
                    // String message1 = signupresponsebodymsg.getMessage();
                    //String status = signupresponsebodymsg.getStatus();

                } else if (response.code() == 401) {

                    //Toast.makeText(Login_screen.this, "Invalid password", Toast.LENGTH_SHORT).show();
                    alertDialog = new androidx.appcompat.app.AlertDialog.Builder(ProfileUpdate_screen.this).create();
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

                    Toast.makeText(ProfileUpdate_screen.this, "Invalid UserName", Toast.LENGTH_SHORT).show();
                    alertDialog = new androidx.appcompat.app.AlertDialog.Builder(ProfileUpdate_screen.this).create();
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
            public void onFailure(Call<Homepageseller_Responsebody2> call, Throwable t) {
                Log.i("sendingfail", "Returned empty response");
                Toast.makeText(ProfileUpdate_screen.this, "connection error", Toast.LENGTH_LONG).show();

            }
        });
    }


    //signupRequestbody.setUsertype(usertypeuser);

       /* Toast.makeText(Signup_screen.this, usertypeseller, Toast.LENGTH_SHORT).show();
        Toast.makeText(Signup_screen.this, usertypeuser, Toast.LENGTH_SHORT).show();*/



    /*=========save btn end =======*/



    /*=======get API save address start=========*/


    private void getAPisaveaddress(){

       /* final ArrayList<String> list = new ArrayList<String>();
        list.clear();
        list.add("select city");*/


        Call<JsonObject> call = registerInterface.getsaveaddresgettingdetails(sellerid_s);
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
                    if (response.body().toString().contains(AppConstant.MESSAGE4)){
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
                            Log.d("savedetasdsijafls",s);

                            JSONObject jsonObject2 = new JSONObject(jsonObject1.getString("data"));
                            Log.d("savedetdsdaijafls",jsonObject2.toString());

                           // JSONArray jsonArray2 = jsonObject1.getJSONArray("data");

                            Log.d("savjaidetails",jsonObject2.getString("city"));

                             statess = jsonObject2.getString("state");
                             cityss = jsonObject2.getString("city");
                             sellerimg = jsonObject2.getString("sellerImage");
                            shopname = jsonObject2.getString("companyName");
                            Picasso.get().load("http://mcommerce.alpha-numero.com:8070/antsEcommerce/downloadFile/"+sellerimg).into(imageView);

                            addressed.setText(jsonObject2.getString("address"));
                            shopnameed.setText(shopname);
                            spinner.setSelection(((ArrayAdapter<String>)spinner.getAdapter()).getPosition(jsonObject2.getString("city")));
                            spinner2.setSelection(((ArrayAdapter<String>)spinner2.getAdapter()).getPosition(jsonObject2.getString("state")));
                           // spinner.setSelection(((ArrayAdapter<String>)spinner.getAdapter()).getPosition(jsonObject2.getString("city")));
                            pincodeed.setText(jsonObject2.getString("pin"));

                            //latandlonged.setText(jsonObject2.getString("latitude")+""+(jsonObject2.getString("longitude")));


                           // latandlonged.setText(jsonObject2.getString("longitude"));


                          //  spinner.setSelection(((ArrayAdapter<String>)spinner.getAdapter()).getPosition(jsonObject2.getString("city")));


/*
                            for (int i =0;i<jsonObject2.length();i++)
                            {
                               // JSONObject msgobj = jsonArray2.getJSONObject(i);
                                //Log.d("savedetailsjjkj",msgobj.toString());
                                // cityed.setText(msgobj.getString("city"));

                               // Log.d("savedeatilsjj",msgobj.getString("city"));

                               // addressed = msgobj.getString("address");
                                addressed.setText(jsonObject2.getString("address"));

                                // JSONArray jsonArray3 = jsonObject1.getJSONArray("city");

                            *//*    addressed.setText(msgobj.getString("address"));
                                cityed.setText(jsonObject1.getString("city"));
                                stateed.setText(jsonObject1.getString("state"));
                                pincodeed.setText(jsonObject1.getString("pin"));*//*
                               // list.add(msgobj.getString("city"));


                                // Toast.makeText(Homepageseller.this, msgobj.getString("city"), Toast.LENGTH_SHORT).show();


                            }*/

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imagepic:
                new MaterialDialog.Builder(this)
                        .title(R.string.uploadImages)
                        .items(R.array.uploadImages)
                        .itemsIds(R.array.itemIds)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                switch (which) {
                                    case 0:
                                        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                        startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO);
                                        break;
                                    case 1:
                                        dialog.dismiss();

                                        break;
                                 /*   case 2:

                                        break;*/
                                }
                            }
                        })
                        .show();
                break;

        }

    }



    /*=============get API save address end=========*/


    /*======profile pic start=======*/

    protected void initDialog() {

        pDialog = new ProgressDialog(this);
        pDialog.setMessage(getString(R.string.msg_loading));
        pDialog.setCancelable(true);
    }


    private void captureImage() {
        if (Build.VERSION.SDK_INT > 21) { //use this if Lollipop_Mr1 (API 22) or above
            Intent callCameraApplicationIntent = new Intent();
            callCameraApplicationIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

            // We give some instruction to the intent to save the image
            File photoFile = null;

            try {
                // If the createImageFile will be successful, the photo file will have the address of the file
                photoFile = createImageFile();
                // Here we call the function that will try to catch the exception made by the throw function
            } catch (IOException e) {
                Logger.getAnonymousLogger().info("Exception error in generating the file");
                e.printStackTrace();
            }
            // Here we add an extra file to the intent to put the address on to. For this purpose we use the FileProvider, declared in the AndroidManifest.
            Uri outputUri = FileProvider.getUriForFile(
                    this,
                    BuildConfig.APPLICATION_ID + ".provider",
                    photoFile);
            callCameraApplicationIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);

            // The following is a new line with a trying attempt
            callCameraApplicationIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);

            Logger.getAnonymousLogger().info("Calling the camera App by intent");

            // The following strings calls the camera app and wait for his file in return.
            startActivityForResult(callCameraApplicationIntent, CAMERA_PIC_REQUEST);
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

            // start the image capture Intent
            startActivityForResult(intent, CAMERA_PIC_REQUEST);
        }


    }

    protected void showpDialog() {

        if (!pDialog.isShowing()) pDialog.show();
    }

    protected void hidepDialog() {

        if (pDialog.isShowing()) pDialog.dismiss();
    }

    File createImageFile() throws IOException {
        Logger.getAnonymousLogger().info("Generating the image - method started");

        // Here we create a "non-collision file name", alternatively said, "an unique filename" using the "timeStamp" functionality
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmSS").format(new Date());
        String imageFileName = "IMAGE_" + timeStamp;
        // Here we specify the environment location and the exact path where we want to save the so-created file
        File storageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/photo_saving_app");
        Logger.getAnonymousLogger().info("Storage directory set");

        // Then we create the storage directory if does not exists
        if (!storageDirectory.exists()) storageDirectory.mkdir();

        // Here we create the file using a prefix, a suffix and a directory
        File image = new File(storageDirectory, imageFileName + ".jpg");
        // File image = File.createTempFile(imageFileName, ".jpg", storageDirectory);

        // Here the location is saved into the string mImageFileLocation
        Logger.getAnonymousLogger().info("File name and path set");

        mImageFileLocation = image.getAbsolutePath();
        // fileUri = Uri.parse(mImageFileLocation);
        // The file is returned to the previous intent across the camera application
        return image;
    }


    /**
     * Here we store the file url as it will be null after returning from camera
     * app
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on screen orientation
        // changes
        outState.putParcelable("file_uri", fileUri);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri");
    }



    /**
     * Receiving activity result method will be called after closing the camera
     * */

    /**
     * ------------ Helper Methods ----------------------
     * */

    /**
     * Creating file uri to store image/video
     */
    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /**
     * returning image / video
     */
    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(TAG2, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + ".jpg");
        }  else {
            return null;
        }

        return mediaFile;
    }

    // Uploading Image/Video
    private void uploadFile() {
        if (postPath == null || postPath.equals("")) {
            Toast.makeText(this, "please select an image ", Toast.LENGTH_LONG).show();
            return;
        } else {

            showpDialog();

            // Map is used to multipart the file using okhttp3.RequestBody
            Map<String, RequestBody> map = new HashMap<>();
            File file = new File(postPath);

            // Parsing any Media type file
            RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
            map.put("file\"; filename=\"" + file.getName() + "\"", requestBody);
            ApiConfig getResponse = AppConfig.getRetrofit().create(ApiConfig.class);
            Call<ProfilepicResponse> call = getResponse.upload("token",map);
            call.enqueue(new Callback<ProfilepicResponse>() {
                @Override
                public void onResponse(Call<ProfilepicResponse> call, Response<ProfilepicResponse> response) {

                /*   ProfilepicResponse p = response.body();
                   Log.d("asgdfhgf",p.getDownloadurl());*/


                String p = response.toString();
                    Log.d("asgdfhgf",p);


                    //Toast.makeText(getApplicationContext(), serverResponse2.getFileName(), Toast.LENGTH_SHORT).show();
                    if (response.isSuccessful()){


                        if (response.body() != null){
                            hidepDialog();
                            ProfilepicResponse serverResponse = response.body();
                       /*     String durl = serverResponse.getDownloadurl();
                            Log.e("downloadurl",durl);*/
                            Toast.makeText(getApplicationContext(), serverResponse.getFileName(), Toast.LENGTH_SHORT).show();





                            Log.e("downloadurl",serverResponse.getFileDownloadUri());

                           // Bitmap b;

                            final String selleridf = String.valueOf(sellerid_s);
                                final String sellerimage =  serverResponse.getFileName();




                            SharedPreferences settings = getSharedPreferences(PREFS_ADDRESS, 0); // 0 - for private mode
                            SharedPreferences.Editor editor = settings.edit();

                            //Set "hasLoggedIn" to true
                            editor.putBoolean("MYAddress", true);
                            editor.putString("sellerimage",sellerimage);
                            editor.apply();


                                Profilepicnamedb_RequestBody profilepicnamedb_requestBody = new Profilepicnamedb_RequestBody();
                            profilepicnamedb_requestBody.setSellerId(selleridf);
                            profilepicnamedb_requestBody.setSellerImage(sellerimage);

                                // Toast.makeText(Login_screen.this, usertypeuser1, Toast.LENGTH_SHORT).show();

                                Call<Profilepicnamedb_ResponseBody2> call2 = registerInterface.getprofilepidb(profilepicnamedb_requestBody);
                                call2.enqueue(new Callback<Profilepicnamedb_ResponseBody2>() {
                                    @Override
                                    public void onResponse(Call<Profilepicnamedb_ResponseBody2> call, Response<Profilepicnamedb_ResponseBody2> response) {

                                        if (response.isSuccessful()) {
//                    String message = signupresponsebodymsg.getMessage();

                                            profilepicnamedb_responseBody2 = response.body();
                                            String status = profilepicnamedb_responseBody2.getMessage();

                                            //Homepage_ResponseBody1 status2 = homepageseller_responsebody2.getData();

                                            if (response.code() == 200) {

                                                Toast.makeText(ProfileUpdate_screen.this, status, Toast.LENGTH_SHORT).show();

                                            } else {
                                                Toast.makeText(ProfileUpdate_screen.this, "check", Toast.LENGTH_SHORT).show();

                                            }
                                            // Do awesome stuff
                                            // signupresponsebodymsg = response.body();
                                            // String message1 = signupresponsebodymsg.getMessage();
                                            //String status = signupresponsebodymsg.getStatus();

                                        } else if (response.code() == 401) {

                                            //Toast.makeText(Login_screen.this, "Invalid password", Toast.LENGTH_SHORT).show();
                                            alertDialog = new androidx.appcompat.app.AlertDialog.Builder(ProfileUpdate_screen.this).create();
                                            alertDialog.setTitle("ANTS App");
                                            alertDialog.setMessage("Check2");
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

                                            Toast.makeText(ProfileUpdate_screen.this, "Invalid UserName", Toast.LENGTH_SHORT).show();
                                            alertDialog = new androidx.appcompat.app.AlertDialog.Builder(ProfileUpdate_screen.this).create();
                                            alertDialog.setTitle("ANTS App");
                                            alertDialog.setMessage("check1");
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
                                    public void onFailure(Call<Profilepicnamedb_ResponseBody2> call, Throwable t) {
                                        Log.i("sendingfail", "Returned empty response");
                                        Toast.makeText(ProfileUpdate_screen.this, "connection error", Toast.LENGTH_LONG).show();

                                    }
                                });








                        }

                    }else {
                        hidepDialog();
                        Toast.makeText(getApplicationContext(), "problem uploading image", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ProfilepicResponse> call, Throwable t) {
                    hidepDialog();
                   // Log.v("Response gotten is", t.getMessage());
                }
            });
        }
    }

    /*======profile pic end ======*/



    /*===Company name API start======*/

    private void shopnameAPi() {

        final String selleridf = String.valueOf(sellerid_s);
        final String accounholdername = shopnameed.getText().toString();
        // final String states = spinner_val_edt_det.getSelectedItem().toString();

//        Log.e("date",usertypeuser);

        Companyname_RequestBody companynameRequestBody = new Companyname_RequestBody();
        companynameRequestBody.setSellerId(selleridf);
        companynameRequestBody.setCompanyName(accounholdername);





        // Toast.makeText(Login_screen.this, usertypeuser1, Toast.LENGTH_SHORT).show();

        Call<Companyname_responseBody2> call = registerInterface.getCompanyname(companynameRequestBody);
        call.enqueue(new Callback<Companyname_responseBody2>() {
            @Override
            public void onResponse(Call<Companyname_responseBody2> call, Response<Companyname_responseBody2> response) {

                if (response.isSuccessful()) {
//                    String message = signupresponsebodymsg.getMessage();

                    companynameResponseBody2 = response.body();
                    String status = companynameResponseBody2.getMessage();
                    //Homepage_ResponseBody1 status2 = homepageseller_responsebody2.getData();

                    if (response.code() == 200) {

                         Toast.makeText(ProfileUpdate_screen.this, status, Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(ProfileUpdate_screen.this, "check", Toast.LENGTH_SHORT).show();

                    }
                    // Do awesome stuff
                    // signupresponsebodymsg = response.body();
                    // String message1 = signupresponsebodymsg.getMessage();
                    //String status = signupresponsebodymsg.getStatus();

                } else if (response.code() == 401) {

                    //Toast.makeText(Login_screen.this, "Invalid password", Toast.LENGTH_SHORT).show();
                    alertDialog = new androidx.appcompat.app.AlertDialog.Builder(ProfileUpdate_screen.this).create();
                    alertDialog.setTitle("ANTS App");
                    alertDialog.setMessage("Check2");
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

                    Toast.makeText(ProfileUpdate_screen.this, "Invalid UserName", Toast.LENGTH_SHORT).show();
                    alertDialog = new androidx.appcompat.app.AlertDialog.Builder(ProfileUpdate_screen.this).create();
                    alertDialog.setTitle("ANTS App");
                    alertDialog.setMessage("check1");
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
            public void onFailure(Call<Companyname_responseBody2> call, Throwable t) {
                Log.i("sendingfail", "Returned empty response");
                Toast.makeText(ProfileUpdate_screen.this, "connection error", Toast.LENGTH_LONG).show();

            }
        });
    }


    /*=====Company name end=======*/



    /*====image retrieve start====*/

   /* public Bitmap DownloadFullFromUrl(String imageFullURL) {
        Bitmap bm = null;
        try {
            URL url = new URL(imageFullURL);
            URLConnection ucon = url.openConnection();
            InputStream is = ucon.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayBuffer baf = new ByteArrayBuffer(50);
            int current = 0;
            while ((current = bis.read()) != -1) {
                baf.append((byte) current);
            }
            bm = BitmapFactory.decodeByteArray(baf.toByteArray(), 0,
                    baf.toByteArray().length);
        } catch (IOException e) {
            Log.d("ImageManager", "Error: " + e);
        }
        return bm;
    }*/

    /*=====image retrieve end=====*/

}
