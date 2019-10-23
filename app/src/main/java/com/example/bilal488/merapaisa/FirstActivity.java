package com.example.bilal488.merapaisa;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        //to close app
        if (getIntent().getBooleanExtra("Exit me", false)){
            finish();
            return;
        }//to close app

        //
        Button b=(Button)findViewById(R.id.buttonAlreadyAccount);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                overridePendingTransition(R.anim.slide_in_from_right,R.anim.slide_out_to_left);
            }
        });

        Button a = (Button)findViewById(R.id.buttonNewAccount);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
                overridePendingTransition(R.anim.slide_in_from_right,R.anim.slide_out_to_left);
            }
        });
    }

}
