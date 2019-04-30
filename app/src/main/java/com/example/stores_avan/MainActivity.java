package com.example.stores_avan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnPForm;
    Button btnProduct;
    Button btnHistory;
    Button btnStatus;
    Button btnt;
    Button btnt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPForm = findViewById(R.id.button12);
        btnProduct = findViewById(R.id.button11);
        btnHistory = findViewById(R.id.button14);
        btnStatus = findViewById(R.id.button13);
        btnt = findViewById(R.id.xyz);
        btnt2 = findViewById(R.id.abc);


        btnPForm.setOnClickListener(v -> showPform());
        btnt.setOnClickListener(v -> showT());
        btnt2.setOnClickListener(v -> showE());
        btnProduct.setOnClickListener(v -> showProduct());
        btnHistory.setOnClickListener(v -> showHistory());
        btnStatus.setOnClickListener(v -> showStatus());
    }
    public void showE(){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
    public void showT(){
        Intent intent = new Intent(this, recycle.class);
        startActivity(intent);
    }
    public void showPform(){
        Intent intent = new Intent(this, pform.class);
        startActivity(intent);
    }
    public void showProduct(){
        Intent intent = new Intent(this, products.class);
        startActivity(intent);
    }
    public void showHistory(){
        Intent intent = new Intent(this, History.class);
        startActivity(intent);
    }
    public void showStatus(){
        Intent intent = new Intent(this, Status.class);
        startActivity(intent);
    }

}
