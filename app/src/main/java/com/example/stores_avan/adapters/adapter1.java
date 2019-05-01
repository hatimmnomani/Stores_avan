package com.example.stores_avan.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.stores_avan.R;

public class adapter1 extends RecyclerView.Adapter<adapter1.MyViewHolder> {
       //ArrayList x ;
       //ArrayList y ;
    int []x;
    String [] y;
    String [] d;
    public adapter1(int[] a,String[] b,String[] date){
           x =a;
           y=b;
           d=date;
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
        myViewHolder.h.setText(""+x[i]);
        myViewHolder.c.setText(""+y[i]);
        myViewHolder.d.setText(""+d[i]);
    }

    @Override
    public int getItemCount() {
        if(x.length > y.length){
            return x.length-1;
        }
        else {
            return y.length - 1;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView h;
        public TextView c;
        public TextView d;
        public CardView card;


        public MyViewHolder(View v){
            super(v);
            h = v.findViewById(R.id.R_n);
            c = v.findViewById(R.id.purpose);
            d = v.findViewById(R.id.r_date);
            card = v.findViewById(R.id.c1);
        }
    }
}
