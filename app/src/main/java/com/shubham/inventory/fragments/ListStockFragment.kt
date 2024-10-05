package com.shubham.inventory.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.shubham.inventory.R
import com.shubham.inventory.data.StockViewModel
import com.shubham.inventory.databinding.FragmentListStockBinding

class ListStockFragment : Fragment() {

    private lateinit var stockViewModel: StockViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentListStockBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_list_stock, container, false
        )

        stockViewModel = ViewModelProvider(this).get(StockViewModel::class.java)
        val adapter = StockListAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        stockViewModel.allStockItems.observe(viewLifecycleOwner) { items ->
            adapter.submitList(items)
        }

        return binding.root
    }
}
