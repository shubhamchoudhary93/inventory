package com.shubham.inventory.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shubham.inventory.data.StockItem
import com.shubham.inventory.databinding.FragmentStockDetailBinding

class StockDetailFragment : Fragment() {

    private lateinit var binding: FragmentStockDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStockDetailBinding.inflate(inflater, container, false)

        // Get the stock item passed in the arguments
        val stockItem = arguments?.getParcelable<StockItem>("stockItem")

        // Populate the fields with the stock information
        stockItem?.let {
            binding.tvItemName.text = it.itemName
            binding.tvCategory.text = it.category
            binding.tvOpeningQty.text = it.openingQty.toString()
            binding.tvItemQuantity.text = it.quantity.toString()
        }

        return binding.root
    }
}