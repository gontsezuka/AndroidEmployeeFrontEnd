package com.optiflex.employeefrontendandroid.employeeProcessing;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.optiflex.employeefrontendandroid.MainActivity;
import com.optiflex.employeefrontendandroid.R;
import com.optiflex.employeefrontendandroid.adapter.EmployeeAdapter;
import com.optiflex.employeefrontendandroid.model.EmployeeModel;
import com.optiflex.employeefrontendandroid.viewmodel.EmployeeViewModel;

import java.util.List;

public class FragmentEmployeeListMain  extends Fragment {

    public static final String TAG= "EMP-LIST";
    private EmployeeViewModel employeeViewModel;
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private EmployeeAdapter employeeAdapter;
    private Handler handler = new Handler();


    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_employee_list_main,container,false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        getActivity().getActionBar().setTitle("Employee List");
//        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FFFFFF"));
//        getActivity().getActionBar().setBackgroundDrawable(colorDrawable);

        employeeViewModel = ViewModelProviders.of(getActivity()).get(EmployeeViewModel.class);
        floatingActionButton = view.findViewById(R.id.employee_list_main_btn_floating);
        employeeAdapter = new EmployeeAdapter();
        recyclerView = view.findViewById(R.id.recyclerview_employees_list_main);

        employeeViewModel.findAllEmployees();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(employeeAdapter);

        employeeViewModel.getAllEmployees().observe(getActivity(), new Observer<List<EmployeeModel>>() {
            @Override
            public void onChanged(List<EmployeeModel> employeeModels) {
                if(employeeModels!=null)
                {
                    employeeAdapter.setEmployeeModelList(employeeModels);
                }else {
                    FragmentEmployeeListMain.this.showEmployeeNotAvailableToast();
                }
            }
        });

        employeeAdapter.setOnItemClickListener(new EmployeeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(EmployeeModel employeeModel) {
                if(employeeModel!=null)
                {
                    Intent intent = new Intent(getActivity(), EditEmployee.class);
                    intent.putExtra("EXTRA_EMPLOYEE_ID",employeeModel.getEmployeeId());
                    intent.putExtra("EXTRA_EMPLOYEE_NAME",employeeModel.getName());
                    intent.putExtra("EXTRA_EMPLOYEE_CODE",employeeModel.getEmployeeCode());
                    intent.putExtra("EXTRA_EMPLOYEE_EMAIL",employeeModel.getEmail());
                    intent.putExtra("EXTRA_EMPLOYEE_URL",employeeModel.getUrl());
                    intent.putExtra("EXTRA_EMPLOYEE_JOB_TITLE",employeeModel.getJobTitle());
                    intent.putExtra("EXTRA_EMPLOYEE_PHONE",employeeModel.getPhone());
                    startActivity(intent);
                }
            }
        });

        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_continue_add_employee);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn_continue = dialog.findViewById(R.id.dialog_continue_add_employee_btn_continue);
                Button btn_cancel = dialog.findViewById(R.id.dialog_continue_add_employee_btn_cancel);
                dialog.show();

                btn_continue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), AddEmployee.class);
                        startActivity(intent);
                        dialog.cancel();
                    }
                });


                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Toast.makeText(getActivity(),"Employee Deleted",Toast.LENGTH_SHORT).show();
                employeeViewModel.deleteEmployee(employeeAdapter.getEmployeeAt(viewHolder.getAdapterPosition()).getEmployeeId());

            }
        }).attachToRecyclerView(recyclerView);

    }

    public void showEmployeeNotAvailableToast()
    {
        Toast.makeText(getActivity(),"Employee List Not Available",Toast.LENGTH_LONG).show();
    }

}
