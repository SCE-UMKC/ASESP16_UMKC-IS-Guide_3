package com.mnpw3d.umkcis_guide;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class IELTSActivity extends AppCompatActivity {
    TextView descriptiontext;
    TextView formattext;
    TextView requirementtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ielts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        descriptiontext = (TextView) findViewById(R.id.descriptiontext);
        formattext = (TextView) findViewById(R.id.formattext);
        requirementtext = (TextView) findViewById(R.id.requirementtext);
        String ielts = "<b><i><a href=\"http://www.ielts.org/\">Official Website</a></i></b>";
        String check = "<b><i><a href=\"https://www.umkc.edu/isao/srv/chkin/index.cfm\">Here</a></i></b>";
        String idcard = "<b><i><a href=\"http://www.umkc.edu/onecard/about-your-one-card.asp\">Here</a></i></b>";
        descriptiontext.setText(Html.fromHtml("<b>Acronym: </b>International English Language Testing System<br>" +
                "It is an international standardised test of English language proficiency for non-native " +
                "English language speakers. It is jointly managed by the British Council," +
                " IDP: IELTS Australia and Cambridge English Language Assessment. " +
                "IELTS is one of the two major English-language tests in the world, the other being the TOEFL.<br>" + ielts));
        descriptiontext.setMovementMethod(LinkMovementMethod.getInstance());
        formattext.setText(Html.fromHtml("IELTS is divided into four sections: <br> " +
                "\u2022 <b>Reading</b><br>" +
                "\u2022 <b>Listening</b><br>" +
                "\u2022 <b>Speaking</b><br>" +
                "\u2022 <b>Writing</b><br>" +
                "Listening, Reading and Writing are completed in one sitting. " +
                "The Speaking test may be taken on the same day or up to seven days before or after the other tests.<br>"+
                "The admission can be based on cutoffs set by the university on each section.<br>"));
        formattext.setMovementMethod(LinkMovementMethod.getInstance());
        requirementtext.setText(Html.fromHtml("A minimum score of 6.5 on the IELTS is required for international students without prior U.S. degrees."));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}

