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

public class UniversityApplicationActivity extends AppCompatActivity {
    TextView documents;
    TextView references;
    TextView mailing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_application);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        documents = (TextView) findViewById(R.id.documentstext);
        references = (TextView) findViewById(R.id.referencestxt);
        mailing = (TextView) findViewById(R.id.mailingtxt);
        String eor = "<b><i><a href=\"http://med.umkc.edu/bamd/admission-requirements-eligibility/\">Addmission&nbsp;Requirement</a></i></b>";
        String kalpan = "<b><i><a href=\"https://www.umkc.edu/isao/index.cfm\">UMKC&nbsp;isao</a></i></b>";
        documents.setText("\u2022 Passport Photocopy \n"+
                "\u2022 Transcripts \n"+
                "\u2022 Marksheets Photocopy \n"+
                "\u2022 3 Letter of Recommendation \n" +
                "\u2022 Statement of Purpose \n" +
                "\u2022 Affadavit of Support \n"+
                "\u2022 Balance Certificate");
        references.setText(Html.fromHtml("\u2022 " + eor + "<br>" +
                "\u2022 " + kalpan ));
        mailing.setText("\u2022 International Student Affairs Office \n"+
                "\u2022 Student Success Center, G-04 \n"+
                "\u2022 5000 Holmes Street \n"+
                "\u2022 Kansas City, MO 64110-2499 \n"+
                "\u2022 PHONE : 816-235-1113 \n"+
                "\u2022 DOCUMENT FAX (USE FOR APPLICATION DOCUMENTS):\n"+ "    573-884-4894 \n"+
                "\u2022 EMAIL\n" +
                "   isao@umkc.edu \n");
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
            Intent i = new Intent(UniversityApplicationActivity.this,ProfileActicity.class);
            startActivity(i);
        }
        if( id == R.id.action_settings)
        {
            Intent i = new Intent(UniversityApplicationActivity.this,ChangePassword.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);

    }
}
