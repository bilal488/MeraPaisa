package com.example.bilal488.merapaisa;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class CNICFragment extends Fragment {

    EditText etCNICC;


    public CNICFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_cnic, container, false);

        etCNICC=(EditText)view.findViewById(R.id.EditTextCNICNumber);
        etCNICC.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(etCNICC.getText().toString().length()==5)  //size as  per your requirement
                {
                    etCNICC.append("-");
                }
                if(etCNICC.getText().toString().length()==13)  //size as per your requirement
                {
                    etCNICC.append("-");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        return view;
    }

}
