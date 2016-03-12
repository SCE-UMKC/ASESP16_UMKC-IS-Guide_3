package com.mnpw3d.umkcis_guide;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
