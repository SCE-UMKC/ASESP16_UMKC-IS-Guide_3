package com.mnpw3d.umkcis_guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ExaminationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examination);
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
    public void grefn(View v)
    {
        Intent i=new Intent(ExaminationActivity.this,GREActivity.class);
        startActivity(i);
    }
    public void toeflfn(View v)
    {
        Intent i=new Intent(ExaminationActivity.this,TOEFLActivity.class);
        startActivity(i);
    }
    public void ieltsfn(View v)
    {
        Intent i=new Intent(ExaminationActivity.this,IELTSActivity.class);
        startActivity(i);
    }
    public void resourcesfn(View v)
    {
        Intent i=new Intent(ExaminationActivity.this,ResourceActivity.class);
        startActivity(i);
    }

}
