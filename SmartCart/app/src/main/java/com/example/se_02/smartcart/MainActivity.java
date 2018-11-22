package com.example.se_02.smartcart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.se_02.smartcart.Bluetooth.Select;
import com.example.se_02.smartcart.Calculation.BarcodeActivity;
import com.example.se_02.smartcart.Location.LocationActivity;

public class MainActivity extends AppCompatActivity {


    Button bt_bt, bt_location, bt_barcode;
    Intent intent_bt, intent_location, intent_barcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_bt = (Button) findViewById(R.id.bt_bluetooth);
        bt_location = (Button) findViewById(R.id.bt_location);
        bt_barcode = (Button) findViewById(R.id.bt_barcode);

        bt_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent_bt = new Intent(MainActivity.this, Select.class);
                startActivity(intent_bt);
            }
        });

        bt_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent_location = new Intent(MainActivity.this, LocationActivity.class);
                startActivity(intent_location);
            }
        });

        bt_barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent_barcode = new Intent(MainActivity.this, BarcodeActivity.class);
                startActivity(intent_barcode);
            }
        });
    }

}