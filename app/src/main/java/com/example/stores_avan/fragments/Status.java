package com.example.stores_avan.fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.stores_avan.Entities.ProcurementRequest;
import com.example.stores_avan.R;
import com.example.stores_avan.adapters.adapter1;
import com.example.stores_avan.adapters.adapter2;
import com.example.stores_avan.server.ServerFetch;
import com.google.gson.Gson;

import static com.example.stores_avan.fragments.inventoryMainFragment.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Status#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Status extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView a;
    private RecyclerView.Adapter b;
    private RecyclerView.LayoutManager c;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    //private ProcurementRequest cat[];


    public Status() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Status.
     */
    // TODO: Rename and change types and number of parameters
    public static Status newInstance(String param1, String param2) {
        Status fragment = new Status();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getActivity().setContentView(R.layout.activity_recycle);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(procStatusReceived, new IntentFilter("broadcast.get.Status"));
        Intent intent = new Intent(getActivity().getApplicationContext(), ServerFetch.class);
        intent.putExtra("fetch","getProcStatusList");
        getActivity().startService(intent);
    }
    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(procStatusReceived);
    }
    BroadcastReceiver procStatusReceived = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(TAG, "action: " + action);
            if (action.equalsIgnoreCase("broadcast.get.Status")) {
                String profileJson = intent.getStringExtra("data");
                ProcurementRequest cat[] = new Gson().fromJson(profileJson, ProcurementRequest[].class);
                Log.d(TAG, "name: " + cat.length);
                Log.d(TAG, "id: " + cat[0].rid);
                responseAs(cat);
                //showCatagory(cat);
            } else {
                Log.d(TAG, "Maybe useful intent action, but not of our interest");
            }

        }
    };

    private void responseAs(ProcurementRequest[] cat) {
        a = getActivity().findViewById(R.id.r2);
        b = new adapter2(cat);
        c = new LinearLayoutManager(getActivity());
        a.setLayoutManager(c);
        a.setAdapter(b);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.activity_recycle2,  null);
        //textView.setText(R.string.hello_blank_fragment);
        Intent intent = new Intent(getActivity().getApplicationContext(), ServerFetch.class);
        intent.putExtra("fetch","getProcStatusList");
        getActivity().startService(intent);

        return rootView;
    }

}
