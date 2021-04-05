package com.optiflex.employeefrontendandroid.employeeProcessing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.optiflex.employeefrontendandroid.R;

public class InitialActivity extends AppCompatActivity {

    private EditText edt_username,edt_password;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        getSupportActionBar().hide();
        edt_username = findViewById(R.id.initial_activity_edt_username);
        edt_password = findViewById(R.id.initial_activity_edt_password);
        btn_login = findViewById(R.id.initial_activity_btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(edt_username.getText().toString().toLowerCase().trim().equals("zuka") && edt_password.getText().toString().toLowerCase().trim().equals("zuka"))
                    {
                        Intent intentMain = new Intent(InitialActivity.this,EmployeeMain.class);
                        startActivity(intentMain);
                        finish();
                    }else{
                        Toast.makeText(InitialActivity.this,"Please Provide Correct Login Details",Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}