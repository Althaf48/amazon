package com.example.antsecommerce1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import static com.example.antsecommerce1.Login_screen.PREFS_NAME;

public class Homepage_Customer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mtoggle;
    private Context mContext;
    String TAG;
    String name;
    String mobile;
    String email;
    String lat_s;
    String long_s;
    int sellerid_s;

    //String token;

    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage__customer);
        TAG = Homepage_Customer.class.getSimpleName();
        //config=new SharedPreferenceConfig(this);
        mContext= Homepage_Customer.this;
        name = getIntent().getExtras().getString("name");
        mobile = getIntent().getExtras().getString("mobile");
        email = getIntent().getExtras().getString("email");
        sellerid_s = getIntent().getExtras().getInt("sellerid");

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        // NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.usernamenavigation);
        navUsername.setText(name);
       // TextView navstate = (TextView) headerView.findViewById(R.id.navigationheaderstate);

        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
        navigationView.setItemIconTintList(null);

        drawerLayout = findViewById(R.id.drawer_layout);
        mtoggle = new ActionBarDrawerToggle(Homepage_Customer.this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        //registercv = findViewById(R.id.register_cv);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
            case R.id.nav_home:
                // do you click actions for the first selection
               /* Intent i = new Intent(Dashboard1.this, Dashboard1.class);
                startActivity(i);*/
                Toast.makeText(Homepage_Customer.this,"Your in Dashboard",Toast.LENGTH_LONG).show();

                break;

           /* case R.id.home_quarantinescreen:
                // do you click actions for the first selection
                Intent i2 = new Intent(Dashboard1.this, HomeQuarantine_dashboard.class);
                startActivity(i2);
                break;*/

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

            case R.id.signout:
                // do you click actions for the first selection
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.clear();
                editor.apply();
                Intent i5 = new Intent(Homepage_Customer.this,MainActivity.class);
                startActivity(i5);
                break;

        }

        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
