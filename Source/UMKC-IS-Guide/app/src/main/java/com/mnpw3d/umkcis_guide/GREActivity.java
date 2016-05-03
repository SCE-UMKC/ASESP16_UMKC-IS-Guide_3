package com.mnpw3d.umkcis_guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class GREActivity extends AppCompatActivity {
    TextView descriptiontext;
    TextView formattext;
    TextView requirementtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gre);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        descriptiontext = (TextView) findViewById(R.id.descriptiontext);
        formattext = (TextView) findViewById(R.id.formattext);
        requirementtext = (TextView) findViewById(R.id.requirementtext);
        String ets = "<b><i><a href=\"http://www.ets.org/\">EDUCATIONAL TESTING SERVICE</a></i></b>";
        String check = "<b><i><a href=\"https://www.umkc.edu/isao/srv/chkin/index.cfm\">Here</a></i></b>";
        String idcard = "<b><i><a href=\"http://www.umkc.edu/onecard/about-your-one-card.asp\">Here</a></i></b>";
        descriptiontext.setText(Html.fromHtml("Graduate Record Examination <br>" +
                "It is a standardized test to be given to posess the admission in the graduate schools in most" +
                " of the Universities in United States. It is created and administered by the " + ets + "(ETS)," +
                " the exam aims to measure verbal reasoning, quantitative reasoning, analytical writing, and critical thinking " +
                "skills that have been acquired over a long period of time and that are not entirely" +
                " based on any specific field of study outside of the GRE itself." +
                " The GRE General Test is offered as a computer-based exam administered at Prometric testing centers."));
        descriptiontext.setMovementMethod(LinkMovementMethod.getInstance());
        formattext.setText(Html.fromHtml("GRE is divided into five sections: <br> " +
                "\u2022 <b>Verbal section</b>(170 marks)<br>" +
                "\u2022 <b>Quantitave Section</b>(170 marks)<br>" +
                "\u2022 <b>Analytical Writing</b>(5 points)<br>" +
                "      \u2022 Issue Task<br>" +
                "      \u2022 Argument Task<br>" +
                "The admission can be based on cutoffs set by the university on each section.<br>"));
        formattext.setMovementMethod(LinkMovementMethod.getInstance());
        requirementtext.setText(Html.fromHtml("The applicant must score in at least the 75th percentile on the quantitative portion of the GRE."));



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.profile) {
            Intent i = new Intent(GREActivity.this,ProfileActicity.class);
            startActivity(i);
        }
        if( id == R.id.action_settings)
        {
            Intent i = new Intent(GREActivity.this,ChangePassword.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);

    }
}
