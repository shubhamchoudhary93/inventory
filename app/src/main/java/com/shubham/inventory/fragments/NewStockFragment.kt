package com.shubham.inventory.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.shubham.inventory.data.StockItem
import com.shubham.inventory.data.StockViewModel
import com.shubham.inventory.databinding.FragmentNewStockBinding

class NewStockFragment : Fragment() {

    private lateinit var binding: FragmentNewStockBinding
    private val stockViewModel: StockViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewStockBinding.inflate(inflater, container, false)

        binding.btnSave.setOnClickListener {
            if (validateInput()) {
                val itemName = binding.etItemName.text.toString()
                val category = binding.etCategory.text.toString()
                val openingQty = binding.etOpeningQty.text.toString().toInt()
                val quantity = openingQty

                val stockItem = StockItem(itemName = itemName, category= category, openingQty = openingQty, quantity = quantity)
                stockViewModel.insert(stockItem)

                // Clear the fields after saving
                binding.etItemName.text.clear()
                binding.etCategory.text.clear()
                binding.etOpeningQty.text.clear()
            }
        }

        return binding.root
    }

    // Function to validate user input
    private fun validateInput(): Boolean {
        var isValid = true

        val itemName = binding.etItemName.text.toString()
        val category = binding.etCategory.text.toString()
        val openingQtyStr = binding.etOpeningQty.text.toString()

        if (itemName.isBlank()) {
            binding.etItemName.error = "Item name cannot be empty"
            isValid = false
        }

        if (category.isBlank()) {
            binding.etCategory.error = "Category cannot be empty"
            isValid = false
        }

        if (openingQtyStr.isBlank()) {
            binding.etOpeningQty.error = "Opening Quantity cannot be empty"
            isValid = false
        } else {
            try {
                val openingQty = openingQtyStr.toInt()
                if (openingQty < 0) {
                    binding.etOpeningQty.error = "Opening Quantity cannot be negative"
                    isValid = false
                }
            } catch (e: NumberFormatException) {
                binding.etOpeningQty.error = "Invalid number"
                isValid = false
            }
        }

        return isValid
    }
}
