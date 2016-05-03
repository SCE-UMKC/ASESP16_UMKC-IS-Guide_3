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

public class AccommodationActivity extends AppCompatActivity {
    TextView findHouse;
    TextView initial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accommodation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String umkc = "<b><i><a href=\"http://info.umkc.edu/housing/\">on-campus</a></i></b>";
        String appartments = "<b><i><a href=\"http://www.apartments.com/kansas-city-mo/\">appartments.com</a></i></b>";
        String rent = "<b><i><a href=\"http://www.rent.com/missouri/kansas-city-apartments/2-556\">rent.com</a></i></b>";
        String zillow = "<b><i><a href=\"http://www.zillow.com/kansas-city-mo/apartments/\">zillow.com</a></i></b>";
        String rental = "<b><i><a href=\"http://www.rentals.com/\">rentals.com</a></i></b>";
        String craig = "<b><i><a href=\"https://kansascity.craigslist.org/search/apa\">craigslist.com</a></i></b>";
        String forrent = "<b><i><a href=\"http://www.forrent.com/find/MO/metro-Kansas+City\">forrent.com</a></i></b>";
        String appfinder = "<b><i><a href=\"http://www.apartmentfinder.com/Missouri/Kansas-City-Apartments\">appartment.com</a></i></b>";
        String mac = "<b><i><a href=\"http://www.macapartments.com/market/Kansas-City\">macapartments.com</a></i></b>";
        findHouse = (TextView) findViewById(R.id.findhousetext);
        initial = (TextView) findViewById(R.id.initialtext);
        findHouse.setText(Html.fromHtml("Student can find Apprtments at here <br>" +
                "•  "+ umkc +"<br>" +
                "•  "+ appartments +"<br>" +
                "•  "+ rent +"<br>" +
                "•  "+ zillow +"<br>" +
                "•  "+ rental +"<br>" +
                "•  "+ craig +"<br>" +
                "•  "+ forrent +"<br>" +
                "•  "+ appfinder +"<br>" +
                "•  "+ mac));
        initial.setText(" Student can find necessary things for intial setup at following places \n" +
                "• Coastco \n" +
                "• Walmart \n" +
                "• SaveALot \n" +
                "• Home Depot\n" +
                "• Sun Fresh \n" +
                "• City Market\n");

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
            Intent i = new Intent(AccommodationActivity.this,ProfileActicity.class);
            startActivity(i);
        }
        if( id == R.id.action_settings)
        {
            Intent i = new Intent(AccommodationActivity.this,ChangePassword.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);

    }

}
