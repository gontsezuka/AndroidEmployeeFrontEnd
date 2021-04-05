package com.optiflex.employeefrontendandroid.repository;

import android.app.Application;
import android.content.Intent;
import android.support.v4.media.MediaBrowserCompat;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.optiflex.employeefrontendandroid.apidao.EmployeeDaoAPI;
import com.optiflex.employeefrontendandroid.model.EmployeeModel;
import com.optiflex.employeefrontendandroid.responses.ErrorFailedToLoadEmployees;
import com.optiflex.employeefrontendandroid.retrofitcon.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeRepository {

    private EmployeeDaoAPI employeeDaoAPI;
    private MutableLiveData<List<EmployeeModel>> mutableLiveDataList = new MutableLiveData<>();
    private MutableLiveData<EmployeeModel> modelMutableLiveData = new MutableLiveData<>();
    private Application application;
    public EmployeeRepository(Application application)
    {
        employeeDaoAPI = RetrofitService.createService(EmployeeDaoAPI.class);
        this.application = application;
    }


    public LiveData<List<EmployeeModel>> getAllEmployee()
    {
        return this.mutableLiveDataList;
    }

    public LiveData<EmployeeModel> getEmployee()
    {
        return this.modelMutableLiveData;
    }


    /**
     * METHODS TO BACKEND
     */

    public void findAllEmployees()
    {
        Call<List<EmployeeModel>> callFindAll = employeeDaoAPI.findAllEmployee();

        callFindAll.enqueue(new Callback<List<EmployeeModel>>() {
            @Override
            public void onResponse(Call<List<EmployeeModel>> call, Response<List<EmployeeModel>> response) {
                if(response.isSuccessful())
                {
                    mutableLiveDataList.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<EmployeeModel>> call, Throwable t) {
                Intent intent = new Intent(application.getApplicationContext(), ErrorFailedToLoadEmployees.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                application.startActivity(intent);
            }
        });
    }

    public void saveEmployee(EmployeeModel employeeModel)
    {
        Call<EmployeeModel> callSave= employeeDaoAPI.saveEmployee(employeeModel);

        callSave.enqueue(new Callback<EmployeeModel>() {
            @Override
            public void onResponse(Call<EmployeeModel> call, Response<EmployeeModel> response) {
                if(response.isSuccessful())
                {
                    modelMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<EmployeeModel> call, Throwable t) {

            }
        });
    }

    public void updateEmployee(EmployeeModel employeeModel)
    {
        Call<EmployeeModel> callUpdate = employeeDaoAPI.updateEmployee(employeeModel);

        callUpdate.enqueue(new Callback<EmployeeModel>() {
            @Override
            public void onResponse(Call<EmployeeModel> call, Response<EmployeeModel> response) {
                if(response.isSuccessful())
                {
                    modelMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<EmployeeModel> call, Throwable t) {

            }
        });
    }

    public void deleteEmployee(Long employeeId)
    {
        Call<Void> callDelete = employeeDaoAPI.deleteEmployee(employeeId);

        callDelete.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful())
                {
                    return;
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }


}
