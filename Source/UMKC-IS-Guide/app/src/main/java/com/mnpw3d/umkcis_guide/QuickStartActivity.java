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

public class QuickStartActivity extends AppCompatActivity {
    TextView eorientationText;
    TextView checkInText;
    TextView orientationText;
    TextView studentIdText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_start);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        eorientationText = (TextView) findViewById(R.id.eorientationtext);
        checkInText = (TextView) findViewById(R.id.checkintext);
        orientationText = (TextView) findViewById(R.id.orientationtext);
        studentIdText = (TextView) findViewById(R.id.studentidtext);
        String eor = "<b><i><a href=\"https://www.umkc.edu/isao/srv/eor/index.cfm\">Here</a></i></b>";
        String check = "<b><i><a href=\"https://www.umkc.edu/isao/srv/chkin/index.cfm\">Here</a></i></b>";
        String idcard = "<b><i><a href=\"http://www.umkc.edu/onecard/about-your-one-card.asp\">Here</a></i></b>";
        eorientationText.setText(Html.fromHtml("\u2022 Welcome to UMKC <br>" +
                "\u2022 Before attending check-in session, you need to complete E-Orientation <br>" +
                "\u2022 E-Orientation helps you to understand various rules for students and gives quick start to your journey of UMKC<br>"+
                "\u2022 Complete E-Orientation at " + eor));
        eorientationText.setMovementMethod(LinkMovementMethod.getInstance());
        checkInText.setText(Html.fromHtml("\u2022 You need to complete check-in session <br> " +
                "\u2022 You need to carry your I-20, Marksheets, Transcripts, Passport for check-in session <br>" +
                "\u2022 You can choose session for check-in after E-orientation <br>" +
                "\u2022 Select Check-In Session at " + check));
        checkInText.setMovementMethod(LinkMovementMethod.getInstance());
        orientationText.setText("\u2022 This is mandotary for all International Students <br>" +
                "\u2022 It provides all necessary information which helps International Students in understanding UMKC system <br>" +
                "Its real FUN...!!! ☻☻☻");
        studentIdText.setText(Html.fromHtml("\u2022 This is one of the most important thing to do <br>"+
                "\u2022 Student have to get Student ID Card form Student Union Building <br>"+
                "\u2022 Student ID Card is necessary for accessing services of UMKC like Library, Swiney Recreation Center and much more..!! <br>"+
                "\u2022 Metro Transportation across Kansas City is also free through Student ID Card ☺☺ <br>"+
        "and Many more stuffs...!!! check "+ idcard));
        studentIdText.setMovementMethod(LinkMovementMethod.getInstance());
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
