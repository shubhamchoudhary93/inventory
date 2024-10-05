package com.shubham.inventory.fragments

import com.shubham.inventory.data.StockItem

interface OnItemClickListener {
    fun onItemClick(stockItem: StockItem)
}