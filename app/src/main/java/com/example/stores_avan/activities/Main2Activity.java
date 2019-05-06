package com.example.stores_avan.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.stores_avan.Entities.Department;
import com.example.stores_avan.R;
import com.example.stores_avan.fragments.BlankFragment2;
import com.example.stores_avan.fragments.inventoryMainFragment;
import com.example.stores_avan.ui.main.SectionsPagerAdapter;

public class Main2Activity extends AppCompatActivity implements inventoryMainFragment.OnListFragmentInteractionListener,BlankFragment2.OnFragmentInteractionListener{
    private static final String TAG = "Main2Activity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);


       // BlankFragment2.OnFragmentInteractionListener;

        //FloatingActionButton fab = findViewById(R.id.fab);

        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }



    @Override
    public void onListFragmentInteraction(Department item) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}