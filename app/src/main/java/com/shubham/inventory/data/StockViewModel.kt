package com.shubham.inventory.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    fun getAllItemNames(onNamesFetched: (List<String>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val itemNames = repository.getAllItemNames()
            withContext(Dispatchers.Main) {
                onNamesFetched(itemNames) // Callback to return the names on the main thread
            }
        }
    }

    fun getItemByName(itemName: String, onItemFetched: (StockItem?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val stockItem = repository.getItemByName(itemName)
            withContext(Dispatchers.Main) {
                // This ensures that the result is returned on the main thread (UI thread)
                onItemFetched(stockItem)
            }
        }
    }
}
