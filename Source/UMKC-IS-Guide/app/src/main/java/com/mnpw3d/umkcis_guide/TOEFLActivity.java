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

public class TOEFLActivity extends AppCompatActivity {
    TextView descriptiontext;
    TextView formattext;
    TextView requirementtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toefl);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        descriptiontext = (TextView) findViewById(R.id.descriptiontext);
        formattext = (TextView) findViewById(R.id.formattext);
        requirementtext = (TextView) findViewById(R.id.requirementtext);
        String toefl = "<b><i><a href=\"http://www.ets.org/toefl/\">Official Website</a></i></b>";
        String check = "<b><i><a href=\"https://www.umkc.edu/isao/srv/chkin/index.cfm\">Here</a></i></b>";
        String idcard = "<b><i><a href=\"http://www.umkc.edu/onecard/about-your-one-card.asp\">Here</a></i></b>";
        descriptiontext.setText(Html.fromHtml("Test Of English as a Foreign Language <br>" +
                "It is a standardized test to measure the English language ability of non-native speakers " +
                "wishing to enroll in American universities. The test is accepted by many English-speaking " +
                "academic and professional institutions. TOEFL is one of the two major English-language tests " +
                "in the world, the other being the IELTS.<br>"+toefl));
        descriptiontext.setMovementMethod(LinkMovementMethod.getInstance());
        formattext.setText(Html.fromHtml("TOEFL is divided into four sections: <br> " +
                "\u2022 <b>Reading</b>: It contains 3–5 passages, each containing 12–14 questions.<br>" +
                "\u2022 <b>Listening:</b> It contains 6–9 passages, each containing 5–6 questions.<br>" +
                "\u2022 <b>Speaking:</b> It contains 6 tasks.<br>" +
                "\u2022 <b>Writing:</b> It contains 2 tasks.<br>" +
                "The admission can be based on cutoffs set by the university on each section.<br>"));
        formattext.setMovementMethod(LinkMovementMethod.getInstance());
        requirementtext.setText(Html.fromHtml("A minimum score of 80 on the TOEFL exam is required for international students without prior U.S. degrees."));


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
