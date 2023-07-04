package com.example.handioin.RetrofitClient.InternetClass;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.handioin.ModelClass.ProductResponseModal;

import java.util.List;

@SuppressWarnings("AndroidUnresolvedRoomSqlReference")
@Dao
public interface ProductDao {

    @Query("SELECT * FROM productRequest ")
    LiveData<List<ProductResponseModal>> getAllProduct();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCardItem(List<ProductResponseModal> ProductResponseModalList);

    @Update
    void updateCardItem(ProductResponseModal model);

    @Delete
    void deleteCardItem(List<ProductResponseModal> ProductResponseModalList);

    @Query("UPDATE productRequest SET quantity=:quantity WHERE productId=:productId")
    void updateQuantity(int productId, int quantity);

    @Query("UPDATE productRequest SET totalItemPrice=:totalItemPrice WHERE productId=:productId")
    void updatePrice(int productId, int totalItemPrice);

    @Query("DELETE FROM productRequest")
    void DeleteFromTable();

//    @Query("SELECT note_table.*, counter.count FROM note_table LEFT JOIN (SELECT note_table.location, count(note_table.location) as count FROM note_table GROUP BY note_table.location) counter ON counter.location = note_table.location ORDER BY counter.count DESC, stars DESC")
//    LiveData<List<ProductResponseModal>> getAllNotes();
//
//    @Query("SELECT note_table.*, counter.count FROM note_table LEFT JOIN (SELECT note_table.location, count(note_table.location) as count FROM note_table GROUP BY note_table.location) counter ON counter.location = note_table.location ORDER BY stars DESC, counter.count DESC")
//    LiveData<List<ProductResponseModal>> getAllNotesStars();

}
