package com.mnpw3d.umkcis_guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class PreDeparture extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_departure);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                                               .setAction("Action", null).show();


                                   }
                               }

            );

        }

    public void shopping(View v) {
        Intent i = new Intent(PreDeparture.this, ShoppingList.class);
        startActivity(i);
    }

    public void flight(View v) {
        Intent i = new Intent(PreDeparture.this, Flight.class);
        startActivity(i);
    }

    public void fee(View v) {
        Intent i = new Intent(PreDeparture.this, FeesDetail.class);
        startActivity(i);
    }

}
