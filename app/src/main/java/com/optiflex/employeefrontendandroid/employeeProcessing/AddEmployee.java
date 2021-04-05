package com.optiflex.employeefrontendandroid.employeeProcessing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.optiflex.employeefrontendandroid.R;
import com.optiflex.employeefrontendandroid.model.EmployeeModel;
import com.optiflex.employeefrontendandroid.responses.SuccessAddEmployee;
import com.optiflex.employeefrontendandroid.viewmodel.EmployeeViewModel;

public class AddEmployee extends AppCompatActivity {


    private EmployeeViewModel employeeViewModel;
    private EditText edt_name,edt_email,edt_phone,edt_job_title,edt_url;
    private Button btn_save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Add Employee");
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);


        employeeViewModel = ViewModelProviders.of(AddEmployee.this).get(EmployeeViewModel.class);

        btn_save = findViewById(R.id.add_employee_btn_complete);
        edt_email = findViewById(R.id.add_employee_edt_email);
        edt_job_title = findViewById(R.id.add_employee_edt_job_title);
        edt_phone = findViewById(R.id.add_employee_edt_phone);
        edt_url = findViewById(R.id.add_employee_edt_url);
        edt_name = findViewById(R.id.add_employee_edt_name);


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmployeeModel employeeModel = new EmployeeModel();

                if(edt_email.getText().toString().equals(""))
                {
                    Toast.makeText(AddEmployee.this,"Please Provide Email",Toast.LENGTH_SHORT).show();
                }else {
                    if(edt_job_title.getText().toString().equals(""))
                    {
                        Toast.makeText(AddEmployee.this,"Please Provide JobTitle",Toast.LENGTH_SHORT).show();
                    }else {
                        if(edt_phone.getText().toString().equals(""))
                        {
                            Toast.makeText(AddEmployee.this,"Please Provide Phone Number",Toast.LENGTH_SHORT).show();
                        }else {
                            if(edt_name.getText().toString().equals(""))
                            {
                                Toast.makeText(AddEmployee.this,"Please Provide Name",Toast.LENGTH_SHORT).show();
                            }else {
                                employeeModel.setEmail(edt_email.getText().toString());
                                employeeModel.setJobTitle(edt_job_title.getText().toString());
                                employeeModel.setName(edt_job_title.getText().toString());
                                employeeModel.setPhone(edt_phone.getText().toString());
                                employeeModel.setUrl(edt_url.getText().toString());
                                employeeViewModel.saveEmployee(employeeModel);
                                Intent intent = new Intent(AddEmployee.this, SuccessAddEmployee.class);
                                startActivity(intent);
                            }
                        }
                    }
                }//BIG EMAIL ELSE
            }//ON-CLICK END
        });



    }//END ON CREATE

    @Override
    public void onBackPressed() {
    }

    /**
     *         <uses-library
     *             android:name="com.google.android.wearable"
     *             android:required="true" />
     *         <!--
     *                Set to true if your app is Standalone, that is, it does not require the handheld
     *                app to run.
     *         -->
     *         <meta-data
     *             android:name="com.google.android.wearable.standalone"
     *             android:value="true" />
     */
}