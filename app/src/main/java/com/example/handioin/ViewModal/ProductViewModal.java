package com.example.handioin.ViewModal;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.handioin.ModelClass.ProductResponseModal;
import com.example.handioin.Respository.ProductRespository;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class ProductViewModal extends AndroidViewModel {
    private ProductRespository respository;
    private LiveData<List<ProductResponseModal>> getAllProduct;

    public ProductViewModal(@NonNull Application application) {
        super(application);
        respository = new ProductRespository(application);
        getAllProduct = respository.getGetAllProduct();
    }

    public void insertList(List<ProductResponseModal> productModalList) {
        // Toast.makeText(getApplication(), "data"+productModalList.get(0), Toast.LENGTH_SHORT).show();
        respository.insertCardItem(productModalList);
    }

    public void updateList(List<ProductResponseModal> productResponseModals) {
        respository.updateCardItem(productResponseModals);
    }

    public void deleteList(List<ProductResponseModal> productResponseModals) {
        respository.deleteCardItem(productResponseModals);
    }

    public void updateQuantity(int productId, int quantity) {
        respository.updateQuantityList(productId, quantity);
    }

    public void updatePrice(int productId, int totalItemPrice) {
        respository.updatePrice(productId, totalItemPrice);

    }

    public void DeleteAllItemFromTeble(List<ProductResponseModal> productResponseModals) {
        respository.DeleteAllItemFromTeble(productResponseModals);
    }

    public LiveData<List<ProductResponseModal>> getGetAllProduct() {
        return getAllProduct;
    }

/*https://stackoverflow.com/questions/
61463186/how-to-sort-livedata-from-room-database-
doing-a-button-to-switch-recyclerviews*/


//    public void sortByStars(){
//        Collection.sort((List<ProductResponseModal>) getAllProduct, ByStars);
//    }
//    public Comparator<ProductResponseModal> ByStars = new Comparator<ProductResponseModal>() {
//        @Override
//        public int compare(ProductResponseModal o1, ProductResponseModal o2) {
//           return 0;
//        }
//    };

}


