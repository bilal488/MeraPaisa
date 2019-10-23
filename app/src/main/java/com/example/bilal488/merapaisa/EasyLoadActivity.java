package com.example.bilal488.merapaisa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EasyLoadActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_load);

        ///subject spinner///

        Spinner spinner2 = (Spinner) findViewById(R.id.spinnerMobileLoad);


/// Spinner Drop Down elements

        List<String> subjects = new ArrayList<>();
        subjects.add("Select Network");
        subjects.add("Ufone");
        subjects.add("JAZZ");
        subjects.add("Warid");
        subjects.add("ZONG");
        subjects.add("Telenor");

// Creating adapter for spinenr
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, subjects);

//Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource (android.R.layout.simple_selectable_list_item);

//attaching data adapter to spinner
        spinner2.setAdapter(dataAdapter2);
        spinner2.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // on selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

//Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected" + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //TODO auto-generated method sub
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        overridePendingTransition(R.anim.slide_in_from_left,R.anim.slide_out_to_right);
        finish();
    }
}
