package com.optiflex.employeefrontendandroid.config;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.optiflex.employeefrontendandroid.responses.error.ErrorNoNetwork;

public class WorkerNetworkCheck extends Worker {

    public WorkerNetworkCheck(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
      repeat();
      return Result.success();
    }

    public void repeat()
    {
        Intent intent = new Intent(getApplicationContext(), ErrorNoNetwork.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);
    }
}
