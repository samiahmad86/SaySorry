package com.saysorry;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import static android.R.attr.onClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST = 112;
    public boolean REFRESH = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imgSettings = (ImageView) findViewById(R.id.iv_setting);
        ImageView imgInfo = (ImageView) findViewById(R.id.iv_info);
        Button call = (Button) findViewById(R.id.btn_call);
        Button sched = (Button) findViewById(R.id.btn_sched);
        call.setOnClickListener(this);
        sched.setOnClickListener(this);
        imgSettings.setOnClickListener(this);
        imgInfo.setOnClickListener(this);
        ArrayList<ItemData> list=new ArrayList<>();
        list.add(new ItemData("Receiving Culture",R.drawable.empty));
        list.add(new ItemData("India",R.drawable.india));
        list.add(new ItemData("Germany",R.drawable.germany));
        list.add(new ItemData("Russia",R.drawable.russia));
        list.add(new ItemData("America",R.drawable.usa));
        list.add(new ItemData("UK",R.drawable.uk));



        Spinner sp=(Spinner)findViewById(R.id.spinner);
        SpinnerAdapter adapter=new SpinnerAdapter(this,
                R.layout.spinner_layout,R.id.tv_countryName,list);
        sp.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.iv_setting) {
            Intent toSettings = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(toSettings);
        }
        if(view.getId() == R.id.iv_info) {
            Intent toSettings = new Intent(getApplicationContext(), InfoActivity.class);
            startActivity(toSettings);
        }
        if(view.getId() == R.id.btn_call) {
            Random rand = new Random();
            int  n = rand.nextInt(50) + 1;
    //            if(n % 2 == 0)
    //                checkCall();
    //            else
                    showWait();



        }
        if(view.getId() == R.id.btn_sched) {
            Intent toSettings = new Intent(getApplicationContext(), ScheduleActivity.class);
            startActivity(toSettings);
        }
    }
    public void checkCall()
    {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] PERMISSIONS = {Manifest.permission.CALL_PHONE};
            if (!hasPermissions(this.getApplicationContext(), PERMISSIONS)) {
                ActivityCompat.requestPermissions((Activity) MainActivity.this  , PERMISSIONS, REQUEST );
            } else {
                makeCall();
            }
        } else {
            makeCall();
        }

    }
    public void makeCall() {
        String number="+4917665329146";
        Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel: "+number));
        intent.setData(Uri.parse("tel:"+number));
        startActivity(intent);
        REFRESH = true;

    }
    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    makeCall();
                } else {
                    Toast.makeText(MainActivity.this, "The app was not allowed to call.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    @Override
    public void onResume(){
        super.onResume();
        if(REFRESH) {
            Intent toSettings = new Intent(getApplicationContext(), RatingActivity.class);
            startActivity(toSettings);
            REFRESH = false;
        }
    }
    void showWait() {
        Intent toSettings = new Intent(getApplicationContext(), WaitingActivity.class);
        startActivity(toSettings);
    }
}
