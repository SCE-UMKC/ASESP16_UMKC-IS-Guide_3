package com.mnpw3d.umkcis_guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ResourceActivity extends AppCompatActivity {
    TextView gre;
    TextView ielts;
    TextView toefl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gre = (TextView)findViewById(R.id.gretext);
        ielts = (TextView)findViewById(R.id.ieltstxt);
        toefl = (TextView)findViewById(R.id.toefltxt);
        String eor = "<b><i><a href=\"http://www.ets.org/gre/\">ETS&nbsp;Official</a></i></b>";
        String kalpan = "<b><i><a href=\"http://www.kaptest.com/gre\">Kalpan&nbsp;Test&nbsp;For&nbsp;GRE</a></i></b>";
        String man = "<b><i><a href=\"https://www.manhattanprep.com/gre/\">Manhattan&nbsp;Material</a></i></b>";
        String prince = "<b><i><a href=\"http://www.princetonreview.com/grad/gre-test-prep\">Princeton&nbsp;Review</a></i></b>";
        String analytical = "<b><i><a href=\"http://magoosh.com/gre/ultimate-gre-guide/gre-analytical-writing-essay/\">Analytical&nbsp;Writing&nbsp;Material</a></i></b>";
        String ielts1 = "<b><i><a href=\"http://takeielts.britishcouncil.org/prepare\">British&nbsp;Council</a></i></b>";
        String ielts2 = "<b><i><a href=\"https://www.ieltsessentials.com/global/prepare/supporttools\">Ielts&nbsp;essentials</a></i></b>";
        String ielts3 = "<b><i><a href=\"https://www.ielts.org/about-the-test/sample-test-questions\">ielts.org</a></i></b>";
        String ielts4 = "<b><i><a href=\"http://www.ielts-worldwide.com/free-study-material-on-ielts.htm\">ielts-worldwide.com</a></i></b>";
        String ielts5 = "<b><i><a href=\"http://www.cambridge.org/us/cambridgeenglish/official-exam-preparation-materials/exam/ielts\">cambridge.org</a></i></b>";
        String ets = "<b><i><a href=\"https://www.ets.org/toefl/ibt/prepare\">ETS&nbsp;Official</a></i></b>";
        String magoos = "<b><i><a href=\"http://magoosh.com/toefl/2014/best-free-toefl-resources/\">Magoosh&nbsp;TOEFL&nbsp;Blog</a></i></b>";
        String learn = "<b><i><a href=\"http://www.learn4good.com/languages/toefl/structure_underl_sntc2.htm\">learn4good.com</a></i></b>";
        String go = "<b><i><a href=\"http://gotoefl.blogspot.com/\">gotoefl.blogspot.com</a></i></b>";
        String noteful = "<b><i><a href=\"https://www.youtube.com/user/NoteFulldotcom\">Noteful&nbsp;Videos</a></i></b>";
        gre.setText(Html.fromHtml("\u2022 "+eor+"<br>"+
                                  "\u2022 "+ kalpan+ "<br>"+
                                 "\u2022 "+ man+ "<br>"+
                "\u2022 " + prince + "<br>" +
                "\u2022 " + analytical));
        ielts.setText(Html.fromHtml("\u2022 " + ielts3 + "<br>" +
                "\u2022 " + ielts1+ "<br>"+
                "\u2022 "+ ielts2+ "<br>"+
                "\u2022 "+ ielts4+ "<br>"+
                "\u2022 "+ ielts5));
        toefl.setText(Html.fromHtml("\u2022 " + ets + "<br>" +
                "\u2022 " + noteful+ "<br>"+
                "\u2022 "+ magoos+ "<br>"+
                "\u2022 "+ go+ "<br>"+
                "\u2022 "+ learn));

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
            Intent i = new Intent(ResourceActivity.this,ProfileActicity.class);
            startActivity(i);
        }
        if( id == R.id.action_settings)
        {
            Intent i = new Intent(ResourceActivity.this,ChangePassword.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);

    }
}
