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

import com.example.stores_avan.Entities.Department;
import com.example.stores_avan.R;
import com.example.stores_avan.activities.MainActivity;
import com.example.stores_avan.adapters.DepartmentAdapter;
import com.example.stores_avan.server.DeptService;
import com.google.gson.Gson;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class inventoryMainFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    public static final String TAG = MainActivity.class.getSimpleName();
    //Department[] cat;
    //private View view;
    //public static final String TAG = MainActivity.class.getSimpleName();
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public inventoryMainFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static inventoryMainFragment newInstance(int columnCount) {
        inventoryMainFragment fragment = new inventoryMainFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(getActivity().getApplicationContext(), DeptService.class);
        getActivity().startService(intent);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventorymain_list, container, false);
        //ArrayList <Catagory> cList= new ArrayList<Catagory>();
        //cList.add(Catagory);
        // Set the adapter

        return view;
    }
    public void responseAss(Department[] cat){
        RecyclerView recyclerView = getActivity().findViewById(R.id.list);
        DepartmentAdapter adapter = new DepartmentAdapter(cat);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
            Log.d(TAG," "+cat.length);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
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
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(departmentReceived, new IntentFilter("com.example.intent.filter"));
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(departmentReceived);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Department item);
    }
    BroadcastReceiver departmentReceived = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(TAG, "action: " + action);
            if (action.equalsIgnoreCase("com.example.intent.filter")) {
                String profileJson = intent.getStringExtra("data");
                Department[] cat = new Gson().fromJson(profileJson, Department[].class);
                Log.d(TAG, "name: " + cat[0].dname);
                Log.d(TAG, "id: " + cat[0].dId);
                responseAss(cat);
                //showCatagory(cat);
            } else {
                Log.d(TAG, "Maybe useful intent action, but not of our interest");
            }

        }
    };

    /*private void showCatagory(Catagory cat) {
    }*/
}
