package com.example.osamamac.taskmanager.Fragments;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.osamamac.taskmanager.Interfaces.AddNewTaskFragmentsInterface;
import com.example.osamamac.taskmanager.R;

public class AddNewLocationFragment extends DialogFragment {

    private AddNewTaskFragmentsInterface addNewTaskFragmentsInterface;

    public AddNewLocationFragment() {
    }

    public AddNewLocationFragment(AddNewTaskFragmentsInterface addNewTaskFragmentsInterface){
        this.addNewTaskFragmentsInterface = addNewTaskFragmentsInterface;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_new_location, container, false);
    }

}
