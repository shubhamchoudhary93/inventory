package com.shubham.inventory.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.shubham.inventory.R
import com.shubham.inventory.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set onClickListener for navigation to other fragments
        binding.btnListStock.setOnClickListener {
            // Navigate to ListStockFragment
            findNavController().navigate(R.id.listStockFragment)
        }

        binding.btnNewStock.setOnClickListener {
            // Navigate to NewStockFragment
            findNavController().navigate(R.id.newStockFragment)
        }

        binding.btnUpdateStock.setOnClickListener {
            // Navigate to UpdateStockFragment
            findNavController().navigate(R.id.updateStockFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Prevent memory leaks
    }
}
