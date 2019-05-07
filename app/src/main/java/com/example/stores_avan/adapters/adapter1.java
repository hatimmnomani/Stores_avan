package com.example.stores_avan.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.stores_avan.Entities.ProcurementRequest;
import com.example.stores_avan.R;

public class adapter1 extends RecyclerView.Adapter<adapter1.MyViewHolder> {
       //ArrayList x ;
       //ArrayList y ;
    ProcurementRequest proc[];
    public adapter1(ProcurementRequest x[]){
           proc = x;
       }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card,viewGroup,false);
         MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

            myViewHolder.h.setText(""+proc[i].rid);
            myViewHolder.c.setText(""+proc[i].purpose);
            myViewHolder.d.setText(""+proc[i].reqD);
        /*if(proc[i].Status.intValue() >= 0 && proc[i].Status.intValue() < 4){
            myViewHolder.card.setVisibility(View.INVISIBLE);
        }*/
        switch(proc[i].status){
            case -1:{
                myViewHolder.e.setText("Rejected By HOD");
                break;
            }
            case -2:{
                myViewHolder.e.setText("Rejected By Store manager");
                break;
            }case -3:{
                myViewHolder.e.setText("Rejected By Registrar");
                break;
            }case -4:{
                myViewHolder.e.setText("Rejected By Vice-Chancellor");
                break;
            }default:{
                myViewHolder.e.setText("Approved");
                myViewHolder.but.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
            return proc.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView h;
        public TextView c;
        public TextView d;
        public TextView e;
        public CardView card;
        public Button but;


        public MyViewHolder(View v){
            super(v);
            h = v.findViewById(R.id.R_n);
            c = v.findViewById(R.id.purpose);
            d = v.findViewById(R.id.r_date);
            card = v.findViewById(R.id.c1);
            e = v.findViewById(R.id.textView8);
            but = v.findViewById(R.id.buttonR);
        }
    }
}
