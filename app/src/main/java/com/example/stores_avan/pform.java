package com.example.stores_avan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class pform extends AppCompatActivity {
    Button btnProduct;
    Button btnHistory;
    Button btnStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pform);
        btnProduct = findViewById(R.id.button15);
        btnHistory = findViewById(R.id.button18);
        btnStatus = findViewById(R.id.button17);

        btnProduct.setOnClickListener(v -> showProduct());
        btnHistory.setOnClickListener(v -> showHistory());
        btnStatus.setOnClickListener(v -> showStatus());
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
