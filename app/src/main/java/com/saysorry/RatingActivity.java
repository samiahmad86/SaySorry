package com.saysorry;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Sami on 06-01-2018.
 */

public class RatingActivity extends AppCompatActivity  implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.rb_rating);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
               //finish();
            }
        });
        Button submit = (Button) findViewById(R.id.btn_submit);
        TextView skip = (TextView) findViewById(R.id.tv_skip);
        submit.setOnClickListener(this);
        skip.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_submit) {
            finish();
        }
        if(view.getId() == R.id.tv_skip) {
            finish();
        }
    }
}
