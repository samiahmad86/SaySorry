package com.saysorry;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.color.holo_blue_dark;


/**
 * Created by Sami on 06-01-2018.
 */

public class ScheduleActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST = 112;
    public boolean REFRESH = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sched);
        final ListView listview = (ListView) findViewById(R.id.lv_time);
        Button call = (Button) findViewById(R.id.btn_call);
        Button sched = (Button) findViewById(R.id.btn_sched);
        ImageView imgBack = (ImageView) findViewById(R.id.iv_back);
        imgBack.setOnClickListener(this);
        call.setOnClickListener(this);
        sched.setOnClickListener(this);
        String[] time = new String[] { "10:30 - 11:00", "11:00 - 11:30", "13:00 - 13:30", "16:30 - 17:00", "20:00 - 20:30"};
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < time.length; ++i) {
            list.add(time[i]);
        }
        final ListViewAdapter adapter = new ListViewAdapter(this,
                R.layout.listview_layout,R.id.tv_time, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                view.setSelected(true);
               // ViewGroup row = (ViewGroup) view.getParent();
                //LinearLayout r = (LinearLayout) (view).getParent();
               // r.setBackgroundColor(getResources().getColor(R.color.white));
                TextView textView = (TextView) view.findViewById(R.id.tv_time);
               // textView.setTextColor(getResources().getColor(holo_blue_dark));
//                view.animate().setDuration(2000).alpha(0)
//                        .withEndAction(new Runnable() {
//                            @Override
//                            public void run() {
//                                list.remove(item);
//                                adapter.notifyDataSetChanged();
//                                view.setAlpha(1);
//                            }
//                        });
            }

        });

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_call) {
            showWait();
        }
        if(view.getId() == R.id.btn_sched) {
            RelativeLayout rl = (RelativeLayout) findViewById(R.id.activity_main);
            Snackbar snackbar = Snackbar
                    .make(rl, "Call was successfully scheduled", Snackbar.LENGTH_LONG);
            View sbView = snackbar.getView();

            sbView.setBackgroundColor(ContextCompat.getColor(ScheduleActivity.this, R.color.green));

           // LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 100);
           // layoutParams.setMargins(10, 10, 10, 10);
           // sbView.setLayoutParams(layoutParams);
            snackbar.show();
        }
        if(view.getId() == R.id.iv_back) {
            finish();
        }

    }
    public void checkCall()
    {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] PERMISSIONS = {Manifest.permission.CALL_PHONE};
            if (!hasPermissions(this.getApplicationContext(), PERMISSIONS)) {
                ActivityCompat.requestPermissions((Activity) ScheduleActivity.this  , PERMISSIONS, REQUEST );
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
                    Toast.makeText(ScheduleActivity.this, "The app was not allowed to call.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    @Override
    public void onResume(){
        super.onResume();
//        if(REFRESH) {
//            Intent toSettings = new Intent(getApplicationContext(), RatingActivity.class);
//            startActivity(toSettings);
//            REFRESH = false;
//        }
    }
    void showWait() {
        Intent toSettings = new Intent(getApplicationContext(), WaitingActivity.class);
        startActivity(toSettings);
    }

}
