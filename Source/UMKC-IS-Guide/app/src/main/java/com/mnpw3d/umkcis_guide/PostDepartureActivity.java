package com.mnpw3d.umkcis_guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class PostDepartureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_departure);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void quickstart(View v){
        Intent i = new Intent(PostDepartureActivity.this,QuickStartActivity.class);
        startActivity(i);

    }
    public void course(View v){
        Intent i = new Intent(PostDepartureActivity.this,CourseSelectionActivity.class);
        startActivity(i);
    }
    public void accomodation(View v){
        Intent i = new Intent(PostDepartureActivity.this,AccommodationActivity.class);
        startActivity(i);
    }
}
