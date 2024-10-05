package com.shubham.inventory.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.shubham.inventory.data.StockItem
import com.shubham.inventory.data.StockViewModel
import com.shubham.inventory.databinding.FragmentNewStockBinding

class NewStockFragment : Fragment() {

    private lateinit var stockViewModel: StockViewModel
    private lateinit var binding: FragmentNewStockBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewStockBinding.inflate(inflater, container, false)

        stockViewModel = ViewModelProvider(this).get(StockViewModel::class.java)

        binding.btnSave.setOnClickListener {
            val itemName = binding.etItemName.text.toString()
            val quantity = binding.etQuantity.text.toString().toInt()
            val description = binding.etDescription.text.toString()

            val stockItem = StockItem(itemName = itemName, quantity = quantity, description = description)
            stockViewModel.insert(stockItem)

            // Clear the fields after saving
            binding.etItemName.text.clear()
            binding.etQuantity.text.clear()
            binding.etDescription.text.clear()
        }

        return binding.root
    }
}
