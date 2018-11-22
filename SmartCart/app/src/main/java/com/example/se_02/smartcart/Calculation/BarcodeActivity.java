package com.example.se_02.smartcart.Calculation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.support.v4.app.FragmentActivity;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.se_02.smartcart.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class BarcodeActivity extends FragmentActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    TextView txtPrice, txtName;
    ArrayAdapter adapter;
    String tmpBarcode;
    Intent intent_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);

        txtPrice = (TextView) findViewById(R.id.txt_price);
        txtName = (TextView) findViewById(R.id.txt_name);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Barcode");
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);

        intent_test = getIntent();
        tmpBarcode = intent_test.getExtras().getString("Barcode");
        Log.i("전달받은 문자열", tmpBarcode);
        //listView.setAdapter(adapter);
        readFromDatabase();
        //메소드를 사용해서 이벤트 리스너를 추가하면 데이터베이스의 내용이 변경될 때마다 콜백 함수를 호출
        //채팅 같은 경우 실시간 변동을 적용해야 하기 때문에 이 메소드 사용

    }

//    @IgnoreExtraProperties
//    public class Barcode {
//        public String name;
//        public String price;
//
//        public Barcode() {
//            //Default constructor requied for call to DataSnapshot.getValue(User.class)
//        }
//
//        public Barcode(String name, String price) {
//            this.name = name;
//            this.price = price;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public String getPrice() {
//            return price;
//        }
//    }

    public void readFromDatabase() {

        Query query = myRef.orderByKey().equalTo(tmpBarcode);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        String Name = ds.child("Name").getValue(String.class);
                        String Price = ds.child("Price").getValue(String.class);
                        txtPrice.setText(Price);
                        txtName.setText(Name);
                        Log.d("TAG", Name + " / " + Price);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
       /* myRef.addValueEventListener(new ValueEventListener() {

            /*public void onDataChange( DataSnapshot dataSnapshot) {
               for (DataSnapshot ds : dataSnapshot.getChildren()){
                   String Name = ds.child("Name").getValue(String.class);
                   String Price = ds.child("Price").getValue(String.class);
                   txtPrice.setText(Price);
                   txtName.setText(Name);
                   Log.d("TAG", Name + " / " + Price);

               }
                 }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("ss", "Failed to read value.", databaseError.toException());
            }
        });
    }
*/
    }
}
