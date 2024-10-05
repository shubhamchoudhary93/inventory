package com.shubham.inventory.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shubham.inventory.data.StockItem
import com.shubham.inventory.databinding.ItemStockBinding

class StockListAdapter(private val listener: OnItemClickListener) :
    ListAdapter<StockItem, StockListAdapter.StockViewHolder>(StockDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val binding = ItemStockBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StockViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class StockViewHolder(
        private val binding: ItemStockBinding,
        private val listener: OnItemClickListener // Store the listener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(stockItem: StockItem) {
            binding.itemName.text = stockItem.itemName
            binding.itemQuantity.text = stockItem.quantity.toString()
            binding.itemDescription.text = stockItem.description

            // Set the click listener inside the bind method
            binding.root.setOnClickListener {
                listener.onItemClick(stockItem) // Now stockItem is in scope
            }
        }
    }
}

class StockDiffCallback : DiffUtil.ItemCallback<StockItem>() {
    override fun areItemsTheSame(oldItem: StockItem, newItem: StockItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: StockItem, newItem: StockItem): Boolean {
        return oldItem == newItem
    }
}
