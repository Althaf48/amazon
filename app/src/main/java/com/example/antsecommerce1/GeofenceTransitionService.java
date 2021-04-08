package com.example.antsecommerce1;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofenceStatusCodes;
import com.google.android.gms.location.GeofencingEvent;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by delaroy on 4/18/17.
 */
public class GeofenceTransitionService extends IntentService {

    private static final String TAG = GeofenceTransitionService.class.getSimpleName();

    public static final int GEOFENCE_NOTIFICATION_ID = 0;
    public static final int check = 1;



    RegisterInterface registerInterface;
   // NotifyresponseBody1 registerResponseBody;
    private String mobile,firstname,token;
    PreferenceHelper preferenceHelper;
    private boolean returnValue = true;


    public GeofenceTransitionService() {
        super(TAG);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onHandleIntent(Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);

        //mobile =  intent.getExtras().getString("")


        //mobile =  intent.getExtras().getString("mobileno");
        mobile =  intent.getExtras().getString("mobileno");
        firstname =  intent.getExtras().getString("username");
        token = intent.getExtras().getString("token");
       // token =  intent.getExtras().getString("token");
        Log.d("mobiasd",mobile);
        Log.d("mobiasdname",firstname);
//        Log.d("mobiasdtoken",token);


       // registerInterface= ApiClient.getClient().create(RegisterInterface.class);


        // Handling errors
        if ( geofencingEvent.hasError() ) {
            String errorMsg = getErrorString(geofencingEvent.getErrorCode() );
            Log.e( TAG, errorMsg );
            return;
        }

        int geoFenceTransition = geofencingEvent.getGeofenceTransition();
        // Check if the transition type is of interest
        if ( geoFenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER ||
                geoFenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT ) {
            // Get the geofence that were triggered
            List<Geofence> triggeringGeofences = geofencingEvent.getTriggeringGeofences();

            String geofenceTransitionDetails = getGeofenceTransitionDetails(geoFenceTransition, triggeringGeofences );
            Toast.makeText(GeofenceTransitionService.this,"geotransiotion",Toast.LENGTH_LONG).show();

            // Send notification details as a String
           // sendNotification( geofenceTransitionDetails );

                    //Write whatever to want to do after delay specified (1 sec)
           // registerMe();
           /* Timer timer = new Timer();
            TimerTask hourlyTask = new TimerTask() {
                @Override
                public void run () {
                    registerMe();
                    // your code here...
                }
            };

// schedule the task to run starting now and then every hour...
            timer.schedule (hourlyTask, 0l, 1000*10*60);
*/


           /* if(1==check) {
            registerMe();
            return;
        } else {

        }*/


            // registerMe2();

            //do something


        }

    }


    private String getGeofenceTransitionDetails(int geoFenceTransition, List<Geofence> triggeringGeofences) {
        // get the ID of each geofence triggered
        ArrayList<String> triggeringGeofencesList = new ArrayList<>();
        for ( Geofence geofence : triggeringGeofences ) {
            triggeringGeofencesList.add( geofence.getRequestId() );

        }

        String status = null;
        if ( geoFenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER )
            status = "Entering ";
          //  Toast.makeText(GeofenceTransitionService.this,"entering geofence",Toast.LENGTH_LONG).show();

         else if( geoFenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT )
            status = "Exiting ";



        //    Toast.makeText(GeofenceTransitionService.this,"exiting geofence",Toast.LENGTH_LONG).show();


        return status + TextUtils.join( ", ", triggeringGeofencesList);
    }

    /*private void sendNotification( String msg ) {
        Log.i(TAG, "sendNotification: " + msg );

        // Intent to start the main Activity
        Intent notificationIntent = Dashboardscreen.makeNotificationIntent(
                getApplicationContext(), msg
        );

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(notificationIntent);
        PendingIntent notificationPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);


        // Creating and sending Notification
        NotificationManager notificatioMng =
                (NotificationManager) getSystemService( Context.NOTIFICATION_SERVICE );
        notificatioMng.notify(
                GEOFENCE_NOTIFICATION_ID,
                createNotification(msg, notificationPendingIntent));

    }
*/
    // Create notification
    private Notification createNotification(String msg, PendingIntent notificationPendingIntent) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder
                .setSmallIcon(R.drawable.ic_action_location)
                .setColor(Color.RED)
                .setContentTitle(msg)
                .setContentText("Geofence Notification!")
                .setContentIntent(notificationPendingIntent)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND)
                .setAutoCancel(true);
        return notificationBuilder.build();
    }



    private static String getErrorString(int errorCode) {
        switch (errorCode) {
            case GeofenceStatusCodes.GEOFENCE_NOT_AVAILABLE:
                return "GeoFence not available";
            case GeofenceStatusCodes.GEOFENCE_TOO_MANY_GEOFENCES:
                return "Too many GeoFences";
            case GeofenceStatusCodes.GEOFENCE_TOO_MANY_PENDING_INTENTS:
                return "Too many pending intents";
            default:
                return "Unknown error.";
        }
    }
