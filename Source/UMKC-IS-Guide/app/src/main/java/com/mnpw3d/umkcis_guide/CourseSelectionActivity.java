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

import org.w3c.dom.Text;

public class CourseSelectionActivity extends AppCompatActivity {
    TextView majors;
    TextView credit;
    TextView select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_selection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String pathway ="<b><i><a href=\"https://umkc.umsystem.edu/psp/prd/?cmd=login\">Here</a></i></b>";
        String eor = "<b><i><a href=\"http://cas.umkc.edu/\">College&nbsp;of&nbsp;Art&nbsp;&&nbsp;Sciences</a></i></b>";
        String kalpan = "<b><i><a href=\"http://bloch.umkc.edu/\">Henry&nbsp;W.&nbsp;Bloch&nbsp;School&nbsp;of&nbsp;Management</a></i></b>";
        String man = "<b><i><a href=\"http://sce.umkc.edu/\">School&nbsp;of&nbsp;Computing&nbsp;&&nbsp;Engineering</a></i></b>";
        majors = (TextView) findViewById(R.id.majorstext);
        credit = (TextView) findViewById(R.id.credittxt);
        select = (TextView) findViewById(R.id.selecttxt);
        majors.setText(Html.fromHtml("UMKC provides various majors in the field of Engineering, Mathematics, Science and list countinues." +
                "Student can find subjects in majors from department website." +
                "Links for departments:" +
                "\u2022 "+eor+"<br>"+
                "\u2022 "+ kalpan+ "<br>"+
                "\u2022 "+ man));
        credit.setText("USA follows credit system in which each major has several credits. For Example, Master of Science has 30 credits." +
                "Each subject of MS has 3 credits, so student have to study 10 subjects for graduation. Each graduate student has to take minimum 9 credits and each undergrade student has to take minimum 12 credits in Spring and Fall semester.");
        select.setText(Html.fromHtml("Student can enroll in subjects after all holds like Orientation holds, Academic Advising holds have been removed." +
                "Student can take suggestions about subject selection from Academic Adviser " +
                "To enroll in classes, Student needs pathway account." +
                "Pathway helps student for searching classes for semester and particular majors" +
                "Using pathway, student can add or drop classes." +
                "Link for Pathway us "+pathway));

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
            Intent i = new Intent(CourseSelectionActivity.this,ProfileActicity.class);
            startActivity(i);
        }
        if( id == R.id.action_settings)
        {
            Intent i = new Intent(CourseSelectionActivity.this,ChangePassword.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);

    }
}
