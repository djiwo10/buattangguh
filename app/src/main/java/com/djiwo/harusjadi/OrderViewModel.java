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

public class OrderViewModel extends AndroidViewModel {

    LiveData<List<DataBaseModel.DatabaseModel>> modelDatabase;
    DatabaseDao databaseDao;

    //untuk inisialisasi databaseDao
    public OrderViewModel(@NonNull Application application) {
        super(application);

        databaseDao = DataBaseClient.getInstance(application).getAppDatabase().databaseDao();
    }

    //untuk mengambil data dari database
    public LiveData<List<DataBaseModel.DatabaseModel>> getDataIdUser() {
        modelDatabase = databaseDao.getAllOrder();
        return modelDatabase;
    }

    //untuk update data berdasarkan Id secara realtime
    public void addDataOrder(final String strMenu, final int strJmlItems, final int strHarga) {
        Completable.fromAction(() -> {
                    DataBaseModel.DatabaseModel databaseModel = DataBaseModel.DatabaseModel();
                    databaseModel.nama_menu = strMenu;
                    databaseModel.items = strJmlItems;
                    databaseModel.harga = strHarga;
                    databaseDao.insertData(databaseModel);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

}
