package com.example.antsecommerce1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
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
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.gson.JsonObject;
import com.mikhaellopez.circularimageview.CircularImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.example.antsecommerce1.Login_screen.PREFS_NAME;

public class Homepageseller extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener/*, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener,
        OnMapReadyCallback,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnMarkerClickListener,
        ResultCallback<Status>*/ {

    //private static final String TAG = MainActivity.class.getSimpleName();
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mtoggle;
    private Context mContext;
    String TAG;
    private CircularImageView imageView;
    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int STORAGE_PERMISSION_CODE = 101;
    androidx.appcompat.app.AlertDialog alertDialog;
    Infocitiesandstatesresponsebody1 infocitiesandstatesresponsebody1;
    Infocitiesandstatesresponsebody2 infocitiesandstatesresponsebody2;
    EditText nameed,mobileed,emailed,latandlonged,addressed,cityed,stateed,pincodeed;
    String name;
    String mobile;
    String email;
    String shopname;
    String sellerimage;
    String lat_s;
    String long_s;
    int sellerid_s;
    String state_s;
    ArrayList<String> list = new ArrayList<String>();
    TextView tvsave,test1;

    //String token;

    NavigationView navigationView;



    private GoogleMap map;
    private GoogleApiClient googleApiClient;
    private Location lastLocation;
    private Button submitbtn;
   // ArrayList<String> list = new ArrayList<String>();
    ArrayList<String> list2 = new ArrayList<String>();
    private int state_si;

    RegisterInterface registerInterface;
    Homepage_ResponseBody1 homepage_responseBody1;
    Homepageseller_Responsebody2 homepageseller_responsebody2;

    Context context;

    private TextView textLat, textLong,save_tv;
    private GeofencingClient geofencingClient;
    // private MapFragment mapFragment;
    SupportMapFragment mapFragment;
    private Spinner spinner,spinner2;


    private static final String NOTIFICATION_MSG = "NOTIFICATION MSG";

    public static Intent makeNotificationIntent(Context context, String msg) {
        Intent intent = new Intent(context, Homepageseller.class);
        intent.putExtra(NOTIFICATION_MSG, msg);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepageseller);
        name = getIntent().getExtras().getString("name");
        mobile = getIntent().getExtras().getString("mobile");
        email = getIntent().getExtras().getString("email");
        shopname = getIntent().getExtras().getString("shopname");
        sellerimage = getIntent().getExtras().getString("sellerimage");
        sellerid_s = getIntent().getExtras().getInt("sellerid");
        tvsave = findViewById(R.id.savelisting_id);
        test1 = findViewById(R.id.signnewsctv);
        /*====Circleview Image start====*/
      /*  imageView = findViewById(R.id.imagepic);
        imageView.setBorderWidth(3f);
        imageView.setShadowEnable(true);
        imageView.setShadowRadius(3f);
        imageView.setShadowColor(Color.RED);
        imageView.setShadowGravity(CircularImageView.ShadowGravity.CENTER);*/
        /*====Circleview Image end====*/

        TAG = Homepage_Customer.class.getSimpleName();
        mContext= Homepageseller.this;

        navigationView = (NavigationView) findViewById(R.id.nav_view1);
        // NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.nav_header_main, null, false);
        TextView navUsername = (TextView) headerView.findViewById(R.id.usernamenavigation);
        navUsername.setText(name);

        registerInterface= ApiClient.getClient().create(RegisterInterface.class);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
        navigationView.setItemIconTintList(null);
        //imageView = (ImageView) findViewById(R.id.imagepic);
        drawerLayout = findViewById(R.id.drawer_layout1);
        mtoggle = new ActionBarDrawerToggle(Homepageseller.this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(mtoggle);
       // drawerLayout.addView(headerView, 0);
        mtoggle.syncState();
        //registercv = findViewById(R.id.register_cv);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Product Files");


        Log.d("status",name);
        Log.d("status",mobile);
        Log.d("status",email);
        Log.d("status", String.valueOf(sellerid_s));




        tvsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Homepageseller.this,Producttosell_screen.class);
                i.putExtra("sellerid",sellerid_s);
                startActivity(i);
            }
        });

        test1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Homepageseller.this,Ui_screens_activity.class);
                i.putExtra("sellerid",sellerid_s);
                startActivity(i);
            }
        });






    }




    /*====enable permission app storage= start=*/

    // Function to check and request permission.
    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(Homepageseller.this, permission)
                == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(Homepageseller.this,
                    new String[] { permission },
                    requestCode);
        }
        else {
            Toast.makeText(Homepageseller.this,
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
                Toast.makeText(Homepageseller.this,
                        "Camera Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            else {
                Toast.makeText(Homepageseller.this,
                        "Camera Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
        else if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(Homepageseller.this,
                        "Storage Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            else {
                Toast.makeText(Homepageseller.this,
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.nav_home3:
                // do you click actions for the first selection
              /*  final String sellerids1 = String.valueOf(sellerid_s);

                Intent i = new Intent(Homepageseller.this, Homepageseller.class);
                i.putExtra("name",name);
                i.putExtra("mobile",mobile);
                i.putExtra("email",email);
                i.putExtra("sellerid",sellerids1);
                i.putExtra("shopname",shopname);
                i.putExtra("sellerimage",sellerimage);
                startActivity(i);*/
                drawerLayout.closeDrawers();

                Toast.makeText(Homepageseller.this,"Homepage",Toast.LENGTH_LONG).show();

                break;


            case R.id.nav_home2:
                // do you click actions for the first selection
               final String sellerids = String.valueOf(sellerid_s);

                Intent i5 = new Intent(Homepageseller.this, ProfileUpdate_screen.class);
                i5.putExtra("name",name);
                i5.putExtra("mobile",mobile);
                i5.putExtra("email",email);
                i5.putExtra("sellerid",sellerids);
                i5.putExtra("shopname",shopname);
                i5.putExtra("sellerimage",sellerimage);
                startActivity(i5);

                Toast.makeText(Homepageseller.this,"profileUpdate",Toast.LENGTH_LONG).show();

                break;

            case R.id.bankdetailspage:
                // do you click actions for the first selection
                final String sellerids2 = String.valueOf(sellerid_s);
                Intent i2 = new Intent(Homepageseller.this, Bank_Account_Details.class);
                i2.putExtra("name",name);
                i2.putExtra("mobile",mobile);
                i2.putExtra("email",email);
                i2.putExtra("sellerid",sellerids2);
                startActivity(i2);
                break;

            case R.id.taxdetailsid:
                // do you click actions for the first selection
                final String sellerids3 = String.valueOf(sellerid_s);
                Intent i3 = new Intent(Homepageseller.this, Taxdetails_Screen.class);
                i3.putExtra("name",name);
                i3.putExtra("mobile",mobile);
                i3.putExtra("email",email);
                i3.putExtra("sellerid",sellerids3);
                startActivity(i3);
                break;

           /* case R.id.notifications:
                // do you click actions for the first selection
                Intent i3 = new Intent(Dashboard1.this, Notification_screen.class);
                startActivity(i3);
                break;*/

           /* case R.id.about:
                // do you click actions for the first selection
                Intent i4 = new Intent(Dashboard1.this,Faqscreen.class);
                startActivity(i4);
                break;*/

            case R.id.signout1:
                // do you click actions for the first selection
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.clear();
                editor.apply();
                Intent i6 = new Intent(Homepageseller.this,MainActivity.class);
                startActivity(i6);
                break;

        }

        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    /*===Image pick from Gallery===*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        imageView.setImageBitmap(selectedImage);
                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
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

                    }
                    break;
            }
        }
    }

    /*===Image pick from Gallery===*/





    /*========Google maps start======*/




    /*private void createGoogleApi() {
        Log.d(TAG, "createGoogleApi()");

        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            // startGeofence();
        }
    }*/

  /*  @Override
    protected void onStart() {
        super.onStart();

        // Call GoogleApiClient connection when starting the Activity
        googleApiClient.connect();
    }*/

/*    @Override
    protected void onStop() {
        super.onStop();

        // Disconnect GoogleApiClient when stopping Activity
        googleApiClient.disconnect();
    }*/

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


/*
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
    private final int UPDATE_INTERVAL = 2000;
    private final int FASTEST_INTERVAL = 2000;

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
    }*/





    /*=========Google maps end===========*/



    /*=========save btn start=======*/

   /* private void loginme() {

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

                            Toast.makeText(Homepageseller.this, status, Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(Homepageseller.this, Bank_Account_Details.class);
                                   *//* i.putExtra("name",status2.getName());
                                    i.putExtra("mobile",status2.getPhonenumber());
                                    i.putExtra("email",status2.getEmailid());*//*
                            startActivity(i);

                           *//* if (status.equalsIgnoreCase("Seller Address saved Success fully")) {

                               *//**//* addressed.setText("");
                                stateed.setText("");
                                cityed.setText("");
                                pincodeed.setText("");*//**//*

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

                            }*//*
                        } else {
                            Toast.makeText(Homepageseller.this, "check", Toast.LENGTH_SHORT).show();

                        }
                        // Do awesome stuff
                        // signupresponsebodymsg = response.body();
                        // String message1 = signupresponsebodymsg.getMessage();
                        //String status = signupresponsebodymsg.getStatus();

                    } else if (response.code() == 401) {

                        //Toast.makeText(Login_screen.this, "Invalid password", Toast.LENGTH_SHORT).show();
                        alertDialog = new androidx.appcompat.app.AlertDialog.Builder(Homepageseller.this).create();
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

                        Toast.makeText(Homepageseller.this, "Invalid UserName", Toast.LENGTH_SHORT).show();
                        alertDialog = new androidx.appcompat.app.AlertDialog.Builder(Homepageseller.this).create();
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
                    Toast.makeText(Homepageseller.this, "connection error", Toast.LENGTH_LONG).show();

                }
            });
        }*/


        //signupRequestbody.setUsertype(usertypeuser);

       /* Toast.makeText(Signup_screen.this, usertypeseller, Toast.LENGTH_SHORT).show();
        Toast.makeText(Signup_screen.this, usertypeuser, Toast.LENGTH_SHORT).show();*/



    /*=========save btn end =======*/

}
