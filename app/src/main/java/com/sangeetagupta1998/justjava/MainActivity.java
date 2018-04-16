package com.sangeetagupta1998.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int numberOfCoffees = 2 ;
    boolean hasWippedCream , hasChocolate ;
    Button button1, button2;
    CheckBox checkBox, checkBox1 ;
    EditText editText ;
    TextView quantityTextView,priceTextView ;
    String name ;

    private void setViewVariables(){

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        editText = findViewById(R.id.editText);

        checkBox = findViewById(R.id.checkbox);
        checkBox1 = findViewById(R.id.checkboxChocolate);

        quantityTextView = findViewById(R.id.quantity_text_view);

        priceTextView = findViewById(R.id.price_text_view);
        priceTextView = findViewById(R.id.price_text_view);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViewVariables();
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void increaseQuantity(View view)
    {

        if(numberOfCoffees == 100 ){
            button1.setClickable(false);
            return;
        }
        numberOfCoffees = numberOfCoffees + 1;
        display();
    }

    public void decreaseQuantity(View view)
    {
        if(numberOfCoffees == 1 ){
            button2.setClickable(false);
            return;
        }
        numberOfCoffees = numberOfCoffees - 1;
        display();
    }

    public void submitOrder(View view) {


        String priceMessage = orderSummary();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order Bill for : " + name);
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    public String orderSummary(){


        name = editText.getText().toString();

        hasWippedCream = checkBox.isChecked();
        hasChocolate = checkBox1.isChecked();

        String priceMessage = "Name : " + name ;

        priceMessage = priceMessage + "\nAdd whipped cream ? " + hasWippedCream ;
        priceMessage = priceMessage + "\nAdd chocolate ? " + hasChocolate ;
        priceMessage = priceMessage + "\nQuantity : " + numberOfCoffees ;
        priceMessage = priceMessage + "\nAmount due : " + "$" + calculatePrice() ;
        priceMessage = priceMessage + "\nThank You ^-^ " ;

        return priceMessage;

    }

    private int calculatePrice(){

        int price;
        if(hasChocolate && hasWippedCream) {
            price = numberOfCoffees * (5 + 1 + 2) ;
        }

        else {

            if (hasChocolate) {
                price = numberOfCoffees * (5 + 2);
            }
            else if(hasWippedCream) {
                price = numberOfCoffees * (5+1);
            }
            else{
               price = numberOfCoffees * 5;
            }

        }

        return price;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayMessage(String message) {

        priceTextView.setText(message);
    }

    private void display() {

        quantityTextView.setText("" + numberOfCoffees);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {

        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

}
