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
                val quantity = binding.etQuantity.text.toString().toInt()
                val description = binding.etDescription.text.toString()

                val stockItem = StockItem(itemName = itemName, quantity = quantity, description = description)
                stockViewModel.insert(stockItem)

                // Clear the fields after saving
                binding.etItemName.text.clear()
                binding.etQuantity.text.clear()
                binding.etDescription.text.clear()
            }
        }

        return binding.root
    }

    // Function to validate user input
    private fun validateInput(): Boolean {
        var isValid = true

        val itemName = binding.etItemName.text.toString()
        val quantityStr = binding.etQuantity.text.toString()
        val description = binding.etDescription.text.toString()

        if (itemName.isBlank()) {
            binding.etItemName.error = "Item name cannot be empty"
            isValid = false
        }

        if (quantityStr.isBlank()) {
            binding.etQuantity.error = "Quantity cannot be empty"
            isValid = false
        } else {
            try {
                val quantity = quantityStr.toInt()
                if (quantity < 0) {
                    binding.etQuantity.error = "Quantity cannot be negative"
                    isValid = false
                }
            } catch (e: NumberFormatException) {
                binding.etQuantity.error = "Invalid number"
                isValid = false
            }
        }

        if (description.isBlank()) {
            binding.etDescription.error = "Description cannot be empty"
            isValid = false
        }

        return isValid
    }
}
