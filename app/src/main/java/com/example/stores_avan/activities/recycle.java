package com.example.stores_avan.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.stores_avan.R;
import com.example.stores_avan.adapters.adapter1;

public class recycle extends AppCompatActivity {

    private RecyclerView a;
    private RecyclerView.Adapter b;
    private RecyclerView.LayoutManager c;

    int [] x ={1,2,3,4,5,6,7,8,9,10};
    String [] y ={"Hii","hi","HELLO","hello","SUP","How","know","never","someday","wishing","well"};
    String [] d = {"10/02/2019","10/02/2019","10/02/2019","10/02/2019","10/02/2019","10/02/2019","10/02/2019","10/02/2019","10/02/2019","10/02/2019"};
    //ArrayList a1 = new ArrayList(Arrays.asList(a));
    //ArrayList b1 = new ArrayList(Arrays.asList(y));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        a = findViewById(R.id.r1);
        b = new adapter1(x,y,d);
        c = new LinearLayoutManager(this);
        a.setLayoutManager(c);
        a.setAdapter(b);

    }
}
