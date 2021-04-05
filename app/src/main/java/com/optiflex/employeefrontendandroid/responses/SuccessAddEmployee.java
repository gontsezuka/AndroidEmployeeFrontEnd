package com.optiflex.employeefrontendandroid.responses;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.optiflex.employeefrontendandroid.MainActivity;
import com.optiflex.employeefrontendandroid.R;

public class SuccessAddEmployee extends AppCompatActivity {


    Button btn_continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_add_employee);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("SUCCESS");

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FFFFFF"));
        actionBar.setBackgroundDrawable(colorDrawable);

        btn_continue = findViewById(R.id.success_add_employee_btn_continue);

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuccessAddEmployee.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(SuccessAddEmployee.this,"Click Button To Continue",Toast.LENGTH_SHORT).show();
    }
}