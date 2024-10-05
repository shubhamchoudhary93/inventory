package com.shubham.inventory.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StockDao {
    @Query("SELECT * FROM stock_table ORDER BY id ASC")
    fun getAllStockItems(): LiveData<List<StockItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(stockItem: StockItem)

    @Update
    suspend fun update(stockItem: StockItem)

    @Delete
    suspend fun delete(stockItem: StockItem)

    @Query("DELETE FROM stock_table WHERE itemName = :stockName")
    suspend fun deleteByName(stockName: String)
}
