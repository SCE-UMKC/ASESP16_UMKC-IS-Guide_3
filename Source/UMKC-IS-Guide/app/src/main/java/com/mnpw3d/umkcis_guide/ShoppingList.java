package com.mnpw3d.umkcis_guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ShoppingList extends AppCompatActivity {
    TextView txtShop1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txtShop1 = (TextView) findViewById(R.id.txt_Shop_Content);
        txtShop1.setText("• Clothing (Summer/Winter)\n" +
                "• Traditional dresses\n" +
                "• Eatables (Spices, snacks, pickles)\n" +
                "• Footwear (Formal/Sports Shoes, flip flops) \n" +
                "• Medicines \n" +
                "• Stationary \n" +
                "• Accessories \n" +
                "• Travel card \n" +
                "• Personal accessories \n" +
                "• Utensils \n"+
                "• E-Data(Downloading is a serious crime)\n" +
                "• Luggage- 2 bags \n" +
                "• books");

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
            Intent i = new Intent(ShoppingList.this,ProfileActicity.class);
            startActivity(i);
        }
        if( id == R.id.action_settings)
        {
            Intent i = new Intent(ShoppingList.this,ChangePassword.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);

    }
}
