package com.example.stores_avan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class History extends AppCompatActivity {
    Button btnProduct;
    Button btnPForm;
    Button btnStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        btnProduct = findViewById(R.id.button15);
        btnPForm = findViewById(R.id.button16);
        btnStatus = findViewById(R.id.button17);

        btnPForm.setOnClickListener(v -> showPform());
        btnProduct.setOnClickListener(v -> showProduct());
        btnStatus.setOnClickListener(v -> showStatus());

    }
    public void showPform(){
        Intent intent = new Intent(this, pform.class);
        startActivity(intent);
    }
    public void showProduct(){
        Intent intent = new Intent(this, products.class);
        startActivity(intent);
    }
    public void showStatus(){
        Intent intent = new Intent(this, Status.class);
        startActivity(intent);
    }

}
