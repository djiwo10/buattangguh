package com.djiwo.harusjadi;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.djiwo.harusjadi.database.DataBaseModel;

import java.util.List;

public class LoginViewModel extends AndroidViewModel {

    LiveData<List<DataBaseModel.DatabaseModel>> modelDatabase;
    DatabaseDao databaseDao;

    //untuk inisialisasi databaseDao
    public LoginViewModel(@NonNull Application application) {
        super(application);

        databaseDao = DataBaseClient.getInstance(application).getAppDatabase().databaseDao();
    }

    //untuk cek login mengambil data dari database
    public LiveData<List<DataBaseModel.DatabaseModel>> getDataUser(String username, String password) {
        modelDatabase = databaseDao.getUserByName(username, password);
        return modelDatabase;
    }

}