package com.optiflex.employeefrontendandroid.responses;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.optiflex.employeefrontendandroid.MainActivity;
import com.optiflex.employeefrontendandroid.R;
import com.optiflex.employeefrontendandroid.model.EmployeeModel;
import com.optiflex.employeefrontendandroid.viewmodel.EmployeeViewModel;

import java.util.List;

public class ErrorFailedToLoadEmployees extends AppCompatActivity {

    private Button btn_retry;
    private Handler handler = new Handler();
    private EmployeeViewModel employeeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_failed_to_load_employees);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FFFFFF"));
        actionBar.setBackgroundDrawable(colorDrawable);

        btn_retry = findViewById(R.id.error_failed_to_load_employees_btn_retry);
        employeeViewModel = ViewModelProviders.of(ErrorFailedToLoadEmployees.this).get(EmployeeViewModel.class);


        btn_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        employeeViewModel.findAllEmployees();
                        Handler insideHandler = new Handler(getMainLooper());




                       insideHandler.post(new Runnable() {
                           @Override
                           public void run() {
                               employeeViewModel.getAllEmployees().observe(ErrorFailedToLoadEmployees.this, new Observer<List<EmployeeModel>>() {
                                   @Override
                                   public void onChanged(List<EmployeeModel> employeeModels) {
                                       if(employeeModels!=null)
                                       {
                                           Intent intent = new Intent(ErrorFailedToLoadEmployees.this, MainActivity.class);
                                           startActivity(intent);
                                           finish();
                                       }
                                   }
                               });
                           }// END INNER HANDLER RUN
                       });

                    }//END MAIN HANDLER RUN
                });
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}