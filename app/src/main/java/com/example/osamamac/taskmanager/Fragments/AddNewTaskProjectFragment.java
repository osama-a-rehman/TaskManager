package com.example.osamamac.taskmanager.Fragments;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.osamamac.taskmanager.Interfaces.AddNewTaskFragmentsInterface;
import com.example.osamamac.taskmanager.R;
import com.example.osamamac.taskmanager.Utilities.Utils;

public class AddNewTaskProjectFragment extends DialogFragment implements View.OnClickListener{

    private RelativeLayout inbox, personal, shopping, work, errands, movies;
    private Button btnCancel;

    private AddNewTaskFragmentsInterface addNewTaskFragmentsInterface;
    private String currentValue;

    private String value;

    public AddNewTaskProjectFragment(){

    }

    public AddNewTaskProjectFragment(AddNewTaskFragmentsInterface addNewTaskFragmentsInterface, String currentValue){
        this.addNewTaskFragmentsInterface = addNewTaskFragmentsInterface;
        this.currentValue = currentValue;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_add_new_task_project, container, false);

        inbox = rootView.findViewById(R.id.addNewTaskProjectFragmentInbox);
        personal = rootView.findViewById(R.id.addNewTaskProjectFragmentPersonal);
        shopping = rootView.findViewById(R.id.addNewTaskProjectFragmentShopping);
        work = rootView.findViewById(R.id.addNewTaskProjectFragmentWork);
        errands = rootView.findViewById(R.id.addNewTaskProjectFragmentErrands);
        movies = rootView.findViewById(R.id.addNewTaskProjectFragmentMovies);

        btnCancel = rootView.findViewById(R.id.btnCancelAddNewTaskProjectFragment);

        inbox.setOnClickListener(this);
        personal.setOnClickListener(this);
        shopping.setOnClickListener(this);
        work.setOnClickListener(this);
        errands.setOnClickListener(this);
        movies.setOnClickListener(this);

        btnCancel.setOnClickListener(this);

        if(currentValue.equals(getString(R.string.add_new_task_project_fragment_inbox))){
            inbox.setBackgroundColor(Color.parseColor("#FFE5E5"));
        }else if(currentValue.equals(getString(R.string.add_new_task_project_fragment_personal))){
            personal.setBackgroundColor(Color.parseColor("#FFE5E5"));
        }else if(currentValue.equals(getString(R.string.add_new_task_project_fragment_shopping))){
            shopping.setBackgroundColor(Color.parseColor("#FFE5E5"));
        }else if(currentValue.equals(getString(R.string.add_new_task_project_fragment_work))){
            work.setBackgroundColor(Color.parseColor("#FFE5E5"));
        }else if(currentValue.equals(getString(R.string.add_new_task_project_fragment_errands))){
            errands.setBackgroundColor(Color.parseColor("#FFE5E5"));
        }else if(currentValue.equals(getString(R.string.add_new_task_project_fragment_movies))){
            movies.setBackgroundColor(Color.parseColor("#FFE5E5"));
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

        addNewTaskFragmentsInterface.doneSelecting(Utils.PROJECT_FRAGMENT_ID, value);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addNewTaskProjectFragmentInbox:
                value = getString(R.string.add_new_task_project_fragment_inbox);
                dismiss();

                break;

            case R.id.addNewTaskProjectFragmentPersonal:
                value = getString(R.string.add_new_task_project_fragment_personal);
                dismiss();

                break;

            case R.id.addNewTaskProjectFragmentShopping:
                value = getString(R.string.add_new_task_project_fragment_shopping);
                dismiss();

                break;

            case R.id.addNewTaskProjectFragmentWork:
                value = getString(R.string.add_new_task_project_fragment_work);
                dismiss();

                break;

            case R.id.addNewTaskProjectFragmentErrands:
                value = getString(R.string.add_new_task_project_fragment_errands);
                dismiss();

                break;

            case R.id.addNewTaskProjectFragmentMovies:
                value = getString(R.string.add_new_task_project_fragment_movies);
                dismiss();

                break;

            case R.id.btnCancelAddNewTaskProjectFragment:
                dismiss();
                break;
        }
    }
}
