package com.optiflex.employeefrontendandroid.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.optiflex.employeefrontendandroid.model.EmployeeModel;
import com.optiflex.employeefrontendandroid.repository.EmployeeRepository;

import java.util.List;

public class EmployeeViewModel extends AndroidViewModel {

    private EmployeeRepository employeeRepository;

    public EmployeeViewModel(@NonNull Application application) {
        super(application);
        employeeRepository = new EmployeeRepository(application);
    }

    /**
     * RETURN METHODS TO FRONT-END
     */

    public LiveData<List<EmployeeModel>> getAllEmployees()
    {
        return employeeRepository.getAllEmployee();
    }

    public LiveData<EmployeeModel> getEmployee()
    {
        return employeeRepository.getEmployee();
    }
    /**
     * METHODS TO BACK-END
     */

    public void findAllEmployees() {

         employeeRepository.findAllEmployees();
    }

    public void saveEmployee(EmployeeModel employeeModel) {
        employeeRepository.saveEmployee(employeeModel);
    }

    public void updateEmployee(EmployeeModel employeeModel)
    {
        employeeRepository.updateEmployee(employeeModel);
    }


    public void deleteEmployee(Long employeeId)
    {
        employeeRepository.deleteEmployee(employeeId);
    }

}
