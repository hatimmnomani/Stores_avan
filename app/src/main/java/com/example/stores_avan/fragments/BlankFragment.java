package com.example.stores_avan.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.stores_avan.R;
import com.example.stores_avan.server.PostService;

import static com.example.stores_avan.fragments.inventoryMainFragment.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {
    private inventoryMainFragment.OnListFragmentInteractionListener mListener;
    Button submit1;
    EditText E1;
    EditText E2;
    EditText E3;
    EditText E4;
    EditText E5;
    EditText E6;
    Switch s1;
    Spinner m_u;

    public BlankFragment() {
        // Required empty public constructor
    }

    private void submitRequest(SharedPreferences sharedPreferences) {
        Intent intentS = new Intent(getContext(), PostService.class);

        intentS.putExtra("purpose",E1.getText().toString());
        intentS.putExtra("eid",sharedPreferences.getInt("eid",8));
        intentS.putExtra("pname",E2.getText().toString());
        intentS.putExtra("pdisc",E3.getText().toString());
        Log.d(TAG,"==> sending from form -> "+E5.getText());
        intentS.putExtra("quantity",E4.getText());
        intentS.putExtra("price",E5.getText());
        intentS.putExtra("reqD",E6.getText().toString());
        intentS.putExtra("m_u",m_u.getBaseline());
        intentS.putExtra("urj",1);

        //intentS.putExtra("urj",s1.getSplitTrack());
        getActivity().startService(intentS);

    }
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof inventoryMainFragment.OnListFragmentInteractionListener) {
            mListener = (inventoryMainFragment.OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //getActivity().setContentView(R.layout.fragment_blank);
        View rootView =inflater.inflate(R.layout.fragment_blank, container, false);
        // Inflate the layout for this fragment
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("com.avan.stores.userprofile", Context.MODE_PRIVATE);
        E1=rootView.findViewById(R.id.purpose);
        E2=rootView.findViewById(R.id.pname);
        E3=rootView.findViewById(R.id.pdisc);
        E4=rootView.findViewById(R.id.quantity);
        m_u=rootView.findViewById(R.id.m_u);
        E5=rootView.findViewById(R.id.price);
        E6=rootView.findViewById(R.id.reqD);
        s1=rootView.findViewById(R.id.priority);
        submit1=(Button) rootView.findViewById(R.id.submit1);
        submit1.setOnClickListener(v -> submitRequest(sharedPreferences));

        return rootView;
    }

}
