package com.optiflex.employeefrontendandroid;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.Constraints;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.optiflex.employeefrontendandroid.adapter.EmployeeAdapter;
import com.optiflex.employeefrontendandroid.config.WorkerNetworkCheck;
import com.optiflex.employeefrontendandroid.employeeProcessing.AddEmployee;
import com.optiflex.employeefrontendandroid.employeeProcessing.EditEmployee;
import com.optiflex.employeefrontendandroid.model.EmployeeModel;
import com.optiflex.employeefrontendandroid.viewmodel.EmployeeViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String WORK_TAG="network-checker";
    private EmployeeViewModel employeeViewModel;
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private EmployeeAdapter employeeAdapter;
    private Handler handler = new Handler();
    private List<EmployeeModel> employeeModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       ActionBar actionBar = getSupportActionBar();
       actionBar.setTitle("Employee List");

       ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#000000"));
       actionBar.setBackgroundDrawable(colorDrawable);

        employeeAdapter = new EmployeeAdapter();
        employeeViewModel = ViewModelProviders.of(MainActivity.this).get(EmployeeViewModel.class);
        floatingActionButton = findViewById(R.id.floating_btn_add_employee);
        recyclerView = findViewById(R.id.recyclerview_employees_list);

        employeeViewModel.findAllEmployees();

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(employeeAdapter);

       employeeViewModel.getAllEmployees().observe(MainActivity.this, new Observer<List<EmployeeModel>>() {
           @Override
           public void onChanged(List<EmployeeModel> employeeModels) {
               if(employeeModels!=null)
               {
                   employeeAdapter.setEmployeeModelList(employeeModels);
               }else {
                   Toast.makeText(MainActivity.this,"NO EMPLOYEES",Toast.LENGTH_SHORT).show();
               }
           }
       });



        employeeAdapter.setOnItemClickListener(new EmployeeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(EmployeeModel employeeModel) {
                if(employeeModel!=null)
                {
                    Intent intent = new Intent(MainActivity.this, EditEmployee.class);
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

        Dialog dialog = new Dialog(MainActivity.this);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.setContentView(R.layout.dialog_continue_add_employee);
                Button btn_cancel = dialog.findViewById(R.id.dialog_continue_add_employee_btn_cancel);
                Button btn_continue = dialog.findViewById(R.id.dialog_continue_add_employee_btn_continue);
                dialog.show();

                btn_continue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, AddEmployee.class);
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
                Toast.makeText(MainActivity.this,"Employee Deleted",Toast.LENGTH_SHORT).show();
                employeeViewModel.deleteEmployee(employeeAdapter.getEmployeeAt(viewHolder.getAdapterPosition()).getEmployeeId());
            }
        }).attachToRecyclerView(recyclerView);

    }// On Create End

    @Override
    public void onBackPressed() {
    }
}