package com.example.crudsqlite.xmlAndjsonparsing;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudsqlite.R;

import java.util.ArrayList;

public class HobbyAdapter extends RecyclerView.Adapter<HobbyAdapter.Viewholder> {

    Activity activity;

    public HobbyAdapter(Activity activity, ArrayList<String> arrhobby) {
        this.activity = activity;
        this.arrhobby = arrhobby;
    }

    ArrayList<String> arrhobby;



    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(activity).inflate(R.layout.hooby_layout,parent,false);
        Viewholder viewholder= new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        final String employee = arrhobby.get(position);
        holder.txt_hobby.setText(employee);

    }

    @Override
    public int getItemCount() {
        return arrhobby.size();
    }

    public class Viewholder  extends RecyclerView.ViewHolder{

        TextView txt_hobby;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            txt_hobby =itemView.findViewById(R.id.txt_hobby);
        }
    }
}
