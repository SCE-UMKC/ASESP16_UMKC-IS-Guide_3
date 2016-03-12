package com.mnpw3d.umkcis_guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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

    public void exam(View v){
        Intent i = new Intent(HomeActivity.this,ExaminationActivity.class);
        startActivity(i);
    }
    public void university(View v){
        Intent i = new Intent(HomeActivity.this,UniversityApplicationActivity.class);
        startActivity(i);
    }
    public void visa(View v){
        Intent i = new Intent(HomeActivity.this,Visa.class);
        startActivity(i);
    }
    public void predeparture(View v){
        Intent i = new Intent(HomeActivity.this,PreDeparture.class);
        startActivity(i);
    }
    public void postdeparture(View v){
        Intent i = new Intent(HomeActivity.this,PostDepartureActivity.class);
        startActivity(i);
    }


}
