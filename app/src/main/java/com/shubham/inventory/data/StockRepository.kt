package com.shubham.inventory.data

import androidx.lifecycle.LiveData

class StockRepository(private val stockDao: StockDao) {

    val allStockItems: LiveData<List<StockItem>> = stockDao.getAllStockItems()

    suspend fun insert(stockItem: StockItem) {
        stockDao.insert(stockItem)
    }

    suspend fun update(stockItem: StockItem) {
        stockDao.update(stockItem)
    }

    suspend fun delete(stockItem: StockItem) {
        stockDao.delete(stockItem)
    }
    suspend fun deleteByName(stockName: String) {
        stockDao.deleteByName(stockName)
    }
    suspend fun getAllItemNames(): List<String> {
        return stockDao.getAllItemNames()
    }
    suspend fun getItemByName(itemName: String): StockItem? {
        return stockDao.getItemByName(itemName)
    }
}
