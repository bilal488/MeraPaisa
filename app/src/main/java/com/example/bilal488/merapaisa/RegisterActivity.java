package com.example.bilal488.merapaisa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    EditText et6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //dash CNIC
        et6=(EditText)findViewById(R.id.EditTextSignupCNIC);
        et6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //TODO Auto-generated method stub
                if(et6.getText().toString().length()==5)  //size as  per your requirement
                {
                    et6.append("-");
                }
                if(et6.getText().toString().length()==13)  //size as per your requirement
                {
                    et6.append("-");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });//CNIC
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),FirstActivity.class));
        overridePendingTransition(R.anim.slide_in_from_left,R.anim.slide_out_to_right);
        finish();
    }
}
