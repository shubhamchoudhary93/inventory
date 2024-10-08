package com.shubham.inventory.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.shubham.inventory.data.StockItem
import com.shubham.inventory.data.StockViewModel
import com.shubham.inventory.databinding.FragmentUpdateStockBinding
import com.shubham.inventory.R

class UpdateStockFragment : Fragment() {

    private lateinit var stockViewModel: StockViewModel
    private lateinit var binding: FragmentUpdateStockBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateStockBinding.inflate(inflater, container, false)
        stockViewModel = ViewModelProvider(this).get(StockViewModel::class.java)

        // Fetch the item names and set up the dropdown
        stockViewModel.getAllItemNames { itemNames ->
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, itemNames)
            binding.etItemName.setAdapter(adapter)
            binding.etItemName.threshold = 1

            // When an item is selected, fetch its details and populate the fields
            binding.etItemName.setOnItemClickListener { parent, _, position, _ ->
                val selectedItemName = parent.getItemAtPosition(position).toString()
                stockViewModel.getItemByName(selectedItemName) { stockItem ->
                    if (stockItem != null) {
                        // Pre-fill the quantity and description fields
                        binding.etQuantity.setText(stockItem.quantity.toString())
                        binding.etDescription.setText(stockItem.description)
                    } else {
                        showToast("Item not found.")
                        clearFields() // Clear fields if item not found
                    }
                }
            }
        }

        // Update button logic
        binding.btnUpdate.setOnClickListener {
            val itemName = binding.etItemName.text.toString()
            val newQuantityStr = binding.etQuantity.text.toString()
            val newDescription = binding.etDescription.text.toString()

            if (itemName.isBlank() || newQuantityStr.isBlank() || newDescription.isBlank()) {
                showToast("Please fill all fields.")
                return@setOnClickListener
            }

            try {
                val newQuantity = newQuantityStr.toInt()

                // Fetch item by name first to get its `id`
                stockViewModel.getItemByName(itemName) { stockItem ->
                    if (stockItem != null) {
                        // Update the existing stock item with new values
                        val updatedStockItem = stockItem.copy(
                            quantity = newQuantity,
                            description = newDescription
                        )
                        stockViewModel.update(updatedStockItem)
                        showToast("Stock updated successfully")

                        // Clear the fields after updating
                        clearFields()
                    } else {
                        showToast("Item not found.")
                    }
                }

            } catch (e: NumberFormatException) {
                showToast("Invalid quantity. Please enter a valid number.")
            }
        }

        // Delete button logic
        binding.btnDelete.setOnClickListener {
            val itemName = binding.etItemName.text.toString()

            if (itemName.isBlank()) {
                showToast("Please select an item to delete.")
                return@setOnClickListener
            }

            stockViewModel.getItemByName(itemName) { stockItem ->
                if (stockItem != null) {
                    stockViewModel.deleteByName(itemName)
                    showToast("Item deleted successfully")

                    // Clear the fields after deletion
                    clearFields()
                } else {
                    showToast("Item not found.")
                }
            }
        }

        return binding.root
    }

    private fun clearFields() {
        binding.etItemName.text.clear()
        binding.etQuantity.text.clear()
        binding.etDescription.text.clear()
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}