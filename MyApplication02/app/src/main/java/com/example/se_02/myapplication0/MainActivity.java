package com.example.se_02.myapplication0;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.indooratlas.android.sdk.IALocation;
import com.indooratlas.android.sdk.IALocationListener;
import com.indooratlas.android.sdk.IALocationManager;
import com.indooratlas.android.sdk.IALocationRequest;

public class MainActivity extends AppCompatActivity {

    IALocationManager mLocationManager;

    IALocationListener mLocationListener = new IALocationListener() {
        @Override
        public void onLocationChanged(IALocation iaLocation) {
            TextView tPrice = (TextView)findViewById(R.id.txtPrice);
            tPrice.setText(String.valueOf(iaLocation.getLatitude() + ", " + iaLocation.getLongitude()));
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("메인 액티비티");

        mLocationManager = IALocationManager.create(this);

        Button bSub = (Button)findViewById(R.id.btnSub);
        bSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void onResume(){
        super.onResume();
        mLocationManager.requestLocationUpdates(IALocationRequest.create(), mLocationListener);
    }

    protected void onPause(){
        super.onPause();
        mLocationManager.removeLocationUpdates(mLocationListener);
    }

    protected void onDestroy(){
        super.onDestroy();
    }
}

