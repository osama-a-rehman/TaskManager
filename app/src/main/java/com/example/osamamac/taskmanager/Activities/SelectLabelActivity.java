package com.example.osamamac.taskmanager.Activities;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.osamamac.taskmanager.R;
import com.example.osamamac.taskmanager.Utilities.Utils;

import org.w3c.dom.Text;

public class SelectLabelActivity extends AppCompatActivity{

    private TextInputLayout labelTextInputLayout;
    private EditText labelEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_label);

        labelTextInputLayout = (TextInputLayout) findViewById(R.id.selectLabelTextInputLayout);

        labelEditText = (EditText) findViewById(R.id.selectLabelEditText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_select_label_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.btnDoneSelectLabel) {
            String labelText = labelEditText.getText().toString().trim();

            if(labelText.isEmpty()){
                labelTextInputLayout.setError(getString(R.string.select_label_error_empty_label));
            }else{
                labelTextInputLayout.setErrorEnabled(false);

                Intent intent = new Intent();
                intent.putExtra(Utils.LABEL_RESULT_EXTRA, labelText);
                setResult(Utils.LABEL_RESULT_CODE, intent);
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}

