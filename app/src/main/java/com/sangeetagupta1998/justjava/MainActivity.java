package com.sangeetagupta1998.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int numberOfCoffees = 2 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void increaseQuantity(View view)
    {
        numberOfCoffees = numberOfCoffees + 1;
        display(numberOfCoffees);
    }

    public void decreaseQuantity(View view)
    {
        numberOfCoffees = numberOfCoffees - 1;
        display(numberOfCoffees);
    }

    public void submitOrder(View view) {
        String priceMessage = "Amount due " + "$" + (numberOfCoffees * 5) ;
        priceMessage = priceMessage + "\nThank You ^-^ " ;
        displayMessage(priceMessage) ;
        //displayPrice(numberOfCoffees * 5 );
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

}
