package com.optiflex.employeefrontendandroid.employeeProcessing;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.optiflex.employeefrontendandroid.R;
import com.optiflex.employeefrontendandroid.model.EmployeeModel;
import com.optiflex.employeefrontendandroid.responses.SuccessAddEmployee;
import com.optiflex.employeefrontendandroid.viewmodel.EmployeeViewModel;

public class EditEmployee extends AppCompatActivity {

    private EmployeeViewModel employeeViewModel;
    private EditText edt_name,edt_email,edt_phone,edt_job_title,edt_url;
    private Button btn_save;

    private Long employeeId;
    private String name,email,phone,jobTitle,url,code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_employee);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);

        employeeViewModel = ViewModelProviders.of(EditEmployee.this).get(EmployeeViewModel.class);
        btn_save = findViewById(R.id.edit_employee_btn_complete);
        edt_name = findViewById(R.id.edit_employee_edt_name);
        edt_job_title = findViewById(R.id.edit_employee_edt_job_title);
        edt_url = findViewById(R.id.edit_employee_edt_url);
        edt_phone = findViewById(R.id.edit_employee_edt_phone);
        edt_email = findViewById(R.id.edit_employee_edt_email);

        Intent intentData = getIntent();

        name = intentData.getStringExtra("EXTRA_EMPLOYEE_NAME");
        jobTitle = intentData.getStringExtra("EXTRA_EMPLOYEE_JOB_TITLE");
        url = intentData.getStringExtra("EXTRA_EMPLOYEE_URL");
        email = intentData.getStringExtra("EXTRA_EMPLOYEE_EMAIL");
        phone = intentData.getStringExtra("EXTRA_EMPLOYEE_PHONE");
        code = intentData.getStringExtra("EXTRA_EMPLOYEE_CODE");
        employeeId = intentData.getLongExtra("EXTRA_EMPLOYEE_ID",-0L);

        edt_name.setText(name);
        edt_job_title.setText(jobTitle);
        edt_url.setText(url);
        edt_email.setText(email);
        edt_phone.setText(phone);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EmployeeModel employeeModel = new EmployeeModel();

                if(edt_email.getText().toString().equals(""))
                {
                    Toast.makeText(EditEmployee.this,"Please Provide Email",Toast.LENGTH_SHORT).show();
                }else {
                    if(edt_job_title.getText().toString().equals(""))
                    {
                        Toast.makeText(EditEmployee.this,"Please Provide JobTitle",Toast.LENGTH_SHORT).show();
                    }else {
                        if(edt_phone.getText().toString().equals(""))
                        {
                            Toast.makeText(EditEmployee.this,"Please Provide Phone Number",Toast.LENGTH_SHORT).show();
                        }else {
                            if(edt_name.getText().toString().equals(""))
                            {
                                Toast.makeText(EditEmployee.this,"Please Provide Name",Toast.LENGTH_SHORT).show();
                            }else {
                                employeeModel.setEmployeeId(employeeId);
                                employeeModel.setEmail(edt_email.getText().toString().trim());
                                employeeModel.setJobTitle(edt_job_title.getText().toString().trim());
                                employeeModel.setName(edt_name.getText().toString().trim());
                                employeeModel.setPhone(edt_phone.getText().toString().trim());
                                employeeModel.setUrl(edt_url.getText().toString().trim());
                                employeeViewModel.updateEmployee(employeeModel);
                                Intent intent = new Intent(EditEmployee.this, SuccessAddEmployee.class);
                                startActivity(intent);
                            }
                        }
                    }
                }//BIG EMAIL ELSE

            }//END onClick
        });// END ONCLICK Listener
    }

    @Override
    public void onBackPressed() {

    }
}