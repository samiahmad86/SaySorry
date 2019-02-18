package com.saysorry;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Sami on 11-01-2018.
 */

public class WaitingActivity extends AppCompatActivity {
    private static final int REQUEST = 112;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
        ImageView iv = (ImageView) findViewById(R.id.iv_gif);
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(iv);
        Glide.with(this).load(R.drawable.cat).into(imageViewTarget);
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){

            @Override
            public void run() {
                // TODO Auto-generated method stub

                checkCall();

            }}, 3000);
    }
    public void checkCall()
    {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] PERMISSIONS = {Manifest.permission.CALL_PHONE};
            if (!hasPermissions(this.getApplicationContext(), PERMISSIONS)) {
                ActivityCompat.requestPermissions((Activity) WaitingActivity.this  , PERMISSIONS, REQUEST );
            } else {
                makeCall();
            }
        } else {
            makeCall();
        }

    }
    public void makeCall() {
        finish();
        String number="+4917665329146";
        Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel: "+number));
        intent.setData(Uri.parse("tel:"+number));
        startActivity(intent);

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
                    Toast.makeText(WaitingActivity.this, "The app was not allowed to call.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    @Override
    public void onBackPressed() {

    }
}
