package com.example.stores_avan.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stores_avan.Entities.ProcurementRequest;
import com.example.stores_avan.R;
import com.example.stores_avan.adapters.adapter1;
import com.example.stores_avan.server.ServerFetch;
import com.google.gson.Gson;

import static com.example.stores_avan.fragments.inventoryMainFragment.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlankFragment2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlankFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView a;
    private RecyclerView.Adapter b;
    private RecyclerView.LayoutManager c;
   // private ProcurementRequest[] cat;
    int [] x ={1,2,3,4,5,6,7,8,9,10};
    String [] y ={"Hii","hi","HELLO","hello","SUP","How","know","never","someday","wishing","well"};
    String [] d = {"10/02/2019","10/02/2019","10/02/2019","10/02/2019","10/02/2019","10/02/2019","10/02/2019","10/02/2019","10/02/2019","10/02/2019"};
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BlankFragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment2 newInstance(String param1, String param2) {
        BlankFragment2 fragment = new BlankFragment2();
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
            mParam2 = getArguments().getString(ARG_PARAM2);/*

            a = getActivity().findViewById(R.id.r1);
            b = new adapter1(x,y,d);
            c = new LinearLayoutManager(getActivity());
            a.setLayoutManager(c);
            a.setAdapter(b);*/
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(procHisReceived, new IntentFilter("broadcast.get.History"));
        Intent intent = new Intent(getActivity().getApplicationContext(), ServerFetch.class);
        intent.putExtra("fetch","getProcHisList");
        getActivity().startService(intent);
    }
    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(procHisReceived);
    }
    BroadcastReceiver procHisReceived = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(TAG, "action: " + action);
            if (action.equalsIgnoreCase("broadcast.get.History")) {
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
        a = getActivity().findViewById(R.id.r1);
        b = new adapter1(cat);
        c = new LinearLayoutManager(getActivity());
        a.setLayoutManager(c);
        a.setAdapter(b);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.activity_recycle,  null);
        // Inflate the layout for this fragment
        //rootView.setContentView(R.layout.activity_recycle);
        Intent intent = new Intent(getActivity().getApplicationContext(), ServerFetch.class);
        intent.putExtra("fetch","getProcHisList");
        getActivity().startService(intent);

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
