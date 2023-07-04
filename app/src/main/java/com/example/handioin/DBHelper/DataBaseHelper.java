package com.example.handioin.DBHelper;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.handioin.ModelClass.ProductResponseModal;
import com.example.handioin.ModelClass.productRequestModal;
import com.example.handioin.RetrofitClient.InternetClass.ProductDao;

@Database(entities = ProductResponseModal.class, exportSchema = false, version = 8)

public abstract class DataBaseHelper extends RoomDatabase {

    private static final String DB_NAME = "productRequest";
    private static DataBaseHelper instance;
    public abstract ProductDao databaseInterface();
    public static synchronized DataBaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, DataBaseHelper.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }

    static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsynTask(instance);
        }
    };

    static class PopulateAsynTask extends AsyncTask<Void, Void, Void> {
        private ProductDao productDao;

        PopulateAsynTask(DataBaseHelper dataBaseHelper) {
            productDao = dataBaseHelper.databaseInterface();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            productDao.DeleteFromTable();
            return null;
        }
    }

}
