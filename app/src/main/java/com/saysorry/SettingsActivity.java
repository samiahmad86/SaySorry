package com.saysorry;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Sami on 06-01-2018.
 */

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etName;
    Spinner sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ImageView imgSettings = (ImageView) findViewById(R.id.iv_back);
        Button btnSave = (Button) findViewById(R.id.btn_save);
        imgSettings.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        String[] language = {"Preferred Language","English", "Russian", "Hindi","Deutsch","Chinese" };
        sp=(Spinner)findViewById(R.id.spinner_language);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.layout_spinner_language, language);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("Name", "");
        String stLang = preferences.getString("Language", "");
        if(!name.equalsIgnoreCase(""))
        {
            etName = (EditText) findViewById(R.id.et_name);
            etName.setText(name);
        }


        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        sp.setAdapter(dataAdapter);
        if(!stLang.equalsIgnoreCase(""))
        {
            int spinnerPosition = dataAdapter.getPosition(stLang);
            sp.setSelection(spinnerPosition);
        }

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if (position > 0) {
                    // Notify the selected item text
//                    Toast.makeText
//                            (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
//                            .show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.iv_back) {
            finish();
        }
        if(view.getId() == R.id.btn_save) {
            etName = (EditText) findViewById(R.id.et_name);

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("Name",etName.getText().toString());
            editor.putString("Language",sp.getSelectedItem().toString());
            editor.apply();

            RelativeLayout rl = (RelativeLayout) findViewById(R.id.activity_main);
            Snackbar snackbar = Snackbar
                    .make(rl, "Changes Saved!", Snackbar.LENGTH_LONG);
            View sbView = snackbar.getView();

            sbView.setBackgroundColor(ContextCompat.getColor(SettingsActivity.this, R.color.green));
            snackbar.show();
            // finish();
        }

    }
}
