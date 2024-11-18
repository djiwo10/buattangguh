package com.djiwo.harusjadi;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.djiwo.harusjadi.database.DataBaseModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RegisterViewModel extends AndroidViewModel {

    DatabaseDao databaseDao;

    //untuk inisialisasi databaseDao
    public RegisterViewModel(@NonNull Application application) {
        super(application);

        databaseDao = DataBaseClient.getInstance(application).getAppDatabase().databaseDao();
    }

    //untuk insert data sesuai dengan menu register
    public void addDataRegister(final String strEmail, final String strUsername,
                                final String strPassword) {
        Completable.fromAction(() -> {
                    DataBaseModel.DatabaseModel databaseModel = new DataBaseModel.DatabaseModel();
                    databaseModel.email = strEmail;
                    databaseModel.username = strUsername;
                    databaseModel.password = strPassword;
                    databaseDao.insertData(databaseModel);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

}