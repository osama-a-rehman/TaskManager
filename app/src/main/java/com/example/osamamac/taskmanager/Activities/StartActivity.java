package com.example.osamamac.taskmanager.Activities;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import com.example.osamamac.taskmanager.Adapters.SlidingImageAdapter;
import com.example.osamamac.taskmanager.R;

public class StartActivity extends AppCompatActivity {

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 3;
    private static final Integer[] IMAGES = {R.drawable.blurred_burger, R.drawable.blurred_burger1, R.drawable.blurred_burger2};

    private Button btnLeft, btnRight, btnSignInWithEmail, btnSignInWithGoogle;

    private ArrayList<Integer> imagesArray = new ArrayList<>();
    private ArrayList<String> imagesTitlesArray = new ArrayList<>();
    private ArrayList<String> imagesTextArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        btnLeft = (Button) findViewById(R.id.startActivityBtnLeft);
        btnRight = (Button) findViewById(R.id.startActivityBtnRight);
        btnSignInWithEmail = (Button) findViewById(R.id.startActivityBtnSignInWithEmail);
        btnSignInWithGoogle = (Button) findViewById(R.id.startActivityBtnSignInWithGoogle);

        init();
    }

    private void init(){
        for(int i=0;i<IMAGES.length;i++){
            imagesArray.add(IMAGES[i]);
        }

        imagesTextArray.add(getString(R.string.start_text_1));
        imagesTextArray.add(getString(R.string.start_text_2));
        imagesTextArray.add(getString(R.string.start_text_3));

        imagesTitlesArray.add(getString(R.string.start_title_1));
        imagesTitlesArray.add(getString(R.string.start_title_2));
        imagesTitlesArray.add(getString(R.string.start_title_3));

        mPager = (ViewPager) findViewById(R.id.pager);

        mPager.setAdapter(new SlidingImageAdapter(StartActivity.this,imagesArray, imagesTextArray, imagesTitlesArray));

        NUM_PAGES =IMAGES.length;

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentPage > 0){
                    currentPage--;
                    mPager.setCurrentItem(currentPage, true);

                    //Log.i("Page", String.valueOf(currentPage));
                }
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentPage < NUM_PAGES-1){
                    currentPage++;
                    mPager.setCurrentItem(currentPage, true);
                }
            }
        });

        btnSignInWithEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, SignInWithEmailActivity.class);
                startActivity(intent);
            }
        });
    }
}
