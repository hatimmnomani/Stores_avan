package com.example.stores_avan.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.example.stores_avan.Entities.ProcurementRequest;
import com.example.stores_avan.R;

public class adapter2 extends RecyclerView.Adapter<adapter2.MyViewHolder> {
    ProcurementRequest proc[];
    public adapter2(ProcurementRequest[] cat) {
        proc=cat;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card2,viewGroup,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        //if(proc[i].Status >= 0 && proc[i].Status < 4){
            myViewHolder.h.setText(""+proc[i].rid);
            myViewHolder.c.setText(""+proc[i].purpose);
            myViewHolder.d.setText(""+proc[i].reqD);//}
        //else{
         //   myViewHolder.card.setVisibility(View.INVISIBLE);
        //}
        switch(proc[i].status){
            case 0:{
                myViewHolder.e.setText("Pending");
                myViewHolder.f.setText("at HOD");
                break;
            }
            case 1:{
                myViewHolder.e.setText("Pending");
                myViewHolder.f.setText("at Store Manager");
                break;
            }case 2:{
                myViewHolder.e.setText("Pending");
                myViewHolder.f.setText("at Registrar");
                break;
            }case 3:{
                myViewHolder.e.setText("Pending");
                myViewHolder.f.setText("at Vice-Chancellor");
                break;
            }default:{
                myViewHolder.e.setText("Approved");
                myViewHolder.ca.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return proc.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView h;
        public TextView c;
        public TextView d;
        public TextView e;
        public TextView f;
        public CardView ca;

        public MyViewHolder(@NonNull View v) {
            super(v);
            h = v.findViewById(R.id.R_n2);
            c = v.findViewById(R.id.purpose2);
            d = v.findViewById(R.id.r_date2);
            ca = v.findViewById(R.id.c2);
            e=v.findViewById(R.id.textView82);
            f=v.findViewById(R.id.by82);
        }
    }
}
