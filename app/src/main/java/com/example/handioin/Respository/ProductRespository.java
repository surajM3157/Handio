package com.example.handioin.Respository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.handioin.DBHelper.DataBaseHelper;
import com.example.handioin.ModelClass.ProductResponseModal;
import com.example.handioin.RetrofitClient.InternetClass.ProductDao;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ProductRespository {
    private DataBaseHelper database;
    private ProductDao productDao;
    private LiveData<List<ProductResponseModal>> getAllProduct;
    private Executor executor = Executors.newSingleThreadExecutor();
    private LiveData<List<ProductResponseModal>> AllProduct;


    public ProductRespository(Application application) {
        database = DataBaseHelper.getInstance(application);
        getAllProduct = database.databaseInterface().getAllProduct();
        AllProduct = database.databaseInterface().getAllProduct();

    }

    public void insertCardItem(List<ProductResponseModal> ProductResponseModalList) {
        new InsertAsynTask(database).execute(ProductResponseModalList);
    }

//    public void deleteCardItem(List<ProductResponseModal> ProductResponseModalList) {
//        new deleteCardItem(database).execute(ProductResponseModalList);
//    }

    public void deleteCardItem(List<ProductResponseModal> productResponseModals){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                productDao.deleteCardItem(productResponseModals);
            }
        });
    }

    public void updateQuantityList(int productId, int quantity) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                productDao.updateQuantity(productId, quantity);
                Log.d("dddddd","ffffff" + productId+quantity);
            }
        });
    }


    public void updatePrice(int productId, int totalItemPrice) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Log.d("ggggg","ggggg"+productId+totalItemPrice);
                productDao.updatePrice(productId, totalItemPrice);
            }
        });
    }

    public void updateCardItem(List<ProductResponseModal> ProductResponseModalList) {
        new UpdataAsynTask(database).execute(ProductResponseModalList);
    }

    public void DeleteAllItemFromTeble(List<ProductResponseModal> ProductResponseModalList) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                productDao.DeleteFromTable();
            }
        });
    }

    public MediatorLiveData<List<ProductResponseModal>> getAllNotes() {
        return (MediatorLiveData<List<ProductResponseModal>>) AllProduct;
    }

    public LiveData<List<ProductResponseModal>> getGetAllProduct() {
        return getAllProduct;
    }

    private static class InsertAsynTask extends AsyncTask<List<ProductResponseModal>, Void, Void> {
        private ProductDao productDao;

        private InsertAsynTask(DataBaseHelper dataBaseHelper) {
            productDao = (ProductDao) dataBaseHelper.databaseInterface();
        }

        @SafeVarargs
        @Override
        protected final Void doInBackground(List<ProductResponseModal>... lists) {
            productDao.insertCardItem(lists[0]);
            return null;
        }
    }

    private static class UpdataAsynTask extends AsyncTask<List<ProductResponseModal>, Void, Void> {
        private ProductDao productDao1;

        private UpdataAsynTask(DataBaseHelper dataBaseHelper) {
            productDao1 = dataBaseHelper.databaseInterface();
        }

        @Override
        protected Void doInBackground(List<ProductResponseModal>... lists1) {
            productDao1.updateCardItem((ProductResponseModal) lists1[0]);
            return null;
        }
    }


    private static class deleteCardItem extends AsyncTask<List<ProductResponseModal>, Void, Void> {
        private ProductDao productDao2;

        private deleteCardItem(DataBaseHelper dataBaseHelper) {
            productDao2 = dataBaseHelper.databaseInterface();
        }

        @Override
        protected Void doInBackground(List<ProductResponseModal>... lists2) {
            productDao2.deleteCardItem(lists2[0]);
            return null;
        }
    }

    private static class DeleteFromTable extends AsyncTask<Void, Void, Void> {
        private ProductDao productDao3;

        private DeleteFromTable(DataBaseHelper dataBaseHelper) {
            productDao3 = dataBaseHelper.databaseInterface();
        }

        @Override
        protected Void doInBackground(Void... Void) {
            productDao3.DeleteFromTable();
            return null;
        }
    }
}
