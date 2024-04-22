package com.example.crudsqlite.xmlAndjsonparsing;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudsqlite.R;

import java.util.ArrayList;

public class EmployeeRecyclerAdapter extends RecyclerView.Adapter<EmployeeRecyclerAdapter.Viewholder> {

    Context context;
    ArrayList<Employee> arremp;

    public EmployeeRecyclerAdapter(Context context, ArrayList<Employee> arremp) {
        this.context = context;
        this.arremp = arremp;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.xml_layout,parent,false);
        Viewholder viewholder= new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        final  Employee employee= arremp.get(position);

        holder.txt_id.setText(employee.getId());
        holder.txt_name.setText(employee.getName());
        holder.txt_salary.setText(employee.getSalary());

        holder.btn_hobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "set the hobby", Toast.LENGTH_SHORT).show();

                Intent intent= new Intent(context, hobbyActivity.class);
                intent.putStringArrayListExtra("hobbies",employee.getHobbies());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arremp.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        TextView txt_id,txt_name,txt_salary;

        Button btn_hobby;
        public Viewholder(@NonNull View itemView) {
            super(itemView);

            txt_id=itemView.findViewById(R.id.txt_id);
            txt_name =itemView.findViewById(R.id.txt_name);
            txt_salary =itemView.findViewById(R.id.txt_salary);
            btn_hobby=itemView.findViewById(R.id.btn_hobby);

        }
    }
}
