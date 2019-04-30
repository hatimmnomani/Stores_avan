package com.example.stores_avan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class Status extends AppCompatActivity {

    Button btnPForm;
    Button btnProduct;
    Button btnHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        btnPForm = findViewById(R.id.button16);
        btnProduct = findViewById(R.id.button15);
        btnHistory = findViewById(R.id.button18);

        btnPForm.setOnClickListener(v -> showPform());
        btnProduct.setOnClickListener(v -> showProduct());
        btnHistory.setOnClickListener(v -> showHistory());

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

}
