package com.shubham.inventory.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "stock_table")
@Parcelize
data class StockItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val itemName: String,
    val category: String,
    val openingQty: Int,
    val quantity: Int
) : Parcelable
