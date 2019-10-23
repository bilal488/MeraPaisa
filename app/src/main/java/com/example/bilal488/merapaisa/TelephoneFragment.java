package com.example.bilal488.merapaisa;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TelephoneFragment extends Fragment implements AdapterView.OnItemSelectedListener {


    public TelephoneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_telephone, container, false);

        Spinner spinnerTelephone = (Spinner) view.findViewById(R.id.spinnerTelephoneBill);


/// Spinner Drop Down elements

        List<String> subjects = new ArrayList<>();
        subjects.add("Select Company Name");
        subjects.add("HBL");
        subjects.add("UBL");
        subjects.add("MCB");
        subjects.add("Alide Bank");
        subjects.add("AlFlah");
        subjects.add("Faisal Bank");
        subjects.add("National Bank");

// Creating adapter for spinenr
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_expandable_list_item_1, subjects);

//Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource (android.R.layout.simple_selectable_list_item);

//attaching data adapter to spinner
        spinnerTelephone.setAdapter(dataAdapter);
        spinnerTelephone.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        // on selecting a spinner item
        String itemTBill = parent.getItemAtPosition(position).toString();

//Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected" + itemTBill, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
