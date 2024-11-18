package com.djiwo.harusjadi;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.djiwo.harusjadi.database.DataBaseModel;

public class AppDataBase {
    //untuk entitas model database
    @Database(entities = {DataBaseModel.DatabaseModel.class}, version = 1)
    public abstract class AppDatabase extends RoomDatabase {
        public abstract DatabaseDao databaseDao();
    }
}
