package com.example.osamamac.taskmanager.Fragments;


import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.osamamac.taskmanager.Interfaces.AddNewTaskFragmentsInterface;
import com.example.osamamac.taskmanager.R;
import com.example.osamamac.taskmanager.Utilities.Utils;

public class AddNewTaskPriorityFragment extends DialogFragment implements View.OnClickListener{

    private RelativeLayout priority4 ,priority3, priority2, priority1;
    private Button btnCancel;

    private AddNewTaskFragmentsInterface addNewTaskFragmentsInterface;
    private String currentValue;

    private String value;

    public AddNewTaskPriorityFragment() {

    }

    public AddNewTaskPriorityFragment(AddNewTaskFragmentsInterface addNewTaskFragmentsInterface, String currentValue){
        this.addNewTaskFragmentsInterface = addNewTaskFragmentsInterface;
        this.currentValue = currentValue;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_add_new_task_priority, container, false);

        priority4 = rootView.findViewById(R.id.addNewTaskPriorityFragment4);
        priority3 = rootView.findViewById(R.id.addNewTaskPriorityFragment3);
        priority2 = rootView.findViewById(R.id.addNewTaskPriorityFragment2);
        priority1 = rootView.findViewById(R.id.addNewTaskPriorityFragment1);

        btnCancel = rootView.findViewById(R.id.btnCancelAddNewTaskPriorityFragment);

        priority4.setOnClickListener(this);
        priority3.setOnClickListener(this);
        priority2.setOnClickListener(this);
        priority1.setOnClickListener(this);

        btnCancel.setOnClickListener(this);

        if(currentValue.equals(getString(R.string.add_new_task_priority_fragment_priority4))){
            priority4.setBackgroundColor(Color.parseColor("#FFE5E5"));
        }else if(currentValue.equals(getString(R.string.add_new_task_priority_fragment_priority3))){
            priority3.setBackgroundColor(Color.parseColor("#FFE5E5"));
        }else if(currentValue.equals(getString(R.string.add_new_task_priority_fragment_priority2))){
            priority2.setBackgroundColor(Color.parseColor("#FFE5E5"));
        }else if(currentValue.equals(getString(R.string.add_new_task_priority_fragment_priority1))){
            priority1.setBackgroundColor(Color.parseColor("#FFE5E5"));
        }

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        addNewTaskFragmentsInterface.doneSelecting(Utils.PRIORITY_FRAGMENT_ID, value);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addNewTaskPriorityFragment4:
                value = getString(R.string.add_new_task_priority_fragment_priority4);
                dismiss();

                break;

            case R.id.addNewTaskPriorityFragment3:
                value = getString(R.string.add_new_task_priority_fragment_priority3);
                dismiss();

                break;

            case R.id.addNewTaskPriorityFragment2:
                value = getString(R.string.add_new_task_priority_fragment_priority2);
                dismiss();

                break;

            case R.id.addNewTaskPriorityFragment1:
                value = getString(R.string.add_new_task_priority_fragment_priority1);
                dismiss();

                break;

            case R.id.btnCancelAddNewTaskPriorityFragment:
                dismiss();
                break;
        }
    }
}
