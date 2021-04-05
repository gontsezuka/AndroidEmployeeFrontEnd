package com.optiflex.employeefrontendandroid.apidao;

import androidx.room.Delete;

import com.optiflex.employeefrontendandroid.model.EmployeeModel;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EmployeeDaoAPI {

    @GET("employees/find-all")
    public Call<List<EmployeeModel>> findAllEmployee();

    @GET("employees/find-by-id/{employeeid}")
    public Call<EmployeeModel> findEmployeeById(@Path("employeeid")Long employeeId);

    @POST("employees/save-employee")
    public Call<EmployeeModel> saveEmployee(@Body EmployeeModel employeeModel);

    @PUT("employees/update-employee-return")
    public Call<EmployeeModel> updateEmployee(@Body EmployeeModel employeeModel);

    @DELETE("employees/delete-employee/{employeeid}")
    public Call<Void> deleteEmployee(@Path("employeeid")Long employeeId);

}
