package com.example.stores_avan;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class DepartmentAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder>{

    private static final String TAG = DepartmentAdapter.class.getSimpleName();
    Department[] dList;
    Catagory[] cList;

    public static final int ITEM_TYPE_DEPARTMENT = 0;
    public static final int ITEM_TYPE_CATAGORY = 1;

    public DepartmentAdapter(Department[] l){
        this.dList=l;
        Log.d(TAG," "+l.length);
    }
    /*public DepartmentAdapter(Catagory[] l){
        this.cList=l;
    }*/
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d(TAG," "+i);
        if(i == ITEM_TYPE_DEPARTMENT){
            View departmentLayout = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.fragment_inventorymain,viewGroup,false);
            return new DepartmentViewHolder(departmentLayout);
        }else{
            Log.w(TAG,"Invalid type of view received");
            return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof DepartmentViewHolder) {
            Log.d(TAG,"name "+dList[i].dname);
            Log.d(TAG,"add "+dList[i]);
            Log.d(TAG,"id "+dList[i].dId);
            Department possiblyDepartment = dList[i];
            bindDeparment((Department) possiblyDepartment, (DepartmentViewHolder) viewHolder);
        } else {
            Log.e(TAG, "Unable to bind this type");
        }
    }

    private void bindDeparment(Department d, DepartmentViewHolder v) {
        if(d!=null){
        v.textViewdId.setText(""+d.dId);
        v.textViewdname.setText(d.dname);
        v.textViewdname.setTag(d);
        v.textViewdId.setTag(d);
        //v.textViewdId.setText("abcdefg");
        v.textViewdname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),v.getTag().toString(),Toast.LENGTH_LONG)
                    .show();

            }
        });}
        else{
            Log.e(TAG,"No department found");
        }
    }

    @Override
    public int getItemCount() {
        return dList.length;
    }

    private class DepartmentViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewdname;
        public TextView textViewdId;
        public DepartmentViewHolder(View itemView) {
            super(itemView);
            textViewdId=itemView.findViewById(R.id.item_number);
            textViewdname=itemView.findViewById(R.id.content);
        }
    }
}
