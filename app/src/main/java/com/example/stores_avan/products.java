package com.example.stores_avan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class products extends AppCompatActivity {

    Button btnPForm;
    Button btnHistory;
    Button btnStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        btnPForm = findViewById(R.id.button16);
        btnHistory = findViewById(R.id.button18);
        btnStatus = findViewById(R.id.button17);

        btnPForm.setOnClickListener(v -> showPform());
        btnStatus.setOnClickListener(v -> showStatus());
        btnHistory.setOnClickListener(v -> showHistory());


    }
    public void showPform(){
        Intent intent = new Intent(this, pform.class);
        startActivity(intent);
    }
    public void showStatus(){
        Intent intent = new Intent(this, Status.class);
        startActivity(intent);
    }
    public void showHistory(){
        Intent intent = new Intent(this, History.class);
        startActivity(intent);
    }
}
