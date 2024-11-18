package com.djiwo.harusjadi;

import android.content.Context;

import androidx.room.Room;

public class DataBaseClient {
    private static DataBaseClient mInstance;
    AppDataBase.AppDatabase mAppDatabase;

    private DataBaseClient(Context context) {
        mAppDatabase = Room.databaseBuilder(context, AppDataBase.AppDatabase.class, "catering_db")
                .fallbackToDestructiveMigration()
                .build();
    }

    //untuk sync data secara realtime
    public static synchronized DataBaseClient getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DataBaseClient(context);
        }
        return mInstance;
    }

    public AppDataBase.AppDatabase getAppDatabase() {
        return mAppDatabase;
    }

}
