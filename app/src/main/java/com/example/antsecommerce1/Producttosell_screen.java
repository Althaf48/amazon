package com.example.antsecommerce1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.antsecommerce1.Interface.ApiClient;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Producttosell_screen extends AppCompatActivity implements View.OnClickListener{


    TextView browsetv,browsetexttv,savetv;

    // ImageView imageView;
    Button pickImage, upload;
    private static final int REQUEST_TAKE_PHOTO = 0;
    private static final int REQUEST_PICK_PHOTO = 2;
    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int STORAGE_PERMISSION_CODE = 101;
    String XLS = "XLS";
    String XLSX = "XLSX";
    String CSV = "CSV";
    RegisterInterface registerInterface;
    androidx.appcompat.app.AlertDialog alertDialog;
    private static final List<String> validKeys = Arrays.asList("sellerId", "file" /* ... */);
    private Uri mMediaUri;
    private static final int CAMERA_PIC_REQUEST = 1111;

    private static final String TAG2 = ProfileUpdate_screen.class.getSimpleName();

    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;

    public static final int MEDIA_TYPE_IMAGE = 1;

    private Uri fileUri;

    private String mediaPath;

    private Button btnCapturePicture;
    Spinner spinnerselectfile;

    private String mImageFileLocation = "";
    public static final String IMAGE_DIRECTORY_NAME = "Android File Upload";
    ProgressDialog pDialog;
    private String postPath;
    String electronicsxlsx = "Seller_Electronics_template.xlsx";
    String groceriesxlsx = "Seller_Grocery_template.xlsx";
    String clothesxlsx = "Seller_Clothing_Template.xlsx";
    String shoesxlsx = "Seller_Shoes_Template.xlsx";
    CardView addproductcv,manageproductcv,uploadproductcv,sellerproductcv;
    LinearLayout lin_addproductcv,lin_manageproductcv,lin_uploadproductcv,lin_sellerproductcv;
    CheckBox electronicscb,groceriescb,clothescb,shoescb,electronicsaddproductcb,clothingaddproductcb,groceriesaddproductcb,shoesaddproductcb;
    TextView downlaodxlsxtv,addproductbtn;
    int sellerid_s;
    DownloadManager downloadManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producttosell_screen);
       // getSupportActionBar().hide();
        registerInterface = ApiClient.getClient().create(RegisterInterface.class);

        sellerid_s = getIntent().getExtras().getInt("sellerid");
       // Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Your Products");
        browsetv = findViewById(R.id.browseid);
        browsetexttv = findViewById(R.id.browsetex_id);
        savetv = findViewById(R.id.saveproducttosell_id);
        spinnerselectfile = findViewById(R.id.selectfilespinners);
        addproductcv = findViewById(R.id.add_prodid_cv);
        manageproductcv = findViewById(R.id.manage_prodid_cv);
        uploadproductcv = findViewById(R.id.uploadproduct_cv);
        sellerproductcv = findViewById(R.id.sellertemplate_cv);
        lin_addproductcv =findViewById(R.id.lin_addproduct);
        lin_uploadproductcv = findViewById(R.id.lin_Uploadproducts);
        lin_sellerproductcv = findViewById(R.id.lin_producttemplates);
        electronicscb = findViewById(R.id.electrinicsxlsxid);
        groceriescb = findViewById(R.id.groceriesxlsxid);
        clothescb = findViewById(R.id.clothesxlsxid);
        shoescb = findViewById(R.id.shoesxlsxid);
        downlaodxlsxtv = findViewById(R.id.download_tvid);
        electronicsaddproductcb = findViewById(R.id.electronicsaddproductcbid);
        groceriesaddproductcb = findViewById(R.id.groceriesaddproductcbid);
        clothingaddproductcb = findViewById(R.id.clothingaddproductcbid);
        shoesaddproductcb = findViewById(R.id.shoesaddproductcbid);
        addproductbtn = findViewById(R.id.addproducttv_id);



        addproductbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(groceriesaddproductcb.isChecked()){

                    Toast.makeText(Producttosell_screen.this, "super", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Producttosell_screen.this,AddproductGrocery_screen.class);
                    startActivity(i);


                }else{

                    Toast.makeText(Producttosell_screen.this, "check", Toast.LENGTH_SHORT).show();
                }



            }
        });








        addproductcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lin_addproductcv.setVisibility(View.VISIBLE);
                lin_uploadproductcv.setVisibility(View.GONE);
                lin_sellerproductcv.setVisibility(View.GONE);
                addproductcv.setCardBackgroundColor(Color.parseColor("#555555"));
                manageproductcv.setCardBackgroundColor(Color.parseColor("#C53D11"));
                sellerproductcv.setCardBackgroundColor(Color.parseColor("#C53D11"));
                uploadproductcv.setCardBackgroundColor(Color.parseColor("#C53D11"));



            }
        });
        manageproductcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addproductcv.setCardBackgroundColor(Color.parseColor("#C53D11"));
                manageproductcv.setCardBackgroundColor(Color.parseColor("#555555"));
                sellerproductcv.setCardBackgroundColor(Color.parseColor("#C53D11"));
                uploadproductcv.setCardBackgroundColor(Color.parseColor("#C53D11"));

            }
        });

        uploadproductcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lin_addproductcv.setVisibility(View.GONE);
                lin_uploadproductcv.setVisibility(View.VISIBLE);
                lin_sellerproductcv.setVisibility(View.GONE);
                addproductcv.setCardBackgroundColor(Color.parseColor("#C53D11"));
                manageproductcv.setCardBackgroundColor(Color.parseColor("#C53D11"));
                sellerproductcv.setCardBackgroundColor(Color.parseColor("#C53D11"));
                uploadproductcv.setCardBackgroundColor(Color.parseColor("#555555"));



            }
        });
        sellerproductcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lin_addproductcv.setVisibility(View.GONE);
                lin_uploadproductcv.setVisibility(View.GONE);
                lin_sellerproductcv.setVisibility(View.VISIBLE);
                addproductcv.setCardBackgroundColor(Color.parseColor("#C53D11"));
                manageproductcv.setCardBackgroundColor(Color.parseColor("#C53D11"));
                sellerproductcv.setCardBackgroundColor(Color.parseColor("#555555"));
                uploadproductcv.setCardBackgroundColor(Color.parseColor("#C53D11"));


            }
        });

        manageproductcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lin_addproductcv.setVisibility(View.GONE);
                lin_uploadproductcv.setVisibility(View.GONE);
                lin_sellerproductcv.setVisibility(View.GONE);
                addproductcv.setCardBackgroundColor(Color.parseColor("#C53D11"));
                manageproductcv.setCardBackgroundColor(Color.parseColor("#555555"));
                sellerproductcv.setCardBackgroundColor(Color.parseColor("#C53D11"));
                uploadproductcv.setCardBackgroundColor(Color.parseColor("#C53D11"));

                Intent i = new Intent(Producttosell_screen.this,Manage_Productlist_Screen.class);
                i.putExtra("sellerid",sellerid_s);
                startActivity(i);


            }
        });

        electronicscb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(electronicscb.isChecked()){

                    groceriescb.setChecked(false);
                    clothescb.setChecked(false);
                    shoescb.setChecked(false);

                }


            }
        });
        groceriescb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(groceriescb.isChecked()){

                    electronicscb.setChecked(false);
                    clothescb.setChecked(false);
                    shoescb.setChecked(false);

                }


            }
        });

        clothescb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(clothescb.isChecked()){

                    electronicscb.setChecked(false);
                    groceriescb.setChecked(false);
                    shoescb.setChecked(false);

                }


            }
        });

        shoescb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(shoescb.isChecked()){

                    electronicscb.setChecked(false);
                    groceriescb.setChecked(false);
                    clothescb.setChecked(false);

                }
            }
        });

        groceriesaddproductcb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(groceriesaddproductcb.isChecked()){

                    electronicsaddproductcb.setChecked(false);
                    clothingaddproductcb.setChecked(false);
                    shoesaddproductcb.setChecked(false);

                }


            }
        });
        electronicsaddproductcb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(electronicsaddproductcb.isChecked()){

                    groceriesaddproductcb.setChecked(false);
                    clothingaddproductcb.setChecked(false);
                    shoesaddproductcb.setChecked(false);

                }


            }
        });
        clothingaddproductcb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(clothingaddproductcb.isChecked()){

                    groceriesaddproductcb.setChecked(false);
                    electronicsaddproductcb.setChecked(false);
                    shoesaddproductcb.setChecked(false);

                }


            }
        });
        shoesaddproductcb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(shoesaddproductcb.isChecked()){

                    groceriesaddproductcb.setChecked(false);
                    electronicsaddproductcb.setChecked(false);
                    clothingaddproductcb.setChecked(false);

                }


            }
        });


        downlaodxlsxtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //getAPibankdetails();

                Toast.makeText(Producttosell_screen.this, "download", Toast.LENGTH_SHORT).show();

                if (clothescb.isChecked()) {


                    downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                    Uri uri = Uri.parse("http://mcommerce.alpha-numero.com:8070/antsEcommerce/downloadProductFile/Seller_Clothing_Template.xlsx");
                    DownloadManager.Request request = new DownloadManager.Request(uri);
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    Long reference = downloadManager.enqueue(request);
                }
                if (groceriescb.isChecked()) {


                    downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                    Uri uri = Uri.parse("http://mcommerce.alpha-numero.com:8070/antsEcommerce/downloadProductFile/Seller_Grocery_template.xlsx");
                    DownloadManager.Request request = new DownloadManager.Request(uri);
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    Long reference = downloadManager.enqueue(request);
                }
                if (electronicscb.isChecked()) {


                    downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                    Uri uri = Uri.parse("http://mcommerce.alpha-numero.com:8070/antsEcommerce/downloadProductFile/Seller_Electronics_template.xlsx");
                    DownloadManager.Request request = new DownloadManager.Request(uri);
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    Long reference = downloadManager.enqueue(request);
                }
                if (shoescb.isChecked()) {


                    downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                    Uri uri = Uri.parse("http://mcommerce.alpha-numero.com:8070/antsEcommerce/downloadProductFile/Seller_Shoes_Template.xlsx");
                    DownloadManager.Request request = new DownloadManager.Request(uri);
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    Long reference = downloadManager.enqueue(request);
                }

            }
        });








       // spinnerselectfile.getSelectedItem().toString();

        browsetv.setOnClickListener(this);


        checkPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                STORAGE_PERMISSION_CODE);


        initDialog();
        savetv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadFile();

            }
        });




    }

    /*====enable permission app storage= start=*/

    // Function to check and request permission.
    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(Producttosell_screen.this, permission)
                == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(Producttosell_screen.this,
                    new String[] { permission },
                    requestCode);
        }
        else {
            Toast.makeText(Producttosell_screen.this,
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
                Toast.makeText(Producttosell_screen.this,
                        "Camera Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            else {
                Toast.makeText(Producttosell_screen.this,
                        "Camera Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
        else if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(Producttosell_screen.this,
                        "Storage Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            else {
                Toast.makeText(Producttosell_screen.this,
                        "Storage Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    /*====enable permission app storage= end=*/

    private void getAPibankdetails(){

       /* final ArrayList<String> list = new ArrayList<String>();
        list.clear();
        list.add("select city");*/
        Call<ResponseBody> call = registerInterface.getelectronicsxlsx("Seller_Electronics_template.xlsx");
        // Call<String> call2 = api.getJSONString2();
      //  Call<ResponseBody> call = downloadService.downloadFileWithDynamicUrlSync(fileUrl);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                    Log.d(TAG2, "server contacted and has file");
                    Log.d("aksjdh",response.toString());

                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            boolean writtenToDisk = writeResponseBodyToDisk(Producttosell_screen.this, response.body(), null);

                            Log.d(TAG2, "file download was a success? " + writtenToDisk);
                            return null;
                        }
                    }.execute();
                } else {
                    Log.d(TAG2, "server contact failed");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG2, "error");
            }
        });

 /*       if(groceriescb.isChecked()) {

            Call<JsonObject> call = registerInterface.getelectronicsxlsx(groceriesxlsx);
            // Call<String> call2 = api.getJSONString2();
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    // Log.i("Responsestring", response.body().toString());
                    Log.d("dataresponssavedetails", response.toString());
                    Log.e("statussave details", response.message());
                    //Toast.makeText(Homepageseller.this, Infocitiesandstatesresponsebody2., Toast.LENGTH_SHORT).show();
                    //Toast.makeText()

                    if (response.body() != null) {

                        Log.e("statusavedetails", response.body().toString());


                    } else {

                        //Log.e("statuse",response.body().toString());
                        Log.e("statuse", response.toString());

                    }

                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {

                }
            });
        }
        if(clothescb.isChecked()) {

            Call<JsonObject> call = registerInterface.getelectronicsxlsx(clothesxlsx);
            // Call<String> call2 = api.getJSONString2();
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    // Log.i("Responsestring", response.body().toString());
                    Log.d("dataresponssavedetails", response.toString());
                    Log.e("statussave details", response.message());
                    //Toast.makeText(Homepageseller.this, Infocitiesandstatesresponsebody2., Toast.LENGTH_SHORT).show();
                    //Toast.makeText()

                    if (response.body() != null) {

                        Log.e("statusavedetails", response.body().toString());


                    } else {

                        //Log.e("statuse",response.body().toString());
                        Log.e("statuse", response.toString());

                    }

                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {

                }
            });
        }
        if(shoescb.isChecked()) {

            Call<JsonObject> call = registerInterface.getelectronicsxlsx(shoesxlsx);
            // Call<String> call2 = api.getJSONString2();
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    // Log.i("Responsestring", response.body().toString());
                    Log.d("dataresponssavedetails", response.toString());
                    Log.e("statussave details", response.message());
                    //Toast.makeText(Homepageseller.this, Infocitiesandstatesresponsebody2., Toast.LENGTH_SHORT).show();
                    //Toast.makeText()

                    if (response.body() != null) {

                        Log.e("statusavedetails", response.body().toString());


                    } else {

                        //Log.e("statuse",response.body().toString());
                        Log.e("statuse", response.toString());

                    }

                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {

                }
            });
        }*/
    }



    private boolean writeResponseBodyToDisk(Producttosell_screen producttosell_screen, ResponseBody responseBody, ResponseBody body) {
        try {
            // todo change the file location/name according to your needs
           // File futureStudioIconFile = new File(getExternalFilesDir(null) + File.separator + "Future Studio Icon.png");
            File futureStudioIconFile = new File(Environment.getDataDirectory()+"/Seller_Electronics_template.xlsx/");

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.d(TAG2, "file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        menu.findItem(R.id.cartid).setVisible(false);
        return true;
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
       // int id = item.getItemId();
        switch (item.getItemId()) {

            case R.id.cartid:

                //Your Code....

                item.setVisible(false);
                item.setEnabled(false);
                break;
        }
        return super.onOptionsItemSelected(item);
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_TAKE_PHOTO || requestCode == REQUEST_PICK_PHOTO) {
                if (data != null) {
                    // Get the Image from data
                  //  Uri selectedImage = data.getData();
                   // String[] filePathColumn = {MediaStore.Images.Media.DATA};

                   /* Uri fileuri = data.getData();
                    String   docFilePath = getFileNameByUri(this, fileuri);
                  *//*  Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    assert cursor != null;
                    cursor.moveToFirst();*//*

                    // Set the Image in ImageView for Previewing the Media
                   // imageView.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    browsetexttv.setText(docFilePath);
                   // cursor.close();


                    postPath = docFilePath;*/

                    /*=======*/
                   // Uri uploadfileuri = data.getData();
                   // File file = new File(uploadfileuri.getPath());

                    /*=======*/

                    Uri selectedImage = data.getData();
                    String s = selectedImage.getPath();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    assert cursor != null;
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    mediaPath = cursor.getString(columnIndex);
                   //  Set the Image in ImageView for Previewing the Media
                    browsetexttv.setText(mediaPath);;
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



    private String getFileNameByUri(Context context, Uri uri) {
        String filepath = "";//default fileName
        File file;
        if (uri.getScheme().toString().compareTo("content") == 0) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{android.provider.MediaStore.Images.ImageColumns.DATA, MediaStore.Images.Media.ORIENTATION}, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

            cursor.moveToFirst();

            String mImagePath = cursor.getString(column_index);
            cursor.close();
            filepath = mImagePath;

        } else if (uri.getScheme().compareTo("file") == 0) {
            try {
                file = new File(new URI(uri.toString()));
                if (file.exists())
                    filepath = file.getAbsolutePath();

            } catch (URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            filepath = uri.getPath();
        }
        return filepath;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.browseid:
                new MaterialDialog.Builder(this)
                        .title(R.string.uploadfile)
                        .items(R.array.uploadFile)
                        .itemsIds(R.array.itemIds)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                switch (which) {


                                    case 0:
                                        final  String selectfiles = spinnerselectfile.getSelectedItem().toString();

                                        if (selectfiles.equalsIgnoreCase("XLS")) {

                                            Intent mRequestFileIntent = new Intent(Intent.ACTION_GET_CONTENT);
                                            mRequestFileIntent.setType("application/vnd.ms-excel");
                                            startActivityForResult(mRequestFileIntent, 0);

                                        } else if (selectfiles.equalsIgnoreCase("XLSX")) {

                                            Intent mRequestFileIntent = new Intent(Intent.ACTION_GET_CONTENT);
                                            mRequestFileIntent.setType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                                            startActivityForResult(mRequestFileIntent, 0);

                                        }
                                        else {
                                            Toast.makeText(Producttosell_screen.this, "Please select file", Toast.LENGTH_SHORT).show();

                                        }



                                       /* String[] mimeTypes =
                                                {"application/vnd.ms-excel","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"};
                                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                                        intent.setType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                                       // intent.addCategory(Intent.CATEGORY_OPENABLE);
                                       *//* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                            intent.setType(mimeTypes.length == 1 ? mimeTypes[0] : "application/vnd.ms-excel\",\"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                                            if (mimeTypes.length > 0) {
                                                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
                                            }
                                        } else {
                                            String mimeTypesStr = "";
                                            for (String mimeType : mimeTypes) {
                                                mimeTypesStr += mimeType + "|";
                                            }
                                            intent.setType(mimeTypesStr.substring(0,mimeTypesStr.length() - 1));
                                        }*//*
                                        startActivityForResult(Intent.createChooser(intent,"ChooseFile"), REQUEST_PICK_PHOTO);*/
                                       // Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
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

    protected void initDialog() {

        pDialog = new ProgressDialog(this);
        pDialog.setMessage(getString(R.string.msg_loading));
        pDialog.setCancelable(true);
    }
    protected void showpDialog() {

       if (!pDialog.isShowing()) pDialog.show();
    }

    protected void hidepDialog() {

        if (pDialog.isShowing()) pDialog.dismiss();
    }

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



    private void uploadFile() {
        if (postPath == null || postPath.equals("")) {
            Toast.makeText(this, "please select a file ", Toast.LENGTH_LONG).show();
            return;
        } else {

            showpDialog();

            // Map is used to multipart the file using okhttp3.RequestBody
            Map<String, RequestBody> map = new HashMap<>();



            File file = new File(postPath);

         /*   FileuploadDoc_RequestBody fileuploadDocRequestBody = new FileuploadDoc_RequestBody();
           // fileuploadDocRequestBody.setSellerId(sellerid_s);
            fileuploadDocRequestBody.setFile(postPath);*/
            String sid = String.valueOf(sellerid_s);

            // Parsing any Media type file
            RequestBody requestBody = RequestBody.create(MediaType.parse("\"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet\""), file);
            RequestBody requestBody2 = RequestBody.create(MediaType.parse("*/*"), sid);

            map.put("sellerId",requestBody2);
           // map.put("file",RequestBody.create(MediaType.parse("\"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet\""), postPath));
           // map.put("sellerId\"; filename=\"" + file.getName() + "\"",requestBody2);
            map.put("file\"; filename=\"" + file.getName() + "\"",requestBody);
            ApiConfig getResponse = AppConfig.getRetrofit().create(ApiConfig.class);
            Call<FileuploadDoc_ResponseBody> call = getResponse.uploaddoc("token",map);
            call.enqueue(new Callback<FileuploadDoc_ResponseBody>() {
                @Override
                public void onResponse(Call<FileuploadDoc_ResponseBody> call, Response<FileuploadDoc_ResponseBody> response) {

                /*   ProfilepicResponse p = response.body();
                   Log.d("asgdfhgf",p.getDownloadurl());*/


                    String p = response.toString();
                    Log.d("asgdfhgf",p);


                    //Toast.makeText(getApplicationContext(), serverResponse2.getFileName(), Toast.LENGTH_SHORT).show();
                    if (response.isSuccessful()){


                        if (response.body() != null){
                            hidepDialog();
                            FileuploadDoc_ResponseBody serverResponse = response.body();
                       /*     String durl = serverResponse.getDownloadurl();
                            Log.e("downloadurl",durl);*/
                            Toast.makeText(getApplicationContext(), serverResponse.getFileName(), Toast.LENGTH_SHORT).show();
                            alertDialog = new androidx.appcompat.app.AlertDialog.Builder(Producttosell_screen.this).create();
                            alertDialog.setTitle("Upload Complete Successfull");
                            alertDialog.setMessage("Congrats! Your upload succesfully done. Check your email for a upload confirmation.");
                            alertDialog.setButton(androidx.appcompat.app.AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();

                            Log.e("downloadurl",serverResponse.getFileName());

                            // Bitmap b;
                        }

                    }else {
                        hidepDialog();
                        Toast.makeText(getApplicationContext(), "problem uploading file", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<FileuploadDoc_ResponseBody> call, Throwable t) {
                    hidepDialog();
                    // Log.v("Response gotten is", t.getMessage());
                }
            });
        }
    }

}