/*
    private void registerMe() {

        *//*token =  preferenceHelper.getTokens();*//*



            Log.d("tokendondsdase",token);
            // final String usernumber = getIntent().getExtras().getString("usernumber");
            Notify_paramsRequestBody registerRequestBody = new Notify_paramsRequestBody();
            registerRequestBody.setUsermobileno(mobile);
            registerRequestBody.setName(firstname);
            String response = registerRequestBody.getUsermobileno();
            String response2 = registerRequestBody.getName();

            Log.e("mobile",mobile);
            Log.e("firstname",firstname);
            Log.e("mobile",response);
            Log.e("firstname",response2);
            Log.e("firstname",response2);



            Call<NotifyresponseBody1> call = registerInterface.getNotify("Bearer"+token,registerRequestBody);
            call.enqueue(new Callback<NotifyresponseBody1>() {
                @Override
                public void onResponse(Call<NotifyresponseBody1> call, Response<NotifyresponseBody1> response) {

                    if (response.body()!=null){

                        // registerMe2();
                        registerResponseBody = response.body();
                        //String message = registerResponseBody.toString();
                        String status = registerResponseBody.getSuccess().toString();
                        if (status.equalsIgnoreCase("success")){
                            Toast.makeText(GeofenceTransitionService.this,"notification went",Toast.LENGTH_SHORT).show();


                        }else {
                            Toast.makeText(GeofenceTransitionService.this,"check connection",Toast.LENGTH_SHORT).show();
                        }

                    }

                }

                @Override
                public void onFailure(Call<NotifyresponseBody1> call, Throwable t) {
                    Log.i("sendingfail", "Returned empty response");
                    Toast.makeText(GeofenceTransitionService.this,"your out of geofence",Toast.LENGTH_LONG).show();

                }
            });




    }

    private void registerMe2() {

        *//*token =  preferenceHelper.getTokens();*//*

        Log.d("tokendondsdase",token);
        // final String usernumber = getIntent().getExtras().getString("usernumber");
        Notify_paramsRequestBody registerRequestBody = new Notify_paramsRequestBody();
        registerRequestBody.setUsermobileno(mobile);
        registerRequestBody.setName(firstname);
        String response = registerRequestBody.getUsermobileno();
        String response2 = registerRequestBody.getName();

        Log.e("mobile",mobile);
        Log.e("firstname",firstname);
        Log.e("mobile",response);
        Log.e("firstname",response2);
        Log.e("firstname",response2);




        Call<NotifyresponseBody1> call = registerInterface.getNotifysms("Bearer"+token,registerRequestBody);
        call.enqueue(new Callback<NotifyresponseBody1>() {
            @Override
            public void onResponse(Call<NotifyresponseBody1> call, Response<NotifyresponseBody1> response) {

                if (response.body()!=null){
                    registerResponseBody = response.body();
                    //String message = registerResponseBody.toString();
                    String status = registerResponseBody.getSuccess().toString();
                    if (status.equalsIgnoreCase("success")){
                        Toast.makeText(GeofenceTransitionService.this,"notification went",Toast.LENGTH_SHORT).show();


                    }else {
                        Toast.makeText(GeofenceTransitionService.this,"check connection",Toast.LENGTH_SHORT).show();
                    }

                }

            }

            @Override
            public void onFailure(Call<NotifyresponseBody1> call, Throwable t) {
                Log.i("sendingfail", "Returned empty response");
                Toast.makeText(GeofenceTransitionService.this,"your out of geofence",Toast.LENGTH_LONG).show();

            }
        });




    }*/
}