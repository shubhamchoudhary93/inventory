package com.shubham.inventory.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.shubham.inventory.R
import com.shubham.inventory.data.StockItem
import com.shubham.inventory.data.StockViewModel
import com.shubham.inventory.databinding.FragmentListStockBinding

class ListStockFragment : Fragment(), OnItemClickListener {

    private lateinit var binding: FragmentListStockBinding
    private val stockViewModel: StockViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout using DataBindingUtil
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_stock, container, false)

        // Initialize the RecyclerView and Adapter
        val adapter = StockListAdapter(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Observe the LiveData from the ViewModel and submit the list to the adapter
        stockViewModel.allStockItems.observe(viewLifecycleOwner) { items ->
            adapter.submitList(items)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clear binding reference to prevent memory leaks
        binding.unbind()
    }

    override fun onItemClick(stockItem: StockItem) {
        // Handle the click event (e.g., navigate to a details screen)
        // Example: show a toast, or navigate to an edit screen
        Toast.makeText(requireContext(), "Clicked: ${stockItem.itemName}", Toast.LENGTH_SHORT).show()
    }
}