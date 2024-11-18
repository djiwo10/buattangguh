package com.djiwo.harusjadi;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.djiwo.harusjadi.database.DataBaseModel;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HistoryViewModel extends AndroidViewModel {

    LiveData<List<DataBaseModel.DatabaseModel>> modelDatabase;
    DatabaseDao databaseDao;

    //untuk inisialisasi databaseDao
    public HistoryViewModel(@NonNull Application application) {
        super(application);

        databaseDao = DataBaseClient.getInstance(application).getAppDatabase().databaseDao();
        modelDatabase = databaseDao.getAllOrder();
    }

    //untuk menampilkan data dari database ke recyclerview
    public LiveData<List<DataBaseModel.DatabaseModel>> getDataList() {
        return modelDatabase;
    }

    //untuk menghapus data berdasarkan Id secara realtime
    public void deleteDataById(final int uid) {
        Completable.fromAction(() -> databaseDao.deleteSingleData(uid))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

}