package com.shubham.inventory.fragments

import android.view.LayoutInflater
import android.view.View
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
        return StockViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class StockViewHolder(
        private val binding: ItemStockBinding
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener  {

        private lateinit var currentStockItem: StockItem

        init {
            // Set the OnClickListener for the whole item view
            itemView.setOnClickListener(this)
        }


        fun bind(stockItem: StockItem) {
            currentStockItem = stockItem
            binding.itemName.text = stockItem.itemName
            binding.itemCategory.text = stockItem.category
            binding.itemOpeningQty.text = stockItem.openingQty.toString()
            binding.itemQuantity.text = stockItem.quantity.toString()

            // Set the click listener inside the bind method
            binding.root.setOnClickListener {
                listener.onItemClick(stockItem) // Now stockItem is in scope
            }

        }

        // Implement the onClick method as part of View.OnClickListener
        override fun onClick(v: View?) {
            listener.onItemClick(currentStockItem)  // Pass the clicked item to the listener
        }
    }


    // Interface for click listener
    interface OnItemClickListener {
        fun onItemClick(stockItem: StockItem)
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
