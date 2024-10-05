package com.shubham.inventory.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StockViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: StockRepository
    val allStockItems: LiveData<List<StockItem>>

    init {
        val stockDao = StockDatabase.getDatabase(application).stockDao()
        repository = StockRepository(stockDao)
        allStockItems = repository.allStockItems
    }

    fun insert(stockItem: StockItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(stockItem)
    }

    fun update(stockItem: StockItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(stockItem)
    }

    fun delete(stockItem: StockItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(stockItem)
    }

    fun deleteByName(stockName: String) {
        viewModelScope.launch {
            repository.deleteByName(stockName)
        }
    }
}
