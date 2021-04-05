package com.optiflex.employeefrontendandroid.employeeProcessing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.optiflex.employeefrontendandroid.R;

public class FragmentEmployeeDashboard  extends Fragment {
    View view;
    private TextView txt_employee_total,txt_meetings_today_total;
    private Button btn_list_employees,btn_list_meetings;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_employee_dashboard,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txt_employee_total = view.findViewById(R.id.fragment_employee_dashboard_txt_list_total_employees);
        txt_meetings_today_total = view.findViewById(R.id.fragment_employee_dashboard_txt_list_total_meetings_today);
        btn_list_employees = view.findViewById(R.id.fragment_employee_dashboard_btn_view_list_employees);
        btn_list_meetings = view.findViewById(R.id.fragment_employee_dashboard_btn_view_meetings_today);

        btn_list_meetings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * TODO LIST ALL MEETINGS TODAY
                 */
            }
        });

        btn_list_meetings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentEmployeeListMain()).commit();
            }
        });
    }

}
