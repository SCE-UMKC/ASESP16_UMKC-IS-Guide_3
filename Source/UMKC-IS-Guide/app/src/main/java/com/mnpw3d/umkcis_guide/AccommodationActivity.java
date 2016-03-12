package com.mnpw3d.umkcis_guide;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
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
