package com.optiflex.employeefrontendandroid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.optiflex.employeefrontendandroid.R;
import com.optiflex.employeefrontendandroid.model.EmployeeModel;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeHolder> {

    private List<EmployeeModel> employeeModelList= new ArrayList<>();
    private OnItemClickListener listener;


    @NonNull
    @Override
    public EmployeeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee_list,parent,false);
        return new EmployeeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeHolder holder, int position) {
        holder.phone.setText(employeeModelList.get(position).getPhone());
        holder.jobTitle.setText(employeeModelList.get(position).getJobTitle());
        holder.email.setText(employeeModelList.get(position).getEmail());
        holder.name.setText(employeeModelList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return employeeModelList.size();
    }

    public class EmployeeHolder extends RecyclerView.ViewHolder{

        private TextView name,email,jobTitle,phone;

        public EmployeeHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.employee_name);
            email = itemView.findViewById(R.id.employee_email);
            jobTitle = itemView.findViewById(R.id.employee_jobTitle);
            phone = itemView.findViewById(R.id.employee_phone);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener !=null && position != RecyclerView.NO_POSITION)
                    {
                        listener.onItemClick(employeeModelList.get(position));
                    }
                }
            });
        }
    }

    public void setEmployeeModelList(List<EmployeeModel> employeeModelList)
    {
        this.employeeModelList = employeeModelList;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener
    {
        void onItemClick(EmployeeModel employeeModel);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.listener = listener;
    }

    public EmployeeModel getEmployeeAt(int position)
    {
        return employeeModelList.get(position);
    }
}
